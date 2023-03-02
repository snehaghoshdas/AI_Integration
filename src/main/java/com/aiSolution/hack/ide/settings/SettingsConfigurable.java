package com.aiSolution.hack.ide.settings;

import com.intellij.openapi.options.Configurable;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

public class SettingsConfigurable implements Configurable {

  private SettingsComponent settingsComponent;

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "ChatGPT: Settings";
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return settingsComponent.getPreferredFocusedComponent();
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    var settings = SettingsState.getInstance();
    settingsComponent = new SettingsComponent(settings);
    return settingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    var settings = SettingsState.getInstance();
    return !settingsComponent.getApiKey().equals(settings.apiKey) ||
        !settingsComponent.getAccessToken().equals(settings.accessToken) ||
        !settingsComponent.getBaseModel().equals(settings.baseModel) ||
        settingsComponent.isGPTOptionSelected() != settings.isGPTOptionSelected;
  }

  @Override
  public void apply() {
    var settings = SettingsState.getInstance();
    settings.isGPTOptionSelected = settingsComponent.isGPTOptionSelected();
    settings.accessToken = settingsComponent.getAccessToken();
    settings.apiKey = settingsComponent.getApiKey();
    settings.baseModel = settingsComponent.getBaseModel();
  }

  @Override
  public void reset() {
    var settings = SettingsState.getInstance();
    settingsComponent.setUseGPTOptionSelected(settings.isGPTOptionSelected);
    settingsComponent.setAccessToken(settings.accessToken);
    settingsComponent.setApiKey(settings.apiKey);
    settingsComponent.setBaseModel(settings.baseModel);
  }

  @Override
  public void disposeUIResources() {
    settingsComponent = null;
  }
}
