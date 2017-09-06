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
import Page.classes.HomePage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;
import jxl.read.biff.BiffException;

/**
 * Created by will.okanumeh on 6/8/2016.
 * AT-18
 * Test Automation for EMA-636
 * DEPENDANT ON CREATE USER PROFILE CLASS
 */
public class PasswordResetFuncViaAccountPage {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    HomePage homePage;
    AccountPage accountPage;
    EMHeaderPage emHeaderPage;
    DjangoPage djangoPage;
    String testCaseStatus = "PASSED";

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @BeforeClass
    public void beforeClassSetup() throws IOException, BiffException, ParseException {
        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\PasswordResetFuncViaAccountPage.html");
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
        emHeaderPage = new EMHeaderPage(driver); 
        djangoPage = new DjangoPage(driver);

    }
    
    @Test
	public void createActiveUserProfile() throws Exception {
		logger = report.startTest("Create 'Account Access' test user");
	    djangoPage.createUserProfile(CredentialsData.getAccAccessUser());
	    logger.log(LogStatus.PASS, "Test user created");
	}   

    @Test (dependsOnMethods={"createActiveUserProfile"}, enabled = true)
    public void changePasswordPageValidation() throws Exception {
        logger = report.startTest("Validate Account Page - Change Password page & actively change the password");
        // 0. Got to URL
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigate to Expert Models URL");

        //1.    Enter Valid email/Valid password
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Enter valid login credentials");

        // 2. Click Login button
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Click on Login Button");

        // 3. Verify User is LogIn Successfully
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verified the user is on the Home Page");

        //4.    navigate to Account Page
        homePage.navigateToAccountPage();
        logger.log(LogStatus.INFO, "Navigated to the Account Page");

        //5.    click Change Password Link
        accountPage.clickChangePasswordLink();
        logger.log(LogStatus.INFO, "Click on Change Password tab");

        //6.    Verify user is on change password window
        accountPage.oldPasswordTextBoxIsDisplayed();
        logger.log(LogStatus.INFO, "Navigated to change password tab & verified old password field is displayed");

        //7.    Verify page contents are displayed      //a.    Title = 'Account'
        accountPage.verifyAccountTileIsDisplayed();
        logger.log(LogStatus.PASS, "Account Link is displayed");

        //b.    Tab = 'CHANGE PASSWORD' is displayed
        accountPage.verifyChangePasswordLinkIsDisplayed();
        logger.log(LogStatus.PASS, "Change Password tab is displayed");

        //c.    Current Password' field (blank by default)
        accountPage.verifyCurrentPasswordFeildIsBlank();
        logger.log(LogStatus.PASS, "Current/Old Password field is displayed");

        //d.    New Password' field (blank by default)
        accountPage.verifyNewPasswordFeildIsBlank();
        logger.log(LogStatus.PASS, "New Password field is displayed");

        //e.    New Password "Again" ' field (blank by default)
        accountPage.verifyNewPasswordAgainFeildIsBlank();
        logger.log(LogStatus.PASS, "New Password Again field is displayed");

        //f.    Confirm' button (disabled, by default)
        accountPage.verifyConfirmButtonIsDisabled();
        logger.log(LogStatus.PASS, "Confirm Button is disable by default");

        //g.    Cancel' button (Available by default)
        accountPage.verifyCancelButtonIsEnabled();
        logger.log(LogStatus.PASS, "Cancel Button is enable by default");

        //8.    Leave the 'Current Password' field empty, complete the remaining fields and attempt to 'Confirm'
        accountPage.clearCurrentPasswordTextBox();
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyErrorMessageForNullCurrentPasswordTextBox();
        accountPage.refreshPageAndClickChangePasswordTab();
        logger.log(LogStatus.PASS, "Password is required error message captured");

        //9.    Leave the 'New Password' field empty, complete the remaining fields and attempt to 'Confirm'
        accountPage.clearNewPasswordTextBox();
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyErrorMessageForNullNewPasswordTextBox();
        accountPage.refreshPageAndClickChangePasswordTab();
        logger.log(LogStatus.PASS, "Password is required error message captured");

        //10.   Leave the 'New Password Again' field empty, complete the remaining fields and attempt to 'Confirm'
        accountPage.enterTextToCurrentPasswordTextBox("Creme#12345");
        accountPage.enterTextToNewPasswordTextBox(CredentialsData.getNewPassword());
        accountPage.clearNewPasswordAgainTextBox();
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyErrorMessageForNullNewPasswordAgainTextBox();
        accountPage.refreshPageAndClickChangePasswordTab();
        logger.log(LogStatus.PASS, "Confirmation Password  is required error message captured");

        //11.   Leave all fields empty and attempt to  'Confirm'
        accountPage.clearCurrentPasswordTextBox();
        accountPage.clearNewPasswordTextBox();
        accountPage.clearNewPasswordAgainTextBox();
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyErrorMessageForNullCurrentPasswordTextBox();
        accountPage.verifyErrorMessageForNullNewPasswordTextBox();
        accountPage.verifyErrorMessageForNullNewPasswordAgainTextBox();
        accountPage.refreshPageAndClickChangePasswordTab();
        logger.log(LogStatus.PASS, "All error messages captured successfully");

        //12.   Enter an incorrect 'Current Password' that satisfies the minimum length of 8 chars and a valid new password combination, Attempt to 'Confirm'
        accountPage.enterTextToNewPasswordTextBox(CredentialsData.getNewPassword());
        accountPage.enterTextToNewPasswordAgainTextBox(CredentialsData.getNewPassword());
        accountPage.enterTextToCurrentPasswordTextBox("Creme#12345");
        accountPage.clickConfirmButton();
        accountPage.verifyCurrentPasswordNotCorrectErrorMassageIsDisplayed();
        accountPage.refreshPageAndClickChangePasswordTab();
        logger.log(LogStatus.PASS, "Current Password not correct Error Message is displayed");

        //14.   Enter a valid new password combination that doesn't match
        accountPage.enterTextToNewPasswordAgainTextBox("Creme#128");
        accountPage.enterTextToNewPasswordTextBox("Creme#12346");
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyPasswordDoesNotMatchErrorMassageIsDisplayed();
        accountPage.refreshPageAndClickChangePasswordTab();
        logger.log(LogStatus.PASS, "Password dose not Match Error Message is displayed");

        //13.   Enter a correct current password along with a new password combination that doesn't meet the minimum length criteria (<8 characters)
        accountPage.enterTextToCurrentPasswordTextBox(CredentialsData.getNewPassword());
        accountPage.enterTextToNewPasswordTextBox("Cre#456");
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyLessThan8PasswordErrorMessageIsDisplayed();
        accountPage.refreshPageAndClickChangePasswordTab();
        logger.log(LogStatus.PASS, "Password must be at least 8 characters long Error Message is displayed");


        //15.   Valid current password, Enter a new password combination that doesn't meet the password strength criteria and attempt to 'Save'

        accountPage.enterTextToNewPasswordTextBox("123654789");
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyWrongPasswordSyntaxIsDisplayed();
        logger.log(LogStatus.PASS, "Verified minimum strength requirements error message is displayed for: 123654789");
        accountPage.refreshPageAndClickChangePasswordTab();

        //b.    lower case letter
        accountPage.enterTextToNewPasswordTextBox("cremeglobaltest");
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyWrongPasswordSyntaxIsDisplayed();
        logger.log(LogStatus.PASS, "Verified minimum strength requirements error message is displayed for: cremeglobaltest");
        accountPage.refreshPageAndClickChangePasswordTab();

        //c.    upper case letter
        accountPage.enterTextToNewPasswordTextBox("CREMEGLOBALTEST");
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyWrongPasswordSyntaxIsDisplayed();
        logger.log(LogStatus.PASS, "Verified minimum strength requirements error message is displayed for: CREMEGLOBALTEST");
        accountPage.refreshPageAndClickChangePasswordTab();

        //d.    special character
        accountPage.enterTextToNewPasswordTextBox("!@#$%^&*()");
        accountPage.verifyConfirmButtonIsDisabled();
        accountPage.verifyNewPasswordNotMatchingMultiCaseErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified multicase requirements error message is displayed for: !@#$%^&*()");
        accountPage.refreshPageAndClickChangePasswordTab();

    }       
    
       
    
