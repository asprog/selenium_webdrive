package com.geekbrains.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseView {
    @FindBy(id = "prependedInput")
    public WebElement inputLogin;

    @FindBy(id = "prependedInput2")
    public WebElement inputPassword;

    @FindBy(id = "_submit")
    public WebElement submitButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);

    }
    public LoginPage inputLogin(String login, String password) {
        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password);
        return this;
    }
    public MainPage submitLogin() {
        submitButton.click();
        return new MainPage(webDriver);
        //return new MainPage(webDriver);
    }
}
