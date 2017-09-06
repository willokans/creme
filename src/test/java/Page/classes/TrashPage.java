package Page.classes;

import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by will.okamuneh on 8/25/2016.
 */
public class TrashPage {


    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();



    @FindBy(xpath = "//div[@id='fs-header-wrapper']//h2[text()=' Trash ']")
    WebElement trashLabel;

    @FindBy(xpath = "//translate[text()='Empty Trash']")
    WebElement emptyTrashButton;

    @FindBy(xpath = "//div[@class='fs-files-counter ng-binding']")
    WebElement fileCounterLabel;

    @FindBy(xpath = "    //div[@class='modified ng-binding flex-60']")
    WebElement fileTimerLabel;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[1]")
    WebElement batchRestoreButton;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-rowmenu-restore-btn']")
    WebElement trashHamburgerRestoreButton;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-rowmenu-delete-btn']")
    WebElement trashHamburgerPermanentlyDeleteButton;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[3]")
    WebElement batchPermanentlyDeleteButton;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-rowmenu-restore-to-btn']")
    WebElement trashHamburgerRestoreToButton;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[2]")
    WebElement batchRestoreToButton;

    @FindBy(xpath = "//*[@id='fs-rc-menu-restore']")
    WebElement trashRestoreButton;

    @FindBy(xpath = "//*[@id='fs-rc-menu-restore-to']")
    WebElement trashRestoreToButton;

    @FindBy(xpath = "//*[text()='Complete Subject Table COPY']")
    WebElement copyCompleteSubjectTable;

    @FindBy (xpath = "//*[@id='fs-rc-menu-delete']")
    WebElement rightClickDropDownPermanentlyDelete;

    @FindBy(xpath = "//*[text()='Test Folder A COPY']")
    WebElement copyTestFolderA;

    @FindBy(xpath = "//*[text()='Complete Diary Table COPY']")
    WebElement hamburgerCopyDiaryTable;

    @FindBy(xpath = "//*[text()='Test Folder B COPY']")
    WebElement hamburgerCopyTestFolderB;

    @FindBy (xpath = "//*[text()='Yes, delete']")
    WebElement yesDeleteButton;

    @FindBy (xpath = "//*[@id='fs-burger-menu-complete-subject-table-copy']/md-menu[2]/md-icon")
    WebElement trashedSubjectTableHamburgerLink;

    @FindBy (xpath = "//*[@id='fs-burger-menu-failed-assessment1']/md-menu[2]/md-icon")
    WebElement trashedFailedAssessment1HamburgerLink;

    @FindBy (xpath = "//*[@id='fs-burger-menu-failed-assessment1-copy']/md-menu[2]/md-icon")
    WebElement trashedFailedAssessment1CopyHamburgerLink;

    @FindBy (xpath = "//*[@id='fs-burger-menu-test-folder-a-copy']/md-menu[2]/md-icon")
    WebElement trashedCopyFolderAHamburgerLink;

    @FindBy (xpath = "//span[text()='Your file has been restored']")
    WebElement yourFileHasBeenRestoredMessage;

    @FindBy (id = "fp-file-selector-my-data")
    WebElement myDataFolderSelect;

    @FindBy (xpath = ".//*[@id='filepicker']/div[1]/div/div[1]/button")
    WebElement filePickerMyDataBackArrowButton;

    @FindBy (xpath = "//*[text()='Permanently Delete']")
    WebElement permanentlyDeleteButton;

    @FindBy (xpath = "//span[text()='Are you sure you want to permanently delete these files/folders?']")
    WebElement permanentlyDeleteMessage;

    @FindBy (xpath = "//span[text()='Are you sure? This cannot be undone.']")
    WebElement emptyTrashMessage;

    @FindBy (xpath = "//translate[text()='Cancel']")
    WebElement emptyTrashCancelButton;

    @FindBy (xpath = "//button[@class='md-primary md-hue-3 md-raised cf-btn-xl md-button md-em-bg-theme md-ink-ripple']//translate[text()='Cancel']")
    WebElement permanentlyDeleteCancelButton;

    @FindBy(xpath = "//p[text()='Failed Assessment1 COPY']")
    WebElement failedAssessment1CopyLink;










