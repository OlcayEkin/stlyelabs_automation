Feature: Google Search
  Me as a user;
  I want to search country from the Google

  Scenario Outline: Search Countries
    Given I navigate to the https://google.com
    And I search <country>
    And I wait the result
    Then I check the <country> result is correct

    Examples:
    |country|
    |Bahamas|
    |Amsterdam|