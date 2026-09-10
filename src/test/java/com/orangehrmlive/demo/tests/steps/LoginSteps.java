package com.orangehrmlive.demo.tests.steps;

import com.orangehrmlive.demo.pages.DashboardPage;
import com.orangehrmlive.demo.pages.LoginPage;
import com.orangehrmlive.demo.tests.Runner.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Given("user is on the OrangeHRM login page")
    public void onLoginPage(){
        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();
        System.out.println("   ➡️ Navigating to the login page");
    }

    @When("the user enters username {string} on Username field")
    public void enterCredentials(String username){
        loginPage.enterUsername(username);
        System.out.println("   ➡️ Entering user: " + username);
    }

    @And("enters password {string} on Password field")
    public void enterPassword(String password){
        loginPage.enterPassword(password);
        System.out.println("   ➡️ Entering pass: " + password);
    }

    @And("clicks on the login button")
    public void clickLoginButton(){
        loginPage.clickLogin();
        System.out.println("   ➡️ Click the Login button.");
    }

    @Then("the user logged should be navigated to the Dashboard page")
    public void beOnDashboardPage() {
        dashboardPage = new DashboardPage(Hooks.driver);
        Assert.assertTrue(dashboardPage.isOnDashboardPage(),"The user should be on the dashboard page");
        System.out.println("   ✅ Verified: user is on the dashboard page");
    }

    @And("the title should be {string} on header page")
    public void titleOnHeaderShouldBeDashboard (String expectedTitleDashboardPage){
        dashboardPage = new DashboardPage(Hooks.driver);
        String actualTitleDashboardPage = dashboardPage.getHeaderText();
        Assert.assertEquals(actualTitleDashboardPage, expectedTitleDashboardPage,
                "The title does not match the expected one");
        System.out.println("   ✅ Verified: Title is '" + actualTitleDashboardPage);
    }

    @Then ("the user should see an error message displayed {string}")
    public void verifiedErrorDisplayed(String expectedErrorMessageLoginPage){
        loginPage.isErrorDisplayed();
        String actualErrorMessageLoginPage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessageLoginPage, expectedErrorMessageLoginPage,
                "The error message does not display the expected one");
        System.out.println("   ✅ Verified: Error message is " + actualErrorMessageLoginPage);
    }

    @And ("the user remaining on the Login page")
    public void remainingOnLoginPage() {
        loginPage = new LoginPage(Hooks.driver);
        Assert.assertTrue(loginPage.isOnLoginPage(),"The user should be on the login page");
        System.out.println("   ✅ Verified: user is remaining on the login page");
    }
}