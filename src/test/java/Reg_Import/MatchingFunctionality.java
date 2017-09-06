package Reg_Import;

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
import Page.classes.FilePage;
import Page.classes.ImportPage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 7/21/2016. Re-factored by alan.sheehy 27/01/2017
 * AT-27
 * Test Automation for EMA-625
 */

public class MatchingFunctionality {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    DjangoPage djangoPage;
    FilePage filePage;
    EMHeaderPage emHeaderPage;
    ImportPage importPage;
    String testCaseStatus = "PASSED";

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();




    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {
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
    public void login () throws Exception {
        logger = report.startTest("Verify login");

        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");

        logInPage.fillInEmailTextBox(CredentialsData.getImportUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        emHeaderPage.verifyHeaderContentsAreDisplayed();
        logger.log(LogStatus.PASS, "Verify that User is on the Home Page");

        report.endTest(logger);

    }
    
   

    @Test(dependsOnMethods={"login"}, enabled = true)
    public void importAndVerifyMatchingPageContentsAndRequiredFields() throws Exception {
        logger = report.startTest("Verify matching required functionality(QA Table type) & post matching import functionality");

        // Select a file that *does* require field matching, import and verify File is imported successfully
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
        importPage.selectIncompleteQACategoryTableForImport();
        logger.log(LogStatus.INFO, "Selected an incomplete category table that requires field matching");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected QA Model from the 'Choose Model Category' drop down");
        importPage.selectQACategoryFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected QA Category from the 'Choose Table Type' drop down");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a new table name");
        
        // Select quoted by 'Quote' & delimited by 'Comma' 
        importPage.clickQuoteRadioButton();
        logger.log(LogStatus.INFO, "Selected Quote('') radio button");
        importPage.clickCommaRadioButton();
        logger.log(LogStatus.INFO, "Selected ,(comma) radio button");  
        importPage.clickImportButton();
        logger.log(LogStatus.INFO, "Clicked on 'Import' button");
        importPage.verifyMatchingPageContentsAndStatus();
        logger.log(LogStatus.PASS, "Verified  page matching error & EM Data/CSV Importing columns are displayed");
        logger.log(LogStatus.PASS, "Verified  button statuses (Import: displayed & disabled); (Go Back: displayed & enabled)");
        
        importPage.verifyRequiredAndOrMatchingStatus(Constants.requiredLabel);
        logger.log(LogStatus.PASS, "Verified all fields are labelled as: "+Constants.requiredLabel);
    }
      
    
    
    @Test(dependsOnMethods = "importAndVerifyMatchingPageContentsAndRequiredFields", enabled = true)
    public void selectAndMatchIncluded() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Included' field");
    	
    	importPage.verifyIncludedLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified included field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchIncludedFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_included' from the dropdown list of options");
    	importPage.verifyIncludedLabelStatus(Constants.matchedLabel);
    	logger.log(LogStatus.PASS, "Verified included field is matched & labelled 'Matched'");
    	
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchIncluded", enabled = true)
    public void selectAndMatchCategory() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Category' field, verify also 'Included' is unavailable");
    	
    	importPage.verifyCategoryLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified category field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchCategoryFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_category' from the dropdown list of options");
    	importPage.verifyCategoryLabelStatus(Constants.matchedLabel); 	
    	logger.log(LogStatus.PASS, "Verified category field is matched & labelled 'Matched'");
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchCategory", enabled = true)
    public void selectAndMatchItem() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Item' field, verify also 'Category' is unavailable");
    	
    	importPage.verifyItemLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified item field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchItemFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_item' from the dropdown list of options");
    	importPage.verifyItemLabelStatus(Constants.matchedLabel); 	
    	logger.log(LogStatus.PASS, "Verified item field is matched & labelled 'Matched'");
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchItem", enabled = true)
    public void selectAndMatchDataPoints() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Data Points' field, verify also 'Item' is unavailable");
    	
    	importPage.verifyDataPointsLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified data points field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchDataPointsFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_data_points' from the dropdown list of options");
    	importPage.verifyDataPointsLabelStatus(Constants.matchedLabel); 	
    	logger.log(LogStatus.PASS, "Verified data points field is matched & labelled 'Matched'");
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchDataPoints", enabled = true)
    public void selectAndMatchComment() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Comment' field, verify also 'Data Points' is unavailable");
    	
    	importPage.verifyCommentLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified comment field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchCommentFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_comment' from the dropdown list of options");
    	importPage.verifyCommentLabelStatus(Constants.matchedLabel); 	
    	logger.log(LogStatus.PASS, "Verified comment field is matched & labelled 'Matched'");
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchComment", enabled = true)
    public void selectAndMatchNumericTest() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Numeric Test' field, verify also 'Comment' is unavailable");
    	
    	importPage.verifyNumericTestLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified numeric test field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchNumericTestFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_numeric_test' from the dropdown list of options");
    	importPage.verifyNumericTestLabelStatus(Constants.matchedLabel); 	
    	logger.log(LogStatus.PASS, "Verified numeric test field is matched & labelled 'Matched'");
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchNumericTest", enabled = true)
    public void selectAndMatchNumericDistTest() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Numeric Distribution Test' field, verify also 'Numeric Test' is unavailable");
    	
    	importPage.verifyNumericDistTestLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified numeric dist test field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchNumericDistTestFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_numeric_distribution_test' from the dropdown list of options");
    	importPage.verifyNumericDistTestLabelStatus(Constants.matchedLabel); 	
    	logger.log(LogStatus.PASS, "Verified numeric dist test field is matched & labelled 'Matched'");
    	importPage.verifyImportButtonIsDisplayedAndNotEnabled();
    	logger.log(LogStatus.PASS, "Verified the import is displayed but not available for use");
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchNumericDistTest", enabled = true)
    public void selectAndMatchIntegerTest() throws Exception{
    	logger = report.startTest("Verify select & match process for 'Integer Test' field, verify also 'Numeric Distribution Test' is unavailable");
    	
    	importPage.verifyIntegerTestLabelStatus(Constants.requiredLabel);
    	logger.log(LogStatus.PASS, "Verified integer test field is unmatched & labelled 'Required'");
    	importPage.selectAndMatchIntegerTestFromDropDown();
    	logger.log(LogStatus.INFO, "Selected 'Test_numeric_distribution_test' from the dropdown list of options");
    	importPage.verifyIntegerTestLabelStatus(Constants.matchedLabel); 	
    	logger.log(LogStatus.PASS, "Verified integer test field is matched & labelled 'Matched'");  	
    }
    
    
    @Test(dependsOnMethods = "selectAndMatchIntegerTest", enabled = true)
    public void verifyAllFieldsAreMatchedAndImportIsEnabled() throws Exception{
    	logger = report.startTest("Verify all fields are labelled 'Matched' & the 'Import' button is available for selection");
    	
    	importPage.verifyRequiredAndOrMatchingStatus(Constants.matchedLabel);
        logger.log(LogStatus.PASS, "Verified all fields are labelled as: "+Constants.matchedLabel );
        
        importPage.verifyImportButtonIsClickable();
        logger.log(LogStatus.PASS, "Import Button is now enabled & available for selection");
    	
    }
    
    
    @Test(dependsOnMethods = "verifyAllFieldsAreMatchedAndImportIsEnabled", enabled = true)
    public void verifyImportCanBeSubmitAfterMatching() throws Exception{
    	logger = report.startTest("Verify import can be submit after matching & the user is returned to My Data");
    	
    	importPage.clickImportButton();
    	logger.log(LogStatus.INFO, "Clicked on Import button");
    	
    	filePage.importStartedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Import started message displayed");
        
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.PASS, "Verified user is returned to their 'My Data' page");
  	
    }
    
    
    @Test(dependsOnMethods = "verifyImportCanBeSubmitAfterMatching", enabled = true)
    public void verifyGoBackFunctionality() throws Exception{
    	logger = report.startTest("Verify 'Go Back' on matching page returns users to the import page");
    	
    	filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
        importPage.selectIncompleteQACategoryTableForImport();
        logger.log(LogStatus.INFO, "Selected an incomplete category table that requires field matching");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected QA Model from the 'Choose Model Category' drop down");
        importPage.selectQACategoryFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected QA Category from the 'Choose Table Type' drop down");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a new table name");
         
        // Select quoted by 'Quote' & delimited by 'Comma' 
        importPage.clickQuoteRadioButton();
        logger.log(LogStatus.INFO, "Selected Quote('') radio button");
        importPage.clickCommaRadioButton();
        logger.log(LogStatus.INFO, "Selected ,(comma) radio button");  
        importPage.clickImportButton();
        logger.log(LogStatus.INFO, "Clicked on 'Import' button");
        importPage.verifyMatchingPageContentsAndStatus();
        logger.log(LogStatus.PASS, "Verified  user is on the import matching page");
         
        importPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked on 'Go Back' button");
        importPage.verifyChooseNewAndRemoveAreDisplayedAndEnabled();
        logger.log(LogStatus.PASS, "Verified user has been returned to the 'Import Page'");
        
    }
   



    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "MatchingFunctionality", imagePath);
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
        driver.quit();
        logger.log(LogStatus.INFO, "Tear down Successful");
        report.endTest(logger);
        report.flush();
        System.out.println(getClass().getName()+" - "+testCaseStatus);
    }

}
