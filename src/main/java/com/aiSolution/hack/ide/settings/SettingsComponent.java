package com.aiSolution.hack.ide.settings;

import com.aiSolution.hack.client.BaseModel;
import com.intellij.ui.TitledSeparator;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UI;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import org.jetbrains.annotations.NotNull;

public class SettingsComponent {

  private final JPanel mainPanel;
  private final JBTextField apiKeyField;
  private final JComboBox<BaseModel> baseModelComboBox;
  private final JBTextField accessTokenField;
  private final JBRadioButton useGPTRadioButton;

  public SettingsComponent(SettingsState settings) {
    apiKeyField = new JBTextField(settings.apiKey);
    baseModelComboBox = new BaseModelComboBox(settings.baseModel);
    accessTokenField = new JBTextField(settings.accessToken, 1);
    useGPTRadioButton = new JBRadioButton("Use OpenAI's official GPT3 API", settings.isGPTOptionSelected);
    mainPanel = FormBuilder.createFormBuilder()
        .addComponent(new TitledSeparator("Integration with ChatGPT"))
        .addComponent(createMainSelectionForm())
        .addComponentFillVertically(new JPanel(), 0)
        .getPanel();

    registerButtons();
    registerFields(settings.isChatGPTOptionSelected);
  }

  public JPanel getPanel() {
    return mainPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return apiKeyField;
  }

  @NotNull
  public String getApiKey() {
    return apiKeyField.getText();
  }

  public void setApiKey(@NotNull String apiKey) {
    apiKeyField.setText(apiKey);
  }

  @NotNull
  public String getAccessToken() {
    return accessTokenField.getText();
  }

  public void setAccessToken(@NotNull String accessToken) {
    accessTokenField.setText(accessToken);
  }

  public boolean isGPTOptionSelected() {
    return useGPTRadioButton.isSelected();
  }

  public void setUseGPTOptionSelected(boolean isSelected) {
    useGPTRadioButton.setSelected(isSelected);
  }

  public BaseModel getBaseModel() {
    return (BaseModel) baseModelComboBox.getSelectedItem();
  }

  public void setBaseModel(BaseModel baseModel) {
    baseModelComboBox.setSelectedItem(baseModel);
  }

  private JPanel createMainSelectionForm() {
    var panel = FormBuilder.createFormBuilder()
        .addVerticalGap(8)
        .addComponent(UI.PanelFactory.panel(useGPTRadioButton)
            .withComment("Requires API key")
            .createPanel())
        .addComponent(createFirstSelectionForm())
        .getPanel();
    panel.setBorder(JBUI.Borders.emptyLeft(16));
    return panel;
  }

  private JPanel createFirstSelectionForm() {
    var baseModelPanel = UI.PanelFactory.panel(baseModelComboBox)
        .withLabel("Model:")
        .createPanel();
    var apiKeyFieldPanel = UI.PanelFactory.panel(apiKeyField)
        .withLabel("API key:")
        .withComment("You can find your Secret API key in your <a href=\"https://platform.openai.com/account/api-keys\">User settings</a>.")
        .withCommentHyperlinkListener(this::handleHyperlinkClicked)
        .createPanel();

    setEqualLabelWidths(baseModelPanel, apiKeyFieldPanel);

    var panel = FormBuilder.createFormBuilder()
        .addComponent(baseModelPanel)
        .addVerticalGap(8)
        .addComponent(apiKeyFieldPanel)
        .getPanel();
    panel.setBorder(JBUI.Borders.emptyLeft(24));
    return panel;
  }


  // TODO: Find better way of doing this
  private void setEqualLabelWidths(JPanel firstPanel, JPanel secondPanel) {
    var firstLabel = firstPanel.getComponents()[0];
    var secondLabel = secondPanel.getComponents()[0];
    if (firstLabel instanceof JLabel && secondLabel instanceof JLabel) {
      firstLabel.setPreferredSize(secondLabel.getPreferredSize());
    }
  }

  private void registerButtons() {
    ButtonGroup myButtonGroup = new ButtonGroup();
    myButtonGroup.add(useGPTRadioButton);
    useGPTRadioButton.addActionListener(e -> registerFields(false));
  }

  private void registerFields(boolean isUseChatGPTOption) {
    apiKeyField.setEnabled(!isUseChatGPTOption);
    baseModelComboBox.setEnabled(!isUseChatGPTOption);
    accessTokenField.setEnabled(isUseChatGPTOption);
  }

  private void handleHyperlinkClicked(HyperlinkEvent event) {
    if (HyperlinkEvent.EventType.ACTIVATED.equals(event.getEventType())) {
      if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        try {
          Desktop.getDesktop().browse(event.getURL().toURI());
        } catch (IOException | URISyntaxException e) {
          throw new RuntimeException("Couldn't open the browser.", e);
        }
      }
    }
  }
}
