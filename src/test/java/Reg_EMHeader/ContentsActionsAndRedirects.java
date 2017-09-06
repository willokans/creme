package Reg_EMHeader;

import java.io.IOException;
import java.text.ParseException;
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

import Page.classes.AccountPage;
import Page.classes.ActivityPage;
import Page.classes.DjangoPage;
import Page.classes.EMHeaderPage;
import Page.classes.FilePage;
import Page.classes.FileSystemPage;
import Page.classes.HelpPage;
import Page.classes.LogInPage;
import Page.classes.ModelsPage;
import Page.classes.ProfilePage;
import Page.classes.TrashPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;
import jxl.read.biff.BiffException;

/**
 * Created by alan.sheehy on 11/11/2016.
 * Test Automation for EMA-808
 * DEPENDANT ON CREATE USER PROFILE CLASS
 * Tests for requirements 1, 2, 3, 4, 10 (inc. all sub req's) in EMA-742
 */

public class ContentsActionsAndRedirects {
	
    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
	DjangoPage djangoPage;
	EMHeaderPage emHeaderPage;
	ModelsPage modelsPage;
	FilePage filePage;
	ActivityPage activityPage;
	TrashPage trashPage;
	FileSystemPage fileSystemPage;
	ProfilePage profilePage;
	AccountPage accountPage;
	HelpPage helpPage;
	String testCaseStatus = "PASSED";
	 
	 private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
	 
