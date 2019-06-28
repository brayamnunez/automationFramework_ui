package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverFactory extends BaseUtils {
    public static WebDriver driver;

    public WebDriver getDriver() {
        try {
            switch (FileReaderManager.getInstance().getConfigReader().getBrowser()) {

                case "firefox":
                    // code
                    if (null == driver) {
                        System.setProperty("webdriver.gecko.driver", Constant.GECKO_DRIVER_DIRECTORY);
                        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                        capabilities.setCapability("marionette", true);
                        capabilities.setCapability("locationContextEnabled", false);
                        driver = new FirefoxDriver();
                    }
                    break;

                case "chrome":
                    // code
                    if (null == driver) {
                        // CHROME OPTIONS
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--disable-geolocation");
                        options.addArguments("--enable-strict-powerful-feature-restrictions");
                        System.setProperty("webdriver.chrome.driver", Constant.CHROME_DRIVER_DIRECTORY);
                        driver = new ChromeDriver(options);
                        driver.manage().window().maximize();
                    }
                    break;

                case "ie":
                    // code
                    if (null == driver) {
                        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                        System.setProperty("webdriver.ie.driver", Constant.IE_DRIVER_DIRECTORY);
                        capabilities.setCapability("ignoreZoomSetting", true);
                        driver = new InternetExplorerDriver();
                        driver.manage().window().maximize();
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Unable to load browser: " + e.getMessage());
        } finally {
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        }
        return driver;
    }

}