    public TrashPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }









    public void rightClickCopiedCompleteSubjectTableAndPermanentlyDelete() throws Exception {
    	Thread.sleep(3000);
    	Constants.refreshPage();
        WaitTypes.waitForElementVisibilityByWebElement(copyCompleteSubjectTable);
        Constants.rightClickAndSelectElement(copyCompleteSubjectTable, rightClickDropDownPermanentlyDelete);
    }

    public void rightClickCopiedFailedAssessmentAndPermanentlyDelete() throws Exception {
    	Thread.sleep(3000);
    	Constants.refreshPage();
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1CopyLink);
        Constants.rightClickAndSelectElement(failedAssessment1CopyLink, rightClickDropDownPermanentlyDelete);
    }

    public void rightClickCopiedFolderAndPermanentlyDelete() throws InterruptedException {
    	Thread.sleep(3000);
    	Constants.refreshPage();
        WaitTypes.waitForElementVisibilityByWebElement(copyTestFolderA);
        Constants.rightClickAndSelectElement(copyTestFolderA, rightClickDropDownPermanentlyDelete);
        Thread.sleep(1000);
    }

    public void rightClickCopiedDiaryTableAndPermanentlyDelete() throws InterruptedException {
    	Thread.sleep(3000);
    	Constants.refreshPage();
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerCopyDiaryTable);
        Constants.rightClickAndSelectElement(hamburgerCopyDiaryTable, rightClickDropDownPermanentlyDelete);
        Thread.sleep(1000);
    }

    public void rightClickCopiedFolderBAndPermanentlyDelete() throws InterruptedException {
    	Thread.sleep(3000);
    	Constants.refreshPage();
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerCopyTestFolderB);
        Constants.rightClickAndSelectElement(hamburgerCopyTestFolderB, rightClickDropDownPermanentlyDelete);
        Thread.sleep(1000);
    }

    public void rightClickCopiedFolderAAndPermanentlyDelete() throws InterruptedException {
    	Thread.sleep(3000);
    	Constants.refreshPage();
        WaitTypes.waitForElementVisibilityByWebElement(copyTestFolderA);
        Constants.rightClickAndSelectElement(copyTestFolderA, rightClickDropDownPermanentlyDelete);
        Thread.sleep(1000);
    }

    public void rightClickCopiedSubjectTableInTrash() {
        Constants.rightClick(copyCompleteSubjectTable);
    }

    public void rightClickCopiedFailedAssessment1InTrash() {
        Constants.rightClick(failedAssessment1CopyLink);
    }

    public void rightClickCopiedFolderAInTrash() {
        Constants.rightClick(copyTestFolderA);
    }

    public void clickSubjectTableHamburgerMenu()  throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(copyCompleteSubjectTable);
        Constants.mouseHoover(copyCompleteSubjectTable);
        WaitTypes.waitForElementVisibilityByWebElement(trashedSubjectTableHamburgerLink);
        WaitTypes.clickWhenReadyByElement(trashedSubjectTableHamburgerLink);
    }

    public void clickFailedAssessment1HamburgerMenu() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1CopyLink);
        Constants.mouseHoover(failedAssessment1CopyLink);
        WaitTypes.waitForElementVisibilityByWebElement(trashedFailedAssessment1HamburgerLink);
        WaitTypes.clickWhenReadyByElement(trashedFailedAssessment1HamburgerLink);
    }

    public void clickFailedAssessment1CopyHamburgerMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1CopyLink);
        Constants.mouseHoover(failedAssessment1CopyLink);
        WaitTypes.waitForElementVisibilityByWebElement(trashedFailedAssessment1CopyHamburgerLink);
        WaitTypes.clickWhenReadyByElement(trashedFailedAssessment1CopyHamburgerLink);
    }

    public void clickCopyFolderAHamburgerMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(copyTestFolderA);
        Constants.mouseHoover(copyTestFolderA);
        WaitTypes.waitForElementVisibilityByWebElement(trashedCopyFolderAHamburgerLink);
        WaitTypes.clickWhenReadyByElement(trashedCopyFolderAHamburgerLink);
    }

    public void clickOnYesDeleteButton() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(yesDeleteButton);
        Thread.sleep(1000);
    }

    public void clickTrashedSubjectTable() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(copyCompleteSubjectTable);
    }

    public void clickTrashedCopyAssessment1() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(failedAssessment1CopyLink);
    }

    public void clickTrashedCopyFolderA() throws InterruptedException {
    	Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(copyTestFolderA);
    }

    public void clickOnBatchRestoreLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(batchRestoreButton);
    }

    public void clickOnBatchRestoreToLink() throws InterruptedException {
       WaitTypes.clickWhenReadyByElement(batchRestoreToButton);
    }

    public void clickBatchRestoreButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(batchRestoreButton);
    }

    public void clickHamburgerRestoreButton() throws Exception {
    	Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(trashHamburgerRestoreButton);
    }

    public void clickHamburgerRestoreToButton() throws Exception {
    	Thread.sleep(3000);
    	//WaitTypes.flientWaitFindElement(driver, By.xpath("//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-rowmenu-restore-to-btn']"), 5000);
    	WaitTypes.verifiedWhenReadyByElement(trashHamburgerRestoreToButton);
        WaitTypes.clickWhenReadyByElement(trashHamburgerRestoreToButton);
    }

    public void clickHamburgerPermanentlyDeleteButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(trashHamburgerPermanentlyDeleteButton);
    }

    public void clickOnPermanentlyDeleteLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(permanentlyDeleteButton);
    }

    public void clickOnPermanentlyCancelDeleteButton() throws Exception {
    	
    	WaitTypes.flientWaitFindElement(driver, By.xpath("//button[@class='md-primary md-hue-3 md-raised cf-btn-xl md-button md-em-bg-theme md-ink-ripple']//translate[text()='Cancel']"), 5000);
    	
    	Thread.sleep(3000);;
    	
        WaitTypes.clickWhenReadyByElement(permanentlyDeleteCancelButton);
    }

    public void clickOnEmptyTrashCancelButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(emptyTrashCancelButton);
    }

    public void clickYesToPermanentlyDeleteButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(yesDeleteButton);
    }

    public void clickEmptyTrashButtonOnTrashPage() throws InterruptedException {

        WaitTypes.clickWhenReadyByElement(emptyTrashButton);

    }

    public void selectMyDataFromFolderDialogBox() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(myDataFolderSelect);

    }

    public void clickTrashedFailedAssessment1Copy() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(failedAssessment1CopyLink);
    }





    public void verifyEmptyTrashButtonIsDisplayed()  {
        WaitTypes.waitForElementVisibilityByWebElement(emptyTrashButton);
    }

    public void verifyFileTimerLabelIsDisplayed()  {
        WaitTypes.waitForElementVisibilityByWebElement(fileTimerLabel);
    }

    public void verifyFileCounterLabelIsDisplayed()  {
        WaitTypes.waitForElementVisibilityByWebElement(fileCounterLabel);
    }

    public void verifyTrashLabelIsDisplayed()  throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(trashLabel);
    }
    
    public void verifyUserIsOnTrashPage(){
    	WaitTypes.waitForElementVisibilityByWebElement(trashLabel);
    	trashLabel.isDisplayed();
    	WaitTypes.waitForElementVisibilityByWebElement(emptyTrashButton);
    	emptyTrashButton.isDisplayed();
    }

    public void verifyRestoreButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(trashRestoreButton);
    }

    public void verifyEmptyTrashMessageIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(emptyTrashMessage);
        Thread.sleep(3000);
    }

    public void verifyRestoreToButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(trashRestoreToButton);
    }

    public void verifyPermanentlyDeleteButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickDropDownPermanentlyDelete);
    }

    public void verifyTrashHamburgerRestoreLinkIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(trashHamburgerRestoreButton);
    }

    public void verifyTrashHamburgerRestoreToLinkIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(trashHamburgerRestoreToButton);
    }

    public void verifyTrashHamburgerPermanentlyDeleteLinkIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(trashHamburgerPermanentlyDeleteButton);
    }

    public void verifyBatchRestoreButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchRestoreButton);
    }

    public void verifyBatchRestoreToButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchRestoreToButton);
    }

    public void verifyBatchPermanentlyButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchPermanentlyDeleteButton);
    }

    public void verifyYourFileHasBeenRestoredMessageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenRestoredMessage);
    }

    public void verifyPermanentlyDeleteMessageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(permanentlyDeleteMessage);
    }


}
