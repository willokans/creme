package WIP;

import Page.classes.*;
import Util.EMDriverSingleton;
import Util.ExcelReader;
import Util.ExtentFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by will.okamuneh on 9/7/2016.
 * AT-57
 Test Automation for EMA-795
 */
public class DataSetsInformationFunctionality {



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


    private WebDriver driver = EMDriverSingleton.getChromeInstanceDriver();


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

    }





    @Test
    public void shouldCreateInActiveUserProfileInAPI() throws Exception {

        logger = report.startTest("DATA SET FUNCTIONALITY TEST CASE");


        //0.   Got to URL
        djangoPage.goToAPIURL(ExcelReader.API_URL());
        logger.log(LogStatus.INFO, "Navigated to Django API URL");


        //1.    Verify User is on Django Log In page
        djangoPage.verifyDjangoLogInPage();
        logger.log(LogStatus.PASS, "Verify LogIn Page");


        //2.    Enter Valid email and valid password
        djangoPage.fillInEmailTextBox(ExcelReader.djangoEmail());
        djangoPage.fillInPasswordTextBox(ExcelReader.djangoPassword());
        logger.log(LogStatus.INFO, "Successfully enter Valid email/Valid password");


        //3.    Click on Login button
        djangoPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Successfully click on Login button");


        //4.    Click on PX users link
        djangoPage.clickPXUserLink();
        logger.log(LogStatus.INFO, "Successfully click on Px Users Link");


        //5.    Verify Use is on PX User to change page
        djangoPage.verifyDjangoHomePage();
        logger.log(LogStatus.PASS, "User is on PX User to Change Page");


        //6.    Click on ADD PX User Link
        djangoPage.clickAddPXUserLink();
        logger.log(LogStatus.INFO, "Successfully click on Add Px User Link");


        //7.    Fill in First, Second Name and email add
        djangoPage.fillInRegFirstNameTextBox(ExcelReader.django_FirstName());
        djangoPage.fillInRegLastNameTextBox(ExcelReader.django_LastName());
        djangoPage.fillInRegEmailTextBox(ExcelReader.EM_EmailAddress());
        logger.log(LogStatus.INFO, "Successfully filled in first, last name & email");


        //8.    Select Creme Global QA Dept from dropdown
        djangoPage.selectOrganisation("Creme Global QA Dept.");
        logger.log(LogStatus.INFO, "Successfully Select Creme Global QA Dept form dropdown");


        //9.    Fill in password, password Confirmation text box and check Is Active Checkbox
        djangoPage.fillInRegPasswordTextBox(ExcelReader.EM_Password());
        djangoPage.fillInRegPasswordConfirmationTextBox(ExcelReader.EM_Password());
        djangoPage.clickIsActiveCheckBox();
        logger.log(LogStatus.INFO, "Successfully filled in password and password confirmation");


        //11.    Scroll to Save Link button
        djangoPage.clickOnRegSaveButton();
        logger.log(LogStatus.INFO, "Successfully scroll to Save Link Button and Click Save");


        //12.    Log Out of API
        djangoPage.logOut();
        logger.log(LogStatus.INFO, "Successfully Click Log Out button");


        //13.	Verify user is logged Out
        djangoPage.verifyLoggedOut();
        logger.log(LogStatus.PASS, "Successfully Logged Out");


        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldCreateInActiveUserProfileInAPI"})
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


    @Test (dependsOnMethods={"shouldLogInEM"})
    public void shouldCreateFoldersFilesAndAssessment() throws Exception {

        logger = report.startTest("Successfully create Files and Folder");

//        //0.     Create New Test Folder 1
//        Util.Constants.refreshPage();
//        fileSystemPage.navigateToNewFolderLink();
//        fileSystemPage.enterTestFolderNameIntoNewFolderTextBox("Test Folder A");
//        fileSystemPage.clickNewItemCheckButton();
//        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
//        fileSystemPage.verifyTestFolderAIsDisplayed();
//        logger.log(LogStatus.PASS, "Successfully Create Test Folder A");
//
//        //1.    Create New Test Folder 3
//        Util.Constants.refreshPage();
//        fileSystemPage.navigateToNewFolderLink();
//        fileSystemPage.enterTestFolderNameIntoNewFolderTextBox("Test Folder C");
//        fileSystemPage.clickNewItemCheckButton();
//        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
//        fileSystemPage.verifyTestFolderCIsDisplayed();
//        logger.log(LogStatus.PASS, "Successfully Create Test Folder C");
//
//        //2.    Import Complete Subject Table
//        Util.Constants.refreshPage();
//        filePage.navigateToUploadTable();
//        filePage.clickBrowserFileButton();
//        filePage.upLoadSubjectCSVTableTypeFile();
//        filePage.clickSubjectRadioButton();
//        filePage.fillInTableNameTextBox("Complete Subject Table");
//        importPage.clickImportButton();
//        filePage.uploadStartedMessageIsDisplayed();
//        filePage.cancelImportFileNotification();
//        logger.log(LogStatus.PASS, "Successfully import Subject Table");
//
//        //3.    Import Complete Diary Table
//        Constants.refreshPage();
//        filePage.navigateToUploadTable();
//        filePage.clickBrowserFileButton();
//        filePage.upLoadDiaryCSVTableTypeFile();
//        filePage.clickDiaryRadioButton();
//        filePage.fillInTableNameTextBox("Complete Diary Table");
//        importPage.clickImportButton();
//        filePage.uploadStartedMessageIsDisplayed();
//        filePage.cancelImportFileNotification();
//        logger.log(LogStatus.INFO, "Successfully select Complete Table Type");
//
//        //4.    Open uploaded file and verify file can't be validated due to incompatible table format
//        importPage.openImportedSubjectTableFromMyDataPage();
//        importPage.verifyValidTableImportLabelIsDisplayed();
//        importPage.clickOnArrowBackInImportedTablePage();
//        logger.log(LogStatus.PASS, "Successfully open and verify file");
//
//        //5.   Create Folder AA and AB in Folder A
//        fileSystemPage.openFolderA();
//        fileSystemPage.navigateToNewFolderLink();
//        fileSystemPage.enterTestFolderNameIntoNewFolderTextBox("Test Folder AA");
//        fileSystemPage.clickNewItemCheckButton();
//        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
//        Util.Constants.refreshPage();
//        fileSystemPage.navigateToNewFolderLink();
//        fileSystemPage.enterTestFolderNameIntoNewFolderTextBox("Test Folder AB");
//        fileSystemPage.clickNewItemCheckButton();
//        fileSystemPage.verifyYourFolderHasBeenCreatedMessageIsDisplayed();
//
//        //6.   Import Complete Subject Table
//        Util.Constants.refreshPage();
//        filePage.navigateToUploadTable();
//        filePage.clickBrowserFileButton();
//        filePage.upLoadSubjectCSVTableTypeFile();
//        filePage.clickSubjectRadioButton();
//        filePage.fillInTableNameTextBox("Complete Subject Table");
//        importPage.clickImportButton();
//        filePage.uploadStartedMessageIsDisplayed();
//        filePage.cancelImportFileNotification();
//        logger.log(LogStatus.PASS, "Successfully import Subject Table");
//
//        //7.   Click on File Link
//        filePage.clickFileLinkOnMainMenuBar();
//        Util.Constants.refreshPage();
//        fileSystemPage.openFolderA();
//
//        //8.   Import Complete Food Table
//        Util.Constants.refreshPage();
//        filePage.navigateToUploadTable();
//        filePage.clickBrowserFileButton();
//        filePage.upLoadFoodCSVTableTypeFile();
//        filePage.clickFoodRadioButton();
//        filePage.fillInTableNameTextBox("Complete Food Table");
//        importPage.clickImportButton();
//        filePage.uploadStartedMessageIsDisplayed();
//        filePage.cancelImportFileNotification();
//        logger.log(LogStatus.PASS, "Successfully import Food Table");
//
//        //9.   Click on File Link
//        filePage.clickFileLinkOnMainMenuBar();
//
//        //10.   Moved assessment from shared data to My Data
//        fileSystemPage.clickSharedDataLink();
//        fileSystemPage.clickFailedAssessment1();
//        fileSystemPage.clickBatchCopyToButton();
//        fileSystemPage.selectMyDataFromFolderDialogBox();
//        fileSystemPage.selectMoveOrCopyToFromFilePickerDialogBox();
//        fileSystemPage.verifyFileHasBeenCopiedIsDisplayed();
//        logger.log(LogStatus.PASS, "Successfully Moved all 3 Assessment to My Data");
//
//        //11.   Verify all 3 assessment files are in My Data Folder
//        filePage.clickFileLinkOnMainMenuBar();
//        fileSystemPage.verifyFailedAssessment1IsDisplayedInMyDataPage();
//        logger.log(LogStatus.PASS, "Successfully verify Assessment1 is displayed in My Data Page");



        report.endTest(logger);

    }




    @Test (dependsOnMethods={"shouldCreateFoldersFilesAndAssessment"})
    public void shouldVerifyDataSetFolderInMyEMDataSubFolder() throws Exception {

        logger = report.startTest("Successfully verify the present of Data Set Folder");


        //0.    Navigate to DataSet in My EM Data Folder
        myDataPage.clickMainPageMyEMDataLink();
        myDataPage.verifyMyEMDataPage();
        myDataPage.openNHANESTwoDayFolder();
        myDataPage.verifyDataSetFolderIconIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully navigate and verify to data Set folder");

        //1.    Open Data Set folder and verify it contents items
        myDataPage.open2012DataSet();
        myDataPage.verify2012DataSetPage();
        myDataPage.verifyYouHaveNoFilesToListIsNOTDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify folder contains items");



        report.endTest(logger);

    }



    @Test (dependsOnMethods={"shouldVerifyDataSetFolderInMyEMDataSubFolder"})
    public void shouldVerifyDataSetMoreInfoDetails() throws Exception {

        logger = report.startTest("Successfully verify DataSet folder can not be copied");

        //0.    Navigate to Data Set Folder
        myDataPage.clickMainPageMyEMDataLink();
        myDataPage.verifyMyEMDataPage();
        myDataPage.openNHANESTwoDayFolder();
        myDataPage.verifyDataSetFolderIconIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully navigate and verify to data Set folder");

        //1.   Verify DataSet hamburger menu contains 'More Detail' option
        myDataPage.clickDataSetHamburgerMenu();
        myDataPage.verifyDataSetHamburgerMoreDetailsMenuIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Verify DataSet Hamburger More Details Menu is displayed");

        //2.    Click and verify Hamburger More Details Menu
        myDataPage.clickDataSetHamburgerMoreDetailsMenu();
        myDataPage.verifyHamburgerMoreDetailsOptionsAreDisplayed();
        logger.log(LogStatus.PASS, "Successfully Verify DataSet Hamburger More Details");

        //3.    Verify Other DataSet and Folders not present in DataSet Folder
        myDataPage.clickMainPageMyEMDataLink();
        myDataPage.verifyMyEMDataPage();
        myDataPage.openNHANESTwoDayFolder();
        myDataPage.open2012DataSet();
        myDataPage.verifyYouHaveNoFilesToListIsNOTDisplayed();
        myDataPage.verifyDataSetIconIsNotDisplayedInDropDown();
        myDataPage.verifyFolderIsNOTDisplayed();
        myDataPage.verifyDataSetMoreInfoIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully Verify no folder or dataSet within a DataSet");

        //4.    Click on Data-set info Icon and verify content
        myDataPage.clickDataSetMoreInfoIcon();
        myDataPage.verifyDataSetWarningInfoIsDisplayed();
        myDataPage.verifyDataSetDescriptionInfoIsDisplayed();
        myDataPage.verifyDataSetDetailsLabelIsDisplayed();
        myDataPage.verifyDataSetTablesLabelIsDisplayed();
        myDataPage.clickDataSetMoreInfoBackArrowButton();
        logger.log(LogStatus.PASS, "Successfully Verify DataSet info Page");



        report.endTest(logger);

    }


    @Test (dependsOnMethods={"shouldVerifyDataSetMoreInfoDetails"})
    public void shouldVerifyDataSetMoreDetailsADataSetTable() throws Exception {

        logger = report.startTest("Successfully More Details Functionality for a dataset table");

        //0.    Navigate to DataSet
        myDataPage.clickMainPageMyEMDataLink();
        myDataPage.verifyMyEMDataPage();
        myDataPage.openNHANESTwoDayFolder();
        myDataPage.open2012DataSet();
        logger.log(LogStatus.PASS, "Successfully navigate into data-set folder");

        //1.    Click on More Detail Hamburger Icon Link
        myDataPage.clickDataSetSubjectHamburgerLink();
        myDataPage.clickDataSetHamburgerMoreDetailsMenu();
        myDataPage.verifyHamburgerMoreDetailsOptionsForSubjectTableAreDisplayed();




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

        logger = report.startTest("Tear Down Successfully");


        //0.   Click on LogOut
        homePage.loggingOut();
        logger.log(LogStatus.INFO, "Successfully log Out");

        //1.   Verify User is Logged Out
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Successfully Logged Out");


        //2.   Got to URL
        driver.get(ExcelReader.API_URL());
        logger.log(LogStatus.INFO, "Navigated to Django API URL");


        //3.    Verify User is on Django Log In page
        djangoPage.verifyDjangoLogInPage();
        logger.log(LogStatus.PASS, "Verify LogIn Page");


        //4.    Enter Valid email and valid password
        djangoPage.fillInEmailTextBox(ExcelReader.djangoEmail());
        djangoPage.fillInPasswordTextBox(ExcelReader.djangoPassword());
        logger.log(LogStatus.INFO, "Successfully enter Valid email/Valid password");


        //5.    Click on Login button
        djangoPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Successfully click on Login button");


        //6.    Verify User is Logged Into Django HomePage
        djangoPage.verifyDjangoHomePage();
        logger.log(LogStatus.PASS, "Successfully navigate to Django Home Page");


        //7.    Click on Px Users
        djangoPage.clickPXUserLink();
        logger.log(LogStatus.INFO, "Successfully Click on PX User Button");


        //8.    Click on QA Link
        djangoPage.qaEmailLink();
        logger.log(LogStatus.INFO, "Successfully Click on creme email link");

        //9.    Click on Delete Link
        djangoPage.clickDeleteButton();
        logger.log(LogStatus.INFO, "Successfully Click on Delete Button");

        //10.    Click on Yes I'm Sure Button
        djangoPage.clickYesImSureButton();
        logger.log(LogStatus.INFO, "Successfully Click on Yes I'm sure Button");

        //11.    Verify User is deleted successfully
        djangoPage.verifyUserDeleteMessageQAUserProfileIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully delete User");

        //12.	Click on Log Out Link
        djangoPage.logOut();
        logger.log(LogStatus.INFO, "Successfully Click Log Out button");

        //13.	Verify user is logged Out
        djangoPage.verifyLoggedOut();
        logger.log(LogStatus.PASS, "Successfully Logged Out");

        //14.	Got to EM URL
        driver.get(ExcelReader.EM_URL());
        logger.log(LogStatus.INFO, "Navigated to Expert Model URL");

        //15 . Enter Valid email/Valid password
        logInPage.fillInEmailTextBox(ExcelReader.EM_EmailAddress());
        logInPage.fillInPasswordTextBox(ExcelReader.EM_Password());
        logger.log(LogStatus.INFO, "Successfully populate email/Valid password");

        //16. Click Login button
        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Successfully Click on Login Button");

        //17. Verify user can't signed in
        logInPage.verifyLogInPage();
        logger.log(LogStatus.PASS, "Successfully can't log in");


        //18.   Clear cache and quit browser
        driver.close();
        Util.Constants.clearBrowserCache();
        logger.log(LogStatus.PASS, "Tear Down Successfully");

        report.endTest(logger);
        report.flush();

    }
}
