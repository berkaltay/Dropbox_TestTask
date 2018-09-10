import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.base.BasePageMethods;
import org.openqa.selenium.By;

import static com.framework.extentFactory.ReportFactory.getChildTest;
import static com.framework.utilities.ScreenshotTaker.takeBase64Screenshot;


public class LoginPage extends BasePageMethods {

    public void clickSignInButtonOnLoginPage(String username, String password) throws Exception {
        sendKeysToElement(By.xpath("//input[@name='login_email']"), username);
        sendKeysToElement(By.xpath("//input[@name='login_password']"), password);
        clickWebElement(By.xpath("//button[@type='submit']/div[text()='Sign in']"));
        waitUntilVisibleByLocator(By.xpath("//h1[@class='page-header__heading']"));
        getChildTest().pass("Log in is successful, check the screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot("HomePage", "try-out")).build());
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.xpath("//h1[@class='page-header__heading']"), 15);
    }
}
