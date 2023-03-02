package com.aiSolution.hack.client.aiAssistant.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AISolutionResponseError {

  private AISolutionResponseErrorDetails detail;

  public AISolutionResponseErrorDetails getDetail() {
    return detail;
  }

  public void setDetail(AISolutionResponseErrorDetails detail) {
    this.detail = detail;
  }
}
