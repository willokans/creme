package Reg_AccountAccess;

/* AT-10
* Test Automation for EMA-640 - Refactored by alan.sheehy 05/01/2016
* Required User Profile Creation completed first
* DEPENDANT ON CREATE USER PROFILE CLASS
 */

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Page.classes.DjangoPage;
import Page.classes.HomePage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

public class LogInPageValidation {

	ExtentReports report;
	ExtentTest logger;
	LogInPage logInPage;
	HomePage homePage;
	DjangoPage djangoPage;
	String testCaseStatus = "PASSED";

	private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

	@BeforeSuite
    public void configLogging() throws IOException{
    	System.out.println("Creating "+CredentialsData.getAccAccessUser()+" user profile on: "+CredentialsData.getApiURL());
    	System.out.println("Running Account Access tests on: "+CredentialsData.getBaseURL());
    }
	
	@BeforeClass
	public void beforeClassSetup() throws Exception, IOException {
		//report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\LogInPageValidation.html");
        report = ExtentFactory.getInstance();

	}




	@BeforeMethod
	public void setup() throws Exception {

		// Maximize the broswer's window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		logInPage = new LogInPage(driver);
		homePage = new HomePage(driver);
		djangoPage = new DjangoPage(driver);

	}





	@Test
	public void createActiveUserProfile() throws Exception {
		logger = report.startTest("Create 'Account Access' test user");
	    djangoPage.createUserProfile(CredentialsData.getAccAccessUser());
	    logger.log(LogStatus.PASS, "Test user created");
	}


	@Test (dependsOnMethods = {"createActiveUserProfile"}, enabled = true)
	public void pageValidationAndErrorVerification() throws Exception {
		logger = report.startTest("User Successfully Sign in");

		driver.get(CredentialsData.getBaseURL());
		logger.log(LogStatus.INFO, "Navigated to Expert Model URL");
		
		// Ensure that the expected login page links, logo's & input fields are displayed
		logInPage.emLogoIsDisplayed();
		logger.log(LogStatus.PASS, "Expert Model Logo is Displayed");
		logInPage.emailTextBoxIsDisplayed();
		logger.log(LogStatus.PASS, "Email TextBox is Displayed");
		logInPage.passwordTextBoxIsDisplayed();
		logger.log(LogStatus.PASS, "Password TextBox is Displayed");
		logInPage.LoginButtonIsDisplayed();
		logger.log(LogStatus.PASS, "LogIn Button is Displayed");
		logInPage.lostPasswordLinkDisplayed();
		logger.log(LogStatus.PASS, "Lost Password Link is Displayed");
					
		// Verify default states for input fields
		logInPage.emailTextBoxIsBlankByDefault();
		logger.log(LogStatus.PASS, "Email TextBox is Blank by default");
		logInPage.passwordTextBoxIsBlankByDefault();
		logger.log(LogStatus.PASS, "Password TextBox is Blank by default");
		
		// Enter a valid email/invalid password & attempt to login
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
		logInPage.fillInPasswordTextBox("invalidPassword");
		logger.log(LogStatus.INFO, "Entered a valid email & invalid password");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");
		logInPage.InvalidLoginErrorMessage();
		logger.log(LogStatus.PASS, "Verified access denied & correct error message is displayed");
		
		// Clear email email and capture error message [blank email]
		logInPage.clearEmailTextBox();
		logger.log(LogStatus.INFO, "Cleared email field & left it blank");
		logInPage.nullEmailErrorMessageIsDisplayed();
		logger.log(LogStatus.PASS, "Verified 'Email is required.' error message is displayed");
		
		// Clear email password and capture error message [blank password]
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
		logger.log(LogStatus.INFO, "Repopulated the email field");
		logInPage.clearPasswordTextBox();
		logger.log(LogStatus.INFO, "Cleared password field & left it blank");
		logInPage.nullPasswordError();
		logger.log(LogStatus.PASS, "Verified 'Password is required.' error message is displayed");
		
		// Clear both email & password fields & capture the error messages [blank email & password]
		logInPage.clearEmailTextBox();
		logger.log(LogStatus.INFO, "Cleared email field & left it blank");
		logInPage.clearPasswordTextBox();
		logger.log(LogStatus.INFO, "Cleared password field & left it blank");
		logInPage.nullEmailErrorMessageIsDisplayed();
		logger.log(LogStatus.PASS, "Verified 'Email is required.' error message is displayed");
		logInPage.nullPasswordError();
		logger.log(LogStatus.PASS, "Verified 'Password is required.' error message is displayed");
	
		// Enter an invalid email/invalid password & attempt to login
		logInPage.fillInEmailTextBox("invalidEmail@info.com");
		logInPage.fillInPasswordTextBox("invalidPassword");
		logger.log(LogStatus.INFO, "Entered an invalid email & invalid password");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");
		logInPage.InvalidLoginErrorMessage();
		logger.log(LogStatus.PASS, "Verified access denied & correct error message is displayed");
		
		// Enter an invalid email/valid password & attempt to login
		logInPage.fillInEmailTextBox("invalidEmail@info.com");
		logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
		logger.log(LogStatus.INFO, "Entered an invalid email & valid password");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");
		logInPage.InvalidLoginErrorMessage();
		logger.log(LogStatus.PASS, "Verified access denied & correct error message is displayed");
		
		// Enter an invalid email & a password that fails to meet the minimum length requirements, attempt to login
		logInPage.fillInEmailTextBox("invalidEmail@info.com");
		logInPage.fillInPasswordTextBox("Pswd#12");
		logger.log(LogStatus.INFO, "Entered an invalid email & a password that doesn't meet the minimum length requirements");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");
		logInPage.lessThanEightCharactersError();
		logger.log(LogStatus.PASS, "Verified access denied & correct error message is displayed");

		// Enter Valid email/Valid password & attempt to login
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
		logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
		logger.log(LogStatus.INFO, "Entered a valid email & valid password");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");
		homePage.verifyHomePage();
		logger.log(LogStatus.PASS, "Verified access is granted & the user is redirected to the home page");

	}


