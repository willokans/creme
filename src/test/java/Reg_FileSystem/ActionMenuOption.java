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
import Page.classes.TrashPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 8/8/2016.
 * AT- 35
 Test Automation for EMA-614
 */
public class ActionMenuOption {


    ExtentReports report;
    ExtentTest logger;

    LogInPage logInPage;
    HomePage homePage;
    DjangoPage djangoPage;
    FilePage filePage;
    ImportPage importPage;
    FileSystemPage fileSystemPage;
    TrashPage trashPage;
    EditorPage editorPage;



    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();




    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\ActionMenuOption.html");
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

//        //0.     Create New Test Folder 1
//        Constants.refreshPage();
//        fileSystemPage.navigateToNewFolderLink();
//        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder A");
//        fileSystemPage.clickNewItemCheckButton();
//        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
//        fileSystemPage.verifyTestFolderAIsDisplayed();
//        logger.log(LogStatus.PASS, "Successfully Create Test Folder A");
//
//        //1.    Create New Test Folder 2
//        Constants.refreshPage();
//        fileSystemPage.navigateToNewFolderLink();
//        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder B");
//        fileSystemPage.clickNewItemCheckButton();
//        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
//        fileSystemPage.verifyTestFolderBIsDisplayed();
//        logger.log(LogStatus.PASS, "Successfully Create Test Folder B");
//
//        //2.    Create New Test Folder 3
//        Constants.refreshPage();
//        fileSystemPage.navigateToNewFolderLink();
//        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder C");
//        fileSystemPage.clickNewItemCheckButton();
//        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
//        fileSystemPage.verifyTestFolderCIsDisplayed();
//        logger.log(LogStatus.PASS, "Successfully Create Test Folder C");

        //4.    Import Complete Subject Table
        Constants.refreshPage();
        filePage.clickNewAndImportTable();

        importPage.selectQAModelFromChooseModelCategory();

        Thread.sleep(10000);
         importPage.clickBrowseFilesButton();


        //importPage.upLoadSubjectCSVTableTypeFile();

        //filePage.clickSubjectRadioButton();
        importPage.fillInTableNameTextBox("Complete Subject Table");
        filePage.clickSingleQuoteRadioButton();
        filePage.clickCommaRadioButton();
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully import Subject Table");

        //5.    Open uploaded file and verify file can't be validated due to incompatible table format
        //importPage.verifySubjectCSVFileImportIsCompleted();
        //importPage.openImportedSubjectTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");

        //6.    Import Complete Food Table
        Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadFoodCSVTableTypeFile();
        filePage.clickFoodRadioButton();
        importPage.fillInTableNameTextBox("Complete Food Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully import Food Table");

        //7.    Open uploaded file and verify file can't be validated due to incompatible table format
        //importPage.openImportedFoodTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");

        //8.    Import Complete Diary Table
        Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadDiaryCSVTableTypeFile();
        filePage.clickDiaryRadioButton();
        importPage.fillInTableNameTextBox("Complete Diary Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        Constants.refreshPage();
        logger.log(LogStatus.INFO, "Successfully select Complete Table Type");

        //9.    Open uploaded file and verify file can't be validated due to incompatible table format
        //importPage.openImportedDiaryTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");


        //10.   Create Folder AA and AB in Folder A
        fileSystemPage.openFolderA();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder AA");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder AB");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();

        //11.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();

        //12.   Copy failed Assessment1 from shared data to My Data
        fileSystemPage.clickSharedDataLink();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.clickBatchCopyToButton();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully copy Assessment1 to My Data");

        //12.   Move Complete Assessment from shared data to My Data
        fileSystemPage.clickCompletedAssessment();
        fileSystemPage.clickBatchMoveButton();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenMovedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Moved Assessment to My Data");

