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

    @Before
    public void setAll(){
        hook = new SeleniumHook ();
        driver= hook.setUpSettings ();
        wait = hook.getWait ();
        searchPage = new SearchPage (driver,wait);
        expediaPage = new ExpediaPage (driver,wait);
    }

    @After
    public void tearDown(){
        hook.tearDownTest ();
    }

    @Given("I navigate to the (.*)")
    public void navigateToSite (String url){ searchPage.navigateTo (url); }

    @And("I search (.*)")
    public void iSearchCountry (String country){ searchPage.searchCountry (country);}

    @And("I wait the result")
    public void iWaitTheResult (){ searchPage.waitResult (); }

    @Then("I check the (.*) result is correct")
    public void iCheckTheResultIsCorrect (String country){ searchPage.isResultCorrect (country); }

    @When("I look for a flight accommodation from (.*) to (.*)")
    public void iLookForAFlightAccommodationFromOriginToDestination (String origin, String dest){
        expediaPage.clickFlights ();
        expediaPage.setOriginAndDest (origin,dest);
        expediaPage.setDates ("6/17/2019", "6/25/2019");
        expediaPage.setTravelerInfo (1,1);
    }

    @Then("I see travel option as a result")
    public void iSeeTravelOptionAsAResult (){ expediaPage.checkResult ();}

}
