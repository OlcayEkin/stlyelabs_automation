Feature: Google Search
  As a Google user
  I want to search some countries
  So that, I can analyze the details of the country

  Scenario Outline: Search Countries
    Given I navigate to the https://google.com
    And I search <country>
    Then I check the <country> result is correct

    Examples:
    |country|
    |Bahamas|
    |Amsterdam|