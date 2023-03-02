package com.aiSolution.hack.client;

import com.aiSolution.hack.client.aiAssistant.AISolutionClient;
import com.aiSolution.hack.client.openai.OpenAiClient;
import com.aiSolution.hack.ide.settings.SettingsState;

public class ClientFactory {

  public Client getClient() {
    if (SettingsState.getInstance().isGPTOptionSelected) {
      return OpenAiClient.getInstance();
    }
    return AISolutionClient.getInstance();
  }
}
