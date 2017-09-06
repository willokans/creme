package Page.classes;

import Util.AssertType;
import Util.Constants;
import Util.CredentialsData;
import Util.WaitTypes;
import Util.EMDriverSingleton;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.Set;

/**
 * Created by will.okanumeh on 6/3/2016.
 */
public class LogInPage {

    final static int WAIT_TIME_OUT=50;
    
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    @FindBy(id = "em-login-input-email")
    //@FindBy(css = "#em-login-input-email")
    WebElement emailTextBox;

    @FindBy(id = "em-login-input-password")
    //@FindBy(css = "#em-login-input-password")
    WebElement passwordTextBox;

    @FindBy (id = "em-back-to-login")
    WebElement goBackToLoginLink;

    @FindBy (id = "em-login-submit")
    WebElement logInButton;

    @FindBy (id = "em-login-lost-pwd")
    WebElement lostPasswordLink;

    @FindBy(xpath = ".//*[@id='main-wrapper']/ng-include/md-toolbar/div/user-icon/md-menu/button")
    WebElement mainMenuButton;

    @FindBy(xpath = ".//*[@id='menu_container_1']/md-menu-content/md-menu-item[4]/button")
    WebElement logoutLink;

    @FindBy (xpath = "//div[@class='em-logo am-layout-gt-sm']")
    WebElement emLogo;
    
    @FindBy (xpath = "//div[@class='ng-binding ng-scope']")
    WebElement InvalidLoginErrorMessage;
    
    @FindBy (xpath = "//*[@id='login-wrapper']//*[@ng-message='minlength']")
    WebElement minLengthErrorMessage;

    @FindBy (xpath = "//div[@class='md-input-message-animation ng-scope']//translate[text()='Email is required.']")
    WebElement nullEmailError;

    @FindBy (xpath = "//div[@class='md-input-message-animation ng-scope']//translate[text()='Password is required.']")
    WebElement nullPasswordError;
    
    @FindBy (xpath = "//*[@id='em-reset-password-again-input']//following-sibling::ng-include//div[@class='md-input-message-animation ng-scope']")
    WebElement rp_nullPasswordAgainError;
    
    @FindBy (xpath = "//*[@id='em-reset-password-input']//following-sibling::ng-include//div[@class='md-input-message-animation ng-scope']")
    WebElement rp_noMinLengthPasswordError;
    
    @FindBy (xpath = "//*[@id='em-reset-password-again-input']//following-sibling::ng-include//div[@class='md-input-message-animation ng-scope']")
    WebElement rp_passwordsDoNotMatchError;
    
    @FindBy (xpath = "//*[@id='em-reset-password-input']//following-sibling::ng-include//div[@class='md-input-message-animation ng-scope']")
    WebElement rp_noMeetPasswordCriteriaError;
    
    @FindBy (xpath = "//div[@class='em-title']/span[@class='md-title']")
    WebElement rp_createPasswordTitle;
    
    @FindBy (xpath = "//div[@class='em-password-strength ng-binding ng-scope']")
    WebElement rp_passwordStrength;

    @FindBy (xpath = "//md-content[@id='login-wrapper']//div[@ng-repeat='error in nonFieldErrors']")
    WebElement loginErrorMessage;
    
    @FindBy (xpath = "//md-content[@id='forgot-pwd-wrapper']//div[@ng-repeat='error in nonFieldErrors']")
    WebElement forgotPwdAuthError;

    @FindBy (xpath = "//*[text()=' This account requires a password change. A link has been sent to the relevant email account. ']")
    WebElement passwordChangeErrorMessage;

    @FindBy (id = "em-reset-password-input")
    WebElement resetPasswordTextBox;

    @FindBy (id = "em-reset-password-again-input")
    WebElement resetPasswordAgainTextBox;

    @FindBy (id = "em-reset-submit")
    WebElement resetPasswordButton;

