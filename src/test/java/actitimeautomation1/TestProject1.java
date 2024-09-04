package actitimeautomation1;
import actitimeautomation.imp1.common.BaseClass;
import actitimeautomation.imp1.common.LoginSetup;
import actitimeautomation.imp1.common.PropertyHandling;
import actitimeautomation.imp1.pages.CustomerPage;
import actitimeautomation.imp1.pages.ProjectPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestProject1 extends BaseClass
{   public static WebDriver driver;
    PropertyHandling propertyHandling;
    CustomerPage customerPage;
    ProjectPage projectPage;

    @BeforeClass
    public void LoginMethod() throws InterruptedException, IOException {
        propertyHandling=new PropertyHandling();
        launchBrowser(propertyHandling.getProperty("browser"));
        driver = super.driver;
        driver.navigate().to(propertyHandling.getProperty("actitimeURL"));
        LoginSetup loginSetup=new LoginSetup(driver);
        loginSetup.loginMethod(propertyHandling.getProperty("username"),propertyHandling.getProperty("password"));
        customerPage=new CustomerPage(driver);
        projectPage=new ProjectPage(driver);
        Thread.sleep(5000);
    }
    @DataProvider
    public Object[][] getCustProjectData()
    {   Object[][] obj=new Object[][]
            {   {"CyberCheck92","Connection30","project regarding the netowork connecntion of the particular area15"}
            };
        return obj;
    }
    @Test(priority = 1,dataProvider = "getCustProjectData")
    public void projectCreation(Object customerName, Object proName,Object discriptionArea) throws InterruptedException {
        projectPage.createProject(customerName,proName,discriptionArea);
    }
    @Test(priority = 2,dataProvider = "getCustProjectData")
    public void checkCustomerProject(Object customerName, Object proName,Object discriptionArea) throws InterruptedException {
        projectPage.checkProject(customerName,proName);
    }
    @DataProvider
    public Object[][] getCustProjectToAssignData()
    {   Object[][] obj=new Object[][]
            {   {"Connection_10"}
            };
        return obj;
    }
    @Test(priority = 4,dataProvider = "getCustProjectToAssignData",enabled = false)
    public void assignProject(Object proName) throws InterruptedException
    {   Thread.sleep(5000);
        driver.findElement(By.xpath("//div[text()='Add New']")).click();
        driver.findElement(By.xpath("//div[text()='+ New Project']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@class='projectNameField inputFieldWithPlaceholder inputNameField']")).click();
        driver.findElement(By.xpath("//input[@class='projectNameField inputFieldWithPlaceholder inputNameField']")).sendKeys(proName.toString());
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='assignedUsers selected']/span")));
        driver.findElement(By.xpath("//div[@id='ext-gen521']")).click();
        Thread.sleep(5000);
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.xpath("//span[text()='Alvarez, Daniel']"))).click().build().perform();
        driver.findElement(By.xpath("//div[text()='Create Project']")).click();
    }
}
