import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverLogLevel
import org.openqa.selenium.chrome.ChromeOptions

import java.util.concurrent.TimeUnit

WebDriverManager.chromedriver().setup()

driver = {
    ChromeOptions options = new ChromeOptions()
    options.setExperimentalOption("excludeSwitches", ["enable-automation"])
    options.setExperimentalOption("useAutomationExtension", false)
    options.addArguments("--disable-blink-features=AutomationControlled")
    options.logLevel = ChromeDriverLogLevel.OFF
    ChromeDriver driver = new ChromeDriver(options)
    driver.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})")
    // Point point = new Point(3008, -690)
    // driver.manage().window().setPosition(point)
    driver.manage().window().maximize()
    driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS)
    return driver
}
