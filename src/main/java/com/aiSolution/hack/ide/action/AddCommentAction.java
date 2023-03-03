package com.aiSolution.hack.ide.action;

import com.intellij.openapi.wm.ToolWindow;

public class AddCommentAction extends BaseAction {

  protected String getPrompt(String selectedText) {
    return "Add Comments to selected code:\n" + selectedText;
  }

  protected void initToolWindow(ToolWindow toolWindow) {
    toolWindow.setTitle("Add Comments");
    toolWindow.show();
  }
}
