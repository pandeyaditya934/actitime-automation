package actitimeautomation1;

import actitimeautomation.imp1.common.*;
import actitimeautomation.imp1.pages.CustomerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(TestListeners.class)
public class TestCustomer1 extends BaseClass {
    public static WebDriver driver;
    CommonUtil commonUtil1;
    PropertyHandling propertyhandling;
    CustomerPage customerPage;

    @BeforeClass
    public void LoginMethod() throws InterruptedException, IOException {
        PropertyHandling propertyHandling=new PropertyHandling();
        launchBrowser(propertyhandling.getProperty("browser"));
        driver = super.driver;
        driver.navigate().to(propertyhandling.getProperty("actitimeURL"));
        LoginSetup loginSetup=new LoginSetup(driver);
        loginSetup.loginMethod(propertyhandling.getProperty("username"),propertyhandling.getProperty("password"));
        CommonUtil commonUtil1=new CommonUtil(driver);
        this.commonUtil1=commonUtil1;
        customerPage=new CustomerPage(driver);
        //Thread.sleep(5000);
    }
    @DataProvider
    public Object[][] getTestdata()
    {   Object[][] obj=new Object[][]
            {   {"Cybercheck288"}
            };
        return obj;
    }
    @Test(priority = 1,dataProvider="getTestdata")
    public void custTesting(Object custName) throws Exception {
        customerPage.createCustomer(custName);
    }
    @Test(priority = 2,dataProvider = "getTestdata")
    public void checkCustomer(Object custName) throws Exception {
        customerPage.verifyCustomer(custName);
    }
    @Test(priority = 3,dataProvider = "getTestdata",enabled = true)
    public void deleteCustomer(Object custName) throws InterruptedException {
        customerPage.deleteCustomer(custName);
    }
}
