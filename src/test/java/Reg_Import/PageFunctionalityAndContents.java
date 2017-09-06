package Reg_Import;

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
import Page.classes.FilePage;
import Page.classes.ImportPage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okanumeh on 7/12/2016. Re-factored by alan.sheehy 24/01/2017
 * AT-26
 * Test Automation for EMA-624
 */
public class PageFunctionalityAndContents {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    DjangoPage djangoPage;
    FilePage filePage;
    EMHeaderPage emHeaderPage;
    ImportPage importPage;
    String testCaseStatus = "PASSED";


    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    @BeforeSuite
    public void configLogging() throws IOException{
    	System.out.println("Creating "+CredentialsData.getImportUser()+" user profile on: "+CredentialsData.getApiURL());
    	System.out.println("Running Import tests on: "+CredentialsData.getBaseURL());
    }

    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {
        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\PageFunctionalityAndContents.html");
        report = ExtentFactory.getInstance();

    }





    @BeforeMethod
    public void setup() throws Exception {

        // Maximize the broswer's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logInPage = new LogInPage(driver);
        djangoPage = new DjangoPage(driver);
        filePage = new FilePage(driver);
        emHeaderPage = new EMHeaderPage(driver);
        importPage = new ImportPage(driver);

    }




    @Test
    public void createActiveUserProfile() throws Exception {
    	logger = report.startTest("Create Import test user");
	 	djangoPage.createUserProfile(CredentialsData.getImportUser());
	 	logger.log(LogStatus.PASS, "Test user created");
    }


    @Test (dependsOnMethods={"createActiveUserProfile"}, enabled = true)
    public void login() throws Exception {
        logger = report.startTest("Login, navigate to import page via 'New + - Import Table' & verify 'Go Back' option");

        // Login
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");

        logInPage.fillInEmailTextBox(CredentialsData.getImportUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        emHeaderPage.verifyHeaderContentsAreDisplayed();
        logger.log(LogStatus.INFO, "Verified user is on the Home Page");
        
    }         
    

    @Test (dependsOnMethods = {"login"}, enabled = true)
    public void verifyImportPageContentsAndDefaultStatus() throws Exception{
    	logger = report.startTest("Verify page contents & default selections");
    	
    	filePage.clickNewAndImportTable();
    	logger.log(LogStatus.INFO, "Clicked 'New +' & Import Table");
    	importPage.verifyImportPageContentsAreDisplayed();
    	logger.log(LogStatus.PASS, "Verified user is on the Import Page & verified page contents");
    	
    	importPage.verifyImportOptionsDefaultStatuses();
    	logger.log(LogStatus.PASS, "Verified import page default state (option statuses) is as expected");
     	
    }
    
       
    @Test (dependsOnMethods = {"verifyImportPageContentsAndDefaultStatus"}, enabled = true)
    public void toggleDropdownsAndRadioButtonAndVerifyNewState() throws Exception{
    	logger = report.startTest("Verify page options can be changed and successfully retained");
    	
    	importPage.selectQAModelFromChooseModelCategory();
    	logger.log(LogStatus.INFO, "Selected 'QA Model' from choose model category dropdown");
    	importPage.selectQADataFromChooseTableType();
    	logger.log(LogStatus.INFO, "Selected 'QA Data' from choose table type dropdown");
    	importPage.clickSingleQuoteRadioButton();
    	logger.log(LogStatus.INFO, "Clicked Single quote(') radio button");
    	importPage.clickSemicolonRadioButton();
    	logger.log(LogStatus.INFO, "Clicked ;(semicolon) radio button");
    	importPage.verifyImportOptionsStatusesAfterFirstToggle();
    	logger.log(LogStatus.PASS, "Verified selections have been retained & only one option per section can be made");
    	
    	importPage.selectQACategoryFromChooseTableType();
    	logger.log(LogStatus.INFO, "Selected 'QA Category' from choose table type dropdown");
    	importPage.clickQuoteRadioButton();
    	logger.log(LogStatus.INFO, "Clicked Quote('') radio button");
    	importPage.clickTabRadioButton();
    	logger.log(LogStatus.INFO, "Clicked (tab) radio button");
    	importPage.verifyImportOptionsStatusesAfterSecondToggle();
    	logger.log(LogStatus.PASS, "Verified selections have been retained & only one option per section can be made");
    	
    	importPage.fillInTableNameTextBox(Constants.tableName);
    	logger.log(LogStatus.INFO, "Entered a table name");
    	importPage.clickCommaRadioButton();
    	logger.log(LogStatus.INFO, "Clicked ,(comma) radio button");
    	importPage.verifyImportOptionsStatusesAfterThirdToggle();
    	logger.log(LogStatus.PASS, "Verified selections have been retained & only one option per section can be made");
    
    }
    
    
    @Test (dependsOnMethods = {"toggleDropdownsAndRadioButtonAndVerifyNewState"}, enabled = true)
    public void verifyGoBackActionAndReturnToImport() throws Exception{
    	logger = report.startTest("Verify 'Go Back' button action");
    	     
        importPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked on Go Back button");
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.PASS, "Verified the import page closed & the user has been redirected to the My Data page");
    	
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Navigated to the Import Page via NEW+ - Import Table option");
    }
    
    
    @Test (dependsOnMethods = {"verifyGoBackActionAndReturnToImport"}, enabled = true)
    public void verifyRequiredFieldsAndSelectATable() throws Exception{
    	logger = report.startTest("Verify table selection/removal & Import button availability");
    	
    	Constants.refreshPage();
    	importPage.clickBrowseFilesButton();
    	logger.log(LogStatus.INFO, "Click 'Browse Files' button");
    	importPage.selectIncompleteQACategoryTableForImport();
        logger.log(LogStatus.INFO, "Verifed table has been selected & remove button is displayed");
        importPage.verifyChooseNewAndRemoveAreDisplayedAndEnabled();
        logger.log(LogStatus.PASS, "Verified 'Choose New' & 'Remove' button are displayed & available for selection");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected 'QA Model' from choose model category dropdown");
        importPage.clickCommaRadioButton();
        logger.log(LogStatus.INFO, "Clicked ,(comma) radio button");
        importPage.clickQuoteRadioButton();
        logger.log(LogStatus.INFO, "Clicked Quote('') radio button");
        importPage.verifyImportButtonIsDisplayedAndNotEnabled();
        logger.log(LogStatus.PASS, "Verifiy import is unavailable as all fields are incomplete");
        
        importPage.selectQACategoryFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected 'QA Category' from choose table type dropdown");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a table name");
        
