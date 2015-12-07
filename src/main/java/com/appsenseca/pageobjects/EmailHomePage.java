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
}
