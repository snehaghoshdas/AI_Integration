/*
package com.aiSolution.hack.employee;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.ui.Messages;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder implements StartupActivity.DumbAware {





    private Timer timer;
    private Project project;

    public Reminder() {
        timer = new Timer();
    }


    @Override
    public void runActivity(Project project) {
        this.project = project;
        timer.scheduleAtFixedRate(new ReminderTask(), 0, 10*1000); // Schedule reminder every hour
    }


    private class ReminderTask extends TimerTask {
        public void run() {
            Messages.showMessageDialog(project,"Reminder!","Don't forget to take a break and stretch your legs!", Messages.getInformationIcon());
        }
    }
}

*/
