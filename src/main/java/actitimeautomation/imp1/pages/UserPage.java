package actitimeautomation.imp1.pages;

import actitimeautomation.imp1.common.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage {
    CommonUtil CommonUtil;
    WebDriver driver;

    public UserPage(WebDriver driver) {
        this.driver = driver;
        CommonUtil CommonUtil = new CommonUtil(this.driver);
        this.CommonUtil = CommonUtil;
    }

    public By UserModule = By.xpath("//div[text()='Users']");
    public By newUser = By.xpath("//div[text()='New User']");
    public By firstNameText = By.xpath("//input[@id='createUserPanel_firstNameField']");
    public By lastNameText = By.xpath("//input[@id='createUserPanel_lastNameField']");
    public By emailText = By.xpath("//input[@id='createUserPanel_emailField']");
    public By saveButton = By.xpath("//div[text()='Save & Send Invitation']");
    public By closeButton = By.xpath("//span[text()='Close'][1]");

    public void createUser(Object email, Object lastName, Object userFirstName) {
        CommonUtil.waitForElementClickable(UserModule);
        driver.findElement(UserModule).click();
        driver.findElement(newUser).click();
        CommonUtil.waitForElementClickable(firstNameText);
        driver.findElement(firstNameText).click();
        driver.findElement(firstNameText).sendKeys(userFirstName.toString());
        driver.findElement(lastNameText).click();
        driver.findElement(lastNameText).sendKeys(lastName.toString());
        driver.findElement(emailText).click();
        driver.findElement(emailText).sendKeys(email.toString());
        driver.findElement(saveButton).click();
        CommonUtil.waitForElementClickable(closeButton);
        driver.findElement(closeButton).click();
    }

    public By searchButton = By.xpath("//div[@class='userList_wordsFilter'][1]/div");
    public By searchButtonTextArea = By.xpath("//input[@placeholder='Start typing name...']");


    public void verifyUser(Object emailid, Object lastname, Object firstname) {
        CommonUtil.waitForElementClickable(UserModule);
        driver.findElement(UserModule).click();
        driver.findElement(searchButton).click();
        driver.findElement(searchButtonTextArea).sendKeys(firstname.toString());
        Boolean indicationMessage = driver.findElement(By.xpath("//span[@id='noUsersToShowId']")).isDisplayed();
        if (!indicationMessage) {
            System.out.println("the customer is created");
        } else {
            System.out.println("the customer is not created");
        }
    }

    //@FindBy(xpath = "//table[@class='userNameContainer']")
    //WebElement userNameContainer;
    public By userNameContainer= By.xpath("//table[@class='userNameContainer']");

    @FindBy(xpath = "//div[@class='simpleListMenuButton components_userGroupSelectorMenu emptyList notEmpty']")
    WebElement emptyList;

   /* public void verifyAssignDepartment(String deptName) throws InterruptedException {
//        CommonUtil.fluentwait(By.xpath("//table[@class='userNameContainer']"));
        CommonUtil.waitForElementClickable(driver.findElement(userNameContainer));
        driver.findElement(userNameContainer).click();
        Thread.sleep(5000);
        emptyList.click();
//        CommonUtil.fluentwait(By.xpath("//div[text()='"+deptName+"']"));
        driver.findElement(By.xpath("//div[text()='" + deptName + "']")).click();
        CommonUtil.waitForElementToPresent(By.xpath("//table[@class='userNameContainer']"));
        driver.findElement(By.xpath("//table[@class='userNameContainer']")).click();
    }
    */

}
