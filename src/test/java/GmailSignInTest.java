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

    @Test
    public void gmailSendAndRecieveEmail() {

        //Click Sign In

        driver.get("http://mail.google.com");
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("kendomustdie@googlemail.com");
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("caprimanga");
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist",driver.findElements(By.partialLinkText("Inbox")).size()>0);

        //Click Compose
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();
        //Fill In Recipient
        WebElement toTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        toTextArea.clear();
        toTextArea.sendKeys("kendomustdie@googlemail.com");

        //Fill In Subject
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        final String subject = "Gmail Send Email Test";
        subjectTextArea.clear();
        subjectTextArea.sendKeys(subject);

        //Fill in Email Body
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
        final String body = "Yeah Buuuuudeeeeeh";
        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);

        //Click Send
        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\" ]"));
        sendButton.click();

        //Click Inbox Again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inbox (1)")));
        WebElement inboxLinkage = driver.findElement(By.linkText("Inbox (1)"));
        inboxLinkage.click();

        //Click New Email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6' ] span[id] b")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6' ] span[id] b"));
        newEmail.click();

        //Verify Email Subject And Body Is Correct
        WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
        Assert.assertEquals("Email Subject Text should be same", subject, subjectArea.getText());
        WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir='ltr']"));
        Assert.assertEquals("Body Text should be same", body, bodyArea.getText());

        //Sign Out.
        WebElement profileButton = driver.findElement(By.cssSelector("span[class='gb_Ka gbii']"));
        profileButton.click();
        WebElement signOutLinkage = driver.findElement(By.id("gb_71"));
        signOutLinkage.click();

    }


    @After
    public void tearDown(){
        driver.close();
    }
}
