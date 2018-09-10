package com.framework.base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.framework.base.TestBase.*;

public class Browsers {

    public static WebDriver prepareDriver() {
        WebDriver driver;

        loadBrowsers();
        driver = getLocalDriver();
        driver.manage().window().maximize();

        return driver;
    }

    private static WebDriver getLocalDriver() {
        switch (BROWSER) {
            default:
            case "chrome":
                return new ChromeDriver();
        }
    }

    private static void loadBrowsers() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//automationFramework//setup//chromedriver.exe");
    }
}
