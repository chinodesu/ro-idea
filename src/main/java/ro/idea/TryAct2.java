package ro.idea;

import com.intellij.execution.*;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.impl.RunManagerImpl;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;

import java.util.Collection;
import java.util.List;

/**
 * Created by roroco on 11/21/14.
 */
public class TryAct2 extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }

        ToolWindow window = ToolWindowManager.getInstance(project).getToolWindow("Debug");
        if (window == null) {
            return;
        }

        final Content content = window.getContentManager().getContents()[0];

        final RunManagerImpl runManager = (RunManagerImpl) RunManager.getInstance(project);
        final Collection<RunnerAndConfigurationSettings> allConfigurations = runManager.getSortedConfigurations();

        for (RunnerAndConfigurationSettings runConfiguration : allConfigurations) {
            System.out.println(runConfiguration.getName());

            ExecutionManager executionManager = ExecutionManager.getInstance(project);

            RunContentDescriptor descriptor = content.getUserData(new Key<RunContentDescriptor>("Descriptor"));
            RunnerAndConfigurationSettings configurationByName = runManager.findConfigurationByName(runConfiguration.getName());
            if (configurationByName == null) {
                continue;
            }

            List<ExecutionTarget> targets = ExecutionTargetManager.getTargetsFor(project, configurationByName);

            for (ExecutionTarget target : targets) {
                executionManager.restartRunProfile(project,
                        DefaultRunExecutor.getRunExecutorInstance(),
                        target,
                        configurationByName,
                        (RunContentDescriptor) null);
            }
        }
    }
}
