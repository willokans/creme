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
import Page.classes.EditorPage;
import Page.classes.FilePage;
import Page.classes.FileSystemPage;
import Page.classes.ImportPage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 7/25/2016. Re-factored by alan.sheehy 26/01/2017
 * Test Automation for EMA-626
 */
public class ImportOptionsFunctionality {

    ExtentReports report;
    ExtentTest logger;
    LogInPage logInPage;
    DjangoPage djangoPage;
    FilePage filePage;
    ImportPage importPage;
    FileSystemPage fileSystemPage;
    EMHeaderPage emHeaderPage;
    EditorPage editorPage;
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
        fileSystemPage = new FileSystemPage(driver);
        editorPage = new EditorPage(driver);

    }




    @Test
    public void createActiveUserProfile() throws Exception {
    	logger = report.startTest("Create Import test user");
	 	djangoPage.createUserProfile(CredentialsData.getImportUser());
	 	logger.log(LogStatus.PASS, "Test user created");       
    }


    @Test (dependsOnMethods={"createActiveUserProfile"}, enabled = true)
    public void login () throws Exception {
    	logger = report.startTest("Login, navigate to import page via 'New + - Import Table' & verify 'Go Back' option");

        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");

        logInPage.fillInEmailTextBox(CredentialsData.getImportUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        emHeaderPage.verifyHeaderContentsAreDisplayed();
        logger.log(LogStatus.PASS, "Verify that the user has logged in");

    }
    
    
    @Test(dependsOnMethods={"login"}, enabled = true)
    public void importQACategoryTableWithQuoteAndComma() throws Exception {
        logger = report.startTest("Import a table with matching parameters (Quote & Comma) and verify successful import");

        // Click on the 'NEW + -> Import Table' option
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
        importPage.selectQACategoryTableWithQuoteAndComma();
        logger.log(LogStatus.INFO, "Selected a table using quote & comma parameters (Default)");
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
        logger.log(LogStatus.INFO, "Clicked 'Import' button");
        filePage.importStartedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Import started message displayed");

        // Open imported table & verify it is valid
        fileSystemPage.openQATestTable();
        logger.log(LogStatus.PASS, "Verified table has imported successfully, Clicked to open table");
        editorPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified table is valid in the editor");
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked 'Go Back' in the editor");
        
        // Delete table
        fileSystemPage.rightClickQATestTableAndTrashFile();
        logger.log(LogStatus.INFO, "Delete table from file system");
        
    }

    
    
    @Test(dependsOnMethods={"importQACategoryTableWithQuoteAndComma"}, enabled = true)
    public void importQACategoryTableWithQuoteAndSemicolon() throws Exception {
        logger = report.startTest("Import a table with matching parameters (Quote & Semicolon) and verify successful import");

        // Click on the 'NEW + -> Import Table' option
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
       	importPage.selectQACategoryTableWithQuoteAndSemicolon();
        logger.log(LogStatus.INFO, "Selected a table using Single quote & comma parameters");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected QA Model from the 'Choose Model Category' drop down");
        importPage.selectQACategoryFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected QA Category from the 'Choose Table Type' drop down");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a new table name");
        
        // Select quoted by 'Quote' & delimited by 'Semicolon' 
        importPage.clickQuoteRadioButton();
        logger.log(LogStatus.INFO, "Selected Quote('') radio button");
        importPage.clickSemicolonRadioButton();
        logger.log(LogStatus.INFO, "Selected ;(semicolon) radio button");           
        importPage.clickImportButton();
        logger.log(LogStatus.INFO, "Clicked 'Import' button");
        filePage.importStartedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Import started message displayed");

        // Open imported table & verify it is valid
        fileSystemPage.openQATestTable();
        logger.log(LogStatus.PASS, "Verified table has imported successfully, Clicked to open table");
        editorPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified table is valid in the editor");
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked 'Go Back' in the editor");
        
        // Delete table
        fileSystemPage.rightClickQATestTableAndTrashFile();
        logger.log(LogStatus.INFO, "Delete table from file system");
       
    }



    @Test(dependsOnMethods={"importQACategoryTableWithQuoteAndSemicolon"}, enabled = true)
    public void importQACategoryTableWithQuoteAndTab() throws Exception {
        logger = report.startTest("Import a table with matching parameters (Quote & Tab) and verify successful import");

        // Click on the 'NEW + -> Import Table' option
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
       	importPage.selectQACategoryTableWithQuoteAndTab();
        logger.log(LogStatus.INFO, "Selected a table using Single quote & comma parameters");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected QA Model from the 'Choose Model Category' drop down");
        importPage.selectQACategoryFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected QA Category from the 'Choose Table Type' drop down");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a new table name");
        
        
        // Select quoted by 'Quote' & delimited by 'Tab'   
        importPage.clickQuoteRadioButton();
        logger.log(LogStatus.INFO, "Selected Quote('') radio button");
        importPage.clickTabRadioButton();
        logger.log(LogStatus.INFO, "Selected (tab) radio button");         
        importPage.clickImportButton();
        logger.log(LogStatus.INFO, "Clicked 'Import' button");
        filePage.importStartedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Import started message displayed");
        
        // Open imported table & verify it is valid
        fileSystemPage.openQATestTable();
        logger.log(LogStatus.PASS, "Verified table has imported successfully, Clicked to open table");
        editorPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified table is valid in the editor");
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked 'Go Back' in the editor");
        
        // Delete table
        fileSystemPage.rightClickQATestTableAndTrashFile();
        logger.log(LogStatus.INFO, "Delete table from file system");
        
    }
    
    
    
    @Test(dependsOnMethods={"importQACategoryTableWithQuoteAndTab"}, enabled = true)
    public void importQADataTableWithSingleQuoteAndComma() throws Exception {
    	logger = report.startTest("Import a table with matching parameters (Single Quote & Comma) and verify successful import");
    	
    	// Click on the 'NEW + -> Import Table' option
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
        importPage.selectQADataTableWithSingleQuoteAndComma();
        logger.log(LogStatus.INFO, "Selected a table using Single quote & comma parameters");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected QA Model from the 'Choose Model Category' drop down");
        importPage.selectQADataFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected QA Data from the 'Choose Table Type' drop down");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a new table name");

        // Select quoted by 'Single quote' & delimited by 'Comma'
        importPage.clickSingleQuoteRadioButton();
        logger.log(LogStatus.INFO, "Selected Single quote(') radio button"); 
        importPage.clickCommaRadioButton();
        logger.log(LogStatus.INFO, "Selected ,(comma) radio button");                 
        importPage.clickImportButton();
        logger.log(LogStatus.INFO, "Clicked 'Import' button");
        filePage.importStartedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Import started message displayed");

        // Open imported table & verify it is valid
        fileSystemPage.openQATestTable();
        logger.log(LogStatus.PASS, "Verified table has imported successfully, Clicked to open table");
        editorPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified table is valid in the editor");
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked 'Go Back' in the editor");
        
        // Delete table
        fileSystemPage.rightClickQATestTableAndTrashFile();
        logger.log(LogStatus.INFO, "Delete table from file system");
    	 
    }



    @Test(dependsOnMethods={"importQADataTableWithSingleQuoteAndComma"}, enabled = true)
    public void importQADataTableWithSingleQuoteAndSemicolon() throws Exception {
    	logger = report.startTest("Import a table with matching parameters (Single Quote & Semicolon) and verify successful import");
    	
    	// Click on the 'NEW + -> Import Table' option
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
        importPage.selectQADataTableWithSingleQuoteAndSemicolon();
        logger.log(LogStatus.INFO, "Selected a table using Single quote & semicolon parameters");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected QA Model from the 'Choose Model Category' drop down");
        importPage.selectQADataFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected QA Data from the 'Choose Table Type' drop down");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a new table name");
    	
        // Select quoted by 'Single quote' & delimited by 'Comma'
        importPage.clickSingleQuoteRadioButton();
        logger.log(LogStatus.INFO, "Selected Single quote(') radio button"); 
        importPage.clickSemicolonRadioButton();
        logger.log(LogStatus.INFO, "Selected ;(semicolon) radio button");                 
        importPage.clickImportButton();
        logger.log(LogStatus.INFO, "Clicked 'Import' button");
        filePage.importStartedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Import started message displayed");
        
        // Open imported table & verify it is valid
        fileSystemPage.openQATestTable();
        logger.log(LogStatus.PASS, "Verified table has imported successfully, Clicked to open table");
        editorPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified table is valid in the editor");
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked 'Go Back' in the editor");
        
        // Delete table
        fileSystemPage.rightClickQATestTableAndTrashFile();
        logger.log(LogStatus.INFO, "Delete table from file system");
    
    }



    @Test(dependsOnMethods={"importQADataTableWithSingleQuoteAndSemicolon"}, enabled = true)
    public void importQADataTableWithSingleQuoteAndTab() throws Exception {
    	logger = report.startTest("Import a table with matching parameters (Single Quote & Tab) and verify successful import");
    	
    	// Click on the 'NEW + -> Import Table' option
        filePage.clickNewAndImportTable();
        logger.log(LogStatus.INFO, "Clicked on New+ & Import Table");
        importPage.clickBrowseFilesButton();
        logger.log(LogStatus.INFO, "Clicked 'Browse Files'");
        importPage.selectQADataTableWithSingleQuoteAndTab();
        logger.log(LogStatus.INFO, "Selected a table using Single quote & tab parameters");
        importPage.selectQAModelFromChooseModelCategory();
        logger.log(LogStatus.INFO, "Selected QA Model from the 'Choose Model Category' drop down");
        importPage.selectQADataFromChooseTableType();
        logger.log(LogStatus.INFO, "Selected QA Data from the 'Choose Table Type' drop down");
        importPage.fillInTableNameTextBox(Constants.tableName);
        logger.log(LogStatus.INFO, "Entered a new table name");
    	
        // Select quoted by 'Single quote' & delimited by 'Comma'
        importPage.clickSingleQuoteRadioButton();
        logger.log(LogStatus.INFO, "Selected Single quote(') radio button"); 
        importPage.clickTabRadioButton();
        logger.log(LogStatus.INFO, "Selected (tab) radio button");                 
        importPage.clickImportButton();
        logger.log(LogStatus.INFO, "Clicked 'Import' button");
        filePage.importStartedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Import started message displayed");
        
        // Open imported table & verify it is valid
        fileSystemPage.openQATestTable();
        logger.log(LogStatus.PASS, "Verified table has imported successfully, Clicked to open table");
        editorPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Verified table is valid in the editor");
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Clicked 'Go Back' in the editor");
        
        // Delete table
        fileSystemPage.rightClickQATestTableAndTrashFile();
        logger.log(LogStatus.INFO, "Delete table from file system");
    	
    }





    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "ImportOptionsFunctionality", imagePath);
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
