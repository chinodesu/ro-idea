package ro.idea.toggleDbgTab;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import ro.idea.RunDbg;

/**
 * It must cooperate with xdo
 */

public class ToggleTab extends RunDbg {

    ContentManager cm;

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        this.cm = PlatformDataKeys.NONEMPTY_CONTENT_MANAGER.getData(e.getDataContext());
        if (cm != null) {
            if (curTabName().equals("Console")) {
                selectCtn("Watches");
            } else {
                selectCtn("Console");
            }
        }
    }

    public String curTabName() {
        return cm.getSelectedContent().getTabName();
    }

    public void selectCtn(String name) {
        for (Content c : cm.getContents()) {
            if (c.getTabName().equals(name)) {
                cm.setSelectedContent(c);
            }
        }
    }
}
