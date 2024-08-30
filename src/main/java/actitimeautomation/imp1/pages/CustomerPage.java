package actitimeautomation.imp1.pages;

import actitimeautomation.imp1.common.BaseClass1;
import actitimeautomation.imp1.common.CommonUtil1;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class CustomerPage extends BaseClass1 {
    WebDriver driver;
    CommonUtil1 commonUtil1;
    public CustomerPage(WebDriver driver)
    {   this.driver=driver;
        CommonUtil1 commonUtil11=new CommonUtil1(this.driver);
        this.commonUtil1=commonUtil11;
    }

    public By taskModule=By.xpath("//div[text()='Tasks']");
    public By addNewButton=By.xpath("//div[text()='Add New']");
    public By createNewCustomer=By.xpath("//div[text()='+ New Customer']");
    public By customerNameTextArea=By.xpath("//input[@class='inputFieldWithPlaceholder newNameField inputNameField']");
    public By customerCreateButton=By.xpath("//div[text()='Create Customer']");

    public void createCustomer(Object customerName) throws InterruptedException {
        commonUtil1.waitForElementToPresent(taskModule);
        driver.findElement(taskModule).click();
        commonUtil1.waitForElementToPresent(addNewButton);
        driver.findElement(addNewButton).click();
        commonUtil1.waitForElementClickable(createNewCustomer);
        driver.findElement(createNewCustomer).click();
        commonUtil1.waitForElementClickable(customerNameTextArea);
        driver.findElement(customerNameTextArea).sendKeys(customerName.toString());
        commonUtil1.waitForElementVisible(driver.findElement(customerCreateButton));
        driver.findElement(customerCreateButton).click();
        Thread.sleep(3000);
    }

    public By searchButton=By.xpath("//div[@class='cellWrapper']//input[@placeholder='Start typing name ...']");

    public void verifyCustomer(Object customerName) throws Exception {
        commonUtil1.waitForElementToPresent(taskModule);
        driver.findElement(taskModule).click();
        commonUtil1.waitForElementVisible(driver.findElement(searchButton));
        driver.findElement(searchButton).click();
        driver.findElement(searchButton).sendKeys(customerName.toString());
        commonUtil1.waitForElementClickable(By.xpath("//span[text()='"+customerName+"']"));
        boolean displayCust = driver.findElement(By.xpath("//span[text()='"+customerName+"']")).isDisplayed();
        if (displayCust) {
            // Thread.sleep(4000);
            commonUtil1.waitForElementClickable(By.xpath("//td[@class='iconColumn']/div"));
            // commonUtil.waitForElementVisible(driver.findElement(By.xpath("//td[@class='iconColumn']/div")));
            driver.findElement(By.xpath("//td[@class='iconColumn']/div")).click();
        }    else {
            throw new Exception("The customer is already created");
        }
    }

    //public By actionButton=By.xpath("(//div[@class='customerNamePlaceHolder']/following::div[2]");
    public By deleteButton=By.xpath("//div[@class='deleteButton'][1]/div");

    public void deleteCustomer(Object customerName) throws InterruptedException {
        commonUtil1.waitForElementToPresent(taskModule);
        driver.findElement(taskModule).click();
        commonUtil1.waitForElementVisible(driver.findElement(searchButton));
        driver.findElement(searchButton).click();
        driver.findElement(searchButton).sendKeys(customerName.toString());
        commonUtil1.waitForElementClickable(By.xpath("//span[text()='"+customerName+"']"));
        driver.findElement(By.xpath("//span[text()='"+customerName+"']")).click();
        //Thread.sleep(5000);
        commonUtil1.waitForElementVisible(driver.findElement(By.xpath("//div[@class='titleEditButtonContainer']//div[2]")));
        driver.findElement(By.xpath("//div[@class='titleEditButtonContainer']//div[2]")).click();
       // Thread.sleep(4000);
        //JavascriptExecutor js=(JavascriptExecutor)driver;
        //WebElement element=driver.findElement(By.xpath("//div[@class='edit_customer_sliding_panel sliding_panel components_panelContainer']"));
        // js.executeScript("arguments[0].setAttribute('style','display:none')", element);
      //  js.executeScript("arguments[0].style.display = 'none'", element);
        commonUtil1.waitForElementClickable(By.xpath("//div[@class='customerNamePlaceHolder']/following::div[2]"));
        driver.findElement(By.xpath("//div[@class='customerNamePlaceHolder']/following::div[2]")).click();
        commonUtil1.waitForElementClickable(deleteButton);
        driver.findElement(deleteButton).click();
        driver.findElement(By.xpath("//span[text()='Delete permanently']")).click();
        Thread.sleep(3000);
    }

}
