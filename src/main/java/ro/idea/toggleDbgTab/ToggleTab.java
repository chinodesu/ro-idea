package ro.idea.toggleDbgTab;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import ro.idea.RunDbgAct;

/**
 * It must cooperate with xdotool
 */

public class ToggleTab extends RunDbgAct {

    ContentManager cm;

    @Override
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        this.cm = PlatformDataKeys.NONEMPTY_CONTENT_MANAGER.getData(e.getDataContext());
        if (cm != null) {
            if (curTabName().equals("Console")) {
                cm.selectPreviousContent();
            } else {
                cm.selectNextContent();
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
