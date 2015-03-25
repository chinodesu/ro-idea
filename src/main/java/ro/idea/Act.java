package ro.idea;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import ro.File;

import static ro.helper.LightKernel.str;

/**
 * Created by roroco on 11/11/14.
 */
public class Act extends AnAction {
    AnActionEvent e;

    @Override
    public void actionPerformed(AnActionEvent e) {
        this.e = e;
    }

    public String cur(Object ... relPaths) {
        return File.join(pj_path(), relPaths);
    }

    public String pj_path() {
        return pj().getBasePath();
    }

    public Project pj() {
        return e.getProject();
    }

    public int curLineno() {
        int idx = ed().getCaretModel().getLogicalPosition().line;
        return idx + 1;
    }

    public void open(String path) {
        VirtualFile f = LocalFileSystem.getInstance().findFileByIoFile(new java.io.File(path));
        em().openFile(f, true);
    }

    public void open(String path, int lineno) {
        VirtualFile f = LocalFileSystem.getInstance().findFileByIoFile(new java.io.File(path));
        em().openFile(f, true);
        go(lineno);
    }

    public void go(int lineno) {
        ed().getCaretModel().moveToOffset(lineno);
    }

    public Document d() {
        return ed().getDocument();
    }

    public Editor ed() {
        return CommonDataKeys.EDITOR.getData(e.getDataContext());
    }

    public String curPath() {
        // ed() may return null, so use fileEditor is better
        FileEditor fed = FileEditorManager.getInstance(pj()).getSelectedEditors()[0];
        FileEditor ed = em().getSelectedEditors()[0];
        return str(ed.toString()).gsub("^Editor\\: file\\:\\/\\/", "").toString();
    }

    public FileEditorManager em() {
        Project pj = pj();
        return FileEditorManager.getInstance(pj);
    }

}



