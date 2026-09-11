package com.orangehrmlive.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PIMPage extends BasePage{

    //Attributes:
    private By titlePIMPage = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By employeeNameField = By.cssSelector("input[placeholder='Type for hints...']");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By resetButton = By.cssSelector("button[type='reset']");
    private By tableRows = By.cssSelector(".oxd-table-body .oxd-table-card");
    private By loadingSpinner = By.cssSelector(".oxd-loading-spinner");
    private By noRecordFoundMessage = By.xpath("//span[normalize-space()='No Records Found']");
    private By infoToast = By.cssSelector(".oxd-text.oxd-text--p.oxd-text--toast-message.oxd-toast-content-text");
    private By dashboardOptionMenu = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name']" +
            "[normalize-space()='Dashboard']");

    //Constructor method:
    public PIMPage(WebDriver driver) {
        super(driver);
    }

    // Mandatory methods:
    public PIMPage searchEmployeeByName(String employeeName){
        type(employeeNameField, employeeName);
        click(searchButton);
        return this;
    }

    public PIMPage clickResetButton(){
        click(resetButton);
        return this;
    }

    public DashboardPage goToDashboard(){
        click(dashboardOptionMenu);
        return new DashboardPage(driver);
    }

    // Verification methods:
    public boolean hasResults(){
        try {
            waitForElementToDisappear(loadingSpinner);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(tableRows, 0));
            return true;
        } catch (TimeoutException e) { return false; }
    }

    public boolean hasEmployeeInResults(String expectedEmployeeName){
        if (!hasResults()) { return false; }
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) { String rowText = row.getText();
            if (rowText.contains(expectedEmployeeName)) { return true; }
        }
        return false;
    }

    public String getPIMHeaderText(){ return getText(titlePIMPage); }

    public boolean isNoRecordsDisplayed(){ return isElementVisible(noRecordFoundMessage); }

    public boolean isInfoToastDisplayedWithMessage(String expectedInfoToastMessage) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(infoToast, expectedInfoToastMessage));
        } catch (TimeoutException e) { return false; }
    }

    public boolean isOnPIMPage(){ return driver.getCurrentUrl().contains("/pim"); }

    public boolean isEmployeeNameFieldEmpty(){
        try {
            WebElement employeeNameInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(employeeNameField));
            String inputValue = employeeNameInput.getAttribute("value");
            return inputValue == null || inputValue.equals("");
        } catch (TimeoutException e) { return false; }
    }
}