package com.stylelabs.pages;

import com.stylelabs.models.SearchModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {
    private static final Logger log   = LogManager.getLogger (SearchPage.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private SearchModel searchModel = new SearchModel ();

    public SearchPage (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait=wait;
        PageFactory.initElements (driver,searchModel);
    }

    /**
     * Navigating to the url
     * @param url
     */
    public void navigateTo(String url){
        driver.navigate ().to (url);
        log.info ("Navigating to the "+url);
    }

    /**
     * Searching any country for the Google Search
     * @param country
     */
    public void searchCountry(String country){
        wait.until (ExpectedConditions.visibilityOf (searchModel.searchInput)).sendKeys (country);
        wait.until (ExpectedConditions.visibilityOf (searchModel.searchInput)).sendKeys (Keys.ENTER);
        log.info ("Country searching => "+country);
    }

    /**
     * Waiting the right panel to be displayed
     */
    public void waitResult(){ wait.until (ExpectedConditions.visibilityOf (searchModel.searchResult)); }

    /**
     * Checking result is correct from the right side panel
     * @param country
     */
    public void isResultCorrect(String country){
        Assert.assertTrue ("Country is not found after the search process", searchModel.searchResult.getText ().contains (country));
        log.info ("Country results are correct");
    }
}
