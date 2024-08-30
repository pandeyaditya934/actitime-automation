package actitimeautomation.imp1.pages;

import actitimeautomation.imp1.common.CommonUtil1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class TaskPage {
    CommonUtil1 commonUtil1;
    WebDriver driver;
    CustomerPage customerPage;
    ProjectPage projectPage;

    @FindBy(xpath = "//input[@placeholder='Enter task name'][1]")
    WebElement taskNameTextArea;

    public TaskPage(WebDriver driver) throws IOException {
        this.driver=driver;
        customerPage=new CustomerPage(driver);
        projectPage=new ProjectPage(driver);
        commonUtil1=new CommonUtil1(driver);
        PageFactory.initElements(driver,this);
    }
    public void createTask(Object customerName, Object projectName,Object discriptionArea, Object taskName) throws InterruptedException {
        customerPage.createCustomer(customerName);
        commonUtil1.waitForElementToPresent(customerPage.addNewButton);
        driver.findElement(customerPage.addNewButton).click();
        commonUtil1.waitForElementClickable(projectPage.createNewPojectButton);
        projectPage.createNewPojectButton.click();
        commonUtil1.waitForElementClickable(projectPage.projectNameTextArea);
        projectPage.projectNameTextArea.sendKeys(projectName.toString());
        commonUtil1.waitForElementClickable(projectPage.discriptionTextArea);
        projectPage.discriptionTextArea.click();
        projectPage.discriptionTextArea.sendKeys(discriptionArea.toString());
        commonUtil1.waitForElementClickable(taskNameTextArea);
        taskNameTextArea.click();
        taskNameTextArea.sendKeys(taskName.toString());
        commonUtil1.waitForElementClickable(projectPage.prjectCreateButton);
        projectPage.prjectCreateButton.click();
    }
}
