package Reg_EMHeader;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Page.classes.DjangoPage;
import Page.classes.EMHeaderPage;
import Page.classes.EditorPage;
import Page.classes.FilePage;
import Page.classes.FileSystemPage;
import Page.classes.GenericWizards;
import Page.classes.LogInPage;
import Page.classes.ModelsPage;
import Page.classes.TrashPage;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.ExtentFactory;
import jxl.read.biff.BiffException;

/**
 * Created by alan.sheehy on 17/11/2016.
 * Test Automation for EMA-1197
 * DEPENDANT ON CREATE USER PROFILE CLASS
 * Tests for requirements 5, 5.1 & 5.2 in EMA-742
 */


public class SearchFunctionality {
	
	String emailAdd;
    String password;
    String baseUrl;
    String djangoEmail;
    String djangoPassword;
    String firstName;
    String lastName;
    String apiUrl;
    ExtentReports report;
    ExtentTest logger;
	
    LogInPage logInPage;
	DjangoPage djangoPage;
	EMHeaderPage emHeaderPage;
	ModelsPage modelsPage;
	FilePage filePage;
	TrashPage trashPage;
	FileSystemPage fileSystemPage;
	GenericWizards genericWizards;
	EditorPage editorPage;
	String searchableString = "searchable";
	String noResultsSearchString = "bfaljksgergskfssk";
	int expectedNumberOfSearchResults = 3;
	int expectedMaxNumberofSearchListResults = 14;
	int expectedShowAllTotalResultsCount = 18;
	String testCaseStatus = "PASSED";
	 
	private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
	 
	
	 @BeforeClass
	 public void beforeClassSetup() throws IOException, BiffException, ParseException {
	        //report = new ExtentReports("C:\\Workspace\\Automation\\Automation Results\\expertModels\\Reg_Accounts\\TeamPage.html");
	        report = ExtentFactory.getInstance();
	                
	 }
	 
	 
	 
	 @BeforeMethod
	 public void setup() throws Exception {
	        // Maximize the broswer's window
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        logInPage = new LogInPage(driver);
	        djangoPage = new DjangoPage(driver);
	        emHeaderPage = new EMHeaderPage(driver);
	        modelsPage = new ModelsPage(driver);
	        filePage = new FilePage(driver);
	        trashPage = new TrashPage(driver);
	        fileSystemPage = new FileSystemPage(driver);
	        genericWizards = new GenericWizards(driver);
	        editorPage = new EditorPage(driver);
	        		
	 }
	 
	 
	 
	 @Test
	 public void CreateActiveUserProfileInAPI() throws Exception {
		 	logger = report.startTest("Create EM Header test user");
		 	djangoPage.createUserProfile(CredentialsData.getEMHeaderUser());
		 	logger.log(LogStatus.PASS, "Test user created");		 
	 }
	 
	 
	 
	 @Test (dependsOnMethods = {"CreateActiveUserProfileInAPI"}, enabled = true)
	 public void loginAndNavigateToMyData() throws Exception{		 	
		 	logger = report.startTest("Login and navigate to 'My Data' folder");
		 	
	        driver.get(CredentialsData.getBaseURL());
	        logger.log(LogStatus.INFO, "Navigated to Expert Models URL");
	        
	        logInPage.fillInEmailTextBox(CredentialsData.getEMHeaderUser());
	        logInPage.fillInPasswordTextBox(CredentialsData.getUserPassword());
	        logger.log(LogStatus.INFO, "Entered EM Header email & password");
	        
	        logInPage.clickLogInButton();
	        logger.log(LogStatus.PASS, "Clicked on Login Button");
	        
	        filePage.clickMyDataOnLeftMenu();
	        logger.log(LogStatus.INFO, "Clicked on side menu 'My Data' link");
	        filePage.verifyUserIsOnMyDataPage();
	        logger.log(LogStatus.INFO, "Verified user is in 'My Data' Folder");
	 }
	 
	 
	 
