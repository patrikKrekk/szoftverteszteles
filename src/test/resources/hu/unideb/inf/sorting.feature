Feature: Product Sorting

  Background:
    Given the home page is opened
    And the 'Username' field is filled with 'standard_user'
    And the 'Password' field is filled with 'secret_sauce'
    And the 'Login' button is clicked

  Scenario: Sorting products by name A to Z
    When the user sorts products by 'Name (A to Z)'
    Then the products should be sorted by 'Name (A to Z)'

  Scenario: Sorting products by name Z to A
    When the user sorts products by 'Name (Z to A)'
    Then the products should be sorted by 'Name (Z to A)'

  Scenario: Sorting products by price low to high
    When the user sorts products by 'Price (low to high)'
    Then the products should be sorted by 'Price (low to high)'

  Scenario: Sorting products by price high to low
    When the user sorts products by 'Price (high to low)'
    Then the products should be sorted by 'Price (high to low)'
