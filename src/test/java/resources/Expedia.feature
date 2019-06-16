Feature: Expedia
  As a Expedia user
  I want to look a flight accomodation
  So that, I can examine flight option

  Scenario: Flight Accomodation
    Given I navigate to the https://www.expedia.com
    When I look for a flight accommodation from Brussels - National to New York
    Then I see travel option as a result