	 @DataProvider(name = "itemNames")
	 public static Object[][] itemsNames() {
		 	return new Object[][]{{"QA Searchable Folder Item", "QA Searchable Table Item", "QA Searchable Draft Ax Item"}, 
		 							{"Auto Folder Item", "Auto Table Item", "Auto Draft Ax Item"},
		 							{"Test Folder Item", "Test Table Item", "Test Draft Ax Item"}};
	 }
	 
	 @Test (dependsOnMethods = {"loginAndNavigateToMyData"}, dataProvider = "itemNames", enabled = true)
	 public void createSearchableData(String folderName, String tableName, String axName) throws Exception{
		 	logger = report.startTest("Create searchable data in 'My Data' folder");
		 	fileSystemPage.createDataItems(folderName, tableName, axName);
		 	genericWizards.clickSaveAndCloseAssessment();
		 	logger.log(LogStatus.PASS, "Created a searchable folder, table and draft assessment in 'My Data'");
	 }
	 
	 
	 
	 @DataProvider(name = "searchInputs")
	 public static Object[][] searchInputs() {
		 	return new Object[][]{{""}, {"s"}, {"se"}};
	 }
	 
	 @Test (dependsOnMethods = {"createSearchableData"}, dataProvider = "searchInputs", enabled = true)
	 public void verifySearchActionsAndNegativeTestMinSearchRequirements(String inputChars) throws Exception{
		 	logger = report.startTest("Verify EM Header basic operations & no search ititiated for: "+inputChars.length()+" character(s)");	 	
		 	emHeaderPage.clickSearchButton();
		 	logger.log(LogStatus.INFO, "Clicked on EM Header search option");
		 	emHeaderPage.clickOnSearchTextBox();
		 	logger.log(LogStatus.PASS, "Search text box found and clicked");
		 	emHeaderPage.enterSearchText(inputChars);
		 	logger.log(LogStatus.INFO, "Search text: "+inputChars+" entered");
		 	emHeaderPage.verifySearchListNotDisplayed();
		 	logger.log(LogStatus.PASS, "No results displayed for string: "+inputChars+" entered");
		 	
		 	emHeaderPage.clearSearchTextBox();
		 	emHeaderPage.closeSearch();
		 	logger.log(LogStatus.INFO, "Cleared search text & Clicked on close search button");
		 	emHeaderPage.verifySearchClosed();
		 	logger.log(LogStatus.PASS, "Verified search has been closed successfully");
	 }
	 
	 
	 
	 @DataProvider(name = "searchData")
	 public static Object[][] searchData() {
		 	return new Object[][]{{"sea"}, {"rchabl"}, {"QA Searchable"}};
	 }
	 
	 @Test (dependsOnMethods = {"verifySearchActionsAndNegativeTestMinSearchRequirements"}, dataProvider = "searchData", enabled = true)
	 public void verifyPartialAndFullSearchResults(String searchString) throws Exception{
		 	logger = report.startTest("Verify Search results returned for minimum required input search strings");
		 
		 	emHeaderPage.clickSearchButton();
		 	logger.log(LogStatus.INFO, "Clicked on EM Header search option");
		 	emHeaderPage.clickOnSearchTextBox();
		 	logger.log(LogStatus.PASS, "Search textbox found and clicked");
		 	emHeaderPage.enterSearchText(searchString);
		 	logger.log(LogStatus.INFO, "Entered search string: "+searchString);
		 	emHeaderPage.verifySearchResultsListAndParentFolder();
		 	logger.log(LogStatus.PASS, "Verified search list results");
		 	emHeaderPage.verifySearchListSize(expectedNumberOfSearchResults);
		 	logger.log(LogStatus.PASS, "Verified expected number of search results = "+expectedNumberOfSearchResults);
		 	
		 	emHeaderPage.clearSearchTextBox();
		 	emHeaderPage.closeSearch();
		 	logger.log(LogStatus.INFO, "Cleared search text & Clicked on close search button");
		 	emHeaderPage.verifySearchClosed();
		 	logger.log(LogStatus.INFO, "Verified search area has closed successfully"); 
	 }
	 
	 
	 