	@Test(dependsOnMethods = {"pageValidationAndErrorVerification"}, enabled = true)
	public void logOut() throws Exception {
		logger = report.startTest("Logout & return to the login page");

		homePage.loggingOut();
		logger.log(LogStatus.INFO, "Clicked on logout");
		logInPage.verifyLogInPage();
		logger.log(LogStatus.PASS, "Verified logout was successful");

        Constants.clearBrowserCache();
	}


	@Test(dependsOnMethods = {"logOut"}, enabled = true)
	public void emailAuthenticationErrorMessageTest() throws Exception {
		logger = report.startTest("Verify email authentication error message for an inactive user");
		
		// Deactivate user profile
		djangoPage.toggleUserActiveStatus(CredentialsData.getAccAccessUser());
		logger.log(LogStatus.INFO, "Deactivated user: "+CredentialsData.getAccAccessUser()+" by setting is active to false (clear)");

		// Go to EM URL & attempt to login
		driver.get(CredentialsData.getBaseURL());
		logger.log(LogStatus.INFO, "Navigated to Expert Models URL");
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
		logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
		logger.log(LogStatus.INFO, "Entered credentials for deactivated user");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");

        // Verify display of error message 'This email has not yet been verified. A new activation link has been sent to your email address. '
        logInPage.verifyNonAuthenticatedLoginErrorMessage();
        logger.log(LogStatus.PASS, "Verified Non Authenticated email error message is displayed");

        // Reactivate user profile
        djangoPage.toggleUserActiveStatus(CredentialsData.getAccAccessUser());
        logger.log(LogStatus.INFO, "Reactivated user: "+CredentialsData.getAccAccessUser()+" by setting is active to true (selected)");
       
        // Go to EM URL & attempt to login
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");
        logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Successfully populate email/Valid password");
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Successfully Click on Login Button");

        // Verify user can login successfully
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verified the user is on the Home Page");

        // LogOut
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Successfully log Out");
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Successfully Logged Out");

    }
	
	
	
