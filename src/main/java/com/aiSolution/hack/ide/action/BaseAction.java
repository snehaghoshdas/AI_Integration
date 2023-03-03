package com.aiSolution.hack.ide.action;

import com.aiSolution.hack.analyzer.SensitiveDataValidator;
import com.aiSolution.hack.client.ClientFactory;
import com.aiSolution.hack.ide.toolwindow.ToolWindowService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import static com.aiSolution.hack.analyzer.ExcelReader.cidcheck;

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
             // If an element is selected in the editor, add info about it.
            if ((null != cidcheck(selectedText.toString()))) {
                   Messages.showMessageDialog(project, dlgMsg.toString(), "**Contains CID data!**", Messages.getErrorIcon());

            } else if(SensitiveDataValidator.containsSensitiveData(selectedText)){
                Messages.showInfoMessage(
                        "Your code selection contains sensitive information. Please remove or mask the sensitive data",
                        "Sensitive data found!!");
            }else {
                getResponseFromAIAssistant(project, toolWindowService, selectedText);
            }

        }
    }

    private void getResponseFromAIAssistant(Project project, ToolWindowService toolWindowService, String selectedText) {
        new ClientFactory().getClient().clearPreviousSession();
        initToolWindow(toolWindowService.getToolWindow(project));
        toolWindowService.removeAll();
        toolWindowService.paintUserMessage(selectedText);
        toolWindowService.sendMessage(getPrompt(selectedText), project, null);
    }

    public void update(AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(e.getProject() != null);
    }
}

