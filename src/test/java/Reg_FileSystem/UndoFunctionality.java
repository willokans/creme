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
 * AT-40
 Test Automation for EMA-619
 */
public class UndoFunctionality {


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

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\UndoFunctionality.html");
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


    @Test (dependsOnMethods={"createActiveUserProfile"}, enabled = false)
    public void shouldLogInEM () throws Exception {

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


    @Test (dependsOnMethods={"shouldLogInEM"}, enabled = false)
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

        //2.    Import Complete Subject Table
        Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadSubjectCSVTableTypeFile();
        filePage.clickSubjectRadioButton();
        importPage.fillInTableNameTextBox("Complete Subject Table");
        filePage.clickSingleQuoteRadioButton();
        filePage.clickCommaRadioButton();
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        ////filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");

        //3.    Import Complete Diary Table - REMOVE Diary & REPLACE WITH QA CAT/DATA
        /*Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        importPage.upLoadDiaryCSVTableTypeFile();
        filePage.clickDiaryRadioButton();
        importPage.fillInTableNameTextBox("Complete Diary Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.INFO, "Successfully select Complete Table Type");

        //4.    Open uploaded file and verify file can't be validated due to incompatible table format
        importPage.openImportedSubjectTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");*/

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


    @Test (dependsOnMethods={"shouldCreateFoldersFilesAndAssessment"}, enabled = false)
    public void shouldUndoMovingSingleItem() throws Exception {


        logger = report.startTest("Successfully Undo Moving Single Item");


        //0.    Click on Table item
        fileSystemPage.rightClickCompleteSubjectTable();
        logger.log(LogStatus.INFO, "Successfully on Subject Table");

        //1.    Perform 'Move' Action and click Undo Link
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.clickUndoLink();
        logger.log(LogStatus.INFO, "Successfully perform move and undo");

        //2.    Verify item is still present in original location
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify item is still present");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldUndoMovingSingleItem"}, enabled = false)
    public void shouldUndoTrashingSingleItem() throws Exception {

        logger = report.startTest("Successfully Undo trashing Single Item");


        //0.    click on Test folder A
        fileSystemPage.rightClickTestFolderA();

        //1.    Perform 'Trash' action and click Undo Link
        fileSystemPage.clickOnTrashFromRightClickMenu();
        fileSystemPage.clickUndoLink();
        logger.log(LogStatus.INFO, "Successfully perform trash and undo");

        //2.    Verify item is still present in original location
        fileSystemPage.verifyTestFolderADisplayed();
        //Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify item is still present");



        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldUndoTrashingSingleItem"}, enabled = false)
    public void shouldUndoMovingMultipleItems() throws Exception {


        logger = report.startTest("Successfully Undo Moving Multiple Items");


        //0.    Select items to move
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickOnCompleteSubjectTable();
        fileSystemPage.rightClickCompleteSubjectTable();
        logger.log(LogStatus.INFO, "Successfully select items to move");

        //1.    Perform 'Move' Action and click Undo Link
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.clickUndoLink();
        logger.log(LogStatus.INFO, "Successfully perform move and undo");

        //2.    Verify item is still present in original location
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyTestFolderADisplayed();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify items are still displayed");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldUndoMovingMultipleItems"}, enabled = false)
    public void shouldUndoTrashingMultipleItems() throws Exception {

        logger = report.startTest("Successfully Undo trashing multiple Items");


        //0.    click on Test folder A
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickOnCompleteSubjectTable();
        fileSystemPage.rightClickCompleteSubjectTable();
        logger.log(LogStatus.INFO, "Successfully select items to move");

        //1.    Perform 'Trash' action and click Undo Link
        fileSystemPage.clickOnTrashFromRightClickMenu();
        fileSystemPage.clickUndoLink();
        logger.log(LogStatus.INFO, "Successfully perform trash and undo");

        //2.    Verify item is still present in original location
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyTestFolderADisplayed();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify item is still present");

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
            logger.log(LogStatus.FAIL, "UndoFunctionality", imagePath);

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

        Constants.clearBrowserCache();
        //driver.close();
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }

}
