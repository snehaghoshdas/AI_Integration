package com.aiSolution.hack.ide.action;

import com.aiSolution.hack.analyzer.SensitiveDataValidator;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.aiSolution.hack.client.ClientFactory;
import com.aiSolution.hack.ide.toolwindow.ToolWindowService;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public abstract class BaseAction extends AnAction {

    protected abstract String getPrompt(String selectedText);

    protected abstract void initToolWindow(ToolWindow toolWindow);

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        var project = event.getProject();
        var editor = event.getData(PlatformDataKeys.EDITOR);
        if (editor != null && project != null) {
            var toolWindowService = ApplicationManager.getApplication().getService(ToolWindowService.class);
            var selectedText = editor.getSelectionModel().getSelectedText();
            StringBuilder dlgMsg = new StringBuilder(event.getPresentation().getText() + " Selected!");
            String dlgTitle = event.getPresentation().getDescription();
            // If an element is selected in the editor, add info about it.
            Navigatable nav = event.getData(CommonDataKeys.NAVIGATABLE);

//      if (selectedText != null) {
//        dlgMsg.append(String.format("\nSelected Element: %s", nav.toString()));
//      }
            if ((null != com.aiSolution.hack.analyzer.ExcelReader.cidcheck(selectedText.toString())) || SensitiveDataValidator.containsSensitiveData(selectedText)) {
//        Messages.showMessageDialog(project, dlgMsg.toString(), "**Contains CID data!Do you want to Proceed**", Messages.getInformationIcon());
                int dialogResult = Messages.showYesNoDialog(
                        "Your code selection contains sensitive information. Are you sure you want to continue?",
                        "Confirmation",
                        Messages.getQuestionIcon()
                );

// Check user's response
                if (dialogResult == Messages.YES) {
                    new ClientFactory().getClient().clearPreviousSession();
                    initToolWindow(toolWindowService.getToolWindow(project));
                    toolWindowService.removeAll();
                    toolWindowService.paintUserMessage(selectedText);
                    toolWindowService.sendMessage(getPrompt(selectedText), project, null);
                }

            } else {
              new ClientFactory().getClient().clearPreviousSession();
              initToolWindow(toolWindowService.getToolWindow(project));
              toolWindowService.removeAll();
              toolWindowService.paintUserMessage(selectedText);
              toolWindowService.sendMessage(getPrompt(selectedText), project, null);
            }


        }
    }

    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null);
    }
}

