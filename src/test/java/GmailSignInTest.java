import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class GmailSignInTest {
    WebDriver driver  = new FirefoxDriver();
    @Test
    public void gmailloginshouldbesuccessful(){
        //1. Go to gmail

        driver.get("http://mail.google.com");
        //2. Fill in username
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("kendomustdie@googlemail.com");
        //For new gmail, click next is needed
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();
        //3. Fill in password
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("caprimanga");
        //4. click sign in
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        //5. verify user did sign in
        WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist",driver.findElements(By.partialLinkText("Inbox")).size()>0);
        //6. sign out
       WebElement profileButton = driver.findElement(By.cssSelector("span[class='gb_Ka gbii']"));
        profileButton.click();
        WebElement signOutLinkage = driver.findElement(By.id("gb_71"));
        signOutLinkage.click();
        //7. verified user did sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));
        Assert.assertTrue("signIn button should exist",driver.findElements(By.id("signIn")).size()>0);

    }
    @After
    public void tearDown(){
        driver.close();
    }
}
