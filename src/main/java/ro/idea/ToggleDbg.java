package ro.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by roroco on 12/7/14.
 */
public class ToggleDbg extends RunDbg {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        if (dbg().isVisible()) {
            dbg().hide(null);

        } else {
            dbg().show(null);
        }

        // TODO: insert action logic here
    }
}
