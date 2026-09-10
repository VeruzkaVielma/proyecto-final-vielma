package com.orangehrmlive.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    //Attributes:
    private By usernameField =  By.cssSelector("input[placeholder='Username']");
    private By passwordField = By.cssSelector("input[placeholder='Password']");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.cssSelector(".oxd-alert-content-text");
    private static final String URL = "https://opensource-demo.orangehrmlive.com/";

    // Constructor method
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Mandatory methods:
    public LoginPage goTo(){
        driver.get(URL);
        return this;
    }

    public LoginPage enterUsername(String username) {
        type(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    public DashboardPage clickLogin(){
        click(loginButton);
        return new DashboardPage(driver);
    }

    // High-level method: complete login
    public void loginAs(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Validations methods:
    public boolean isOnLoginPage() {
        return waitForUrlContains("/login");
    }

    public boolean isErrorDisplayed() {
        return isElementVisible(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}