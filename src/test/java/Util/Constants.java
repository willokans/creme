package Util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by will.okanumeh on 6/3/2016.
 */
public class Constants {

    static private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
    
    public static String tableName = "QA Test Table";
    public static String folderName = "QA Test Folder";
    public static String axName = "QA Test Assessment";
    public static String matchedLabel = "Matched";
    public static String requiredLabel = "Required";
    

    public static String captureScreenshot(String screenshotName)
    {

        try {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            //String dest = "C:\\Workspace\\Automation\\Automation Results\\Screenshots\\"+screenshotName+".png";
            String dest = "test-output/automation-results/expert-models/screenshots/"+screenshotName+".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }


    public static void readOnly(WebElement element) {
        element.getAttribute("readonly");
        Assert.assertNotNull("readonly");
    }


    public static void scrollToElement(By locator) {

        WebElement element = driver.findElement(locator);
        Coordinates coordinates = ((Locatable)element).getCoordinates();
        coordinates.inViewPort();
    }

    public static void scrollToElement2(WebElement element) throws InterruptedException {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }


    public static void scrollToElementByElement(WebElement element) {

        Coordinates coordinates = ((Locatable)element).getCoordinates();
        coordinates.inViewPort();
    }


    public static void
    upLoadFileViaWindowsPrompt(String filePath) throws AWTException, InterruptedException {

        StringSelection ss = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();

        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        //Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_V);
        //Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(4000);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }


    public static void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
    }


    public static void dropDownSelectByElementByIndex(WebElement element, int index) {

        Select dpt = new Select(element);
        dpt.selectByIndex(index);

    }


    public static void dropDownSelectByElementByNextOption(WebElement element) {

        Select dpt = new Select(element);
        dpt.getFirstSelectedOption();


    }


    public static void dropDownSelectByElementByValue(WebElement element, String value) {
        Select dpt = new Select(element);
        dpt.selectByValue(value);
    }
    
   /* public static void assertDropDownOptionIsUnavailableByElementByValue(WebElement element, String value){
    	Select dpt = new Select(element);
    	try {
    		dpt.selectByValue(value);
		} catch (Exception e) {
			boolean status = e.getMessage().contains("Cannot locate option with value: "+value);
			Assert.assertTrue(status);
		}	
    }*/
    
    public static void assertDropDownOptionIsNotPresentByElementByValue(WebElement element, String value){
    	Select dpt = new Select(element);
    	
    	List<WebElement> allOptions = dpt.getOptions();
    	for(WebElement option : allOptions){
    		//System.out.println(option.getAttribute("value"));
    		//System.out.println(value);
    		Assert.assertFalse(option.getAttribute("value") == value);  		
    	} 	
    }


    public static void rowCount(WebElement element) {

        element.getSize();
    }


    public static void clearBrowserCache() throws InterruptedException {
        driver.manage().deleteAllCookies();
        Thread.sleep(5000);
    }


    public static void dropDownSelect(By locator, String s) {

        Select dpt = new Select(driver.findElement(locator));
        dpt.selectByVisibleText(s);
    }


    public static void  clearTextInTextBox (By locator) {

        WebElement element = driver.findElement(locator);
        element.clear();
    }


    public static void click(By locator) {
    	WaitTypes.waitForElementVisibility(locator);
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public static void clickByElement(WebElement element) {
        WaitTypes.waitForElementVisibilityByWebElement(element);
        element.click();
    }


    public static  void clearTextInTextBox(WebElement element) throws InterruptedException{
    	WaitTypes.clickWhenReadyByElement(element);
        element.clear();
    }


    public static void hooverClick(WebDriver driver, String s, String s1) {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(s)));

        Actions action = new Actions(driver);
        WebElement selectChemTable = driver.findElement(By.xpath(s));
        action.moveToElement(selectChemTable).moveToElement(driver.findElement(By.xpath(s1))).click().build().perform();
    }

    public static void hooverClickElement(WebElement element1, WebElement element2) {

        WaitTypes.waitForElementVisibilityByWebElement(element1);

        Actions action = new Actions(driver);
        action.moveToElement(element1).moveToElement(element2).click().build().perform();
    }

    public static void mouseHoover(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }


    public static void doubleClickOnElement(WebElement element) {
        WaitTypes.waitForElementVisibilityByWebElement(element);
        Actions action = new Actions(driver);
        //Double click
        action.doubleClick(element).perform();
    }


    public  static void dragAndDropHTML5 () throws IOException, InterruptedException {

        try {
        final String JQUERY_LOAD_SCRIPT = "/src/test/Util/jquery_load_helper.js";
        String jQueryLoader = readFile(JQUERY_LOAD_SCRIPT);

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript(
                jQueryLoader /* , http://localhost:8080/jquery-1.7.2.js */);

        // ready to rock
                js.executeScript("jQuery(function($) { " + " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); "
                                + " }); ");


        String filePath = "/src/test/Util/drag_and_drop_helper.js";


            String source = "section#wrapper article ul li:nth-child(4) a";
            String target =  "section#wrapper article div"; //#bin";



        StringBuffer buffer = new StringBuffer();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while((line = br.readLine())!=null)
            buffer.append(line);

        String javaScript = buffer.toString();

        javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
        ((JavascriptExecutor)driver).executeScript(javaScript);

        Thread.sleep(1000);
        source = "section#wrapper article ul li:nth-child(2) a";
        javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
        ((JavascriptExecutor)driver).executeScript(javaScript);

        Thread.sleep(1000);
        source = "section#wrapper article ul li:nth-child(1) a";
        javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
        ((JavascriptExecutor)driver).executeScript(javaScript);

        Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //https://github.com/vikramvi/Selenium-Java/commit/a1354ca5854315fded8fc80ba24a4717927d08c7
    }


    private static String readFile(String file) throws IOException {

        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(file);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }

    }

    public static void rightClickAndSelectElement(WebElement element1, WebElement element2) {
        Actions action= new Actions(driver);
        action.contextClick(element1).moveToElement(element2).click().build().perform();
    }

    public static void rightClick(WebElement element) {
        Actions action= new Actions(driver);
        action.contextClick(element).build().perform();
    }


    public static void enterDataIntoTableCell(WebElement locator, String element) throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(locator);
        Constants.doubleClickOnElement(locator);
        //locator.clear();
        locator.sendKeys(element);

    }


    public static void javascriptClickWebElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }





}