package Page.classes;

import Util.AssertType;
import Util.EMDriverSingleton;
import Util.Constants;
import Util.CredentialsData;
import Util.WaitTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by will.okanumeh on 6/14/2016.
 */
public class DjangoPage {

    final static int WAIT_TIME_OUT=50;

    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    @FindBy(xpath = "//div[@id='content']/h1[text()='Add px user']")
    WebElement addUserHeader;
    
    @FindBy(id = "id_username")
    WebElement emailTextBox;

    @FindBy(id = "id_password")
    WebElement passwordTextBox;

    @FindBy(xpath = "//div[@class='submit-row']//input[@value='Log in']")
    WebElement LogInButton;

    @FindBy(id = "id_first_name")
    WebElement regFirstName;

    @FindBy(id = "id_last_name")
    WebElement regLastName;

    @FindBy(id = "id_email")
    WebElement regEmailAdd;

    @FindBy(id = "id_password2")
    WebElement regPasswordConfirmation;

    @FindBy(id = "id_password1")
    WebElement regPassword;

    @FindBy (xpath = ".//*[@id='pxuser_form']/div/div/input[1]")
    WebElement regSaveButton;

    @FindBy (xpath = ".//*[@id='pxuser_form']/div/div/p/a")
    WebElement regDeleteButton;

    @FindBy (xpath = ".//*[@id='content']/form/div/input[2]")
    WebElement regYesImSureButton;

    @FindBy (xpath = ".//*[@id=\'container\']/ul/li")
    WebElement userDeleteMessage;

    @FindBy (xpath = ".//*[@id='container']/ul/li")
    WebElement userCreateMessage;

    @FindBy (xpath = ".//*[@id='pxuser_form']/div/p")
    WebElement correctAllErrorsMessage;

    @FindBy (xpath = ".//*[@id='pxuser_form']/div/fieldset/div[1]/ul/li")
    WebElement firstnameRequiredError;

    @FindBy (xpath = ".//*[@id='pxuser_form']/div/fieldset/div[2]/ul/li")
    WebElement lastnameRequiredError;

    @FindBy (xpath = ".//*[@id='pxuser_form']/div/fieldset/div[3]/ul/li")
    WebElement emailRequiredError;
    
    @FindBy (xpath = "//*[@id='pxuser_form']/div/fieldset/div[4]/ul/li")
    WebElement orgRequiredError;

    @FindBy (xpath = ".//*[@id='pxuser_form']/div/fieldset/div[5]/ul/li")
    WebElement pwdRequiredError;
    
    @FindBy (xpath = ".//*[@id='pxuser_form']/div/fieldset/div[6]/ul/li")
    WebElement confirmPwdRequiredError;

    @FindBy(xpath = "//div[@id='branding']//a[text()='Django administration']")
    WebElement DjangoLogInPageVerification;

    @FindBy(id = "user-tools")
    WebElement DjangoLogHomePageVerification;

    @FindBy(xpath = "//div[@class='app-accounts module']//a[text()='Px users']")
    WebElement pXUserLink;

    @FindBy(xpath = "//div[@class='results']//table[@id='result_list']//a[text()='qa@cremelabs.com']")
    WebElement qaEmailLink;

    @FindBy(xpath = "//div[@class='results']//table[@id='result_list']//a[text()='fileSystemTest@cremelabs.com']")
    WebElement fileSystemEmailLink;

    @FindBy(xpath = "//div[@class='results']//table[@id='result_list']//*[text()='importTest@cremelabs.com']")
    WebElement importUserEmailLink;

    @FindBy(xpath = "//div[@class='results']//table[@id='result_list']//*[text()='exportTest@cremelabs.com']")
    WebElement exportUserEmailLink;

    @FindBy(xpath = "//div[@class='results']//table[@id='result_list']//*[text()='accountAccessTest@cremelabs.com']")
    WebElement accountAccessUserEmailLink;
    
    @FindBy(xpath = "//div[@class='results']//table[@id='result_list']//a[text()='emheader@cremelabs.com']")
    WebElement emHeaderUserEmailLink;
    
    @FindBy(xpath = ".//*[@id='result_list']/tbody/tr/th/a")
    WebElement userToSelect;

