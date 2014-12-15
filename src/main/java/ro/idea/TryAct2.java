package ro.idea;

import com.intellij.execution.*;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.impl.RunManagerImpl;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.ide.actions.NextTabAction;
import com.intellij.ide.actions.PreviousTabAction;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.impl.ToolWindowHeadlessManagerImpl;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import ro.Time;

import java.util.Collection;
import java.util.List;

/**
 * Created by roroco on 11/21/14.
 */
public class TryAct2 extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        ToolWindow dbg = ToolWindowManager.getInstance(e.getProject()).getToolWindow("Debug");
        dbg.activate(null, true, true);

        int n = 0;

        while (true) {

            if (dbg.isActive()) {
                break;
            }
            try {
                Thread.sleep((long) 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if (n > 5) {
                throw new RuntimeException("Timeout");
            }
            n++;
        }

        ContentManager cm = (ContentManager) PlatformDataKeys.NONEMPTY_CONTENT_MANAGER.getData(e.getDataContext());
        Content sc = cm.getSelectedContent();
        if (sc.getTabName().equals("Console")) {
            new NextTabAction().actionPerformed(e);
        } else {
            new PreviousTabAction().actionPerformed(e);
        }
    }
}
