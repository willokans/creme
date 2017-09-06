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
 * Created by will.okamuneh on 9/1/2016.
 *  * AT-38
 Test Automation for EMA-617
 */
public class CopyFunctionality {


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

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\CopyFunctionality.html");
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
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        ////filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");

        //3.    Import Complete Diary Table -> Remove Diary and replace with QA
       /* Constants.refreshPage();
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

        //6.   Import Complete Subject Table
        Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadSubjectCSVTableTypeFile();
        filePage.clickSubjectRadioButton();
        importPage.fillInTableNameTextBox("Complete Subject Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");

        //7.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();
        Util.Constants.refreshPage();
        fileSystemPage.openFolderA();

        //8.   Import Complete Food Table
        Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadFoodCSVTableTypeFile();
        filePage.clickFoodRadioButton();
        importPage.fillInTableNameTextBox("Complete Food Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Food Table");

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
    public void shouldCopyActionOnFolderAAfterRightClickMenuIsSelected() throws Exception {



        logger = report.startTest("Successfully Click on Copy Action on Folder After right click");


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

        //2.    Perform 'Copy' Action from right click menu on Folder
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed copy action on Folder");

        //3.    Open Test Folder A COPY and verify correct content in folder and clean up
        fileSystemPage.openFolderACopy();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyCompletedFoodTableIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFolderAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.verifyFileBeenDeleteMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Test Folder A COPY Content");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldCopyActionOnFolderAAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldCopyActionOnFolderAFromHamburgerButton() throws Exception {


        logger = report.startTest("Successfully Click on Copy Action on Folder from Hamburger button");


        //0.    Click on Hamburger Menu for Test Folder B
        fileSystemPage.clickOnTestFolderAHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Test Folder B");

        //1.    Verify Dropdown Content Options for Folder is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all folder dropdown hamburger menu is displayed");

        //2.    Perform 'Copy' Action from the Hamburger Menu on Folder and clean up
        fileSystemPage.clickOnTestFolderAHamburgerMenu();
        fileSystemPage.clickOnCopyFromHamburgerItemMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully performed copy action on Folder");

        //3.    Open Test Folder A COPY and verify correct content in folder and clean up
        fileSystemPage.openFolderACopy();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyCompletedFoodTableIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFolderAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.verifyFileBeenDeleteMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Test Folder A COPY Content");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldCopyActionOnFolderAFromHamburgerButton"}, enabled = false)
    public void shouldCopyActionOnFolderAFromBatchButton() throws Exception {


        logger = report.startTest("Successfully Click on Copy Action on Folder from Batch button");

        //0.    Single Select a Folder
        fileSystemPage.clickOnTestFolderA();
        logger.log(LogStatus.INFO, "Successfully Select File");

        //1.    Perform 'Copy' Batch Action on Folder and clean up
        fileSystemPage.clickBatchCopyButton();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed copy Batch action on Folder");

        //3.    Open Test Folder A COPY and verify correct content in folder and clean up
        fileSystemPage.openFolderACopy();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyCompletedFoodTableIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFolderAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.verifyFileBeenDeleteMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Test Folder A COPY Content");


        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldCopyActionOnFolderAFromBatchButton"}, enabled = false)
    public void shouldCopyActionOnSubjectTableAfterRightClickMenuIsSelected() throws Exception {



        logger = report.startTest("Successfully Click on Copy Action on Subject Table After right click");


        //0.    Click on Subject table
        fileSystemPage.rightClickCompleteSubjectTable();
        logger.log(LogStatus.INFO, "Successfully right-click on Subject Table");

        //1.    Verify Dropdown Content Options for Folder is displayed
        fileSystemPage.verifyRightClickCopyMenuIsDisplayed();
        fileSystemPage.verifyRightClickCopyToMenuIsDisplayed();
        fileSystemPage.verifyRightClickMoveMenuIsDisplayed();
        fileSystemPage.verifyRightClickTrashMenuIsDisplayed();
        fileSystemPage.verifyRightClickOpenInMenuIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all folder dropdown hamburger menu is displayed");

        //2.    Perform 'Copy' Action from right click menu on Folder
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnCopyToFromRightClickMenu();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyCopyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedCompleteSubjectTableAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully performed Copy To action on Subject Table");


        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldCopyActionOnSubjectTableAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldCopyActionOnDiaryTableFromHamburgerButton() throws Exception {


        logger = report.startTest("Successfully Click on Copy Action on Diary table from Hamburger button");

        //7.    Click on Hamburger Menu for Diary Table
        fileSystemPage.clickOnQATableHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Diary Table");

        //8.    Verify Dropdown Content Option for Table is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerDownloadMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify all File dropdown Hamburger menu is displayed");

        //9.    Perform 'Copy' Action from the Hamburger Menu on Table and clean up
        Constants.refreshPage();
        fileSystemPage.clickOnQATableHamburgerMenu();
        fileSystemPage.clickOnCopyFromHamburgerItemMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyCompleteDiaryTableCopyIsDisplayed();
        fileSystemPage.rightClickCopiedDiaryTableAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedDiaryTableAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.verifyFileBeenDeleteMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully performed copy action on Table");

        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldCopyActionOnDiaryTableFromHamburgerButton"}, enabled = false)
    public void shouldCopyActionOnDiaryTableFromBatchButton() throws Exception {


        logger = report.startTest("Successfully Action Copy Batch Action on a Table");

        //0.    Single Select a file
        fileSystemPage.clickOnCompleteSubjectTable();
        logger.log(LogStatus.INFO, "Successfully Select File");

        //1.    Perform 'Copy' Batch Action and clean up
        fileSystemPage.clickBatchCopyButton();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedCompleteSubjectTableAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully performed copy action on Subject Table");


        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldCopyActionOnDiaryTableFromBatchButton"}, enabled = false)
    public void shouldCopyActionOnAssessmentAfterRightClickMenuIsSelected() throws Exception {


        logger = report.startTest("Successfully Click on Copy Action on Assessment After right click");


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

        //2.    Perform 'Copy' Action from right click menu on Table and clean up
        fileSystemPage.rightClickFailedAssessment1();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyAssessmentCopyIsDisplayed();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFailedAssessmentAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully performed copy action on failed Assessment");

        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldCopyActionOnAssessmentAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldCopyActionOnAssessmentFromHamburgerButton() throws Exception {

        logger = report.startTest("Successfully carry out Copy on hamburger assessment item");

        //0.    Click on Hamburger Menu for Model Run
        fileSystemPage.clickOnFailedAssessmentHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Assessment");

        //1.    Verify Dropdown Content Option for Table is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all Model Run dropdown Hamburger menu is displayed");

        //2.   Perform 'Copy' Action from the Hamburger Menu on Model Run and clean up
        fileSystemPage.clickOnFailedAssessmentHamburgerMenu();
        fileSystemPage.clickOnCopyFromHamburgerItemMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFailedAssessmentAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.verifyFileBeenDeleteMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully performed copy action on Model Run");

        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldCopyActionOnAssessmentFromHamburgerButton"}, enabled = false)
    public void shouldCopyActionOnAssessmentFromBatchButton() throws Exception {


        logger = report.startTest("Successfully Action Batch Action on a Assessment");


        //0.    Single Select a Assessment
        fileSystemPage.clickFailedAssessment1();
        logger.log(LogStatus.INFO, "Successfully Select Assessment");

        //1.    Perform 'Copy' Batch Action on Assessment and clean up
        fileSystemPage.clickBatchCopyButton();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyFailedAssessmentCopyIsDisplayed();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFailedAssessmentAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully performed copy Batch action on Assessment");

        //2.    Perform 'Copy To' Batch Action on Assessment and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickBatchCopyToButton();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyFailedAssessmentCopyIsDisplayed();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFailedAssessmentAndPermanentlyDelete();
        trashPage.clickOnYesDeleteButton();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully performed Copy To Batch action on Assessment");

        report.endTest(logger);
    }


        @Test (dependsOnMethods={"shouldCopyActionOnAssessmentFromBatchButton"}, enabled = false)
    public void shouldCopyActionOnMultipleItemAfterRightClickMenuIsSelected() throws Exception {


        logger = report.startTest("Successfully Click on Copy Action on Multiple Item After right click");


        //0.    Select Multiple Items
        fileSystemPage.clickOnTestFolderA();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickOnCompleteSubjectTable();
        logger.log(LogStatus.PASS, "Successfully select multiple items");

        //1.    Perform 'Copy' Action from right click menu on one of the items
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFilesHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        fileSystemPage.verifyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.verifyFailedAssessment1CopyIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully performed copy action on all items");

        //2.    Open Test Folder A COPY and verify correct content in folder and clean up
        fileSystemPage.openFolderACopy();
        fileSystemPage.verifyTestFolderAADisplayed();
        fileSystemPage.verifyTestFolderABDisplayed();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        fileSystemPage.verifyCompletedFoodTableIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Test Folder A COPY Content");

        //3.    Permanently delete copy items
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        fileSystemPage.rightClickCopiedTestFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        fileSystemPage.verifyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.verifyFailedAssessment1CopyIsDisplayed();
        trashPage.clickEmptyTrashButtonOnTrashPage();
        trashPage.clickOnYesDeleteButton();
        fileSystemPage.verifyFileHasBeenDeletedIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully permanently delete copied items");


        report.endTest(logger);
    }



    @Test (dependsOnMethods={"shouldCopyActionOnMultipleItemAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldVerifyCopyFunctionalityNotPresentInSystemFolders() throws Exception {


        logger = report.startTest("Successfully verify copy functionality is not present in System Folders");


        //0.    Click on My EM Data link
        myDataPage.clickMainPageMyEMDataLink();
        logger.log(LogStatus.INFO, "Successfully click on My EM Data link");

        //1.    right click on a folder and verify Copy functionality is not present
        myDataPage.rightClickCaresFolder();
        myDataPage.verifyCopyFunctionIsNotDisplayedInDropDown();
        Util.Constants.refreshPage();
        logger.log(LogStatus.INFO, "Successfully verify Copy Function not present if right-click menu");

        // 2.   Click Hamburger menu and and verify Copy functionality is not present
        myDataPage.clickOnMyEMDataCaresFolderHamburgerMenu();
        myDataPage.verifyCopyFunctionIsNotDisplayedInDropDown();
        Util.Constants.refreshPage();
        logger.log(LogStatus.INFO, "Successfully verify Copy Function not present on hamburger menu");

        //3.    Prompt Batch Menu and verify Copy functionality is not present
        myDataPage.clickMainPageMyEMDataCaresFolderLink();
        myDataPage.verifyCopyFunctionIsNotDisplayedInDropDown();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.INFO, "Successfully verify Copy Function not present on batch menu");


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
            logger.log(LogStatus.FAIL, "CopyFunctionality", imagePath);

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

        //driver.close();
        Util.Constants.clearBrowserCache();
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }



}
