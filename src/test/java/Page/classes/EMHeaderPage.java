package Page.classes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Util.AssertType;
import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;

public class EMHeaderPage {
	final static int WAIT_TIME_OUT=50;
    
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    // Search Options
    @FindBy(id = "em-header-searchbox")
    WebElement SearchButton;
    
    @FindBy(id = "search-box-input")
    WebElement searchTextBox;
    
    @FindBy(id = "search-box-close")
    WebElement closeSearchBox;
    
    @FindBy(id = "search-results-qa-searchable-table-item-my-data")
    WebElement searchResultsTable;
    
    @FindBy(id = "search-results-qa-searchable-draft-ax-item-my-data")
    WebElement searchResultsDraftAx;
    
    @FindBy(id = "search-results-qa-searchable-folder-item-my-data")
    WebElement searchResultsFolder;
    
    @FindBy(xpath = "//div[@id='search-box']//translate[text()='File']")
    WebElement searchListFileHeader;
    
    @FindBy(xpath = "//div[@id='search-box']//translate[text()='Folder']")
    WebElement searchListFolderHeader;
    
    @FindBy(id = "search-all-results-count")
    WebElement searchListResultsCount;
    
    @FindBy(id = "search-all-results")
    WebElement showAllResultsButton;
    
    @FindBy(xpath = "//div[@id='fs-header-wrapper']//h2/translate[text()='Search results for']")
    WebElement showAllResultsPageTitle;
    
    @FindBy(xpath = "//div[@id='search-box']//translate[text()='Nothing found with the search key']")
    WebElement noResultsMessage;    
    
    // Header Links & Logo
    @FindBy(id = "em-header-files")
    WebElement filesLink;
    
    @FindBy(id = "em-header-models")
    WebElement modelsLink;
    
    @FindBy(id = "em-header-activity")
    WebElement activityLink;
    
    @FindBy(id = "em-header-logo")
    WebElement appLogo;
    
    // Notifications Section 
    @FindBy(id = "notifications-button")
    WebElement notificationsIconButton;
    
    @FindBy(id = "notification-counter")
    WebElement notificationCounter;
    
    // List of Notifications (need to be generated for the tests)
    @FindBy(id = "notifications-close-successful-assessment")
    WebElement deleteSuccessulAxNotificationButton;
    
    @FindBy(id = "notifications-close-failed-assessment")
    WebElement deleteFailedAxNotificationButton;
    
    @FindBy(id = "notifications-close-successful-import")
    WebElement deleteSuccessfulImportNotificationButton;
    
    @FindBy(id = "notifications-close-failed-import")
    WebElement deleteFailedImportNotificationButton;
    
    @FindBy(id = "notifications-open-assessment-successful-assessment")
    WebElement openAssessmentSuccessfulAxLink;
    
    @FindBy(id = "notifications-open-folder-successful-assessment")
    WebElement openFolderSuccessfulAxLink;
    
    @FindBy(id = "notifications-open-file-successful-import")
    WebElement openFileSuccessfulImportLink;
    
    @FindBy(id = "notifications-open-folder-successful-import")
    WebElement openFolderSuccessfulImportLink;
    
    @FindBy(id = "notifications-view-report-failed-assessment")
    WebElement viewReportFailedAxLink;
    
    //@FindBy(id = "notifications-assessment-complete-successful-assessment")
    //WebElement successfulAxStatus;
    
    //@FindBy(id = "notifications-assessment-failed-failed-assessment")
    //WebElement failedAxStatus;
    
    //@FindBy(id = "notifications-import-complete-successful-import")
    //WebElement successfulImportStatus;
    
    //@FindBy(id = "notifications-import-failed-failed-assessment")
    //WebElement failedImportStatus;
    
    
    // Job Queue Section
    @FindBy(xpath = "//md-toolbar//active-jobs/job-icon/button")
    WebElement jobQueueIcon;
      
    // Common to all job queue items
    @FindBy(id = "job-notification-close-assessment-job")
    WebElement cancelAxJob;
    
    @FindBy(id = "job-notification-title-assessment-job")
    WebElement axJobTitle;
    
