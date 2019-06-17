package com.stylelabs.steps;

import com.stylelabs.hook.SeleniumHook;
import com.stylelabs.pages.ExpediaPage;
import com.stylelabs.pages.SearchPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinition {

    private SeleniumHook hook;
    private WebDriver driver;
    private WebDriverWait wait;
    SearchPage searchPage;
    ExpediaPage expediaPage;

    /**
     * Setting browser and page elements
     */
    @Before
    public void setAll(){
        hook = new SeleniumHook ();
        driver= hook.setUpSettings ();
        wait = hook.getWait ();
        searchPage = new SearchPage (driver,wait);
        expediaPage = new ExpediaPage (driver,wait);
    }

    /**
     * Getting console logs and closing the browser
     */
    @After
    public void tearDown(){
        hook.tearDownTest ();
    }

    /**
     * Navigating specific page
     * @param url
     */
    @Given("I navigate to the (.*)")
    public void navigateToSite (String url){ searchPage.navigateTo (url); }

    /**
     * Searching specific text/country from the Google Search
     * @param country
     */
    @And("I search (.*)")
    public void iSearchCountry (String country){ searchPage.searchCountry (country);}

    /**
     * Checking if the result is correct
     * @param country
     */
    @Then("I check the (.*) result is correct")
    public void iCheckTheResultIsCorrect (String country){
        searchPage.waitResult ();
        searchPage.isResultCorrect (country);
    }

    /**
     * Setting flight accommodation from the Expedia
     * @param origin
     * @param dest
     */
    @When("I look for a flight accommodation from (.*) to (.*)")
    public void iLookForAFlightAccommodationFromOriginToDestination (String origin, String dest){
        expediaPage.clickFlights ();
        expediaPage.setOriginAndDest (origin,dest);
        expediaPage.setDates ("6/17/2019", "6/25/2019");
        expediaPage.setTravelerInfo (1,1);
    }

    /**
     * Checking result about the flight accommodation
     */
    @Then("I see travel option as a result")
    public void iSeeTravelOptionAsAResult (){ expediaPage.checkResult ();}

}
