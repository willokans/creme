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
import Page.classes.EMHeaderPage;
import Page.classes.LogInPage;
import Page.classes.ZohoEmailPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;
import jxl.read.biff.BiffException;

/**
 * Created by laura.val 24/01/2017
 * AT-19
 * Test Automation for EMA-635
 */
public class PasswordResetFuncViaForgotPasswordEmail {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    AccountPage accountPage;
    DjangoPage djangoPage;
    ZohoEmailPage zohoPage;
    EMHeaderPage emHeaderPage;
    String testCaseStatus = "PASSED";

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @BeforeClass
    public void beforeClassSetup() throws IOException, BiffException, ParseException {
        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\PasswordResetFuncViaForgotPasswordEmail.html");
        report = ExtentFactory.getInstance();

    }


    @BeforeMethod
    public void setup() throws Exception {
        // Maximize the broswer's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logInPage = new LogInPage(driver);
        accountPage = new AccountPage(driver);
        djangoPage = new DjangoPage(driver);
        zohoPage = new ZohoEmailPage(driver);
        emHeaderPage = new EMHeaderPage(driver);
    }
    
    
    @Test
	public void createActiveUserProfile() throws Exception {
		logger = report.startTest("Create 'Account Access' test user");
	    djangoPage.createUserProfile(CredentialsData.getAccAccessUser());
	    logger.log(LogStatus.PASS, "Test user created");
	}

	@Test (dependsOnMethods={"createActiveUserProfile"}, enabled = true)
	public void clickOnLostPasswordlink() throws Exception{
		logger = report.startTest("Click on Lost password link");
    	// Go to URL
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigate to Expert Models URL");
        
        // Verify forgot password links
        logInPage.clickLostPasswordLink();
        logger.log(LogStatus.INFO, "Clicked on 'Lost Password' Link");
	}
	
	@Test (dependsOnMethods={"clickOnLostPasswordlink"}, enabled = true)
	public void resetPassword() throws Exception{
		logger = report.startTest("User is redirected to Forgot Password page");
		// Verify user is redirected to Forgot Password page
		logInPage.forgetPasswordTitleIsDisplayed();
        logger.log(LogStatus.PASS, "Title is Displayed");
        // Populate email Text box with accAccessUser email
        logInPage.fillInForgetPasswordEmailTextBox(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.PASS, "Email text box populated with AccAccessUser email");
        //Click on Reset Password button 
        logInPage.clickForgetResetPasswordButton();
        logger.log(LogStatus.INFO, "Clicked on 'Reset Password' button");
        // Verified user is presented with 'Thanks you! We have just emailed you a link to reset your password'
        logInPage.notificationOfEmailSentMessageIsDisplayed();
        logger.log(LogStatus.PASS, "User is presented with reset your password message");
	}
	
	@Test (dependsOnMethods={"resetPassword"}, enabled = true)
	public void forgotPasswordEmail() throws Exception{
    	logger = report.startTest("Successfully Change Password via Email Inbox");

        // Go to ZoHo Mail URL & Login
        driver.get(CredentialsData.getZohoURL());
        logger.log(LogStatus.INFO, "Navigated to Zoho Mail URL");
        zohoPage.enterTextToEmailTextBox(CredentialsData.getZohoEmailUser());
        zohoPage.enterTextToPasswordTextBox(CredentialsData.getZohoEmailPassword());
        logger.log(LogStatus.INFO, "Entered email and Password credentials");
        zohoPage.clickOnSignInButton();
        logger.log(LogStatus.INFO, "Clicked on SignIn Button");
        
        // Open change password email & verify 
        zohoPage.verifyInboxPage();
        zohoPage.waitAndClickOnPasswordRestEmail();
        logger.log(LogStatus.INFO, "Clicked on 'Your password reset link....' email");
        zohoPage.verifyChangePasswordEmailIsDisplayed();
        logger.log(LogStatus.PASS, "Verified change password link is displayed");
 	}
	
	@Test (dependsOnMethods={"forgotPasswordEmail"}, enabled = true)    
	public void verifyChangePasswordPage() throws Exception {
		logger = report.startTest("Successfully Redirected to Change password page");
		// Verify Create Password page contents
		 logger.log(LogStatus.INFO, "Verifying page contents");
		 logInPage.verifyChangePasswordPageContents();
		 logger.log(LogStatus.PASS, "All contents verified");	
	}
	
	@Test (dependsOnMethods={"verifyChangePasswordPage"}, enabled = true)  
	public void verifyGoBackToLoginLinkFunctionality() throws Exception{
		logger = report.startTest("Back to login link functionality");
		//Click on Go Back to login link
		logInPage.clickGoBackToLoginLink();	
		logger.log(LogStatus.INFO, "Click on Go back to Login Page");
		//Verified user is redirected to Login page
		logInPage.emailTextBoxIsDisplayed();
		logInPage.emailTextBoxIsBlankByDefault();
		logInPage.emLogoIsDisplayed();
		logInPage.passwordTextBoxIsDisplayed();
		logInPage.passwordTextBoxIsBlankByDefault();
		logInPage.LoginButtonIsDisplayed();		
		logger.log(LogStatus.PASS, "Verified user is redirected to Login Page");
	}
	
