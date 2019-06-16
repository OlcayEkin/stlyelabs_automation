package com.stylelabs.hook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SeleniumHook {

    private static RemoteWebDriver driver;
    private static final Logger log    = LogManager.getLogger (SeleniumHook.class);

    private RemoteWebDriver  setUpBrowser (){
        String browser = "chrome";
        switch(browser){
            case "chrome":{
                driver = (RemoteWebDriver)setDriver (setChromeDriver ());
                break;
            }
            case "firefox":{
                driver = (RemoteWebDriver)setDriver (setFirefoxDriver ());
                break;
            }
            case("opera"):{
                driver = (RemoteWebDriver)setDriver (setOperaDriver ());
                break;
            }
            case "android":{
                driver = (RemoteWebDriver)setDriver (setMobileBrowser ("android"));
                break;
            }
            case "ios":{
                driver = (RemoteWebDriver)setDriver (setMobileBrowser ("ios"));
                break;
            }
            default:
                throw new IllegalStateException ("Unexpected value: " + browser);
        }
        return driver;
    }

    public WebDriver getDriver () {
        return driver;
    }

    private static WebDriver setDriver (RemoteWebDriver driver) {
        SeleniumHook.driver = driver;
        driver.manage().timeouts().implicitlyWait (20L, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20L, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;

    }

    public WebDriverWait getWait (){
        WebDriverWait wait = new WebDriverWait(driver,60);
        return wait;
    }

    private ChromeDriver setChromeDriver (){
        ChromeOptions co = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object> ();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable (LogType.BROWSER, Level.ALL);
        co.setCapability (CapabilityType.LOGGING_PREFS, logPrefs);
        co.setExperimentalOption("prefs", prefs);
        co.addArguments("--test-type");
        co.addArguments("--disable-popup-blocking");
        co.addArguments("--disable-save-password-bubble");
        co.addArguments("--ignore-certificate-errors");
        co.addArguments("--disable-translate");
        co.addArguments("--start-maximized");
        co.addArguments("--allow-silent-push");
        co.addArguments("--enable-automation");
        co.addArguments("--enable-javascript");
        System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver");
        return new ChromeDriver(co);
    }

    private FirefoxDriver setFirefoxDriver (){
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        profile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", "false");
        firefoxOptions.setProfile(profile);
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setCapability("marionette","true");
        System.setProperty("webdriver.gecko.driver","properties/driver/geckodriver");
        return new FirefoxDriver(firefoxOptions);
    }

    private ChromeDriver setOperaDriver (){
        System.setProperty("webdriver.chrome.driver", "properties/driver/operadriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
        options.addArguments("--single-process ");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-gpu");
        options.setBinary (new File ("properties/driver/operadriver"));
        return new ChromeDriver(options);
    }

    private ChromeDriver setMobileBrowser (String device){
        Map<String, String> mobileEmulation = new HashMap<String, String>();
        switch(device){
            case ("android"):{
                log.info("Device: "+device+" - Android");
                mobileEmulation.put("deviceName", "Nexus 5");
                break;
            }
            case ("ios"):{
                log.info("Device: "+device+" - ios");
                mobileEmulation.put("deviceName", "iPhone X");
                break;
            }
            default:
                throw new IllegalStateException ("Unexpected value: " + device);
        }
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver");
        return new ChromeDriver(chromeOptions);
    }

    //@Before
    public WebDriver setUpSettings(){
        return setUpBrowser();
    }

    //@After
    public void tearDownTest(){
        try{
            LogEntries logEntries = driver.manage ().logs ().get (LogType.BROWSER);
            for (LogEntry entry : logEntries) {
                log.info ("CONSOLE LOGS == > "+new Date (entry.getTimestamp ()) + " " + entry.getLevel () + " " + entry.getMessage ());
            }
        }catch (Exception e){
            e.printStackTrace ();
        }
        getDriver().quit();
    }

}
