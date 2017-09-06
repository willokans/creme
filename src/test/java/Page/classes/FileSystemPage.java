package Page.classes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
 * Created by will.okamuneh on 7/29/2016.
 */
public class FileSystemPage {


    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    // to be deleted
    @FindBy(xpath = ".//*[@id='fs-row-2725']/td[2]/div[1]/div[2]")
    WebElement diaryTable;
    
    @FindBy(xpath = "//div[@title='QA']/ancestor::tr")
    //@FindBy(id = "O137")
    WebElement emDataQAFolder;
    
    //@FindBy(id = "fs-row-152")
    @FindBy(xpath = "//div[@title='Category2']/ancestor::tr")
    WebElement category2Table;
    
    @FindBy(id = "empty-folder")
    WebElement emptyFolderText;
    
    @FindBy(xpath = "//div[@id='fs-header-wrapper']//h2[text()=' QA ']")
    WebElement emDataQAFolderTitle;

    @FindBy(id = "fs-file-creator-launcher")
    WebElement newPlusLink;

    //@FindBy(id = "fs-create-new")
    //WebElement newFolderLink;

    @FindBy(xpath = "//div/div[@id='fs-create-menu']//div//span//translate[text()='New Folder']")
    WebElement newFolderLink;

    @FindBy(xpath = "//*[text()='Recents']")
    WebElement sideMenuBarRecentLink;

    @FindBy(id = "newfile")
    WebElement newFileTextBox;

    @FindBy(xpath = "//md-icon[text()='check']")
    WebElement newItemCheckButton;

    @FindBy(xpath = "//md-icon[text()='clear']")
    WebElement newItemClearButton;
    
    @FindBy(xpath = "//*[text()='QA Sub Folder']")
    WebElement qaSubFolder;
    
    @FindBy(xpath = "//div[@id='fs-header-wrapper']//h2[text()=' QA Sub Folder ']")
    WebElement qaSubFolderTitle;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder A']")
    WebElement testFolderA;
    // to be deleted
    @FindBy(xpath = "//*[text()=' Test Folder A ']")
    WebElement testFolderACookie;
    // to be deleted
    @FindBy(xpath = "//*[text()=' Test Folder C ']")
    WebElement testFolderCCookie;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder AA1']")
    WebElement testFolderAA1;
	// to be deleted
    @FindBy(xpath = "//*[text()='Test Folder AA2']")
    WebElement testFolderAA2;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder A (1)']")
    WebElement testFolderAAppend1;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder A (1) (1)']")
    WebElement testFolderAAppend2;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder B']")
    WebElement testFolderB;
	// to be deleted
    @FindBy(xpath = "//*[text()='Test Folder C']")
    WebElement testFolderC;
    // to be deleted
    @FindBy(xpath = "//h2[text()='Test Folder C']")
    WebElement testFolderCLable;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder D']")
    WebElement testFolderD;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder AB']")
    WebElement testFolderAB;
    // to be deleted
    @FindBy(xpath = "//*[text()='Test Folder AA']")
    WebElement testFolderAA;
    // to be deleted
    @FindBy(xpath = "//*[text()=' Test Folder AA ']")
    WebElement testFolderAACookie;

    @FindBy(xpath = "//*[text()=' Test Folder AA ']")
    WebElement testFolderAAPage;

    @FindBy(xpath = "//p[text()='QA Test Folder']")
    WebElement qaTestFolder;
    
    @FindBy(xpath = "//p[text()='QA Test Table']")
    WebElement qaTestTable;
    
    @FindBy(xpath = "//p[text()='QA Test Assessment']")
    WebElement qaTestAx;
    
    // delete food
    @FindBy(xpath = "//p[text()='Food Test Table']")
    WebElement qaTestTableLink;
    // delete diary
    @FindBy(xpath = "//p[text()='Diary Test Table']")
    WebElement diaryTestTableLink;
 
    @FindBy (xpath = ".//*[@id='fs-rc-menu-trash']")
    WebElement rightClickDropDownTrash;
    // delete nutrients
    @FindBy(xpath = "//p[text()='Nutrient Test Table']")
    WebElement NutrientTestTableLink;
    // delete RF
    @FindBy(xpath = "//p[text()='RF Training Test Table']")
    WebElement RFTrainingTestTableLink;

    @FindBy(xpath = "//p[text()='RF Test Table']")
    WebElement RFTestTestTableLink;

    @FindBy(xpath = "//p[text()='GRF Test Table']")
    WebElement GRFTestTableLink;

    @FindBy(xpath = "//p[text()='Sugar Con Test Table']")
    WebElement sugarConTestTableLink;

    @FindBy(xpath = "//p[text()='Mortality Rate Test Table']")
    WebElement mortalityRateTestTableLink;

    @FindBy(xpath = "//p[text()='New Folder']")
    WebElement defaultFolderNameLink;

    @FindBy(xpath = "//p[text()='Bacchus Data Test Table']")
    WebElement bacchusDataTestTableLink;

    @FindBy(xpath = "//*[text()='Your table has been created.']")
    WebElement yourTableHasBeenCreatedMessage;
    
    @FindBy(xpath = "//*[text()='Your folder has been created.']")
    WebElement yourFolderHasBeenCreatedMessage;

    @FindBy(xpath = "//span[text()='Your file has been copied successfully']")
    WebElement yourFileHasBeenCopiedMessage;

    @FindBy(xpath = "//*[text()='Your files have been copied successfully']")
    WebElement yourFilesHasBeenCopiedMessage;

    @FindBy(xpath = "//*[text()='Your file has been moved successfully']")
    WebElement yourFileHasBeenMovedMessage;

    @FindBy(xpath = "//*[text()='Your files have been moved successfully']")
    WebElement yourFilesHasBeenMovedMessage;

    @FindBy(xpath = "//*[text()='Your file has been restored']")
    WebElement yourFileHasBeenRestoredMessage;

    @FindBy(xpath = "//*[text()='Your files have been restored']")
    WebElement yourFilesHasBeenRestoredMessage;

    @FindBy(xpath = "//*[text()='Your file has been deleted.']")
    WebElement yourFileHasBeenDeletedMessage;

    @FindBy(xpath = "//*[text()='Your file has been trashed.']")
    WebElement yourFileHasBeenTrashMessage;

    @FindBy(xpath = "//*[text()='Your files have been trashed.']")
    WebElement yourFilesHasBeenTrashMessage;

    @FindBy(xpath = "//span[text()='Started to copy 1 file']")
    WebElement startedToCopy1FileMessage;

    @FindBy(xpath = "//*[@id='fs-rc-menu-copy']")
    WebElement rightClickCopyMenu;

    @FindBy(xpath = "//*[@id='fs-rc-menu-copy-to']")
    WebElement rightClickCopyToMenu;

    @FindBy(xpath = ".//*[@id='fs-rc-menu']/md-menu-content/md-menu-item[3]/button")
    WebElement rightClickMoveMenu;

    @FindBy(xpath = "//*[@ng-if=\"(fs.rightClick.data.job_status === 'Failed' || fs.rightClick.data.job_status === 'Completed' || fs.rightClick.data.job_status === 'Cancelled') && fs.rightClick.data.fileTypeIs('job')\"]")
    WebElement rightClickReRunAssessmentMenu;

    @FindBy(xpath = "//*[@id='fs-rc-menu-trash']")
    WebElement rightClickTrashMenu;

    //@FindBy(xpath = "html/body/div[1]/md-content/div/div/div/md-content/div[1]/div/div/md-menu-content/md-menu-item[5]/button")
    @FindBy(id = "fs-rc-menu-download")
    WebElement rightClickDownloadMenu;

    @FindBy(xpath = "//*[@id='fs-rc-menu-rerun']")
    WebElement rightClickOpenInTabMenu;

    @FindBy(xpath = "//*[text()='Complete Diary Table']")
    WebElement completeDiaryTable;

    @FindBy(xpath = "//*[text()='Complete Subject Table COPY']")
    WebElement copyCompleteSubjectTable;

    @FindBy(xpath = "//*[text()='Complete Subject Table']")
    WebElement completeSubjectTable;

