import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract public class BaseTest {

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.browserSize = "1200x900";
        Configuration.headless = false;
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
