Feature: Saucedemo Logout

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked

  Scenario: Successful logout
    When the user logs out
    Then the login page should be displayed