        //13.   Verify all 3 assessment files are in My Data Folder
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.verifyFailedAssessment1IsDisplayedInMyDataPage();
        fileSystemPage.verifyCompletedAssessmentIsDisplayedInMyDataPage();
        logger.log(LogStatus.PASS, "Successfully verify all 3 Assessment is displayed in My Data Page");



        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldCreateFoldersFilesAndAssessment"}, enabled = false)
    public void shouldClickOnEachActionOnFolderAAfterRightClickMenuIsSelected() throws Exception {

        logger = report.startTest("Successfully Click on Each Action on Folder After right click");

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

        //6.    Perform 'Open in Tab' Action from right click menu on Folder and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickTestFolderA();
        fileSystemPage.clickOnOpenInTabFromRightClickMenuVerifyAndCloseTab();
        filePage.verifyUserIsOnMyDataPage();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully performed Open in Tab action on Folder");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldClickOnEachActionOnFolderAAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldClickOnEachActionOnSubjectTableAfterRightClickMenuIsSelected() throws Exception {

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

        //2.    Perform 'Download' Action from right click menu on Table and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnDownloadFromRightClickMenu();
        fileSystemPage.verifyExportPageLabelIsDisplayed();
        filePage.clickGoBackAndVerifyPage();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully performed Trash action on Folder");

        //3.    Perform 'Open in Tab' Action from right click menu on Table and clean up
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompleteSubjectTable();
        fileSystemPage.clickOnOpenInTabFromRightClickMenuVerifyTableAndCloseTab();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully performed Open in Tab action on Folder");

        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldClickOnEachActionOnSubjectTableAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldClickOnEachActionOnAssessmentAfterRightClickMenuIsSelected() throws InterruptedException {

        logger = report.startTest("Successfully Click on Each Action on Assessment After right click");


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




        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldClickOnEachActionOnAssessmentAfterRightClickMenuIsSelected"}, enabled = false)
    public void shouldClickHamburgerMenuVerifyDropdownAndCarryOutActionsOnEachOptions() throws Exception {

        logger = report.startTest("Successfully display hamburger Right click dropdown Menu Contents");


        //0.    Click on Hamburger Menu for Test Folder B
        fileSystemPage.clickOnTestFolderBHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Test Folder B");

        //1.    Verify Dropdown Content Options for Folder is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all folder dropdown hamburger menu is displayed");

        //2.    Click on 'More Details', verify Folder Name and Folder Type is displayed
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.clickOnTestFolderBHamburgerMenu();
        fileSystemPage.clickOnMoreDetailsFromHamburgerFolderMenu();
        fileSystemPage.verifyMoreDetailsOnFolderIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Folder name and type");

        //3.    Click on Hamburger Menu for Diary Table
        fileSystemPage.clickOnQATableHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Diary Table");

        //4.    Verify Dropdown Content Option for Table is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerDownloadMenuForTestFolderBIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all File dropdown Hamburger menu is displayed");

        //5.   Perform 'Download' Action from the Hamburger Menu on Table and clean up
        Constants.refreshPage();
        fileSystemPage.clickOnQATableHamburgerMenu();
        fileSystemPage.clickOnDownloadFromHamburgerMenu();
        fileSystemPage.verifyExportPageLabelIsDisplayed();
        fileSystemPage.verifyDownloadTheZipFileButtonIsDisplayed();
        filePage.verifyGoBackButtonIsDisplayedAndEnabled();
        filePage.clickOnGoBackButton();
        logger.log(LogStatus.PASS, "Successfully performed Download action on Table");

        //6.   Click on 'More Details', verify Table Name and Type is displayed
        fileSystemPage.clickOnQATableHamburgerMenu();
        fileSystemPage.clickOnMoreDetailsFromHamburgerFolderMenu();
        //fileSystemPage.verifyMoreDetailsOnTableIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Table name and type");

        //7.    Click on Hamburger Menu for Model Run
        fileSystemPage.clickOnFailedAssessmentHamburgerMenu();
        logger.log(LogStatus.INFO, "Successfully click on Hamburger Menu for Assessment");

        //8.    Verify Dropdown Content Option for Table is displayed
        fileSystemPage.verifyHamburgerCopyMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerCopyToMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoveMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerTrashMenuForTestFolderBIsDisplayed();
        fileSystemPage.verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify all Model Run dropdown Hamburger menu is displayed");
//
        //9.   Perform 'Re-run Assessment' Action from the Hamburger Menu on Model Run and clean up
        fileSystemPage.rightClickCompletedAssessment();
        fileSystemPage.verifyRightClickReRunAssessmentMenuIsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully performed Re-run Assessment action on Model Run");

        //10.   Click on 'More Details', verify Model Run Name and Type is displayed
        fileSystemPage.clickOnFailedAssessmentHamburgerMenu();
        fileSystemPage.clickOnMoreDetailsFromHamburgerFolderMenu();
        fileSystemPage.verifyMoreDetailsOnFailedAssessment1IsDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify Table name and type");


        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldClickHamburgerMenuVerifyDropdownAndCarryOutActionsOnEachOptions"}, enabled = false)
    public void shouldSelectSingleAndBatchAndVerifyActionSelectButtons() throws InterruptedException {

        logger = report.startTest("Successfully Action Single and Batch Select");

        //0.    Single Select a file
        fileSystemPage.clickOnCompleteSubjectTable();
        logger.log(LogStatus.INFO, "Successfully Select File");

        //1.    Verify batch Action Content
        fileSystemPage.verifyTrashButtonIsDisplayed();
        fileSystemPage.verifyMoveButtonIsDisplayed();
        fileSystemPage.verifyCopyToButtonIsDisplayed();
        fileSystemPage.verifyCopyButtonIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Action Content");

        //2.    Deselect selected File and verify batch action is no longer displayed
        fileSystemPage.clickOnCompleteSubjectTable();
        fileSystemPage.verifyTrashButtonIsNotDisplayed();
        fileSystemPage.verifyMoveButtonIsNotDisplayed();
        fileSystemPage.verifyCopyToButtonIsNotDisplayed();
        fileSystemPage.verifyCopyButtonIsNotDisplayed();
        logger.log(LogStatus.INFO, "Successfully deselect selected file");

        //3.    Single select a folder
        fileSystemPage.clickTestFolderA();
        logger.log(LogStatus.INFO, "Successfully Select Folder");

        //4.    Verify batch Action Content
        fileSystemPage.verifyTrashButtonIsDisplayed();
        fileSystemPage.verifyMoveButtonIsDisplayed();
        fileSystemPage.verifyCopyToButtonIsDisplayed();
        fileSystemPage.verifyCopyButtonIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Action Content");

        //5.    Deselect selected folder and verify batch action is no longer displayed
        fileSystemPage.clickTestFolderA();
        fileSystemPage.verifyTrashButtonIsNotDisplayed();
        fileSystemPage.verifyMoveButtonIsNotDisplayed();
        fileSystemPage.verifyCopyToButtonIsNotDisplayed();
        fileSystemPage.verifyCopyButtonIsNotDisplayed();
        logger.log(LogStatus.INFO, "Successfully deselect selected folder");

        //6.    Single select a Assessment
        fileSystemPage.clickFailedAssessment1();
        logger.log(LogStatus.INFO, "Successfully Select Assessment");

        //7.    Verify batch Action Content
        fileSystemPage.verifyTrashButtonIsDisplayed();
        fileSystemPage.verifyMoveButtonIsDisplayed();
        fileSystemPage.verifyCopyToButtonIsDisplayed();
        fileSystemPage.verifyCopyButtonIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Action Content");

        //8.    Deselect selected Assessment and verify batch action is no longer displayed
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.verifyTrashButtonIsNotDisplayed();
        fileSystemPage.verifyMoveButtonIsNotDisplayed();
        fileSystemPage.verifyCopyToButtonIsNotDisplayed();
        fileSystemPage.verifyCopyButtonIsNotDisplayed();
        logger.log(LogStatus.INFO, "Successfully deselect selected file");

        //9.    Select one of each Table, Folder and Assessment
        fileSystemPage.clickOnCompleteSubjectTable();
        fileSystemPage.clickTestFolderA();
        fileSystemPage.clickFailedAssessment1();
        logger.log(LogStatus.INFO, "Successfully Select each Type");

        //10.   Verify batch Action Content
        fileSystemPage.verifyTrashButtonIsDisplayed();
        fileSystemPage.verifyMoveButtonIsDisplayed();
        fileSystemPage.verifyCopyToButtonIsDisplayed();
        fileSystemPage.verifyCopyButtonIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Action Content");

        //11.   Deselect selected folder and verify batch action is no longer displayed
        fileSystemPage.clickOnCompleteSubjectTable();
        fileSystemPage.clickTestFolderA();
        fileSystemPage.clickFailedAssessment1();
        fileSystemPage.verifyTrashButtonIsNotDisplayed();
        fileSystemPage.verifyMoveButtonIsNotDisplayed();
        fileSystemPage.verifyCopyToButtonIsNotDisplayed();
        fileSystemPage.verifyCopyButtonIsNotDisplayed();
        Constants.refreshPage();
        logger.log(LogStatus.INFO, "Successfully deselect selected and verify Action button is not Displayed");



        report.endTest(logger);
    }




    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "ActionMenuOption", imagePath);

        }
        report.endTest(logger);
    }




