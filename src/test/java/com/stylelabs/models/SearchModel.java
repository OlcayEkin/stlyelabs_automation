package com.stylelabs.models;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchModel {

    @FindBy(how = How.CSS , using = "[type='text']")
    public WebElement searchInput;

    @FindBy(how = How.ID, using = "rhs_block")
    public WebElement searchResult;

}
