package ro.idea.toggleDbgTab;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ro.idea.RunDbg;

/**
 * Created by roroco on 12/10/14.
 */
public class ActivateDbg  extends RunDbg {
    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        dbg().activate(null);
    }
}
