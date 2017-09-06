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

import Page.classes.DjangoPage;
import Page.classes.HomePage;
import Page.classes.LogInPage;
import Page.classes.ZohoEmailPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;
import jxl.read.biff.BiffException;

/**
 * Created by will.okanumeh on 6/8/2016. Re-factored by alan.sheehy 21/12/2016
 * Test Automation for EMA-637
 * Does not Required User Profile Creation completed first
 */

public class ForgotPasswordFunctionality {

    ExtentReports report;
    ExtentTest logger;
    DjangoPage djangoPage;
    LogInPage logInPage;
    HomePage homePage;
    ZohoEmailPage zohoPage;
    String testCaseStatus = "PASSED";

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();



    @BeforeClass
    public void beforeClassSetup() throws IOException, BiffException, ParseException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\ForgotPasswordFunctionality.html");
        report = ExtentFactory.getInstance();

    }




    @BeforeMethod
    public void setup() throws Exception {

        // Maximize the broswer's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        djangoPage = new DjangoPage(driver);
        logInPage = new LogInPage(driver);
        homePage = new HomePage(driver);
        zohoPage = new ZohoEmailPage(driver);

    }





    @Test
    public void createInactiveUserProfile() throws Exception {
    	logger = report.startTest("Create 'Account Access - Forgot Password' test user");
    	//djangoPage.createUserProfile(CredentialsData.getAccAssessUser());
    	djangoPage.createInActiveUserProfile(CredentialsData.getAccAccessUser());
	 	logger.log(LogStatus.PASS, "Test user created");
    }


    @Test (dependsOnMethods={"createInactiveUserProfile"}, enabled = true)
    public void verifyPageContentsAndStatus() throws Exception{
    	logger = report.startTest("Verify forgot password links, page contents & available actions");
    	// Go to URL
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");

        // Verify forgot password links
        logInPage.clickLostPasswordLink();
        logger.log(LogStatus.INFO, "Clicked on 'Lost Password' Link");
        logInPage.clickGoBackToLoginLink();
        logger.log(LogStatus.INFO, "Clicked on 'Go Back to Login' Link");

        logInPage.clickLostPasswordLink();
        logger.log(LogStatus.INFO, "Clicked again on 'Lost Password' Link");

        // Verify page options and status
        logInPage.forgetPasswordTitleIsDisplayed();
        logger.log(LogStatus.PASS, "Title is Displayed");
        logInPage.forgetPasswordEmailTextBoxIsBlankByDefault();
        logger.log(LogStatus.PASS, "Email TextBox is Blank by default");
        logInPage.forgetResetPasswordButtonIsDisplayed();
        logInPage.forgetResetPasswordButtonIsDisabled();
        logger.log(LogStatus.PASS, "Reset Password button is disabled but displayed");
  	
    }
    
    
    @Test (dependsOnMethods={"verifyPageContentsAndStatus"}, enabled = true)
    public void verifyForgotPasswordErrorMessages() throws Exception {
        logger = report.startTest("Verify email syntax error messages & valid entry message");

        // Enter an unregistered email address (and/or validated), reset and verify error message
        Constants.refreshPage();
        logInPage.fillInForgetPasswordEmailTextBox("blank@cremeglobal.com");
        logger.log(LogStatus.INFO, "Entered unregistered email address");
        logInPage.clickForgetResetPasswordButton();
        logger.log(LogStatus.INFO, "Clicked on 'Reset Password' button");
        logInPage.emailNotRecognizedErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'Email not recognised' error message");

        // Enter incorrect email syntax, reset
        Constants.refreshPage();
        logInPage.fillInForgetPasswordEmailTextBox("blank@.com");
        logger.log(LogStatus.INFO, "Entered incorrect Email Syntax");
        logInPage.clickForgetResetPasswordButton();
        logger.log(LogStatus.INFO, "Clicked on 'Reset Password' Button");
        logInPage.emailNotFormattedErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'Incorrect format' error message");
        
        // Enter a registered email & unauthenticated/inactive and attempt to reset the password
        Constants.refreshPage();
        logInPage.fillInForgetPasswordEmailTextBox(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.INFO, "Entered a registered & unauthenticated/inactive email address");
        logInPage.clickForgetResetPasswordButton();
        logger.log(LogStatus.INFO, "Clicked on 'Reset Password' Button");
        logInPage.verifyNonAuthenticatedForgotPwdMessage();
        logger.log(LogStatus.PASS, "Verified 'Activation link has been sent....' message is displayed");

        report.endTest(logger);

    }
    
    
    @Test (dependsOnMethods={"verifyForgotPasswordErrorMessages"}, enabled = true)
    public void verifyActivationEmailReceivedAndDelete() throws Exception{
    	logger = report.startTest("Successfully Change Password via Email Inbox");

        // Go to ZoHo Mail URL & Login
        driver.get(CredentialsData.getZohoURL());
        logger.log(LogStatus.INFO, "Navigated to Zoho Mail URL");
        zohoPage.enterTextToEmailTextBox(CredentialsData.getZohoEmailUser());
        zohoPage.enterTextToPasswordTextBox(CredentialsData.getZohoEmailPassword());
        logger.log(LogStatus.INFO, "Entered email and Password credentials");
        zohoPage.clickOnSignInButton();
        logger.log(LogStatus.INFO, "Clicked on SignIn Button");

        // Verify Confirm Email is displayed in Email Inbox
        zohoPage.verifyInboxPage();
        logger.log(LogStatus.INFO, "Inbox verified");
        zohoPage.verifyClickToConfirmEmailIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'Confirm Email' is displayed in Inbox");

        // Delete email & confirm
        zohoPage.clickOnDeleteEmailCheckBox();
        logger.log(LogStatus.INFO, "Clicked on checkbox to delete email");
        zohoPage.clickOnTrashEmailIconLink();
        logger.log(LogStatus.INFO, "Successfully Trashed Emails");
        zohoPage.verifyInboxEmptyIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully trashed all emails");

        // Log Out of Zoho Mail & confirm
        zohoPage.logOutOfZohoMail();
        logger.log(LogStatus.INFO, "Log Out initiated");
        zohoPage.verifySignInAgainIsDisplayed();
        logger.log(LogStatus.PASS, "Verified user is logged out");

        report.endTest(logger);
    	
    }
    
    
    @Test (dependsOnMethods={"verifyActivationEmailReceivedAndDelete"}, enabled = true)
    public void activateUserProfileAndVerifyForgotPasswordAccepted() throws Exception{
    	Constants.clearBrowserCache();
    	djangoPage.toggleUserActiveStatus(CredentialsData.getAccAccessUser());
    	logger.log(LogStatus.INFO, "User profile activated");
    	
    	Constants.clearBrowserCache();
    	driver.get(CredentialsData.getBaseURL());
        logInPage.clickLostPasswordLink();
        logger.log(LogStatus.INFO, "Navigated to Expert Models - Lost Password Page");
    	
    	// Enter a registered email & authenticated/active and attempt to reset the password
        logInPage.fillInForgetPasswordEmailTextBox(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.INFO, "Entered a registered & verified email address");
        logInPage.clickForgetResetPasswordButton();
        logger.log(LogStatus.INFO, "Clicked on 'Reset Password' Button");
        logInPage.notificationOfEmailSentMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'A link to reset your password...' message is displayed");
    	
    }


    @Test (dependsOnMethods={"activateUserProfileAndVerifyForgotPasswordAccepted"}, enabled = true)
    public void verifyPasswordResetEmailAndChangePassword() throws Exception {
        logger = report.startTest("Successfully Change Password via Email Inbox");

        driver.get(CredentialsData.getZohoURL());
        logger.log(LogStatus.INFO, "Navigated to Zoho Mail URL");

        // Login to Zoho
        zohoPage.enterTextToEmailTextBox(CredentialsData.getZohoEmailUser());
        zohoPage.enterTextToPasswordTextBox(CredentialsData.getZohoEmailPassword());
        logger.log(LogStatus.INFO, "Entered email and password credentials");
        zohoPage.clickOnSignInButton();
        logger.log(LogStatus.INFO, "Clicked on SignIn Button");

        // Open change password email & verify 
        zohoPage.verifyInboxPage();
        zohoPage.waitAndClickOnPasswordRestEmail();
        logger.log(LogStatus.INFO, "Clicked on 'Your password reset link....' email");
        zohoPage.verifyChangePasswordEmailIsDisplayed();
        logger.log(LogStatus.PASS, "Verified change password link is displayed");

        // Click on Link in Email, Change Password and Submit
        logInPage.clickChangePasswordLinkAndSubmit(CredentialsData.getNewPassword());
        logger.log(LogStatus.PASS, "Clicked on Change Password Link & reset password");


        // Delete email from Zoho account & logout
        zohoPage.clickOnDeleteEmailCheckBox();
        logger.log(LogStatus.INFO, "Clicked on checkbox to delete emails");
        zohoPage.clickOnTrashEmailIconLink();
        logger.log(LogStatus.INFO, "Trash emails");
        zohoPage.verifyInboxEmptyIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully trashed all email");
        zohoPage.logOutOfZohoMail();
        logger.log(LogStatus.INFO, "Clicked Log Out");

        zohoPage.verifySignInAgainIsDisplayed();
        logger.log(LogStatus.INFO, "Verified user is logged out");


        

    }


    @Test (dependsOnMethods={"verifyPasswordResetEmailAndChangePassword"}, enabled = true)
    public void verifyAccessCredentialsPostPasswordChange() throws Exception {
    	logger = report.startTest("Verify access credentials have changed");
    	logInPage.verifyLoginAccessDenied(CredentialsData.getAccAccessUser());
    	logger.log(LogStatus.PASS, "Access denied for old credentials");

        // Enter old credentials & verify access denied
    	Constants.refreshPage();
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getNewPassword());
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Entered old email & password credentials & clicked Login button");
        
        // Verify access is granted & user is on Home Page
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Access granted & user is on the home page");

        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Log out");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.INFO, "Successfully Logged Out");

        report.endTest(logger);
        

    }



    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "ForgotPasswordFunctionality", imagePath);
            testCaseStatus = "FAILED";
        }
        report.endTest(logger);
    }





    @AfterClass
    public void tearDown() throws Exception {
    	logger = report.startTest("Start tear down");
        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.PASS, "'Account Access - Forgot Password' user has been deleted");	      
        logInPage.verifyLoginAccessDeniedPostReset(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");

        Constants.clearBrowserCache();
        //driver.quit();
        logger.log(LogStatus.INFO, "Tear down Successful");
        report.endTest(logger);
        report.flush();
        System.out.println(getClass().getName()+" - "+testCaseStatus);
    }
}