    @FindBy(id = "id_is_active")
    WebElement isActiveButton;

    @FindBy(id = "id_needs_password_change")
    WebElement needsPasswordChangeButton;

    @FindBy(xpath = "//div[@id='content-main']//input[@name='_save']")
    WebElement saveButton;

    @FindBy(xpath = ".//*[@id='container']/ul/li")
    WebElement saveSuccessErrorMessage;

    @FindBy(xpath = "//div[@id='user-tools']//a[text()='Log out']")
    WebElement logOutButton;

    @FindBy(xpath = ".//*[@id='content']/p[2]/a")
    WebElement logOutAgainButton;

    @FindBy(xpath = "//div[@id='content']//a[text()='Log in again']")
    WebElement DjangoLogOutPageVerification;

    @FindBy(xpath = ".//*[@id='content-main']/ul/li/a")
    WebElement addPXUserLink;

    @FindBy(id = "id_organization")
    WebElement orgDropDown;

    //@FindBy(xpath = ".//*[@id='searchbar']")
    @FindBy(id = "searchbar")
    WebElement pxUserSearchTextBox;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement pxUserSearchButton;

    @FindBy(xpath = "//select[@id='id_groups_from']//option[text()='Expert Models']")
    WebElement expertModelsAvailableGroup;

    @FindBy(id = "id_groups_add_link")
    WebElement groupAddButton;

    @FindBy(xpath = "//select[@id='id_groups_to']//option[text()='Expert Models']")
    WebElement expertModelsChosenGroups;








