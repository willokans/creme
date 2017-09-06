package Reg_Export;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Page.classes.DjangoPage;
import Page.classes.EMHeaderPage;
import Page.classes.ExportPage;
import Page.classes.FileSystemPage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 11/10/2016. Re-factored by alan.sheehy 15/12/2016
 * AT- 114
 * Test Automation for EMA- 1010
 */

public class MenuOptionVerification {

	ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    DjangoPage djangoPage;
    FileSystemPage fileSystemPage;
    ExportPage exportPage;
    EMHeaderPage emHeaderPage;
    String testCaseStatus = "PASSED";


    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    
    @BeforeSuite
    public void configLogging() throws IOException{
    	System.out.println("Creating "+CredentialsData.getExportUser()+" user profile on: "+CredentialsData.getApiURL());
    	System.out.println("Running Export tests on: "+CredentialsData.getBaseURL());
    }

    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\MenuOptionVerification.html");
        report = ExtentFactory.getInstance();

    }




    @BeforeMethod
    public void setup() throws Exception {

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logInPage = new LogInPage(driver);
        djangoPage = new DjangoPage(driver);
        fileSystemPage = new FileSystemPage(driver);
        exportPage = new ExportPage(driver);
        emHeaderPage = new EMHeaderPage(driver);

    }



    @Test
    public void createActiveUserProfile() throws Exception {
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
        logger = report.startTest("Create test table");
        
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
    public void verifyExportOptionsAreAvailable() throws InterruptedException {
        logger = report.startTest("Verify page contents options");

        exportPage.verifyColumnsDelimitedByIsDisplayed();
        logger.log(LogStatus.PASS, "'Columns are delimited by:' is displayed");
        exportPage.verifyCommaRadioButtonIsDisplayed();
        logger.log(LogStatus.PASS, "',(comma)' radio button is available");
        exportPage.verifySemiColonRadioButtonIsDisplayed();
        logger.log(LogStatus.PASS, "';(semicolon)' radio button is available");
        exportPage.verifyTabRadioButtonIsDisplayed();
        logger.log(LogStatus.PASS, "'(tab)' radio button is available");

        exportPage.verifyColumnsTerminatedByIsDisplayed();
        logger.log(LogStatus.PASS, "'Columns are terminated by:' is displayed");
        exportPage.verifyWindowRadioButtonIsDisplayed();
        logger.log(LogStatus.PASS, "'Windows (r\n)' radio button is available");
        exportPage.verifyUnixRadioButtonIsDisplayed();
        logger.log(LogStatus.PASS, "'Unix (\n)' radio button is available");

        exportPage.verifyColumnsQuotedByIsDisplayed();
        logger.log(LogStatus.PASS, "'Columns are quoted by:' is displayed");
        exportPage.verifyQuoteRadioButtonIsDisplayed();
        logger.log(LogStatus.PASS, "'Quote ('')' radio button is available");
        exportPage.verifySingleQuoteRadioButtonIsDisplayed();
        logger.log(LogStatus.PASS, "'Single quote (')' radio button is available");

        exportPage.verifyDownloadButtonIsDisplayed();
        logger.log(LogStatus.PASS, "'Download the zip file' button is displayed");
        exportPage.verifyGoBackButtonIsDisplayed();
        logger.log(LogStatus.PASS, "'Go Back' button displayed");

    }
    
    
    @Test (dependsOnMethods = {"verifyExportOptionsAreAvailable"}, enabled = true)
	public void verifyRadioButtonOptionsFunctionality() throws Exception {
		exportPage.verifyDeafultStatusesForOptions();
		logger.log(LogStatus.PASS, "Verified default radio button selected are correct (Comma, Windows, Quote)");
		
		// toggle options & verify they can be selected
		exportPage.clickSemiColonRadioButton();
		logger.log(LogStatus.INFO, "Selected ';(semicolon)' radio button");
		exportPage.clickUnixRadioButton();
		logger.log(LogStatus.INFO, "Selected 'Unix (\n)' radio button");
		exportPage.clickSingleQuoteRadioButton();
		logger.log(LogStatus.INFO, "Selected 'Single quote (')' radio button");
		exportPage.verifyToggledStatusesForOptions();
		logger.log(LogStatus.PASS, "Verified new selections have been made & only one selection per section is possible");
		
		exportPage.clickTabRadioButton();
		logger.log(LogStatus.INFO, "Selected '(tab)' radio button");
		exportPage.clickWindowRadioButton();
		logger.log(LogStatus.INFO, "Selected 'Windows (\r\n)' radio button");
		exportPage.clickQuoteRadioButton();
		logger.log(LogStatus.INFO, "Selected 'Quote ('')' radio button");
		exportPage.verifyUpdatedStatusesForOptions();
		logger.log(LogStatus.PASS, "Verified new selections have been made & only one selection per section is possible");
		
	
	}




    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Util.Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "MenuOptionVerification", imagePath);
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
        //driver.quit();
        logger.log(LogStatus.PASS, "Tear down Successful");
        report.endTest(logger);
        report.flush();
        System.out.println(getClass().getName()+" - "+testCaseStatus);
    }

}