	@DataProvider(name = "caseVariations")
	public static Object[][] caseVariations(){
		return new Object [][]{{"QA@CREMELABS.COM"},{"QA@cremelabs.com"},{"qa@CREMELABS.COM"}};
	}

	@Test(dependsOnMethods = {"emailAuthenticationErrorMessageTest"}, dataProvider = "caseVariations", enabled = true)
	public void verifyCaseVariationsOfEmailAddressesAreAccepted(String emailCaseVariation) throws Exception {
		logger = report.startTest("Verify email field is not case sensitive: "+emailCaseVariation);

		// Enter the data provider (caseVariations) email case variations & attempt to login
		logInPage.fillInEmailTextBox(emailCaseVariation);
		logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
		logger.log(LogStatus.INFO, "Entered email case variation: "+emailCaseVariation+" + a valid password");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");
		homePage.verifyHomePage();
		logger.log(LogStatus.PASS, "Verified access is granted & the user is on the Home Page");

		// Log out
		homePage.loggingOut();
		logger.log(LogStatus.INFO, "Logout initiated");
		logInPage.verifyLogInPage();
		logger.log(LogStatus.PASS, "Verified Logout was successful");

	}

	
	
	@DataProvider(name = "pwdCaseVariations")
	public static Object[][] pwdCaseVariations(){
		return new Object [][]{{"CREME#999"},{"creMe#999"}};
	}

	@Test(dependsOnMethods = {"verifyCaseVariationsOfEmailAddressesAreAccepted"}, dataProvider = "pwdCaseVariations", enabled = true)
	public void verifyVariationsOfValidCaseSensitivePasswordsAreNotAccepted(String pwdCaseVariation) throws Exception {
		logger = report.startTest("Verify password field is case sensitive & access is denied for: "+pwdCaseVariation);

		// Enter a case variation of a valid case Sensitive password (+ valid password)
		logInPage.fillInEmailTextBox(CredentialsData.getAccAccessUser());
		logInPage.fillInPasswordTextBox(pwdCaseVariation);
		logger.log(LogStatus.INFO, "Entered password case variation: "+pwdCaseVariation+" + a valid password");
		logInPage.clickLogInButton();
		logger.log(LogStatus.INFO, "Clicked on Login Button");

		// Verify access denied & error message "Unable to login with provided credentials" is displayed
		logInPage.InvalidLoginErrorMessage();
		logger.log(LogStatus.PASS, "Verified access denied & 'Unable to login with....' error Message is displayed");
		Constants.refreshPage();

	}

	
	
	@DataProvider(name = "incorrectEmailSyntax")
	public static Object[][] incorrectEmailSyntax(){
		return new Object [][]{{"qa@cremelabs"},{"@cremelabs.com"},{"cremelabs.com"}};
	}

	@Test(dependsOnMethods = {"verifyVariationsOfValidCaseSensitivePasswordsAreNotAccepted"}, dataProvider = "incorrectEmailSyntax", enabled = true)
	public void verifyIncorrectEmailSyntaxError(String incorrectEmailSyntax) throws Exception {
		logger = report.startTest("Verify access denied for incorrect email systax: "+incorrectEmailSyntax);

        // Enter incorrect email syntax (with a valid password) & attempt to login 
        logInPage.fillInEmailTextBox(incorrectEmailSyntax);
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered incorrect email syntax: "+incorrectEmailSyntax+" + valid password");
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");

        // Verify access denied & error message "Unable to login with provided credentials" is displayed
        logInPage.InvalidLoginErrorMessage();
        logger.log(LogStatus.PASS, "Verified access denied & 'Unable to login with....' error Message is displayed");
        Constants.refreshPage();
	}





	 @AfterMethod
	 public void screenCap(ITestResult result) {
		 if(result.getStatus()== ITestResult.FAILURE) {

			 String screenshot_path= Constants.captureScreenshot(result.getName());
			 String imagePath = logger.addScreenCapture(screenshot_path);
			 logger.log(LogStatus.FAIL, "LogInPageValidation", imagePath);
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