package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Objects;
import java.util.function.Supplier;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebDriverProvider() {

        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        if (Objects.isNull(config.getRemoteUrl())) {
            switch (config.getBrowser()) {
                case CHROME: {
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();
                }
                case FIREFOX: {
                    WebDriverManager.firefoxdriver().setup();
                    return new FirefoxDriver();
                }
                default: {
                    throw new RuntimeException("No such driver");
                }
            }
        } else {
            switch (config.getBrowser()) {
                case CHROME: {
                    capabilities.setVersion(config.getVersion());
                    return new RemoteWebDriver(config.getRemoteUrl(), new ChromeOptions());
                }
                case FIREFOX: {
                    capabilities.setVersion(config.getVersion());
                    return new RemoteWebDriver(config.getRemoteUrl(), new FirefoxOptions());
                }
                default: {
                    throw new RuntimeException("No such driver");
                }
            }
        }
    }
}
