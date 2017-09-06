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
import Page.classes.TrashPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExcelReader;
import Util.ExtentFactory;

/**
 * Created by will.okamuneh on 11/10/2016.
 * AT- 77
 * Test Automation for EMA- 1105
 */

public class RightClickMenuFunctionality {

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


    @Test (dependsOnMethods={"shouldLogInEM"}, enabled = false)
    public void shouldCreateTestTable() throws Exception {

        logger = report.startTest("Successfully create Files and Folder");


        //1.    Import Complete Subject Table
        Util.Constants.refreshPage();
        filePage.clickNewAndImportTable();
        importPage.clickBrowseFilesButton();
        //importPage.upLoadDiaryCSVTableTypeFile(); -> Replace with QA Table
        filePage.clickDiaryRadioButton();
        importPage.fillInTableNameTextBox("Complete Diary Table");
        importPage.clickImportButton();
        filePage.importStartedMessageIsDisplayed();
        //importPage.verifyDiaryCSVFileImportIsCompleted(); -> Replace with QA Table
        logger.log(LogStatus.PASS, "Successfully import Subject Table");



        report.endTest(logger);

    }


    @Test (dependsOnMethods = {"shouldCreateTestTable"}, enabled = false)
    public void shouldTestCopyCutPasteClearFunctionality() throws Exception {

        logger = report.startTest("Successfully test Copy, Cut, Paste and Clear functionality");



        //1.    Navigate to a folder that contains a populated table and open a table
        filePage.clickFileLinkOnMainMenuBar();
        //importPage.openImportedDiaryTableFromMyDataPage(); -> Replace with QA Table
        importPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully open Table");

        //2.    Right click on one of the cells and select 'Copy' option
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickCopyDropDownMenuOption();
        logger.log(LogStatus.INFO, "Successfully Copy Cell Data");

        //3.    Select another cell and right click -> Paste
        editorPage.rightClickEditorSubjectCodeCellRow_1_Col_0();
        editorPage.clickPasteDropDownMenuOption();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully Paste Cell Data");

        //4.    Verify Copied Data is displayed in Cell
        editorPage.verifyCopyDataInCell_0_1_IsDisplayed();
        logger.log(LogStatus.PASS, "Successfully copy Cell data");

        //5.    Right click on one of the cells and select 'Cut' option
        editorPage.rightClickEditorSubjectCodeCellRow_1_Col_0();
        editorPage.clickCutDropDownMenuOption();
        logger.log(LogStatus.INFO, "Successfully Cut Cell Data");

        //6.    Select another cell and right click -> Paste
        editorPage.rightClickEditorSubjectCodeCell_Row_2_Col_0();
        editorPage.clickPasteDropDownMenuOption();
        logger.log(LogStatus.INFO, "Successfully Paste Cell Data");

        //7.   fill in empty rows
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickCopyDropDownMenuOption();
        editorPage.rightClickEditorSubjectCodeCellRow_1_Col_0();
        editorPage.clickPasteDropDownMenuOption();
        editorPage.clickOnRowZero();

        //8.    Right click on one of the rows and select 'Clear' option
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickClearDropDownMenuOption();
        editorPage.verifyCopyDataInCell_0_2_IsDisplayed();
        logger.log(LogStatus.INFO, "Successfully Clear Row 1");

        //9.    Navigate away and back to the table
        editorPage.clickGoBackButton();
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.INFO, "Successfully navigate back to My Data page");

        //10 Navigate back to Table and verify Cell data
        filePage.clickFileLinkOnMainMenuBar();
        //importPage.openImportedDiaryTableFromMyDataPage(); -> Replace with QA Table
        editorPage.verifyCopyDataInCell_0_2_IsDisplayed();
        logger.log(LogStatus.INFO, "Successfully verify table content");




