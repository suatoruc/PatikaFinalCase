package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;


public class Driver {
    private Driver() {
    }

    private static WebDriver driver;

     private static int timeout = 5;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options=new ChromeOptions();
                   options.addArguments("--disable-blink-features");
                   options.addArguments("--disable-blink-features=AutomationControlled");
                   options.addArguments("--disable-extensions");
                   options.addArguments("−−lang=tur");
                    driver = new ChromeDriver(options=options);
                    driver.manage().deleteAllCookies();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();

            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }


    public static void closeDriver(){
         if(driver != null){
          driver.quit();
        }
        driver = null;

    }


}

