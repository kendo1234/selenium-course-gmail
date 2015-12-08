import com.appsenseca.pageobjects.EmailHomePage;
import com.appsenseca.pageobjects.EmailViewPage;
import com.appsenseca.pageobjects.SignInPage;
import com.appsenseca.util.WebUtil;
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
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

        //2. Fill in username
        signInPage.fillInUsername(driver, "kendomustdie@googlemail.com");

        //For new gmail, click next is needed
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        //3. Fill in password
        signInPage.fillInPassword(driver, "caprimanga");

        //4. click sign in
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        //5. verify user did sign in
        Assert.assertTrue("Inbox should exist", emailHomePage.doesInboxExist(driver));

        //6. sign out
        signInPage = emailHomePage.signOut(driver);

        //7. verified user did sign out
        Assert.assertTrue("signIn button should exist", signInPage.doesSignInButtonExist(driver));

    }

    @Test
    public void gmailSendAndRecieveEmail() {

        //1. Go to gmail
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

        //2. Fill in username
        signInPage.fillInUsername(driver, "kendomustdie@googlemail.com");

        //For new gmail, click next is needed
        WebElement nextButton = driver.findElement(By.id("next"));
        nextButton.click();

        //3. Fill in password
        signInPage.fillInPassword(driver, "caprimanga");

        //4. click sign in
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);


        //Click Compose
        emailHomePage.clickComposeButton(driver);

        //Fill In Recipient
        emailHomePage.fillInRecipient(driver,"kendomustdie@googlemail.com");


        //Fill In Subject
        final String subject = "Gmail Send Email Test";
        emailHomePage.fillInSubject(driver, subject);


        //Fill in Email Body
        final String body = "Yeah Buuuuudeeeeeh";
        emailHomePage.fillInEmailBody(driver, body);


        //Click Send
        emailHomePage.clickSendEmail(driver);

        //Click Inbox Again
        emailHomePage.clickInboxWithNewEmail(driver, "Inbox (1)");


        //Click New Email
        EmailViewPage emailViewPage = emailHomePage.clickNewEmail(driver);

        //Verify Email Subject And Body Is Correct
        String actualSubject = emailViewPage.getEmailSubjectText(driver);

        Assert.assertEquals("Email Subject Text should be same", subject, actualSubject);
        String actualBody = emailViewPage.getEmailBodyText(driver);
        Assert.assertEquals("Body Text should be same", body, actualBody);

        //Sign Out.
         emailHomePage.signOut(driver);


    }


    @After
    public void tearDown(){
        driver.close();
    }
}
