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
 * Created by will.okamuneh on 8/25/2016.
 *  * AT-41 & AT-33
 Test Automation for EMA-620 & EMA-612
 */
public class TrashFolderAndActionFunctionality {



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

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\ActionMenuOption.html");
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

        //1.     Create New Test Folder 1
        Util.Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder A");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder A");

        //3.    Create New Test Folder 3
        Util.Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder C");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderCIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder C");

        //5.    Import Complete Subject Table - REMOVE Subject & REPLACE WITH QA CAT/DATA
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
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");

        //6.    Open uploaded file and verify file can't be validated due to incompatible table format
        importPage.openImportedSubjectTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");
*/
        //13.   Create Folder AA and AB in Folder A
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

        //14.   Import Complete Subject Table
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

        //15.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();
        Util.Constants.refreshPage();
        fileSystemPage.openFolderA();

        //16.   Import Complete Food Table
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

        //17.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();

        //19.   Moved assessment from shared data to My Data
        fileSystemPage.clickSharedDataLink();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickBatchCopyToButton();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Moved all 3 Assessment to My Data");

        //18.   Verify all 3 assessment files are in My Data Folder
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyFailedAssessment1IsDisplayedInMyDataPage();
        logger.log(LogStatus.PASS, "Successfully verify Assessment1 is displayed in My Data Page");



        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldLogInEM"}, enabled = false)
    public void shouldVerifyTrashFunctionalitiesWorksForFolderItem() throws Exception {


        logger = report.startTest("Successfully Verify Trash functionalities Works for Folder Item");



        //0.    Copy Folder A and Send to Trash for Testing Purposes
        Util.Constants.refreshPage();
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        fileSystemPage.rightClickCopiedFolderAAndTrash();
        logger.log(LogStatus.PASS, "Successfully made copy of Folder A and send to Trash for testing purposes");


        //0.     Navigate to Trash page
        filePage.clickOnMainMenuTrashIcon();
        logger.log(LogStatus.INFO, "Successfully navigate to Trash Folder");

        //1.    Verify Trash Page content
        trashPage.verifyTrashLabelIsDisplayed();
        trashPage.verifyEmptyTrashButtonIsDisplayed();
        trashPage.verifyFileCounterLabelIsDisplayed();
        trashPage.verifyFileTimerLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Trash Page Content");

        //2.    Right Click Folder Item in Trash and Verify Content is displayed
        trashPage.rightClickCopiedFolderAInTrash();
        trashPage.verifyRestoreButtonIsDisplayed();
        trashPage.verifyRestoreToButtonIsDisplayed();
        trashPage.verifyPermanentlyDeleteButtonIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Folder Right-click Content is displayed");

        //3.    Click Folder Hamburger Menu and verify Contents is displayed
        trashPage.clickCopyFolderAHamburgerMenu();
        trashPage.verifyTrashHamburgerRestoreLinkIsDisplayed();
        trashPage.verifyTrashHamburgerRestoreToLinkIsDisplayed();
        trashPage.verifyTrashHamburgerPermanentlyDeleteLinkIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Folder Hamburger Content is displayed");

        //4.    Click Table Batch Action Menu and verify Contents is displayed
        trashPage.clickTrashedCopyFolderA();
        trashPage.verifyBatchRestoreButtonIsDisplayed();
        trashPage.verifyBatchRestoreToButtonIsDisplayed();
        trashPage.verifyBatchPermanentlyButtonIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Folder Batch Action Content is displayed");

        //5.    Restore and Restore To Using Batch Action button and Cancel Permanently Delete
        trashPage.clickTrashedCopyFolderA();
        trashPage.clickOnBatchRestoreLink();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedTestFolderAAndTrash();
        filePage.verifyFileBeenTrashMessageIsDisplayed();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedCopyFolderA();
        trashPage.clickOnBatchRestoreToLink();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedTestFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedCopyFolderA();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.verifyPermanentlyDeleteMessageIsDisplayed();
        trashPage.clickOnPermanentlyCancelDeleteButton();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using Batch Action on Folder");

        //6.    Restore and Restore To Using Right-Click Function and Cancel Permanently Delete
        trashPage.rightClickCopiedFolderAInTrash();
        filePage.clickOnRestoreLink();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedTestFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFolderAInTrash();
        filePage.clickOnRestoreToLink();
        trashPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedTestFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedCopyFolderA();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.verifyPermanentlyDeleteMessageIsDisplayed();
        trashPage.clickOnPermanentlyCancelDeleteButton();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using right-click on Folder");

        //8.    Restore and Restore To Using Hamburger Function and Permanently Delete
        trashPage.clickCopyFolderAHamburgerMenu();
        trashPage.clickHamburgerRestoreButton();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedTestFolderAAndTrash();
        fileSystemPage.verifyFileHasBeenTrashIsDisplayed();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickCopyFolderAHamburgerMenu();
        trashPage.clickHamburgerRestoreToButton();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedTestFolderAAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedCopyFolderA();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.clickYesToPermanentlyDeleteButton();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using Hamburger on Folder");




        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldVerifyTrashFunctionalitiesWorksForFolderItem"}, enabled = false)
    public void shouldVerifyTrashFunctionalitiesWorksForTableItem() throws Exception {

        logger = report.startTest("Successfully Verify Trash functionalities Works for Table Item");


        //0.    Copy Complete Subject Table and Send to Trash for Testing Purposes
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        logger.log(LogStatus.PASS, "Successfully made copy of Subject Table and send to Trash for testing purposes");

        //1.     Navigate to Trash page
        filePage.clickOnMainMenuTrashIcon();
        logger.log(LogStatus.INFO, "Successfully navigate to Trash Folder");

        //2.    Verify Trash Page content
        trashPage.verifyTrashLabelIsDisplayed();
        trashPage.verifyEmptyTrashButtonIsDisplayed();
        trashPage.verifyFileCounterLabelIsDisplayed();
        trashPage.verifyFileTimerLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Trash Page Content");

        //3.    Right Click Table Item in Trash and Verify Content is displayed
        trashPage.rightClickCopiedSubjectTableInTrash();
        trashPage.verifyRestoreButtonIsDisplayed();
        trashPage.verifyRestoreToButtonIsDisplayed();
        trashPage.verifyPermanentlyDeleteButtonIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Table Right-click Content is displayed");

        //4.    Click Table Hamburger Menu and verify Contents is displayed
        trashPage.clickSubjectTableHamburgerMenu();
        trashPage.verifyTrashHamburgerRestoreLinkIsDisplayed();
        trashPage.verifyTrashHamburgerRestoreToLinkIsDisplayed();
        trashPage.verifyTrashHamburgerPermanentlyDeleteLinkIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Table Hamburger Content is displayed");

        //5.    Click Table Batch Action Menu and verify Contents is displayed
        trashPage.clickTrashedSubjectTable();
        trashPage.verifyBatchRestoreButtonIsDisplayed();
        trashPage.verifyBatchRestoreToButtonIsDisplayed();
        trashPage.verifyBatchPermanentlyButtonIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Table Batch Action Content is displayed");

        //6.    Restore and Restore To Using Batch Action button and Cancel Permanently Delete
        trashPage.clickTrashedSubjectTable();
        trashPage.clickOnBatchRestoreLink();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        filePage.verifyFileBeenTrashMessageIsDisplayed();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedSubjectTable();
        trashPage.clickOnBatchRestoreToLink();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedSubjectTable();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.verifyPermanentlyDeleteMessageIsDisplayed();
        trashPage.clickOnPermanentlyCancelDeleteButton();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using Batch Action on Table");

        //7.    Restore and Restore To Using Right-Click Function and Cancel Permanently Delete
        trashPage.rightClickCopiedSubjectTableInTrash();
        filePage.clickOnRestoreLink();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedSubjectTableInTrash();
        filePage.clickOnRestoreToLink();
        trashPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedSubjectTable();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.verifyPermanentlyDeleteMessageIsDisplayed();
        trashPage.clickOnPermanentlyCancelDeleteButton();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using right-click on Table");

        //8.    Restore and Restore To Using Hamburger Function and Permanently Delete
        trashPage.clickSubjectTableHamburgerMenu();
        trashPage.clickHamburgerRestoreButton();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        fileSystemPage.verifyFileHasBeenTrashIsDisplayed();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickSubjectTableHamburgerMenu();
        trashPage.clickHamburgerRestoreToButton();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedSubjectTable();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.clickYesToPermanentlyDeleteButton();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using Hamburger on Table");




        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldVerifyTrashFunctionalitiesWorksForTableItem"}, enabled = false)
    public void shouldVerifyTrashFunctionalitiesWorksForAssessmentItem() throws Exception {

        logger = report.startTest("Successfully Verify Trash functionalities Works for Assessment Item");


        //0.    Copy Assessment and Send to Trash for Testing Purposes
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickFailedAssessment1();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyFailedAssessment1CopyIsDisplayed();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        logger.log(LogStatus.PASS, "Successfully made copy of Assessment and send to Trash for testing purposes");

        //1.     Navigate to Trash page
        filePage.clickOnMainMenuTrashIcon();
        logger.log(LogStatus.INFO, "Successfully navigate to Trash Folder");

        //2.    Verify Trash Page content
        trashPage.verifyTrashLabelIsDisplayed();
        trashPage.verifyFileCounterLabelIsDisplayed();
        trashPage.verifyFileTimerLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Trash Page Content");

        //3.    Right Click Assessment Item in Trash and Verify Content is displayed
        trashPage.rightClickCopiedFailedAssessment1InTrash();
        trashPage.verifyRestoreButtonIsDisplayed();
        trashPage.verifyRestoreToButtonIsDisplayed();
        trashPage.verifyPermanentlyDeleteButtonIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Assessment Right-click Content is displayed");

        //4.    Click Assessment Hamburger Menu and verify Contents is displayed
        trashPage.clickFailedAssessment1CopyHamburgerMenu();
        trashPage.verifyTrashHamburgerRestoreLinkIsDisplayed();
        trashPage.verifyTrashHamburgerRestoreToLinkIsDisplayed();
        trashPage.verifyTrashHamburgerPermanentlyDeleteLinkIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Assessment Hamburger Content is displayed");

        //5.    Click Assessment Batch Action Menu and verify Contents is displayed
        trashPage.clickTrashedCopyAssessment1();
        trashPage.verifyBatchRestoreButtonIsDisplayed();
        trashPage.verifyBatchRestoreToButtonIsDisplayed();
        trashPage.verifyBatchPermanentlyButtonIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Table Batch Action Content is displayed");

        //6.    Restore and Restore To Using Batch Action button and Cancel Permanently Delete
        trashPage.clickTrashedCopyAssessment1();
        trashPage.clickOnBatchRestoreLink();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.verifyFileBeenTrashMessageIsDisplayed();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedCopyAssessment1();
        trashPage.clickOnBatchRestoreToLink();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedCopyAssessment1();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.verifyPermanentlyDeleteMessageIsDisplayed();
        trashPage.clickOnPermanentlyCancelDeleteButton();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using Batch Action on Assessment");

        //7.    Restore and Restore To Using Right-Click Function and Cancel Permanently Delete
        trashPage.rightClickCopiedFailedAssessment1InTrash();
        filePage.clickOnRestoreLink();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.rightClickCopiedFailedAssessment1InTrash();
        filePage.clickOnRestoreToLink();
        trashPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedFailedAssessment1Copy();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.verifyPermanentlyDeleteMessageIsDisplayed();
        trashPage.clickOnPermanentlyCancelDeleteButton();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using right-click on Table");

        //8.    Restore and Restore To Using Hamburger Function and Permanently Delete
        trashPage.clickFailedAssessment1CopyHamburgerMenu();
        trashPage.clickHamburgerRestoreButton();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        fileSystemPage.verifyFileHasBeenTrashIsDisplayed();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickFailedAssessment1CopyHamburgerMenu();
        trashPage.clickHamburgerRestoreToButton();
        fileSystemPage.selectTestFolderCFromFilePickerDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        trashPage.verifyYourFileHasBeenRestoredMessageIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.openFolderC();
        fileSystemPage.rightClickCopiedFailedAssessment1AndTrash();
        filePage.clickOnMainMenuTrashIcon();
        trashPage.clickTrashedCopyAssessment1();
        trashPage.clickOnPermanentlyDeleteLink();
        trashPage.clickYesToPermanentlyDeleteButton();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Restore and Restored using Hamburger on Assessment");


        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldVerifyTrashFunctionalitiesWorksForAssessmentItem"}, enabled = false)
    public void shouldVerifyTrashEmptyTrashFunctionality() throws Exception {

        logger = report.startTest("Successfully Verify Empty Trash Button works correctly");


        //0.    Copy Folder A and Send to Trash for Testing Purposes
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        fileSystemPage.rightClickCopiedFolderAAndTrash();
        logger.log(LogStatus.PASS, "Successfully made copy of Folder A and send to Trash for testing purposes");


        //1.     Navigate to Trash page
        filePage.clickOnMainMenuTrashIcon();
        logger.log(LogStatus.INFO, "Successfully navigate to Trash Folder");

        //2.    Click on Empty Trash Button and Select Cancel
        trashPage.clickEmptyTrashButtonOnTrashPage();
        trashPage.verifyEmptyTrashMessageIsDisplayed();
        trashPage.clickOnEmptyTrashCancelButton();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        trashPage.clickEmptyTrashButtonOnTrashPage();
        trashPage.verifyEmptyTrashMessageIsDisplayed();
        trashPage.clickOnYesDeleteButton();
        fileSystemPage.verifyFileHasBeenDeletedIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Test Empty Trash Button");

        //3.    Copy Complete Subject Table and Send to Trash for Testing Purposes
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        logger.log(LogStatus.PASS, "Successfully made copy of Subject Table and send to Trash for testing purposes");

        //4.     Navigate to Trash page
        filePage.clickOnMainMenuTrashIcon();
        logger.log(LogStatus.INFO, "Successfully navigate to Trash Folder");

        //5.    Click on Empty Trash Button and Select Cancel
        trashPage.clickEmptyTrashButtonOnTrashPage();
        trashPage.verifyEmptyTrashMessageIsDisplayed();
        trashPage.clickOnEmptyTrashCancelButton();
        fileSystemPage.verifyCopyCompleteSubjectTableCopyIsDisplayed();
        Util.Constants.refreshPage();
        trashPage.clickEmptyTrashButtonOnTrashPage();
        trashPage.verifyEmptyTrashMessageIsDisplayed();
        trashPage.clickOnYesDeleteButton();
        fileSystemPage.verifyFileHasBeenDeletedIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Test Empty Trash Button");

        //6.    Copy Complete Subject Table and Folder A, Send to Trash for Testing Purposes
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        fileSystemPage.rightClickCopiedFolderAAndTrash();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnCopyFromRightClickMenu();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        fileSystemPage.verifyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.rightClickCopiedCompleteSubjectTableAndTrash();
        logger.log(LogStatus.PASS, "Successfully made copies of files and Trashed");

        //7.    Navigate to Trash page
        filePage.clickOnMainMenuTrashIcon();
        logger.log(LogStatus.INFO, "Successfully navigate to Trash Folder");

        //8.    Click on Empty Trash Button and Select Cancel
        trashPage.clickEmptyTrashButtonOnTrashPage();
        trashPage.verifyEmptyTrashMessageIsDisplayed();
        trashPage.clickOnEmptyTrashCancelButton();
        fileSystemPage.verifyCopyCompleteSubjectTableCopyIsDisplayed();
        fileSystemPage.verifyTestFolderACopyIsDisplayed();
        trashPage.clickEmptyTrashButtonOnTrashPage();
        trashPage.verifyEmptyTrashMessageIsDisplayed();
        trashPage.clickOnYesDeleteButton();
        fileSystemPage.verifyFileHasBeenDeletedIsDisplayed();
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.PASS, "Successfully Test Empty Trash Button");




        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldVerifyTrashEmptyTrashFunctionality"}, enabled = false)
    public void shouldVerifyNoTrashAndCopyFunctionalityInSystemFolder() throws Exception {

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
        myDataPage.verifyCopyToHamburgerIsDisplayed();
        myDataPage.verifyMoreDetailsHamburgerIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Copy To is displayed");

        //3.    Right Click on Cares Folder and verify dropdown options
        myDataPage.rightClickCaresFolder();
        myDataPage.verifyCopyToRightClickIsDisplayed();
        myDataPage.verifyOpenInRightClickIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify right click menu option is displayed");
        
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
            logger.log(LogStatus.FAIL, "TrashFolderAndActionFunctionality", imagePath);

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
        //driver.quit();
        logger.log(LogStatus.INFO, "Tear down Successful");
        report.endTest(logger);
        report.flush();

    }


}
