package Reg_AccountAccess;

import java.io.IOException;
import java.text.ParseException;
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

import Page.classes.AccountPage;
import Page.classes.DjangoPage;
import Page.classes.HomePage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;
import jxl.read.biff.BiffException;

/**
 * Created by will.okanumeh on 6/8/2016. Re-factored by alan.sheehy 22/12/2016
 * AT-20
 * Test Automation for EMA-632
 * DEPENDANT ON CREATE USER PROFILE CLASS
 */
public class UserProfileAccountPage {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    HomePage homePage;
    AccountPage accountPage;
    DjangoPage djangoPage;
    String testCaseStatus = "PASSED";
    
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();



    @BeforeClass
    public void beforeClassSetup() throws IOException, BiffException, ParseException {
        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\UserProfileAccountPage.html");
        report = ExtentFactory.getInstance();


    }





    @BeforeMethod
    public void setup() throws Exception {
        // Maximize the broswer's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logInPage = new LogInPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        djangoPage = new DjangoPage(driver);

    }




    @Test
    public void createActiveUserProfile() throws Exception {
    	logger = report.startTest("Create 'Account Access' test user");
	 	djangoPage.createUserProfile(CredentialsData.getAccAccessUser());
	 	logger.log(LogStatus.PASS, "Test user created");
    }


    @Test (dependsOnMethods = {"createActiveUserProfile"}, enabled = true)
    public void verifyAccountDetailsPageContents() throws Exception {
        logger = report.startTest("Login & verify 'Account Details' page contents'");

        // Go to URL & Login
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");

        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verified the user is on the Home Page");

        // Navigate to the Account Page & Verify Contents
        homePage.navigateToAccountPage();
        logger.log(LogStatus.PASS, "Navigated to Account Page");
        
        accountPage.verifyFirstNameTextIsDisplayed();
        logger.log(LogStatus.PASS, "First name is displayed");

        accountPage.verifyLastNameTextIsDisplayed();
        logger.log(LogStatus.PASS, "Last name is displayed");

        accountPage.verifyEmailAddIsDisplayedNotEditable();
        logger.log(LogStatus.PASS, "Email is displayed and not editable");

        accountPage.saveButtonIsDisplayed();
        logger.log(LogStatus.PASS, "Save button is displayed");

        accountPage.changePasswordLinkIsDisplayed();
        logger.log(LogStatus.PASS, "Change Password tab is displayed");
        accountPage.clickChangePasswordLink();
        accountPage.verifyUserIsOnChangePasswordTab();
        logger.log(LogStatus.PASS, "Verified user is on the change password tab");
              
        //accountPage.licenseTabLinkIsDisplayed();
        //logger.log(LogStatus.PASS, "License tab is displayed");
        //accountPage.navigateToLicensePage();
        //logger.log(LogStatus.PASS, "Verified user is on the license page");
        
        accountPage.detailLinkIsDisplayed();
        logger.log(LogStatus.PASS, "Details tab is displayed");
        accountPage.clickOnDetailsTabLink();
        logger.log(LogStatus.INFO, "Clicked on Details tab");
        accountPage.verifyUserIsOnDetailsTab();
        logger.log(LogStatus.PASS, "Verified user is on the details tab");

    }       
        
    
    
    @Test (dependsOnMethods = {"verifyAccountDetailsPageContents"}, enabled = true)
    public void verifyPageValidationErrorsAndSaveChangesAction() throws Exception{
    	logger = report.startTest("Edit 'Account Details' page & verify actions & error messages");
        
        // Clear the 'First name' field and attempt to 'Save Changes'
        accountPage.clearFirstNameAndAttemptToSaveError();
        accountPage.verifyDisplayOfErrorMessageAfterClearingFirstNameAndAttemptingToSave();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "First Name cleared and Error Message displayed");

        // Clear the 'Last' field and attempt to 'Save Changes'
        accountPage.clearLastNameAndAttemptToSaveError();
        accountPage.clickSaveChangeButton();
        accountPage.verifyDisplayOfErrorMessageAfterClearingLastNameAndAttemptingToSave();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Last Name cleared and Error Message displayed");

        // Clear both 'First Name' & 'Last' fields and attempt to 'Save Changes'
        accountPage.clearFirstAndLastNameAndAttemptToSaveError();
        accountPage.clickSaveChangeButton();
        accountPage.verifyDisplayOfErrorMessageAfterClearingFirstAndLastNameAndAttemptingToSave();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "First & Last Name cleared and Error Message displayed");

        // Enter the following input values for both First Name and Surname fields and attempt to 'Save Changes'
        // - Characters only (Lower case entry & single letter)
        accountPage.enterTextToFirstNameTextBox("test");
        accountPage.enterTextToLastNameTextBox("t");
        accountPage.clickSaveChangeButton();
        accountPage.verifySaveMessageIsDisplayed();  
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Lower case entries - saved successfully");

        // - Characters types
        accountPage.enterTextToFirstNameTextBox("£$%^&*");
        accountPage.enterTextToLastNameTextBox("?~@@~:><¬!+_)*");
        accountPage.clickSaveChangeButton();
        accountPage.verifySaveMessageIsDisplayed();  
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Character type entries - saved successfully");

        // - Upper case entry
        accountPage.enterTextToFirstNameTextBox("TEST");
        accountPage.enterTextToLastNameTextBox("TEST");
        accountPage.clickSaveChangeButton();
        accountPage.verifySaveMessageIsDisplayed();   
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Upper case entries - saved successfully");

        // - Numeric values only
        accountPage.enterTextToFirstNameTextBox("3");
        accountPage.enterTextToLastNameTextBox("4.56");
        accountPage.clickSaveChangeButton();
        accountPage.verifySaveMessageIsDisplayed();     
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Numeric value entries - saved successfully");

        // - Mixture of the above
        accountPage.enterTextToFirstNameTextBox("Test#101");
        accountPage.enterTextToLastNameTextBox("teSt$202");
        accountPage.clickSaveChangeButton();
        accountPage.verifySaveMessageIsDisplayed();    
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Upper, lower, character & numeric mixture entry - saved successfully");

        // - Default first name and last name
        accountPage.enterTextToFirstNameTextBox("Automation");
        accountPage.enterTextToLastNameTextBox("Test");
        accountPage.clickSaveChangeButton();
        accountPage.verifySaveMessageIsDisplayed();      
        logger.log(LogStatus.PASS, "Original Name - saved successfully");

        report.endTest(logger);
    }


    
    @Test(dependsOnMethods = {"verifyPageValidationErrorsAndSaveChangesAction"}, enabled = true)
    public void shouldLogOut() throws Exception {
        logger = report.startTest("Successfully SignOut");

        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Clicked Logout");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Verified user has logged out");

        report.endTest(logger);
    }




    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "UserProfileAccountPage", imagePath);
            testCaseStatus = "FAILED";
        }
        report.endTest(logger);
    }




    @AfterClass
    public void tearDown() throws Exception {
    	logger = report.startTest("Start tear down");
        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.PASS, "'Account Access' user has been deleted");	         
        logInPage.verifyLoginAccessDenied(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");

        Constants.clearBrowserCache();
        //driver.quit();
        logger.log(LogStatus.INFO, "Tear down Successful");
        report.endTest(logger);
        report.flush();
        System.out.println(getClass().getName()+" - "+testCaseStatus);

    }
}
