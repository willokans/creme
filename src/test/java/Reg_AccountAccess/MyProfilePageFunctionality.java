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
import Page.classes.ProfilePage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;
import jxl.read.biff.BiffException;

/**
 * Created by will.okanumeh on 6/8/2016.
 *  * AT-24
 Test Automation for EMA-638
 * DEPENDANT ON CREATE USER PROFILE CLASS
 */


public class MyProfilePageFunctionality {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    HomePage homePage;
    AccountPage accountPage;
    ProfilePage profilePage;
    DjangoPage djangoPage;
    String role = "Test";
    String company = "Creme Global";
    String location = "Dublin";
    String testCaseStatus = "PASSED";

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();



    @BeforeClass
    public void beforeClassSetup() throws IOException, BiffException, ParseException {
        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\UserProfileFunctionality.html");
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
        profilePage = new ProfilePage(driver);
        djangoPage = new DjangoPage(driver);

    }





    @Test
    public void createActiveUserProfile() throws Exception {
    	logger = report.startTest("My Profile: Create 'Account Access' test user");
	 	djangoPage.createUserProfile(CredentialsData.getAccAccessUser());
	 	logger.log(LogStatus.PASS, "Test user created");
    }


    @Test (dependsOnMethods = {"createActiveUserProfile"}, enabled = true)
    public void verifyMyProfilePageContents() throws Exception {
    	logger = report.startTest("My Profile: Verify My Profile page contents");


        // Go to URL & Login
    	driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verified the user is on the Home Page");

        // Navigate to User Account - My Profile Page
        homePage.navigateToMyProfilePage();
        logger.log(LogStatus.INFO, "Navigated to User Account - My Profile Page");

        // Verify 'My Profile' page contents are displayed            
        profilePage.verifyUserNameIsDisplayed();
        logger.log(LogStatus.PASS, "Username is displayed");
        profilePage.verifyAddRoleLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Job role is displayed");
        profilePage.verifyCompanyLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Company name is displayed");
        profilePage.verifyLocationLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Location is displayed");
        profilePage.verifyEmailAddressIsDisplayed();
        logger.log(LogStatus.PASS, "Email address is displayed");
        profilePage.verifyTelLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Phone number is displayed");
        profilePage.verifyWebLinkIsDisplayed();
        logger.log(LogStatus.PASS, "Website link is displayed");
        profilePage.verifyLinkedInLabelIsDisplayed();
        logger.log(LogStatus.PASS, "LinkedIn link is displayed");
        profilePage.verifyTwitterLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Twitter link is displayed");
        profilePage.verifyFacebookLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Facebook link is displayed");
        profilePage.verifyTeamLinkIsDisplayed();
        logger.log(LogStatus.PASS, "Team page tab option is available");
        
    }
    
    
    
    @Test (dependsOnMethods = {"verifyMyProfilePageContents"}, enabled = true )
    public void verifyEditModeMyProfileHeader() throws Exception{
    	logger = report.startTest("My Profile: Verify edit/cancel & edit/save modes for My Profile header");
    		
    	// Read only attribute non existent for UserName
    	//profilePage.verifyReadOnlyUserName();
        //logger.log(LogStatus.PASS, "Username Label is Read Only");
        
        // Edit & Cancel (Role, Company & location)
        profilePage.fillInRoleTextBox(role);
        logger.log(LogStatus.INFO, "Clicked and entered role information");
        profilePage.clickCheckCancelChangeButton();
        logger.log(LogStatus.INFO, "Clicked cancel X option");
        profilePage.verifyAddRoleLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified edit and cancel functionality for role option");
        
        profilePage.fillInCompanyTextBox(company);
        logger.log(LogStatus.INFO, "Clicked and entered company information");
        profilePage.clickCheckCancelChangeButton();
        logger.log(LogStatus.INFO, "Clicked cancel X option");
        profilePage.verifyCompanyLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified edit and cancel functionality for company option");
        
        profilePage.fillInLocationTextBox(location);
        logger.log(LogStatus.INFO, "Clicked and entered location information");
        profilePage.clickCheckCancelChangeButton();
        logger.log(LogStatus.INFO, "Clicked cancel X option");
        profilePage.verifyLocationLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified edit and cancel functionality for location option");
        
        // Edit & Save (Role, Company & location)
        profilePage.fillInRoleTextBox(role);
        logger.log(LogStatus.INFO, "Clicked and entered role information");
        profilePage.clickCheckSaveChangeButton();
        logger.log(LogStatus.INFO, "Clicked save (tick) option");
        profilePage.roleTextBoxUpdateIsDisplayed(role);
        logger.log(LogStatus.PASS, "Verified edit and save functionality for role option");
        
        profilePage.fillInCompanyTextBox(company);
        logger.log(LogStatus.INFO, "Clicked and entered company information");
        profilePage.clickCheckSaveChangeButton();
        logger.log(LogStatus.INFO, "Clicked save (tick) option");
        profilePage.companyTextBoxUpdateIsDisplayed(company);
        logger.log(LogStatus.PASS, "Verified edit and save functionality for company option");
        
        profilePage.fillInLocationTextBox(location);
        logger.log(LogStatus.INFO, "Clicked and entered location information");
        profilePage.clickCheckSaveChangeButton();
        logger.log(LogStatus.INFO, "Clicked save (tick) option");
        profilePage.locationTextBoxUpdateIsDisplayed(location);
        logger.log(LogStatus.PASS, "Verified edit and save functionality for location option");
        
        // Delete entered data
        profilePage.clearAddRoleTextBox();
        profilePage.clearAddCompTextBox();
        profilePage.clearAddLocationTextBox();
        
    }
    
    
    
