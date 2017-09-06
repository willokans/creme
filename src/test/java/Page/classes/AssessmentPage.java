package Page.classes;

import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by will.okamuneh on 8/22/2016.
 */
public class AssessmentPage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();



    @FindBy(id = "fs-file-creator-launcher")
    WebElement newPlusLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/md-menu/button[@id='fs-run-model-btn']")
    WebElement runModelLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item/button[@id='fs-new-model-cremefood']")
    WebElement cremeFoodAssessment;

    @FindBy(xpath = "//input[@class='ng-pristine ng-untouched ng-valid md-input ng-not-empty']")
    WebElement assessmentNameTextBox;

    @FindBy(xpath = "//input[@class='ng-valid md-input ng-empty ng-dirty ng-valid-parse ng-touched'][@aria-invalid='false']")
    WebElement assessmentNameTextBoxEmpty;

    @FindBy(xpath = "//button[@aria-label='Confirm']")
    WebElement assessmentConfirmButton;

    @FindBy(xpath = "//button[@aria-label='Cancel']")
    WebElement assessmentCancelButton;

    @FindBy(xpath = "//form-builder/form/div[1]/field-builder/ng-include/md-input-container/div/div/button")
    WebElement foodAssessmentChooseFileButton;

    @FindBy(xpath = "//form-builder/form/div[2]/field-builder/ng-include/md-input-container/div/div/button")
    WebElement subjectAssessmentChooseFileButton;

    @FindBy(xpath = "//form-builder/form/div[3]/field-builder/ng-include/md-input-container/div/div/button")
    WebElement diaryAssessmentChooseFileButton;

    @FindBy(xpath = "//form-builder/form/div[4]/field-builder/ng-include/md-input-container/div/div/button")
    WebElement nutrientsAssessmentChooseFileButton;

    @FindBy(xpath = "//input[@class='ng-pristine ng-untouched md-input ng-empty ng-valid-min ng-valid-max ng-invalid ng-invalid-required']")
    WebElement numberOfDaysReported;

    @FindBy(xpath = "//input[@class='ng-pristine ng-untouched ng-valid md-input ng-not-empty ng-valid-min ng-valid-max ng-valid-required']")
    WebElement randomSeed;

    @FindBy(xpath = "//button[@class='md-accent md-raised cf-btn-m md-button md-em-bg-theme md-ink-ripple']/translate[text()='Run']")
    WebElement assessmentRunButton;

    @FindBy(xpath = "//button[@class='md-primary md-hue-3 md-raised cf-btn-m md-button md-em-bg-theme md-ink-ripple']/translate[text()='Save & Close']")
    WebElement assessmentSaveAndCloseButton;

    @FindBy(xpath = "//button[@class='md-primary md-hue-3 md-raised cf-icon-sq md-button md-em-bg-theme md-ink-ripple']/*[text()='delete']")
    WebElement assessmentDeleteButton;

    @FindBy (xpath = "//*[@id='filepicker']/div[2]/md-list/md-list-item[1]/button[1]")
    WebElement myDataFilePickerFolderSelect;

    @FindBy (xpath = "//*[@id='filepicker']/div[2]/md-list/md-list-item[4]/button")
    WebElement assessmentFilePickerTableLink;

    @FindBy (xpath = "//button[@class='md-primary md-raised cf-btn-s md-button ng-scope md-ink-ripple']/span[text()='Select']")
    WebElement filePickerSelectButton;

    @FindBy (xpath = ".//*[@id='filepicker']/div[1]/div/div[1]/button")
    WebElement filePickerBackArrowButton;

    @FindBy(xpath = "//*[text()='      Assessment started    ']")
    WebElement assessmentStartedMessage;

    @FindBy (xpath = "//*[@class='md-icon-button md-button md-em-bg-theme md-ink-ripple active']")
    WebElement importCompleteLink;

    @FindBy (xpath = "//*[@class='md-icon-button md-button md-em-bg-theme md-ink-ripple']")
    WebElement cancelImportFile;

    @FindBy (xpath = "//*[text()='Assessment Failed']")
    WebElement notificationAssessmentFail;




    public AssessmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }





    public void enterAssessmentNameIntoTextBox(String s) throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(assessmentNameTextBox);
        assessmentNameTextBox.sendKeys(s);
        Thread.sleep(1000);
    }

    public void enterNumberOfDaysReportedIntoTextBox(String s) throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(numberOfDaysReported);
        numberOfDaysReported.sendKeys(s);
        Thread.sleep(1000);
    }

    public void enterRandomSeedIntoTextBox(String s) throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(randomSeed);
        randomSeed.sendKeys(s);
        Thread.sleep(1000);
    }





    public void createCremeFoodAssessment() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newPlusLink);
        WaitTypes.clickWhenReadyByElement(newPlusLink);
        WaitTypes.waitForElementVisibilityByWebElement(runModelLink);
        WaitTypes.clickWhenReadyByElement(runModelLink);
        WaitTypes.waitForElementVisibilityByWebElement(cremeFoodAssessment);
        WaitTypes.clickWhenReadyByElement(cremeFoodAssessment);

    }





    public void clickAssessmentConfirmButton() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(assessmentConfirmButton);
        WaitTypes.clickWhenReadyByElement(assessmentConfirmButton);
    }

    public void clickAssessmentCancelButton() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(assessmentCancelButton);
        WaitTypes.clickWhenReadyByElement(assessmentCancelButton);
    }

    public void clickFoodAssessmentChooseFileButton() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(foodAssessmentChooseFileButton);
        WaitTypes.clickWhenReadyByElement(foodAssessmentChooseFileButton);
    }

    public void clickSubjectAssessmentChooseFileButton() throws InterruptedException {
        Constants.scrollToElement2(subjectAssessmentChooseFileButton);
        WaitTypes.clickWhenReadyByElement(subjectAssessmentChooseFileButton);

        //WaitTypes.waitForElementVisibilityByWebElement(subjectAssessmentChooseFileButton);
        //WaitTypes.clickWhenReadyByElement(subjectAssessmentChooseFileButton);
    }

    public void clickNutrientsAssessmentChooseFileButton() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(nutrientsAssessmentChooseFileButton);
        WaitTypes.clickWhenReadyByElement(nutrientsAssessmentChooseFileButton);
    }

    public void clickDiaryAssessmentChooseFileButton() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(diaryAssessmentChooseFileButton);
        WaitTypes.clickWhenReadyByElement(diaryAssessmentChooseFileButton);
    }

    public void clickMyDataFolderFromOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(myDataFilePickerFolderSelect);
    }

    public void clickBackArrowButtonOnFilePicker() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(filePickerBackArrowButton);
    }

    public void clickAndSelectTableFromOption() throws InterruptedException {
        Thread.sleep(3000);
        WaitTypes.clickWhenReadyByElement(assessmentFilePickerTableLink);
        WaitTypes.clickWhenReadyByElement(filePickerSelectButton);

    }

    public void clickAssessmentRunButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(assessmentRunButton);
    }





    public void verifyAssessmentStartedMessageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(assessmentStartedMessage);
    }

    public void verifyAssessmentFailedAndCancelNotification() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(importCompleteLink);
        WaitTypes.waitForElementVisibilityByWebElement(notificationAssessmentFail);
        WaitTypes.clickWhenReadyByElement(cancelImportFile);
    }

}
