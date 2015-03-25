package ro.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by roroco on 11/12/14.
 */
public class RstDbgConsole extends RunDbg {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        // for idea 14
        runLastDescriptorInToolWin("Debug");
//        for idea 13
//        ToolWindow tw = ToolWindowManager.getInstance(pj()).getToolWindow("Debug");
//        AL<Content> cs = new AL<Content>(tw.getContentManager().getContents());
//        Content c = cs.last();
//        if (all(c)) {
//            RunContentDescriptor d = new RunContentManagerImpl(pj(), DockManager.getInstance(pj())).getRunContentDescriptorByContent(c);
//            AL<RunnerAndConfigurationSettings> ss = new AL<RunnerAndConfigurationSettings>(RunManager.getInstance(pj()).getAllSettings()).select(new AL.Proc2() {
//                @Override
//                public <T2> Boolean call(T2 o) {
//                    return ((RunnerAndConfigurationSettings) o).getName() == d.getDisplayName();
//                }
//            });
//
//            RunnerAndConfigurationSettings s = ss.last();
//            AL<ExecutionTarget> ts = new AL<ExecutionTarget>(ExecutionTargetManager.getTargetsFor(pj(), s));
//            ExecutionTarget t = ts.last();
//            d.getProcessHandler().destroyProcess();
//            ExecutionManager.getInstance(pj()).restartRunProfile(pj(),
//                    DefaultDebugExecutor.getDebugExecutorInstance(),
//                    t,
//                    s,
//                    d.getProcessHandler()
//            );
//        } else {
//            Bash.notify("didn't find run descriptor" + "\t\t" + new Exception().getStackTrace()[0].getFileName() + ":" + new Exception().getStackTrace()[0].getLineNumber());
//        }
    }

}
