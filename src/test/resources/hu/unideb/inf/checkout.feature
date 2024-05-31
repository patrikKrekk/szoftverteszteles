Feature: Saucedemo Checkout

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked
    And the 'Cart' button is clicked
    And the 'Checkout' button is clicked

  Scenario Outline: Incomplete checkout information
    Given the 'First Name' field is filled with '<firstName>'
    And the 'Last Name' field is filled with '<lastName>'
    And the 'Zip Code' field is filled with '<zipCode>'
    When the 'Continue' button is clicked
    Then the checkout error message '<errorMessage>' is shown
    Examples:
      | firstName | lastName | zipCode | errorMessage                          |
      |           |          |         | Error: First Name is required         |
      | John      |          |         | Error: Last Name is required          |
      | John      | Doe      |         | Error: Postal Code is required        |
      |           | Doe      | 12345   | Error: First Name is required         |
      | John      |          | 12345   | Error: Last Name is required          |
      |           | Doe      |         | Error: First Name is required         |
      |           |          | 12345   | Error: First Name is required         |