import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.base.BasePageMethods;
import org.openqa.selenium.By;

import static com.framework.extentFactory.ReportFactory.getChildTest;
import static com.framework.utilities.ScreenshotTaker.takeBase64Screenshot;


public class WelcomePage extends BasePageMethods {

    public void signinButtonClick() throws Exception {
        if (isElementPresent(By.xpath("//*[@id='sign-up-in']"), 5))
            clickWebElement(By.xpath("//*[@id='sign-up-in']"));
        else {
            clickWebElement(By.xpath("//a[@href='/login']"));
        }
        getChildTest().pass("Clicked on sign in on the top, check the screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot("HomePage", "try-out")).build());
    }
}