    //@FindBy(id = "job-notification-status-assessment-job")
    //WebElement axJobStatus;
    
    @FindBy(id = "job-notification-close-import-job")
    WebElement cancelImportJob;
    
    @FindBy(id = "job-notification-title-import-job")
    WebElement importJobTitle;
    
    //@FindBy(id = "job-notification-status-import-job")
    //WebElement importJobStatus;
    
    
    // User Profile Section
    @FindBy(id = "profile-button")
    WebElement userProfileIconButton;
    
    @FindBy(xpath = "//user-icon//button[@id='profile-button']/span[text()=' AT ']")
    WebElement userProfileInitials;
    
    @FindBy(id = "profile-my-profile")
    WebElement myProfileButton;
    
    @FindBy(id = "profile-account")
    WebElement accountButton;
    
    @FindBy(id = "profile-help")
    WebElement helpButton;
    
    @FindBy(id = "profile-logout")
    WebElement logoutButton;
    
    
    
    
    // Initialize Page Elements
    public EMHeaderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Methods - General Actions
    public void verifyHeaderContentsAreDisplayed(){
    	WaitTypes.waitForElementVisibilityByWebElement(filesLink);
    	Assert.assertEquals(filesLink.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(modelsLink);
    	Assert.assertEquals(modelsLink.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(activityLink);
    	Assert.assertEquals(activityLink.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(SearchButton);
    	Assert.assertEquals(SearchButton.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(notificationsIconButton);
    	Assert.assertEquals(notificationsIconButton.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(userProfileIconButton);
    	Assert.assertEquals(userProfileIconButton.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(userProfileInitials);
    	Assert.assertEquals(userProfileInitials.isDisplayed(), true);
    	//WaitTypes.waitForElementVisibilityByWebElement(appLogo);
    	//Assert.assertEquals(appLogo.isDisplayed(), true);
    }
    
    public void logout() throws Exception{
    	Constants.refreshPage();
    	WaitTypes.waitForElementVisibilityByWebElement(userProfileIconButton);
    	WaitTypes.clickWhenReadyByElement(userProfileIconButton);
        clickOnLogoutButton();  	
    }
    
    // Methods - Header Links & Logo
    public void clickOnFilesLink() throws Exception {
        WaitTypes.clickWhenReadyByElement(filesLink);
    }
    
    public void clickOnModelsLink() throws Exception {
        WaitTypes.clickWhenReadyByElement(modelsLink);
    }
    
    public void clickOnActivityLink() throws Exception {
    	WaitTypes.clickWhenReadyByElement(activityLink);
    }
    
    public void clickOnAppLogo() throws Exception {
    	WaitTypes.clickWhenReadyByElement(appLogo);
    }
    
    // Methods - Search
    public void clickSearchButton() throws Exception {
        WaitTypes.clickWhenReadyByElement(SearchButton);
    }
    
    public void clickOnSearchTextBox() throws Exception {
    	WaitTypes.clickWhenReadyByElement(searchTextBox);
    }
    
    public void enterSearchText(String textEntry){
    	searchTextBox.clear();
    	searchTextBox.sendKeys(textEntry);
    }
    
    public void verifySearchResultsListAndParentFolder(){
    	WaitTypes.waitForElementVisibilityByWebElement(searchListFileHeader);
    	Assert.assertEquals(searchListFileHeader.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(searchListFolderHeader);
    	Assert.assertEquals(searchListFolderHeader.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(searchResultsFolder);
    	Assert.assertEquals(searchResultsFolder.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(searchResultsTable);
    	Assert.assertEquals(searchResultsTable.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(searchResultsDraftAx);
    	Assert.assertEquals(searchResultsDraftAx.isDisplayed(), true);
    }
    
    public void verifySearchListNotDisplayed() throws Exception{
    	AssertType.assertEqualsNotDisplayed(By.xpath("//div[@id='search-box']//translate[text()='File']"));
    	AssertType.assertEqualsNotDisplayed(By.xpath("//div[@id='search-box']//translate[text()='Folder']"));
    }
    
    public int getSearchListSize(String location) {
        List<WebElement> searchList = driver.findElements(By.xpath(location));
        int searchListSize = searchList.size();
        return searchListSize;
    }
    
    public void verifySearchListSize(int expectedListSize){
    	WaitTypes.waitForElementVisibilityByWebElement(searchListFileHeader);
    	Assert.assertEquals(searchListFileHeader.isDisplayed(), true);
    	Assert.assertEquals(getSearchListSize("//div[contains(@class,'search-item')]"), expectedListSize);
    }
    
    public void clickShowAllResults() throws Exception{
    	WaitTypes.clickWhenReadyByElement(showAllResultsButton);
    }
    
    public void verifyShowAllResultsIsDisplayed() throws Exception{
    	WaitTypes.waitForElementVisibilityByWebElement(showAllResultsButton);
    	Assert.assertEquals(showAllResultsButton.isDisplayed(), true);
    }
    
    public void verifyResultsCountIsDisplayed() throws Exception{
    	WaitTypes.waitForElementVisibilityByWebElement(searchListResultsCount);
    	Assert.assertEquals(searchListResultsCount.isDisplayed(), true);
    }
    
    public void closeSearch() throws InterruptedException{
    	WaitTypes.clickWhenReadyByElement(closeSearchBox);	 
    }
    
    public void verifySearchClosed(){
    	verifyHeaderContentsAreDisplayed();
    }
    
    public void clearSearchTextBox(){
    	searchTextBox.clear();
    }
        
    public void verifySearchListCount(String expectedListCount){
    	WaitTypes.waitForElementVisibilityByWebElement(searchListResultsCount);
    	Assert.assertEquals(searchListResultsCount.isDisplayed(), true);
    	String count = searchListResultsCount.getText();
    	Assert.assertEquals(count, expectedListCount);
    }
    
    public void verifyShowAllResultsListSize(int expectedListSize){
    	WaitTypes.waitForElementVisibilityByWebElement(showAllResultsPageTitle);
    	Assert.assertEquals(showAllResultsPageTitle.isDisplayed(), true);
      	Assert.assertEquals(getSearchListSize("//table[@id='fs-table']//tr/td[2]//p"), expectedListSize);
    }
    
    public void clickOnSearchResultsFolder() throws Exception{
    	WaitTypes.clickWhenReadyByElement(searchResultsFolder); 	
    }
    
    public void clickOnSearchResultsTable() throws Exception{
    	WaitTypes.clickWhenReadyByElement(searchResultsTable); 	
    }
    
    public void clickOnSearchResultsDraftAx() throws Exception{
    	WaitTypes.clickWhenReadyByElement(searchResultsDraftAx); 	
    }
    
    public void verifyNoResultsFoundMessage(String expectedString){
    	WaitTypes.waitForElementVisibilityByWebElement(noResultsMessage);
    	Assert.assertEquals(noResultsMessage.isDisplayed(), true);
    	//Assert.assertEquals(noResultsMessage.getText(), expectedString);
    	// Replace with id's for string attribute
    }
    
    // Methods - Notifications
    
    
    // Methods - Job Queue
       
    
    // Methods - User Profile
    public void clickOnUserProfileButton() throws Exception {
    	WaitTypes.clickWhenReadyByElement(userProfileIconButton);
    }
    
    public void clickOnMyProfileButton() throws Exception {
    	WaitTypes.clickWhenReadyByElement(myProfileButton);
    }
    
    public void clickOnAccountButton() throws Exception {
    	WaitTypes.clickWhenReadyByElement(accountButton);
    }
    
    //
    
    public void clickOnHelpButton() throws Exception {
    	WaitTypes.clickWhenReadyByElement(helpButton);     
    }
    
    public void clickOnLogoutButton() throws Exception {
    	WaitTypes.clickWhenReadyByElement(logoutButton);
    }
    
    public void verifyUserProfileDropDownMenu(){
    	WaitTypes.waitForElementVisibilityByWebElement(myProfileButton);
    	Assert.assertEquals(myProfileButton.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(accountButton);
    	Assert.assertEquals(accountButton.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(helpButton);
    	Assert.assertEquals(helpButton.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(logoutButton);
    	Assert.assertEquals(logoutButton.isDisplayed(), true);
    }
    
}
