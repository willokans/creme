package Page.classes;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Util.EMDriverSingleton;
import Util.WaitTypes;

public class HelpPage {
	final static int WAIT_TIME_OUT=50;
    
	private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
	
	@FindBy(id = "page-header")
	WebElement helpHeader;
	
	// Initialize Page Elements
    public HelpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
  
  
    public void verifyUserIsOnHelpPage(){
    	String parentHandle = driver.getWindowHandle();  	
    	Set<String> handles = driver.getWindowHandles();
    	
    	for(String handle: handles){
    		if(!handle.equals(parentHandle)){
    			driver.switchTo().window(handle);
    			WaitTypes.waitForElementVisibilityByWebElement(helpHeader);
    			Assert.assertEquals(helpHeader.isDisplayed(), true);
    			driver.close();
    			break;
    		}
    	}
    	driver.switchTo().window(parentHandle);
    }




}