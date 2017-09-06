package Reg_Editor;

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
import Page.classes.EditorPage;
import Page.classes.ExportPage;
import Page.classes.FilePage;
import Page.classes.FileSystemPage;
import Page.classes.HomePage;
import Page.classes.ImportPage;
import Page.classes.LogInPage;
import Page.classes.MyEMDataPage;
import Page.classes.TrashPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExcelReader;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 11/28/2016.
 *  AT- 101
 * Test Automation for EMA- 1134
 */

public class ViewFunctionality {


    ExtentReports report;
    ExtentTest logger;

    LogInPage logInPage;
    HomePage homePage;
    DjangoPage djangoPage;
    FilePage filePage;
    ImportPage importPage;
    FileSystemPage fileSystemPage;
    TrashPage trashPage;
    ExportPage export;
    EditorPage editorPage;
    MyEMDataPage myDataPage;



    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\ActionExportPage.html");
        report = ExtentFactory.getInstance();


    }




    @BeforeMethod
    public void setup() throws Exception {

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logInPage = new LogInPage(driver);
        homePage = new HomePage(driver);
        djangoPage = new DjangoPage(driver);
        filePage = new FilePage(driver);
        importPage = new ImportPage(driver);
        fileSystemPage = new FileSystemPage(driver);
        trashPage = new TrashPage(driver);
        export = new ExportPage(driver);
        editorPage = new EditorPage(driver);
        myDataPage = new MyEMDataPage(driver);

    }



    @Test
    public void createActiveUserProfile() throws Exception {

    	logger = report.startTest("Create 'Editor' test user");
        djangoPage.createUserProfile(CredentialsData.getEditorUser());
        logger.log(LogStatus.PASS, "Test user created");

    }





    @Test (dependsOnMethods={"createActiveUserProfile"}, enabled = false)
    public void shouldLogInEM () throws Exception {

        logger = report.startTest("Successfully log in");


        // 0. Got to URL
        driver.get(ExcelReader.EM_URL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");

        // 1. Enter Valid email/Valid password
        logInPage.fillInEmailTextBox(ExcelReader.EM_EmailAddress());
        logInPage.fillInPasswordTextBox(ExcelReader.EM_Password());
        logger.log(LogStatus.INFO, "Successfully populate email/Valid password");

        // 2. Click Login button
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO
                , "Successfully Click on Login Button");

        // 3. Verify User is LogIn Successfully
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verify that User is on the Home Page");

        report.endTest(logger);

    }




    @Test (dependsOnMethods = {"shouldLogInEM"}, enabled = false)
    public void moveTableFromNHANESDataSetToMyDataFolder() throws Exception {


        logger = report.startTest("Successfully copy Table to My Data Folder");

        //0.    Navigate to NHANES folder
        myDataPage.clickMainPageMyEMDataLink();
        myDataPage.verifyMyEMDataPage();
        myDataPage.openNHANESTwoDayFolder();
        logger.log(LogStatus.INFO, "Successfully navigate and verify to data Set folder");

        //1.    Open Data Set folder and copy a Table to my data folder
        myDataPage.open2012DataSet();
        myDataPage.verify2012DataSetPage();
        myDataPage.verifyYouHaveNoFilesToListIsNOTDisplayed();
        logger.log(LogStatus.INFO, "Successfully navigate to 2012 Dataset");

        //2.    Copy Nutrient table to my data folder
        fileSystemPage.rightClickDatasetNutrientTable();
        fileSystemPage.clickOnCopyToFromRightClickMenu();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyStartedToCopy1FileIsDisplayed();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully copy table to my data folder");

        //3.    verify table is displayed in my data folder
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyNutrientTableIsDisplayed();
        logger.log(LogStatus.PASS, "Copied Table is displayed in Folder");

        report.endTest(logger);

    }




    @Test (dependsOnMethods = {"moveTableFromNHANESDataSetToMyDataFolder"}, enabled = false)
    public void viewerFunctionalityTest() throws Exception {

        logger = report.startTest("Successfully test viewer functionality");

        //0.    open Nutrient table and verify user is on editor page
        fileSystemPage.openDataSetNutrientTable();
        editorPage.verifyTablePageFileMainMenuIsDisplayed();
        editorPage.verifyTablePageEditMainMenuIsDisplayed();
        editorPage.verifyTablePageViewMainMenuIsDisplayed();
        editorPage.verifyTablePageInsertMainMenuIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully navigate to Nutrient table");

        //1.    Check the editor viewer
        editorPage.verifyFirstCellIsSelectedByDefault();
        editorPage.verifyEditorTableNameIsDisplayed();
        editorPage.verifyEditorRowCountIsDisplayed();
        editorPage.verifyEditorNutrientCodeFieldNameIsDisplayed();
        editorPage.verifyEditorFoodCodeFieldNameIsDisplayed();
        editorPage.verifyEditorPresenceProbabilityFieldNameIsDisplayed();
        editorPage.verifyEditorConcentrationFieldNameIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully validate editor viewer");

        //2.    Scroll down and then up and verify field is displayed
        editorPage.clickOnCell_Row_24_Col_0();
        editorPage.verifyEditorNutrientCodeFieldNameIsDisplayed();
        editorPage.verifyEditorFoodCodeFieldNameIsDisplayed();
        editorPage.verifyEditorPresenceProbabilityFieldNameIsDisplayed();
        editorPage.verifyEditorConcentrationFieldNameIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify field name is still displayed");

        //3.    Navigate away and back to the table
        editorPage.clickGoBackButton();
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.INFO, "Successfully navigate back to My Data page");


        //0.   Click on LogOut
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Successfully log Out");

        //1.   Verify User is Logged Out
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Successfully Logged Out");

        
        report.endTest(logger);

    }




    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Util.Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "ViewFunctionality", imagePath);

        }
        report.endTest(logger);
    }




    @AfterClass
    public void tearDown() throws Exception {

    	logger = report.startTest("Start tear down");
        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getEditorUser());
        logger.log(LogStatus.PASS, "Editor user has been deleted");	      
        logInPage.verifyLoginAccessDenied(CredentialsData.getEditorUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");

      //driver.close();
        Constants.clearBrowserCache();
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }

}