    @AfterClass
    public void tearDown() throws Exception {

        logger = report.startTest("Start tear down");
        

        //0.    Move Completed Assessment to Shared Data
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompletedAssessment();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectSharedDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenMovedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully move Complete Assessment to Shared Data");

        //1.   Click on LogOut
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Successfully log Out");

        //2.   Verify User is Logged Out
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Successfully Logged Out");
        
        //5. Enter Valid email/Valid password
        logInPage.fillInEmailTextBox(CredentialsData.getEMPermanentUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getNewPassword());
        logger.log(LogStatus.INFO, "Successfully populate email/Valid password");

        //6. Click Login button
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Successfully Click on Login Button");

        //7. Verify User is LogIn Successfully 
        homePage.verifyHomePage();
        logger.log(LogStatus.PASS, "Verify that User is on the Home Page");

        //8.    Move Complete Assessment to My Data
        fileSystemPage.clickSharedDataLink();
        fileSystemPage.rightClickCompletedAssessment();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectMyDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenMovedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully move Complete Assessment to Shared Data");

        //9.    Move Complete Assessment back to Shared Data
        filePage.clickFileLinkOnMainMenuBar();
        fileSystemPage.rightClickCompletedAssessment();
        fileSystemPage.clickOnMoveFromRightClickMenu();
        fileSystemPage.selectSharedDataFromFolderDialogBox();
        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
        fileSystemPage.verifyFileHasBeenMovedIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully move Complete Assessment to Shared Data");

        //10. Verify file is displayed in Shared Data
        fileSystemPage.clickSharedDataLink();
        fileSystemPage.verifyCompletedAssessmentIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Completed Assessment is displayed in shared Data");
        
        //11.   Click on LogOut
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Successfully log Out");

        //12.   Verify User is Logged Out
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Successfully Logged Out");

        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getFileSystemUser());
        logger.log(LogStatus.PASS, "File System user has been deleted");	      
        logInPage.verifyLoginAccessDenied(CredentialsData.getFileSystemUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");
     
        //28.   Clear cache and quit browser
        //driver.close();
        Util.Constants.clearBrowserCache();
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }
}
