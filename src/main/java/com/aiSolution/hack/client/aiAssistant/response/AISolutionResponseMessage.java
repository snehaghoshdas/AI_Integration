package com.aiSolution.hack.client.aiAssistant.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AISolutionResponseMessage {

  private String id;
  private AISolutionResponseMessageAuthor author;
  private AISolutionResponseMessageContent content;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AISolutionResponseMessageAuthor getAuthor() {
    return author;
  }

  public void setAuthor(AISolutionResponseMessageAuthor author) {
    this.author = author;
  }

  public AISolutionResponseMessageContent getContent() {
    return content;
  }

  public void setContent(AISolutionResponseMessageContent content) {
    this.content = content;
  }
}
