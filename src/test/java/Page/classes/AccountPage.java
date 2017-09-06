package Page.classes;

import Util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Util.EMDriverSingleton;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by will.okanumeh on 6/24/2016.
 */
public class AccountPage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @FindBy(xpath = "//div[@class='topnav-container layout-row']//*[text()='Account']")
    WebElement accountLabel;

    @FindBy(id = "acc-firstname")
    WebElement firstNameTextBox;

    @FindBy(id = "acc-lastname")
    WebElement lastNameTextBox;

    @FindBy(id = "acc-email")
    WebElement emailLabel;

    @FindBy(id = "acc-save")
    WebElement saveChangeButton;

    @FindBy(xpath = "//*[@ng-click='$mdTabsCtrl.select(tab.getIndex())']//span[text()='Details']")
    WebElement detailsLabelLink;

    @FindBy(xpath = "//*[@ng-click='$mdTabsCtrl.select(tab.getIndex())']//span[text()='Change Password']")
    WebElement changePasswordLabelLink;
    
    @FindBy(xpath = "//*[@ng-click='$mdTabsCtrl.select(tab.getIndex())']//span[text()='License']")
    WebElement licenseTabLink;

    @FindBy (xpath = "//div[@class='md-toast-content']//*[text()='First name is required']")
    WebElement firstNameErrorMessage;

    @FindBy (xpath = "//*[@id='main-wrapper']/md-content/md-content//h2/translate[text()='Account']")
    WebElement accountDetailsPage;

    @FindBy (xpath = "//div[@class='md-toast-content']//*[text()='Last name is required']")
    WebElement lastNameErrorMessage;

    @FindBy (xpath = "html/body/md-toast/div/span")
    WebElement savePopMessage;

    @FindBy (id = "acc-old-password")
    WebElement oldAccountTextBox;

    @FindBy (id = "acc-reset-password-input")
    WebElement newPasswordTextBox;

    @FindBy (id = "acc-reset-password-again-input")
    WebElement newPasswordAgainTextBox;

    @FindBy (id = "acc-confirm")
    WebElement confirmButton;

    @FindBy (id = "acc-cancel")
    WebElement cancelButton;

    //@FindBy (xpath = ".//*[@id='acc-old-password-errors']/div/div/*[text()='Password is required.']")
    @FindBy (xpath = ".//*[@id='acc-old-password-errors']//div[@class='account-error-pass-required md-input-message-animation ng-scope']/translate")
    WebElement blankOldPasswordErrorMessage;

    //@FindBy (xpath = "//*[@id='acc-new-password-errors']/div/div/*[text()='Password is required.']")
    @FindBy (xpath = ".//*[@id='acc-new-password-errors']//div[@class='account-error-pass-required md-input-message-animation ng-scope']/translate")
    WebElement blankNewPasswordErrorMessage;

    //@FindBy (xpath = "//*[text()='Confirmation Password is required.']")
    @FindBy (xpath = ".//*[@id='acc-conf-password-errors']//div[@class='account-error-req-conf-pass md-input-message-animation ng-scope']/translate")
    WebElement blankNewPasswordAgainErrorMessage;

    @FindBy (xpath = "//*[@id='acc-new-password-errors']/div/div/*[text()='Password must be at least 8 characters long.']")
    WebElement lessThan8PasswordErrorMassagetErrorMessage;

    @FindBy (xpath = "//*[text()='The two passwords do not match.']")
    WebElement passwordDoesNotMatchErrorMassage;

    @FindBy (xpath = "//*[@id='acc-new-password-errors']/div/div/*[text()='Passwords do not match the multicase requirement.']")
    WebElement newPasswordNotMatchingMultiCaseErrorMassage;

    @FindBy (xpath = "//*[text()='Your current password is not correct']")
    WebElement currentPasswordNotCorrectErrorMassage;

    @FindBy (xpath = "//*[@id='acc-new-password-errors']/div/div/*[text()='Please use at least one of each of the following character types: uppercase, lowercase, numbers, special characters.']")
    WebElement wrongPasswordSyntax;

    @FindBy (xpath = "//div[@class=\'ng-scope flex\']")
    WebElement passwordStrength;

    @FindBy (xpath = "//div[@class='acc-info em-password-strength']")
    WebElement passwordStrengthInfo;

    @FindBy (xpath = "//*[text()='VERY WEAK']")
    WebElement veryWeakPasswordStrengthMessage;

    @FindBy (xpath = "//*[text()='WEAK']")
    WebElement weakPasswordStrengthMessage;

    @FindBy (xpath = "//*[text()='GOOD']")
    WebElement goodPasswordStrengthMessage;

    @FindBy (xpath = "//*[text()='STRONG']")
    WebElement strongPasswordStrengthMessage;

    @FindBy (xpath = "html/body/md-toast/div/span")
    WebElement userPasswordUpdatedMessage;





    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    
    public void verifyUserIsOnChangePasswordTab() {
        WaitTypes.waitForElementVisibilityByWebElement(passwordStrengthInfo);
        passwordStrengthInfo.isDisplayed();
        oldPasswordTextBoxIsDisplayed();
    }

    public void oldPasswordTextBoxIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(oldAccountTextBox);
        oldAccountTextBox.isDisplayed();
    }

    public void verifyAccountTileIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(accountDetailsPage);
        accountDetailsPage.isDisplayed();
    }

    public void verifyChangePasswordLinkIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(changePasswordLabelLink);
        changePasswordLabelLink.isDisplayed();
    }

    public void onAccountDetailPageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(accountDetailsPage);
        accountDetailsPage.isDisplayed();
    }

    public void saveButtonIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(saveChangeButton);
        saveChangeButton.isDisplayed();
    }

    public void changePasswordLinkIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(changePasswordLabelLink);
        changePasswordLabelLink.isDisplayed();
    }
    
    public void licenseTabLinkIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(licenseTabLink);
        licenseTabLink.isDisplayed();
    }

    public void verifyWrongPasswordSyntaxIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(wrongPasswordSyntax, "Please use at least one of each of the following character types: uppercase, lowercase, numbers, special characters.");
    }

    public void verifyNewPasswordNotMatchingMultiCaseErrorMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordNotMatchingMultiCaseErrorMassage);
        newPasswordNotMatchingMultiCaseErrorMassage.isDisplayed();
    }


    public void detailLinkIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(detailsLabelLink);
        detailsLabelLink.isDisplayed();
    }
    
    public void clickOnDetailsTabLink() throws Exception{
    	WaitTypes.clickWhenReadyByElement(detailsLabelLink);
    }
    
    public void verifyUserIsOnDetailsTab(){
    	WaitTypes.waitForElementVisibilityByWebElement(firstNameTextBox);
    	firstNameTextBox.isDisplayed();
    }


    public void veryWeakPasswordStrengthMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(veryWeakPasswordStrengthMessage);
        veryWeakPasswordStrengthMessage.isDisplayed();
    }

    public void weakPasswordStrengthMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(weakPasswordStrengthMessage);
        weakPasswordStrengthMessage.isDisplayed();
    }

    public void goodPasswordStrengthMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(goodPasswordStrengthMessage);
        goodPasswordStrengthMessage.isDisplayed();
    }

    public void strongPasswordStrengthMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(strongPasswordStrengthMessage);
        strongPasswordStrengthMessage.isDisplayed();
    }






    public void clickCancelButton() {
        WaitTypes.waitForElementVisibilityByWebElement(cancelButton);
        cancelButton.click();
    }

    public void clickChangePasswordLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
        if (newPasswordTextBox.isDisplayed()) {
            changePasswordLabelLink.isDisplayed();
            WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
            WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
            Thread.sleep(1000);
            WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
        }else {
            WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
            WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
            Thread.sleep(1000);
            WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
        }

    }

    public void clickSaveChangeButton() {
        WaitTypes.waitForElementVisibilityByWebElement(saveChangeButton);
        saveChangeButton.click();
    }

    public void clickConfirmButton() {
        WaitTypes.waitForElementVisibilityByWebElement(confirmButton);
        confirmButton.click();
    }

    public void clickNewPasswordTextbox() {
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordAgainTextBox);
        newPasswordAgainTextBox.click();
    }

    


    public void verifyCurrentPasswordFeildIsBlank() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(oldAccountTextBox);
        AssertType.assertTrueIsDisplayedByText(oldAccountTextBox, "");
    }

    public void verifyNewPasswordFeildIsBlank() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordTextBox);
        AssertType.assertTrueIsDisplayedByText(newPasswordTextBox, "");
    }

    public void verifyNewPasswordAgainFeildIsBlank() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordAgainTextBox);
        AssertType.assertTrueIsDisplayedByText(newPasswordAgainTextBox, "");
    }

    public void verifyConfirmButtonIsDisabled() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(confirmButton);
        confirmButton.getAttribute("disabled");
       
    }

    public void verifyCancelButtonIsEnabled() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(cancelButton);
        cancelButton.isEnabled();
    }

    public void verifyCurrentPasswordNotCorrectErrorMassageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(currentPasswordNotCorrectErrorMassage);
        currentPasswordNotCorrectErrorMassage.isDisplayed();
    }

    public void verifyLessThan8PasswordErrorMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(lessThan8PasswordErrorMassagetErrorMessage);
    }


    public void verifyPasswordDoesNotMatchErrorMassageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(passwordDoesNotMatchErrorMassage, "The two passwords do not match." );
    }

    public void verifySaveMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(savePopMessage);
        AssertType.assertTrueIsDisplayedByText(savePopMessage, "User details saved");
    }

    public void verifyUserPasswordIsUpdatedMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(userPasswordUpdatedMessage);
        AssertType.assertTrueIsDisplayedByText(userPasswordUpdatedMessage, "User password updated");
    }

    public void verifyFirstNameTextIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(firstNameTextBox);
        firstNameTextBox.isDisplayed();
    }

    public void verifyLastNameTextIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(lastNameTextBox);
        lastNameTextBox.isDisplayed();
    }

    public void verifyEmailAddIsDisplayedNotEditable() throws Exception {
        //WaitTypes.waitForElementVisibilityByWebElement(emailLabel);
    	WaitTypes.waitUntilElementIsClickable(emailLabel);
        AssertType.assertTrueIsDisplayedByText(emailLabel, "qa@cremelabs.com");
        Constants.readOnly(emailLabel);
    }


    public void verifyErrorMessageForNullCurrentPasswordTextBox() throws Exception {
    	Thread.sleep(1000);
        WaitTypes.waitForElementVisibilityByWebElement(blankOldPasswordErrorMessage);
        Assert.assertEquals(blankOldPasswordErrorMessage.isDisplayed(), true);
        AssertType.assertTrueIsDisplayedByText(blankOldPasswordErrorMessage, "Password is required.");
        
    }

    public void verifyErrorMessageForNullNewPasswordTextBox() throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(blankNewPasswordErrorMessage);
        Assert.assertEquals(blankNewPasswordErrorMessage.isDisplayed(), true);
        AssertType.assertTrueIsDisplayedByText(blankNewPasswordErrorMessage, "Password is required.");
    }

    public void verifyErrorMessageForNullNewPasswordAgainTextBox() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(blankNewPasswordAgainErrorMessage);
        Assert.assertEquals(blankNewPasswordAgainErrorMessage.isDisplayed(), true);
        AssertType.assertTrueIsDisplayedByText(blankNewPasswordAgainErrorMessage, "Confirmation Password is required.");
    }
    
    public void verifyUserIsOnAccountPage() throws Exception{
    	verifyAccountTileIsDisplayed();
    	verifyFirstNameTextIsDisplayed();
    }
    
    public void navigateToLicensePage(){
    	WaitTypes.waitForElementVisibilityByWebElement(licenseTabLink);
    	licenseTabLink.click();
    	// add confirmation options when page has been developed
    }






    public void clearCurrentPasswordTextBox() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(oldAccountTextBox);
        oldAccountTextBox.clear();
    	WaitTypes.clickWhenReadyByElement(oldAccountTextBox);
        WaitTypes.clickWhenReadyByElement(newPasswordTextBox);
        WaitTypes.clickWhenReadyByElement(newPasswordAgainTextBox);
        WaitTypes.clickWhenReadyByElement(oldAccountTextBox);
        
        
    }

    public void clearNewPasswordTextBox() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordTextBox);
        newPasswordTextBox.clear();
        WaitTypes.clickWhenReadyByElement(newPasswordTextBox);
        WaitTypes.clickWhenReadyByElement(oldAccountTextBox);
        WaitTypes.clickWhenReadyByElement(newPasswordAgainTextBox);
        WaitTypes.clickWhenReadyByElement(newPasswordTextBox);
           
    }

    public void clearNewPasswordAgainTextBox() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordAgainTextBox);
        newPasswordAgainTextBox.clear();
        WaitTypes.clickWhenReadyByElement(newPasswordAgainTextBox);
        WaitTypes.clickWhenReadyByElement(oldAccountTextBox);
        WaitTypes.clickWhenReadyByElement(newPasswordTextBox);
        WaitTypes.clickWhenReadyByElement(newPasswordAgainTextBox);
    }

    public void refreshPageAndClickChangePasswordTab() throws InterruptedException {

        Constants.refreshPage();
        WaitTypes.clickWhenReadyByElement(changePasswordLabelLink);
        WaitTypes.waitForElementVisibilityByWebElement(changePasswordLabelLink);
    }


    public void clearFirstNameTextBox() {
        WaitTypes.waitForElementVisibilityByWebElement(firstNameTextBox);
        firstNameTextBox.clear();
    }

    public void clearLastNameTextBox() {
        WaitTypes.waitForElementVisibilityByWebElement(lastNameTextBox);
        lastNameTextBox.clear();
    }

    public void clearFirstAndLastNameAndAttemptToSaveError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(firstNameTextBox);
        firstNameTextBox.clear();
        WaitTypes.waitForElementVisibilityByWebElement(lastNameTextBox);
        lastNameTextBox.clear();
        saveChangeButton.click();
    }


    public void verifyDisplayOfErrorMessageAfterClearingFirstAndLastNameAndAttemptingToSave() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(firstNameErrorMessage);
        AssertType.assertTrueIsDisplayedByText(firstNameErrorMessage, "First name is required");
    }

    public void clearFirstNameAndAttemptToSaveError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(firstNameTextBox);
        firstNameTextBox.clear();
        WaitTypes.clickWhenReadyByElement(saveChangeButton);
    }


    public void verifyDisplayOfErrorMessageAfterClearingFirstNameAndAttemptingToSave() throws Exception {

        WaitTypes.waitForElementVisibilityByWebElement(firstNameErrorMessage);
        AssertType.assertTrueIsDisplayedByText(firstNameErrorMessage, "First name is required");

    }


    public void clearLastNameAndAttemptToSaveError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(lastNameTextBox);
        lastNameTextBox.clear();
        WaitTypes.clickWhenReadyByElement(saveChangeButton);
    }


    public void verifyDisplayOfErrorMessageAfterClearingLastNameAndAttemptingToSave() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(lastNameErrorMessage);
        AssertType.assertTrueIsDisplayedByText(lastNameErrorMessage, "Last name is required");
    }






    public void enterTextToFirstNameTextBox(String element) {
        WaitTypes.waitForElementVisibilityByWebElement(firstNameTextBox);
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(element);
    }

    public void enterTextToCurrentPasswordTextBox(String element) {
        WaitTypes.waitForElementVisibilityByWebElement(oldAccountTextBox);
        oldAccountTextBox.clear();
        oldAccountTextBox.sendKeys(element);
    }

    public void enterTextToNewPasswordTextBox(String element) throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordTextBox);
        newPasswordTextBox.clear();
        newPasswordTextBox.sendKeys(element);
        WaitTypes.clickWhenReadyByElement(newPasswordAgainTextBox);
        WaitTypes.clickWhenReadyByElement(newPasswordTextBox);
    }

    public void enterTextToNewPasswordAgainTextBox(String element) throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(newPasswordAgainTextBox);
        newPasswordAgainTextBox.clear();
        newPasswordAgainTextBox.sendKeys(element);

        WaitTypes.clickWhenReadyByElement(newPasswordTextBox);
    }

    public void enterTextToLastNameTextBox(String element) {
        WaitTypes.waitForElementVisibilityByWebElement(lastNameTextBox);
        lastNameTextBox.clear();
        lastNameTextBox.sendKeys(element);
    }
    










}
