package Page.classes;

import Util.Constants;
import Util.CredentialsData;
import Util.EMDriverSingleton;
import Util.WaitTypes;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

/**
 * Created by will.okanumeh on 6/9/2016.
 */
public class ZohoEmailPage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @FindBy(id = "lid")
    WebElement emailTextBox;

    @FindBy(id = "pwd")
    WebElement passwordTextBox;

    @FindBy(id = "rem")
    WebElement remCheckBox;

    @FindBy(id = "submit_but")
    WebElement signInButton;

    @FindBy(xpath = ".//*[@id='zm_centerHolder']/div/div/div[1]/div[1]/span")
    WebElement inboxLink;

    @FindBy(xpath = ".//div[@class='zmSub']/*[text()='Expert Models - Your password reset link is attached']")
    WebElement expertModelsQAInboxTitle;

    @FindBy(xpath = "//div[@class='zmSub']/*[text()='Account setup on Expert Models Beta']")
    WebElement accountSetupQAInboxTitle;

    //@FindBy(id = 'mail-cta-button')
    @FindBy(xpath = "//tbody//a[contains(text(),'Change Password')]")
    WebElement changePasswordEmailButton;

    @FindBy(xpath = "//div[@class='zmSub']//*[text()='Expert Models: please confirm you email address']")
    WebElement clickToConfirmInboxLink;

    @FindBy (xpath = ".//*[@id='zm_centerHolder']/div/div/div[2]/div[2]/ul[1]/li/b/span/i[1]")
    WebElement deleteCheckBox;

    @FindBy (xpath = "//div[@id='zm_centerHolder']//div[@class='SCmb']//i[@class='msi-trash']")
    WebElement trashIconLink;

    @FindBy (id = "nomails")
    WebElement inboxEmpty;

    @FindBy (id = "ztb-profile-image-pre")
    WebElement mainProfileMenu;

    @FindBy (id = "ztb-signout")
    WebElement logOutLink;

    @FindBy (xpath = "html/body/div[1]/div[1]/a")
    WebElement signInAgainLink;

    @FindBy (xpath = "//*[@id='continue_skip']")
    WebElement skipLink;

    @FindBy (xpath = "//*[@id='continue_url']")
    WebElement iUnderstandTheRiskLink;






    public ZohoEmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }






    public void enterTextToEmailTextBox(String element) {
        driver.switchTo().frame("zohoiam");
        WaitTypes.waitForElementVisibilityByWebElement(emailTextBox);
        emailTextBox.clear();
        emailTextBox.sendKeys(element);
    }

    public void enterTextToPasswordTextBox(String element) {
        WaitTypes.waitForElementVisibilityByWebElement(passwordTextBox);
        passwordTextBox.clear();
        passwordTextBox.sendKeys(element);
    }

    public void verifyChangePasswordEmailIsDisplayed() throws InterruptedException {
        Thread.sleep(4000);
        if (changePasswordEmailButton.isDisplayed()) {
            changePasswordEmailButton.isDisplayed();
        }else {
            Thread.sleep(4000);
            Constants.refreshPage();
            WaitTypes.clickWhenReadyByElement(expertModelsQAInboxTitle);
            WaitTypes.verifiedWhenReadyByElement(changePasswordEmailButton);
        }

    }

    public void verifyClickToConfirmEmailIsDisplayed() throws Exception {
    	Thread.sleep(3000);
    	Constants.refreshPage();
    	WaitTypes.waitForElementVisibilityByWebElement(clickToConfirmInboxLink);

    }

    public void verifyInboxEmptyIsDisplayed() throws InterruptedException {
        inboxEmpty.isDisplayed();
    }

    public void logOutOfZohoMail() throws InterruptedException{
        //WaitTypes.clickWhenReadyByElement(mainProfileMenu);
        Constants.javascriptClickWebElement(mainProfileMenu);
        WaitTypes.clickWhenReadyByElement(logOutLink);
    }

    public void verifyInboxPage() throws InterruptedException{
        if(WaitTypes.waitForElementVisibilityByWebElement(inboxLink)) {
            WaitTypes.clickWhenReadyByElement(skipLink);
            WaitTypes.clickWhenReadyByElement(iUnderstandTheRiskLink);
        } else {
            WaitTypes.waitForElementVisibilityByWebElement(inboxLink);

        }


    }

    public void verifySignInAgainIsDisplayed() {
        signInAgainLink.isDisplayed();
    }





    public void clickOnSignInButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(signInButton);
    }

    public void clickOnEMQAEmail() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(expertModelsQAInboxTitle);
    }

    public void clickOnChangePasswordLink() throws InterruptedException{

        WaitTypes.clickWhenReadyByElement(changePasswordEmailButton);
    }

    public void clickOnDeleteEmailCheckBox() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(deleteCheckBox);
    }

    public void clickOnTrashEmailIconLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(trashIconLink);
    }





    public void waitAndClickOnPasswordRestEmail() throws Exception {
    	Thread.sleep(4000);
    	Constants.refreshPage();
    	WaitTypes.clickWhenReadyByElement(expertModelsQAInboxTitle);

    }

    public void waitAndClickAccountSetupEmail() throws Exception { 	
    	Thread.sleep(5000);
    	Constants.refreshPage();
    	WaitTypes.clickWhenReadyByElement(accountSetupQAInboxTitle);
    	


    }
    
    
    public void deleteAllEmails() throws Exception{
    	 driver.get(CredentialsData.getZohoURL());
         enterTextToEmailTextBox(CredentialsData.getZohoEmailUser());
         enterTextToPasswordTextBox(CredentialsData.getZohoEmailPassword());
         clickOnSignInButton();
         
         verifyInboxPage();
         verifyClickToConfirmEmailIsDisplayed();
         clickOnDeleteEmailCheckBox();
         clickOnTrashEmailIconLink();
         verifyInboxEmptyIsDisplayed();
         logOutOfZohoMail();
         verifySignInAgainIsDisplayed();
    }

}
