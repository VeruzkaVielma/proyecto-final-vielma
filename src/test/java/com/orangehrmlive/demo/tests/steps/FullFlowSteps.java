package com.orangehrmlive.demo.tests.steps;

import com.orangehrmlive.demo.pages.DashboardPage;
import com.orangehrmlive.demo.pages.LoginPage;
import com.orangehrmlive.demo.pages.PIMPage;
import com.orangehrmlive.demo.tests.Runner.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static com.orangehrmlive.demo.tests.Runner.Hooks.driver;

public class FullFlowSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @Given("user is on the OrangeHRM login page")
    public void onLoginPage(){
        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();
        System.out.println("   ➡️ Navigating to the login page");
    }

    @When("the user enters username {string} in the Username field")
    public void enterUsername(String username){
        loginPage.enterUsername(username);
        System.out.println("   ➡️ Entering user: " + username);
    }

    @And("the user enters password {string} in the Password field")
    public void enterPassword(String password){
        loginPage.enterPassword(password);
        System.out.println("   ➡️ Entering pass: " + password);
    }

    @And("the user clicks the login button")
    public void clickLoginButton(){
        loginPage.clickLogin();
        System.out.println("   ➡️ Click the Login button");
    }

    @Then("the user should be navigated to the Dashboard page")
    public void beOnDashboardPage() {
        dashboardPage = new DashboardPage(Hooks.driver);
        Assert.assertTrue(dashboardPage.isOnDashboardPage(),"The user should be on the dashboard page");
        System.out.println("   ✅ Verified: user is on the dashboard page");
    }

    @And ("the Dashboard header should be {string}")
    public void verifyDashboardHeader(String expectedDashboardHeader){
        dashboardPage = new DashboardPage(Hooks.driver);
        String actualDashboardHeader = dashboardPage.getDashboardHeaderText();
        Assert.assertEquals(actualDashboardHeader,expectedDashboardHeader,"The title on the dashboard page is incorrect");
        System.out.println("   ✅ Verified: Header in Dashboard Page is: " + actualDashboardHeader);
    }

    @Then ("the user should see the error message {string}")
    public void verifiedErrorDisplayed(String expectedErrorMessageLoginPage){
        loginPage.isErrorDisplayed();
        String actualErrorMessageLoginPage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessageLoginPage, expectedErrorMessageLoginPage,
                "The error message does not display the expected one");
        System.out.println("   ✅ Verified: Error message is " + actualErrorMessageLoginPage);
    }

    @And ("the user should remain on the Login page")
    public void remainingOnLoginPage() {
        loginPage = new LoginPage(Hooks.driver);
        Assert.assertTrue(loginPage.isOnLoginPage(),"The user should be on the login page");
        System.out.println("   ✅ Verified: user is remaining on the login page");
    }

    @When("the user logs in successfully with username {string} and password {string}")
    public void login(String username, String password) {
        loginPage = new LoginPage(driver);
        loginPage.loginAs(username, password);
        System.out.println("   ➡️ Logging in: ");
    }

    @When("the user clicks the PIM module")
    public void clickPIM(){
        dashboardPage = new DashboardPage(driver);
        dashboardPage.goToPIM();
        System.out.println("   ➡️ Click the PIM module on menu");
    }

    @Then ("the user should be navigated to the PIM page")
    public void onPIMPage() {
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isOnPIMPage(),"The user should be on the PIM page");
        System.out.println("   ✅ Verified: user is on the PIM page");
    }

    @And ("the PIM header should be {string}")
    public void verifyPIMHeader(String expectedPIMHeader){
        pimPage = new PIMPage(Hooks.driver);
        String actualPIMHeader = pimPage.getPIMHeaderText();
        Assert.assertEquals(actualPIMHeader,expectedPIMHeader,"The title on the PIM page is incorrect");
        System.out.println("   ✅ Verified: Header in PIM Page is: " + actualPIMHeader);
    }

    @When ("the user searches for an employee by {string}")
    public void searchForEmployeesByName(String employees){
        pimPage.searchEmployeeByName(employees);
        System.out.println("   ✅ Searching: employees by name");
    }

    @Then ("the user should see search results matching {string}")
    public void verifiedSearchResultsMatches(String expectedEmployeeName){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.hasEmployeeInResults(expectedEmployeeName),
                "No table row contains the employee name: " + expectedEmployeeName);
        System.out.println("   ✅ Verified: a table row contains " + expectedEmployeeName);
    }

    @Then ("the user should see an info toast with the message {string}")
    public void verifiedInfoToastDisplayed (String expectedToastInfoMessage){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isInfoToastDisplayedWithMessage(expectedToastInfoMessage),
                "The expected info toast was not displayed: " + expectedToastInfoMessage);
        System.out.println("   ✅ Verified: info toast message is " + expectedToastInfoMessage);
    }

    @Then ("the user should not see search results")
    public void verifiedSearchResultsUnmatched(){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isNoRecordsDisplayed(), "Search results found");
        System.out.println("   ✅ Verified: search results of employees by name unmatches");
    }

    @When ("the user clicks the reset button")
    public void clickResetButton(){
        pimPage = new PIMPage(Hooks.driver);
        pimPage.clickResetButton();
        System.out.println("   ➡️ Click the Reset button");
    }
    @Then ("the employee name search field should be empty")
    public void verifiedEmployeeNameFieldIsEmpty(){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.isEmployeeNameFieldEmpty(), "The employee search field was not cleared");
        System.out.println("   ✅ Verified: the employee name search field is empty");
    }

    @And ("the default employee results should be displayed")
    public void verifiedDefaultEmployeeResultsAreDisplayed(){
        pimPage = new PIMPage(Hooks.driver);
        Assert.assertTrue(pimPage.hasResults(), "Search results have not been restored");
        System.out.println("   ✅ Verified: default employee results were restored");
    }

    @When ("the user clicks the Dashboard option in the menu")
    public void clickDashboardOptionMenu() {
        pimPage = new PIMPage(Hooks.driver);
        pimPage.goToDashboard();
        System.out.println("   ➡️ Returning to the dashboard page");
    }

    @When("the user clicks the logout button")
    public void clickLogoutButton() {
        dashboardPage.clickLogout();
        System.out.println("   ➡️ Click the Logout button");
    }
    @Then ("the user should be redirected to the Login page")
    public void redirectedToLoginPage() {
        loginPage = new LoginPage(Hooks.driver);
        Assert.assertTrue(loginPage.isOnLoginPage(),"The user should be on the Login page");
        System.out.println("   ✅ Verified: user logout successful");
    }
}