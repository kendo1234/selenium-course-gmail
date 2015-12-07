package com.appsenseca.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SignInPage {
    public void fillInUsername(WebDriver driver, String s) {
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("kendomustdie@googlemail.com");
    }

    public void fillInPassword(WebDriver driver, String s) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys(s);

    }

    public EmailHomePage clickSignIn(WebDriver driver) {
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));

        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean doesSignInButtonExist(WebDriver driver) {
        return driver.findElements(By.id("signIn")).size () > 0;
    }
}
