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
 * Created by will.okamuneh on 9/6/2016.
 * AT-44
 Test Automation for EMA-623
 */
public class SortFunctionality {


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


    public WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\SortFunctionality.html");
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
    public void shouldModifyEachItemTypeInMyDataFolder() throws Exception {

        logger = report.startTest("Successfully modify each item type in my data folder");

        //0.    Click on Test Folder A edit Icon
        fileSystemPage.clickOnTestFolderAPencilIcon();
        logger.log(LogStatus.INFO, "Successfully click Folder A");

        //1.    Click on Test Folder A TextBox and Click Save with blank Name
        fileSystemPage.clickOnTextFolderATextBoxAndClearName();
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyTestFolderATextBoxIsStillDisplayed();
        fileSystemPage.clickNewItemClearButton();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully unsuccessfully save blank name Folder ");

        //2.    Click on Test Folder A and click on Save with New Folder name
        fileSystemPage.clickOnTestFolderAPencilIcon();
        fileSystemPage.clickOnTextFolderATextBoxAndClearName();
        fileSystemPage.enterNewFolderName("New Folder Name");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyNewFolderNameIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify display of New Folder Name");

        //6.    Click on Assessment edit Icon
        Thread.sleep(6000);
        fileSystemPage.clickOnFailedAssessment1PencilIcon();
        logger.log(LogStatus.INFO, "Successfully click Failed Assessment1");

        //7.    Click on Assessment and Click Save with blank Name
        fileSystemPage.clickOnFailedAssessment1TextBoxAndClearName();
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyCheckAndCancelIsDisplayed();
        fileSystemPage.clickNewItemClearButton();
        fileSystemPage.verifyFailedAssessment1IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully unsuccessfully save blank name Assessment ");

        //8.    Click on Test Folder A and click on Save with New Folder name
        fileSystemPage.clickOnFailedAssessment1PencilIcon();
        fileSystemPage.clickOnFailedAssessment1TextBoxAndClearName();
        fileSystemPage.enterNewFolderName("New Assessment Name");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyNewAssessmentNameIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify display of New Assessment Name");

        //3.    Click on Subject Table edit Icon
        Thread.sleep(6000);
        fileSystemPage.clickOnCompletedSubjectTablePencilIcon();
        logger.log(LogStatus.INFO, "Successfully click Completed Subject Table");

        //4.    Click on Subject Table and Click Save with blank Name
        fileSystemPage.clickOnCompletedSubjectTableTextBoxAndClearName();
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyCheckAndCancelIsDisplayed();
        fileSystemPage.clickNewItemClearButton();
        fileSystemPage.verifyCompletedSubjectTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully unsuccessfully save blank name table ");

        //5.    Click on Test Folder A and click on Save with New Folder name
        fileSystemPage.clickOnCompletedSubjectTablePencilIcon();
        fileSystemPage.clickOnCompletedSubjectTableTextBoxAndClearName();
        fileSystemPage.enterNewFolderName("New Table Name");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyNewTableNameIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify display of New table Name");



        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldModifyEachItemTypeInMyDataFolder"}, enabled = false)
    public void shouldValidateSortFunctionality() throws Exception {

        logger = report.startTest("Successfully test sort Functionality on My Data Page");

        //0.    Verify Sort label display in My Data folder
        fileSystemPage.verifySortLabelIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify sort link is displayed");

        //1.    click on sort label and verify drop down content
        fileSystemPage.clickSortLabelLink();
        fileSystemPage.verifySortByNameIsDisplayed();
        fileSystemPage.verifySortByModifiedIsDisplayed();
        fileSystemPage.verifySortByCreatedIsDisplayed();
        fileSystemPage.verifySortByTypeIsDisplayed();
        fileSystemPage.verifySortByOwnerIsDisplayed();
        Util.Constants.refreshPage();
        logger.log(LogStatus.PASS, "Successfully verify sort option content is correctly displayed");

        //2.    Sort by Name and verify items are sorted by name
        fileSystemPage.clickSortLabelByNameLink();
        fileSystemPage.verifyItemListSortedByName();
        fileSystemPage.verifySortByNameXIconIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by name");

        //3.    cancel Name sorting and verify item list order switch back to default order
        fileSystemPage.clickSortLabelCancelLink();
        fileSystemPage.verifyItemListReturnsToDefaultOrderAfterCancellingSortByName();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by default");

        //4.    Sort by Created and verify items are sorted by name
        fileSystemPage.clickSortLabelByCreatedLink();
        fileSystemPage.verifyItemListSortedByCreated();
        fileSystemPage.verifySortByCreatedXIconIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by name");

        //5.    cancel Name sorting and verify item list order switch back to default order
        fileSystemPage.clickSortLabelCancelLink();
        fileSystemPage.verifyItemListReturnsToDefaultOrderAfterCancellingSortByName();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by default");

        //6.    Sort by Type and verify items are sorted by name
        fileSystemPage.clickSortLabelByTypeLink();
        fileSystemPage.verifyItemListSortedByType();
        fileSystemPage.verifySortByTypeFolderLabelIsDisplayed();
        fileSystemPage.verifySortByTypeJobLabelIsDisplayed();
        fileSystemPage.verifySortByTypeTableLabelIsDisplayed();
        fileSystemPage.verifySortByCreatedXIconIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by name");

        //7.    cancel Name sorting and verify item list order switch back to default order
        fileSystemPage.clickSortLabelCancelLink();
        fileSystemPage.verifyItemListReturnsToDefaultOrderAfterCancellingSortByName();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by default");

        //8.    Sort by Type and verify items are sorted by name
        fileSystemPage.clickSortLabelByOwnerLink();
        fileSystemPage.verifyItemListSortedByOwner();
        fileSystemPage.verifySortByCreatedXIconIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by name");

        //9.    cancel Name sorting and verify item list order switch back to default order
        fileSystemPage.clickSortLabelCancelLink();
        fileSystemPage.verifyItemListReturnsToDefaultOrderAfterCancellingSortByName();
        logger.log(LogStatus.PASS, "Successfully verify items list is sorted and displayed by default");

        //0.   Click on LogOut
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Successfully log Out");

        //1.   Verify User is Logged Out
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Successfully Logged Out");

        //fileSystemPage.getDefaultListOfItemsOnMyDataPage();
        report.endTest(logger);

    }




    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Util.Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "SortFunctionality", imagePath);

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
        Constants.clearBrowserCache();
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }

}