        importPage.verifyImportButtonIsClickable();
        logger.log(LogStatus.PASS, "Verified all required fields have been completee & the import button is now available for selection");
         	
    }     
   

    @Test(dependsOnMethods={"verifyRequiredFieldsAndSelectATable"}, enabled = true)
    public void verifyReselectOptionsAndImportAvailability() throws Exception {
        logger = report.startTest("Verify 'Choose New' & 'Remove' options are available post table selection and exercise both");

        importPage.verifyChooseNewAndRemoveAreDisplayedAndEnabled();
        logger.log(LogStatus.PASS, "Verified 'Choose New' & 'Remove' button are displayed & available for selection");
        importPage.clickRemoveButton();
        logger.log(LogStatus.INFO, "Clicked on 'Remove'");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.PASS, "Verified table has been removed, Clicked on the available 'Browse Files' option");
        importPage.selectIncompleteQACategoryTableForImport();
        logger.log(LogStatus.INFO, "Selected a table to import");
        importPage.clickChooseNewButton();
        logger.log(LogStatus.INFO, "Clicked on 'Choose New'");
        importPage.selectQACategoryTableWithQuoteAndComma();
        logger.log(LogStatus.INFO, "Selected a different table");
        importPage.verifyChooseNewAndRemoveAreDisplayedAndEnabled();
        logger.log(LogStatus.PASS, "Verified 'Choose New' & 'Remove' button are displayed & available for selection");
        importPage.verifyImportButtonIsClickable();
        logger.log(LogStatus.PASS, "Verified 'Import' button is available for selection");
    }



    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "PageFunctionalityAndContents", imagePath);
            testCaseStatus = "FAILED";
        }
        report.endTest(logger);
    }




    @AfterClass
    public void tearDown() throws Exception {
    	logger = report.startTest("Start tear down");
        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getImportUser());
        logger.log(LogStatus.PASS, "Import user has been deleted");	      
        logInPage.verifyLoginAccessDenied(CredentialsData.getImportUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");

        Constants.clearBrowserCache();
        //driver.quit();
        logger.log(LogStatus.INFO, "Tear down Successful");
        report.endTest(logger);
        report.flush();
        System.out.println(getClass().getName()+" - "+testCaseStatus);
    }

}