package ro.idea;

import com.intellij.execution.KillableProcess;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import static ro.helper.Kernel.*;

/**
 * Created by roroco on 2/23/15.
 */
public class KillCurDescriptor extends RunDbg {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        RunContentDescriptor d = null;
        if (dbg().isVisible()) {
            d = lastDescriptor("Debug");
        }

        if (run().isVisible()) {
            d = lastDescriptor("Run");
        }

        ((KillableProcess)d.getProcessHandler()).killProcess();
    }
}
