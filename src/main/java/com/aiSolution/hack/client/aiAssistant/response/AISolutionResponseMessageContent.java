package com.aiSolution.hack.client.aiAssistant.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AISolutionResponseMessageContent {

  @JsonProperty("content_type")
  private String contentType;
  private List<String> parts;

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public List<String> getParts() {
    return parts;
  }

  public void setParts(List<String> parts) {
    this.parts = parts;
  }
}
