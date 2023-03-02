package com.aiSolution.hack.ide.settings;

import com.aiSolution.hack.client.BaseModel;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
    name = "com.aiSolution.hack.ide.settings.SettingsState",
    storages = @Storage("SdkSettingsPlugin.xml")
)
public class SettingsState implements PersistentStateComponent<SettingsState> {

  public String apiKey = "";
  public String accessToken = "";
  public String reverseProxyUrl = "";
  public BaseModel baseModel = BaseModel.DAVINCI;
  public boolean isGPTOptionSelected = true;
  public boolean isChatGPTOptionSelected = false;

  public static SettingsState getInstance() {
    return ApplicationManager.getApplication().getService(SettingsState.class);
  }

  @Nullable
  @Override
  public SettingsState getState() {
    return this;
  }

  @Override
  public void loadState(@NotNull SettingsState state) {
    XmlSerializerUtil.copyBean(state, this);
  }

}
