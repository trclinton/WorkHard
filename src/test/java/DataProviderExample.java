import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderExample {

    // Define the DataProvider
    @DataProvider(name = "testData", parallel = true)
    public Object[][] provideTestData(Method method) {
        return new Object[][] {
                { "chrome", method.getName() },
                { "firefox", method.getName() },
                { "edge", method.getName() }
        };
    }

    // Use the DataProvider in a Test
    @Test(dataProvider = "testData")
    public void testExecution(String browser, String testName) {
        System.out.println("Executing test: " + testName + " on browser: " + browser);

        String dir = System.getProperty("user.dir");

        if (browser.equals("chrome")) {
            System.out.println("Launching Chrome browser...");
            String driverPath = dir + "/src/test/resources/drivers/chromedriver";
            System.setProperty("webdriver.chrome.driver", driverPath);
            WebDriver driver = new ChromeDriver();
            driver.get("https://www.google.com/");
            System.out.println(driver.getTitle());
            driver.quit();
        } else if (browser.equals("firefox")) {
            System.out.println("Launching Firefox browser...");
            String driverPath = dir + "/src/test/resources/drivers/geckodriver";
            System.setProperty("webdriver.gecko.driver", driverPath);
            WebDriver driver = new FirefoxDriver();
            driver.get("https://www.google.com/");
            System.out.println(driver.getTitle());
            driver.quit();
        } else if (browser.equals("edge")) {
            System.out.println("Launching Edge browser...");
            // Code to launch Edge WebDriver
        }

        // Placeholder for actual test logic
        System.out.println("Test execution complete for: " + testName + " on " + browser);
    }
}