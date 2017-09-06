package Page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util.EMDriverSingleton;
import Util.WaitTypes;
import org.testng.Assert;

public class ResultsViewerPage {
	final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

    // Report page elements
    @FindBy(id = "toolbar")
    WebElement toolbar;
    
    @FindBy(className = "go-back")
    WebElement goBackButton;
    
    @FindBy(id = "report")
    WebElement reportBody;
    
    @FindBy(id = "tabs-report")
    WebElement reportTab;
    
    @FindBy(id = "tabs-table")
    WebElement qaResultsTab;
    
    @FindBy(id = "tabs-inputs")
    WebElement inputsTab;
    
    
       
    // Initialize Page Elements
    public ResultsViewerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    // Action methods
    public void clickGoBack() throws Exception{
    	WaitTypes.clickWhenReadyByElement(goBackButton);
    }
    
    
    // Verification methods
    public void verifyResultsViewerIsDisplayed(){
    	WaitTypes.waitForElementVisibilityByWebElement(toolbar);
    	Assert.assertEquals(toolbar.isDisplayed(), true);
    	Assert.assertEquals(reportBody.isDisplayed(), true);
    	Assert.assertEquals(reportTab.isDisplayed(), true);
    	Assert.assertEquals(qaResultsTab.isDisplayed(), true);
    	Assert.assertEquals(inputsTab.isDisplayed(), true);
    	
    }
    
    
}
