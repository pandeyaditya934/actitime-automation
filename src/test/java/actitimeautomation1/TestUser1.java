package actitimeautomation1;

import actitimeautomation.imp1.common.BaseClass;
import actitimeautomation.imp1.common.CommonUtil;
import actitimeautomation.imp1.common.LoginSetup;
import actitimeautomation.imp1.common.PropertyHandling;
import actitimeautomation.imp1.pages.UserPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestUser1 extends BaseClass
{   public WebDriver driver;
    CommonUtil commonUtil;
    Actions actions;
    PropertyHandling propertyHandling;
    UserPage userPage;

    @BeforeClass
    public void LoginMethod() throws InterruptedException, IOException {
        PropertyHandling propertyHandling=new PropertyHandling();
        this.propertyHandling=propertyHandling;
        launchBrowser(propertyHandling.getProperty("browser"));
        driver=super.driver;
        driver.navigate().to(propertyHandling.getProperty("actitimeURL"));
        LoginSetup loginSetup=new LoginSetup(driver);
        loginSetup.loginMethod(propertyHandling.getProperty("username"),propertyHandling.getProperty("password"));
        CommonUtil commonUtil=new CommonUtil(driver);
        this.commonUtil=commonUtil;
        UserPage userPage=new UserPage(driver);
        this.userPage=userPage;
    }
    @DataProvider
    public Object[][] userData()
    {    Object[][] obj=new Object[][]
            {   {"kumar22@yopmail.com-73","morajkar-73","ketan-73"}
            };
        return obj;
    }
    @Test(priority = 1,dataProvider = "userData")
    public void testCustomer(Object emailid,Object lastname, Object firstname) throws InterruptedException {
        userPage.createUser(emailid, lastname, firstname);
    }
    @Test(priority = 2,dataProvider = "userData")
    public void checkCustomer(Object emailid,Object lastname, Object firstname)
    {   userPage.verifyUser( emailid, lastname, firstname);
    }
    @DataProvider
    public Object[][] departmentData()
    {    Object[][] obj=new Object[][]
            {   {"networkingDepartment5"},{"financeDept"},{"salesExecutive"}
            };
        return obj;
    }
    @Test(priority = 3,dataProvider = "departmentData",dependsOnMethods = {"checkCustomer"})
    public void checkCustAssignDept(Object deptName ) throws InterruptedException {
        //commonUtil.fluentWait(By.xpath("//table[@class='userNameContainer']"));
        commonUtil.waitForElementClickable(By.xpath("//table[@class='userNameContainer']"));
        driver.findElement(By.xpath("//table[@class='userNameContainer']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@class='simpleListMenuButton components_userGroupSelectorMenu emptyList notEmpty']")).click();
        commonUtil.fluentWait(By.xpath("//div[text()='"+deptName+"']"));
        driver.findElement(By.xpath("//div[text()='"+deptName+"']")).click();
        commonUtil.waitForElementToPresent(By.xpath("//table[@class='userNameContainer']"));
        driver.findElement(By.xpath("//table[@class='userNameContainer']")).click();
    }

    @DataProvider
    public Object[][] deptData()
    {   Object[][] obj=new Object[][]{
            {"networkingDepartment7"}
    };
        return obj;
    }

    @Test(priority = 4,dataProvider = "deptData")
    public void createDepartment(String deptName) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='  Departments']")).click();
        driver.findElement(By.xpath("//input[@id='groupManagementLightBox_newGroupInput']")).click();
        commonUtil.fluentWait(By.xpath("//input[@id='groupManagementLightBox_newGroupInput']"));
        driver.findElement(By.xpath("//input[@id='groupManagementLightBox_newGroupInput']")).sendKeys(deptName);
        driver.findElement(By.xpath("//button[@id='groupManagementLightBox_addGroupButton']")).click();
    }

    @Test(priority = 5,dataProvider = "deptData")
    public void checkDepartment(String deptName) throws InterruptedException {
       commonUtil.fluentWait(By.xpath("//input[@id='groupManagementLightBox_newGroupInput']"));
        driver.findElement(By.xpath("//input[@id='groupManagementLightBox_newGroupInput']")).click();
        Actions actions = new Actions(driver);
        this.actions=actions;
        //delete department
        Thread.sleep(3000);
        actions.scrollToElement(driver.findElement(By.xpath("(//td[@class='groupNameCell'])//div[@title='"+deptName+"']")))
                .click(driver.findElement(By.xpath(" ((//td[@class='groupNameCell'])//div[@title='"+deptName+"']/following::td[@class='deleteGroupCell'])[1]")))
                .build().perform();
        actions.keyDown(Keys.ENTER).build().perform();
        actions.keyUp(Keys.ENTER).build().perform();
        commonUtil.waitForElementClickable(By.xpath("//div[@id='groupManagementLightBox_closeLightbox']"));
        driver.findElement(By.xpath("//div[@id='groupManagementLightBox_closeLightbox']")).click();
       // userPage.verifyAssignDepartment(deptName);
    }

    @Test(priority = 6)
    public void deleteCustomer() throws InterruptedException {
        Thread.sleep(4000);
        commonUtil.waitForElementToPresent(By.xpath("//table[@class='userNameContainer']"));
        driver.findElement(By.xpath("//table[@class='userNameContainer']")).click();
        commonUtil.waitForElementClickable(By.xpath("(//div[@class='actionButtonWrapper'])[1]"));
        driver.findElement(By.xpath("(//div[@class='actionButtonWrapper'])[1]")).click();
        Thread.sleep(3000);
        Alert alert= driver.switchTo().alert();
        alert.accept();
        actions.keyDown(Keys.ENTER).build().perform();
        actions.keyUp(Keys.ENTER).build().perform();;
    }

    @Test(priority = 7,dataProvider = "userData", dependsOnMethods = "testCustomer")
    public void checkDeleteCustomer(Object emailid,Object lastname, Object firstname) throws InterruptedException {
        commonUtil.waitForElementClickable(By.xpath("(//div[@class='close'])[1]"));
        driver.findElement(By.xpath("(//div[@class='close'])[1]")).click();
        commonUtil.waitForElementClickable(By.xpath("(//div[@class='icon'])[1]"));
        driver.findElement(By.xpath("(//div[@class='icon'])[1]")).click();
        commonUtil.waitForElementClickable(By.xpath("(//input[@placeholder='Start typing name...'])[1]"));
        driver.findElement(By.xpath("(//input[@placeholder='Start typing name...'])[1]")).sendKeys(firstname.toString());
        commonUtil.waitForElementToPresent(By.xpath("//span[text()='There are no users found']"));
        driver.findElement(By.xpath("//span[text()='There are no users found']")).click();
        boolean errorMessage=driver.findElement(By.xpath("//span[text()='There are no users found']")).isDisplayed();
        if(errorMessage)
        {   System.out.println("delete sucessfully");
        }
        else{
            System.out.println("delete unsucessful");
        }
    }

}