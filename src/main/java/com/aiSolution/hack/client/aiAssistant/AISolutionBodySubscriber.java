package com.aiSolution.hack.client.aiAssistant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aiSolution.hack.client.aiAssistant.response.AISolutionResponseDetail;
import com.aiSolution.hack.client.Subscriber;
import com.aiSolution.hack.client.aiAssistant.response.AISolutionResponse;
import com.aiSolution.hack.client.aiAssistant.response.AISolutionResponseError;
import java.util.function.Consumer;

public class AISolutionBodySubscriber extends Subscriber<AISolutionResponse> {

  private final Consumer<String> responseConsumer;
  private final Consumer<AISolutionResponse> onCompleteCallback;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private AISolutionResponse lastReceivedResponse;

  public AISolutionBodySubscriber(
      Consumer<String> responseConsumer,
      Consumer<AISolutionResponse> onCompleteCallback) {
    this.responseConsumer = responseConsumer;
    this.onCompleteCallback = onCompleteCallback;
  }

  protected void onRequestComplete() {
    onCompleteCallback.accept(lastReceivedResponse);
  }

  protected void onErrorOccurred() {
    responseConsumer.accept("Something went wrong. Please try again later.");
  }

  protected void send(String responsePayload, String token) {
    if (!responsePayload.isEmpty()) {
      try {
        var response = objectMapper.readValue(responsePayload, AISolutionResponse.class);
        var author = response.getMessage().getAuthor();
        if (author != null && "assistant".equals(author.getRole())) {
          var message = response.getFullMessage();
          if (lastReceivedResponse != null) {
            message = message.replace(lastReceivedResponse.getFullMessage(), "");
          }
          lastReceivedResponse = response;
          this.responseConsumer.accept(message);
        }
      } catch (JsonProcessingException e) {
        throw new RuntimeException("Unable to deserialize the payload", e);
      }
    } else {
      try {
        var response = objectMapper.readValue(token, AISolutionResponseDetail.class);
        this.responseConsumer.accept(response.getDetail());
      } catch (JsonProcessingException e) {
        tryProcessingErrorResponse(token);
      }
    }
  }

  private void tryProcessingErrorResponse(String jsonPayload) {
    try {
      var error = objectMapper.readValue(jsonPayload, AISolutionResponseError.class);
      if ("invalid_api_key".equals(error.getDetail().getCode())) {
        responseConsumer.accept(error.getDetail().getMessage());
      }
      future.complete(null);
    } catch (JsonProcessingException e) {
      future.completeExceptionally(e);
    }
  }
}
