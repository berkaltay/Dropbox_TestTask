import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import com.framework.base.TestBase;
import com.framework.utilities.Constants;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class DropBoxTest extends TestBase {

    private DbxRequestConfig config = new DbxRequestConfig("Optile");
    private DbxClientV2 client = new DbxClientV2(config, Constants.ACCESS_TOKEN);

    @Test(description = "LogIN & LogOUT", invocationCount = 1)
    public void shouldLogINandLogOUT() throws Exception {
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.signinButtonClick();

        LoginPage loginPage = new LoginPage();
        loginPage.clickSignInButtonOnLoginPage(Constants.USERNAME, Constants.PASSWORD);
        assertThat("After a user logged in, user should see the homepage", loginPage.isLoggedIn());

        HomePage homePage = new HomePage();
        homePage.clickOnAccountPhoto();
        homePage.clickOnSignOut();
        assertThat("After a user logged out, user should see the logoutpage", not(loginPage.isLoggedIn()));
    }

    @Test(description = "Delete & Upload File", invocationCount = 1)
    public void shouldUploadFileScenario() throws Exception {
        String file_name = "upload_test_file.txt";

        DropboxUtils dropboxUtils = new DropboxUtils(client);
        dropboxUtils.deleteFileandFolder(file_name);
        dropboxUtils.uploadFile(file_name);

        WelcomePage welcomePage = new WelcomePage();
        welcomePage.signinButtonClick();

        LoginPage loginPage = new LoginPage();
        loginPage.clickSignInButtonOnLoginPage(Constants.USERNAME, Constants.PASSWORD);

        HomePage homePage = new HomePage();
        assertThat("After user uploaded a file, user should see the file on the list", homePage.isFileorFolderExist(file_name));

        dropboxUtils.deleteFileandFolder(file_name);
    }

    @Test(description = "Delete Folder & Create New Folder", invocationCount = 1)
    public void shouldCreateFolderScenario() throws Exception {
        String folder_name = "Optile";

        DropboxUtils dropboxUtils = new DropboxUtils(client);
        dropboxUtils.deleteFileandFolder(folder_name);

        WelcomePage welcomePage = new WelcomePage();
        welcomePage.signinButtonClick();

        LoginPage loginPage = new LoginPage();
        loginPage.clickSignInButtonOnLoginPage(Constants.USERNAME, Constants.PASSWORD);

        HomePage homePage = new HomePage();
        homePage.createNewFolder(folder_name);
        assertThat("After a user created a folder, user should see the folder on the list", homePage.isFileorFolderExist(folder_name));

        dropboxUtils.deleteFileandFolder(folder_name);
    }

    @Test(description = "Search for a File", invocationCount = 1)
    public void shouldSearchForaFile() throws Exception {
        String file_name = "search_test_file.txt";

        DropboxUtils dropboxUtils = new DropboxUtils(client);
        dropboxUtils.uploadFile(file_name);

        WelcomePage welcomePage = new WelcomePage();
        welcomePage.signinButtonClick();

        LoginPage loginPage = new LoginPage();
        loginPage.clickSignInButtonOnLoginPage(Constants.USERNAME, Constants.PASSWORD);

        HomePage homePage = new HomePage();
        homePage.searchFile(file_name);
        assertThat("After a user searched for a file, user should see the file on the search results", homePage.isFileorFolderExist(file_name));

        dropboxUtils.deleteFileandFolder(file_name);
    }

    @Test(description = "Renaming of a File", invocationCount = 1)
    public void shouldRenameFile() throws Exception {
        String fileName = "rename_test_file.txt";
        String newFileName = "optile_test_file.txt";

        DropboxUtils dropboxUtils = new DropboxUtils(client);
        dropboxUtils.deleteFileandFolder(newFileName);
        dropboxUtils.uploadFile(fileName);

        WelcomePage welcomePage = new WelcomePage();
        welcomePage.signinButtonClick();

        LoginPage loginPage = new LoginPage();
        loginPage.clickSignInButtonOnLoginPage(Constants.USERNAME, Constants.PASSWORD);

        HomePage homePage = new HomePage();
        homePage.renameFile(fileName, newFileName);
        assertThat("After a user renamed a file, user should see the file on the search results", homePage.isFileorFolderExist(newFileName));

        dropboxUtils.deleteFileandFolder(newFileName);
    }
}
