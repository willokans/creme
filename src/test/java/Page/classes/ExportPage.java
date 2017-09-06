package Page.classes;

import Util.EMDriverSingleton;
import Util.WaitTypes;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;



/**
 * Created by will.okamuneh on 11/10/2016.
 */
public class ExportPage {


    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @FindBy(xpath = "//div[@id='main-wrapper']//h2[text()='Export']")
    WebElement exportTextLabel;

    @FindBy(xpath = "//form[@id='exportForm']//p[text()='Columns are delimited by:']")
    WebElement columnsDelimitedByTextLabel;

    @FindBy(xpath = "//form[@id='exportForm']//p[text()='Columns are terminated by:']")
    WebElement columnsTerminatedByTextLabel;

    @FindBy(xpath = "//form[@id='exportForm']//p[text()='Columns are quoted by:']")
    WebElement columnsQuotedByTextLabel;

    @FindBy(id = "export-option--comma")
    WebElement commaRadioButton;

    @FindBy(id = "export-option--semicolon")
    WebElement semiColonRadioButton;

    @FindBy(id = "export-option---tab")
    WebElement tabRadioButton;

    @FindBy(id = "export-option-windows-rn")
    WebElement windowsRadioButton;

    @FindBy(id = "export-option-unix-n")
    WebElement unixRadioButton;

    @FindBy(id = "export-option-quote-")
    WebElement quoteRadioButton;

    @FindBy(id = "export-option-single-quote-")
    WebElement singleQuoteRadioButton;

    @FindBy(xpath = "//button[@analytics-event='Download']")
    WebElement downloadTheZipFileButton;

    @FindBy(xpath = "//button//translate[text()='Go back']")
    WebElement goBackButton;




    public ExportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    public void verifyExportLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(exportTextLabel);
    }

    public void verifyColumnsDelimitedByIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(columnsDelimitedByTextLabel);
    }

    public void verifyColumnsTerminatedByIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(columnsTerminatedByTextLabel);
    }

    public void verifyColumnsQuotedByIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(columnsQuotedByTextLabel);
    }

    public void verifyCommaRadioButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(commaRadioButton);
    }

    public void verifySemiColonRadioButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(semiColonRadioButton);
    }

    public void verifyTabRadioButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(tabRadioButton);
    }

    public void verifyWindowRadioButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(windowsRadioButton);
    }

    public void verifyUnixRadioButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(unixRadioButton);
    }

    public void verifyQuoteRadioButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(quoteRadioButton);
    }

    public void verifySingleQuoteRadioButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(singleQuoteRadioButton);
    }

    public void verifyDownloadButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(downloadTheZipFileButton);
    }

    public void verifyGoBackButtonIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
    }





    public void clickCommaRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(commaRadioButton);
    }

    public void clickSemiColonRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(semiColonRadioButton);
    }

    public void clickTabRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(tabRadioButton);
    }

    public void clickWindowRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(windowsRadioButton);
    }

    public void clickUnixRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(unixRadioButton);
    }

    public void clickQuoteRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(quoteRadioButton);
    }

    public void clickSingleQuoteRadioButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(singleQuoteRadioButton);
    }

    public void clickDownloadTheZipFileButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(downloadTheZipFileButton);
        Thread.sleep(5000);
    }
    
    public void DownloadTheZipFileDialogBox() throws AWTException, IOException, InterruptedException {
        //Runtime.getRuntime().exec("C:\\Workspace\\Automation\\git\\expert-models-regression-suite\\AutoITFilePicker\\ExportDownload.exe");
        Runtime.getRuntime().exec("AutoITFilePicker/ExportDownload.exe");

    }

    public void clickGoBackButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(goBackButton);
    }
    
    public void verifyActionButtonsAreClickable(){
    	WaitTypes.waitUntilElementIsClickable(goBackButton);
    	WaitTypes.waitUntilElementIsClickable(downloadTheZipFileButton);   	
    }
    
    public void verifyDeafultStatusesForOptions(){
    	WaitTypes.waitUntilElementIsClickable(commaRadioButton);	
    	Assert.assertEquals(commaRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(semiColonRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(tabRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(windowsRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(unixRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(quoteRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(singleQuoteRadioButton.getAttribute("aria-checked"), "false"); 
    }
    
    public void verifyToggledStatusesForOptions(){
    	WaitTypes.waitUntilElementIsClickable(commaRadioButton);	
    	Assert.assertEquals(commaRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(semiColonRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(tabRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(windowsRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(unixRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(quoteRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(singleQuoteRadioButton.getAttribute("aria-checked"), "true"); 
    }
    
    public void verifyUpdatedStatusesForOptions(){
    	WaitTypes.waitUntilElementIsClickable(commaRadioButton);	
    	Assert.assertEquals(commaRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(semiColonRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(tabRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(windowsRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(unixRadioButton.getAttribute("aria-checked"), "false");
    	Assert.assertEquals(quoteRadioButton.getAttribute("aria-checked"), "true");
    	Assert.assertEquals(singleQuoteRadioButton.getAttribute("aria-checked"), "false"); 
    }

}