    @FindBy(xpath = "//*[text()='Complete Food Table']")
    WebElement completeFoodTable;

    @FindBy(xpath = "//*[text()='Nutrient']")
    WebElement datasetNutrientTable;

    @FindBy(xpath = "//*[text()='Complete Subject Table COPY']")
    WebElement completeSubjectTableCopy;

    @FindBy(xpath = "//*[text()='Test Folder A COPY']")
    WebElement copyTestFolderA;

    @FindBy(xpath = "//*[text()='Test Folder B COPY']")
    WebElement hamburgerCopyTestFolderB;

    @FindBy(xpath = "//*[text()='Complete Diary Table COPY']")
    WebElement hamburgerCopyDiaryTable;

    @FindBy (id = "fp-file-selector-my-data")
    WebElement myDataFolderSelect;

    @FindBy (id = "fp-file-selector-shared-data")
    WebElement sharedDataFolderSelect;

    @FindBy (xpath = "//*[@id='filepicker']/div[3]/div/div[3]/button")
    WebElement filePickerMenuCopyToOrMoveOrRestoreButton;

    @FindBy (xpath = "//*[@id='fs-burger-menu-test-folder-a']/md-menu[1]/md-icon")
    WebElement testFolderAHamburgerMenu;

    @FindBy(xpath = "//*[@id='fs-burger-menu-test-folder-b']/md-menu[1]/md-icon")
    WebElement hamburgerMenuTestFolderB;

    @FindBy(xpath = "//*[@id='fs-burger-menu-test-folder-a']/md-menu[1]/md-icon")
    WebElement hamburgerMenuTestFolderA;

    @FindBy(id = "fs-burger-menu-qa-test-table")
    WebElement hamburgerMenuQATable;

    @FindBy(xpath = "//*[@id='fs-burger-menu-failed-assesment']/md-menu[1]/md-icon")
    WebElement hamburgerMenuFailedAssessment;

    @FindBy(xpath = "//*[@id='fs-burger-menu-failed-assessment1']/md-menu[1]/md-icon")
    WebElement hamburgerMenuFailedAssessment1;

    @FindBy (xpath = "//*[@id='fp-file-selector-test-folder-c']/div/button")
    WebElement filePickerMyDataTestFolderC;

    @FindBy (xpath = "//*[@id='filepicker']/div[1]/div/div[1]/button")
    WebElement filePickerMyDataBackArrowButton;

    @FindBy (xpath = "//h2[text()='Export']")
    WebElement exportPageLabel;

    @FindBy(xpath = "//*[text()='FILES']")
    WebElement mainMenuBarFilesLink;

    @FindBy(xpath = "//*[text()='Valid table']")
    WebElement validTableImportTableLabel;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-copy-btn']")
    WebElement hamburgerDropDownCopyOption;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-copy-to-btn']")
    WebElement hamburgerDropDownCopyToOption;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-move-btn']")
    WebElement hamburgerDropDownMoveOption;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-trash-btn']")
    WebElement hamburgerDropDownTrashOption;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-details-btn']")
    WebElement hamburgerDropDownMoreDetailsOption;

    //@FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-download-btn']")
    @FindBy(id = "fs-rowmenu-download-btn")
    WebElement hamburgerDropDownDownloadOption;

    @FindBy(xpath = "//h1[text()='Test Folder B']")
    WebElement moreDetailsTestFolderB;

    @FindBy(xpath = "//h1[text()='Complete Diary Table']")
    WebElement moreDetailsCompleteDiaryTable;

    @FindBy(xpath = "//h1[text()='Failed Assessment1']")
    WebElement moreDetailsFailedAssessment1;

    @FindBy(xpath = "//div[text()='folder']")
    WebElement moreDetailsFolderType;

    @FindBy(xpath = "//div[text()='table']")
    WebElement moreDetailsTableType;

    @FindBy(xpath = "//div[text()='job']")
    WebElement moreDetailsJobType;

    @FindBy(xpath = ".//*[@id='fs-viewer']/md-toolbar/div[2]/md-icon")
    WebElement closeMoreDetailsInfoBox;

    @FindBy(xpath = "//div[@class='intercom-launcher']")
    WebElement intercomIconLink;

    @FindBy(xpath = "//*[@id='intercom-composer']/div[2]/div[2]/textarea")
    WebElement intercomTextBox;

    @FindBy(xpath = "//*[@id='intercom-conversation']/div[1]")
    WebElement intercomConversationDialogBox;

    @FindBy(xpath = "//*[@id='intercom-conversation']/div[1]/a[2]/div")
    WebElement intercomConversationDialogBoxCloseButton;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/md-menu/button[@id='fs-run-model-btn']")
    WebElement runModelLink;
    
    @FindBy(id = "fs-new-model-qa-test-model")
    WebElement runQAModelLink;
    
    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-new-model-creme-nutrition-intakes']")
    WebElement newCremeNutritionIntakesLinks;

    //@FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/md-menu/button[@id='fs-create-table-btn']")
    @FindBy(id = "fs-create-table-btn")
    WebElement newTableLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/a[@id='fs-import-new']")
    WebElement uploadTableLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-subjects']")
    WebElement newTableSubjectLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-food']")
    WebElement newTableFoodLink;
    
    @FindBy(id = "fs-new-model-qamodel")
    WebElement newTableQAModelLink;
    
    @FindBy(id = "fs-create-qa-category")
    WebElement newTableQACategory;
    
    @FindBy(id = "fs-create-qa-data")
    WebElement newTableQAData;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-diary']")
    WebElement newTableDiaryLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-nutrients']")
    WebElement newTableNutrientLink;

    @FindBy(xpath = "//div[@class='fs-table-file-name no-outline flex']//*[text()='New Table']")
    WebElement defaultFileName;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-random-forest-training']")
    WebElement newTableRFTrainLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-random-forest-test']")
    WebElement newTableRFTestLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-generic-random-forest']")
    WebElement newTableGRFLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-sugar-consumption']")
    WebElement newTableSugarConsumptionLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-mortality-rate']")
    WebElement newTableMortalityRateLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-create-bacchus-data']")
    WebElement newTableBacchusDataLink;

    @FindBy(xpath = "//*[@id='exportForm']/div/button[1]")
    WebElement downloadTheZipFileButton;

    @FindBy(xpath = "//a[text()='Shared Data']")
    WebElement sharedDataLink;

    @FindBy(xpath = "//p[text()='Failed Assessment1']")
    WebElement failedAssessment1Link;

    @FindBy(xpath = "//p[text()='Failed Assessment1 COPY']")
    WebElement failedAssessment1LinkCopy;

    @FindBy(xpath = "//p[text()='Failed Assessment2']")
    WebElement failedAssessment2Link;

    @FindBy(xpath = "//p[text()='Failed Assessment1 COPY']")
    WebElement failedAssessment1CopyLink;

    @FindBy(xpath = "//p[text()='Completed Assessment']")
    WebElement completedAssessmentLink;

    @FindBy(xpath = "//p[text()='Draft Assessment']")
    WebElement draftAssessmentLink;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[3]")
    WebElement batchMoveButton;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[4]")
    WebElement batchTrashButton;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[3]")
    WebElement batchPermanentlyDeleteButton;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[2]")
    WebElement batchRestoreToButton;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[2]")
    WebElement batchCopyToButton;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button[1]")
    WebElement batchCopyButton;

    @FindBy(xpath = "//md-input-container[@aria-label='update filename']//*[@aria-invalid='false']")
    WebElement testItemTextBox;

    @FindBy(xpath = "//md-input-container[@aria-label='update filename']//*[@aria-invalid='true']")
    WebElement blankTestFolderATextBox;

    @FindBy(xpath = "//p[text()='New Folder Name']")
    WebElement newFolderNameLink;

    @FindBy(xpath = "//p[text()='New Table Name']")
    WebElement newTableNameLink;

    @FindBy(xpath = "//p[text()='New Assessment Name']")
    WebElement newAssessmentNameLink;

    @FindBy(xpath = "//div[@class='md-toast-content']")
    WebElement fileAlreadyExistsErrorMessage;

