# FULL_FLOW.FEATURE - E2E scenario for the complete workflow involving the use of the OrangeHRM page.

Feature: Full Flow on OrangeHRM
  As an authenticated user
  I want to navigate through OrangeHRM
  To manage employees and verify system functionalities

  Background:
    Given user is on the OrangeHRM login page

  # ============================================================
  # SCENARIO 1: Successful Login
  # ============================================================
  @login @successful @smoke
  Scenario: Successful login with valid credentials
    When the user enters username "Admin" in the Username field
    And the user enters password "admin123" in the Password field
    And the user clicks the login button
    Then the user should be navigated to the Dashboard page

  # ============================================================
  # SCENARIO 2: Failed Login
  # ============================================================
  @login @failed @smoke
  Scenario: Failed login with invalid credentials
    When the user enters username "user" in the Username field
    And the user enters password "pass" in the Password field
    And the user clicks the login button
    Then the user should see the error message "Invalid credentials"
    And the user should remain on the Login page

  # ============================================================
  # SCENARIO 3: Search Employee by name
  # ============================================================
  @pim @search
  Scenario: Search for an existing employee in the PIM module
    When the user logs in successfully with username "Admin" and password "admin123"
    Then the user should be navigated to the Dashboard page
    When the user clicks the PIM module
    Then the user should be navigated to the PIM page
    When the user searches for an employee by "John"
    Then the user should see search results matching "John"

  # ============================================================
  # SCENARIO 4: Search for a non-existing employee
  # ============================================================
  @pim @search @notfound
  Scenario: Search for a non-existing employee in the PIM module
    When the user logs in successfully with username "Admin" and password "admin123"
    Then the user should be navigated to the Dashboard page
    When the user clicks the PIM module
    Then the user should be navigated to the PIM page
    When the user searches for an employee by "test-automation"
    Then the user should see an info toast with the message "No Records Found"
    And the user should not see search results

  # ============================================================
  # SCENARIO 5: Clear applied employee search
  # ============================================================
  @pim @search @reset
  Scenario: Reset an employee search in the PIM module
    When the user logs in successfully with username "Admin" and password "admin123"
    Then the user should be navigated to the Dashboard page
    When the user clicks the PIM module
    Then the user should be navigated to the PIM page
    When the user searches for an employee by "John"
    Then the user should see search results matching "John"
    When the user clicks the reset button
    Then the employee name search field should be empty
    And the default employee results should be displayed

  # ============================================================
  # SCENARIO 6: Full E2E Flow
  # ============================================================
  @e2e
  Scenario: Complete E2E flow - Login, Search Employee and Logout
    When the user logs in successfully with username "Admin" and password "admin123"
    Then the user should be navigated to the Dashboard page
    When the user clicks the PIM module
    Then the user should be navigated to the PIM page
    When the user searches for an employee by "John"
    Then the user should see search results matching "John"
    When the user clicks the Dashboard option in the menu
    Then the user should be navigated to the Dashboard page
    When the user clicks the logout button
    Then the user should be redirected to the Login page