package com.stylelabs.hook;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(plugin = { "pretty",

        "html:target/site/cucumber-html-report",

        "json:target/site/cucumber-json-report.json"

}, dryRun = false, monochrome = true,
        features = "src/test/java/resources ", glue = { "" })
@RunWith(Cucumber.class)
public class CucumberRunner {
}