    @FindBy(xpath = "//*[@id='fs-table-file-edit-test-folder-a']")
    WebElement testFolderAPencilEditLink;

    @FindBy(xpath = "//*[@id='fs-table-file-edit-complete-subject-table']")
    WebElement testSubjectTablePencilEditLink;

    @FindBy(xpath = "//*[@id='fs-table-file-edit-failed-assessment1']")
    WebElement testAssessmentPencilEditLink;

    @FindBy(xpath = "//*[text()='undo']")
    WebElement undoLink;

    @FindBy(xpath = "//*[@ng-if='!fs.fileSystem.currentFile.isRoot()']")
    WebElement cookieLink;

    @FindBy(xpath = "//*[@id='fs-table-container']")
    List<WebElement> myDataTable;

    @FindBy(xpath = "//span[text()=' Sort ']")
    WebElement sortLabel;

    @FindBy(xpath = "//span[text()=' Name ']")
    WebElement sortLabelByName;

    @FindBy(xpath = "//*[@id='fs-table-info-block']/div[2]/div/md-chips/md-chips-wrap/md-chip")
    WebElement sortByNameXIcon;

    @FindBy(xpath = "//md-chips/md-chips-wrap/md-chip/div[2]/button")
    WebElement sortByCancelIcon;

    @FindBy(xpath = "//span[text()=' Modified ']")
    WebElement sortLabelByModified;

    @FindBy(xpath = "//span[text()=' Created ']")
    WebElement sortLabelByCreate;

    @FindBy(xpath = "//*[@id='fs-table-info-block']/div[2]/div/md-chips/md-chips-wrap/md-chip")
    WebElement sortByCreateXIcon;

    @FindBy(xpath = "//span[text()=' Type ']")
    WebElement sortLabelByType;

    @FindBy(xpath = "//span[text()=' Owner ']")
    WebElement sortLabelByOwner;
    
    @FindBy(xpath = "//a[text()='My Data']")
    WebElement error500MyDataLink;
    
    @FindBy (xpath = "//*[text()='delete']")
    WebElement mainMenuTrashIcon;

    @FindBy (xpath = "//input[@aria-label='Model Name']")
    WebElement newModelNameTextBox;
    
    @FindBy (xpath = "//md-dialog//button[@ng-click='submit(jobName)']")
    WebElement confirmModelNameButton;










