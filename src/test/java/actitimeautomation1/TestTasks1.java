package actitimeautomation1;

import actitimeautomation.imp1.common.BaseClass;
import actitimeautomation.imp1.common.LoginSetup;
import actitimeautomation.imp1.common.PropertyHandling;
import actitimeautomation.imp1.pages.TaskPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestTasks1 extends BaseClass
{   WebDriver driver;
    TaskPage taskPage;
    @BeforeClass
    public void LoginMethod() throws InterruptedException, IOException {
        PropertyHandling propertyhandling=new PropertyHandling();
        launchBrowser(propertyhandling.getProperty("browser"));
        driver = super.driver;
        driver.navigate().to(propertyhandling.getProperty("actitimeURL"));
        LoginSetup loginSetup=new LoginSetup(driver);
        loginSetup.loginMethod(propertyhandling.getProperty("username"), propertyhandling.getProperty("password") );
        TaskPage taskPage=new TaskPage(driver);
        this.taskPage=taskPage;
        Thread.sleep(5000);
    }
    @DataProvider
    public Object[][] getCustProjectData()
    {   Object[][] obj=new Object[][]
            {   {"CyberCheckConnection11","ConnectionTask11","project regarding the netowork connecntion of the particular area of the task11","connectionSpeed11"}
            };
        return obj;
    }
    @Test(dataProvider = "getCustProjectData")
    public void testTask(Object customername, Object projectName, Object discriptionArea, Object taskName) throws InterruptedException {
        taskPage.createTask(customername,projectName,discriptionArea,taskName);
      /*  // driver.findElement(By.xpath("//div[text()='Add New']")).click();
        Thread.sleep(5000);
        String taskname="Connenctivity-3";
        driver.findElement(By.xpath("//input[@placeholder='Enter task name'][1]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Enter task name'][1]")).sendKeys(taskname);
        driver.findElement(By.xpath("//div[text()='Create Project']")).click();
       */
    }

}
