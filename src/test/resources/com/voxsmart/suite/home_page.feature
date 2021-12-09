Feature: Basic tests for the home page

  Scenario: User wants to load the home page
    Given user navigates to home page
    Then home page loads correctly
    And 100 results are displayed