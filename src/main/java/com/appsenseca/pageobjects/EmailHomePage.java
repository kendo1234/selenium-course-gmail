package com.appsenseca.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailHomePage {
    public SignInPage signOut(WebDriver driver) {
        WebElement profileButton = driver.findElement(By.cssSelector("span[class='gb_Ka gbii']"));
        profileButton.click();
        WebElement signOutLinkage = driver.findElement(By.id("gb_71"));
        signOutLinkage.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signIn")));

        return PageFactory.initElements(driver, SignInPage.class);

    }

    public boolean doesInboxExist(WebDriver driver) {

        return driver.findElements(By.partialLinkText("Inbox")).size() > 0;
    }

    public void clickComposeButton(WebDriver driver) {
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();
    }

    public void fillInRecipient(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
        WebElement toTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        toTextArea.clear();
        toTextArea.sendKeys("kendomustdie@googlemail.com");
    }

    public void fillInSubject(WebDriver driver, String subject) {
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectTextArea.clear();
        subjectTextArea.sendKeys(subject);
    }

    public void fillInEmailBody(WebDriver driver, String body) {
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body']"));
        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);
    }

    public void clickSendEmail(WebDriver driver) {
        WebElement sendButton = driver.findElement(By.cssSelector("div[aria-label*=\"Send\" ]"));
        sendButton.click();

    }

    public void clickInboxWithNewEmail(WebDriver driver, String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inbox (1)")));
        WebElement inboxLinkage = driver.findElement(By.linkText("Inbox (1)"));
        inboxLinkage.click();
    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6' ] span[id] b")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6' ] span[id] b"));
        newEmail.click();

        return PageFactory.initElements(driver, EmailViewPage.class);
    }
}
