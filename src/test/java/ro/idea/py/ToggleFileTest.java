package ro.idea.py;

import ro.File;
import ro.junit.TestCase;

public class ToggleFileTest extends TestCase {
    class Kls extends ToggleFile {
        public String cur(Object... relPaths) {
            return File.join("/home/roroco/Dropbox/pys/ro", relPaths);
        }
    }

    class Kls1 extends Kls {
        public String curPath() {
            return "/home/roroco/Dropbox/pys/ro/test/test_out.py";
        }
    }

    public void testToggle() throws Exception {
        Object r = new Kls1().toggle();
        assertEq("/home/roroco/Dropbox/pys/ro/out.py", r);
    }

    class Kls2 extends Kls {
        public String curPath() {
            return "/home/roroco/Dropbox/pys/ro/out.py";
        }
    }

    public void testToggle2() throws Exception {
        Object r = new Kls2().toggle();
        assertEq("/home/roroco/Dropbox/pys/ro/test/test_out.py", r);
    }
}


