package com.stylelabs.models;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ExpediaModel {
    /**
     * ExpediaModel contains element details for the Expedia page
     */

    @FindBy(how = How.ID, using = "flight-origin-hp-flight")
    public WebElement origin;

    @FindBy(how = How.ID, using = "flight-destination-hp-flight")
    public WebElement destination;

    @FindBy(how = How.ID, using = "traveler-selector-hp-flight")
    public WebElement travelerInformation;

    @FindBy(how = How.CSS, using = "#traveler-selector-hp-flight > div > ul > li > div > div > div.traveler-selector-room-data.target-clone-field > div.uitk-grid.step-input-outside.gcw-component.gcw-component-step-input.gcw-step-input.gcw-component-initialized > div:nth-child(4) > button > span.uitk-icon > svg")
    public WebElement adultCount;

    @FindBy(how = How.CSS, using = "li.open > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > button:nth-child(1) > span:nth-child(1) > svg:nth-child(1)")
    public WebElement childCount;

    @FindBy(how = How.ID, using = "flight-age-select-2-label-hp-flight")
    public List<WebElement> childAgeList;

    @FindBy(how = How.ID,using = "flight-departing-hp-flight")
    public WebElement departureDate;

    @FindBy(how = How.ID, using = "flight-returning-hp-flight")
    public WebElement arrivalDate;

    @FindBy(how = How.CSS, using = "#gcw-flights-form-hp-flight > div.cols-nested.ab25184-submit > label > button")
    public WebElement search;

    @FindBy(how = How.ID, using = "flight-listing-container")
    public WebElement result;

    @FindBy(how = How.CSS, using = "#typeaheadDataPlain > div > li")
    public List<WebElement> dropDownResultList;

    @FindBy(how = How.ID, using = "tab-flight-tab-hp")
    public WebElement flight;

    @FindBy(how = How.CSS, using = ".datepicker-cal-month-header")
    public List<WebElement> monthListPicker;

    @FindBy(how = How.CSS, using = "li.open > div:nth-child(2) > footer:nth-child(2) > div:nth-child(1) > div:nth-child(2) > button:nth-child(1)")
    public WebElement close;

}
