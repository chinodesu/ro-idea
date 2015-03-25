package ro.idea;

import ro.junit.TestCase;
import org.junit.Before;
import ro.Faker;

public class ToggleFileTest extends TestCase {
    class Kls extends ToggleFile {
        public String _curPath;

        public String curPath() {
            return _curPath;
        }

        public void setCurPath(String val) {
            _curPath = val;
        }

        public void resetCurPath() {
            _curPath = null;
        }
    }

    Kls o;

    public void setUp() throws Exception {
        this.o = new Kls();
    }

    @Before
    public void todo() {
        Faker.reset();
    }

    public void test_TogglePath() throws Exception {
        o.setCurPath("app/src/androidTest/java/ATest.java");
        Object r = o._togglePath("main/java", "androidTest/java");
        assertEq("app/src/main/java/A.java", r);
    }

    public void test_TogglePath2() throws Exception {
        o.setCurPath("src/test/java/ATest.java");
        Object r = o._togglePath("/main/java/", "/test/java/");
        assertEq("src/main/java/A.java", r);
    }

    public void testIsAd() throws Exception {
        String test = Faker.f("/app/src/androidTest/java/ATest.java");
        String lib = Faker.f("/app/src/main/java/A.java");
        o.setCurPath(lib);
        Object r = o.isAd();
        assertEq(true, r);
    }
}