	 @Test (dependsOnMethods = {"verifyPartialAndFullSearchResults"}, dataProvider = "itemNames", enabled = true)
	 public void createSearchableDataInSharedData(String folderName, String tableName, String axName) throws Exception{
		 	logger = report.startTest("Create searchable data in 'Shared Data' folder");
		 	filePage.clickSharedDataOnLeftMenu();
		 	filePage.verifyUserIsOnSharedDataPage();
		 	logger.log(LogStatus.INFO, "Verified user is in 'Shared Data' folder");
		 	fileSystemPage.createDataItems(folderName, tableName, axName);
		 	genericWizards.clickSaveAndCloseAssessment();
		 	logger.log(LogStatus.PASS, "Created a searchable folder, table and draft assessment in 'Shared Data'");
		 	filePage.verifyUserIsOnSharedDataPage();
		 	logger.log(LogStatus.INFO, "User returned to the 'Shared Data' folder");
	 }
	 
	 
	 
	 @DataProvider(name = "searchAll")
	 public static Object[][] searchAll(){
		 return new Object[][]{{"item"}};
	 }
	
	 @Test (dependsOnMethods = {"createSearchableDataInSharedData"}, dataProvider = "searchAll", enabled = true)
	 public void verifySearchListSizeActionsAndLinks(String searchItems) throws Exception{
		 	logger = report.startTest("Verify search list actions and show all results page");
		 
		 	filePage.clickMyDataOnLeftMenu();
		 	filePage.verifyUserIsOnMyDataPage();
		 	logger.log(LogStatus.INFO, "Navigated back to My Data folder");
		 	emHeaderPage.clickSearchButton();
		 	logger.log(LogStatus.INFO, "Clicked on EM Header search option");
		 	emHeaderPage.clickOnSearchTextBox();
		 	logger.log(LogStatus.PASS, "Search textbox found and clicked");
		 	emHeaderPage.enterSearchText(searchItems);
		 	logger.log(LogStatus.INFO, "Entered search string: "+searchItems);
		 	
		 	emHeaderPage.verifySearchListSize(expectedMaxNumberofSearchListResults);
		 	logger.log(LogStatus.PASS, "Verified maximum list returned is: "+expectedMaxNumberofSearchListResults+" items");
		 	
		 	emHeaderPage.verifyShowAllResultsIsDisplayed();
		 	logger.log(LogStatus.PASS, "Show all X results option is displayed");
		 	emHeaderPage.verifySearchListCount(Integer.toString(expectedShowAllTotalResultsCount));
		 	logger.log(LogStatus.PASS, "Expected number of 'All' search results verified as: "+expectedShowAllTotalResultsCount);
		 	
		 	emHeaderPage.clickShowAllResults();
		 	logger.log(LogStatus.INFO, "Show all results option clicked");
		 	emHeaderPage.verifyShowAllResultsListSize(expectedShowAllTotalResultsCount);
		 	logger.log(LogStatus.PASS, "Verified total number of results in 'Show All' FS page is: "+expectedShowAllTotalResultsCount);	 	
	 }
	 
	 
	 