	 @BeforeSuite
	 public void configLogging() throws IOException{
	    	System.out.println("Creating "+CredentialsData.getEMHeaderUser()+" user profile on: "+CredentialsData.getApiURL());
	    	System.out.println("Running EM Header tests on: "+CredentialsData.getBaseURL());
	 }
	 
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
	        djangoPage = new DjangoPage(driver);
	        emHeaderPage = new EMHeaderPage(driver);
	        modelsPage = new ModelsPage(driver);
	        filePage = new FilePage(driver);
	        activityPage = new ActivityPage(driver);
	        trashPage = new TrashPage(driver);
	        fileSystemPage = new FileSystemPage(driver);
	        profilePage = new ProfilePage(driver);
	        accountPage = new AccountPage(driver);
	        helpPage = new HelpPage(driver);
	        		
	 }
	 
	 
	 
	 @Test
	 public void createActiveUserProfile() throws IOException, Exception{
		 	logger = report.startTest("Create EM Header test user");
		 	djangoPage.createUserProfile(CredentialsData.getEMHeaderUser());
		 	logger.log(LogStatus.PASS, "Test user created");
	 }
	 
	 
	 
	 @Test (dependsOnMethods = {"createActiveUserProfile"}, enabled = true)
	 public void verifyEMHeaderContents() throws Exception{		 	
		 	logger = report.startTest("Verify EM Header contents");

		 	driver.get(CredentialsData.getBaseURL());
	        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");

	        logInPage.fillInEmailTextBox(CredentialsData.getEMHeaderUser());
	        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
	        logger.log(LogStatus.INFO, "Entered email & password");

	        logInPage.clickLogInButton();
	        logger.log(LogStatus.INFO, "Clicked on Login Button");

	        emHeaderPage.verifyHeaderContentsAreDisplayed();
	        logger.log(LogStatus.PASS, "EM Header Contents verified");
	        report.endTest(logger);
	 } 
	 
	 
	 
	 @Test (dependsOnMethods = {"verifyEMHeaderContents"}, enabled = true)
	 public void verifyHeaderLinkRedirects() throws Exception{
		 	logger = report.startTest("Verify EM Header link redirects");
		 	// Models Link
		 	emHeaderPage.clickOnModelsLink();
		 	logger.log(LogStatus.INFO, "Models link clicked");
		 	modelsPage.verifyModelsPageIsDisplayed();
		 	logger.log(LogStatus.PASS, "Verified User is redirected to the Models Page");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents are displayed on Models Page");
		 	// Files Link
		 	emHeaderPage.clickOnFilesLink();
		 	logger.log(LogStatus.INFO, "Files link clicked");
		 	filePage.verifyUserIsOnMyDataPage();
		 	logger.log(LogStatus.PASS, "Verified User is redirected to the Files Page");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verifed EM Header contents are displayed on File Page");
		 	// Activity Link
		 	emHeaderPage.clickOnActivityLink();
		 	logger.log(LogStatus.INFO, "Activity link clicked");
		 	activityPage.verifyActivityPageIsDisplayed();
		 	logger.log(LogStatus.PASS, "Verified User is redirected to the Activity Page");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents are displayed on Activity Page");
		 	// EM Logo Link
		 	//emHeaderPage.clickOnAppLogo();
		 	//logger.log(LogStatus.INFO, "Application Logo Clicked");
		 	//filePage.verifyUserIsOnMyDataPage();
		 	//logger.log(LogStatus.PASS, "Verified User is redirected to the Files/Home Page");
		 	//emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	//logger.log(LogStatus.PASS, "Verified EM Header contents are displayed on File/Home Page");	 	
		 	report.endTest(logger);
	 }
	 
	 
	 
	 @Test (dependsOnMethods = {"verifyHeaderLinkRedirects"}, enabled = true)
	 public void verifyEMHeaderInAllSystemFolders() throws Exception{
		 	logger = report.startTest("Verify EM Header in all system folders");
		 	emHeaderPage.clickOnFilesLink();
		 	logger.log(LogStatus.INFO, "Files link clicked");
		 	        
		 	filePage.clickSharedDataOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Clicked on left menu 'Shared Data'");
		 	filePage.verifyUserIsOnSharedDataPage();
		 	logger.log(LogStatus.PASS, "Verified user is in the 'Shared Data' system folder");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in the 'Shared Data' system folder");
		 	
		 	filePage.clickMyEMDataOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Clicked on left menu 'My EM Data'");
		 	filePage.verifyUserIsOnMyEMDataPage();
		 	logger.log(LogStatus.PASS, "Verified user is in the 'My EM Data' system folder");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in the 'My EM Data' system folder");
		 	
		 	filePage.clickMyDataOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Clicked on left menu 'My Data'");
		 	filePage.verifyUserIsOnMyDataPage();
		 	logger.log(LogStatus.PASS, "Verified user is in the 'My Data' system folder");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in the 'My Data' system folder");
		 	
		 	filePage.clickRecentsOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Clicked on left menu 'Recent'");
		 	filePage.verifyUserIsOnRecentsPage();
		 	logger.log(LogStatus.PASS, "Verified user is in the 'Recent Files' system location");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in the 'Recent Files' system location");
		 	
		 	filePage.clickTrashOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Clicked on left menu 'Trash'");
		 	trashPage.verifyUserIsOnTrashPage();
		 	logger.log(LogStatus.PASS, "Verified user is in the 'Trash' system folder");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in the 'Trash' system folder");	
		 	report.endTest(logger);
	 }
		
	 
	 
	 @Test (dependsOnMethods = {"verifyEMHeaderInAllSystemFolders"}, enabled = true)
	 	 public void verifyEMHeaderInSubFolders() throws Exception{
		 	logger = report.startTest("Verify EM Header in Sub folders");
		 	filePage.clickSharedDataOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Navigated to 'Shared Data' Folder ");
		 	fileSystemPage.createAndAccessSystemSubFolder();
		 	logger.log(LogStatus.INFO, "Created and navitagted to a sub folder");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in 'Shared Data - Sub Folder'");
		 	
		 	filePage.clickMyDataOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Navigated to 'My Data' Folder ");
		 	fileSystemPage.createAndAccessSystemSubFolder();
		 	logger.log(LogStatus.INFO, "Created and navigated to a sub folder");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in 'My Data - Sub Folder'");
		 	
		 	filePage.clickMyEMDataOnLeftMenu();
		 	logger.log(LogStatus.INFO, "Navigated to 'My EM Data' Folder ");
		 	fileSystemPage.accessEMDataQASubFolder();
		 	logger.log(LogStatus.INFO, "Navigated to 'My EM Data' Sub Folder (QA) ");
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	logger.log(LogStatus.PASS, "Verified EM Header contents in 'My Data - Sub Folder'");
		 	report.endTest(logger);
	 } 
	 
	 
	 
	 @Test (dependsOnMethods = {"verifyEMHeaderInSubFolders"}, enabled = true)
	 public void verifyUserProfileFunctionality() throws Exception{
		 	logger = report.startTest("Verify EM Header user profile functionality");
		 	emHeaderPage.clickOnUserProfileButton();
		 	logger.log(LogStatus.INFO, "Clicked on User Profile Icon");
		 	emHeaderPage.verifyUserProfileDropDownMenu();
		 	logger.log(LogStatus.PASS, "Verified user profile drop down menu contents");
		 	emHeaderPage.clickOnMyProfileButton();
		 	logger.log(LogStatus.INFO, "Clicked on 'My Profile' button");
		 	profilePage.verifyUserIsOnMyProfilePage();
		 	logger.log(LogStatus.PASS, "Verified user is redirected to the 'My Profile' page");
		 	emHeaderPage.clickOnUserProfileButton();
		 	logger.log(LogStatus.INFO, "Clicked on User Profile Icon");
		 	emHeaderPage.clickOnAccountButton();
		 	logger.log(LogStatus.INFO, "Clicked on 'Account' button");
		 	accountPage.verifyUserIsOnAccountPage();
		 	logger.log(LogStatus.PASS, "Verified user is redirected to the 'Account' page");
		 	emHeaderPage.clickOnUserProfileButton();
		 	logger.log(LogStatus.INFO, "Clicked on User Profile Icon");
		 	emHeaderPage.clickOnHelpButton();
		 	logger.log(LogStatus.INFO, "Clicked on 'Help' button");
		 	helpPage.verifyUserIsOnHelpPage();
		 	logger.log(LogStatus.PASS, "Verified user is redirected to the Help page");
		 	emHeaderPage.logout();
		 	logger.log(LogStatus.INFO, "Clicked on 'Logout' button");
		 	logInPage.verifyLogInPage();
		 	logger.log(LogStatus.PASS, "Verified user is logged out and redirected to the 'Login' page");
		 	report.endTest(logger);
	 }
	 	 
	 
	 
	 @AfterMethod
	 public void screenCap(ITestResult result) {
	        if(result.getStatus()==ITestResult.FAILURE) {

	            String screenshot_path= Constants.captureScreenshot(result.getName());
	            String imagePath = logger.addScreenCapture(screenshot_path);
	            logger.log(LogStatus.FAIL, "ContentsActionsAndRedirects", imagePath);
	            testCaseStatus = "FAILED";
	        }
	        report.endTest(logger);
	 }
	
	 
 
	 @AfterClass
	 public void tearDown() throws Exception {	
		 	logger = report.startTest("Start tear down");
	        Constants.clearBrowserCache();
	        
	        djangoPage.deleteUserProfile(CredentialsData.getEMHeaderUser());
	        logger.log(LogStatus.PASS, "EMHeader user has been deleted");	      
	        logInPage.verifyLoginAccessDenied(CredentialsData.getEMHeaderUser());
	        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");

	        Constants.clearBrowserCache();
	        //driver.quit();
	        logger.log(LogStatus.INFO, "Tear down Successful");
	        report.endTest(logger);
	        report.flush();
	        System.out.println(getClass().getName()+" - "+testCaseStatus);
	 }
	 
}
