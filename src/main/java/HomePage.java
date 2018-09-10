import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.base.BasePageMethods;
import com.framework.utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static com.framework.base.TestBase.getDriver;
import static com.framework.extentFactory.ReportFactory.getChildTest;
import static com.framework.utilities.ScreenshotTaker.takeBase64Screenshot;


public class HomePage extends BasePageMethods {

    public HomePage() {
        getDriver().navigate().to("https://www.dropbox.com/home");
    }

    public boolean isFileorFolderExist(String name) throws IOException {

        List<WebElement> al = visibilityOfAllWait(By.xpath("//tr[contains(@class,'brws-file-row')]"), 60);
        for (WebElement size1 : al) {
            String str = size1.getText();

            if (str.contains(name)) {
                return true;

            }

        }
        return false;
    }

    public void createNewFolder(String folder_name) throws IOException {
        clickWebElement(By.xpath("//button[contains(@class,'action-new-folder')]"));
        sendKeysToElementWithoutCleanInside(By.xpath("//tr[contains(@class,'brws-new-folder-row')]//input"), folder_name);
        pressEnter();
        waitForElementToHide(By.xpath("//tr[contains(@class,'brws-new-folder-row')]//input"), 15);
        waitForElementToGetAttribute(60, By.id("notify"), "style", "display: inline-block;");
        waitForElementToGetAttribute(30, By.id("notify"), "style", "display: none;");
        getChildTest().pass("Folder creation is successful, check the screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot("HomePage", "try-out")).build());
    }

    public void clickOnAccountPhoto() throws IOException {
        clickWebElement(By.xpath("//div/button[@role='button']//img[@alt='Account photo']"));
        getChildTest().pass("Clicked on Account Photo, check the screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot("HomePage", "try-out")).build());
    }

    public void clickOnSignOut() throws IOException {
        clickWebElement(By.xpath("//a[contains(text(),'Sign out')]"));
        getChildTest().pass("Clicked on Sign Out, check the screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot("HomePage", "try-out")).build());
    }


    public void searchFile(String fileName) throws Exception {
        sendKeysToElementWithoutCleanInside(By.xpath("//input[@class='search-bar__text-input']"), fileName);
        pressEnter();
        isFileorFolderExist(fileName);
        getChildTest().pass("File check assertion successfull, check the screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot("HomePage", "try-out")).build());
    }

    public void renameFile(String fileName, String newFileName) throws Exception {
        mouseHover(By.xpath("//tr[@data-filename='" + fileName + "']//span[@class='mc-checkbox-border']"));
        clickWebElement(By.xpath("//tr[@data-filename='" + fileName + "']//span[@class='mc-checkbox-border']"));
        clickWebElement(By.xpath("//button[contains(@class,'action-rename')]"));
        waitUntilPresenceOfElementByLocator(By.xpath("//div[contains(@class,'brws-file-name-cell-text-rename')]"));
        sendKeysToElement(By.xpath("//div[contains(@class,'brws-file-name-cell-text-rename')]//form//input"), newFileName);
        pressEnter();

        waitForElementToGetAttribute(30, By.id("notify"), "style", "display: inline-block;");
        waitUntilPresenceOfElementByLocator(By.xpath("//tr[@data-filename='" + newFileName + "']"));
        getChildTest().pass("Re-naming of the file is successfull, check the screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot("HomePage", "try-out")).build());
    }
}






