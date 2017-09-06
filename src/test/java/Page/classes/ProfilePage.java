package Page.classes;

import Util.AssertType;
import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.WaitTypes;

import java.io.IOException;

import javax.lang.model.element.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by will.okanumeh on 7/1/2016.
 */
public class ProfilePage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @FindBy(xpath = "//*[text()='My Profile']")
    WebElement mainMenuMyProfileLink;

    @FindBy(xpath = "//div[@class='profile-tabs']//span[text()='Details']")
    WebElement profilePageDetailLink;

    @FindBy(xpath = "//div[@class='profile-header-name']//*[text()='Automation Tester']")
    WebElement profilePageUserNameLabel;
    
    //@FindBy(xpath = "//div[@id='profile-team']//md-list-item//div[text()='Automation Tester']")
    @FindBy(xpath = "//div[@id='profile-team']/md-list//md-list-item//span[text()='QA Automation Engineer']//preceding-sibling::div")  
    WebElement editedTeamPageMember;

    @FindBy(xpath = "//div[@id='profile-main-info']//span[text()='Add Role']")
    WebElement profilePageAddRoleLabel;

    @FindBy(xpath = "//span[text()='Add Company']")
    WebElement profilePageAddCompLabel;

    @FindBy(xpath = "//span[text()='Add Location']")
    WebElement profilePageAddLocationLabel;

    @FindBy(xpath = ".//*[@id='profile-avatar']/img")
    WebElement profilePageImageLogo;

    @FindBy(xpath = ".//*[@id='content']/div/div/div/form/div[1]/div/a")
    WebElement profilePageEmailLabel;

    @FindBy(xpath = "//div[@class='details-value ng-scope editable editable-empty']//span[text()='Add Phone']")
    WebElement profilePageTelLabel;

    @FindBy(xpath = "//div[@class='details-value ng-scope editable editable-empty']//span[text()='Add Website']")
    WebElement profilePageWebLabel;

    @FindBy(xpath = "//div[@class='details-value ng-scope editable editable-empty']//span[text()='Add Linkedin']")
    WebElement profilePageLinkedInLabel;

    @FindBy(xpath = "//div[@class='details-value ng-scope editable editable-empty']//span[text()='Add Twitter']")
    WebElement profilePageTwitterLabel;

    @FindBy(xpath = "//div[@class='details-value ng-scope editable editable-empty']//span[text()='Add Facebook']")
    WebElement profilePageFacebookLabel;

    @FindBy(xpath = "//span[@ng-if='profileData.location']")
    WebElement profilePageAddLocationLabelAfterUpdate;

    @FindBy(xpath = "//span[@ng-if='profileData.company']")
    WebElement profilePageAddCompLabelAfterUpdate;

    @FindBy(xpath = "//span[@ng-if='profileData.job_title']")
    WebElement profilePageAddRoleLabelAfterUpdate;

    @FindBy(xpath = "//*[text()='01-12345']")
    WebElement profilePageTelLabelAfterUpdate;

    @FindBy(xpath = "//*[text()='www.google.com']")
    WebElement profilePageWebLabelAfterUpdate;

    @FindBy(xpath = "//*[text()='www.linkedIn.com']")
    WebElement profilePageLinkedInLabelAfterUpdate;

    @FindBy(xpath = "//*[text()='www.twitter.com']")
    WebElement profilePageTwitterLabelAfterUpdate;

    @FindBy(xpath = "//*[text()=' www.facebook.com']")
    WebElement profilePageFacebookLabelAfterUpdate;

    @FindBy(xpath = "html/body/div[1]/md-content/md-content/div[3]/div/div/div/div/div/div/div/form/div[2]/span/md-input-container/input")
    WebElement profilePageTelTextBox;

    @FindBy(xpath = "html/body/div[1]/md-content/md-content/div[3]/div/div/div/div/div/div/div/form/div[3]/span/md-input-container/input")
    WebElement profilePageWebTextBox;

    @FindBy(xpath = "html/body/div[1]/md-content/md-content/div[3]/div/div/div/div/div/div/div/form/div[4]/span/md-input-container/input")
    WebElement profilePageLinkedInTextBox;

    @FindBy(xpath = "html/body/div[1]/md-content/md-content/div[3]/div/div/div/div/div/div/div/form/div[5]/span/md-input-container/input")
    WebElement profilePageTwitterTextBox;

    @FindBy(xpath = ".//*[@id='fs-edit-filename-field']/input")
    WebElement profilePageAddRoleTextBox;

    @FindBy(xpath = ".//*[@id='fs-edit-filename-field']/input")
    WebElement profilePageAddLocationTextBox;

    @FindBy(xpath = ".//*[@id='fs-edit-filename-field']/input")
    WebElement profilePageAddCompTextBox;

    @FindBy(xpath = "html/body/div[1]/md-content/md-content/div[3]/div/div/div/div/div/div/div/form/div[6]/span/md-input-container/input")
    WebElement profilePageFacebookTextBox;

    @FindBy(xpath = ".//*[@id='content']/div/div/div/form/button")
    WebElement profilePageEditButton;

    @FindBy(xpath = "//button[@class='md-accent md-raised cf-btn-s form-btn md-button md-ink-ripple']//*[text()='Save']")
    WebElement profilePageSaveButton;

    @FindBy(xpath = "//button[@class='md-primary md-raised md-hue-3 cf-btn-s form-btn md-button md-em-bg-theme md-ink-ripple']//*[text()='Cancel']")
    WebElement profilePageCancelButton;

    @FindBy(xpath = "//*[text()='Team']")
    WebElement profilePageTeamLink;

    @FindBy(id = "editable-form-cancel-btn")
    WebElement profileMainInfoCancelButton;

    @FindBy(xpath = "//*[@class='md-accent md-raised cf-icon-sq md-button md-ink-ripple']//*[text()='check']")
    WebElement profileMainInfoSaveButton;

    @FindBy(xpath = "//span[text()='QA Automation Engineer']")
    WebElement updatedRoleQAEngr;

    @FindBy(xpath = "//div[@id='prof-phone-number']//span[text()='No Phone provided']")
    WebElement profilePageDetailsNoPhoneProvided;

    @FindBy(xpath = "//div[@id='prof-website']//span[text()='No Website provided']")
    WebElement profilePageDetailsNoWebsiteProvided;

    @FindBy(xpath = "//div[@id='prof-linkedin']//span[text()='No Linkedin provided']")
    WebElement profilePageDetailsNoLinkedInProvided;

    @FindBy(xpath = "//div[@id='prof-twitter']//span[text()='No Twitter provided']")
    WebElement profilePageDetailsNoTwitterProvided;

    @FindBy(xpath = "//div[@id='prof-facebook']//span[text()='No Facebook provided']")
    WebElement profilePageDetailsNoFacebookProvided;

    @FindBy(xpath = "//*[@id='profile-main-info']//span[text()='QA Automation Engineer']")
    WebElement profilePageRoleProvided;

    //@FindBy(xpath = "//*[@id='profile-main-info']//span[text()='No Company provided']")
    @FindBy(xpath = "//div[@id='profile-main-info']//span[contains(text(),'Company')]")
    WebElement profilePageNoCompanyProvided;

    //@FindBy(xpath = "//*[@id='profile-main-info']//span[text()='No Location provided']")
    @FindBy(xpath = "//div[@id='profile-main-info']//span[contains(text(),'Location')]")
    WebElement profilePageNoLocationProvided;

    @FindBy(xpath = "//div[@id = 'content']//div[@class='details-value']")
    WebElement profilePageEmailLink;





    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }








    public void verifyUserNameIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageUserNameLabel);
        profilePageUserNameLabel.isDisplayed();
    }

    public void verifyGravaterImgIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageImageLogo);
        profilePageImageLogo.isDisplayed();
    }

    public void verifyUserNameLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageUserNameLabel);
        profilePageUserNameLabel.isDisplayed();
    }

    public void verifyAddRoleLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageAddRoleLabel);
        profilePageAddRoleLabel.isDisplayed();
        Assert.assertEquals(profilePageAddRoleLabel.getText(), "Add Role");
    }

    public void verifyCompanyLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageNoCompanyProvided);
        profilePageNoCompanyProvided.isDisplayed();      
        Assert.assertEquals(profilePageNoCompanyProvided.getText(), "Add Company");
    }

    public void verifyLocationLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageNoLocationProvided);
        profilePageNoLocationProvided.isDisplayed();
        Assert.assertEquals(profilePageNoLocationProvided.getText(), "Add Location");
    }

    public void verifyEmailAddressIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageEmailLabel);
        profilePageEmailLabel.isDisplayed();
    }

    public void verifyTelLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageTelLabel);
        profilePageTelLabel.isDisplayed();
    }

    public void verifyWebLinkIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageWebLabel);
        profilePageWebLabel.isDisplayed();
    }

    public void verifyLinkedInLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageLinkedInLabel);
        profilePageLinkedInLabel.isDisplayed();
    }

    public void verifyTwitterLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageTwitterLabel);
        profilePageTwitterLabel.isDisplayed();
    }

    public void verifyFacebookLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageFacebookLabel);
        profilePageFacebookLabel.isDisplayed();
    }

    public void verifyEditButtonIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageEditButton);
        profilePageEditButton.isDisplayed();
    }

    public void verifyTeamLinkIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageTeamLink);
        profilePageTeamLink.isDisplayed();
    }
    
    public void verifyDetailsLinkIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageDetailLink);
        profilePageDetailLink.isDisplayed();
    }

    public void verifyUpdatedRoleIsDisplayed() {
    	WaitTypes.waitForElementVisibilityByWebElement(editedTeamPageMember);
    	editedTeamPageMember.isDisplayed();
        WaitTypes.waitForElementVisibilityByWebElement(updatedRoleQAEngr);
        updatedRoleQAEngr.isDisplayed();
    }

    public void verifyTestUserPageIsDisplayed() throws Exception {
    	WaitTypes.verifiedWhenReadyByElement(profilePageEmailLink);
        profilePageEmailLink.isDisplayed();
        Thread.sleep(1000);
        Assert.assertEquals(profilePageEmailLink.getText(), CredentialsData.getAccAccessUser());
    }

    public void verifyTelLabelIsReadOnly() {
        Constants.clickByElement(profilePageDetailsNoPhoneProvided);
        WaitTypes.waitForElementVisibilityByWebElement(profilePageDetailsNoPhoneProvided);
        Assert.assertEquals(profilePageDetailsNoPhoneProvided.getAttribute("ng-show"), "!profileData.phone_number && readOnly");
    }

    public void verifyWebSiteLabelIsReadOnly() {
        Constants.clickByElement(profilePageDetailsNoWebsiteProvided);
        WaitTypes.waitForElementVisibilityByWebElement(profilePageDetailsNoWebsiteProvided);;
        Assert.assertEquals(profilePageDetailsNoWebsiteProvided.getAttribute("ng-show"), "!profileData.website && readOnly");
    }

    public void verifyLinkedInLabelIsReadOnly() {
        Constants.clickByElement(profilePageDetailsNoLinkedInProvided);
        WaitTypes.waitForElementVisibilityByWebElement(profilePageDetailsNoLinkedInProvided);
        Assert.assertEquals(profilePageDetailsNoLinkedInProvided.getAttribute("ng-show"), "!profileData.linkedin && readOnly");
    }

    public void verifyTwitterLabelIsReadOnly() {
        Constants.clickByElement(profilePageDetailsNoTwitterProvided);
        WaitTypes.waitForElementVisibilityByWebElement(profilePageDetailsNoTwitterProvided);
        Assert.assertEquals(profilePageDetailsNoTwitterProvided.getAttribute("ng-show"), "!profileData.twitter && readOnly");
    }

    public void verifyFacebookLabelIsReadOnly() {
        Constants.clickByElement(profilePageDetailsNoFacebookProvided);
        WaitTypes.waitForElementVisibilityByWebElement(profilePageDetailsNoFacebookProvided);
        Assert.assertEquals(profilePageDetailsNoFacebookProvided.getAttribute("ng-show"), "!profileData.facebook && readOnly");
    }

    public void verifyRoleIsReadOnly() {
    	Constants.clickByElement(profilePageRoleProvided);
        WaitTypes.waitForElementVisibilityByWebElement(profilePageRoleProvided);
        Assert.assertEquals(profilePageRoleProvided.getAttribute("ng-if"), "profileData.job_title");
    }

    public void verifyCompanyIsReadOnly() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageNoCompanyProvided);
        Assert.assertEquals(profilePageNoCompanyProvided.getAttribute("ng-if"), "!profileData.company && readOnly");      
    }

    public void verifyLocationIsReadOnly() {
        Constants.clickByElement(profilePageNoLocationProvided);
        WaitTypes.waitForElementVisibilityByWebElement(profilePageNoLocationProvided);
        Assert.assertEquals(profilePageNoLocationProvided.getAttribute("ng-if"), "!profileData.location && readOnly");
    }



    public void verifyReadOnlyUserName() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageUserNameLabel);
        Constants.readOnly(profilePageUserNameLabel);
    }




    public void clickAddRoleLabel() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(profilePageAddRoleLabel);
    }

    public void clickAddCompanyLabel() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(profilePageNoCompanyProvided);
    }

    public void clickAddLocationLabel() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(profilePageNoLocationProvided);
    }

    public void clickEditButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(profilePageEditButton);
    }

    public void clickSaveButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(profilePageSaveButton);
    }

    public void clickCancelButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(profilePageCancelButton);
    }

    public void clickTeamLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(profilePageTeamLink);
    }

    public void clickTestUserLink() throws InterruptedException {
    	WaitTypes.clickWhenReadyByElement(editedTeamPageMember);
    }

    public void clickCheckSaveChangeButton() throws InterruptedException{

        WaitTypes.clickWhenReadyByElement(profileMainInfoSaveButton);
    }

    public void clickCheckCancelChangeButton() throws InterruptedException{

        WaitTypes.clickWhenReadyByElement(profileMainInfoCancelButton);
    }




    public void fillInRoleTextBox(String s) throws InterruptedException{

        WaitTypes.clickWhenReadyByElement(profilePageAddRoleLabel);

        WaitTypes.clickWhenReadyByElement(profilePageAddRoleTextBox);
        profilePageAddRoleTextBox.clear();
        profilePageAddRoleTextBox.sendKeys(s);
    }

    public void fillInCompanyTextBox(String s) throws InterruptedException{

        WaitTypes.clickWhenReadyByElement(profilePageAddCompLabel);

        WaitTypes.clickWhenReadyByElement(profilePageAddCompTextBox);
        profilePageAddCompTextBox.clear();
        profilePageAddCompTextBox.sendKeys(s);
    }

    public void fillInLocationTextBox(String s) throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(profilePageAddLocationLabel);

        WaitTypes.clickWhenReadyByElement(profilePageAddLocationTextBox);
        profilePageAddLocationTextBox.clear();
        profilePageAddLocationTextBox.sendKeys(s);
    }




    public void roleTextBoxUpdateIsDisplayed(String roleEntered) throws Exception {
    	WaitTypes.waitUntilElementIsClickable(profilePageAddRoleLabelAfterUpdate);
        profilePageAddRoleLabelAfterUpdate.isDisplayed();
        Assert.assertEquals(profilePageAddRoleLabelAfterUpdate.getText(), roleEntered);

    }

    public void companyTextBoxUpdateIsDisplayed(String companyEntered) throws Exception {
    	WaitTypes.waitUntilElementIsClickable(profilePageAddCompLabelAfterUpdate);
        profilePageAddCompLabelAfterUpdate.isDisplayed();
        Assert.assertEquals(profilePageAddCompLabelAfterUpdate.getText(), companyEntered);

    }

    public void locationTextBoxUpdateIsDisplayed(String locationEntered) throws Exception {
    	WaitTypes.waitUntilElementIsClickable(profilePageAddLocationLabelAfterUpdate);
        profilePageAddLocationLabelAfterUpdate.isDisplayed();
        Assert.assertEquals(profilePageAddLocationLabelAfterUpdate.getText(), locationEntered);

    }

    public void telNumberIsDisplayed() throws Exception {

        profilePageTelLabelAfterUpdate.isDisplayed();

    }

    public void webSiteNameLinkIsDisplayed() throws Exception {

        profilePageWebLabelAfterUpdate.isDisplayed();

    }

    public void facebookLinkIsDisplayed() throws Exception {

        profilePageFacebookLabelAfterUpdate.isDisplayed();

    }

    public void linkedInLinkIsDisplayed() throws Exception {

        profilePageLinkedInLabelAfterUpdate.isDisplayed();

    }

    public void twitterLinkIsDisplayed() throws Exception {

        profilePageTwitterLabelAfterUpdate.isDisplayed();

    }






    public void clearAddRoleTextBox() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(profilePageAddRoleLabelAfterUpdate);
        profilePageAddRoleTextBox.clear();
        WaitTypes.clickWhenReadyByElement(profileMainInfoSaveButton);
    }

    public void clearAddCompTextBox() throws InterruptedException{

        WaitTypes.clickWhenReadyByElement(profilePageAddCompLabelAfterUpdate);
        profilePageAddCompTextBox.clear();
        WaitTypes.clickWhenReadyByElement(profileMainInfoSaveButton);
    }

    public void clearAddLocationTextBox() throws InterruptedException{

        WaitTypes.clickWhenReadyByElement(profilePageAddLocationLabelAfterUpdate);
        profilePageAddLocationTextBox.clear();
        WaitTypes.clickWhenReadyByElement(profileMainInfoSaveButton);
    }

    public void clearTelNumberTextBox() {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageTelTextBox);
        profilePageTelTextBox.clear();
    }

    public void clearWebSiteTextBox() {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageWebTextBox);
        profilePageWebTextBox.clear();
    }

    public void clearLinkedInTextBox() {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageLinkedInTextBox);
        profilePageLinkedInTextBox.clear();
    }

    public void clearTwitterTextBox() {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageTwitterTextBox);
        profilePageTwitterTextBox.clear();
    }

    public void clearFacebookTextBox() {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageFacebookTextBox);
        profilePageFacebookTextBox.clear();
    }






    public void enterPhoneNumber(String s) {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageTelTextBox);
        profilePageTelTextBox.sendKeys(s);

    }

    public void enterWebSiteLink(String s) {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageWebTextBox);
        profilePageWebTextBox.sendKeys(s);
    }

    public void enterFaceBookAccountLink(String s) {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageFacebookTextBox);
        profilePageFacebookTextBox.sendKeys(s);

    }

    public void enterTwitterAccountLink (String s) {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageTwitterTextBox);
        profilePageTwitterTextBox.sendKeys(s);
    }

    public void enterLinkedInAccountLink (String s) {

        WaitTypes.waitForElementVisibilityByWebElement(profilePageLinkedInTextBox);
        profilePageLinkedInTextBox.sendKeys(s);

    }




    public void emailIsLinkedToMailTo() {
    	WaitTypes.waitForElementVisibilityByWebElement(profilePageEmailLabel);
    	profilePageEmailLabel.isDisplayed();
        Assert.assertEquals(profilePageEmailLabel.getAttribute("href"), "mailto:qa@cremelabs.com");

    }

    public void verifyQAGravatarSource() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageImageLogo);
        profilePageImageLogo.isDisplayed();
        Assert.assertEquals(profilePageImageLogo.getAttribute("ng-src"), "http://www.gravatar.com/avatar/7979d2dcf6412f4af0196ed09a69d54e?s=48&d=identicon&s=200");

    }


    public void verifyTestGravatarSource() {
        WaitTypes.waitForElementVisibilityByWebElement(profilePageImageLogo);
        profilePageImageLogo.getAttribute("http://www.gravatar.com/avatar/8a973af4b5614cbb4e7e8c0a3e0bd67a?s=48&d=identicon&s=200");

    }
    
    public void verifyUserIsOnMyProfilePage() throws Exception{
    	verifyUserNameIsDisplayed();
    	verifyDetailsLinkIsDisplayed();
    	verifyEmailAddressIsDisplayed();
    	
    }
    
    public void verifyDetailsAreEmpty(){
    	WaitTypes.waitForElementVisibilityByWebElement(profilePageTelLabel);
    	Assert.assertEquals(profilePageTelLabel.getText(), "Add Phone");
    	Assert.assertEquals(profilePageWebLabel.getText(), "Add Website");
    	Assert.assertEquals(profilePageLinkedInLabel.getText(), "Add Linkedin");
    	Assert.assertEquals(profilePageTwitterLabel.getText(), "Add Twitter");
    	Assert.assertEquals(profilePageFacebookLabel.getText(), "Add Facebook");
    }



}
