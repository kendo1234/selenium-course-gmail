package com.appsenseca.util;

import com.appsenseca.pageobjects.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class WebUtil {
    public static SignInPage goToSignInPage(WebDriver driver) {
        driver.get("http://mail.google.com");
        return PageFactory.initElements(driver, SignInPage.class);
    }
}
