package com.aiSolution.hack.client.openai;

import com.aiSolution.hack.client.openai.response.OpenAiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aiSolution.hack.client.Subscriber;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class OpenAiBodySubscriber extends Subscriber<OpenAiResponse> {

  private final Consumer<String> onCompleteCallback;
  private final StringBuilder messageBuilder = new StringBuilder();
  private final Consumer<String> responseConsumer;

  public OpenAiBodySubscriber(
      Consumer<String> responseConsumer,
      Consumer<String> onCompleteCallback) {
    this.responseConsumer = responseConsumer;
    this.onCompleteCallback = onCompleteCallback;
  }

  protected void onRequestComplete() {
    if(messageBuilder.toString().contains("@Test")) {
      String filtered = messageBuilder.toString().replaceAll("```java", " ");
      File file = new File("C:\\Users\\admin\\IdeaProjects\\zinkworks\\atm\\src\\test\\java\\");
      try {
        FileUtils.writeStringToFile(file, filtered);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
    onCompleteCallback.accept(messageBuilder.toString());
  }

  protected void onErrorOccurred() {
    responseConsumer.accept("Something went wrong. Please try again later.");
  }

  protected void send(String responsePayload, String token) {
    try {
      if (!responsePayload.isEmpty()) {
        var response = new ObjectMapper().readValue(responsePayload, OpenAiResponse.class);
        var message = response.getChoices().get(0).getText();
        messageBuilder.append(message);
        this.responseConsumer.accept(message);
      }
    } catch (JsonProcessingException e) {
      future.completeExceptionally(e);
    }
  }
}
