package com.aiSolution.hack.client.aiAssistant.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.aiSolution.hack.client.ApiResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AISolutionResponse implements ApiResponse {

  private AISolutionResponseMessage message;
  @JsonProperty("conversation_id")
  private String conversationId;

  public AISolutionResponseMessage getMessage() {
    return message;
  }

  public void setMessage(AISolutionResponseMessage message) {
    this.message = message;
  }

  public String getConversationId() {
    return conversationId;
  }

  public void setConversationId(String conversationId) {
    this.conversationId = conversationId;
  }

  public String getFullMessage() {
    return String.join("", message.getContent().getParts());
  }
}
