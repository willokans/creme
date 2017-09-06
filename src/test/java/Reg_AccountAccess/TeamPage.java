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
 * Created by will.okanumeh on 7/5/2016. Re-factored by alan.sheehy 21/12/2016
 * Test Automation for EMA-639
 * DEPENDANT ON CREATE USER PROFILE CLASS
 */
public class TeamPage {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    HomePage homePage;
    AccountPage accountPage;
    ProfilePage profilePage;
    DjangoPage djangoPage;
    String userRole = "QA Automation Engineer";
    String testCaseStatus = "PASSED";

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @BeforeClass
    public void beforeClassSetup() throws IOException, BiffException, ParseException {
        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\TeamPage.html");
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
        logger = report.startTest("Create 'Account Access' test user");
        djangoPage.createUserProfile(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.PASS, "Test user created");
     
    }


    
    @Test (dependsOnMethods = {"createActiveUserProfile"}, enabled = true)
    public void verifyTeamListIsCorrect() throws Exception {
        logger = report.startTest("Verify team list = API organisation list");

        // Go to EM URL & Login
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verified the User is on the Home Page");

        // Navigate to the Team Page
        homePage.navigateToMyProfilePage();
        logger.log(LogStatus.INFO, "Navigate to Account Page");
        profilePage.clickTeamLink();
        logger.log(LogStatus.INFO, "Clicked on Team Link");

        // Create List of User profile in Dept
        djangoPage.getListOfUserProfileDepartmentInEM();
        logger.log(LogStatus.INFO, "Obtained list of User Profile Dept in EM");

        // Log out of EM
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Log out of EM");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Verified the user is on the Login page");

        // Log into Django & filter department users
        djangoPage.goToAPIURL(CredentialsData.getApiURL());
        logger.log(LogStatus.INFO, "Navigated to Django API URL");
        djangoPage.verifyDjangoLogInPage();
        logger.log(LogStatus.PASS, "Verified Login Page");
        djangoPage.fillInEmailTextBox(CredentialsData.getApiAdminUser());
        djangoPage.fillInPasswordTextBox(CredentialsData.getApiAdminPassword());
        logger.log(LogStatus.INFO, "Entered admin email/password credentials");
        djangoPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login button");

        djangoPage.clickPXUserLink();
        logger.log(LogStatus.INFO, "Clicked on Px Users Link");
        djangoPage.fillInPxUserSearchTextBox(CredentialsData.getQAOrganisation());
        djangoPage.clickPXUserSearchButton();
        logger.log(LogStatus.INFO, "Filtered department/organisation users");

        // Create List of User profiles in first and last name
        djangoPage.getListOfUserProfileDepartmentInAPI();
        logger.log(LogStatus.INFO, "Obtained list of User Profile in Dept from API");

        // In the 'Team Page' tab, verify the list of users against the list gathered from the API
        djangoPage.verifyDeptListAreEqual();
        logger.log(LogStatus.INFO, "Verified API & EM lists are equal");

        // Log Out of API
        djangoPage.logOut();
        logger.log(LogStatus.INFO, "Clicked Log Out button");
        djangoPage.verifyLoggedOut();
        logger.log(LogStatus.PASS, "Logged Out");          

    }
    
    
    
    @Test (dependsOnMethods = {"verifyTeamListIsCorrect"}, enabled = true)
    public void verifyUserEditModeForOwnDetails() throws Exception{
    	logger = report.startTest("Verify team members can edit their own information & it's displayed");
    	
    	// Go to EM URL & Login
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verified the User is on the Home Page");
        
        // Edit current user & add Job Title/Role "QA Engr." to profile
        homePage.navigateToMyProfilePage();
        profilePage.clickTeamLink();
        profilePage.fillInRoleTextBox(userRole);
        profilePage.clickCheckSaveChangeButton();
        logger.log(LogStatus.INFO, "Edit made to 'Role' for current user");
        Constants.refreshPage();

        // Verify added Role is displayed
        profilePage.clickTeamLink();
        profilePage.verifyUpdatedRoleIsDisplayed();
        logger.log(LogStatus.PASS, "Verified edit (Updated Role) is correctly displayed for current user");
        homePage.loggingOut();
        logInPage.verifyLogInPage();
        logger.log(LogStatus.INFO, "Current user logged out");

        // Verify the edit is displayed for a different team member (log in as a different team member)       
        Constants.clearBrowserCache();
        logInPage.fillInEmailTextBox(CredentialsData.getEMPermanentUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logInPage.clickLogInButton();
        homePage.verifyHomePage();
        logger.log(LogStatus.INFO, "Logged in as a differnt team member");
        homePage.navigateToMyProfilePage();
        profilePage.clickTeamLink();
        logger.log(LogStatus.INFO, "Navigated to Team Page");
        profilePage.verifyUpdatedRoleIsDisplayed();
        logger.log(LogStatus.PASS, "Verified edit (Updated Role) is displayed for another team member");
        
    }
    
    
    
    @Test (dependsOnMethods = {"verifyUserEditModeForOwnDetails"}, enabled = true)
    public void verifyReadOnlyModeForOtherMembersDetails() throws Exception{
    	logger = report.startTest("Verify read only mode when viewing other team members information");
    	
    	// Click on the edited Team Member and verify user is directed to that profile
        profilePage.clickTestUserLink();
        profilePage.verifyTestUserPageIsDisplayed();
        logger.log(LogStatus.PASS, "User Test Profile is opened & displayed");

        // Verify All fields are read only and cannot be altered
        profilePage.verifyRoleIsReadOnly();
        profilePage.verifyCompanyIsReadOnly();
        profilePage.verifyLocationIsReadOnly();
        profilePage.verifyTelLabelIsReadOnly();
        profilePage.verifyWebSiteLabelIsReadOnly();
        profilePage.verifyLinkedInLabelIsReadOnly();
        profilePage.verifyTwitterLabelIsReadOnly();
        profilePage.verifyFacebookLabelIsReadOnly();
        logger.log(LogStatus.PASS, "Verified all fields belonging to another team member are read only");
        
        // Logout the second team member
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Clicked on Log Out button");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Logged Out");

    }
    
    
    
    @Test (dependsOnMethods = {"verifyReadOnlyModeForOtherMembersDetails"}, enabled = true)
    public void deletePreviouslyEnteredData() throws Exception{
    	logger = report.startTest("Verify previously entered data can be deleted");
    	
    	logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Logged in as user to be updated");
        homePage.verifyHomePage();
        homePage.navigateToMyProfilePage();
        logger.log(LogStatus.INFO, "Navigated to the profile - team page");
        profilePage.clearAddRoleTextBox();
        logger.log(LogStatus.PASS, "Previously entered data has been deleted");
        
        homePage.loggingOut();
        logInPage.verifyLogInPage();
        logger.log(LogStatus.INFO, "Logged Out");
        
    }




    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "TeamPage", imagePath);
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
