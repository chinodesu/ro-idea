package ro.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by roroco on 12/7/14.
 */
public class ToggleRun extends RunDbgAct {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        if (run().isVisible()) {
            run().hide(null);
        } else {
            run().show(null);
        }
        // TODO: insert action logic here
    }

}
