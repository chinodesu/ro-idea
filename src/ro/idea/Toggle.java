package ro.idea;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
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

    public void actionPerformed(AnActionEvent e) {
        this.e = e;
        Str cur = CurPath();
        String path;

        if (allTrue(cur.match("Test\\.java"))) {
            path = cur.gsub("/test/", "/src/")
                    .gsub("Test\\.java", "\\.java")
                    .toString();
        } else {
            path = cur.gsub("/src/", "/test/")
                    .gsub("/main/", "/test/")
                    .gsub("\\.java$", "Test\\.java")
                    .toString();
        }

        if (file(path).exist()) {
            open(path);
        } else {
            new GotoTestOrCodeAction().actionPerformed(e);
        }
    }

    private Str CurPath() {
        FileEditor ed = em().getSelectedEditors()[0];
        return str(ed.toString()).gsub("^Editor\\: file\\:\\/\\/", "");

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

