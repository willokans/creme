package Reg_Django;

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
import Page.classes.LogInPage;
import Page.classes.ZohoEmailPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okanumeh on 6/28/2016. Re-factored by alan.sheehy on 18/01/2017
 * AT-53
 * Test Automation for EMA-753
 * NOT DEPENDANT ON CREATE USER PROFILE CLASS
 */
public class APIRegFunctionality {

    ExtentReports report;
    ExtentTest logger;
    DjangoPage djangoPage;
    LogInPage logInPage;
    ZohoEmailPage zohoPage;
    EMHeaderPage emHeaderPage;
    String testCaseStatus = "PASSED";
    String blankEntry = "";

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\APIRegFunctionality.html");
        report = ExtentFactory.getInstance();

    }



    @BeforeMethod
    public void setup() throws Exception {
        // Maximize the broswer's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        djangoPage = new DjangoPage(driver);
        logInPage = new LogInPage(driver);
        zohoPage = new ZohoEmailPage(driver);
        emHeaderPage = new EMHeaderPage(driver);
    }




    @Test
    public void loginAsAdminUserAndNavigateToAddPxUserPage() throws Exception {
        logger = report.startTest("Verify API admin users can access the API admin panel");

        // Login as admin & verify access is granted
        djangoPage.goToAPIURL(CredentialsData.getApiURL());
        logger.log(LogStatus.INFO, "Navigated to Django API URL");
        djangoPage.verifyDjangoLogInPage();
        logger.log(LogStatus.INFO, "Verified login page is displayed");

        djangoPage.fillInEmailTextBox(CredentialsData.getApiAdminUser());
        djangoPage.fillInPasswordTextBox(CredentialsData.getApiAdminPassword());
        logger.log(LogStatus.INFO, "Entered admin user credentials");
        djangoPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on the Login button");
        
        djangoPage.verifyDjangoHomePage();
        logger.log(LogStatus.PASS, "Verified access granted for an admin user");
        
        // Create a new user option
        djangoPage.clickPXUserLink();
        logger.log(LogStatus.INFO, "Clicked on 'Px users' Link");
        djangoPage.verifyUsersListIsDisplayed();
        logger.log(LogStatus.PASS, "Verified a list of users is displayed");
        
        djangoPage.clickAddPXUserLink();
        logger.log(LogStatus.INFO, "Click on 'ADD PX USER +' button");

    }



    @Test (dependsOnMethods={"loginAsAdminUserAndNavigateToAddPxUserPage"}, enabled = true)
    public void verifyRequiredFieldsAndErrorMessages() throws Exception {
        logger = report.startTest("Verify error messages for incomplete fields");
               
        // Verify first name is a required field 
        djangoPage.fillInRegFirstNameTextBox(blankEntry);
        djangoPage.fillInRegLastNameTextBox(CredentialsData.getLastName());
        djangoPage.fillInRegEmailTextBox(CredentialsData.getAccAccessUser());
        djangoPage.selectOrganisation(CredentialsData.getQAOrganisation());
        djangoPage.fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        djangoPage.fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
        djangoPage.clickIsActiveCheckBox();
        djangoPage.clickNeedsPasswordChangeCheckBox();
        logger.log(LogStatus.INFO, "Completed all fields except first name");
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Click on the 'SAVE' Button");
        djangoPage.verifyPleaseCorrectTheErrorBelowIsDisplayed();
        djangoPage.verifyFirstNameErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified first name is a required field");
        djangoPage.verifyUserIsOnAddPXUserPage();
        logger.log(LogStatus.PASS, "Verified user remains on the add px user page");
        
        // Verify last name is a required field (toggle checkboxes which are not required, off)
        djangoPage.fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        djangoPage.fillInRegLastNameTextBox(blankEntry);
        djangoPage.fillInRegEmailTextBox(CredentialsData.getAccAccessUser());
        djangoPage.selectOrganisation(CredentialsData.getQAOrganisation());
        djangoPage.fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        djangoPage.fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
        djangoPage.clickIsActiveCheckBox();
        djangoPage.clickNeedsPasswordChangeCheckBox();
        logger.log(LogStatus.INFO, "Completed all fields except last name");
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Click on the 'SAVE' Button");
        djangoPage.verifyPleaseCorrectTheErrorBelowIsDisplayed();
        djangoPage.verifyLastNameErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified last name is a required field");
        djangoPage.verifyUserIsOnAddPXUserPage();
        logger.log(LogStatus.PASS, "Verified user remains on the add px user page");
        
        // Verify email is a required field (toggle checkboxes which are not required, on)
        djangoPage.fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        djangoPage.fillInRegLastNameTextBox(CredentialsData.getLastName());
        djangoPage.fillInRegEmailTextBox(blankEntry);
        djangoPage.selectOrganisation(CredentialsData.getQAOrganisation());
        djangoPage.fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        djangoPage.fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
        djangoPage.clickIsActiveCheckBox();
        djangoPage.clickNeedsPasswordChangeCheckBox();
        logger.log(LogStatus.INFO, "Completed all fields except email address");
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Click on the 'SAVE' Button");
        djangoPage.verifyPleaseCorrectTheErrorBelowIsDisplayed();
        djangoPage.verifyEmailErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified email address is a required field");
        djangoPage.verifyUserIsOnAddPXUserPage();
        logger.log(LogStatus.PASS, "Verified user remains on the add px user page");
        
        // Verify organisation is a required field (toggle checkboxes which are not required, off)
        djangoPage.fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        djangoPage.fillInRegLastNameTextBox(CredentialsData.getLastName());
        djangoPage.fillInRegEmailTextBox(CredentialsData.getAccAccessUser());
        djangoPage.selectOrganisation("---------");
        djangoPage.fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        djangoPage.fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
        djangoPage.clickIsActiveCheckBox();
        djangoPage.clickNeedsPasswordChangeCheckBox();
        logger.log(LogStatus.INFO, "Completed all fields except organisation");
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Click on the 'SAVE' Button");
        djangoPage.verifyPleaseCorrectTheErrorBelowIsDisplayed();
        djangoPage.verifyOrgErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified organisation is a required field");
        djangoPage.verifyUserIsOnAddPXUserPage();
        logger.log(LogStatus.PASS, "Verified user remains on the add px user page");

        // Verify password is a required field (toggle checkboxes which are not required, on)
        djangoPage.fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        djangoPage.fillInRegLastNameTextBox(CredentialsData.getLastName());
        djangoPage.fillInRegEmailTextBox(CredentialsData.getAccAccessUser());
        djangoPage.selectOrganisation(CredentialsData.getQAOrganisation());
        djangoPage.fillInRegPasswordTextBox(blankEntry);
        djangoPage.fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
        djangoPage.clickIsActiveCheckBox();
        djangoPage.clickNeedsPasswordChangeCheckBox();
        logger.log(LogStatus.INFO, "Completed all fields except password");
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Click on the 'SAVE' Button");
        djangoPage.verifyPleaseCorrectTheErrorBelowIsDisplayed();
        djangoPage.verifyPasswordErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified password is a required field");
        djangoPage.verifyUserIsOnAddPXUserPage();
        logger.log(LogStatus.PASS, "Verified user remains on the add px user page");
       
        // Verify 'password confirmation' is a required field (toggle checkboxes which are not required, off)
        djangoPage.fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        djangoPage.fillInRegLastNameTextBox(CredentialsData.getLastName());
        djangoPage.fillInRegEmailTextBox(CredentialsData.getAccAccessUser());
        djangoPage.selectOrganisation(CredentialsData.getQAOrganisation());
        djangoPage.fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        djangoPage.fillInRegPasswordConfirmationTextBox(blankEntry);
        djangoPage.clickIsActiveCheckBox();
        djangoPage.clickNeedsPasswordChangeCheckBox();
        logger.log(LogStatus.INFO, "Completed all fields except password confirmation");
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Click on the 'SAVE' Button");
        djangoPage.verifyPleaseCorrectTheErrorBelowIsDisplayed();
        djangoPage.verifyConfirmPasswordErrorMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified password confirmation is a required field");
        djangoPage.verifyUserIsOnAddPXUserPage();
        logger.log(LogStatus.PASS, "Verified user remains on the add px user page"); 
        
    }
    
    
    @Test (dependsOnMethods = {"verifyRequiredFieldsAndErrorMessages"}, enabled = true)
    public void completeAllRequiredFieldsAndCreateUser() throws Exception{
    	logger = report.startTest("Verify a user can be created once all requried fields have been completed");
    	
    	// Complete all required field, select needs a password change & save
        djangoPage.fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        djangoPage.fillInRegLastNameTextBox(CredentialsData.getLastName());
        djangoPage.fillInRegEmailTextBox(CredentialsData.getAccAccessUser());
        djangoPage.selectOrganisation(CredentialsData.getQAOrganisation());
        djangoPage.fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        djangoPage.fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());      
        djangoPage.clickIsActiveCheckBox();
        djangoPage.clickNeedsPasswordChangeCheckBox();
        logger.log(LogStatus.INFO, "Completed all required field & selected 'Needs Password Change'");

        // click save & save profile
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Clicked on the 'SAVE' Button");
        djangoPage.verifyUserCreateMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Verified user has been created message");
        djangoPage.clickOnSave();
        logger.log(LogStatus.INFO, "Clicked on Save button on User Profile");

        djangoPage.logOut();
        logger.log(LogStatus.INFO, "Click on Log Out button");
        djangoPage.verifyLoggedOut();
        logger.log(LogStatus.PASS, "Verified admin user has logged out");

    }
    
    
    @Test(dependsOnMethods = {"completeAllRequiredFieldsAndCreateUser"}, enabled = true)
    public void verifyAccessDeniedBeforePasswordChange() throws Exception{
    	logger = report.startTest("Verify access denied before user password has been changed");
    	
    	// Attempt to login with the credentials created
    	driver.get(CredentialsData.getBaseURL());
    	logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
    	logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
    	logger.log(LogStatus.INFO, "Created credentials entered");
    	logInPage.clickLogInButton();
    	logger.log(LogStatus.INFO, "Login button clicked");
    	logInPage.verifyLogInPage();
    	logger.log(LogStatus.PASS, "Verified access is denied & the user remains on the login page");
    	logInPage.passwordChangeErrorMessageDisplayed();
    	logger.log(LogStatus.PASS, "Verified password change required error message is displayed");
    	
    }


    @Test (dependsOnMethods={"verifyAccessDeniedBeforePasswordChange"}, enabled = true)
    public void verifyPasswordChangeEmailIsReceived() throws Exception {
        logger = report.startTest("Verify account setup email (inc. Change Password Link) is received");

        // Log into Zoho mail
        driver.get(CredentialsData.getZohoURL());
        logger.log(LogStatus.INFO, "Navigated to Zoho Mail URL");
        zohoPage.enterTextToEmailTextBox(CredentialsData.getZohoEmailUser());
        zohoPage.enterTextToPasswordTextBox(CredentialsData.getZohoEmailPassword());
        logger.log(LogStatus.INFO, "Entered zoho credentials");
        zohoPage.clickOnSignInButton();
        logger.log(LogStatus.INFO, "Clicked on Sign in Button");
        zohoPage.verifyInboxPage();
        logger.log(LogStatus.INFO, "Verified login complete");
        
        // Verify email receipt & contents
        zohoPage.waitAndClickAccountSetupEmail();
        logger.log(LogStatus.PASS, "Verified account setup email has been received & email has been opened");
        zohoPage.verifyChangePasswordEmailIsDisplayed();
        logger.log(LogStatus.PASS, "Verified Change Password Link is displayed in the email");

    }
    
    
    @Test (dependsOnMethods = {"verifyPasswordChangeEmailIsReceived"}, enabled = true)
    public void changePasswordViaChangePasswordLink() throws Exception{
    	logger = report.startTest("Change Password via account seup email 'Change Password' button");
        
    	// Click link & change password
        logInPage.clickChangePasswordLinkAndSubmit(CredentialsData.getNewPassword());
        logger.log(LogStatus.PASS, "Clicked on 'Change Password' link & changed password on change password page");

        // Clean up email inbox
        zohoPage.clickOnDeleteEmailCheckBox();
        logger.log(LogStatus.INFO, "Clicked on select all checkbox to delete email(s)");
        zohoPage.clickOnTrashEmailIconLink();
        logger.log(LogStatus.INFO, "Clicked on Trash Emails");
        zohoPage.verifyInboxEmptyIsDisplayed();
        logger.log(LogStatus.INFO, "Verified all emails have been trashed");

        // Logout of Zoho mail
        zohoPage.logOutOfZohoMail();
        logger.log(LogStatus.PASS, "Clicked on Log Out");
        zohoPage.verifySignInAgainIsDisplayed();
        logger.log(LogStatus.PASS, "Verified user has logged out");

    }



    @Test (dependsOnMethods = {"changePasswordViaChangePasswordLink"}, enabled = true)
    public void loginWithChangedPassword() throws Exception {
        logger = report.startTest("Verify access is granted with new/updated credentials");

        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");

        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getNewPassword());
        logger.log(LogStatus.INFO, "Entered new credentials");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        emHeaderPage.verifyHeaderContentsAreDisplayed();
        logger.log(LogStatus.PASS, "Verified access granted with the new credentials");

        emHeaderPage.logout();
        logger.log(LogStatus.INFO, "Clicked on logout");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Verified user has logged out");

    }



   
    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "createUserProfile", imagePath);
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
