package Reg_FileSystem;

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
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 9/5/2016.
 * AT-42
 Test Automation for EMA-621
 */
public class CookieTrailFunctionality {


    ExtentReports report;
    ExtentTest logger;

    LogInPage logInPage;
    HomePage homePage;
    DjangoPage djangoPage;
    FilePage filePage;
    ImportPage importPage;
    FileSystemPage fileSystemPage;
    TrashPage trashPage;
    MyEMDataPage myDataPage;
    EditorPage editorPage;


    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\CookieTrailFunctionality.html");
        report = ExtentFactory.getInstance();


    }


    @BeforeMethod
    public void setup() throws Exception {

        // Maximize the browser window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logInPage = new LogInPage(driver);
        homePage = new HomePage(driver);
        djangoPage = new DjangoPage(driver);
        filePage = new FilePage(driver);
        importPage = new ImportPage(driver);
        fileSystemPage = new FileSystemPage(driver);
        trashPage = new TrashPage(driver);
        myDataPage = new MyEMDataPage(driver);
        editorPage = new EditorPage(driver);

    }


    @Test
    public void createActiveUserProfile() throws Exception {

    	logger = report.startTest("Create 'File System' test user");
        djangoPage.createUserProfile(CredentialsData.getFileSystemUser());
        logger.log(LogStatus.PASS, "Test user created");
    }


    @Test(dependsOnMethods = {"createActiveUserProfile"}, enabled = false)
    public void shouldLogInEM() throws Exception {

        logger = report.startTest("Successfully log in");


        // 0. Got to URL
        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");

        // 1. Enter Valid email/Valid password
        logInPage.fillInEmailTextBox(CredentialsData.getFileSystemUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
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


    @Test(dependsOnMethods = {"shouldLogInEM"}, enabled = false)
    public void shouldCreateFoldersFilesAndAssessment() throws Exception {

        logger = report.startTest("Successfully create Files and Folder");

        //0.     Create New Test Folder 1
        Util.Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder A");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder A");

        //1.    Create New Test Folder 3
        Util.Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder C");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderCIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder C");

        //2.    Import Complete Subject Table -> replace with QA Category/Data
        /*Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        importPage.upLoadSubjectCSVTableTypeFile();
        filePage.clickSubjectRadioButton();
        importPage.fillInTableNameTextBox("Complete Subject Table");
        filePage.clickSingleQuoteRadioButton();
        filePage.clickCommaRadioButton();
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        importPage.verifySubjectCSVFileImportIsCompleted();
        filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");*/

        //3.    Import Complete Diary Table
        Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadDiaryCSVTableTypeFile();
        filePage.clickDiaryRadioButton();
        importPage.fillInTableNameTextBox("Complete Diary Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.INFO, "Successfully select Complete Table Type");

        //4.    Open uploaded file and verify file can't be validated due to incompatible table format
        //importPage.openImportedSubjectTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");

        //5.   Create Folder AA and AB in Folder A
        fileSystemPage.openFolderA();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder AA");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        Util.Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder AB");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();

        //6.    Create New Test Folder AA1
        fileSystemPage.openFolderAA();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder AA1");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyCookieTestFolderAA1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder AA1");

        //7.    Create New Test Folder AA2
        Util.Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder AA2");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderAA2IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder AA2");

        //7.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();
        Util.Constants.refreshPage();
        fileSystemPage.openFolderA();

        //9.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();

        //10.   Moved assessment from shared data to My Data
        fileSystemPage.clickSharedDataLink();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickBatchCopyToButton();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Moved all 3 Assessment to My Data");

        //11.   Verify all 3 assessment files are in My Data Folder
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyFailedAssessment1IsDisplayedInMyDataPage();
        logger.log(LogStatus.PASS, "Successfully verify Assessment1 is displayed in My Data Page");


        report.endTest(logger);

    }


    @Test(dependsOnMethods = {"shouldCreateFoldersFilesAndAssessment"}, enabled = false)
    public void shouldVerifyCookieIsNotDisplayedInEachSystemFolder() throws Exception {

        logger = report.startTest("Successfully verify cookie trail is not displayed in each System Folder");


        //0.    Click on My Data System Folder
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyCookieTrailIsNotDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify cookie trail is not displayed in My Data Folder");

        //1.    Click on Shared Data System Folder
        fileSystemPage.clickSharedDataLink();
        Util.Constants.refreshPage();
        fileSystemPage.verifyCookieTrailIsNotDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify cookie trail is not displayed in Shared Data Folder");

        //2.    Click on My EM Data System Folder
        myDataPage.clickMainPageMyEMDataLink();
        Util.Constants.refreshPage();
        fileSystemPage.verifyCookieTrailIsNotDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify cookie trail is not displayed in Shared Data Folder");


        report.endTest(logger);
    }


    @Test(dependsOnMethods = {"shouldVerifyCookieIsNotDisplayedInEachSystemFolder"}, enabled = false)
    public void shouldVerifyCookieIsDisplayedInASubFolder() throws Exception {


        logger = report.startTest("Successfully verify cookie trail is displayed in Sub Folder");


        //0.    Navigate to Test Folder A
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderA();
        logger.log(LogStatus.PASS, "Successfully navigate to sub folder A");

        //1.    Verify cookie trail is displayed in Sub folder A
        fileSystemPage.verifyCookieTrailIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify cookie trail is displayed in Sub Folder");


        report.endTest(logger);
    }


    @Test(dependsOnMethods = {"shouldVerifyCookieIsDisplayedInASubFolder"}, enabled = false)
    public void shouldVerifyCookieTrailIsUpdatedAfterClick() throws Exception {


        logger = report.startTest("Successfully verify cookie trail updated according to click");

        //0.    Navigate to Test Folder AA1
        fileSystemPage.openFolderAA();
        fileSystemPage.openFolderAA1();
        logger.log(LogStatus.PASS, "Successfully navigate to Test Folder AA1");

        //1.    Verify cookie trail displays correct links
          fileSystemPage.verifyCookieTrailTestFolderAIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        fileSystemPage.verifyCookieTestFolderAA1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify correct cookie trail is displayed");

        //2.    click on Test Folder AA cookie Trail link and re verify updated cookie link is displayed
        fileSystemPage.clickTestFolderAACookieTrailLink();
        Util.Constants.refreshPage();
        filePage.verifyMyDataCookieTrailIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        logger.log(LogStatus.PASS, "Successfully click and verify correct cookie trail is displayed");

        //3.    Verify user on Test Folder AA page
        fileSystemPage.verifyUserOnTestFolderAAPage();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully verify user in correct page");



        report.endTest(logger);
    }


    @Test(dependsOnMethods = {"shouldVerifyCookieTrailIsUpdatedAfterClick"}, enabled = false)
    public void shouldVerifyCookieTrailIsUpdatedAfterTrashAction() throws Exception {

        logger = report.startTest("Successfully verify cookie update after trash action");

        //0.    Trash Folder A
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnTrashFromRightClickMenu();
        logger.log(LogStatus.INFO, "Successfully trash folder A");

        //1.    navigate to Trash and verify Folder A is displayed
        filePage.clickOnMainMenuTrashIcon();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Test folder A is displayed in Trash");

        //2.    navigate to Test Folder AA1
        fileSystemPage.openFolderAInTrash();
        fileSystemPage.openFolderAA();
        fileSystemPage.openFolderAA1();
        logger.log(LogStatus.PASS, "Successfully navigate to Test Folder AA1");

        //3.    Verify cookie trail displays correct links
        filePage.verifyTrashCookieTrailIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        fileSystemPage.verifyCookieTestFolderAA1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify correct cookie trail is displayed");

        //4.    click on Test Folder AA cookie Trail link and re verify updated cookie link is displayed
        fileSystemPage.clickTestFolderAACookieTrailLink();
        Util.Constants.refreshPage();
        filePage.verifyTrashCookieTrailIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        logger.log(LogStatus.PASS, "Successfully click and verify correct cookie trail is displayed");


        report.endTest(logger);
    }


    @Test(dependsOnMethods = {"shouldVerifyCookieTrailIsUpdatedAfterTrashAction"}, enabled = false)
    public void shouldVerifyCookieTrailIsUpdatedAfterRestoreAndRestoreToAction() throws Exception {

        logger = report.startTest("Successfully verify cookie update after restore and restore to action");


        //0.    Verify user on Test Folder AA page
        fileSystemPage.verifyUserOnTestFolderAAPage();
        logger.log(LogStatus.PASS, "Successfully verify user in correct page");

        //1.    Restore Test Folder A to a new Location Test Folder C
        filePage.clickOnMainMenuTrashIcon();
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickBatchRestoreToButton();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        logger.log(LogStatus.PASS, "Successfully restore to a new location, Test Folder C");

        //2.    Navigate to Test Folder AA1
        fileSystemPage.openFolderAInTestFolderC();
        fileSystemPage.openFolderAA();
        fileSystemPage.openFolderAA1();
        logger.log(LogStatus.INFO, "Successfully navigate to Test Folder AA1");

        //3.    verify updated cookie trail is displayed
        filePage.verifyMyDataCookieTrailIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderCIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        fileSystemPage.verifyCookieTestFolderAA1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify updated cookie trail is displayed");

        //4.    click on Test Folder AA cookie Trail link and re verify updated cookie link is displayed
        fileSystemPage.clickTestFolderAACookieTrailLink();
        //Util.Constants.refreshPage();
        filePage.verifyMyDataCookieTrailIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderCIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        logger.log(LogStatus.PASS, "Successfully click and verify updated cookie trail is displayed");

        //5. Restore Test Folder A to original location
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.clickTestFolderA();
        fileSystemPage.clickBatchMoveButton();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully restore Test Folder A to original location");


        report.endTest(logger);
    }


    @Test(dependsOnMethods = {"shouldVerifyCookieTrailIsUpdatedAfterRestoreAndRestoreToAction"}, enabled = false)
    public void shouldVerifyCookieTrailIsUpdatedAfterCopyToAction() throws Exception {

        logger = report.startTest("Successfully verify cookie update after Copy To action");




        //1.    Verify Test Folder A Sub Folders and Tables
        fileSystemPage.openFolderA();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.openFolderAA();
        fileSystemPage.verifyTestFolderAA2IsDisplayed();
        fileSystemPage.verifyCookieTestFolderAA1IsDisplayed();
        fileSystemPage.openFolderAA1();
        //fileSystemPage.verifyCompletedFoodTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Test Folder A Sub folder is displayed");

        //2.    Verify the original folder location cookie trail remain the same
        filePage.verifyMyDataCookieTrailIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        fileSystemPage.verifyCookieTestFolderAA1IsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully verify cookie remains the same");

        //3.    Verify Copied Test Folder A Sub Folders and Tables
        fileSystemPage.openFolderA();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.openFolderAA();
        fileSystemPage.verifyTestFolderAA2IsDisplayed();
        fileSystemPage.verifyTestFolderAA1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Copied Test Folder A Sub folder is displayed");

        //4.    Verify the copied folder location cookie trail is updated
        Constants.refreshPage();
        filePage.verifyMyDataCookieTrailIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAIsDisplayed();
        fileSystemPage.verifyCookieTrailTestFolderAADisplayed();
        logger.log(LogStatus.PASS, "Successfully verify copied Test Folder A cookie trail is Updated");



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
            logger.log(LogStatus.FAIL, "CookieTrailFunctionality", imagePath);

        }
        report.endTest(logger);
    }




    @AfterClass
    public void tearDown() throws Exception {

        logger = report.startTest("Start tear down");
        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getFileSystemUser());
        logger.log(LogStatus.PASS, "File System user has been deleted");	      
        logInPage.verifyLoginAccessDenied(CredentialsData.getFileSystemUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");


        //18.   Clear cache and quit browser
        //driver.close();
        Util.Constants.clearBrowserCache();
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }
}
