package com.framework.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.framework.base.TestBase.RETRY;
import static com.framework.extentFactory.ReportFactory.setSuccessToChild;

public class ReTryTestCase implements IRetryAnalyzer {

    private int count = 0;

    public static int globalcounter = 0;

    private static int maxTry = RETRY;

    public synchronized boolean retry(ITestResult result) {
        if ((!result.isSuccess()) && (count < maxTry)) {
            count++;
            globalcounter = count;
            result.setStatus(ITestResult.FAILURE);
            setSuccessToChild(false);
            return true;
        } else {
            result.setStatus(ITestResult.SUCCESS);
            return false;
        }
    }
}