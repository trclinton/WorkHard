import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.URL;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    protected WebDriver createChromeDriver() throws Exception {
        ChromeOptions options = new ChromeOptions();
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    protected WebDriver createFirefoxDriver() throws Exception {
        FirefoxOptions options = new FirefoxOptions();
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver = createChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
