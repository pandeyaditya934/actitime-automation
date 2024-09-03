package actitimeautomation1;

import actitimeautomation.imp1.common.BaseClass;
import actitimeautomation.imp1.common.PropertyHandling;
import actitimeautomation.imp1.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProjectTest extends BaseClass {
    WebDriver driver;
    PropertyHandling propertyHandling;
    @BeforeClass
    public void setup(ITestContext context) throws IOException {
        propertyHandling = new PropertyHandling();
        String browser = propertyHandling.getProperty("browser");
        launchBrowser(browser);
        driver = super.driver;
        driver.get(propertyHandling.getProperty("actitimeURL"));
    }
    @Test
    public void verifyLogin(){
        LoginPage loginPage = new LoginPage();
    }

    @Test
    public void verifyCreateNewProject(){
        
    }








}