	@Test (dependsOnMethods={"verifyGoBackToLoginLinkFunctionality"}, enabled = true)
	public void backToInbox() throws Exception{
    	logger = report.startTest("Navigate back to Inbox");

        // Go to ZoHo Mail URL & Login
        driver.get(CredentialsData.getZohoURL());
        logger.log(LogStatus.INFO, "Navigated to Zoho Mail URL");
             
        // Open change password email & verify 
        zohoPage.verifyInboxPage();
        zohoPage.waitAndClickOnPasswordRestEmail();
        logger.log(LogStatus.INFO, "Clicked on 'Your password reset link....' email");
        zohoPage.verifyChangePasswordEmailIsDisplayed();
        logger.log(LogStatus.PASS, "Verified change password link is displayed");
 	}
			
	@Test (dependsOnMethods={"backToInbox"}, enabled = true)
	public void redirectToChangePasswordPage() throws Exception {
		logger = report.startTest("Successfully Redirected to Change password page");
		// Verify Create Password page contents
		 logger.log(LogStatus.INFO, "Verifying page contents");
		 logInPage.verifyChangePasswordPageContents();
		 logger.log(LogStatus.PASS, "All contents verified");	
	}
	
	@Test (dependsOnMethods={"redirectToChangePasswordPage"}, enabled = true)
	public void resetPasswordFieldBlank() throws Exception {
		logger = report.startTest("Blank password and password again populated");
		logInPage.clickResetPasswordTextBox();
		logInPage.fillInResetPasswordAgainTextBox("Creme#12345..");
		// Reset button is disabled
		logInPage.verifyResetPasswordButtonIsDisabled();
		logger.log(LogStatus.PASS, "Password button is disabled");
		logger.log(LogStatus.INFO, "Password blank and Password again populated");
		//Verify error message is displayed
		logInPage.nullPasswordError();
		logger.log(LogStatus.PASS, "Error Message displayed");
	}
	
	@Test (dependsOnMethods={"resetPasswordFieldBlank"}, enabled = true)
	public void resetPasswordAgainFieldBlank() throws Exception {
		logger = report.startTest("password populated and password again blank");
		logInPage.clickResetPasswordAgainTextBox();
		logInPage.fillInResetPasswordTextBox("Creme#12345..");
		logger.log(LogStatus.INFO, "Password blank and Password again populated");
		// Reset button is disabled
		logInPage.verifyResetPasswordButtonIsDisabled();
		logger.log(LogStatus.PASS, "Password button is disabled");
		//Verify error message is displayed
		logInPage.nullPasswordAgainError();
		logger.log(LogStatus.PASS, "Error Message displayed");
	}
		
	@Test (dependsOnMethods={"resetPasswordAgainFieldBlank"}, enabled = true)
	public void resetPasswordandPasswordAgainFieldsBlank() throws Exception {
		logger = report.startTest("Both password fields blank");
		logInPage.clickResetPasswordAgainTextBox();
		logInPage.clickResetPasswordTextBox();
		logInPage.clickResetPasswordAgainTextBox();
		// Reset button is disabled
		logInPage.verifyResetPasswordButtonIsDisabled();
		logger.log(LogStatus.PASS, "Password button is disabled");
		//Verify error messages are being displayed
		logInPage.nullPasswordandPasswordAgainError();
		logger.log(LogStatus.PASS, "Error Messages displayed");
	}
	
	@Test (dependsOnMethods={"resetPasswordandPasswordAgainFieldsBlank"}, enabled = true)
	public void passwordShorterThanMinLength() throws Exception {
		logger = report.startTest("Both passwords have less than 8 characters");
		logInPage.fillInResetPasswordTextBox("Pswd#12");
		logInPage.fillInResetPasswordAgainTextBox("Pswd#12");
		// Reset button is disabled
		logInPage.verifyResetPasswordButtonIsDisabled();
		logger.log(LogStatus.PASS, "Password button is disabled");
		// Verify the error message
		logInPage.noMinLengthPasswordError();
		logger.log(LogStatus.PASS, "Error Messages displayed");
	}
	
	@Test (dependsOnMethods={"passwordShorterThanMinLength"}, enabled = true)
	public void passwordsDoNotMatch() throws Exception {
		logger = report.startTest("Passwords do not match");
		logInPage.fillInResetPasswordTextBox("PaSSword#65.");
		logInPage.fillInResetPasswordAgainTextBox("PaSSword#99.");
		logInPage.clickResetPasswordTextBox();
		// Reset button is disabled
		logInPage.verifyResetPasswordButtonIsDisabled();
		logger.log(LogStatus.PASS, "Password button is disabled");
		// Verify the error message
		logInPage.passwordsDoNotMatchError();
		logger.log(LogStatus.PASS, "Error Messages displayed");
	}
	
