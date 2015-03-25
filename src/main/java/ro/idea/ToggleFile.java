package ro.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.testIntegration.GotoTestOrCodeAction;
import ro.Str;

import static ro.helper.Kernel.file;
import static ro.helper.LightKernel.all;
import static ro.helper.Kernel.*;

/**
 * Created by roroco on 11/13/14.
 */
public class ToggleFile extends Act {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        Str cur = str(curPath());
        String path = "";

        if (all(cur.matcher("\\.java$"))) {
            path = togglePath();
        } else {
            doOld();
            return;
        }

        if (file(path).exist()) {
            open(path);
        } else {
            createNewTest();
        }
    }

    public String togglePath() {
        String path;
        if (isAd()) {
            path = _togglePath("/main/java/", "/androidTest/java/");
        } else {
            path = _togglePath("/main/java/", "/test/java/");
        }
        return path;
    }

    public String _togglePath(String libDir, String testDir) {
        Str cur = str(curPath());
        String path;

        if (isTest()) {
            path = cur.gsub(testDir, libDir)
                    .gsub("Test\\.java$", ".java")
                    .toString();
            return path;

        }
        if (isLib()) {
            path = cur.gsub(libDir, testDir)
                    .gsub("\\.java$", "Test\\.java")
                    .toString();
            return path;
        }
        return null;
    }

    public boolean isTest() {
        return str(curPath()).match("Test\\.java$");
    }

    public boolean isLib() {
        return str(curPath()).match("\\.java$") && !isTest();
    }

    public boolean isAd() {
        Str cp = str(curPath());
        if (all(cp.matcher("/app/"))) {
            Str appDir = cp.gsub("/app/.*$", "/app/");
            if (all(appDir)) {
                return appDir.join("src/androidTest").exist();
            }
        }

        return false;
    }


    private void doOld() {
        new GotoTestOrCodeAction().actionPerformed(e);
    }

    public void createNewTest() {
        new GotoTestOrCodeAction().actionPerformed(e);
    }
}
