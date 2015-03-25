package ro.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by roroco on 11/12/14.
 */
public class ExAct  extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
System.out.println("do smth" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
    }
}
