Feature: Expedia
  Me as a user
  I look for a flight accomodations

  Scenario: Flight Accomodation
    Given I navigate to the https://www.expedia.com
    When I look for a flight accommodation from Brussels - National to New York
    Then I see travel option as a result
