import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxDriverLogLevel
import org.openqa.selenium.firefox.FirefoxOptions

import java.util.concurrent.TimeUnit

driver = {
    FirefoxOptions options = new FirefoxOptions()
    options.logLevel = FirefoxDriverLogLevel.ERROR
    WebDriver driver = new FirefoxDriver(options)
    // Point point = new Point(3008, -690)
    // driver.manage().window().setPosition(point)
    driver.manage().window().maximize()
    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS)
    return driver
}

// driver = {
//     ChromeOptions options = new ChromeOptions()
//     options.logLevel = ChromeDriverLogLevel.OFF
//     ChromeDriver driver = new ChromeDriver(options)
//     Point point = new Point(3008, -690)
//     driver.manage().window().setPosition(point)
//     driver.manage().window().maximize()
//     driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS)
//     return driver
// }
