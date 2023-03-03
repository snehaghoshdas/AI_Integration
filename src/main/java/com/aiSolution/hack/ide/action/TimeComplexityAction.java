package com.aiSolution.hack.ide.action;

import com.intellij.openapi.wm.ToolWindow;


public class TimeComplexityAction extends BaseAction {

    protected String getPrompt(String selectedText) {
        return "Find Time complexity for the following code:\n" + selectedText;
    }

    protected void initToolWindow(ToolWindow toolWindow) {
        toolWindow.setTitle("Calculate TimeComplexity");
        toolWindow.show();
    }

//  Write method to sum two number
}

