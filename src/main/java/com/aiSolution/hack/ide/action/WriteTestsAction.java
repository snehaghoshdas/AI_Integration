package com.aiSolution.hack.ide.action;

import com.intellij.openapi.wm.ToolWindow;

public class WriteTestsAction extends BaseAction {

  protected String getPrompt(String selectedText) {
    return "Generate unit tests for the following code:\n" + selectedText;
  }

  protected void initToolWindow(ToolWindow toolWindow) {
    toolWindow.setTitle("Write Tests");
    toolWindow.show();
  }
}
