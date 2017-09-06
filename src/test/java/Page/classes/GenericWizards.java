package Page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util.EMDriverSingleton;
import Util.WaitTypes;

public class GenericWizards {
	final static int WAIT_TIME_OUT=50;
    
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
    
    // Generic wizard header elements
    @FindBy(id = "wiz-header-run")
    WebElement runButton;
    
    @FindBy(id = "wiz-header-save")
    WebElement saveAndCloseButton;
    
    @FindBy(id = "wiz-header-delete")
    WebElement trashButton;
    
    // QA tool box model elements
    @FindBy(xpath = "//form//button[@file-picker-ref='cat1_file']")
    WebElement chooseFileCat1Button;
    
    @FindBy(xpath = "//form//button[@file-picker-ref='cat2_file']")
    WebElement chooseFileCat2Button;
    
    		
    // Initialize Page Elements
    public GenericWizards(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }	
    
    // Methods - General Actions -header
    public void clickSaveAndCloseAssessment() throws Exception{
    	Thread.sleep(2000);
     	WaitTypes.waitUntilElementIsClickable(saveAndCloseButton);  	
    	saveAndCloseButton.click();
    }
    
    public void clickRunButton() throws Exception{
    	WaitTypes.waitUntilElementIsClickable(runButton);
    	WaitTypes.clickWhenReadyByElement(runButton);
    }
    
    public void trashAssessment() throws Exception{
    	WaitTypes.waitUntilElementIsClickable(trashButton);
    	WaitTypes.clickWhenReadyByElement(trashButton);
    }
    
    public void verifyGenericWizardOpened(){
    	WaitTypes.waitForElementVisibilityByWebElement(runButton);
    	runButton.isDisplayed();
    	WaitTypes.waitForElementVisibilityByWebElement(saveAndCloseButton);
    	saveAndCloseButton.isDisplayed();
    	WaitTypes.waitForElementVisibilityByWebElement(trashButton);
    	trashButton.isDisplayed();
    }
    
    
    // Methods for QA tool box model
    public void clickOnCat1ChooseFilesButton() throws Exception{
    	WaitTypes.clickWhenReadyByElement(chooseFileCat1Button);
    }
    
    public void clickOnCat2ChooseFilesButton() throws Exception{
    	WaitTypes.clickWhenReadyByElement(chooseFileCat2Button);
    }
    
}
