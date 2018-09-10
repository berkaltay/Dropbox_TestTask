package com.framework.utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThread.set(driver);
    }
}