    @Test (dependsOnMethods = {"changePasswordPageValidation"}, enabled = true)
    public void VerifyChangePasswordFunctionality() throws Exception {

        logger = report.startTest("Reset Password: Verify password change via 'CHNAGE PASSWORD' tab");
        
        // Enter a valid current password plus a valid new password combination that satisfies all the criteria
        accountPage.enterTextToCurrentPasswordTextBox(CredentialsData.getUserPassword());
        accountPage.enterTextToNewPasswordTextBox(CredentialsData.getNewPassword());
        accountPage.enterTextToNewPasswordAgainTextBox(CredentialsData.getNewPassword());
        accountPage.clickConfirmButton();
        accountPage.verifyUserPasswordIsUpdatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "User Password Is Updated Message is displayed");

        // Logout and attempt to login with the old password
        homePage.loggingOut();
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logInPage.clickLogInButton();
        logInPage.InvalidLoginErrorMessage();
        logger.log(LogStatus.PASS, "Access is denied with Old password");

        // Attempt to login with the new password
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getNewPassword());
        logInPage.clickLogInButton();
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "User Successfully logs In with New Password");

        // Sign Out
        homePage.loggingOut();
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Logout verified");


        report.endTest(logger);

    }


    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "PasswordResetFuncViaAccountPage", imagePath);
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
