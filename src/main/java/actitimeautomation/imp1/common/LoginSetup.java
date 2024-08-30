package actitimeautomation.imp1.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSetup {
    public WebDriver driver;
    public LoginSetup(WebDriver driver)
    {   this.driver=driver;
    }
    public void loginMethod(String username, String password)
    {   driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//div[starts-with(text(),'Login')]")).click();
    }
}
