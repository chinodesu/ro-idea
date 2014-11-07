package ro.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testIntegration.GotoTestOrCodeAction;
import ro.Str;

import static ro.helper.Kernel.*;

/**
 * Created by roroco on 10/18/14.
 */

public class Toggle extends AnAction {
    AnActionEvent e;

    Str curPath = null;

    public void actionPerformed(AnActionEvent e) {
        this.e = e;
        Str cur = getCurPath();
        String path = "";

        if (all(cur.match("\\.java$"))) {
            if (isAd()) {
                path = toggle("/main/java", "androidTest/java/");
            } else {
                path = toggle("/src/", "/test/");
            }
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

    public String toggle(String libDir, String testDir) {
        Str cur = getCurPath();
        String path;

        if (all(cur.match("Test\\.java$"))) {
            path = cur.gsub(testDir, libDir)
                    .gsub("Test\\.java", "\\.java")
                    .toString();

        } else {
            path = cur.gsub(libDir, testDir)
                    .gsub("\\.java$", "Test\\.java")
                    .toString();
        }
        return path;
    }

    public boolean isAd() {
        Str cp = getCurPath();
        if (all(cp.match("/app/"))) {
            Str appDir = cp.gsub("/app/.*$", "/app/");
            if (all(appDir)) {
                return appDir.join("androidTest").exist();
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

    private Str getCurPath() {
        if (curPath == null) {
            FileEditor ed = em().getSelectedEditors()[0];
            curPath = str(ed.toString()).gsub("^Editor\\: file\\:\\/\\/", "");
        }
        return curPath;
    }

    public void setCurPath(String path) {
        curPath = new Str(path);
    }

    public void delCurPath() {
        curPath = null;
    }

    private FileEditorManager em() {
        Project pj = e.getProject();
        return FileEditorManager.getInstance(pj);
    }


    public void open(String path) {
        VirtualFile f = LocalFileSystem.getInstance().findFileByIoFile(new java.io.File(path));
        em().openFile(f, true);
    }

    public static void main(String[] args) {
    }
}

