import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BrowserTest extends TestBase {

    @Test
    public void testInChrome() throws Exception {
        driver = createChromeDriver();
        driver.get("https://www.example.com");
        assertEquals("Example Domain", driver.getTitle());
    }

    @Test
    public void testInFirefox() throws Exception {
        driver = createFirefoxDriver();
        driver.get("https://www.example.com");
        assertEquals("Example Domain", driver.getTitle());
    }
}
