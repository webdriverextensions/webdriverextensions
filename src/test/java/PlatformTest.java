
import org.junit.Test;

import static org.openqa.selenium.Platform.*;

public class PlatformTest {

    @Test
    public void macIsAnyTest() {
        assert MAC.is(ANY);
    }

    @Test
    public void anyIsNotMacTest() {
        assert !ANY.is(MAC);
    }

    @Test
    public void xpIsAnyTest() {
        assert XP.is(ANY);
    }

    @Test
    public void anyIsNotXpTest() {
        assert !ANY.is(XP);
    }

    @Test
    public void xpIsWindowsTest() {
        assert XP.is(WINDOWS);
    }

    @Test
    public void windowsIsNotXpTest() {
        assert !WINDOWS.is(XP);
    }

}
