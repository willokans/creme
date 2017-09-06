package Util;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WaitTypes {
	
	
	final static int WAIT_TIME_OUT_GENERAL=50;
	final static int WAIT_TIME_OUT_JOBS=600;
	static private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();

	
	
	
	/**Get element when it's ready
	 * @param
	 * @param locator
	 * @return
	 */
	 public static WebElement getWhenVisible(By locator) {
	    	WebElement element = null;
	    	WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
	    	element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return element;
	    	
	    }
	 
	 
	 /**Click the element once it's ready and click
		 * @param locator
		 * @return
		 */
	    public static void clickWhenReady(By locator) {
	    	WebElement element = null;
	    	WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
	    	element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	    	element.click();
	    }



	public static boolean clickWhenReadyByElement(WebElement element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		Thread.sleep(2000);
		element.click();
		return false;
	}
	
	public static boolean waitUntilElementIsClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		return false;
	}


    public static void verifiedWhenReadyByElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
    }


	public static void waitForEmailByElement(WebElement element) throws Exception {
		Thread.sleep(3000);
		if (element.isDisplayed()) {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} else {
			WebDriverWait wait = new WebDriverWait(driver, 100);
            driver.navigate().refresh();
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}


	}
	        
	    
	 /**Wait until visibility of element

		 * @param locator
		 * @return
		 */
	    public static void waitForElementVisibility(By locator) {
	        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }



    public static boolean waitForElementVisibilityByWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
        wait.until(ExpectedConditions.visibilityOf(element));
		return false;
	}

	public static boolean waitForElementVisibilityByJobWebElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_JOBS);
		wait.until(ExpectedConditions.visibilityOf(element));
		return false;
	}
    
    public static boolean waitForCSVFileAvailability(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_JOBS);
        //wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
		return false;
	}
    
    public static boolean waitForJobCompletionByWebElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_JOBS);
		wait.until(ExpectedConditions.attributeToBe(element, "job-status", "completed"));
		return false;
	}
	    
	       
	 /**Poll for element until the element is present or timeout
		 * @param locator
		 * @return
		 */
	    public static WebElement fluentWait(final By locator) {
	        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(500, TimeUnit.SECONDS)
	                .pollingEvery(5, TimeUnit.SECONDS)
	                .ignoring(NoSuchElementException.class);

	        WebElement element = wait.until(new Function<WebDriver, WebElement>(){
	                @Override
	            public WebElement apply(WebDriver driver) {
	                return driver.findElement(locator);
	            }
	        });
	        return element;
	    }
	    
	    
	    

	public static WebElement flientWaitFindElement(final WebDriver driver, final By locator, final int timeoutSeconds) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeoutSeconds, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				return driver.findElement(locator);
			}
		});
	}
	
	

//    public static WebElement fluentWait(WebElement element) {
//
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(30, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);
//
//		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
//			@Override
//			public WebElement apply(WebDriver driver) {
//				return WebElement element;
//			}
//		});
//        return element;
//    }
		


}
