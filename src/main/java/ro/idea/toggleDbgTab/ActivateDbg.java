package ro.idea.toggleDbgTab;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ro.idea.RunDbgAct;

/**
 * Created by roroco on 12/10/14.
 */
public class ActivateDbg  extends RunDbgAct{
    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        dbg().show(null);
        dbg().activate(null);
    }
}
