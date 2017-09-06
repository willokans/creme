package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.jna.platform.win32.COM.Dispatch.ByReference;

public class AssertType {

	
	
	final static int WAIT_TIME_OUT=50;
	  static private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
    
    
	
    /**Assert Element is displayed
	 * @param
	 * @param locator
	 * @return
	 */
    public static void assertIsDisplayed(By locator) throws Exception {

        WaitTypes.waitForElementVisibility(locator);

        Assert.assertTrue(driver.findElement(locator).isDisplayed());
    }
    
    
    /**Assert Element is NOT displayed
	 * @param
	 * @param locator
	 * @return
	 */
    public static void assertEqualsNotDisplayed(By locator) throws Exception {

        //WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //Assert.assertEquals(0, driver.findElements(locator).size());
    	Assert.assertEquals(driver.findElements(locator).size(), 0);
    }
    
    
    /**Assert Text displayed is same
	 * @param
	 * @param locator
	 * @return
	 */
    public static void assertTrueIsDisplayed(By locator, String s) throws Exception {


        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT);

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Assert.assertTrue(driver.findElement(locator).isDisplayed(), s);
    }


    public static void assertTrueIsDisplayedByText(WebElement element, String s) throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(element);
        Assert.assertEquals(element.getText(), s);
    }

}
