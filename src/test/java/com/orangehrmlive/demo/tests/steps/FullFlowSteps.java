package com.orangehrmlive.demo.tests.steps;

import com.orangehrmlive.demo.pages.DashboardPage;
import com.orangehrmlive.demo.pages.LoginPage;
import com.orangehrmlive.demo.pages.PIMPage;
import com.orangehrmlive.demo.tests.Runner.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class FullFlowSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @When ("clicks on Dashboard option from menu")
    public void clickDashboardOptionMenu() {
        dashboardPage = new DashboardPage(Hooks.driver);
        clickDashboardOptionMenu();
        System.out.println("   ➡️ Returning to the dashboard page");
    }

    @When("clicks on logout button")
    public void clickLogoutButton() {
        dashboardPage.clickLogout();
        System.out.println("   ➡️ Click the Logout button");
    }
    @Then ("the user should be redirected to the Login page")
    public void redirectedToLoginPage() {
        loginPage = new LoginPage(Hooks.driver);
        Assert.assertTrue(loginPage.isOnLoginPage(),"The user should be on the Login page");
        System.out.println("   ✅ Verified: user logout sucessful");
    }
}
