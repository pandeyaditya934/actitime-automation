package actitimeautomation.imp1.common;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

public class BaseClass {

    public WebDriver driver;
    public ExtentTest extentTest;
    public void launchBrowser(String browserName){
        if(browserName.equals("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver= new FirefoxDriver();
        }
        driver.manage().window().maximize();

    }
    @BeforeMethod
    public void beforeMethod(){
        extentTest = ExtentReportUtil.objectMap.get("extentTest");
    }

}
