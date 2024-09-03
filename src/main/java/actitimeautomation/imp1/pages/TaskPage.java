package actitimeautomation.imp1.pages;

import actitimeautomation.imp1.common.CommonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class TaskPage {
    CommonUtil CommonUtil;
    WebDriver driver;
    CustomerPage customerPage;
    ProjectPage projectPage;

    @FindBy(xpath = "//input[@placeholder='Enter task name'][1]")
    WebElement taskNameTextArea;

    public TaskPage(WebDriver driver) throws IOException {
        this.driver=driver;
        customerPage=new CustomerPage(driver);
        projectPage=new ProjectPage(driver);
        CommonUtil=new CommonUtil(driver);
        PageFactory.initElements(driver,this);
    }
    public void createTask(Object customerName, Object projectName,Object discriptionArea, Object taskName) throws InterruptedException {
        customerPage.createCustomer(customerName);
        CommonUtil.waitForElementToPresent(customerPage.addNewButton);
        driver.findElement(customerPage.addNewButton).click();
        CommonUtil.waitForElementClickable(projectPage.createNewPojectButton);
        projectPage.createNewPojectButton.click();
        CommonUtil.waitForElementClickable(projectPage.projectNameTextArea);
        projectPage.projectNameTextArea.sendKeys(projectName.toString());
        CommonUtil.waitForElementClickable(projectPage.discriptionTextArea);
        projectPage.discriptionTextArea.click();
        projectPage.discriptionTextArea.sendKeys(discriptionArea.toString());
        CommonUtil.waitForElementClickable(taskNameTextArea);
        taskNameTextArea.click();
        taskNameTextArea.sendKeys(taskName.toString());
        CommonUtil.waitForElementClickable(projectPage.prjectCreateButton);
        projectPage.prjectCreateButton.click();
    }
}
