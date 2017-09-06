/**
 * 
 */
package Util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author will.okanumeh
 *
 */
public class EMDriverSingleton {

	private static WebDriver driver = null;

	/**
	 * 
	 */
	private EMDriverSingleton() {
		// Suppress constructor for Singleton
	}

	public static WebDriver getFireFoxInstanceDriver() {
		if(null == driver) {
			driver = new FirefoxDriver();
		}
		return driver;
	}


	public static WebDriver getChromeInstanceDriver() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\Automation\\git\\expert-models-regression-suite\\Browser Drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "Browser Drivers/chromedriver.exe");
		if(null == driver) {
			driver = new ChromeDriver();
		}
		return driver;
	}

	public static WebDriver getIEdgeInstanceDriver() {
		//System.setProperty("webdriver.edge.driver", "C:\\Workspace\\Automation\\git\\expert-models-regression-suite\\Browser Drivers\\MicrosoftWebDriver.exe");
		System.setProperty("webdriver.edge.driver", "Browser Drivers/MicrosoftWebDriver.exe");
		if(null == driver) {
			driver = new EdgeDriver();
		}
		return driver;
	}
}
