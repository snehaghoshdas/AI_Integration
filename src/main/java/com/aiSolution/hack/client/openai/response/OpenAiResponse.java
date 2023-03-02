package com.aiSolution.hack.client.openai.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.aiSolution.hack.client.ApiResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenAiResponse implements ApiResponse {

  private String id;
  private List<OpenAiResponseChoice> choices;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<OpenAiResponseChoice> getChoices() {
    return choices;
  }

  public void setChoices(List<OpenAiResponseChoice> choices) {
    this.choices = choices;
  }
}