        report.endTest(logger);
    }



    @Test (dependsOnMethods = {"shouldTestCopyCutPasteClearFunctionality"}, enabled = false)
    public void shouldTestInsertAndDeleteRowFunctionality() throws Exception {

        logger = report.startTest("Successfully test Insert and Delete Row functionality");

        //0.    Fill in row 0
        editorPage.clickOnRowOne();
        editorPage.rightClickEditorSubjectCodeCellRow_1_Col_0();
        editorPage.clickCopyDropDownMenuOption();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickPasteDropDownMenuOption();
        editorPage.clickOnRowZero();
        logger.log(LogStatus.INFO, "Successfully fill in row 0");

        //1.    Verify Valid Table is displayed
        Constants.refreshPage();
        importPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully verify table is validated");

        //2.    Right click on a cell and select 'Insert row above' option
        editorPage.clickOnRowOne();
        editorPage.rightClickEditorRowOne();
        editorPage.clickInsertRowAboveDropDownMenuOption();
        logger.log(LogStatus.INFO, "Successfully Insert New Row above");

        //3.    Right click on a cell and select 'Insert row below' option
        editorPage.clickOnRowOne();
        editorPage.rightClickEditorRowOne();
        editorPage.clickInsertRowBelowDropDownMenuOption();
        //Constants.refreshPage();
        logger.log(LogStatus.INFO, "Successfully Insert New Row below");

        //4.    Right click on a cell and select 'Delete row' option
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorRowZero();
        editorPage.clickRemoveRowDropDownMenuOption();
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorRowZero();
        editorPage.clickRemoveRowDropDownMenuOption();
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorRowZero();
        editorPage.clickRemoveRowDropDownMenuOption();
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorRowZero();
        editorPage.clickRemoveRowDropDownMenuOption();
        Constants.refreshPage();
        logger.log(LogStatus.INFO, "Successfully Remove Empty Cells");

        //5.    Verify Valid Table is displayed
        importPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully verify table is validated");



        report.endTest(logger);
    }


    @Test (dependsOnMethods = {"shouldTestInsertAndDeleteRowFunctionality"}, enabled = false)
    public void shouldPrepTableForSortTesting() throws Exception {

        logger = report.startTest("Successfully prep table for sort testing");

        //0.    Copy row 1 and paste on row 2
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickCopyDropDownMenuOption();
        editorPage.rightClickEditorSubjectCodeCellRow_1_Col_0();
        editorPage.clickPasteDropDownMenuOption();
        editorPage.verifyCopyDataInCell_0_1_IsDisplayed();
        logger.log(LogStatus.INFO, "Successfully copy row 1 and paste on row 2");

        //1.    Copy row 1 and paste on row 3
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickCopyDropDownMenuOption();
        editorPage.rightClickEditorSubjectCodeCell_Row_2_Col_0();
        editorPage.clickPasteDropDownMenuOption();
        editorPage.verifyCopyDataInCell_0_2_IsDisplayed();
        logger.log(LogStatus.INFO, "Successfully copy row 1 and paste on row 3");

        //2.    Copy row 1 and paste on row 4
        editorPage.clickOnRowZero();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickCopyDropDownMenuOption();
        editorPage.rightClickEditorSubjectCodeCell_0_3();
        editorPage.clickPasteDropDownMenuOption();
        editorPage.verifyCopyDataInCell_0_3_IsDisplayed();
        logger.log(LogStatus.INFO, "Successfully copy row 1 and paste on row 4");

        //3.    Change Cell data in Row 0 Col 0 for sorting
        //editorPage.clearAndEnterDataIntoCell0_0();
        editorPage.clickOnCell_Row_0_Col_0();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_0_Col_0();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 0 Col 0");

        //3.    Change Cell data in Row 1 Col 0 for sorting
        editorPage.clickOnCell_Row_1_Col_0();
        editorPage.rightClickEditorSubjectCodeCellRow_1_Col_0();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_1_Col_0();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 1 Col 0");

        //4.    Change Cell data in Row 2 Col 0 for sorting
        editorPage.clickOnCell_Row_2_Col_0();
        editorPage.rightClickEditorSubjectCodeCell_Row_2_Col_0();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_2_Col_0();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 2 Col 0");

        //5.    Change Cell data in Row 0 Col 1 for sorting
        editorPage.clickOnCell_Row_0_Col_1();
        editorPage.rightClickEditorSubjectCodeCell_Row_0_Col_1();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_0_Col_1();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 0 Col 1");

        //6.    Change Cell data in Row 1 Col 1 for sorting
        editorPage.clickOnCell_Row_1_Col_1();
        editorPage.rightClickEditorSubjectCodeCell_Row_1_Col_1();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_1_Col_1();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 1 Col 1");

        //7.    Change Cell data in Row 2 Col 1 for sorting
        editorPage.clickOnCell_Row_2_Col_1();
        editorPage.rightClickEditorSubjectCodeCell_Row_2_Col_1();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_2_Col_1();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 1 Col 1");

        //8.    Change Cell data in Row 0 Col 2 for sorting
        editorPage.clickOnCell_Row_0_Col_2();
        editorPage.rightClickEditorSubjectCodeCell_Row_0_Col_2();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_0_Col_2();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 0 Col 2");

        //9.    Change Cell data in Row 0 Col 2 for sorting
        editorPage.clickOnCell_Row_1_Col_2();
        editorPage.rightClickEditorSubjectCodeCell_Row_1_Col_2();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_1_Col_2();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 1 Col 2");

        //10.    Change Cell data in Row 3 Col 2 for sorting
        editorPage.clickOnCell_Row_3_Col_2();
        editorPage.rightClickEditorSubjectCodeCell_Row_3_Col_2();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_3_Col_2();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 3 Col 2");

        //11.    Change Cell data in Row 0 Col 3 for sorting
        editorPage.clickOnCell_Row_0_Col_3();
        editorPage.rightClickEditorSubjectCodeCell_Row_0_Col_3();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_0_Col_3();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 0 Col 3");

        //12.    Change Cell data in Row 1 Col 3 for sorting
        editorPage.clickOnCell_Row_1_Col_3();
        editorPage.rightClickEditorSubjectCodeCell_Row_1_Col_3();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_1_Col_3();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 1 Col 3");

        //12.    Change Cell data in Row 3 Col 3 for sorting
        editorPage.clickOnCell_Row_3_Col_3();
        editorPage.rightClickEditorSubjectCodeCell_Row_3_Col_3();
        editorPage.clickClearDropDownMenuOption();
        editorPage.enterDataInCellRow_3_Col_3();
        editorPage.clickOnRowOne();
        logger.log(LogStatus.INFO, "Successfully change cell data in Row 3 Col 3");



        report.endTest(logger);
    }

    @Test (dependsOnMethods = {"shouldPrepTableForSortTesting"}, enabled = false)
    public void shouldTestAscendingDescendingAndRemoveSortFunctionality() throws Exception {

        logger = report.startTest("Successfully test ascending, descending and remove sort functionality");


        //0.    Right click on a cell and select sort ascending
        Constants.refreshPage();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickSortAscendingDropDownMenuOption();
        editorPage.verifyCopyDataInCell_Row3_Col0_IsDisplayed();
        editorPage.verifyCopyDataInCell_Row1_Col1_IsDisplayed();
        editorPage.verifyCopyDataInCell_Row1_Col2_IsDisplayed();
        editorPage.verifyCopyDataInCell_Row2_Col3_IsDisplayed();
        logger.log(LogStatus.INFO, "Successfully sort cell in ascending order");

        //1.    Right click on the cell where the sorting is applied and select remove sorting
        editorPage.rightClickSubjectCodeTableHead();
        editorPage.clickRemoveSortingDropDownMenuOption();
        logger.log(LogStatus.INFO, "Successfully remove sort");

        //2.    Right click on a cell and select sort descending
        //editorPage.rightClickSubjectCodeTableHead();
        editorPage.rightClickEditorSubjectCodeCellRow_0_Col_0();
        editorPage.clickSortDescendingDropDownMenuOption();
        editorPage.verifyCopyDataInCell_Row1_Col0_IsDisplayed();
        editorPage.verifyCopyDataInCell_Row2_Col1_IsDisplayed();
        editorPage.verifyCopyDataInCell_Row2_Col2_IsDisplayed();
        editorPage.verifyCopyDataInCell_Row3_Col3_IsDisplayed();
        logger.log(LogStatus.INFO, "Successfully sort cell in descending order");

        //3.    Navigate away and back to the table
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Successfully navigate back to My Data page");


        report.endTest(logger);
    }

    @Test (dependsOnMethods = {"shouldTestAscendingDescendingAndRemoveSortFunctionality"}, enabled = false)
    public void shouldTestInsertAndDeleteFieldFunctionality() throws Exception {

        logger = report.startTest("Successfully test insert and delete field functionality");

        //0.    navigate back into table
        filePage.clickFileLinkOnMainMenuBar();
        //importPage.openImportedDiaryTableFromMyDataPage(); -> Replace with QA Table
        editorPage.verifyValidTableImportLabelIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully open Table");

        //1.    Select Insert Menu -> Field and provide a name
        editorPage.clickTablePageInsertMainMenu();
        editorPage.clickTablePageFileMainMenu();
        editorPage.verifyInsertPopDialogLabelIsDisplayed();
        editorPage.verifyInsertPopDialogConfirmButtonIsDisplayed();
        editorPage.verifyInsertPopDialogCancelButtonIsDisplayed();
        editorPage.verifyInsertPopDialogDisableConfirmButtonIsDisplayed();
        editorPage.enterFieldNameInInsertPopUp("Test column");
        editorPage.clickInsertPopUpDialogConfirmButton();
        logger.log(LogStatus.INFO, "Successfully Insert and provide field name");

        //2.    verify user is on table editor page and verify new column
        editorPage.verifyTablePageFileMainMenuIsDisplayed();
        editorPage.verifyTablePageEditMainMenuIsDisplayed();
        editorPage.verifyTablePageViewMainMenuIsDisplayed();
        editorPage.verifyTablePageInsertMainMenuIsDisplayed();
        editorPage.verifyNewTestColumnIsDisplayed();
        logger.log(LogStatus.PASS, "Successfully verify new column is displayed");

        //3.    navigate out and into table and verify new role is still present
        editorPage.clickGoBackButton();
        filePage.clickFileLinkOnMainMenuBar();
        //importPage.openImportedDiaryTableFromMyDataPage(); -> Replace with QA Table
        editorPage.verifyNewTestColumnIsDisplayed();
        logger.log(LogStatus.INFO, "Successfully navigate out and in of table and verify new column is still displayed");

        //4.    Right click on a cell and select 'Delete Field' option
        editorPage.rightClickEditorSubjectCodeCell_Row_0_Col_4();
        editorPage.clickDeleteFieldDropDownMenuOption();
        Constants.refreshPage();
        editorPage.verifyNewTestColumnIsNotDisplayed();
        logger.log(LogStatus.PASS, "Successfully delete New Column");

        //5.     navigate out and into table and verify new role is not displayed
        editorPage.clickGoBackButton();
        filePage.clickFileLinkOnMainMenuBar();
        //importPage.openImportedDiaryTableFromMyDataPage(); -> Replace with QA Table
        editorPage.verifyNewTestColumnIsNotDisplayed();
        editorPage.clickGoBackButton();
        logger.log(LogStatus.INFO, "Successfully navigate out and in of table and verify new column is still displayed");


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
            logger.log(LogStatus.FAIL, "RightClickMenuFunctionality", imagePath);

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
