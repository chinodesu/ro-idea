package ro.idea;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ro.Faker;

public class ToggleTest extends TestCase {
    Toggle o;

    @Override
    public void setUp() throws Exception {
        this.o = new Toggle();
    }

    @Before
    public void todo() {
        o.setCurPath(null);
    }

    public void testToggle() throws Exception {
        o.setCurPath("androidTest/java/ATest.java");
        Object r = o.toggle("main/java", "androidTest/java");
        assertEquals("main/java/A.java", r);
    }

    @Test
    public void testIsAd() throws Exception {
        String test = Faker.f("src/androidTest/java/ATest.java");
        Faker.f("/src/main/java/A.java");
        o.setCurPath(test);
        Object r = o.isAd();
        assertEquals(r, true);
    }
}
