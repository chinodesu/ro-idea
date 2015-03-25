package ro.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by roroco on 11/12/14.
 */
public class RstRunConsole extends RunDbg {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        runLastDescriptorInToolWin("Run");
    }
}
