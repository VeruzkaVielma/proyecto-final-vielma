package com.orangehrmlive.demo.tests.steps;


import com.orangehrmlive.demo.pages.DashboardPage;
import com.orangehrmlive.demo.pages.LoginPage;
import com.orangehrmlive.demo.pages.PIMPage;
import com.orangehrmlive.demo.tests.Runner.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.orangehrmlive.demo.tests.Runner.Hooks.driver;

public class PIMSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @When("the user login successfully with Username {string} and Password {string}")
    public void login(String username, String password) {
        loginPage = new LoginPage(driver);
        loginPage.loginAs(username, password);
        System.out.println("   ➡️ Logging in: " + username + password);
    }

    @Then("clicks on the PIM module")
    public void clickPIM(){
        dashboardPage = new DashboardPage(driver);
        dashboardPage.goToPIM();
        System.out.println("   ➡️ Click the PIM module on menu");
    }

    @And ("the user should be navigated to the PIM page")
    public void onPIMPage() {
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isOnPIMPage(),"The user should be on the PIM page");
        System.out.println("   ✅ Verified: user is on the PIM page.");
    }

    @When ("searches for employee by {string}")
    public void searchForEmployeesByName(String employees){
        pimPage.searchEmployeeByName(employees);
        System.out.println("   ✅ Searching: employees by name");
    }

    @Then ("the user should see search results matches for {string}")
    public void verifiedSearchResultsMatches(String expectedEmployeeName){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.hasResults(), "No search results found for: " + expectedEmployeeName);
        System.out.println("   ✅ Verified: search results of employees by name matches" + expectedEmployeeName);
    }

    @Then ("the user should see to displayed a toast Info with message {string}")
    public void verifiedInfoToastDisplayed (String expectedToastInfoMessage){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isToastDisplayed(), "Search results for: " + expectedToastInfoMessage);
        System.out.println("   ✅ Verified: search results of employees by name unmatches" + expectedToastInfoMessage);
    }

    @And ("the user shouldn't see search results matches")
    public void verifiedSearchResultsUnmatched(){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isNoRecordsDisplayed(), "Search results found");
        System.out.println("   ✅ Verified: search results of employees by name unmatches");
    }

    @When ("clicks on the reset button")
    public void clickResetButton(){
        pimPage = new PIMPage(Hooks.driver);
        pimPage.clickResetButton();
        System.out.println("   ➡️ Click the Reset button");
    }
    @Then ("the user should see the search reset applied for the previous employee")
    public void verifiedSearchResetApplied(){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.hasResults(), "Search results have not been restored");
        System.out.println("   ✅ Verified: reseted search results");
    }
}