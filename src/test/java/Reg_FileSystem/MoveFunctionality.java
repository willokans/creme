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
 *  * AT-43
 Test Automation for EMA-622
 */
public class MoveFunctionality {



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

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\MoveFunctionality.html");
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

        //2.    Import Complete Subject Table - REMOVE Diary/Subject & REPLACE WITH QA CAT/DATA
       /* Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        importPage.upLoadSubjectCSVTableTypeFile();
        filePage.clickSubjectRadioButton();
        importPage.fillInTableNameTextBox("Complete Subject Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        importPage.verifySubjectCSVFileImportIsCompleted();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");

        //3.    Import Complete Diary Table
        Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        importPage.upLoadDiaryCSVTableTypeFile();
        filePage.clickDiaryRadioButton();
        importPage.fillInTableNameTextBox("Complete Diary Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        importPage.verifyDiaryCSVFileImportIsCompleted();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.INFO, "Successfully select Complete Table Type");

        //4.    Open uploaded file and verify file can't be validated due to incompatible table format
        importPage.openImportedDiaryTableFromMyDataPage();
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

        //6.   Import Complete Subject Table - REMOVE Diary/Subject & REPLACE WITH QA CAT/DATA
       /* Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        importPage.upLoadSubjectCSVTableTypeFile();
        filePage.clickSubjectRadioButton();
        importPage.fillInTableNameTextBox("Complete Subject Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        importPage.verifySubjectCSVFileImportIsCompleted();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");*/

        //7.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();
        Util.Constants.refreshPage();
        fileSystemPage.openFolderA();

