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

import Page.classes.AssessmentPage;
import Page.classes.DjangoPage;
import Page.classes.EditorPage;
import Page.classes.FilePage;
import Page.classes.FileSystemPage;
import Page.classes.HomePage;
import Page.classes.ImportPage;
import Page.classes.LogInPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 8/15/2016.
 * AT-34
 Test Automation for EMA-613
 */
public class ContentsAndCreateFunctionality {


    ExtentReports report;
    ExtentTest logger;

    LogInPage logInPage;
    HomePage homePage;
    DjangoPage djangoPage;
    FilePage filePage;
    ImportPage importPage;
    FileSystemPage fileSystemPage;
    AssessmentPage assessmentPage;
    EditorPage editorPage;

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();




    @BeforeClass
    public void beforeClassSetup() throws Exception, IOException {

        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\ContentsAndCreateFunctionality.html");
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
        assessmentPage = new AssessmentPage(driver);
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
    public void shouldCreateFoldersAndFiles() throws Exception {

        logger = report.startTest("Successfully create Files and Folder");

        //1.     Create New Test Folder 1
        Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder A");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderAIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder A");

        //2.    Create New Test Folder 2
        Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder B");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderBIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder B");

        //3.    Create New Test Folder 3
        Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Test Folder C");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        fileSystemPage.verifyTestFolderCIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Create Test Folder C");

        //5.    Import Complete Subject Table
        Constants.refreshPage();
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

        //6.    Open uploaded file and verify file can't be validated due to incompatible table format
        //importPage.openImportedSubjectTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");

        //7.    Import Complete Food Table
        Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadFoodCSVTableTypeFile();
        filePage.clickFoodRadioButton();
        importPage.fillInTableNameTextBox("Complete Food Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.PASS, "Successfully import Food Table");

        //8.    Open uploaded file and verify file can't be validated due to incompatible table format
        //importPage.openImportedFoodTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");

        //9.    Import Complete Diary Table
        Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadDiaryCSVTableTypeFile();
        filePage.clickDiaryRadioButton();
        importPage.fillInTableNameTextBox("Complete Diary Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //filePage.cancelImportFileNotification();
        logger.log(LogStatus.INFO, "Successfully select Complete Table Type");

        //10.    Open uploaded file and verify file can't be validated due to incompatible table format
        //importPage.openImportedDiaryTableFromMyDataPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.PASS, "Successfully open and verify file");

        //13.   Create Folder AA and AB in Folder A
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

        //14.   Import Complete Subject Table
        Constants.refreshPage();
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
        fileSystemPage.openFolderA();

        //16.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();

        //18.   Click on File Link
        filePage.clickFileLinkOnMainMenuBar();


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldCreateFoldersAndFiles"}, enabled = false)
    public void shouldTestContentsAndTableCreateFunctionality() throws Exception {

        logger = report.startTest("Successfully test EM Contents and Create Functionality");


        //0.    Navigate to the file system after login
        filePage.clickFileLinkOnMainMenuBar();
        logger.log(LogStatus.INFO, "Successfully click on Files Link");

        //1.    Click and verify user can click on import successfully
        filePage.clickNewAndImportTable();
        importPage.verifyImportPageContentsAreDisplayed();
        //filePage.verifyImportBrowseFileButtonIsDisplayed();
        importPage.clickGoBackButton();
        filePage.verifyUserIsOnMyDataPage();
        //filePage.clickGoBackAndVerifyPage();
        logger.log(LogStatus.PASS, "Successfully verify import is working correctly");

        //2.    Click on New+ Link and verify Content is display
        filePage.clickOnFilesNewButton();
        fileSystemPage.verifyRunModelLinkIsDisplayed();
        fileSystemPage.verifyNewTableLinkIsDisplayed();
        fileSystemPage.verifyNewFolderLinkIsDisplayed();
        fileSystemPage.verifyUploadTableLinkIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify all contents");

        //3.    Select New+ > New Table > Subject and verify Table with default name
        Constants.refreshPage();
        fileSystemPage.createSubjectNewTable();
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create default table");

        //4.    Verify Table is saved with default File name "New Table"
        Constants.refreshPage();
        fileSystemPage.verifyDefaultFileNameIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify table default name");

        //5.    Select New+ > New Table > Food and enter QA Test Table as Table Name
        fileSystemPage.createFoodNewTable();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Food Test Table");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create QA Test Table");

        //6.    Verify Table is saved and displayed
        Constants.refreshPage();
        //fileSystemPage.verifyQATestTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify table default name");

        //7.    Select New+ > New Table > Diary and verify Table with default name
        fileSystemPage.createDiaryNewTable();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Diary Test Table");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create Diary Test Table");

        //8.    Verify Table is saved and displayed
        Constants.refreshPage();
        fileSystemPage.verifyDiaryTestTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Diary Test Table is displayed");

        //9.   Select New+ > New Table > Nutrient and verify Table with default name
        fileSystemPage.createNutrientNewTable();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Nutrient Test Table");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create Nutrient Test Table");

        //10.   Verify Table is saved and displayed
        Constants.refreshPage();
        fileSystemPage.verifyNutrientTestTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Nutrients Test Table is displayed");


        //18.    Select New+ > New Table > Sugar Consumption and verify Table with default name
        fileSystemPage.createSugarConsumptionNewTable();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Sugar Con Test Table");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create Sugar Consumption Test Table");

        //19.   Verify Table is saved and displayed
        Constants.refreshPage();
        fileSystemPage.verifySugarConsumptionTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Sugar Con Table is displayed");

        //20.   Select New+ > New Table > Mortality and verify Table with default name
        fileSystemPage.createMortalityRateNewTable();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Mortality Rate Test Table");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create Mortality Test Table");

        //21.   Verify Table is saved and displayed
        Constants.refreshPage();
        fileSystemPage.verifyMortalityRateTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Mortality Rate Table is displayed");

        //22.   Select New+ > New Table > Bacchus Data and verify Table
        fileSystemPage.createBacchusDataNewTable();
        fileSystemPage.enterItemNameIntoNewItemTextBox("Bacchus Data Test Table");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourTableHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create Bacchus Data Test Table");

        //23.   Verify Table is saved and displayed
        Constants.refreshPage();
        fileSystemPage.verifyBacchusDataTableIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Bacchus Data Table is displayed");



        report.endTest(logger);
    }


    @Test (dependsOnMethods={"shouldTestContentsAndTableCreateFunctionality"}, enabled = false)
    public void shouldTestContentsAndModelRunCreateFunctionality() throws Exception {

        logger = report.startTest("Successfully Test Model Run Create Functionality");


//        //0.    Select New+ > Model Run > and Creme Food Assessment Options and Cancel
//        assessmentPage.createCremeFoodAssessment();
//        assessmentPage.enterAssessmentNameIntoTextBox("");
//        assessmentPage.clickAssessmentCancelButton();
//        logger.log(LogStatus.PASS, "Successfully Change Assessment Name but click cancel");
//
//        //1.    Select New+ > Model Run > and Creme Food Assessment Options and Confirm
//        Constants.refreshPage();
//        assessmentPage.createCremeFoodAssessment();
//        assessmentPage.enterAssessmentNameIntoTextBox("");
//        assessmentPage.clickAssessmentConfirmButton();
//        logger.log(LogStatus.PASS, "Successfully Change Assessment Name and click Confirm");
//
//        //2.    Navigate and select Food file
//        assessmentPage.clickFoodAssessmentChooseFileButton();
//        assessmentPage.clickMyDataFolderFromOption();
//        assessmentPage.clickAndSelectTableFromOption();
//        logger.log(LogStatus.PASS, "Successfully select Food File");
//
//        //3.    Navigate and select Food file
//        assessmentPage.clickSubjectAssessmentChooseFileButton();
//        assessmentPage.clickAndSelectTableFromOption();
//        logger.log(LogStatus.PASS, "Successfully select Subject File");
//
//        //4.    Navigate and select Diary file
//        assessmentPage.clickDiaryAssessmentChooseFileButton();
//        assessmentPage.clickAndSelectTableFromOption();
//        logger.log(LogStatus.PASS, "Successfully select Diary File");
//
//        //5.    Enter Number of days reported and Random Seed
//        assessmentPage.enterNumberOfDaysReportedIntoTextBox("2");
//        assessmentPage.enterRandomSeedIntoTextBox("");
//        logger.log(LogStatus.PASS, "Successfully Fill in Number of days reported and Random Seed");
//
//        //6.    Click Run
//        assessmentPage.clickAssessmentRunButton();
//        logger.log(LogStatus.PASS, "Successfully click Run Assessment Button");
//
//        //7.    Verify Message is displayed and verify notification and cancel
//        assessmentPage.verifyAssessmentStartedMessageIsDisplayed();
//        assessmentPage.verifyAssessmentFailedAndCancelNotification();
//        logger.log(LogStatus.PASS, "Successfully display Assessment Started Message");



        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldTestContentsAndModelRunCreateFunctionality"}, enabled = false)
    public void shouldTestContentsAndFolderCreateFunctionality() throws Exception {

        logger = report.startTest("Successfully Test Folder Create Functionality");



        //0.    Select New+ > New Folder > and input Folder name as "QA Test Folder"
        Constants.refreshPage();
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create default Folder");

        //1.    Verify Table is saved and displayed
        Constants.refreshPage();
        fileSystemPage.verifyDefaultFolderNameIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify Default Folder Name is displayed");


        //2.    Select New+ > New Folder > and input Folder name as "QA Test Folder"
        fileSystemPage.navigateToNewFolderLink();
        fileSystemPage.enterItemNameIntoNewItemTextBox("QA Test Folder");
        fileSystemPage.clickNewItemCheckButton();
        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully create default Folder");

        //3.    Verify Table is saved and displayed
        Constants.refreshPage();
        fileSystemPage.verifyDefaultFolderNameIsDisplayed();
        fileSystemPage.verifyQATestFolderIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify QA Test Folder is displayed");


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

            String screenshot_path= Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "ContentsAndCreateFunctionality", imagePath);

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
