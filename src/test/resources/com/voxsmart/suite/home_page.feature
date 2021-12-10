Feature: Basic tests for the home page

  Scenario: User loads the home page and sees 100 coins displayed to them
    Given user navigates to home page
    Then home page loads correctly
    And 100 results are displayed