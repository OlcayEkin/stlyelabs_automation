package com.stylelabs.pages;

import com.stylelabs.models.ExpediaModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpediaPage {
    private static final Logger log   = LogManager.getLogger (SearchPage.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private ExpediaModel expediaModel = new ExpediaModel ();

    public ExpediaPage (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait=wait;
        PageFactory.initElements (driver,expediaModel);
    }

    /**
     * Choosing flight option from the panel
     */
    public void clickFlights(){ wait.until (ExpectedConditions.visibilityOf (expediaModel.flight)).click (); }

    /**
     * Choosing origin and destination locations
     * @param origin
     * @param dest
     */
    public void setOriginAndDest(String origin, String dest){
        wait.until (ExpectedConditions.visibilityOf (expediaModel.origin)).sendKeys (origin);
        selectDropDownList (origin);
        wait.until (ExpectedConditions.visibilityOf (expediaModel.destination)).sendKeys (dest);
        selectDropDownList (dest);
        log.info ("Searching flights from "+origin+" to "+dest);
    }

    /**
     * Setting passenger info
     * @param adult
     * @param child
     */
    public void setTravelerInfo(int adult, int child){
        wait.until (ExpectedConditions.visibilityOf (expediaModel.travelerInformation)).click ();
        if(adult > 1) setPerson (adult,expediaModel.adultCount);
        setPerson (child,expediaModel.childCount);
        for(int i =1; i <= child; i++){
            Select selectAge = new Select (driver.findElement (By.id ("flight-age-select-"+i+"-hp-flight")));
            selectAge.selectByValue ("3");
        }
        wait.until (ExpectedConditions.elementToBeClickable (expediaModel.close)).click ();
        wait.until (ExpectedConditions.elementToBeClickable (expediaModel.search)).click ();
        log.info (adult+" adult and "+child+" child is added");
    }

    /**
     * Setting departure and arrival dates
     * @param departureDate
     * @param arrivalDate
     */
    public void setDates(String departureDate, String arrivalDate){
        wait.until (ExpectedConditions.visibilityOf (expediaModel.departureDate)).click ();
        datePicker (departureDate);
        wait.until (ExpectedConditions.visibilityOf (expediaModel.arrivalDate)).click ();
        datePicker (arrivalDate);
        log.info ("Searching flights between "+arrivalDate+" and "+departureDate+" dates");

    }

    /**
     * Checking is there any result related to the selection
     */
    public void checkResult(){
        wait.until (ExpectedConditions.visibilityOf (expediaModel.result));
        log.info ("Flights were found");
    }

    /**
     * Setting passenger info with picker element. passengerCount is decision point
     * for how many times picker element should be clicked.
     * @param count
     * @param pickerElement
     */
    private void setPerson(int count, WebElement pickerElement){
        for(int i = 0; i<count; i++){
            pickerElement.click ();
        }
    }

    /**
     * Selecting specific text from the dropdown list
     * @param text
     */
    private void selectDropDownList(String text){
        for(WebElement listElement:
                expediaModel.dropDownResultList){
            if(listElement.getText ().contains (text)){
                wait.until (ExpectedConditions.visibilityOf (listElement)).click ();
                break;
            }
        }
    }

    /**
     * Selecting specific date for the date picker
     * @param date
     */
    private void datePicker(String date){
        String[] dateSplit = date.split ("/");
        Integer month = Integer.parseInt (dateSplit[0]);
        String monthConv = String.valueOf (month-1);
        WebElement selectedDate = driver.findElement (By.cssSelector ("[data-month='"+monthConv+"'][data-day='"+dateSplit[1]+"']"));
        selectedDate.click ();
    }

}
