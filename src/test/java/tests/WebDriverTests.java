package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import config.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class WebDriverTests {

    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        driver = new WebDriverProvider().get();
    }

    @Test
    public void testGitHub() {
        String title = driver.getTitle();
        assertEquals("GitHub: Where the world builds software · GitHub", title);
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
