package Reg_FileSystem;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Page.classes.DjangoPage;
import Page.classes.EMHeaderPage;
import Page.classes.EditorPage;
import Page.classes.FilePage;
import Page.classes.FilePicker;
import Page.classes.FileSystemPage;
import Page.classes.GenericWizards;
import Page.classes.HomePage;
import Page.classes.ImportPage;
import Page.classes.LogInPage;
import Page.classes.MyEMDataPage;
import Page.classes.ResultsViewerPage;
import Page.classes.TrashPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;

/**
 * Created by alan.sheehy 03/02/2017. 
 * Test Automation for EMA-613
 */

public class SystemFolderRedirectAndNavigation {


    ExtentReports report;
    ExtentTest logger;

    LogInPage logInPage;
    HomePage homePage;
    EMHeaderPage emHeaderPage;
    DjangoPage djangoPage;
    FilePage filePage;
    ImportPage importPage;
    FileSystemPage fileSystemPage;
    TrashPage trashPage;
    MyEMDataPage myDataPage;
    EditorPage editorPage;
    GenericWizards genericWizards;
    FilePicker filePicker;
    ResultsViewerPage resultsViewerPage;
    String testCaseStatus = "PASSED";


    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    @BeforeSuite
    public void configLogging() throws IOException{
    	System.out.println("Creating "+CredentialsData.getFileSystemUser()+" and "+CredentialsData.getOtherOrgUser()+" user profiles on: "+CredentialsData.getApiURL());
    	System.out.println("Running file system tests on: "+CredentialsData.getBaseURL());
    }
    
    
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
        emHeaderPage = new EMHeaderPage(driver);
        djangoPage = new DjangoPage(driver);
        filePage = new FilePage(driver);
        importPage = new ImportPage(driver);
        fileSystemPage = new FileSystemPage(driver);
        trashPage = new TrashPage(driver);
        myDataPage = new MyEMDataPage(driver);
        editorPage = new EditorPage(driver);
        genericWizards = new GenericWizards(driver);
        filePicker = new FilePicker(driver);
        resultsViewerPage = new ResultsViewerPage(driver);

    }


    @Test
    public void createActiveUserProfile() throws Exception {

        logger = report.startTest("Create 'File System' test users");
        djangoPage.createUserProfile(CredentialsData.getFileSystemUser());
        djangoPage.createUserInAnotherOrg(CredentialsData.getOtherOrgUser());
        logger.log(LogStatus.PASS, "Test users created");
    }


    @Test (dependsOnMethods={"createActiveUserProfile"}, enabled = true)
    public void loginAndVerifyFSAndHeader () throws Exception {
    	logger = report.startTest("Verify login & File System/Header are displayed");

        driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");

        logInPage.fillInEmailTextBox(CredentialsData.getFileSystemUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.PASS, "Verified the user has access to the file system");
        emHeaderPage.verifyHeaderContentsAreDisplayed();
        logger.log(LogStatus.PASS, "Verified the EM header is displayed above the 'My Data' page");

    }
    
    
    
    @Test (dependsOnMethods={"loginAndVerifyFSAndHeader"}, enabled = true)
    public void verifyLeftMenuSystemFolderRedirects() throws Exception {
        logger = report.startTest("Verify system folder redirects via left menu");

        // Verify system folders are visible & accessible
        filePage.verifyLeftMenuMyEMDataIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'My EM Data' folder is visible in the left menu");
        filePage.clickMyEMDataOnLeftMenu();
        logger.log(LogStatus.INFO, "Clicked on 'My EM Data' left menu option");
        filePage.verifyUserIsOnMyEMDataPage();
        logger.log(LogStatus.PASS, "Verified user has been redirected to the 'My EM Data' folder");
    
        filePage.verifyLeftMenuMyDataIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'My Data' folder is visible in the left menu");
        filePage.clickMyDataOnLeftMenu();
        logger.log(LogStatus.INFO, "Clicked on 'My Data' left menu option");
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.PASS, "Verified user has been redirected to the 'My Data' folder");
              
        filePage.verifyLeftMenuRecentsIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'Recents' folder is visible in the left menu");
        filePage.clickRecentsOnLeftMenu();
        logger.log(LogStatus.INFO, "Clicked on 'Recents' left menu option");
        filePage.verifyUserIsOnRecentsPage();
        logger.log(LogStatus.PASS, "Verified user has been redirected to the 'Recents' folder");
        
        filePage.verifyLeftMenuTrashIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'Trash' folder is visible in the left menu");
        filePage.clickTrashOnLeftMenu();
        logger.log(LogStatus.INFO, "Clicked on 'Trash' left menu option");
        trashPage.verifyUserIsOnTrashPage();
        logger.log(LogStatus.PASS, "Verified user has been redirected to the 'Trash' folder");
        
        filePage.verifyLeftMenuSharedDataIsDisplayed();
        logger.log(LogStatus.PASS, "Verified 'Shared Data' folder is visible in the left menu");
        filePage.clickSharedDataOnLeftMenu();
        logger.log(LogStatus.INFO, "Clicked on 'Shared Data' left menu option");
        filePage.verifyUserIsOnSharedDataPage();
        logger.log(LogStatus.PASS, "Verified user has been redirected to the 'Shared Data' folder");
        
        
    }
      
    
    @DataProvider(name = "userOrgData")
    public static Object [][] userOrgData(){
    	return new Object [][] {{Constants.folderName, Constants.tableName, Constants.axName},
    							{Constants.folderName, Constants.tableName, Constants.axName}};
    }
    
    @Test(dependsOnMethods = {"verifyLeftMenuSystemFolderRedirects"}, dataProvider = "userOrgData", enabled = true)
    public void createItemsInSharedDataAndMyDataFolders(String folderName, String tableName, String axName) throws Exception{
    	logger = report.startTest("Create data items in Shared & My Data");
    	
    	fileSystemPage.verifyLocationIsEditable();
    	logger.log(LogStatus.PASS, "Folder location is editable");
    	fileSystemPage.createDataItems(folderName, tableName, axName);
    	logger.log(LogStatus.INFO, "Created a table, folder & launched a draft assessment");
    	genericWizards.clickSaveAndCloseAssessment();
    	logger.log(LogStatus.INFO, "Saved a draft assessment");  	
    	filePage.clickMyDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on My Data link");

    }
       
        
    
    @Test(dependsOnMethods = {"createItemsInSharedDataAndMyDataFolders"}, enabled = true)
    public void verifyCurrentUserFolderContentsView() throws Exception{
    	logger = report.startTest("Verify created data is displayed in Shared/My Data & EM data is also displayed");
    	
    	filePage.clickMyDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'My Data' link");
    	fileSystemPage.verifyQATestTableIsDisplayed();  
    	logger.log(LogStatus.PASS, "Verified created table is displayed in 'My Data'");
    	fileSystemPage.verifyQATestFolderIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified created folder is displayed in 'My Data'");
    	fileSystemPage.verifyQATestAxIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified draft assessment is displayed in 'My Data'");
    	
    	filePage.clickSharedDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'Shared Data' link");
    	filePage.verifyUserIsOnSharedDataPage();
    	logger.log(LogStatus.INFO, "Verified the user is redirected the the 'Shared Data' folder");
    	fileSystemPage.verifyQATestTableIsDisplayed();  
    	logger.log(LogStatus.PASS, "Verified created table is displayed in 'Shared Data'");
    	fileSystemPage.verifyQATestFolderIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified created folder is displayed in 'Shared Data'");
    	fileSystemPage.verifyQATestAxIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified draft assessment is displayed in 'Shared Data'");
    	
    	filePage.clickMyEMDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'My EM Data' link");
    	fileSystemPage.verifyEMDataQAFolderIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified the QA folder is displayed in 'My EM Data' system folder");
    	fileSystemPage.verifyMyEMDataQAFolderIsReadOnly();
    	logger.log(LogStatus.PASS, "Verified the 'My EM Data -> QA Folder' is read only"); 
    	
    }
        
     
    
    @Test(dependsOnMethods = {"verifyCurrentUserFolderContentsView"}, enabled = true)
    public void verifyFolderContentsViewForUsersInTheSameOrg() throws Exception{
    	logger = report.startTest("Verify system folder contents view for a user in the same organisation");
    	
    	emHeaderPage.logout();
    	logger.log(LogStatus.INFO, "Log out current user: "+CredentialsData.getFileSystemUser());
    	Constants.clearBrowserCache();
    	
    	logInPage.fillInEmailTextBox(CredentialsData.getEMPermanentUser());
    	logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
    	logger.log(LogStatus.INFO, "Filled in credentials for: "+CredentialsData.getEMPermanentUser()+" (same organisation)");
    	logInPage.clickLogInButton();
    	logger.log(LogStatus.INFO, "Clicked on Login button");
    	
    	filePage.clickMyDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'My Data' link");
    	fileSystemPage.verifyFolderLocationIsEmpty();
    	logger.log(LogStatus.PASS, "Verified My Data folder is user specific & no items from another users 'My Data' are visible");
    	
    	filePage.clickSharedDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'Shared Data' link");
    	filePage.verifyUserIsOnSharedDataPage();
    	logger.log(LogStatus.INFO, "Verified the user is redirected the the 'Shared Data' folder");
    	fileSystemPage.verifyQATestTableIsDisplayed();  
    	logger.log(LogStatus.PASS, "Verified the table created by: "+CredentialsData.getFileSystemUser()+ " in 'Shared Data' is displayed");
    	fileSystemPage.verifyQATestFolderIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified the folder created by: "+CredentialsData.getFileSystemUser()+ " in 'Shared Data' is displayed");
    	fileSystemPage.verifyQATestAxIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified the draft assessment created by: "+CredentialsData.getFileSystemUser()+" in 'Shared Data' is displayed");
    	
    	filePage.clickMyEMDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'My EM Data' link");
    	fileSystemPage.verifyEMDataQAFolderIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified the QA folder is displayed in 'My EM Data' system folder");
    	fileSystemPage.verifyMyEMDataQAFolderIsReadOnly();
    	logger.log(LogStatus.PASS, "Verified the 'My EM Data -> QA Folder' is read only"); 
    	
    	emHeaderPage.logout();
    	logger.log(LogStatus.INFO, "Logout user: "+CredentialsData.getEMPermanentUser());
    	Constants.clearBrowserCache();
    	 	
    }
       
   
    
    @Test(dependsOnMethods = {"verifyFolderContentsViewForUsersInTheSameOrg"}, enabled = true)
    public void verifyFolderContentsViewForUsersInADifferentOrg() throws Exception{
    	logger = report.startTest("Verify system folder contents view for a user in a different organisation");
    	
    	driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");
    	logInPage.fillInEmailTextBox(CredentialsData.getOtherOrgUser());
    	logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
    	logger.log(LogStatus.INFO, "Filled in credentials for: "+CredentialsData.getOtherOrgUser()+" (different organisation)");
    	logInPage.clickLogInButton();
    	logger.log(LogStatus.INFO, "Clicked on Login button");
    	
    	filePage.clickMyDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'My Data' link");
    	fileSystemPage.verifyFolderLocationIsEmpty();
    	logger.log(LogStatus.PASS, "Verified My Data folder is user specific & no items from another users 'My Data' are visible");
    	
    	filePage.clickSharedDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'Shared Data' link");
    	filePage.verifyUserIsOnSharedDataPage();
    	logger.log(LogStatus.INFO, "Verified the user is redirected the the 'Shared Data' folder");  	
    	fileSystemPage.verifyFolderLocationIsEmpty();
    	logger.log(LogStatus.PASS, "Verified My Data folder is user specific & no items from another users 'My Data' are visible");
    	
    	filePage.clickMyEMDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on 'My EM Data' link");
    	fileSystemPage.verifyEMDataQAFolderIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified the QA folder is displayed in 'My EM Data' system folder");
    	fileSystemPage.verifyMyEMDataQAFolderIsReadOnly();
    	logger.log(LogStatus.PASS, "Verified the 'My EM Data -> QA Folder' is read only"); 
    	
    	emHeaderPage.logout();
    	logger.log(LogStatus.INFO, "Logout user: "+CredentialsData.getOtherOrgUser());
    	Constants.clearBrowserCache();
    	 	
    }
    
    
    @Test(dependsOnMethods = {"verifyFolderContentsViewForUsersInADifferentOrg"}, enabled = true)
    public void verifyItemSelectionAndDeselection() throws Exception{
    	logger = report.startTest("Verify when clicked, items are selected &/or deselected in the file system");
    	
    	driver.get(CredentialsData.getBaseURL());
        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");

        logInPage.fillInEmailTextBox(CredentialsData.getFileSystemUser());
        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
        logger.log(LogStatus.INFO, "Entered email & password");

        logInPage.clickLogInButton();
        logger.log(LogStatus.INFO, "Clicked on Login Button");
        filePage.verifyUserIsOnMyDataPage();
        logger.log(LogStatus.PASS, "Verified the user has access to the file system");
        
        fileSystemPage.selectItemAndVerifySelectionStatus(Constants.tableName, true);
        logger.log(LogStatus.PASS, "Selected test table & verified selection via batch button appearance");
        fileSystemPage.deselectItemAndVerifySelectionStatus(Constants.tableName);
        logger.log(LogStatus.PASS, "Deselected test table & verified deselection via batch button disappearance");
        
        fileSystemPage.selectItemAndVerifySelectionStatus(Constants.folderName, true);
        logger.log(LogStatus.PASS, "Selected test folder & verified selection via batch button appearance");
        fileSystemPage.deselectItemAndVerifySelectionStatus(Constants.folderName);
        logger.log(LogStatus.PASS, "Deselected test folder & verified deselection via batch button disappearance");
        
        fileSystemPage.selectItemAndVerifySelectionStatus(Constants.axName, true);
        logger.log(LogStatus.PASS, "Selected draft assessment & verified selection via batch button appearance");
        fileSystemPage.deselectItemAndVerifySelectionStatus(Constants.axName);
        logger.log(LogStatus.PASS, "Deselected draft assessment & verified deselection via batch button disappearance");
    
    }
    
    
    @Test(dependsOnMethods = {"verifyItemSelectionAndDeselection"}, enabled = true)
    public void verifyItemAccessRedirectsInMyData() throws Exception{
    	logger = report.startTest("Verify when accessed, items in 'My Data' are opened and users are correctly redirected");
    	
    	// Table
    	fileSystemPage.openQATestTable();
    	logger.log(LogStatus.INFO, "Double clicked on :"+Constants.tableName);
    	editorPage.verifyUserIsOnEditorPage();
    	logger.log(LogStatus.PASS, "Verified the table is opened in the editor");
    	editorPage.clickGoBackButton();
    	logger.log(LogStatus.INFO, "Clicked on go back button to return to the file system");
    	
    	// Folder
    	fileSystemPage.openQATestFolder();
    	logger.log(LogStatus.INFO, "Double clicked on :"+Constants.folderName);
    	fileSystemPage.verifyFolderLocationIsEmpty();
    	logger.log(LogStatus.PASS, "Verified the folder is opened & the folder is empty message is displayed");
    	filePage.clickMyDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on left menu - 'My Data' option");
    	
    	// Draft Ax (draft model run)
    	fileSystemPage.openQATestAx();
    	logger.log(LogStatus.INFO, "Double clicked on draft assessment :"+Constants.axName);
    	genericWizards.verifyGenericWizardOpened();
    	logger.log(LogStatus.PASS, "Verified the draft Ax is opened in the generic wizard");
    	
    	// Completed Ax (completed model run)
    	genericWizards.clickOnCat1ChooseFilesButton();
    	logger.log(LogStatus.INFO, "Clicked on 'Choose File' for category 1");
    	filePicker.clickmyEMDataFolder();
    	logger.log(LogStatus.INFO, "Selected 'My EM Data' folder");
    	filePicker.clickQAFolder();
    	logger.log(LogStatus.INFO, "Selected 'QA' folder");
    	filePicker.clickCategory1Table();
    	logger.log(LogStatus.INFO, "Selected 'Category 1' table");
    	filePicker.clickSelectButton();
    	logger.log(LogStatus.INFO, "Clicked 'Select' button, file picker closed");
    	
    	genericWizards.clickOnCat2ChooseFilesButton();
    	logger.log(LogStatus.INFO, "Clicked on 'Choose File' for category 2");
    	filePicker.clickCategory2Table();
    	logger.log(LogStatus.INFO, "Selected 'Category 2' table");
    	filePicker.clickSelectButton();
    	logger.log(LogStatus.INFO, "Clicked 'Select' button, file picker closed");
    	
    	genericWizards.clickRunButton();
    	logger.log(LogStatus.INFO, "Clicked 'Run' button, model run has been submitted");
    	fileSystemPage.openCompletedQAAx();
    	logger.log(LogStatus.INFO, "Waited for completion of :"+Constants.axName+" model run, double clicked to open");
    	resultsViewerPage.verifyResultsViewerIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified the user is redirected to the Results Viewer");
    	resultsViewerPage.clickGoBack(); 
    	logger.log(LogStatus.INFO, "Clicked on 'Go Back' arrow");
    	
    	
    }

    
    
    @Test (dependsOnMethods={"verifyItemAccessRedirectsInMyData"}, enabled = true)
    public void verifyItemAccessRedirectsInSharedData() throws Exception {
    	logger = report.startTest("Verify when accessed, items in 'Shared Data' are opened and users are correctly redirected");
        
    	filePage.clickSharedDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked 'Shared Data' on the left menu");
    	filePage.verifyUserIsOnSharedDataPage();
    	logger.log(LogStatus.INFO, "Verified the user is redirected the the 'Shared Data' folder");
    	
    	// Table
    	fileSystemPage.openQATestTable();
    	logger.log(LogStatus.INFO, "Double clicked on :"+Constants.tableName);
    	editorPage.verifyUserIsOnEditorPage();
    	logger.log(LogStatus.PASS, "Verified the table is opened in the editor");
    	editorPage.clickGoBackButton();
    	logger.log(LogStatus.INFO, "Clicked on go back button to return to the file system");
    	
    	// Folder
    	fileSystemPage.openQATestFolder();
    	logger.log(LogStatus.INFO, "Double clicked on :"+Constants.folderName);
    	fileSystemPage.verifyFolderLocationIsEmpty();
    	logger.log(LogStatus.PASS, "Verified the folder is opened & the folder is empty message is displayed");
    	filePage.clickSharedDataOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on left menu - 'My Data' option");
    	
    	// Draft Ax (draft model run)
    	fileSystemPage.openQATestAx();
    	logger.log(LogStatus.INFO, "Double clicked on draft assessment :"+Constants.axName);
    	genericWizards.verifyGenericWizardOpened();
    	logger.log(LogStatus.PASS, "Verified the draft Ax is opened in the generic wizard");
    	genericWizards.clickSaveAndCloseAssessment();
    	logger.log(LogStatus.INFO, "Closed draft assessment");
    	
    }
    
    
    
    @Test (dependsOnMethods={"verifyItemAccessRedirectsInSharedData"}, enabled = true)
    public void verifyItemAccessRedirectsInMyEMData() throws Exception {
    	logger = report.startTest("Verify when accessed, items in 'My EM Data' are opened and users are correctly redirected");
        
    	filePage.clickMyEMDataOnLeftMenu();;
    	
    	// Folder
    	fileSystemPage.openEMDataQAFolder();
    	logger.log(LogStatus.INFO, "Double clicked on My Em Data -> QA Folder");
    	fileSystemPage.verifyEMDataCategory2TableIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified the folder is opened & the folder contains 'Category2' table");
    	    	
    	// Table
    	fileSystemPage.openEMDataCategory2Table();
    	logger.log(LogStatus.INFO, "Double clicked on the 'Category2' table");
    	editorPage.verifyUserIsOnEditorPage();
    	logger.log(LogStatus.PASS, "Verified the table is opened in the editor");
    	editorPage.clickGoBackButton();
    	logger.log(LogStatus.INFO, "Clicked on go back button to return to the file system");
    	
    }
    
    
    @Test(dependsOnMethods = {"verifyItemAccessRedirectsInMyEMData"}, enabled = true)
    public void verifyRecents() throws Exception{
    	logger = report.startTest("Verify files accessed are correctly recorded in 'Recents'");
    	
    	filePage.clickRecentsOnLeftMenu();
    	logger.log(LogStatus.INFO, "Clicked on left menu 'Recents' link");
    	
    	fileSystemPage.verifyQATestTableIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified "+Constants.tableName+" is displayed");
    	fileSystemPage.verifyQATestAxIsDisplayed();
    	logger.log(LogStatus.PASS, "Verified "+Constants.axName+" is displayed");
    	fileSystemPage.verifyQATestFolderIsNotDisplayed();
    	logger.log(LogStatus.PASS, "Verified folders are not displayed in 'Recents'");
    	
    }


   

    @AfterMethod
    public void screenCap(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE) {

            String screenshot_path= Util.Constants.captureScreenshot(result.getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "SystemFolderRedirectAndNavigation", imagePath);
            testCaseStatus = "FAILED";

        }
        report.endTest(logger);
    }




    @AfterClass
    public void tearDown() throws Exception {

    	logger = report.startTest("Start tear down");
        Constants.clearBrowserCache();
        
        djangoPage.deleteUserProfile(CredentialsData.getFileSystemUser());
        logger.log(LogStatus.PASS, "File System user has been deleted");
        djangoPage.deleteUserProfile(CredentialsData.getOtherOrgUser());
        logger.log(LogStatus.PASS, "Other org user has been deleted");
        
        logInPage.verifyLoginAccessDenied(CredentialsData.getFileSystemUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user: "+CredentialsData.getFileSystemUser());     
        logInPage.verifyLoginAccessDenied(CredentialsData.getOtherOrgUser());
        logger.log(LogStatus.PASS, "Verified login denied for deleted user: "+CredentialsData.getOtherOrgUser());

        Constants.clearBrowserCache();
        //driver.close();
        logger.log(LogStatus.PASS, "Tear Down successful");
        report.endTest(logger);
        report.flush();
        System.out.println(getClass().getName()+" - "+testCaseStatus);
    }

}
