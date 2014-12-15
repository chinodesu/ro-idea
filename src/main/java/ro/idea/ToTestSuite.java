package ro.idea;

import com.intellij.openapi.actionSystem.AnActionEvent;
import ro.AL;
import ro.File;
import ro.Range;
import ro.Str;
import ro.file.Line;

import static ro.helper.Kernel.*;

/**
 * Created by roroco on 11/13/14.
 * depreacted, idea will tip repeat test meth, it's not necessary
 */

public class ToTestSuite extends Act {
    ToggleFile t;

    public void actionPerformed(AnActionEvent e) {
        t = new ToggleFile();
        String lib = curPath().toString();
        int lineno = curLineno();
        String test = t.toggle();
        if (t.isLib()) {
            if (all(testMethDeclaration(lib, lineno, test))) {
                open(test, testMethDeclaration(lib, lineno, test).no());
            }
        }
    }

    public Line testMethDeclaration(String lib, int lineno, String test) {
        Str methName = closedMethDeclaration(lib, lineno).methName();
        return File.readlines(test).grep("test", methName.camelize()).first();
    }

    public Line closedMethDeclaration(String lib, int lineno) {
        AL<Line> ls = File.readlines(lib);
        for (int n : new Range(lineno - 1, 0)) {
            Str l = ls.get(n).line();
            if (l.match("^\\s+public|protected|private")) {
                return new Line(l.toString(), n + 1);
            }
        }
        return null;
    }
}
