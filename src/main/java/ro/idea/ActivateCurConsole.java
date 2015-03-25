package ro.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by roroco on 12/18/14.
 */
public class ActivateCurConsole extends RunDbg {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        if (dbg().isVisible()) {
            dbg().activate(null);
        }

        if (run().isVisible()) {
            run().activate(null);
        }
    }
}