        //8.   Import Complete Food Table - REMOVE food & REPLACE WITH QA CAT/DATA
       /* Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        importPage.upLoadFoodCSVTableTypeFile();
        filePage.clickFoodRadioButton();
        importPage.fillInTableNameTextBox("Complete Food Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        importPage.verifyFoodCSVFileImportIsCompleted();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Food Table");*/

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
    public void shouldMoveActionOnFolderAAfterRightClickMenuIsSelected() throws Exception {



        logger = report.startTest("Successfully Click on Move Action on Folder After right click");


        //0.    Click on Test Folder A
        fileSystemPage.rightClickTestFolderA();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Test Folder B");

        //1.    Verify Dropdown Content Options for Folder is displayed
        fileSystemPage.verifyRightClickCopyMenuIsDisplayed();
        fileSystemPage.verifyRightClickCopyToMenuIsDisplayed();
        fileSystemPage.verifyRightClickMoveMenuIsDisplayed();
        fileSystemPage.verifyRightClickTrashMenuIsDisplayed();
        fileSystemPage.verifyRightClickOpenInMenuIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all folder dropdown hamburger menu is displayed");

        //2.    Perform 'Move' Action from right click menu on Folder
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenMovedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed move action on Folder");

        //3.    Open Test Folder C, verify correct content in folder and clean up
        fileSystemPage.openFolderC();
        Constants.refreshPage();
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenMovedIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyTestFolderADisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move action on Folder");



        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnFolderAAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldMoveActionOnFolderAFromHamburgerButton() throws Exception {


        logger = report.startTest("Successfully Click on Move Action on Folder from Hamburger button");


        //0.    Click on Hamburger Menu for Test Folder A
        fileSystemPage.clickOnTestFolderAHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Test Folder A");

        //1.    Verify Dropdown Content Options for Folder is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all folder dropdown hamburger menu is displayed");

        //2.    Perform 'Move' Action from right click menu on Folder and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.clickOnTestFolderAHamburgerMenu();
        fileSystemPage.clickOnMoveFromHamburgerMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.verifyFileBeenMovedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderAInTestFolderCIsDisplayed();
        fileSystemPage.clickOnTestFolderAHamburgerMenu();
        fileSystemPage.clickOnMoveFromHamburgerMenu();
        fileSystemPage.moveTestFolderAToMyDataFolder();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyTestFolderADisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move action on Folder");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnFolderAFromHamburgerButton"}, enabled = false)
    public void shouldMoveActionOnFolderAFromBatchButton() throws Exception {

        logger = report.startTest("Successfully Action Batch Action on a Folder");


        //0.    Single Select a Folder
        fileSystemPage.clickOnTestFolderA();
        logger.log(LogStatus.INFO, "Successfully Select File");

        //1.    Perform 'Move' Batch Action on Folder and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickBatchMoveButton();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        //filePage.verifyStartedToMoveMessageIsDisplayed();
        fileSystemPage.verifyTestFolderAInTestFolderCIsDisplayed();
        fileSystemPage.clickOnTestFolderAHamburgerMenu();
        fileSystemPage.clickOnMoveFromHamburgerMenu();
        fileSystemPage.moveTestFolderAToMyDataFolder();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyTestFolderADisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move Batch action on Folder");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnFolderAFromBatchButton"}, enabled = false)
    public void shouldMoveActionOnSubjectTableAfterRightClickMenuIsSelected() throws Exception {

        logger = report.startTest("Successfully Click on Each Action on Table After right click");

    //0.    Click on Test Folder A
        fileSystemPage.rightClickCompleteSubjectTable();
        logger.log(LogStatus.INFO, "Successfully on Subject Table");

    //1.    Verify Dropdown Content Options for Table is displayed
        fileSystemPage.verifyRightClickCopyMenuIsDisplayed();
        fileSystemPage.verifyRightClickCopyToMenuIsDisplayed();
        fileSystemPage.verifyRightClickMoveMenuIsDisplayed();
        fileSystemPage.verifyRightClickTrashMenuIsDisplayed();
        fileSystemPage.verifyRightClickDownloadMenuOptionIsDisplayed();
        fileSystemPage.verifyRightClickOpenInMenuIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all Table dropdown option menu is displayed");

    //2.    Perform 'Move' Action from right click menu on Table and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.openFolderC();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move action on Subject Table");


        report.endTest(logger);

}


    @Test (dependsOnMethods={"shouldMoveActionOnSubjectTableAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldMoveActionOnDiaryTableFromHamburgerButton() throws Exception {


        logger = report.startTest("Successfully Click on Move Action on Diary table from Hamburger button");

        //0.    Click on Hamburger Menu for Diary Table
        fileSystemPage.clickOnQATableHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Diary Table");

        //1.    Verify Dropdown Content Option for Table is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerDownloadMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all File dropdown Hamburger menu is displayed");

        //2.   Perform 'Copy To' Action from the Hamburger Menu on Table and clean up
        fileSystemPage.clickOnQATableHamburgerMenu();
        fileSystemPage.clickOnMoveFromHamburgerMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.openFolderC();
        fileSystemPage.verifyCompletedDiaryTableIsDisplayed();
        fileSystemPage.rightClickCompleteDiaryTable();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyCompletedDiaryTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Copy To action on Table");

        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnDiaryTableFromHamburgerButton"}, enabled = false)
    public void shouldMoveActionOnDiaryTableFromBatchButton() throws Exception {

        logger = report.startTest("Successfully Action Move Batch Action on a Table");

        //0.    Perform 'Move' Batch Action and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.clickOnCompleteSubjectTable();
        fileSystemPage.clickBatchMoveButton();

        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.openFolderC();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move Batch action on Subject Table");

        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnDiaryTableFromBatchButton"}, enabled = false)
    public void shouldMoveActionOnAssessmentAfterRightClickMenuIsSelected() throws Exception {


        logger = report.startTest("Successfully Click on Move Action on Assessment After right click");


        //0.    Click on Draft Assessment
        fileSystemPage.rightClickFailedAssessment1();
        logger.log(LogStatus.INFO, "Successfully on Subject Table");

        //1.    Verify Dropdown Content Options for Table is displayed
        fileSystemPage.verifyRightClickCopyMenuIsDisplayed();
        fileSystemPage.verifyRightClickCopyToMenuIsDisplayed();
        fileSystemPage.verifyRightClickMoveMenuIsDisplayed();
        fileSystemPage.verifyRightClickTrashMenuIsDisplayed();
        fileSystemPage.verifyRightClickOpenInMenuIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all Assessment dropdown option menu is displayed");

        //2.    Perform 'Move' Action from right click menu on Table and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickFailedAssessment1();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.openFolderC();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        fileSystemPage.rightClickFailedAssessment1();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move action on failed Assessment1");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnAssessmentAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldMoveActionOnAssessmentFromHamburgerButton() throws Exception {


        logger = report.startTest("Successfully carry out Move on hamburger assessment item");


        //0.    Click on Hamburger Menu for Test Folder A
        fileSystemPage.clickOnTestFolderAHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Assessment");

        //1.    Verify Dropdown Content Options for Folder is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all folder dropdown hamburger menu is displayed");

        //2.    Perform 'Move' Action from the Hamburger Menu on Folder and clean up
        fileSystemPage.clickOnFailedAssessmentHamburgerMenu();
        fileSystemPage.clickOnMoveFromHamburgerMenu();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.openFolderC();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        fileSystemPage.rightClickFailedAssessment1();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move action on Folder");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnAssessmentFromHamburgerButton"}, enabled = false)
    public void shouldMoveActionOnAssessmentFromBatchButton() throws Exception {

        logger = report.startTest("Successfully Action Batch Action on a Assessment");


        //0.    Single Select a Assessment
        fileSystemPage.clickFailedAssessment1();
        logger.log(LogStatus.INFO, "Successfully Select Assessment");

        //1.    Perform 'Move' Batch Action on Assessment and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickBatchMoveButton();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.openFolderC();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        fileSystemPage.rightClickFailedAssessment1();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Copy To Batch action on Assessment");

        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldMoveActionOnAssessmentFromBatchButton"}, enabled = false)
    public void shouldMoveActionOnMultipleItemAfterRightClickMenuIsSelected() throws Exception {

        logger = report.startTest("Successfully Click on Move Action on Multiple Item After right click");


        //0.    Select Multiple Items
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickOnCompleteSubjectTable();
        logger.log(LogStatus.PASS, "Successfully select multiple items");

        //1.    Perform 'Copy' Action from right click menu on one of the items
        fileSystemPage.clickBatchMoveButton();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFilesHasBeenMovedIsDisplayed();
        fileSystemPage.openFolderC();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed move action on all items");

        //2.    clean up
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickOnCompleteSubjectTable();
        fileSystemPage.rightClickFailedAssessment1();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully restore items");



        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldMoveActionOnMultipleItemAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldVerifyAppendingOfItemsMovedToTheSameLocation() throws Exception {


        logger = report.startTest("Successfully Verify item(s) is appended when moved to the same location");

                //0.    Single Select a Folder
        fileSystemPage.clickOnTestFolderA();
        logger.log(LogStatus.INFO, "Successfully Select File");

        //1.    Perform 'Move' Batch Action on Folder and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickBatchMoveButton();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyTestFolderAAppend1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move Batch action on same Folder");

        //2.    Verify Folder Content is retained
        fileSystemPage.openFolderAAppend1();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.verifyCompletedFoodTableIsDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully verify appended folder content");

        //3.    Perform 'Move' Batch Action on same appended folder
        Util.Constants.refreshPage();
        fileSystemPage.clickOnTestFolderAAppended1();
        fileSystemPage.clickBatchMoveButton();
        fileSystemPage.selectMyDateFromFolderSelect();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyTestFolderAAppend2IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed Move Batch action on appended1 Folder");

        //4.    Verify Folder Content is retained
        fileSystemPage.openFolderAAppend2();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.verifyCompletedFoodTableIsDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully verify appended folder content");



        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldVerifyAppendingOfItemsMovedToTheSameLocation"}, enabled = false)
    public void shouldVerifyCopyToFunctionalityNotPresentInSystemFolders() throws Exception {


        logger = report.startTest("Successfully Verify no trash or copy in system folder");

        //0. click on My EM Data Link
        myDataPage.clickMainPageMyEMDataLink();
        logger.log(LogStatus.PASS, "Successfully click on my EM Data");

        //1.    Click on Cares Folder and verify Copy To is displayed
        myDataPage.clickMainPageMyEMDataCaresFolderLink();
        myDataPage.verifyCopyToBatchIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Copy To is displayed");

        //2.    Click on Cares Folder Hamburger link
        myDataPage.clickOnMyEMDataCaresFolderHamburgerMenu();
        myDataPage.verifyMoveFunctionIsNotDisplayedInDropDown();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Copy To is displayed");

        //3.    Right Click on Cares Folder and verify dropdown options
        myDataPage.rightClickCaresFolder();
        myDataPage.verifyMoveFunctionIsNotDisplayedInDropDown();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully verify right click menu option is displayed");

        //0.   Click on LogOut
        Constants.refreshPage();
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
            logger.log(LogStatus.FAIL, "MoveFunctionality", imagePath);

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
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }

}
