package com.aiSolution.hack.client.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiResponseChoice {

  private String text;
  @JsonProperty("finish_reason")
  private String finishReason;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getFinishReason() {
    return finishReason;
  }

  public void setFinishReason(String finishReason) {
    this.finishReason = finishReason;
  }
}
