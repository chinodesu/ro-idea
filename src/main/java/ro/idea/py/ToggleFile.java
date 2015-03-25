package ro.idea.py;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ro.File;
import ro.Matcher;
import ro.Str;
import ro.idea.Act;

import static ro.helper.Kernel.*;

/**
 * Created by roroco on 3/14/15.
 */
public class ToggleFile extends Act {
    public void actionPerformed(AnActionEvent e) {
        super.actionPerformed(e);
        open(toggle());
    }

    public String toggle() {
        return toggle(curPath());
    }

    public String test(String... relPaths) {
        return cur("test", relPaths);
    }

    public String toggle(String p) {
        Str td = str(p);
        Matcher m;
        m = td.matcher("(.*)/test/test_(.*)\\.py$");
        if (m.toString() != null) {
            return File.join(m.get(1), m.get(2) + ".py");
        }

        m = td.matcher(File.join(cur(), "/(.*)\\.py$"));
        if (m.toString() != null) {
            Str par = str(File.parent(p));
            return File.join(par.gsub(cur(), test()), "test_" + m.get(1) + ".py");
        }

        return null;
    }
}