    //@FindBy(id = 'mail-cta-button')
    @FindBy(xpath = "//tbody//a[contains(text(),'Change Password')]")
    WebElement changePasswordEmailButton;

    @FindBy (xpath = "//div[@class='em-title']//*[text()='Forgot Password']")
    WebElement forgetPasswordTitle;

    @FindBy (id = "em-forgot-input-email")
    WebElement forgetPasswordEmailTextBox;

    @FindBy (id = "em-forgot-input-submit")
    WebElement forgetResetPasswordButton;

    @FindBy(xpath = "//md-content[@id='forgot-pwd-wrapper']//*[@ng-repeat='error in fieldErrors']")
    WebElement forgetPasswordErrorMessage;

    @FindBy (xpath = "//*[text()=\"Email isn't formatted correctly.\"]")
    WebElement wrongEmailSyntaxErrorMessage;

    @FindBy (xpath = "//div[@class='em-forgot-pwd-success']//*[text()='We have just emailed you a link to reset your password']")
    WebElement notifyOfEmailMessage;

    @FindBy(xpath = "//div[text()=' This email has not yet been verified. A new activation link has been sent to your email address. ']")
    WebElement notBeenVerifiedEmail;

    @FindBy(xpath = ".//*[@class='zmPCnt']/table/tbody/tr/td/div/center/table/tbody/tr/td/table/tbody/tr/td/center/table/tbody/tr[2]/td/center/a")
    WebElement clickToConfirmInboxLink;









    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }







    public void goToEMUrl(String Url) {
        driver.get(Url);
    }





    public void fillInEmailTextBox(String email) throws InterruptedException {
    	
    	Thread.sleep(2000);
    	boolean isDisplayed = driver.findElement(By.cssSelector("#em-login-input-email")).isDisplayed();
    	
    	if (isDisplayed = true) {
    		WaitTypes.clickWhenReadyByElement(emailTextBox);
    		emailTextBox.clear();
            emailTextBox.sendKeys(email);
    	}else {
    		Constants.refreshPage();
    		Thread.sleep(2000);
            WaitTypes.waitForElementVisibilityByWebElement(emailTextBox);
            WaitTypes.clickWhenReadyByElement(emailTextBox);
            emailTextBox.clear();
            emailTextBox.sendKeys(email);
    	}
    	
    	
    }

    public void fillInPasswordTextBox(String password) {
        WaitTypes.waitForElementVisibilityByWebElement(passwordTextBox);
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }

    public void clickResetPasswordAgainTextBox() {
        WaitTypes.waitForElementVisibilityByWebElement(resetPasswordAgainTextBox);
        resetPasswordAgainTextBox.click();        
    }
    
    public void clickResetPasswordTextBox() {
        WaitTypes.waitForElementVisibilityByWebElement(resetPasswordTextBox);
        resetPasswordTextBox.click();        
    }
    
    public void fillInResetPasswordAgainTextBox(String password){
        WaitTypes.waitForElementVisibilityByWebElement(resetPasswordAgainTextBox);
        resetPasswordAgainTextBox.clear();
        resetPasswordAgainTextBox.sendKeys(password);
    }

    public void fillInResetPasswordTextBox(String password){
        WaitTypes.waitForElementVisibilityByWebElement(resetPasswordTextBox);
        resetPasswordTextBox.clear();
        resetPasswordTextBox.sendKeys(password);
    }
    
    public void fillInForgetPasswordEmailTextBox(String password){
        WaitTypes.waitForElementVisibilityByWebElement(forgetPasswordEmailTextBox);
        forgetPasswordEmailTextBox.clear();
        forgetPasswordEmailTextBox.sendKeys(password);
    }
    
    public void verifyLoginAccessDenied(String inactiveProfile) throws IOException, Exception{
    	driver.get(CredentialsData.getBaseURL());
    	fillInEmailTextBox(inactiveProfile);
    	fillInPasswordTextBox(CredentialsData.getUserPassword());
    	clickLogInButton();
    	verifyLogInPage();
    	InvalidLoginErrorMessage();
    }
    
    public void verifyLoginAccessDeniedPostReset(String inactiveProfile) throws IOException, Exception{
    	driver.get(CredentialsData.getBaseURL());
    	fillInEmailTextBox(inactiveProfile);
    	fillInPasswordTextBox(CredentialsData.getNewPassword());
    	clickLogInButton();
    	verifyLogInPage();
    	InvalidLoginErrorMessage();
    }





    public void emailTextBoxIsBlankByDefault() {

        Assert.assertEquals(emailTextBox.getText(), "");
    }

    public void forgetPasswordEmailTextBoxIsBlankByDefault() {
        Assert.assertEquals(forgetPasswordEmailTextBox.getText(), "");
    }

    public void passwordTextBoxIsBlankByDefault() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(passwordTextBox);
        Assert.assertEquals(passwordTextBox.getText(), "");
    }

    public void forgetResetPasswordButtonIsDisabled() {
        WaitTypes.waitForElementVisibilityByWebElement(forgetResetPasswordButton);
        forgetResetPasswordButton.getAttribute("disable");
    }





    public void emailTextBoxIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(emailTextBox);
        Assert.assertEquals(emailTextBox.isDisplayed(), true);
    }

    public void emailNotRecognizedErrorMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(forgetPasswordErrorMessage);
        Assert.assertEquals(forgetPasswordErrorMessage.getText(), "Email not recognized. Are you sure it is correct?");
        Constants.refreshPage();
    }

    public void emailNotFormattedErrorMessageIsDisplayed() throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(wrongEmailSyntaxErrorMessage);
    	Assert.assertEquals(wrongEmailSyntaxErrorMessage.getText(), "Email isn't formatted correctly.");
    	Constants.refreshPage();
    }

    public void notificationOfEmailSentMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(notifyOfEmailMessage);
        Assert.assertEquals(notifyOfEmailMessage.isDisplayed(), true);
    }

    public void passwordTextBoxIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(passwordTextBox);
        Assert.assertEquals(passwordTextBox.isDisplayed(), true);
    }

    public void passwordChangeErrorMessageDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(passwordChangeErrorMessage);
        Assert.assertEquals(passwordChangeErrorMessage.isDisplayed(), true);
    }

    public void verifyNotBeenVerifiedEmailErrorMessageIsDisplayed() throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(notBeenVerifiedEmail);
        Assert.assertEquals(notBeenVerifiedEmail.isDisplayed(), true);
    }

    public void emLogoIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(emLogo);
        Assert.assertEquals(emLogo.isDisplayed(), true);
    }

    public void forgetResetPasswordButtonIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(forgetResetPasswordButton);
        Assert.assertEquals(forgetResetPasswordButton.isDisplayed(), true);
    }

    public void forgetPasswordTitleIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(forgetPasswordTitle);
        Assert.assertEquals(forgetPasswordTitle.isDisplayed(), true);
    }

    public void lostPasswordLinkDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(lostPasswordLink);
        Assert.assertEquals(lostPasswordLink.isDisplayed(), true);
    }

    public void LoginButtonIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(logInButton);
        Assert.assertEquals(logInButton.isDisplayed(), true);
    }

    public void InvalidLoginErrorMessage() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(InvalidLoginErrorMessage);
        AssertType.assertTrueIsDisplayedByText(InvalidLoginErrorMessage, "Unable to login with provided credentials.");
        Constants.refreshPage();
    }
    
    public void lessThanEightCharactersError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(minLengthErrorMessage);
        AssertType.assertTrueIsDisplayedByText(minLengthErrorMessage, "Password must be at least 8 characters long.");
        Constants.refreshPage();
    }
    

    public void nullPasswordError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(nullPasswordError);
        Assert.assertEquals(nullPasswordError.isDisplayed(), true);
        
    }
    
    public void nullPasswordAgainError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(rp_nullPasswordAgainError);
        AssertType.assertTrueIsDisplayedByText(rp_nullPasswordAgainError, "Confirmation Password is required.");  
        Constants.refreshPage();
    }
    
    public void noMinLengthPasswordError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(rp_noMinLengthPasswordError);
        AssertType.assertTrueIsDisplayedByText(rp_noMinLengthPasswordError, "Password must be at least 8 characters long.");  
        Constants.refreshPage();
    }
    
    public void passwordsDoNotMatchError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(rp_passwordsDoNotMatchError);
        AssertType.assertTrueIsDisplayedByText(rp_passwordsDoNotMatchError, "The two passwords do not match.");  
        Constants.refreshPage();
    }   
    
    public void passwordDoNotMeetCriteriaError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(rp_noMeetPasswordCriteriaError);
        AssertType.assertTrueIsDisplayedByText(rp_noMeetPasswordCriteriaError, "Please use at least one of each of the following character types: uppercase, lowercase, numbers, special characters.");  
        Constants.refreshPage();
    }       
    
    public void passwordStrength() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(rp_passwordStrength);
        AssertType.assertTrueIsDisplayedByText(rp_passwordStrength, "Password Strength : GOOD");  
     }       
    
    
    public void nullPasswordandPasswordAgainError() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(rp_nullPasswordAgainError);
        WaitTypes.waitForElementVisibilityByWebElement(nullPasswordError);
        Assert.assertEquals(nullPasswordError.isDisplayed(), true);
        AssertType.assertTrueIsDisplayedByText(rp_nullPasswordAgainError, "Confirmation Password is required.");  
        Constants.refreshPage();
    }
    
    public void verifyCreatePasswordTitle() throws Exception {
   	 WaitTypes.waitForElementVisibilityByWebElement(rp_createPasswordTitle);
        AssertType.assertTrueIsDisplayedByText(rp_createPasswordTitle, "Create Password"); 
   	
   }
       
    public void verifyPasswordTextbox() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(resetPasswordTextBox);
        Assert.assertEquals(resetPasswordTextBox.isDisplayed(), true);
        Assert.assertEquals(resetPasswordTextBox.getText(), "");
    }
    
    public void verifyPasswordAgainTextbox() throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(resetPasswordAgainTextBox);
    	Assert.assertEquals(resetPasswordAgainTextBox.isDisplayed(), true);
    	Assert.assertEquals(resetPasswordAgainTextBox.getText(), "");
    }

    public void verifyResetPasswordButtonIsDisabled() throws InterruptedException {
    	WaitTypes.waitForElementVisibilityByWebElement(resetPasswordButton);
    	Assert.assertEquals(resetPasswordButton.isDisplayed(), true);
    	Assert.assertEquals(resetPasswordButton.isEnabled(), false);
    }
    
    public void verifyResetPasswordButtonIsEnabled() throws InterruptedException {
    	WaitTypes.waitForElementVisibilityByWebElement(resetPasswordButton);
    	Assert.assertEquals(resetPasswordButton.isDisplayed(), true);
    	Assert.assertEquals(resetPasswordButton.isEnabled(), true);
    }
  
    public void verifyBackToLoginLink() throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(goBackToLoginLink);
    	Assert.assertEquals(goBackToLoginLink.isDisplayed(), true);
    }
    
    public void verifyChangePasswordPageContents() throws Exception {
    	String parentHandle = driver.getWindowHandle();

    	WaitTypes.clickWhenReadyByElement(changePasswordEmailButton);

        Set<String> handles = driver.getWindowHandles();

        for (String handle:handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                this.verifyCreatePasswordTitle();
                this.verifyPasswordTextbox();
                this.verifyPasswordAgainTextbox();
                this.verifyResetPasswordButtonIsDisabled();
                this.verifyBackToLoginLink();
            }
            else {
            	 driver.switchTo().window(parentHandle);
                 driver.close();
            }
            	
        }
      
    }

    public void nullEmailErrorMessageIsDisplayed() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(nullEmailError);
        Assert.assertEquals(nullEmailError.isDisplayed(), true);
    }

    public void verifyLogInPage() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(logInButton);
        Assert.assertEquals(logInButton.isDisplayed(), true);
    }

    public void verifyNonAuthenticatedLoginErrorMessage() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(loginErrorMessage);
        Assert.assertEquals(loginErrorMessage.getText(), "This email has not yet been verified. A new activation link has been sent to your email address.");
        Constants.refreshPage();
    }
    
    public void verifyNonAuthenticatedForgotPwdMessage() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(forgotPwdAuthError);
        Assert.assertEquals(forgotPwdAuthError.getText(), "This account has not yet been activated. a new activation link has been sent to your email address.");
        Constants.refreshPage();
    }

    public void clickLostPasswordLink() throws Exception {
    	WaitTypes.fluentWait(By.id("em-login-lost-pwd"));
        WaitTypes.clickWhenReadyByElement(lostPasswordLink);
    }

    public void clickGoBackToLoginLink() throws Exception {
        WaitTypes.clickWhenReadyByElement(goBackToLoginLink);
    }

    public void clickLogInButton() {
        WaitTypes.waitForElementVisibilityByWebElement(logInButton);
    	logInButton.click();
    }

    public void clickResetPassword() throws InterruptedException {

        WaitTypes.clickWhenReadyByElement(resetPasswordButton);
    }

    public void clickForgetResetPasswordButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(forgetResetPasswordButton);
    }

    public void clickChangePasswordLinkAndSubmit(String password) throws Exception {
        String parentHandle = driver.getWindowHandle();

        WaitTypes.clickWhenReadyByElement(changePasswordEmailButton);

        Set<String> handles = driver.getWindowHandles();

        for (String handle:handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                WaitTypes.waitForElementVisibilityByWebElement(resetPasswordTextBox);
                resetPasswordTextBox.clear();
                resetPasswordTextBox.sendKeys(password);

                WaitTypes.waitForElementVisibilityByWebElement(resetPasswordAgainTextBox);
                resetPasswordAgainTextBox.clear();
                resetPasswordAgainTextBox.sendKeys(password);

                WaitTypes.clickWhenReadyByElement(resetPasswordButton);
                Thread.sleep(2000);
                driver.close();
            }
        }
        driver.switchTo().window(parentHandle);
    }

    
    public void clickLinkClickToConfirmAndSubmit(String email, String password) throws InterruptedException {
        String parentHandle = driver.getWindowHandle();

        clickToConfirmInboxLink.click();

        Set<String> handles = driver.getWindowHandles();

        for (String handle:handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                WaitTypes.waitForElementVisibilityByWebElement(emailTextBox);
                emailTextBox.clear();
                emailTextBox.sendKeys(email);

                WaitTypes.waitForElementVisibilityByWebElement(passwordTextBox);
                passwordTextBox.clear();
                passwordTextBox.sendKeys(password);

                WaitTypes.clickWhenReadyByElement(logInButton);

                WaitTypes.clickWhenReadyByElement(mainMenuButton);

                WaitTypes.clickWhenReadyByElement(logoutLink);
            }
        }
        driver.switchTo().window(parentHandle);
    }

    
    public void clearEmailTextBox() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(emailTextBox);
    	emailTextBox.clear();
    	WaitTypes.clickWhenReadyByElement(emailTextBox);
        WaitTypes.clickWhenReadyByElement(passwordTextBox);
    }
    
    public void clearPasswordTextBox() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(passwordTextBox);
    	passwordTextBox.clear();
    	WaitTypes.clickWhenReadyByElement(passwordTextBox); 
        WaitTypes.clickWhenReadyByElement(emailTextBox);  
    }


    public void closeWebBrowser() {
        driver.close();
    }



}