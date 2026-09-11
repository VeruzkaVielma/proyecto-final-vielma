package com.orangehrmlive.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage{

    //Attributes:
    private By titleDashboardPage = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By pimOptionMenu = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']" +
            "[normalize-space()='PIM']");
    private By userMenuDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private By logoutButton = By.cssSelector(".oxd-userdropdown-link[href*='logout']");

    // Constructor method:
    public DashboardPage(WebDriver driver){
        super(driver);
    }

    // Mandatory methods:
    public PIMPage goToPIM(){
        click(pimOptionMenu);
        return new PIMPage(driver);
    }

    public LoginPage clickLogout(){
        click(userMenuDropdown);
        click(logoutButton);
        return new LoginPage(driver);
    }

    //Verifications methods:
    public boolean isOnDashboardPage(){
        return driver.getCurrentUrl().contains("/dashboard");
    }

    public String getDashboardHeaderText(){return getText(titleDashboardPage);
    }
}