	 @Test (dependsOnMethods = "verifySearchListSizeActionsAndLinks", enabled = true)
	 public void verifySearchListOpenItemFunctionality() throws Exception{
		 	logger = report.startTest("Verify search list open item functionality & no results message");
		 	
		 	// redirect for a folder
		 	emHeaderPage.clickSearchButton();
		 	logger.log(LogStatus.INFO, "Clicked on EM Header search option");
		 	emHeaderPage.clickOnSearchTextBox();
		 	logger.log(LogStatus.PASS, "Search textbox found and clicked");
		 	emHeaderPage.enterSearchText(searchableString);
		 	logger.log(LogStatus.INFO, "Entered search string: "+searchableString);
		 	emHeaderPage.clickOnSearchResultsFolder();
		 	logger.log(LogStatus.INFO, "Clicked on search list folder");
		 	filePage.verifyUserIsInSearchableFolder();
		 	logger.log(LogStatus.PASS, "Verified user has been redirected to the folder selected in the search list");
		 	
		 	// redirect for a table
		 	emHeaderPage.clickSearchButton();
		 	logger.log(LogStatus.INFO, "Clicked on EM Header search option");
		 	emHeaderPage.clickOnSearchTextBox();
		 	logger.log(LogStatus.PASS, "Search textbox found and clicked");
		 	emHeaderPage.enterSearchText(searchableString);
		 	logger.log(LogStatus.INFO, "Entered search string: "+searchableString);
		 	emHeaderPage.clickOnSearchResultsTable();
		 	logger.log(LogStatus.INFO, "Clicked on search list table");
		 	editorPage.verifyUserIsOnEditorPage();		 	
		 	editorPage.clickGoBackButton();

		 	// redirect for draft Ax
		 	emHeaderPage.clickSearchButton();
		 	logger.log(LogStatus.INFO, "Clicked on EM Header search option");
		 	emHeaderPage.clickOnSearchTextBox();
		 	logger.log(LogStatus.PASS, "Search textbox found and clicked");
		 	emHeaderPage.enterSearchText(searchableString);
		 	logger.log(LogStatus.INFO, "Entered search string: "+searchableString);
		 	emHeaderPage.clickOnSearchResultsDraftAx();
		 	logger.log(LogStatus.INFO, "Clicked on search list draft ax");
		 	genericWizards.verifyGenericWizardOpened();
		 	genericWizards.clickSaveAndCloseAssessment();
		 	emHeaderPage.verifyHeaderContentsAreDisplayed();
		 	
	 }
	 
	 
	 @Test (dependsOnMethods = "verifySearchListOpenItemFunctionality", enabled = true)
	 public void verifyNoSearchResultsMessage() throws Exception{
		 	logger = report.startTest("Verify 'Nothing Found' search message");
		 	
		 	emHeaderPage.clickSearchButton();
		 	logger.log(LogStatus.INFO, "Clicked on EM Header search option");
		 	emHeaderPage.clickOnSearchTextBox();
		 	logger.log(LogStatus.PASS, "Search textbox found and clicked");
		 	emHeaderPage.enterSearchText(noResultsSearchString);
		 	logger.log(LogStatus.INFO, "Entered search string: "+noResultsSearchString);
		 	emHeaderPage.verifyNoResultsFoundMessage(noResultsSearchString);
		 	logger.log(LogStatus.PASS, "Verified 'Nothing found with the search key '"+noResultsSearchString+"', is displayed");
	 }
	 			
	 
	 	 
	 @AfterMethod
	 public void screenCap(ITestResult result) {
		 	if(result.getStatus()== ITestResult.FAILURE) {

		 		String screenshot_path= Constants.captureScreenshot(result.getName());
		 		String imagePath = logger.addScreenCapture(screenshot_path);
		 		logger.log(LogStatus.FAIL, "SearchFunctionality", imagePath);
		 		testCaseStatus = "FAILED";
		 	}
		 	report.endTest(logger);
	 }
	 
	 
	 
	 @AfterClass
	 public void tearDown() throws Exception {
		 	logger = report.startTest("Start tear down");
		 	emHeaderPage.logout();
		 	logInPage.verifyLogInPage();		 
	        
	        djangoPage.deleteUserProfile(CredentialsData.getEMHeaderUser());
	        logger.log(LogStatus.PASS, "EMHeader User has been deleted");	      
	        logInPage.verifyLoginAccessDenied(CredentialsData.getEMHeaderUser());
	        logger.log(LogStatus.PASS, "Verified login denied for deleted user credentials");

	        Constants.clearBrowserCache();
	        //driver.quit();
	        logger.log(LogStatus.PASS, "Tear down Successful");
	        report.endTest(logger);
	        report.flush();
	        System.out.println(getClass().getName()+" - "+testCaseStatus);
	 }
	 
	 

}
