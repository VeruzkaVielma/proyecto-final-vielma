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
  @login @sucessful @smoke
  Scenario: Successful login with valid credentials
    When the user enters username "Admin" on Username field
    And enters password "admin123" on Password field
    And clicks on the login button
    Then the user logged should be navigated to the Dashboard page
    And the title should be "Dashboard" on header page

  # ============================================================
  # SCENARIO 2: Failed Login
  # ============================================================
  @login @failed @smoke
  Scenario: Failed login with invalid credentials
    When the user enters username "user" on Username field
    And enters password "pass" on Password field
    And clicks on the login button
    Then the user should see an error message displayed "Invalid credentials"
    And the user remaining on the Login page

  # ============================================================
  # SCENARIO 3: Search Employee by name
  # ============================================================
  @pim @search
  Scenario: Search an existing employee in PIM module
    When the user login successfully with Username "Admin" and Password "admin123"
    Then clicks on the PIM module
    And the user should be navigated to the PIM page
    When searches for employee by "John"
    Then the user should see search results matches for "John"

  # ============================================================
  # SCENARIO 4: Search Employee no existing
  # ============================================================
  @pim @search @notfound
  Scenario: Search an no existing employee in PIM module
    When the user login successfully with Username "Admin" and Password "admin123"
    Then clicks on the PIM module
    And the user should be navigated to the PIM page
    When searches for employee by "test-automation"
    Then the user should see to displayed a toast Info with message "No Records Found"
    And the user shouldn't see search results matches

  # ============================================================
  # SCENARIO 5: Clear applied employee search
  # ============================================================
  @pim @search @reset
  Scenario: Apply the employee search reset in PIM module
    When the user login successfully with Username "Admin" and Password "admin123"
    Then clicks on the PIM module
    And the user should be navigated to the PIM page
    When searches for employee by "John"
    Then the user should see search results matches for "John"
    When clicks on the reset button
    Then the user should see the search reset applied for the previous employee

  # ============================================================
  # SCENARIO 6: Full E2E Flow
  # ============================================================
  @e2e
  Scenario: Complete E2E flow - Login, Search Employee and Logout
    When the user login successfully with Username "Admin" and Password "admin123"
    Then the user logged should be navigated to the Dashboard page
    And clicks on the PIM module
    Then the user should be navigated to the PIM page
    When searches for employee by "John"
    Then the user should see search results matches for "John"
    When clicks on Dashboard option from menu
    Then the user should returned to the Dashboard page
    When clicks on logout button
    Then the user should be redirected to the Login page