	@Test (dependsOnMethods={"passwordsDoNotMatch"}, enabled = true)
	public void passwordDoNotMeetCriteria() throws Exception {
		logger = report.startTest("Passwords do not meet the password criteria");
		logInPage.fillInResetPasswordTextBox("HelloTester");
		logInPage.clickResetPasswordAgainTextBox();
		// Reset button is disabled
		logInPage.verifyResetPasswordButtonIsDisabled();
		logger.log(LogStatus.PASS, "Password button is disabled");
		// Verify the error message
		logInPage.passwordDoNotMeetCriteriaError();
		logger.log(LogStatus.PASS, "Error Messages displayed");
	}
	
	@Test (dependsOnMethods={"passwordDoNotMeetCriteria"}, enabled = true)	
	public void resetPasswordFunctionality() throws Exception {
		logger = report.startTest("Passwords meet the password criteria");
		logInPage.fillInResetPasswordTextBox(CredentialsData.getNewPassword());
		logInPage.fillInResetPasswordAgainTextBox(CredentialsData.getNewPassword());
		// Verify password strength message 
		logInPage.passwordStrength();
		logger.log(LogStatus.PASS, "Password strength: GOOD");
		// Reset button is enabled
		logInPage.verifyResetPasswordButtonIsEnabled();
		logger.log(LogStatus.PASS, "Password button is enabled");
		//Click on Reset password button
		logInPage.clickResetPassword();
		logger.log(LogStatus.PASS, "Click on Reset Password button");
	}
	
	@Test (dependsOnMethods={"resetPasswordFunctionality"}, enabled = true)
	public void verifyUserRedirectedToLoginPage() throws Exception{
		logger = report.startTest("Verify user is redirected to Login page");
		//Verified user is redirected to Login page
		logInPage.emailTextBoxIsDisplayed();
		logInPage.emailTextBoxIsBlankByDefault();
		logInPage.emLogoIsDisplayed();
		logInPage.passwordTextBoxIsDisplayed();
		logInPage.passwordTextBoxIsBlankByDefault();
		logInPage.LoginButtonIsDisplayed();		
		logger.log(LogStatus.PASS, "Verified user is redirected to Login Page");
	}
	
	@Test (dependsOnMethods={"verifyUserRedirectedToLoginPage"}, enabled = true)
	public void verifyCanNotLoginOldPassword() throws Exception{
		logger = report.startTest("Verify user can't login with old password");
		// Populate email Text box with accAccessUser email - Old password
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");
        //Click on Login button
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        // Verify user can't log in
        logInPage.InvalidLoginErrorMessage();
        logger.log(LogStatus.INFO, "Unable to login");   
  	}
	
	@Test (dependsOnMethods={"verifyCanNotLoginOldPassword"}, enabled = true)
	public void verifyCanLogInNewPassword() throws Exception{
		logger = report.startTest("Verify user can login with new password");
		// Populate email Text box with accAccessUser email - Old password
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getNewPassword());
        logger.log(LogStatus.INFO, "Entered email & password");
        //Click on Login button
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        // Verify user can log in
        emHeaderPage.verifyHeaderContentsAreDisplayed();
        logger.log(LogStatus.INFO, "Access granted");   
        //user logs out
        emHeaderPage.logout();
        logger.log(LogStatus.INFO, "Clicked Logout");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Verified user has logged out");
    }
		
	@Test (dependsOnMethods={"verifyCanLogInNewPassword"}, enabled = true)
	public void deleteEmailFromZohoAccount() throws Exception{
		logger = report.startTest("Navigate to zoho email page");
		// Go to ZoHo Mail URL & Login
        driver.get(CredentialsData.getZohoURL());
        logger.log(LogStatus.INFO, "Navigated to Zoho Mail URL");
        // Delete email from Zoho account & logout
        zohoPage.clickOnDeleteEmailCheckBox();
        logger.log(LogStatus.INFO, "Clicked on checkbox to delete emails");
        zohoPage.clickOnTrashEmailIconLink();
        logger.log(LogStatus.INFO, "Trash emails");
        zohoPage.verifyInboxEmptyIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully trashed all email");
        zohoPage.logOutOfZohoMail();
        logger.log(LogStatus.INFO, "Clicked Log Out");
        //Verified user is logged out
        zohoPage.verifySignInAgainIsDisplayed();
        logger.log(LogStatus.INFO, "Verified user is logged out");
	}



    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "PasswordResetFuncViaForgotPasswordEmail", imagePath);
            testCaseStatus = "FAILED";
        }
        report.endTest(logger);
    }


    @AfterClass
    public void tearDown() throws Exception {
    	
		logger = report.startTest("Start tear down");
        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.PASS, "Account Access user has been deleted");	      
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