    public DjangoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }





    public void goToAPIURL(String Url) throws InterruptedException {
        driver.get(Url);
        if (!emailTextBox.isDisplayed()) {

            WaitTypes.clickWhenReadyByElement(logOutButton);
            WaitTypes.clickWhenReadyByElement(logOutAgainButton);

        }else {
            emailTextBox.isDisplayed();
        }
    }





    public void fillInEmailTextBox(String email) {
    	WaitTypes.waitUntilElementIsClickable(emailTextBox);
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
    }

    public void fillInPasswordTextBox(String password) {
    	WaitTypes.waitUntilElementIsClickable(passwordTextBox);
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
    }

    public void fillInRegPasswordTextBox(String password) {
    	WaitTypes.waitUntilElementIsClickable(regPassword);
        regPassword.clear();
        regPassword.sendKeys(password);
    }

    public void fillInRegPasswordConfirmationTextBox(String password) {
    	WaitTypes.waitUntilElementIsClickable(regPasswordConfirmation);
        regPasswordConfirmation.clear();
        regPasswordConfirmation.sendKeys(password);
    }

    public void fillInRegFirstNameTextBox(String FirstName) {
    	WaitTypes.waitUntilElementIsClickable(regFirstName);
        regFirstName.clear();
        regFirstName.sendKeys(FirstName);
    }

    public void fillInRegLastNameTextBox(String LastName) {
    	WaitTypes.waitUntilElementIsClickable(regLastName);
        regLastName.clear();
        regLastName.sendKeys(LastName);
    }

    public void fillInRegEmailTextBox(String email) {
    	WaitTypes.waitUntilElementIsClickable(regEmailAdd);
        regEmailAdd.clear();
        regEmailAdd.sendKeys(email);
    }

    public void fillInPxUserSearchTextBox(String itemToFind) throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(pxUserSearchTextBox);
        pxUserSearchTextBox.sendKeys(itemToFind);
    }

    public void verifyUsersListIsDisplayed(){
    	WaitTypes.waitForElementVisibilityByWebElement(userToSelect);
    	Assert.assertEquals(userToSelect.isDisplayed(), true);
    }

    public void clickLogInButton() throws Exception {
    	WaitTypes.clickWhenReadyByElement(LogInButton);
        //LogInButton.click();

    }

    public void clickIsActiveCheckBox() throws Exception {
    	WaitTypes.clickWhenReadyByElement(isActiveButton);
        //isActiveButton.click();
    }

    public void clickNeedsPasswordChangeCheckBox() throws Exception {
    	WaitTypes.clickWhenReadyByElement(needsPasswordChangeButton);
        needsPasswordChangeButton.click();
    }

    public void clickOnSave() {
        Constants.scrollToElementByElement(saveButton);
        saveButton.click();
    }

    public void clickDeleteButton() {
        Constants.scrollToElementByElement(regDeleteButton);
        regDeleteButton.click();
    }

    public void clickYesImSureButton() {
        Constants.scrollToElementByElement(regYesImSureButton);
        regYesImSureButton.click();
    }

    public void importUserEmailLink() {
        Constants.scrollToElement(By.xpath("//div[@class='results']//table[@id='result_list']//*[text()='importTest@cremelabs.com']"));
        importUserEmailLink.click();
    }

    public void exportUserEmailLink() {
        Constants.scrollToElement(By.xpath("//div[@class='results']//table[@id='result_list']//*[text()='exportTest@cremelabs.com']"));
        exportUserEmailLink.click();
    }

    public void accountAccessUserEmailLink() {
        Constants.scrollToElement(By.xpath("//div[@class='results']//table[@id='result_list']//*[text()='accountAccessTest@cremelabs.com']"));
        accountAccessUserEmailLink.click();
    }

    public void fileSystemEmailLink() {
        Constants.scrollToElement(By.xpath("//div[@class='results']//table[@id='result_list']//*[text()='fileSystemTest@cremelabs.com']"));
        fileSystemEmailLink.click();
    }

    public void qaEmailLink() {
        Constants.scrollToElement(By.xpath("//div[@class='results']//table[@id='result_list']//*[text()='qa@cremelabs.com']"));
        qaEmailLink.click();
    }
    
    public void emHeaderUserEmailLink() {	
        Constants.scrollToElement(By.xpath("//div[@class='results']//table[@id='result_list']//*[text()='emheader@cremelabs.com']"));
        //Constants.scrollToElementByElement(emHeaderUserEmailLink);
        emHeaderUserEmailLink.click();
    }

    public void clickAddPXUserLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(addPXUserLink);
    }
    
    public void findAndSelectProfile(String profileToDelete) throws Exception{
    	fillInPxUserSearchTextBox(profileToDelete);
    	clickPXUserSearchButton();
    	Assert.assertEquals(userToSelect.getText(), profileToDelete);
    	userToSelect.click();
    }

    public void logOut() {
        Constants.scrollToElement(By.xpath("//div[@id='user-tools']//a[text()='Log out']"));
        logOutButton.click();
    }

    public void clickOnRegSaveButton() {
        Constants.scrollToElementByElement(regSaveButton);
        regSaveButton.click();
    }

    public void clickPXUserSearchButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(pxUserSearchButton);

    }

    public void clickExpertModelsFromAvailableGroups() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(expertModelsAvailableGroup);
    }

    public void clickAddArrowButtonToAddGroup() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(groupAddButton);
    }

    public void verifyUserIsOnAddPXUserPage(){
    	Assert.assertEquals(addUserHeader.isDisplayed(), true);
    	Assert.assertEquals(correctAllErrorsMessage.isDisplayed(), true);
    }
    


    public void verifyDjangoLogInPage() {
    	WaitTypes.waitForElementVisibilityByWebElement(DjangoLogInPageVerification);
        Assert.assertEquals(DjangoLogInPageVerification.isDisplayed(), true);
    }

    public void verifyDjangoHomePage() {
    	WaitTypes.waitForElementVisibilityByWebElement(DjangoLogHomePageVerification);
    	Assert.assertEquals(DjangoLogHomePageVerification.isDisplayed(), true);
    	//DjangoLogHomePageVerification.isDisplayed();
    }

    public void clickPXUserLink() throws Exception {
    	WaitTypes.clickWhenReadyByElement(pXUserLink);
    }

    public void verifyPleaseCorrectTheErrorBelowIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(correctAllErrorsMessage, "Please correct the error below.");
    }

    public void verifyFirstNameErrorMessageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(firstnameRequiredError, "This field is required.");
    }

    public void verifyLastNameErrorMessageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(lastnameRequiredError, "This field is required.");
    }

    public void verifyEmailErrorMessageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(emailRequiredError, "This field is required.");
    }
    
    public void verifyOrgErrorMessageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(orgRequiredError, "This field is required.");
    }

    public void verifyPasswordErrorMessageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(pwdRequiredError, "This field is required.");
    }
    
    public void verifyConfirmPasswordErrorMessageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(confirmPwdRequiredError, "This field is required.");
    }
    

    public void verifyLoggedOut() {
    	WaitTypes.waitForElementVisibilityByWebElement(DjangoLogOutPageVerification);
        Assert.assertEquals(DjangoLogOutPageVerification.isDisplayed(), true);
    }

    public void verifyChangesAreSave() {
    	WaitTypes.waitForElementVisibilityByWebElement(saveSuccessErrorMessage);
        //saveSuccessErrorMessage.isDisplayed();
    	Assert.assertEquals(saveSuccessErrorMessage.isDisplayed(), true);
    }

    public void verifyUserDeleteMessageQAUserProfileIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(userDeleteMessage, "The px user \"qa@cremelabs.com\" was deleted successfully.");
    }

    public void verifyUserDeleteMessageImportUserProfileIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(userDeleteMessage, "The px user \"importTest@cremelabs.com\" was deleted successfully.");
    }

    public void verifyUserCreateMessageIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(userCreateMessage, "The px user \"qa@cremelabs.com\" was added successfully. You may edit it again below.");
    }

    public void verifyExpertModelsInChosenGroupsIsDisplayed() throws Exception {
        AssertType.assertTrueIsDisplayedByText(expertModelsChosenGroups, "Expert Models");
    }

    public void selectOrganisation(String s) {
        Select dpt = new Select(orgDropDown);
        dpt.selectByVisibleText(s);
    }

    public void closeWebBrowser() {
        driver.close();
    }






    public String getListOfUserProfileDepartmentInAPI() {
        List<WebElement> userProfileFirstName = (List<WebElement>) driver.findElements(By.cssSelector(".field-first_name"));

        List<String> APIUserFirstName = new ArrayList<String>();

        for (WebElement allListFirstName : userProfileFirstName) {
            APIUserFirstName.add(allListFirstName.getText());
        }

        List<WebElement> userProfileLastName = (List<WebElement>) driver.findElements(By.cssSelector(".field-last_name"));
        List<String> APIUserLastName = new ArrayList<String>();


        for (WebElement allListLastName : userProfileLastName) {
            APIUserLastName.add(allListLastName.getText());
        }

        //combine List Array
        List<String> combinedList = combineLists(APIUserFirstName, APIUserLastName);


        //convert List Array to string
        String listString = "";

        for (String s : combinedList)
        {
            listString += s + "\n";
        }

//        System.out.println(listString);


        return listString;

    }

    public <T> List<T> combineLists(List<T> list1, List<T> list2) {
        // Assuming the inputs are both of the same size and has a string representation
        ArrayList<T> combinedList = new ArrayList<T>();
        for(int counter=0; counter < list1.size();counter++) {
            combinedList.add ((T) (list1.get(counter) + " " + list2.get(counter)));
        }
        return combinedList;
    }

    public String getListOfUserProfileDepartmentInEM() {
        List<WebElement> deptUserProfile = driver.findElements(By.xpath(".//div[contains(@class,  'team-username')]"));
        String EMUserProfileName = "";
        for (WebElement allList : deptUserProfile) {
            EMUserProfileName = allList.getText();
        }
        return EMUserProfileName;
    }

    public void verifyDeptListAreEqual() {
        getListOfUserProfileDepartmentInEM().compareTo(getListOfUserProfileDepartmentInAPI());

    }
    
    
    public void createUserProfile(String profileToCreate) throws Exception{
    	 	//logger = report.startTest("Creating Test User");

	        goToAPIURL(CredentialsData.getApiURL());
	        //logger.log(LogStatus.INFO, "Navigated to Django API URL");
	        verifyDjangoLogInPage();
	        //logger.log(LogStatus.PASS, "Verify LogIn Page");

	        fillInEmailTextBox(CredentialsData.getApiAdminUser());
	        fillInPasswordTextBox(CredentialsData.getApiAdminPassword());
	        //logger.log(LogStatus.INFO, "Successfully enter Valid email/Valid password");
	        clickLogInButton();
	        //logger.log(LogStatus.INFO, "Successfully click on Login button");

	        clickPXUserLink();
	        //logger.log(LogStatus.INFO, "Successfully click on Px Users Link");
	        verifyDjangoHomePage();
	        //logger.log(LogStatus.PASS, "User is on PX User to Change Page");

	        clickAddPXUserLink();
	        //logger.log(LogStatus.INFO, "Successfully click on Add Px User Link");
	        fillInRegFirstNameTextBox(CredentialsData.getFirstName());
	        fillInRegLastNameTextBox(CredentialsData.getLastName());
	        fillInRegEmailTextBox(profileToCreate);
	        //logger.log(LogStatus.INFO, "Successfully filled in first, last name & email");
	        
	        selectOrganisation(CredentialsData.getQAOrganisation());
	        //logger.log(LogStatus.INFO, "Successfully Select Creme Global QA Dept form dropdown");
	        fillInRegPasswordTextBox(CredentialsData.getUserPassword());
	        fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
	        clickIsActiveCheckBox();
	        //logger.log(LogStatus.INFO, "Successfully filled in password and password confirmation");

	        clickOnRegSaveButton();
	        //logger.log(LogStatus.INFO, "Successfully scroll to Save Link Button and Click Save");
	        logOut();
	        //logger.log(LogStatus.INFO, "Successfully Click Log Out button");
	        verifyLoggedOut();
	        //logger.log(LogStatus.PASS, "Successfully Logged Out");	        
	        //report.endTest(logger);
    }


    public void deleteUserProfile(String profileToDelete) throws Exception{
    	 	driver.get(CredentialsData.getApiURL());
	        //logger.log(LogStatus.INFO, "Navigated to Django API URL");

	        verifyDjangoLogInPage();
	        //logger.log(LogStatus.PASS, "Verified user is on API LogIn Page");
	        fillInEmailTextBox(CredentialsData.getApiAdminUser());
	        fillInPasswordTextBox(CredentialsData.getApiAdminPassword());
	        //logger.log(LogStatus.INFO, "Enter API admin email & password");
	        clickLogInButton();
	        //logger.log(LogStatus.INFO, "Clicked on Login button");
	        verifyDjangoHomePage();
	        //logger.log(LogStatus.PASS, "Successfully navigated to Django API Home Page");
	 
	        clickPXUserLink();
	        
	        findAndSelectProfile(profileToDelete);
	        //logger.log(LogStatus.INFO, "Clicked on test user email link");
	        clickDeleteButton();
	        //logger.log(LogStatus.INFO, "Clicked on Delete Button");
	        clickYesImSureButton();
	        //logger.log(LogStatus.INFO, "Confirmed & Clicked on Yes I'm sure Button");
	        logOut();
	        //logger.log(LogStatus.INFO, "Clicked on Log Out button");
	        verifyLoggedOut();
	        //logger.log(LogStatus.PASS, "Verified log out was successful");
    	
    }
    
    
    
    public void createInActiveUserProfile(String profileToCreate) throws Exception{
    		//logger = report.startTest("Creating Test User");

        	goToAPIURL(CredentialsData.getApiURL());
        	//logger.log(LogStatus.INFO, "Navigated to Django API URL");
        	verifyDjangoLogInPage();
        	//logger.log(LogStatus.PASS, "Verify LogIn Page");

        	fillInEmailTextBox(CredentialsData.getApiAdminUser());
        	fillInPasswordTextBox(CredentialsData.getApiAdminPassword());
        	//logger.log(LogStatus.INFO, "Successfully enter Valid email/Valid password");
        	clickLogInButton();
        	//logger.log(LogStatus.INFO, "Successfully click on Login button");

        	clickPXUserLink();
        	//logger.log(LogStatus.INFO, "Successfully click on Px Users Link");
        	verifyDjangoHomePage();
        	//logger.log(LogStatus.PASS, "User is on PX User to Change Page");

        	clickAddPXUserLink();
        	//logger.log(LogStatus.INFO, "Successfully click on Add Px User Link");
        	fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        	fillInRegLastNameTextBox(CredentialsData.getLastName());
        	fillInRegEmailTextBox(profileToCreate);
        	//logger.log(LogStatus.INFO, "Successfully filled in first, last name & email");
        	
        	
        	selectOrganisation(CredentialsData.getQAOrganisation());
        	//logger.log(LogStatus.INFO, "Successfully Select Creme Global QA Dept form dropdown");
        	fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        	fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
        	Assert.assertTrue(!isActiveButton.isSelected());
        	//logger.log(LogStatus.INFO, "Successfully filled in password and password confirmation");

        	clickOnRegSaveButton();
        	//logger.log(LogStatus.INFO, "Successfully scroll to Save Link Button and Click Save");
        	logOut();
        	//logger.log(LogStatus.INFO, "Successfully Click Log Out button");
        	verifyLoggedOut();
        	//logger.log(LogStatus.PASS, "Successfully Logged Out");	        
        	//report.endTest(logger);
    	
    }
    
    
    public void toggleUserActiveStatus(String profileToUpdate) throws Exception{
    		goToAPIURL(CredentialsData.getApiURL());
    		
    		verifyDjangoLogInPage();
    		//logger.log(LogStatus.PASS, "Verify LogIn Page");

    		fillInEmailTextBox(CredentialsData.getApiAdminUser());
    		fillInPasswordTextBox(CredentialsData.getApiAdminPassword());
    		//logger.log(LogStatus.INFO, "Successfully enter Valid email/Valid password");
    		clickLogInButton();
    		//logger.log(LogStatus.INFO, "Successfully click on Login button");

    		clickPXUserLink();
    		//logger.log(LogStatus.INFO, "Successfully click on Px Users Link");
    		verifyDjangoHomePage();
    		findAndSelectProfile(profileToUpdate);
    		// toggle the state by clicking
    		clickIsActiveCheckBox();
    		clickOnSave();
    		logOut();
	        //logger.log(LogStatus.INFO, "Clicked on Log Out button");
	        verifyLoggedOut();
	        //logger.log(LogStatus.PASS, "Verified log out was successful");
    		
    		
    }
    
    
    public void createUserInAnotherOrg(String profileToCreate) throws Exception{
	 	//logger = report.startTest("Creating Test User");

        goToAPIURL(CredentialsData.getApiURL());
        //logger.log(LogStatus.INFO, "Navigated to Django API URL");
        verifyDjangoLogInPage();
        //logger.log(LogStatus.PASS, "Verify LogIn Page");

        fillInEmailTextBox(CredentialsData.getApiAdminUser());
        fillInPasswordTextBox(CredentialsData.getApiAdminPassword());
        //logger.log(LogStatus.INFO, "Successfully enter Valid email/Valid password");
        clickLogInButton();
        //logger.log(LogStatus.INFO, "Successfully click on Login button");

        clickPXUserLink();
        //logger.log(LogStatus.INFO, "Successfully click on Px Users Link");
        verifyDjangoHomePage();
        //logger.log(LogStatus.PASS, "User is on PX User to Change Page");

        clickAddPXUserLink();
        //createNewOrg(organisationToCreate);
        //logger.log(LogStatus.INFO, "Successfully click on Add Px User Link");
        fillInRegFirstNameTextBox(CredentialsData.getFirstName());
        fillInRegLastNameTextBox(CredentialsData.getLastName());
        fillInRegEmailTextBox(profileToCreate);
        //logger.log(LogStatus.INFO, "Successfully filled in first, last name & email");
        
        selectOrganisation(CredentialsData.getOtherOrganisation());
        //logger.log(LogStatus.INFO, "Successfully Select qaEmptyOrg from dropdown");
        fillInRegPasswordTextBox(CredentialsData.getUserPassword());
        fillInRegPasswordConfirmationTextBox(CredentialsData.getUserPassword());
        clickIsActiveCheckBox();
        //logger.log(LogStatus.INFO, "Successfully filled in password and password confirmation");

        clickOnRegSaveButton();
        //logger.log(LogStatus.INFO, "Successfully scroll to Save Link Button and Click Save");
        logOut();
        //logger.log(LogStatus.INFO, "Successfully Click Log Out button");
        verifyLoggedOut();
        //logger.log(LogStatus.PASS, "Successfully Logged Out");	        
        //report.endTest(logger);
    } 
    
    


}
