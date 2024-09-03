package actitimeautomation.imp1.pages;

import actitimeautomation.imp1.common.CommonUtil;
import actitimeautomation.imp1.common.CommonUtil;
import actitimeautomation.imp1.common.PropertyHandling;
import actitimeautomation.imp1.common.PropertyHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ProjectPage {
    WebDriver driver;
    PropertyHandling PropertyHandling;
    CommonUtil CommonUtil;
    CustomerPage customerPage;

    @FindBy(xpath = "//div[@class='item createNewProject']")
    WebElement createNewPojectButton;
    @FindBy(xpath = "(//input[@placeholder='Enter Project Name'])[2]")
    WebElement projectNameTextArea;
    @FindBy(xpath = "//textarea[@placeholder='Add Project Description']")
    WebElement discriptionTextArea;
    @FindBy(xpath = "//div[text()='Create Project']")
    WebElement prjectCreateButton;

    public ProjectPage(WebDriver driver) throws IOException {
        this.driver=driver;
        PropertyHandling=new PropertyHandling();
        CommonUtil=new CommonUtil(driver);
        PageFactory.initElements(driver,this);
        customerPage=new CustomerPage(driver);
    }

    public void createProject(Object customerName,Object proName, Object discriptionArea) throws InterruptedException {
        customerPage.createCustomer(customerName);
        CommonUtil.waitForElementToPresent(customerPage.addNewButton);
        driver.findElement(customerPage.addNewButton).click();
        createNewPojectButton.click();
        CommonUtil.waitForElementClickable(projectNameTextArea);
        projectNameTextArea.sendKeys(proName.toString());
        CommonUtil.waitForElementClickable(discriptionTextArea);
        discriptionTextArea.click();
        discriptionTextArea.sendKeys(discriptionArea.toString());
        CommonUtil.waitForElementClickable(prjectCreateButton);
        prjectCreateButton.click();
        Thread.sleep(4000);
    }
    public void checkProject(Object customerName,Object projectName) throws InterruptedException {
        //CommonUtil.waitForElementClickable(customerPage.searchButton);
        driver.findElement(customerPage.searchButton).click();
        driver.findElement(customerPage.searchButton).sendKeys(projectName.toString());
        Thread.sleep(3000);
        //CommonUtil.waitForElementVisible(driver.findElement(By.xpath("//span[text()='"+projectName+"']")));
        boolean projectVisible=driver.findElement(By.xpath("//span[text()='"+projectName+"']")).isDisplayed();
        if(projectVisible)
        {   System.out.println("yes project is created");
            Thread.sleep(3000);
            //CommonUtil.waitForElementVisible(driver.findElement(By.xpath("//div[text()='"+customerName+"']")));
            boolean assignedCustomer=driver.findElement(By.xpath("//div[text()='"+customerName+"']")).isDisplayed();
            if(assignedCustomer)
            {   System.out.println("yes assign to the right customer as well");
                CommonUtil.waitForElementClickable(By.xpath("//td[@class='iconColumn']/div"));
                driver.findElement(By.xpath("//td[@class='iconColumn']/div")).click();
            }else{  System.out.println("not assign to the right customer");
            }
        }else { System.out.println("the project is not created");
        }
    }
}
