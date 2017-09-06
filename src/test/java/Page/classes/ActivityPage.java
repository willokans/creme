package Page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util.EMDriverSingleton;
import Util.WaitTypes;

public class ActivityPage {
	
	final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
    
    @FindBy(xpath = "//md-content[@id='activity-wrapper']//h2[contains(text(),'Activity')]")
    WebElement pageTitle;
    
 // Initialize Page Elements
    public ActivityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void verifyActivityPageIsDisplayed(){
    	WaitTypes.waitForElementVisibilityByWebElement(pageTitle);
    	pageTitle.isDisplayed();
    }
    

}
