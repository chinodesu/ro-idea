package ro.idea;

import com.intellij.execution.ExecutionManager;
import com.intellij.execution.impl.ExecutionManagerImpl;
import com.intellij.execution.process.BaseOSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionUtil;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.RunContentManager;
import com.intellij.execution.ui.RunContentManagerImpl;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.externalSystem.service.execution.ExternalSystemRunConfiguration;
import com.intellij.openapi.util.Trinity;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.impl.ToolWindowManagerImpl;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.docking.DockManager;
import ro.AL;
import ro.Sh;
import ro.Obj;
import ro.Str;

import java.lang.reflect.Field;
import java.util.List;

import static ro.helper.Kernel.*;

/**
 * Created by roroco on 11/12/14.
 */
public class RunDbg extends Act {
    public ToolWindow dbg() {
        return twm().getToolWindow("Debug");
    }

    public ToolWindowManagerImpl twm() {
        return (ToolWindowManagerImpl) ToolWindowManager.getInstance(pj());
    }

    public void runLastDescriptorInToolWin(String toolWinName) {
        RunContentDescriptor d = lastDescriptor(toolWinName);

        if (d == null) {
            return;
        }

        d.getProcessHandler().destroyProcess();
        ExecutionUtil.restart(d);
    }

    public RunContentDescriptor lastDescriptor(String toolWinName) {
        try {
            ExecutionManagerImpl em = (ExecutionManagerImpl) ExecutionManager.getInstance(pj());
            Field f = em.getClass().getDeclaredField("f");
            f.setAccessible(true);
            List<RunContentDescriptor> ds = (List<RunContentDescriptor>) f.get(em);
            AL<RunContentDescriptor> finDs = new AL<RunContentDescriptor>();
            List<Trinity> trinities = (List<Trinity>) f.get(em);
            ToolWindow tw = twm().getToolWindow(toolWinName);
            if (tw == null) {
                return null;
            }
            Content[] cs = tw.getContentManager().getContents();
            AL<Long> ids = new AL<Long>();

            for (Content c : cs) {
                ids.add(c.getExecutionId());
            }

            for (Trinity t : trinities) {
                RunContentDescriptor d = (RunContentDescriptor) t.getFirst();

                if (ids.contains(d.getExecutionId())) {
                    finDs.add(d);
                }
            }

            RunContentDescriptor d = finDs.last();

            if (d == null) {
                return null;
            }

            return d;
        } catch (NoSuchFieldException e1) {
            e1.printStackTrace();
            System.exit(-1);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public ToolWindow run() {
        return twm().getToolWindow("Run");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
    }

    public boolean isDebug(RunContentDescriptor d) {
        ProcessHandler ph = null;
        try {
            ph = d.getProcessHandler();
            Str cli = str(((BaseOSProcessHandler) ph).getCommandLine());
            return cli.match("jdwp") || cli.match("rdebug-ide") || cli.match("rspec");
        } catch (ClassCastException exc) {
            if (str(d.getProcessHandler().getClass().getName()).match("ExternalSystemRunConfiguration")) {
                if (str(d.getDisplayName()).match("Test$")) {
                    return true;
                }
            }
            exc.printStackTrace();
            return false;
        }
    }

    public boolean isRun(RunContentDescriptor d) {
//        Bash.notify(!isDebug(d));
        return !isDebug(d);
    }

    public List<RunContentDescriptor> ds() {
        RunContentManager cm = ExecutionManager.getInstance(pj()).getContentManager();
        return cm.getAllDescriptors();
    }
}