    public FileSystemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }





    public void dragDiaryTableToTestFolder2 () {

        //Constants.dragAndDrop(diaryTable, testFolderB);
    }

    public void navigateToNewFolderLink() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        Thread.sleep(1000);
        WaitTypes.clickWhenReadyByElement(newFolderLink);
        WaitTypes.waitForElementVisibilityByWebElement(newFileTextBox);

    }
    
    public void enterItemNameIntoNewItemTextBox(String s) throws InterruptedException {
        //WaitTypes.waitForElementVisibilityByWebElement(newFileTextBox);
    	WaitTypes.waitUntilElementIsClickable(newFileTextBox);
        newFileTextBox.sendKeys(s);
        Thread.sleep(1000);

    }
    
    public void createNewDraftQAAx(String newAxName) throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        Thread.sleep(1000);
        WaitTypes.clickWhenReadyByElement(runQAModelLink);
        WaitTypes.waitUntilElementIsClickable(newModelNameTextBox);
        newModelNameTextBox.clear();
        newModelNameTextBox.sendKeys(newAxName);
        WaitTypes.clickWhenReadyByElement(confirmModelNameButton);        
    }

    public void selectTestFolderCFromFilePickerDialogBox() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(myDataFolderSelect);

        if (myDataFolderSelect.isDisplayed()) {
            Constants.doubleClickOnElement(myDataFolderSelect);
            Thread.sleep(1000);
            WaitTypes.waitForElementVisibilityByWebElement(filePickerMyDataTestFolderC);
            Thread.sleep(1000);
            WaitTypes.clickWhenReadyByElement(filePickerMyDataTestFolderC);

        }else {
            WaitTypes.clickWhenReadyByElement(filePickerMyDataBackArrowButton);
            Constants.doubleClickOnElement(myDataFolderSelect);
            WaitTypes.waitForElementVisibilityByWebElement(filePickerMyDataTestFolderC);
            Thread.sleep(1000);
            WaitTypes.clickWhenReadyByElement(filePickerMyDataTestFolderC);
        }


    }

    public void selectMyDataFromFolderDialogBox() throws InterruptedException {
        WaitTypes.waitUntilElementIsClickable(myDataFolderSelect);
        //WaitTypes.fluentWait(By.xpath("//*[@id='fp-file-selector-my-data']/button[1]"));
        Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(myDataFolderSelect);

    }

    public void selectSharedDataFromFolderDialogBox() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(sharedDataFolderSelect);
        Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(sharedDataFolderSelect);

    }

    public void selectMyDateFromFolderSelect () throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(myDataFolderSelect);
        Thread.sleep(2000);
            WaitTypes.clickWhenReadyByElement(myDataFolderSelect);
    }

    public void selectMyDataFromFolderSelectWithFilePicker() throws InterruptedException {
    	Thread.sleep(2000);
    	//WaitTypes.fluentWait(By.xpath("//*[@id='filepicker']/div[1]/div/div[1]/button"));
    	WaitTypes.waitUntilElementIsClickable(filePickerMyDataBackArrowButton);
        WaitTypes.clickWhenReadyByElement(filePickerMyDataBackArrowButton);
        
        
        WaitTypes.waitForElementVisibilityByWebElement(myDataFolderSelect);
        
        WaitTypes.waitUntilElementIsClickable(myDataFolderSelect);
        WaitTypes.clickWhenReadyByElement(myDataFolderSelect);
    }


    public void selectMoveOrCopyToFromFilePickerDialogBox() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(filePickerMenuCopyToOrMoveOrRestoreButton);
        WaitTypes.clickWhenReadyByElement(filePickerMenuCopyToOrMoveOrRestoreButton);
        Thread.sleep(1000);
    }

    public void pressEnterInNewFolderTextBox() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newFileTextBox);
        newFileTextBox.sendKeys(Keys.RETURN);
    }
    // delete
    public void createSubjectNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableSubjectLink);
        WaitTypes.clickWhenReadyByElement(newTableSubjectLink);

    }
    
    public void createQACategoryTable() throws InterruptedException{
    	WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableQAModelLink);
        WaitTypes.clickWhenReadyByElement(newTableQAModelLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableQACategory);
        WaitTypes.clickWhenReadyByElement(newTableQACategory);
    }
    // to be deleted
    public void createFoodNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableFoodLink);
        WaitTypes.clickWhenReadyByElement(newTableFoodLink);

    }
    // to be deleted
    public void createDiaryNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableDiaryLink);
        WaitTypes.clickWhenReadyByElement(newTableDiaryLink);

    }
    // to be deleted
    public void createNutrientNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableNutrientLink);
        WaitTypes.clickWhenReadyByElement(newTableNutrientLink);

    }
    // to be deleted
    public void createFRTrainingNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableRFTrainLink);
        WaitTypes.clickWhenReadyByElement(newTableRFTrainLink);

    }
    // to be deleted
    public void createFRTestNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableRFTestLink);
        WaitTypes.clickWhenReadyByElement(newTableRFTestLink);

    }
    // to be deleted
    public void createGRFNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        Constants.scrollToElementByElement(newTableGRFLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableGRFLink);
        WaitTypes.clickWhenReadyByElement(newTableGRFLink);

    }
    // to be deleted
    public void createSugarConsumptionNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        Constants.scrollToElementByElement(newTableSugarConsumptionLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableSugarConsumptionLink);
        WaitTypes.clickWhenReadyByElement(newTableSugarConsumptionLink);

    }
    // to be deleted
    public void createMortalityRateNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        Constants.scrollToElementByElement(newTableMortalityRateLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableMortalityRateLink);
        WaitTypes.clickWhenReadyByElement(newTableMortalityRateLink);

    }
    // to be deleted
    public void createBacchusDataNewTable() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
        WaitTypes.clickWhenReadyByElement(newTableLink);
        Constants.scrollToElementByElement(newTableBacchusDataLink);
        WaitTypes.waitForElementVisibilityByWebElement(newTableBacchusDataLink);
        WaitTypes.clickWhenReadyByElement(newTableBacchusDataLink);

    }





    public void verifyTestFolderAIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
        testFolderA.isDisplayed();
    }

    public void verifyCookieTrailTestFolderAIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderACookie);
    }

    public void verifyCookieTrailTestFolderCIsDisplayed() throws InterruptedException {
    	Constants.refreshPage();
    	Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderCCookie);
    }
    // to be deleted
    public void verifyCookieTestFolderAA1IsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAA1);
    }
    // to be deleted
    public void verifyTestFolderAA2IsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAA2);
    }
    // to be deleted  
    public void verifyTestFolderAA1IsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAA1);
    }
    // to be deleted
    public void verifyTestFolderAAppend1IsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAAppend1);
    }
    // to be deleted
    public void verifyTestFolderAAppend2IsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAAppend2);
    }
    // to be deleted
    public void verifyCompletedSubjectTableIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(completeSubjectTable);
    }
    
    
    
    // Used from here ->
    public void verifyQATestTableIsDisplayed() throws Exception {
        WaitTypes.waitUntilElementIsClickable(qaTestTable);
        Assert.assertEquals(qaTestTable.isDisplayed(), true);
    }
    
    public void verifyQATestFolderIsDisplayed() throws Exception {
        WaitTypes.waitUntilElementIsClickable(qaTestFolder);
        Assert.assertEquals(qaTestFolder.isDisplayed(), true);
    }
    
    public void verifyQATestAxIsDisplayed() throws Exception {
        WaitTypes.waitUntilElementIsClickable(qaTestAx);
        Assert.assertEquals(qaTestAx.isDisplayed(), true);
    }
    
    public void openQATestTable() throws Exception {
        WaitTypes.waitForCSVFileAvailability(qaTestTable);
        Constants.doubleClickOnElement(qaTestTable);
    }
    
    public void openQATestFolder() throws Exception {
        WaitTypes.waitUntilElementIsClickable(qaTestFolder);
        Constants.doubleClickOnElement(qaTestFolder);
    }
    
    public void openQATestAx() throws Exception {
        WaitTypes.waitUntilElementIsClickable(qaTestAx);
        Constants.doubleClickOnElement(qaTestAx);
    }
    
    public void verifyEMDataQAFolderIsDisplayed(){
    	WaitTypes.waitForElementVisibilityByWebElement(emDataQAFolder);
        Assert.assertEquals(emDataQAFolder.isDisplayed(), true);
    }
    
    public void openEMDataQAFolder() throws Exception {
        WaitTypes.waitUntilElementIsClickable(emDataQAFolder);
        Constants.doubleClickOnElement(emDataQAFolder);
    }
    
    public void openEMDataCategory2Table() throws Exception {
        WaitTypes.waitUntilElementIsClickable(category2Table);
        Constants.doubleClickOnElement(category2Table);
    }
    
    public void verifyEMDataCategory2TableIsDisplayed() throws Exception {
        WaitTypes.waitUntilElementIsClickable(category2Table);
        Assert.assertEquals(category2Table.isDisplayed(), true);
    }
    
    public void verifyFolderLocationIsEmpty(){
    	WaitTypes.waitForElementVisibilityByWebElement(emptyFolderText);
    	Assert.assertEquals(emptyFolderText.isDisplayed(), true);
    }
    
    public void selectItemAndVerifySelectionStatus(String item, boolean status) throws Exception {
    	WebElement element = driver.findElement(By.xpath("//p[text()='"+item+"']"));
        WaitTypes.clickWhenReadyByElement(element);
        WaitTypes.waitForElementVisibilityByWebElement(batchCopyButton);
        Assert.assertEquals(batchCopyButton.isDisplayed(), status);
    }
    
    public void deselectItemAndVerifySelectionStatus(String item) throws Exception {
    	WebElement element = driver.findElement(By.xpath("//p[text()='"+item+"']"));
        WaitTypes.clickWhenReadyByElement(element);
        AssertType.assertEqualsNotDisplayed(By.xpath("//*[@id='fs-batch-actions']"));
    }
    
    public void waitForModelRunCompletion(String modelRun){
    	//WebElement jobElement = modelRun.findElement(By.xpath("/ancestor::tr"));
    	WebElement jobElement = driver.findElement(By.xpath(modelRun+"/ancestor::tr"));
    	System.out.println("Status is: "+jobElement.getAttribute("job-status"));
    	WaitTypes.waitForJobCompletionByWebElement(jobElement);
    }
    
    public void openCompletedQAAx(){
    	waitForModelRunCompletion("//p[text()='QA Test Assessment']");
    	Constants.doubleClickOnElement(qaTestAx);
    }
    
    public void verifyQATestFolderIsNotDisplayed() throws Exception{
    	AssertType.assertEqualsNotDisplayed(By.xpath("//p[text()='QA Test Folder']"));
    }
    // -> to here
    
    
    
    public void verifyCompletedDiaryTableIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(completeDiaryTable);
    }

    public void verifyCompletedFoodTableIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(completeFoodTable);
    }

    public void verifyTestFolderBIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderB);
    }

    public void verifyTestFolderCIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderC);
    }

    public void verifyTestFolderDIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderD);
    }
    
    

    public void verifyRightClickCopyMenuIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickCopyMenu);
    }

    public void verifyRightClickCopyToMenuIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickCopyToMenu);
    }

    public void verifyRightClickMoveMenuIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickMoveMenu);
    }

    public void verifyRightClickReRunAssessmentMenuIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickReRunAssessmentMenu);
    }

    public void verifyRightClickTrashMenuIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickTrashMenu);
    }

    public void verifyRightClickOpenInMenuIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickOpenInTabMenu);
    }

    public void verifyRightClickDownloadMenuOptionIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickDownloadMenu);
    }

    public void verifyFileHasBeenCopiedIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenCopiedMessage);
        Thread.sleep(2000);
    }

    public void verifyFilesHasBeenCopiedIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFilesHasBeenCopiedMessage);
        Thread.sleep(2000);
    }

    public void verifyFileHasBeenMovedIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenMovedMessage);
        Thread.sleep(2000);
    }

    public void verifyFilesHasBeenMovedIsDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(yourFilesHasBeenMovedMessage);
        Thread.sleep(1000);
    }

    public void verifyFileHasBeenRestoredIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenRestoredMessage);
    }

    public void verifyFilesHasBeenRestoredIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(yourFilesHasBeenRestoredMessage);
    }

    public void verifyFileHasBeenTrashIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenTrashMessage);
        Thread.sleep(2000);
    }

    public void verifyFileHasBeenDeletedIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFileHasBeenDeletedMessage);
        Thread.sleep(1000);
    }

    public void verifyFilesHasBeenTrashIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(yourFilesHasBeenTrashMessage);
        Thread.sleep(1000);
    }

    public void verifyMoreDetailsOnFolderIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsTestFolderB);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsFolderType);
        WaitTypes.fluentWait(By.xpath(".//*[@id='fs-viewer']/md-toolbar/div[2]/md-icon"));
        //WaitTypes.clickWhenReadyByElement(closeMoreDetailsInfoBox);
        

    }

    public void verifyMoreDetailsOnTableIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsCompleteDiaryTable);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsTableType);
        WaitTypes.fluentWait(By.xpath("//*[@id='fs-viewer']/md-toolbar/div[2]/md-icon"));
        WaitTypes.clickWhenReadyByElement(closeMoreDetailsInfoBox);

    }

    public void verifyMoreDetailsOnFailedAssessment1IsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsFailedAssessment1);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsJobType);
        WaitTypes.clickWhenReadyByElement(closeMoreDetailsInfoBox);

    }

    public void verifyTestFolderACopyIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(copyTestFolderA);
        Thread.sleep(2000);
    }

    public void verifyTestFolderBCopyIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerCopyTestFolderB);
        Thread.sleep(1000);
    }
    // delete
    public void verifyCompleteDiaryTableCopyIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(qaTestTable);
        Thread.sleep(1000);
    }

    public void verifyFailedAssessmentCopyIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1Link);
        Thread.sleep(1000);
    }

    public void verifyTestFolderBInTestFolderCIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderC);
        Constants.doubleClickOnElement(testFolderC);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderB);
    }

    public void verifyTestFolderAInTestFolderCIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderC);
        Constants.doubleClickOnElement(testFolderC);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
    }
    // delete
    public void verifyCompleteDiaryTableInTestFolderDIsDisplayed() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderC);
        Constants.doubleClickOnElement(testFolderC);
        WaitTypes.waitForElementVisibilityByWebElement(qaTestTable);
    }

    public void verifyTestCompleteSubjectTableIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(completeSubjectTable);
    }

    public void verifyCheckAndCancelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(newItemCheckButton);
        WaitTypes.waitForElementVisibilityByWebElement(newItemClearButton);
    }

    public void verifyExportPageLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(exportPageLabel);
    }

    public void verifyCopyCompleteSubjectTableCopyIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(completeSubjectTableCopy);
    }

    public void verifyCompleteSubjectTableCopyIsDisplayed() {
        //WaitTypes.waitForElementVisibilityByWebElement(copyCompleteSubjectTable);
        WaitTypes.flientWaitFindElement(driver, By.xpath("//*[text()='Complete Subject Table COPY']"), 2000 );
    }

    public void verifyFailedAssessment1CopyIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1LinkCopy);
    }

    public void verifyAssessmentCopyIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1CopyLink);
    }

    public void verifyYourTableHasBeenCreatedMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(yourTableHasBeenCreatedMessage);
        Thread.sleep(2000);
    }

    public void verifyYourFolderHasBeenCreatedMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(yourFolderHasBeenCreatedMessage);
    }

    public void verifyIntercomDialogBoxIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(intercomTextBox);
        WaitTypes.waitForElementVisibilityByWebElement(intercomConversationDialogBox);
    }

    public void verifyRunModelLinkIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(runModelLink);
    }

    public void verifyNewTableLinkIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(newTableLink);
    }

    public void verifyNewFolderLinkIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(newFolderLink);
    }

    public void verifyUploadTableLinkIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(uploadTableLink);
    }

    public void verifyDefaultFileNameIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(defaultFileName);
    }
    // delete
   /* public void verifyQATestTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(qaTestTableLink);
    }*/
    // delete
    public void verifyDiaryTestTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(diaryTestTableLink);
    }
    // delete
    public void verifyNutrientTestTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(NutrientTestTableLink);
    }
    // delete
    public void verifyRFTrainingTestTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(RFTrainingTestTableLink);
    }
    // delete
    public void verifyRFTestTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(RFTestTestTableLink);
    }
    // delete
    public void verifyGRFTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(GRFTestTableLink);
    }
    // delete
    public void verifySugarConsumptionTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(sugarConTestTableLink);
    }
    // delete
    public void verifyMortalityRateTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(mortalityRateTestTableLink);
    }
    // delete
    public void verifyBacchusDataTableIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(bacchusDataTestTableLink);
    }

    

    public void verifyDefaultFolderNameIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(defaultFolderNameLink);
    }

    public void verifyDownloadTheZipFileButtonIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(downloadTheZipFileButton);
    }

    public void verifyFailedAssessment1IsDisplayedInMyDataPage() {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1Link);
    }

    public void verifyCompletedAssessmentIsDisplayedInMyDataPage() {
        WaitTypes.waitForElementVisibilityByWebElement(completedAssessmentLink);
    }

    public void verifyFailedAssessment2IsDisplayedInMyDataPage() {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment2Link);
    }
    // delete after FS refactoring
    public void verifyFailedAssessment1IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1Link);
    }
 // delete after FS refactoring
    public void verifyFailedAssessment2IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment2Link);
    }

    public void verifyCompletedAssessmentIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(completedAssessmentLink);
    }

    public void verifyTestFolderATextBoxIsStillDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(blankTestFolderATextBox);
    }

    public void verifyFileNameAlreadyExistsIsStillDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(fileAlreadyExistsErrorMessage);
    }

    public void verifySortLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortLabel);
    }

    public void verifySortByNameIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortLabelByName);
    }

    public void verifySortByNameXIconIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortByNameXIcon);
    }

    public void verifySortByModifiedIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortLabelByModified);
    }

    public void verifySortByCreatedIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortLabelByCreate);
    }

    public void verifySortByCreatedXIconIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortByCreateXIcon);
    }

    public void verifySortByTypeIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortLabelByType);
    }

    public void verifySortByOwnerIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(sortLabelByOwner);
    }

    public void verifySortByTypeFolderLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsFolderType);
    }

    public void verifySortByTypeJobLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsJobType);
    }

    public void verifySortByTypeTableLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsTableType);
    }

    public void verifyStartedToCopy1FileIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(startedToCopy1FileMessage);
    }








    public void openFolderA() throws InterruptedException {
    	Thread.sleep(1000);
    	WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
        Thread.sleep(10000);
        Constants.doubleClickOnElement(testFolderA);

    }
    
    public void openFolderAInTestFolderC() throws InterruptedException {
    	Thread.sleep(1000);
    	WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
        Thread.sleep(10000);
        Constants.doubleClickOnElement(testFolderA);

    }
    
    public void openFolderAInTrash() throws InterruptedException {
    	
    	Thread.sleep(1000);
    	WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
        Thread.sleep(10000);
        Constants.doubleClickOnElement(testFolderA);

    }

    public void openFolderAA() throws Exception {
    	//Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAA);
        Thread.sleep(7000);
        Constants.doubleClickOnElement(testFolderAA);
    }

    public void openFolderAA1() throws Exception {
    	//Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAA1);
        Thread.sleep(7000);
        Constants.doubleClickOnElement(testFolderAA1);
    }

    public void openFolderAAppend1() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAAppend1);
        Thread.sleep(7000);
        Constants.doubleClickOnElement(testFolderAAppend1);
    }

    public void openFolderAAppend2() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAAppend2);
        Thread.sleep(7000);
        Constants.doubleClickOnElement(testFolderAAppend2);
    }

    public void openFolderACopy() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(copyTestFolderA);
        Thread.sleep(7000);
        Constants.doubleClickOnElement(copyTestFolderA);
    }

    public void openFolderB() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testFolderB);
        Thread.sleep(10000);
        Constants.doubleClickOnElement(testFolderB);
    }

    public void openFolderC() throws Exception {
    	
    	Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderC);
        Thread.sleep(10000);
        Constants.doubleClickOnElement(testFolderC);

    }

    public void openFolderD() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testFolderD);
        Constants.doubleClickOnElement(testFolderD);
    }

    public void openDataSetNutrientTable() throws Exception {

        Thread.sleep(5000);
        WaitTypes.waitForElementVisibilityByWebElement(datasetNutrientTable);
        Thread.sleep(5000);
        Constants.doubleClickOnElement(datasetNutrientTable);

    }

    public void rightClickTestFolderA() throws Exception {
    	Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
        Constants.rightClick(testFolderA);
    }

    public void rightClickTestFolderB() {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderB);
        Constants.rightClick(testFolderB);
    }

    public void rightClickCompleteDiaryTable() {
        WaitTypes.waitForElementVisibilityByWebElement(completeDiaryTable);
        Constants.rightClick(completeDiaryTable);
    }


    public void rightClickCompleteSubjectTable() {
        WaitTypes.waitForElementVisibilityByWebElement(completeSubjectTable);
        Constants.rightClick(completeSubjectTable);
    }

    public void rightClickDatasetNutrientTable() {
        WaitTypes.waitForElementVisibilityByWebElement(datasetNutrientTable);
        Constants.rightClick(datasetNutrientTable);
    }

    public void rightClickFailedAssessment1() {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1Link);
        Constants.rightClick(failedAssessment1Link);
    }

    public void rightClickFailedAssessment2() {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment2Link);
        Constants.rightClick(failedAssessment2Link);
    }

    public void rightClickCompletedAssessment() {
        WaitTypes.waitForElementVisibilityByWebElement(completedAssessmentLink);
        Constants.rightClick(completedAssessmentLink);
    }

    public void rightClickCopiedFolderAAndTrash() throws InterruptedException {
        Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(copyTestFolderA);
        Constants.rightClickAndSelectElement(copyTestFolderA, rightClickTrashMenu);
        Thread.sleep(1000);
    }

    public void rightClickCopiedFolderBAndTrash() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerCopyTestFolderB);
        Constants.rightClickAndSelectElement(hamburgerCopyTestFolderB, rightClickTrashMenu);

        Thread.sleep(2000);
    }

    public void rightClickCopiedDiaryTableAndTrash() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerCopyDiaryTable);
        Constants.rightClickAndSelectElement(hamburgerCopyDiaryTable, rightClickTrashMenu);

        Thread.sleep(2000);
    }

    public void rightClickCopiedCompleteSubjectTableAndTrash() {
        WaitTypes.waitForElementVisibilityByWebElement(completeSubjectTableCopy);
        Constants.rightClickAndSelectElement(completeSubjectTableCopy, rightClickTrashMenu);
    }

    public void rightClickCopiedFailedAssessment1AndTrash() {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1CopyLink);
        Constants.rightClickAndSelectElement(failedAssessment1CopyLink, rightClickTrashMenu);
    }

    public void rightClickCopiedTestFolderAAndTrash() throws Exception {
    	Thread.sleep(3000);
        WaitTypes.waitForElementVisibilityByWebElement(copyTestFolderA);
        Constants.rightClickAndSelectElement(copyTestFolderA, rightClickTrashMenu);
    }

    public void rightClickCopiedCompleteSubjectTableAndTrash1() {
        WaitTypes.waitForElementVisibilityByWebElement(copyCompleteSubjectTable);
        Constants.rightClickAndSelectElement(copyCompleteSubjectTable, rightClickTrashMenu);
    }

    public void clickOnTestFolderAPencilIcon() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
        Util.Constants.mouseHoover(testFolderA);
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAPencilEditLink);
        WaitTypes.clickWhenReadyByElement(testFolderAPencilEditLink);

    }

    public void clickOnFailedAssessment1PencilIcon() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1Link);
        Util.Constants.mouseHoover(failedAssessment1Link);
        WaitTypes.waitForElementVisibilityByWebElement(testAssessmentPencilEditLink);
        WaitTypes.clickWhenReadyByElement(testAssessmentPencilEditLink);

    }

    public void clickOnCompletedSubjectTablePencilIcon() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(completeSubjectTable);
        Util.Constants.mouseHoover(completeSubjectTable);
        WaitTypes.waitForElementVisibilityByWebElement(testSubjectTablePencilEditLink);
        WaitTypes.clickWhenReadyByElement(testSubjectTablePencilEditLink);

    }

    public void clickOnTextFolderATextBoxAndClearName() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testItemTextBox);
        testItemTextBox.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT);
        testItemTextBox.clear();
    }

    public void clickOnCompletedSubjectTableTextBoxAndClearName() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testItemTextBox);
        testItemTextBox.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT);
        testItemTextBox.clear();
    }

    public void clickOnFailedAssessment1TextBoxAndClearName() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testItemTextBox);
        testItemTextBox.sendKeys(Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT);
        testItemTextBox.clear();
    }







    public void clickOnCopyFromRightClickMenu() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickCopyMenu);
        WaitTypes.clickWhenReadyByElement(rightClickCopyMenu);
    }

    public void clickOnCopyFromHamburgerItemMenu() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownCopyOption);
        WaitTypes.clickWhenReadyByElement(hamburgerDropDownCopyOption);
    }

    public void clickOnCopyToFromHamburgerMenu() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownCopyToOption);
        WaitTypes.clickWhenReadyByElement(hamburgerDropDownCopyToOption);
    }

    public void clickOnMoveFromHamburgerMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownMoveOption);
        WaitTypes.clickWhenReadyByElement(hamburgerDropDownMoveOption);
    }

    public void clickOnTrashFromHamburgerMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownTrashOption);
        WaitTypes.clickWhenReadyByElement(hamburgerDropDownTrashOption);
    }

    public void clickOnMoreDetailsFromHamburgerFolderMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownMoreDetailsOption);
        WaitTypes.clickWhenReadyByElement(hamburgerDropDownMoreDetailsOption);
    }

    public void clickOnDownloadFromHamburgerMenu() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownDownloadOption);
        WaitTypes.clickWhenReadyByElement(hamburgerDropDownDownloadOption);
    }

    public void clickOnCopyToFromRightClickMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(rightClickCopyToMenu);
        WaitTypes.clickWhenReadyByElement(rightClickCopyToMenu);
    }

    public void clickOnMoveFromRightClickMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(rightClickMoveMenu);
        WaitTypes.clickWhenReadyByElement(rightClickMoveMenu);
    }

    public void clickOnTrashFromRightClickMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(rightClickTrashMenu);
        WaitTypes.clickWhenReadyByElement(rightClickTrashMenu);
    }

    public void clickOnDownloadFromRightClickMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(rightClickDownloadMenu);
        WaitTypes.clickWhenReadyByElement(rightClickDownloadMenu);
    }

    public void clickOnOpenInTabFromRightClickMenuVerifyAndCloseTab() throws InterruptedException {

        String originalHandle = driver.getWindowHandle();

        WaitTypes.waitForElementVisibilityByWebElement(rightClickOpenInTabMenu);
        WaitTypes.clickWhenReadyByElement(rightClickOpenInTabMenu);

        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                WaitTypes.clickWhenReadyByElement(testFolderA);
                driver.close();
            }
        }

        driver.switchTo().window(originalHandle);

    }

    public void clickOnOpenInTabFromRightClickMenuVerifyTableAndCloseTab() throws InterruptedException {

        String originalHandle = driver.getWindowHandle();

        WaitTypes.waitForElementVisibilityByWebElement(rightClickOpenInTabMenu);
        WaitTypes.clickWhenReadyByElement(rightClickOpenInTabMenu);

        for(String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                WaitTypes.waitForElementVisibilityByWebElement(validTableImportTableLabel);
                driver.close();
            }
        }

        driver.switchTo().window(originalHandle);

    }
    
    public void rightClickQATestTableAndTrashFile() {
        WaitTypes.waitForElementVisibilityByWebElement(qaTestTable);
        Constants.rightClickAndSelectElement(qaTestTable, rightClickDropDownTrash);
    }

    public void clickOnTestFolderBHamburgerMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(testFolderB);
        Constants.mouseHoover(testFolderB);
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerMenuTestFolderB);
        WaitTypes.clickWhenReadyByElement(hamburgerMenuTestFolderB);
    }

    public void clickOnTestFolderAHamburgerMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
        Constants.mouseHoover(testFolderA);
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerMenuTestFolderA);
        WaitTypes.clickWhenReadyByElement(hamburgerMenuTestFolderA);
    }

    public void clickOnQATableHamburgerMenu() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(qaTestTable);
        Constants.mouseHoover(qaTestTable);
        Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerMenuQATable);
        WaitTypes.clickWhenReadyByElement(hamburgerMenuQATable);
    }

    public void clickOnFailedAssessmentHamburgerMenu() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1Link);
        Constants.mouseHoover(failedAssessment1Link);
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerMenuFailedAssessment1);
        WaitTypes.clickWhenReadyByElement(hamburgerMenuFailedAssessment1);
    }

    public void clickNewItemCheckButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(newItemCheckButton);
    }

    public void clickNewItemClearButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(newItemClearButton);
    }

    public void clickIntercomIconLink() throws InterruptedException {
    	Thread.sleep(2000);
    	driver.switchTo().frame(0);
    	Thread.sleep(2000);
    	//WaitTypes.waitUntilElementIsClickable(intercomIconLink);
    	WaitTypes.clickWhenReadyByElement(intercomIconLink);
        //WaitTypes.waitForElementVisibilityByWebElement(intercomIconLink);
        //WaitTypes.clickWhenReadyByElement(intercomIconLink);
    }

    // use the filePage (to be renamed leftMenu) reference
    public void clickSharedDataLink() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(sharedDataLink);
        WaitTypes.clickWhenReadyByElement(sharedDataLink);
    }
    // use the filePage (to be renamed leftMenu) reference
    public void clickRecentLink() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(sideMenuBarRecentLink);
        WaitTypes.clickWhenReadyByElement(sideMenuBarRecentLink);
    }

    public void clickFailedAssessment1() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment1Link);
        WaitTypes.clickWhenReadyByElement(failedAssessment1Link);
    }

    public void clickFailedAssessment2() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(failedAssessment2Link);
        WaitTypes.clickWhenReadyByElement(failedAssessment2Link);
    }

    public void clickCompletedAssessment() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(completedAssessmentLink);
        WaitTypes.clickWhenReadyByElement(completedAssessmentLink);
    }

    public void clickDraftAssessment() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(draftAssessmentLink);
        WaitTypes.clickWhenReadyByElement(draftAssessmentLink);
    }

    public void clickBatchMoveButton() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(batchMoveButton);
        WaitTypes.clickWhenReadyByElement(batchMoveButton);
    }

    public void clickBatchCopyButton() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(batchCopyButton);
        WaitTypes.clickWhenReadyByElement(batchCopyButton);
    }

    public void clickBatchCopyToButton() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(batchCopyToButton);
        WaitTypes.clickWhenReadyByElement(batchCopyToButton);
    }

    public void clickBatchTrashButton() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(batchTrashButton);
        WaitTypes.clickWhenReadyByElement(batchTrashButton);
    }

    public void clickOnCompleteSubjectTable() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(completeSubjectTable);
    }

    public void clickTestFolderA() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testFolderA);
    }

    public void clickOnTestFolderA() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(testFolderA);
    }

    public void clickOnTestFolderAAppended1() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testFolderAAppend1);
    }

    public void clickBatchRestoreToButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(batchRestoreToButton);
    }

    public void clickBatchPermanentlyDeleteButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(batchPermanentlyDeleteButton);
    }

    public void closeIntercomDialogBox() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(intercomConversationDialogBoxCloseButton);
        WaitTypes.clickWhenReadyByElement(intercomConversationDialogBoxCloseButton);
    }

    public void clickUndoLink() throws InterruptedException {
        Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(undoLink);
        WaitTypes.clickWhenReadyByElement(undoLink);
    }

    public void clickTestFolderAACookieTrailLink() throws InterruptedException {
        Thread.sleep(7000);
        WaitTypes.clickWhenReadyByElement(testFolderAACookie);
    }

    public void clickTestFolderCCookieTrailLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(testFolderCCookie);
    }

    public void clickSortLabelLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(sortLabel);
    }

    public void clickSortLabelCancelLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(sortByCancelIcon);
    }

    public void clickSortLabelByNameLink() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(sortLabel);
        WaitTypes.clickWhenReadyByElement(sortLabelByName);
    }

    public void clickSortLabelByCreatedLink() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(sortLabel);
        WaitTypes.clickWhenReadyByElement(sortLabelByCreate);
    }

    public void clickSortLabelByModifiedLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(sortLabel);
        WaitTypes.clickWhenReadyByElement(sortLabelByModified);
    }

    public void clickSortLabelByOwnerLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(sortLabel);
        WaitTypes.clickWhenReadyByElement(sortLabelByOwner);
    }

    public void clickSortLabelByTypeLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(sortLabel);
        WaitTypes.clickWhenReadyByElement(sortLabelByType);
    }







    public void verifyHamburgerCopyMenuForTestFolderBIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownCopyOption);
    }

    public void verifyHamburgerCopyToMenuForTestFolderBIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownCopyToOption);
    }

    public void verifyHamburgerMoveMenuForTestFolderBIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownMoveOption);
    }

    public void verifyHamburgerTrashMenuForTestFolderBIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownTrashOption);
    }

    public void verifyHamburgerDownloadMenuForTestFolderBIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownDownloadOption);
    }

    public void verifyHamburgerMoreDetailsMenuForTestFolderBIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerDropDownMoreDetailsOption);
    }

    public void verifyTestFolderABDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAB);
    }

    public void verifyTestFolderAADisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAA);
    }

    public void verifyCookieTrailTestFolderAADisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAACookie);
    }

    public void verifyUserOnTestFolderAAPage() {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderAAPage);
    }

    public void verifyTestFolderADisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(testFolderA);
    }

    public void verifyNutrientTableIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(datasetNutrientTable);
    }


    public void moveTestFolderOrTableToMyData() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(filePickerMyDataBackArrowButton);
        Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(myDataFolderSelect);
        WaitTypes.clickWhenReadyByElement(myDataFolderSelect);
        WaitTypes.waitForElementVisibilityByWebElement(filePickerMenuCopyToOrMoveOrRestoreButton);
        WaitTypes.clickWhenReadyByElement(filePickerMenuCopyToOrMoveOrRestoreButton);
    }

    public void moveTestFolderAToMyDataFolder() throws InterruptedException {
        Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(myDataFolderSelect);
        WaitTypes.clickWhenReadyByElement(myDataFolderSelect);
        WaitTypes.waitForElementVisibilityByWebElement(filePickerMenuCopyToOrMoveOrRestoreButton);
        WaitTypes.clickWhenReadyByElement(filePickerMenuCopyToOrMoveOrRestoreButton);
    }


    public void verifyTrashButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchTrashButton);
    }

    public void verifyMoveButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchMoveButton);
    }

    public void verifyMoveButtonIsNotDisplayed() {

        Boolean isNOTPresent = driver.findElements(By.xpath("//button[@aria-label='Move']")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }

    public void verifyCopyToButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchCopyToButton);
    }

    public void verifyCopyToButtonIsNotDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[@id='fs-batch-actions']/button[2]")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }

    public void verifyCopyButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchCopyButton);
    }

    public void verifyCopyButtonIsNotDisplayed() {

        Boolean isNOTPresent = driver.findElements(By.xpath("//button[@aria-label='Copy']")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }


    public void enterNewFolderName(String s) throws InterruptedException {
        Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(blankTestFolderATextBox);
        blankTestFolderATextBox.sendKeys(s);
    }

    public void verifyNewFolderNameIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(newFolderNameLink);

    }

    public void verifyNewTableNameIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(newTableNameLink);
    }

    public void verifyCookieTrailIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(cookieLink);
    }

    public void verifyNewAssessmentNameIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(newAssessmentNameLink);
    }

    public void verifyTrashButtonIsNotDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//button[@aria-label='Trash']")).size() < 1;
        Assert.assertTrue(isNOTPresent);

    }

    public void verifyCookieTrailIsNotDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[@ng-if='!fs.fileSystem.currentFile.isRoot()']")).size() < 1;
        Assert.assertTrue(isNOTPresent);

    }

    public void verifyFailedAssessment1IsNotDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//p[text()='Failed Assessment1']")).size() < 1;
        Assert.assertTrue(isNOTPresent);

    }

    public void verifyTestFolderAIsNotDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[text()='Test Folder A']")).size() < 1;
        Assert.assertTrue(isNOTPresent);

    }

    public void verifyCompleteSubjectTableIsNotDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[text()='Complete Subject Table']")).size() < 1;
        Assert.assertTrue(isNOTPresent);

    }




    public void getItemsOrder() throws InterruptedException {


        List<WebElement> tableRowCollection = myDataTable;
        List<String> defaultOrder = new LinkedList<String>();
        for (WebElement e : tableRowCollection) {

            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Test Folder C']"));
            defaultOrder.add(e.findElement(By.xpath("//p[text()='Test Folder C']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Test Folder A']"));
            defaultOrder.add(e.findElement(By.xpath("//p[text()='Test Folder A']")).getText());
            defaultOrder.add(e.findElement(By.xpath("//p[text()='Failed Assessment1']")).getText());
            defaultOrder.add(e.findElement(By.xpath("//p[text()='Complete Food Table']")).getText());
            defaultOrder.add(e.findElement(By.xpath("//p[text()='Complete Diary Table']")).getText());
            defaultOrder.add(e.findElement(By.xpath("//p[text()='Complete Subject Table']")).getText());

            defaultOrder.add(e.findElement(By.className("fs-table-file-name")).getText());

            //System.out.println(defaultOrder);
        }
    }

    public void verifyItemListSortedByName() throws InterruptedException {


        List<WebElement> tableRowCollection = myDataTable;
        List<String> listOrderByName = new LinkedList<String>();
        for (WebElement e : tableRowCollection) {


            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Folder Name']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='New Folder Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Test Folder C']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='Test Folder C']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Complete Diary Table']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='Complete Diary Table']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Assessment Name']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='New Assessment Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Table Name']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='New Table Name']")).getText());


            //System.out.println(listOrderByName);


            List<WebElement> itemsTable = (List<WebElement>) driver.findElements(By.cssSelector(".fs-table-file-name.no-outline.flex"));

            List<String> itemsName = new ArrayList<>();

            for (WebElement allItemsName : itemsTable) {
                itemsName.add(allItemsName.getText());
            }

            Assert.assertEquals(listOrderByName, itemsName);

        }
    }

    public void verifyItemListSortedByCreated() throws InterruptedException {


        List<WebElement> tableRowCollection = myDataTable;
        List<String> listOrderByCreated = new LinkedList<String>();
        for (WebElement e : tableRowCollection) {


            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Test Folder C']"));
            listOrderByCreated.add(e.findElement(By.xpath("//p[text()='Test Folder C']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Folder Name']"));
            listOrderByCreated.add(e.findElement(By.xpath("//p[text()='New Folder Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Assessment Name']"));
            listOrderByCreated.add(e.findElement(By.xpath("//p[text()='New Assessment Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Complete Diary Table']"));
            listOrderByCreated.add(e.findElement(By.xpath("//p[text()='Complete Diary Table']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Table Name']"));
            listOrderByCreated.add(e.findElement(By.xpath("//p[text()='New Table Name']")).getText());



            //System.out.println(defaultOrder);


            List<WebElement> itemsTable = (List<WebElement>) driver.findElements(By.cssSelector(".fs-table-file-name.no-outline.flex"));

            List<String> itemsName = new ArrayList<>();

            for (WebElement allItemsName : itemsTable) {
                itemsName.add(allItemsName.getText());
            }

            Assert.assertEquals(listOrderByCreated, itemsName);

        }
    }

    public void verifyItemListSortedByType() throws InterruptedException {


        List<WebElement> tableRowCollection = myDataTable;
        List<String> listOrderByType = new LinkedList<String>();
        for (WebElement e : tableRowCollection) {


            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Folder Name']"));
            listOrderByType.add(e.findElement(By.xpath("//p[text()='New Folder Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Test Folder C']"));
            listOrderByType.add(e.findElement(By.xpath("//p[text()='Test Folder C']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Complete Diary Table']"));
            listOrderByType.add(e.findElement(By.xpath("//p[text()='Complete Diary Table']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Table Name']"));
            listOrderByType.add(e.findElement(By.xpath("//p[text()='New Table Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Assessment Name']"));
            listOrderByType.add(e.findElement(By.xpath("//p[text()='New Assessment Name']")).getText());



            //System.out.println(defaultOrder);


            List<WebElement> itemsTable = (List<WebElement>) driver.findElements(By.cssSelector(".fs-table-file-name.no-outline.flex"));

            List<String> itemsName = new ArrayList<>();

            for (WebElement allItemsName : itemsTable) {
                itemsName.add(allItemsName.getText());
            }
            //System.out.println(itemsName);

            Assert.assertEquals(listOrderByType, itemsName);


        }
    }




    public void verifyItemListSortedByOwner() throws InterruptedException {


        List<WebElement> tableRowCollection = myDataTable;
        List<String> listOrderByOwner = new LinkedList<String>();
        for (WebElement e : tableRowCollection) {


            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Test Folder C']"));
            listOrderByOwner.add(e.findElement(By.xpath("//p[text()='Test Folder C']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Folder Name']"));
            listOrderByOwner.add(e.findElement(By.xpath("//p[text()='New Folder Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Complete Diary Table']"));
            listOrderByOwner.add(e.findElement(By.xpath("//p[text()='Complete Diary Table']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Assessment Name']"));
            listOrderByOwner.add(e.findElement(By.xpath("//p[text()='New Assessment Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Table Name']"));
            listOrderByOwner.add(e.findElement(By.xpath("//p[text()='New Table Name']")).getText());



            //System.out.println(defaultOrder);


            List<WebElement> itemsTable = (List<WebElement>) driver.findElements(By.cssSelector(".fs-table-file-name.no-outline.flex"));

            List<String> itemsName = new ArrayList<>();

            for (WebElement allItemsName : itemsTable) {
                itemsName.add(allItemsName.getText());
            }


            Assert.assertEquals(listOrderByOwner, itemsName);

        }
    }


    public void verifyItemListReturnsToDefaultOrderAfterCancellingSortByName() throws Exception {

        List<WebElement> tableRowCollection = myDataTable;
        List<String> listOrderByName = new LinkedList<>();
        for (WebElement e : tableRowCollection) {


            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Folder Name']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='New Folder Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Test Folder C']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='Test Folder C']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Table Name']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='New Table Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='New Assessment Name']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='New Assessment Name']")).getText());
            Thread.sleep(2000);
            WaitTypes.getWhenVisible(By.xpath("//p[text()='Complete Diary Table']"));
            listOrderByName.add(e.findElement(By.xpath("//p[text()='Complete Diary Table']")).getText());

            //System.out.println(defaultOrder);


            List<WebElement> itemsTable = driver.findElements(By.cssSelector(".fs-table-file-name.no-outline.flex"));

            List<String> itemsName = new ArrayList<>();

            for (WebElement allItemsName : itemsTable) {
                itemsName.add(allItemsName.getText());
            }

            Assert.assertEquals(listOrderByName, itemsName);

        }

    }

    public void createAndAccessSystemSubFolder() throws Exception{
        navigateToNewFolderLink();
        enterItemNameIntoNewItemTextBox("QA Sub Folder");
        clickNewItemCheckButton();
        verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        WaitTypes.waitUntilElementIsClickable(qaSubFolder);
        Constants.doubleClickOnElement(qaSubFolder);
        WaitTypes.waitForElementVisibilityByWebElement(qaSubFolderTitle);
    }
    
    public void accessEMDataQASubFolder(){
    	 WaitTypes.waitUntilElementIsClickable(emDataQAFolder);
         Constants.doubleClickOnElement(emDataQAFolder);
         WaitTypes.waitForElementVisibilityByWebElement(emDataQAFolderTitle);
    }
    
    public void createDataItems(String folderName, String tableName, String axName) throws Exception{
        navigateToNewFolderLink();
        enterItemNameIntoNewItemTextBox(folderName);
        clickNewItemCheckButton();
        verifyYourFolderHasBeenCreatedMessageIsDisplayed();
        createQACategoryTable();
        enterItemNameIntoNewItemTextBox(tableName);
        clickNewItemCheckButton();
        verifyYourTableHasBeenCreatedMessageIsDisplayed();
        createNewDraftQAAx(axName);        
    }
   
    
    public void getDefaultListOfItemsOnMyDataPage() {
        List<WebElement> itemsTable = (List<WebElement>) driver.findElements(By.cssSelector(".fs-table-file-name.no-outline.flex"));

        List<String> itemsName = new ArrayList<>();

        for (WebElement allItemsName : itemsTable) {
            itemsName.add(allItemsName.getText());
        }

        //System.out.println(itemsName);
    }
    
    
    public void verifyMyEMDataQAFolderIsReadOnly(){
    	WaitTypes.waitUntilElementIsClickable(emDataQAFolder);
    	Assert.assertEquals(emDataQAFolder.getAttribute("px-draggable-disabled"), "true");
    }
    
    
    public void verifyLocationIsEditable() throws Exception{
    	WaitTypes.waitUntilElementIsClickable(newPlusLink);
    	Thread.sleep(1000);
    	Assert.assertEquals(newPlusLink.isDisplayed(), true);
    	Assert.assertEquals(newPlusLink.isEnabled(), true);
    }
   


}
