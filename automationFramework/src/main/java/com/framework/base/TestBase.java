package com.framework.base;

import com.framework.extentFactory.ReportFactory;
import com.framework.listener.Listener;
import com.framework.utilities.DriverManager;
import com.framework.utilities.ReTryTestCase;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import static com.framework.base.Browsers.prepareDriver;
import static com.framework.extentFactory.ReportFactory.createReportFile;
import static java.util.concurrent.TimeUnit.SECONDS;


@Listeners({Listener.class})
public class TestBase {

    public static Properties CONFIG = null;
    public static String BROWSER = null;
    public static String BASEURL = null;
    public static int RETRY;
    public String testNameFromXml = null;

    public void initializeConfig(String reTry, String browser) throws Throwable {
        CONFIG = new Properties();
        FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//automationFramework//setup//config.properties");
        CONFIG.load(ip);
        BROWSER = browser;
        RETRY = Integer.parseInt(reTry);
        BASEURL = CONFIG.getProperty("baseUrl");
    }

    @Parameters(value = {"reTry", "browser"})
    @BeforeSuite
    public void beforeSuite(ITestContext context, String reTry, String browser) throws Throwable {

        initializeConfig(reTry, browser);
        createReportFile();

        for (ITestNGMethod method : context.getSuite().getAllMethods()) {
            method.setRetryAnalyzer(new ReTryTestCase());
        }
    }

    @BeforeClass
    @Parameters(value = {"testCategory"})
    public void beforeClass(String testCategory) {
        testNameFromXml = this.getClass().getName();
        ReportFactory.createTest(this.getClass().getName(), testCategory);
    }

    @BeforeMethod
    public void openBrowser(Method method) {
        DriverManager.setDriver(prepareDriver());

        getDriver().navigate().to(BASEURL);
        getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(CONFIG.getProperty("default_pageLoadWait")), SECONDS);

        ReportFactory.createChildTest(testNameFromXml, method.getName());
    }

    @AfterMethod
    public void closeBrowser(ITestResult result) {
        getDriver().quit();
        IRetryAnalyzer retry = result.getMethod().getRetryAnalyzer();
        if (retry == null) {
            return;
        }
        result.getTestContext().getSkippedTests().removeResult(result.getMethod());
    }

    public synchronized static WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}