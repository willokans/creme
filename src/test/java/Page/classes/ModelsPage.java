package Page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util.EMDriverSingleton;
import Util.WaitTypes;

public class ModelsPage {
	
	final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    @FindBy(id = "new-model-btn")
    WebElement newModelButton;
    
    @FindBy(xpath = "//md-content[@id='models']//h2[contains(text(),'Models')]")
    WebElement pageTitle;
    
    
    // Initialize Page Elements
    public ModelsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void verifyModelsPageIsDisplayed(){
    	WaitTypes.waitForElementVisibilityByWebElement(pageTitle);
    	pageTitle.isDisplayed();
    	WaitTypes.waitForElementVisibilityByWebElement(newModelButton);
    	newModelButton.isDisplayed();
    	
    }
    
}