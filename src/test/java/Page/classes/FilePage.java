package Page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Util.AssertType;
import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;

/**
 * Created by will.okanumeh on 7/12/2016.
 */
public class FilePage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @FindBy(xpath = "//md-sidenav[@id='fs-aside']//a[text()='Shared Data']")
    WebElement leftMenuSharedDataLink;

    @FindBy(xpath = "//md-sidenav[@id='fs-aside']//translate[text()='Recents']")
    WebElement leftMenuRecentsLink;

    @FindBy(xpath = "//md-sidenav[@id='fs-aside']//a[text()='My Data']") 
    WebElement leftMenuMyDataLink;

    @FindBy(xpath = "//md-sidenav[@id='fs-aside']//a[text()='My EM Data™']")
    WebElement leftMenuMyEMDataLink;

    @FindBy(xpath = "//md-sidenav[@id='fs-aside']//translate[text()='Trash']")
    WebElement leftMenuTrashLink;
    
    // Delete along with it's method - covered in EM Header
    @FindBy(xpath = "//*[text()='FILES']")
    WebElement mainMenuBarFilesLink;

    @FindBy(id = "fs-file-creator-launcher")
    WebElement newPlusLink;

    @FindBy(id = "fs-import-new")
    WebElement uploadTableLink;

    // duplicate of import page element - delete
    @FindBy(xpath = "//button[@class='md-primary md-raised cf-btn-l md-button md-ink-ripple']")
    WebElement browserFilesButton;

    @FindBy(xpath = "//h2[text()=' My Data ']")
    WebElement myDataPageTitle;

    @FindBy(xpath = "//a[text()=' My Data ']")
    WebElement myDataCookie;

    @FindBy(xpath = "//h2[text()=' My EM Data™ ']")
    WebElement myMyEmDataPageTitle;

    @FindBy(xpath = "//h2[text()=' Shared Data ']")
    WebElement sharedDataPageTitle;
    
    @FindBy(xpath = "//h2[text()=' QA Searchable Folder Item ']")
    WebElement searchFolderTitle;
    
    @FindBy(xpath = "//div[@id='fs-header-wrapper']//h2/translate[text()='Recent Files']")
    WebElement recentsTextLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Subjects']")
    WebElement subjectTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Food']")
    WebElement foodTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Diary']")
    WebElement diaryTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Nutrients']")
    WebElement nutrientsTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='RandomForestTrain']")
    WebElement randomForestTrainingTableTypeLabel;
    
    @FindBy(xpath = "//md-radio-button[@aria-label='Random Forest Test']")
    WebElement randomForestTestTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Random Forest']")
    WebElement genericRandomForestTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Sugar Consumption']")
    WebElement sugarConsumptionTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Mortality Rate']")
    WebElement mortalityRateTableTypeLabel;

    @FindBy(xpath = "//md-radio-button[@aria-label='Bacchus Data']")
    WebElement bacchusDataTableTypeLabel;

    @FindBy(id = "em-import-quote-double")
    WebElement quoteRadioButton;

    @FindBy(id = "em-import-quote-single")
    WebElement singleQuoteRadioButton;

    @FindBy(id = "em-import-delimiter-comma")
    WebElement commaRadioButton;

    @FindBy(id = "em-import-delimiter-semicolon")
    WebElement semicolonRadioButton;

    @FindBy(id = "em-import-delimiter-tab")
    WebElement tabRadioButton;

    // Duplicate of importPage
    @FindBy(xpath = "//input[@id='em-import-table-name']")
    WebElement tableNameTextBox;

    @FindBy(xpath = "//button//translate[text()='Go back']")
    WebElement goBackButton;

    @FindBy(xpath = "//md-toast//span[text()='Import started']")
    //@FindBy (xpath = "html/body/md-toast/div/span")
    WebElement importStartedToastMsg;

    @FindBy (xpath = "//button[@aria-label='Delete']")
    WebElement importDeleteButton;

    @FindBy (xpath = "//*[@class='md-icon-button md-button md-em-bg-theme md-ink-ripple active']")
    WebElement importCompleteLink;

    @FindBy (xpath = "//*[@class='md-icon-button md-button md-em-bg-theme md-ink-ripple']")
    WebElement cancelImportFile;

    @FindBy (xpath = "//*[text()='Complete Subject Table']")
    WebElement subjectTableMyData;

    @FindBy (xpath = "//div[@class='md-container md-ink-ripple']")
    WebElement tableCheckbox;

    @FindBy (xpath = "//button[@aria-label='Trash']")
    WebElement trashButton;

    @FindBy (xpath = "//*[text()='Permanently Delete']")
    WebElement permanentlyDeleteButton;

    @FindBy (xpath = ".//*[@id='fs-rc-menu-restore']")
    WebElement rightClickRestoreLink;

    @FindBy (xpath = ".//*[@id='fs-rc-menu-restore-to']")
    WebElement rightClickRestoreToLink;

    @FindBy (xpath = "//span[text()='Your file has been trashed.']")
    WebElement yourFileHasBeenTrashMessage;

    @FindBy (xpath = "//span[text()='Your file has been deleted.']")
    WebElement yourFileHasBeenDeletedMessage;

    @FindBy (xpath = "//span[text()='Your file has been moved successfully']")
    WebElement yourFileHasBeenMovedMessage;

    @FindBy (xpath = "//span[text()='Started to move 1 file']")
    WebElement startedToMoveOneFileMessage;

    @FindBy (xpath = "//*[text()='delete']")
    WebElement mainMenuTrashIcon;

    @FindBy (xpath = "//*[text()='Import Failed']")
    WebElement ImportFileFailedNotification;

    @FindBy (xpath = ".//*[@id='fs-rc-menu-trash']")
    WebElement rightClickDropDownTrash;

    @FindBy (xpath = ".//*[@id='fs-rc-menu-delete']")
    WebElement rightClickDropDownPermanentlyDelete;

    @FindBy(xpath = "//*[text()='Incomplete Subject Table']")
    WebElement IncompleteSubjectTableInMyDataPage;

    @FindBy(xpath = "//*[text()='Incomplete Subject Table']")
    WebElement IncompleteFoodTableInMyDataPage;

    @FindBy(xpath = "//*[text()=' Trash ']")
    WebElement cookieTrashLink;




    //Initialize page
    public FilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    // Click on side menu items
    public void clickMyDataOnLeftMenu() throws Exception{
    	WaitTypes.clickWhenReadyByElement(leftMenuMyDataLink);
    }
    
    public void clickSharedDataOnLeftMenu() throws Exception{
    	WaitTypes.clickWhenReadyByElement(leftMenuSharedDataLink);
    }
    
    public void clickMyEMDataOnLeftMenu() throws Exception{
    	WaitTypes.clickWhenReadyByElement(leftMenuMyEMDataLink);
    }
      
    public void clickRecentsOnLeftMenu() throws Exception{
    	WaitTypes.clickWhenReadyByElement(leftMenuRecentsLink);
    }
    
    public void clickTrashOnLeftMenu() throws Exception{
    	WaitTypes.clickWhenReadyByElement(leftMenuTrashLink);
    }
    



    // Delete this - Covered in EM Header
    public void clickFileLinkOnMainMenuBar() throws Exception {
    	Constants.refreshPage();
    	Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(mainMenuBarFilesLink);
    }

    public void clickGoBackAndVerifyPage () throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(goBackButton);
        WaitTypes.waitForElementVisibilityByWebElement(myDataPageTitle);
    }


    public void clickNewAndImportTable() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(uploadTableLink);
    }





    public void verifyUserIsOnMyDataPage() {
        WaitTypes.waitForElementVisibilityByWebElement(myDataPageTitle);
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
    }

    public void verifyUserIsOnSharedDataPage() {
        WaitTypes.waitForElementVisibilityByWebElement(sharedDataPageTitle);
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
    }

    public void verifyUserIsOnMyEMDataPage() {
        WaitTypes.waitForElementVisibilityByWebElement(myMyEmDataPageTitle);
    }
    
    public void verifyUserIsOnRecentsPage() {
        WaitTypes.waitForElementVisibilityByWebElement(recentsTextLabel);
        recentsTextLabel.isDisplayed();
    }
    
    public void verifyUserIsInSearchableFolder(){
    	WaitTypes.waitForElementVisibilityByWebElement(searchFolderTitle);
    	searchFolderTitle.isDisplayed();
    }

    public void verifyMyDataCookieTrailIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(myDataCookie);
    }

    public void verifyTrashCookieTrailIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(cookieTrashLink);
    }
    
    // Duplicate of importPage - delete
    public void verifyTableNameTextBoxIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(tableNameTextBox);
    }
    // Duplicate of import functionality  - delete
    public void verifyTableNameIsDisplayAndBlank() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(tableNameTextBox);
        AssertType.assertTrueIsDisplayedByText(tableNameTextBox, "");
    }
    // Is this used
    public void verifyGoBackButtonIsDisplayedAndEnabled() {
        WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
        goBackButton.isEnabled();
    }

    public void verifyMyDataSubjectTableIsDisplayed () throws InterruptedException {
        Constants.refreshPage();
        WaitTypes.waitForCSVFileAvailability(subjectTableMyData);
    }

    public void verifyFileBeenTrashMessageIsDisplayed () throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenTrashMessage);
        Thread.sleep(5000);
    }

    public void verifyFileBeenDeleteMessageIsDisplayed () throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenDeletedMessage);
        Thread.sleep(3000);
    }

    public void verifyFileBeenMovedMessageIsDisplayed () throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenMovedMessage);
        Thread.sleep(3000);
    }

    public void verifyStartedToMoveMessageIsDisplayed () throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(startedToMoveOneFileMessage);
    }

    public void verifyImportBrowseFileButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(browserFilesButton);
        Assert.assertEquals(browserFilesButton.isDisplayed(), true);
    }

    public void verifyLeftMenuMyDataIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(leftMenuMyDataLink);
        Assert.assertEquals(leftMenuMyDataLink.isDisplayed(), true);
    }

    public void verifyLeftMenuSharedDataIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(leftMenuSharedDataLink);
        Assert.assertEquals(leftMenuSharedDataLink.isDisplayed(), true);
        
    }

    public void verifyLeftMenuRecentsIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(leftMenuRecentsLink);
        Assert.assertEquals(leftMenuRecentsLink.isDisplayed(), true);
    }

    public void verifyLeftMenuMyEMDataIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(leftMenuMyEMDataLink);
        Assert.assertEquals(leftMenuMyEMDataLink.isDisplayed(), true);
    }
    
    public void verifyLeftMenuTrashIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(leftMenuTrashLink);
        Assert.assertEquals(leftMenuTrashLink.isDisplayed(), true);
    }








    //Click Import Radio Button Methods

    public void clickSubjectRadioButton() throws InterruptedException {
        Thread.sleep(4000);
        WaitTypes.clickWhenReadyByElement(subjectTableTypeLabel);
        Thread.sleep(1000);
        WaitTypes.clickWhenReadyByElement(subjectTableTypeLabel);
    }

    public void clickFoodRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(foodTableTypeLabel);
        Thread.sleep(1000);
        WaitTypes.clickWhenReadyByElement(foodTableTypeLabel);
    }

    public void clickDiaryRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(diaryTableTypeLabel);
        Thread.sleep(1000);
        WaitTypes.clickWhenReadyByElement(diaryTableTypeLabel);
    }

    public void clickNutrientsRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(nutrientsTableTypeLabel);
        Thread.sleep(1000);
        WaitTypes.clickWhenReadyByElement(nutrientsTableTypeLabel);
    }

    public void clickRandomForestTrainingRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(randomForestTrainingTableTypeLabel);
    }

    public void clickRandomForestTestRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(randomForestTestTableTypeLabel);
    }

    public void clickGenericRandomForestRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(genericRandomForestTableTypeLabel);
    }

    public void clickSugarConsumptionRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(sugarConsumptionTableTypeLabel);
    }

    public void clickMortalityRateRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(mortalityRateTableTypeLabel);
    }

    public void clickBacchusDataRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(bacchusDataTableTypeLabel);
    }
    // Duplicate declaration - import page - delete
    public void clickQuoteRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(quoteRadioButton);
    }
    // Duplicate declaration - import page - delete
    public void clickSingleQuoteRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(singleQuoteRadioButton);
    }
    // Duplicate declaration - import page - delete
    public void clickCommaRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(commaRadioButton);
    }
    // Duplicate declaration - import page - delete
    public void clickSemiColonRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(semicolonRadioButton);
    }
    // Duplicate declaration - import page - delete
    public void clickTabRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(tabRadioButton);
    }

    public void cancelImportFileNotification() throws Exception {

    	//WaitTypes.fluentWait(By.xpath("//*[@class='md-icon-button md-button md-em-bg-theme md-ink-ripple active']"));
    	
   	 	WaitTypes.clickWhenReadyByElement(importCompleteLink);
         WaitTypes.clickWhenReadyByElement(cancelImportFile);
         Thread.sleep(2000);
         

//        if (!(WaitTypes.waitForElementVisibilityByWebElement(importCompleteLink))) {
//            WaitTypes.clickWhenReadyByElement(importCompleteLink);
//            WaitTypes.clickWhenReadyByElement(cancelImportFile);
//        } else {
//            System.out.println("No notification");
//        }

    }

    public void clickOnTableCheckBox() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(tableCheckbox);

    }

    public void clickOnTrashButton() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(trashButton);

    }

    public void clickOnMainMenuTrashIcon() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(mainMenuTrashIcon);

    }

    public void clickOnRestoreLink() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(rightClickRestoreLink);

    }

    public void clickOnRestoreToLink() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(rightClickRestoreToLink);

    }

    public void clickOnGoBackButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(goBackButton);
    }

    public void clickOnFilesNewButton() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(newPlusLink);

    }

    public void clickOnMyDataLink() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(myDataPageTitle);

    }




    //Verifying checkbox is unchecked
    public void verifySubjectCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifyFoodCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifyDiaryCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifyNutrientsCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifyRandomForestTrainingCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifyRandomForestTestCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifyGenericRandomTestCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifySugarConsumptionCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }

    public void verifyMortalityRateCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }
    // delete
    public void verifyBacchusDataCheckBoxIsUnchecked() {
        subjectTableTypeLabel.getAttribute("false");
    }
    // delete
    public void verifyCommaCheckBoxIsUnchecked() {
        commaRadioButton.getAttribute("true");
    }
    // delete
    public void verifySemicolonBoxIsUnchecked() {
        semicolonRadioButton.getAttribute("false");
    }
    // delete
    public void verifyTabBoxIsUnchecked() {
        tabRadioButton.getAttribute("false");
    }

    public void verifyFileImportFailedAndCancelNotification() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(importCompleteLink);
        WaitTypes.waitForElementVisibilityByWebElement(ImportFileFailedNotification);
        WaitTypes.clickWhenReadyByElement(cancelImportFile);
    }




    public void importStartedMessageIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(importStartedToastMsg);
        Thread.sleep(3000);
    }



}
