package com.appsenseca.pageobjects;

import com.appsenseca.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SignInPage {
    public void fillInUsername(WebDriver driver, String s) {
        WebUtil.clearAndSendKeys(driver, By.id("Email"), s);

    }

    public void fillInPassword(WebDriver driver, String s) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebUtil.clearAndSendKeys(driver, By.id("Passwd"), s);

    }

    public EmailHomePage clickSignIn(WebDriver driver) {
        WebUtil.click(driver, By.id("signIn"));

        WebUtil.waitForElementVisible(driver, By.partialLinkText("Inbox"));

        return PageFactory.initElements(driver, EmailHomePage.class);
    }

    public boolean doesSignInButtonExist(WebDriver driver) {
        return WebUtil.doesElementExist(driver, By.id("signIn"));
    }
}
