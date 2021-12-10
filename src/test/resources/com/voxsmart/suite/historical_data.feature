Feature: Tests that cover the historical data feature

  Scenario: User wants to view a coin's historical data
    Given home page loads correctly
    And user views a random coins historical data
    And checks every predefined date option
    Then the current dates data is always the same

    # More tests to write if I had more time:
    # 1 - Test that when custom picking a date that isn't the current date, then the current date's data is not shown
    # 2 - Test trying to view data for future dates