    @Test (dependsOnMethods = {"verifyEditModeMyProfileHeader"}, enabled = true )
    public void verifyEditModeDetailsSection() throws Exception{
    	logger = report.startTest("My Profile: Verify My Profile details section edit mode functionality");
    	
    	profilePage.emailIsLinkedToMailTo();
        logger.log(LogStatus.PASS, "Email is Linked to Mail To");
        
        // Click 'EDIT', make some changes and 'SAVE'
        profilePage.clickEditButton();
        logger.log(LogStatus.INFO, "Clicked 'Edit' button");
        profilePage.enterPhoneNumber("01-12345");
        profilePage.enterWebSiteLink("www.google.com");
        profilePage.enterTwitterAccountLink("www.twitter.com");
        profilePage.enterLinkedInAccountLink("www.linkedIn.com");
        profilePage.enterFaceBookAccountLink("www.facebook.com");
        logger.log(LogStatus.INFO, "Edited details entries");
        profilePage.clickSaveButton();
        logger.log(LogStatus.INFO, "Clicked 'Save' for new details entries");

        // Verify that changes are retained after clicking on save
        profilePage.telNumberIsDisplayed();
        profilePage.webSiteNameLinkIsDisplayed();
        profilePage.facebookLinkIsDisplayed();
        profilePage.linkedInLinkIsDisplayed();
        profilePage.twitterLinkIsDisplayed();
        logger.log(LogStatus.PASS, "Verified edited details are saved & displayed successfully");
        
        // Delete details entries
        profilePage.clickEditButton();
        logger.log(LogStatus.INFO, "Clicked 'Edit' again");
        profilePage.clearTelNumberTextBox();
        profilePage.clearWebSiteTextBox();
        profilePage.clearLinkedInTextBox();
        profilePage.clearTwitterTextBox();
        profilePage.clearFacebookTextBox();
        logger.log(LogStatus.INFO, "Cleared all details entries");
        profilePage.clickSaveButton();
        logger.log(LogStatus.INFO, "Saved cleared field for details");
        
        // Verify entries have been deleted
        profilePage.verifyDetailsAreEmpty();
        logger.log(LogStatus.PASS, "Verified all details have been cleared");

    }   
    
    
    
    @Test (dependsOnMethods = {"verifyEditModeDetailsSection"}, enabled = true)
    public void verifyGravitarSource() throws Exception{
    	logger = report.startTest("My Profile: Verify Gravitar Source");
    	
    	profilePage.verifyGravaterImgIsDisplayed();
        logger.log(LogStatus.PASS, "Gravatar is displayed");
        profilePage.verifyQAGravatarSource();
        logger.log(LogStatus.PASS, "Verified Gravatar Source");
        
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Current user clicks logout");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Verified user has logged out");
        
        logInPage.goToEMUrl(CredentialsData.getBaseURL());
        logInPage.fillInEmailTextBox(CredentialsData.getEMPermanentUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Logged in with a different user");
        homePage.navigateToMyProfilePage();
        logger.log(LogStatus.INFO, "Navigated to My Profile page");
        profilePage.verifyTestGravatarSource();
        logger.log(LogStatus.PASS, "Verified Gravatar Source for a different user");
        
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Current user clicks logout");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Verified user has logged out");
        
    }




    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "User Profile - My Profile Functionality", imagePath);
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
