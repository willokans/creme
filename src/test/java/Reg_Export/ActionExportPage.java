package Reg_Export;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Page.classes.DjangoPage;
import Page.classes.EMHeaderPage;
import Page.classes.ExportPage;
import Page.classes.FilePage;
import Page.classes.FileSystemPage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 11/10/2016. Re-factored by alan.sheehy 12/01/2017
 * AT- 85
 * Test Automation for EMA- 1077
 */

public class ActionExportPage {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    DjangoPage djangoPage;
    FilePage filePage;
    FileSystemPage fileSystemPage;
    ExportPage exportPage;
    EMHeaderPage emHeaderPage;
    String testCaseStatus = "PASSED";


    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {
        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\ActionExportPage.html");
        report = ExtentFactory.getInstance();
    
    }




    @BeforeMethod
    public void setup() throws Exception {

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logInPage = new LogInPage(driver);
        djangoPage = new DjangoPage(driver);
        filePage = new FilePage(driver);
        fileSystemPage = new FileSystemPage(driver);
        exportPage = new ExportPage(driver);
        emHeaderPage = new EMHeaderPage(driver);

    }



    @Test
    public void createActiveUserProfile() throws IOException, Exception {
        logger = report.startTest("Create Export test user");
        djangoPage.createUserProfile(CredentialsData.getExportUser());
	 	logger.log(LogStatus.PASS, "Test user created");      
    }



    @Test (dependsOnMethods={"createActiveUserProfile"}, enabled = true)
    public void LogIn () throws Exception {
        logger = report.startTest("Verify login");

        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");
        logInPage.fillInEmailTextBox(CredentialsData.getExportUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        emHeaderPage.verifyHeaderContentsAreDisplayed();
        logger.log(LogStatus.PASS, "Verified user has logged in");

    }


    @Test (dependsOnMethods={"LogIn"}, enabled = true)
    public void createTestItem() throws Exception {
        logger = report.startTest("Create a test table");

        // Create New Test Table
        fileSystemPage.createQACategoryTable();
        fileSystemPage.enterItemNameIntoNewItemTextBox(Constants.tableName);
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.INFO, "Created a test table");
        fileSystemPage.verifyQATestTableIsDisplayed();
        logger.log(LogStatus.PASS, "Verified the table is available to the user in the file system");

    }


    @Test (dependsOnMethods={"createTestItem"}, enabled = true)
    public void startDownloadAndVerifyRedirect() throws Exception {
        logger = report.startTest("Verify 'Download' redirects user to the Export page");

        // Navigate to Export Page via Subject Table Hamburger menu
        fileSystemPage.clickOnQATableHamburgerMenu();
        logger.log(LogStatus.INFO, "Clicked on table hamburger menu");
        fileSystemPage.clickOnDownloadFromHamburgerMenu();
        logger.log(LogStatus.INFO, "Clicked on 'Download' from the drop down menu");
        exportPage.verifyExportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified the user has been redirected to the Export page");

    }


    @Test (dependsOnMethods = {"startDownloadAndVerifyRedirect"}, enabled = true)
    public void verifyAvailableActionsAndClickGoBack() throws InterruptedException {
        logger = report.startTest("Verify available actions & click 'Go Back'");
        
        exportPage.verifyActionButtonsAreClickable();
        logger.log(LogStatus.PASS, "'Download the zip file' & 'Go Back' buttons are clickable");
 
        // Click 'Go Back' & verify the user is redirected to My Data Page
        exportPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked on 'Go Back' Button");
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.PASS, "Verified the user has been redirected to the My Data age");

    }


    @Test (dependsOnMethods = {"verifyAvailableActionsAndClickGoBack"}, enabled = true)
    public void verifyDownloadTheZipFileFunctionality() throws Exception {
        logger = report.startTest("Validate 'Download the zip file' Button Functionality & No page redirect");
        
        // Navigate to Export Page via Subject Table Hamburger menu
        fileSystemPage.clickOnQATableHamburgerMenu();
        logger.log(LogStatus.INFO, "Clicked on table hamburger menu");    
        fileSystemPage.clickOnDownloadFromHamburgerMenu();
        logger.log(LogStatus.INFO, "Clicked on 'Download' from the drop down menu");
        exportPage.verifyExportLabelIsDisplayed();
        logger.log(LogStatus.INFO, "Verified the user has been redirected to the Export age");

        // Click 'Download the zip file' & verify system download launched plus user remains on the export page
        exportPage.clickDownloadTheZipFileButton();
        logger.log(LogStatus.INFO, "Clicked on the 'Download The Zip File' button");
        exportPage.DownloadTheZipFileDialogBox();
        logger.log(LogStatus.PASS, "Verified OS system download started ");
        exportPage.verifyExportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified user has been redirected to My Data page");

    }


    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Util.Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "ActionExportPage", imagePath);
            testCaseStatus = "FAILED";
        }
        report.endTest(logger);
    }




    @AfterClass
    public void tearDown() throws Exception {
        logger = report.startTest("Start tear down");
        emHeaderPage.logout();
	 	logger.log(LogStatus.INFO, "Clicked on 'Logout' button");
	 	logInPage.verifyLogInPage();
	 	logger.log(LogStatus.PASS, "Verified user is logged out and redirected to the 'Login' page");
        Constants.clearBrowserCache();       
        
        djangoPage.deleteUserProfile(CredentialsData.getExportUser());
        logger.log(LogStatus.PASS, "Export user has been deleted");	
        logInPage.verifyLoginAccessDenied(CredentialsData.getExportUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");

        Constants.clearBrowserCache();
        // driver.quit();
        logger.log(LogStatus.PASS, "Tear down Successful");
        report.endTest(logger);
        report.flush();
        System.out.println(getClass().getName()+" - "+testCaseStatus);
    }
}
