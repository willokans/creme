package Page.classes;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Util.AssertType;
import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;

/**
* Created by will.okamuneh on 7/22/2016. Re-factored by alan.sheehy 30/01/2017
*/
public class ImportPage {


    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
    String blankEntry = "";
    String noDDSelectionStatus = "ng-pristine ng-untouched ng-valid ng-empty";
    String selectedDDOptionStatus = "ng-not-empty ng-dirty ng-valid-parse";

    // Importing matching page elements
    @FindBy(xpath = ".//*[@id='matching-form']/h4")
    WebElement attemptImportFieldErrorMessage;

    @FindBy (id = "field-0")
    WebElement matchingDropdownIncluded;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[1]/td[3]")
    WebElement includedMatchingStatusLabel;

    @FindBy (id = "field-1")
    WebElement matchingDropdownCategory;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[2]/td[3]")
    WebElement categoryMatchingStatusLabel;
    
    @FindBy (id = "field-2")
    WebElement matchingDropdownItem;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[3]/td[3]")
    WebElement itemMatchingStatusLabel;
    
    @FindBy (id = "field-3")
    WebElement matchingDropdownDataPoints;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[4]/td[3]")
    WebElement dataPointsMatchingStatusLabel;
    
    @FindBy (id = "field-4")
    WebElement matchingDropdownComment;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[5]/td[3]")
    WebElement commentMatchingStatusLabel;
    
    @FindBy (id = "field-5")
    WebElement matchingDropdownNumericTest;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[6]/td[3]")
    WebElement numericTestMatchingStatusLabel;
    
    @FindBy (id = "field-6")
    WebElement matchingDropdownNumericDistTest;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[7]/td[3]")
    WebElement numericDistTestMatchingStatusLabel;
    
    @FindBy (id = "field-7")
    WebElement matchingDropdownIntegerTest;
    
    @FindBy(xpath = "//table[@id='matching-table']/tbody/tr[8]/td[3]")
    WebElement integerTestMatchingStatusLabel;

    @FindBy(xpath = "//table[@id='matching-table']//thead//th[1]")
    WebElement matchingPageFirstColumn;
    
    @FindBy(xpath = "//table[@id='matching-table']//thead//th[2]")
    WebElement matchingPageSecondColumn;
    
    // Delete valid 7 invalid once included in Editor (methods also)
    @FindBy(xpath = "//*[text()='Invalid table']")
    WebElement invalidTableImportTableLabel;

    @FindBy(xpath = "//*[text()='Valid table']")
    WebElement validTableImportTableLabel;

 
    
    // Import page elements
    @FindBy(xpath = "//h2//translate[text()='Import Table']")
    WebElement importPageTitle;
    
    @FindBy(xpath = "//form[@id='import']//button//translate[contains(text(),'Browse Files')]")
    WebElement browseFilesButton;

    @FindBy(id = "em-import-table-name")
    WebElement tableNameTextBox;

    @FindBy(id = "em-import-app-type")
    WebElement chooseModelCategory;

    @FindBy(id = "em-import-table-type")
    WebElement chooseTableType;

    @FindBy(id = "em-import-quote-single")
    WebElement singleQuoteRadioButton;

    @FindBy(id = "em-import-quote-double")
    WebElement quoteRadioButton;

    @FindBy(id = "em-import-delimiter-comma")
    WebElement commaRadioButton;

    @FindBy(id = "em-import-delimiter-semicolon")
    WebElement semicolonRadioButton;

    @FindBy(id = "em-import-delimiter-tab")
    WebElement tabRadioButton;
    
    @FindBy(xpath = "//button[@class='md-accent md-raised cf-btn-l md-button md-ink-ripple']")
    WebElement importButton;
    
    @FindBy(xpath = "//button//translate[text()='Go back']")
    WebElement goBackButton;
    
    @FindBy(id = "em-import-app-type-qamodel")
    WebElement qaModelDropdownOption;

    @FindBy(id = "em-import-table-type-qadata")
    WebElement qaDataDropdownOption;

    @FindBy(id = "em-import-table-type-qacategory")
    WebElement qaCategoryDropdownOption;

    @FindBy(xpath = "//form[@id='import']//button//translate[contains(text(),'Remove')]")
    WebElement removeImportButton;
    
    @FindBy(xpath = "//form[@id='import']//button//translate[contains(text(),'Choose New')]")
    WebElement chooseNewButton;



    public ImportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    /*public void selectSubjectCodeFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownIncluded);

        Constants.dropDownSelectByElementByIndex(matchingDropdownIncluded, 1);
    }*/
    
    public void selectAndMatchIncludedFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownIncluded);
        //Constants.dropDownSelectByElementByIndex(matchingDropdownIncluded, 1);
        Constants.dropDownSelectByElementByValue(matchingDropdownIncluded, "Test_included");
    	
    }
    
    public void selectAndMatchCategoryFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownCategory);
        //Constants.assertDropDownOptionIsUnavailableByElementByValue(matchingDropdownCategory, "Test_included");
        Constants.assertDropDownOptionIsNotPresentByElementByValue(matchingDropdownCategory, "Test_included");
        Constants.dropDownSelectByElementByValue(matchingDropdownCategory, "Test_category");	
    }
    
    public void selectAndMatchItemFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownItem);
        Constants.assertDropDownOptionIsNotPresentByElementByValue(matchingDropdownItem, "Test_category");
        Constants.dropDownSelectByElementByValue(matchingDropdownItem, "Test_item");	
    }
    
    public void selectAndMatchDataPointsFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownDataPoints);
        Constants.assertDropDownOptionIsNotPresentByElementByValue(matchingDropdownDataPoints, "Test_item");
        Constants.dropDownSelectByElementByValue(matchingDropdownDataPoints, "Test_data_points");	
    }
    
    public void selectAndMatchCommentFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownComment);
        Constants.assertDropDownOptionIsNotPresentByElementByValue(matchingDropdownComment, "Test_data_points");
        Constants.dropDownSelectByElementByValue(matchingDropdownComment, "Test_comment");	
    }
    
    public void selectAndMatchNumericTestFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownNumericTest);
        Constants.assertDropDownOptionIsNotPresentByElementByValue(matchingDropdownNumericTest, "Test_comment");
        Constants.dropDownSelectByElementByValue(matchingDropdownNumericTest, "Test_numeric_test");	
    }
    
    public void selectAndMatchNumericDistTestFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownNumericDistTest);
        Constants.assertDropDownOptionIsNotPresentByElementByValue(matchingDropdownNumericDistTest, "Test_numeric_test");
        Constants.dropDownSelectByElementByValue(matchingDropdownNumericDistTest, "Test_numeric_distribution_test");	
    }
    
    public void selectAndMatchIntegerTestFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownIntegerTest);
        Constants.assertDropDownOptionIsNotPresentByElementByValue(matchingDropdownIntegerTest, "Test_numeric_distribution_test");
        Constants.dropDownSelectByElementByValue(matchingDropdownIntegerTest, "Test_integer_test");	
    }

    // Select by Index - First Option - Example
    public void selectCategoryFromDropDown () {
        WaitTypes.waitForElementVisibilityByWebElement(matchingDropdownCategory);
        Constants.dropDownSelectByElementByIndex(matchingDropdownCategory, 1);
    }
  
    public void selectQAModelFromChooseModelCategory() throws InterruptedException {
    	WaitTypes.clickWhenReadyByElement(chooseModelCategory);
    	WaitTypes.clickWhenReadyByElement(qaModelDropdownOption);
        //WaitTypes.waitForElementVisibilityByWebElement(chooseModelCategory);
        //Constants.javascriptClickWebElement(chooseModelCategory);
        //Constants.javascriptClickWebElement(qaModelDropdownOption);

    }

    public void selectQADataFromChooseTableType() throws InterruptedException {
    	WaitTypes.clickWhenReadyByElement(chooseTableType);
    	WaitTypes.clickWhenReadyByElement(qaDataDropdownOption);
        //WaitTypes.waitForElementVisibilityByWebElement(chooseTableType);
        //Constants.javascriptClickWebElement(chooseTableType);
        //Constants.javascriptClickWebElement(qaDataDropdownOption);
    }
    
    public void selectQACategoryFromChooseTableType() throws InterruptedException {
    	WaitTypes.clickWhenReadyByElement(chooseTableType);
    	WaitTypes.clickWhenReadyByElement(qaCategoryDropdownOption);
        //WaitTypes.waitForElementVisibilityByWebElement(chooseTableType);
        //Constants.javascriptClickWebElement(chooseTableType);
        //Constants.javascriptClickWebElement(qaCategoryDropdownOption);
    }

    public void verifyCategoryTableRequiresMatchingFieldMessageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(attemptImportFieldErrorMessage);
        String oopsText = attemptImportFieldErrorMessage.getText();
        Assert.assertEquals("Oops. Looks like the system can't match 8 fields", oopsText);
    }

    public void verifyFoodTableFileThatRequiresMatchingFieldMessageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(attemptImportFieldErrorMessage);
        String oopsText = attemptImportFieldErrorMessage.getText();
        Assert.assertEquals("Oops. Looks like the system can't match 5 fields", oopsText);
    }

    public void verifyDiaryTableFileThatRequiresMatchingFieldMessageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(attemptImportFieldErrorMessage);
        String oopsText = attemptImportFieldErrorMessage.getText();
        Assert.assertEquals("Oops. Looks like the system can't match 4 fields", oopsText);
    }

    public void verifyNutrientsTableFileThatRequiresMatchingFieldMessageIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(attemptImportFieldErrorMessage);
        String oopsText = attemptImportFieldErrorMessage.getText();
        Assert.assertEquals("Oops. Looks like the system can't match 3 fields", oopsText);
    }
    
    // DELETE - after FS/Editor re-factor - Should have this located in the Editor page class
    public void verifyInvalidTableImportLabelIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(invalidTableImportTableLabel);
    }
    // DELETE - After FS/Editor re-factor - Should have this located in the Editor page class
    public void verifyValidTableImportLabelIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(validTableImportTableLabel);
    }


    
    
    // ********** Import Matching Methods ***********
    
    public void verifyMatchingPageContentsAndStatus() throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(attemptImportFieldErrorMessage);
        String oopsText = attemptImportFieldErrorMessage.getText();
        Assert.assertEquals("Oops. Looks like the system can't match 8 fields", oopsText);
    	
    	WaitTypes.waitForElementVisibilityByWebElement(matchingPageFirstColumn);
    	AssertType.assertTrueIsDisplayedByText(matchingPageFirstColumn, "ExpertModels Data");
    	WaitTypes.waitForElementVisibilityByWebElement(matchingPageSecondColumn);
    	AssertType.assertTrueIsDisplayedByText(matchingPageSecondColumn, "CSV Importing"); 	
    	
    	Assert.assertEquals(importButton.isDisplayed(), true);
    	Assert.assertEquals(importButton.isEnabled(), false);
    	Assert.assertEquals(goBackButton.isDisplayed(), true);
    	Assert.assertEquals(goBackButton.isEnabled(), true);
    }
    
    public void verifyRequiredAndOrMatchingStatus(String statusLabel) throws Exception{
    	List<WebElement> allRequiredFields = driver.findElements(By.xpath("//table[@id='matching-table']/tbody//td[3]"));
    	for (WebElement fieldList : allRequiredFields){
    		AssertType.assertTrueIsDisplayedByText(fieldList, statusLabel);
    	}
    }

    public void verifyIncludedLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(includedMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(includedMatchingStatusLabel, expectedLabel);
    }
    
    public void verifyCategoryLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(categoryMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(categoryMatchingStatusLabel, expectedLabel);
    }
    
    public void verifyItemLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(itemMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(itemMatchingStatusLabel, expectedLabel);
    }
    
    public void verifyDataPointsLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(dataPointsMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(dataPointsMatchingStatusLabel, expectedLabel);
    }
    
    public void verifyCommentLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(commentMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(commentMatchingStatusLabel, expectedLabel);
    }
    
    public void verifyNumericTestLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(numericTestMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(numericTestMatchingStatusLabel, expectedLabel);
    }
    
    public void verifyNumericDistTestLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(numericDistTestMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(numericDistTestMatchingStatusLabel, expectedLabel);
    }
    
    public void verifyIntegerTestLabelStatus(String expectedLabel) throws Exception {
    	WaitTypes.waitForElementVisibilityByWebElement(integerTestMatchingStatusLabel);
    	AssertType.assertTrueIsDisplayedByText(integerTestMatchingStatusLabel, expectedLabel);
    }
        
    
    


    // ************* Page Option Methods ***************
    
    public void verifyImportPageContentsAreDisplayed () {
    	WaitTypes.waitForElementVisibilityByWebElement(importPageTitle);
    	Assert.assertEquals(importPageTitle.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(browseFilesButton);
        Assert.assertEquals(browseFilesButton.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseModelCategory);
        Assert.assertEquals(chooseModelCategory.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseTableType);
        Assert.assertEquals(chooseTableType.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(tableNameTextBox);
        Assert.assertEquals(tableNameTextBox.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(quoteRadioButton);
        Assert.assertEquals(quoteRadioButton.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(singleQuoteRadioButton);
        Assert.assertEquals(singleQuoteRadioButton.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(commaRadioButton);
        Assert.assertEquals(commaRadioButton.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(semicolonRadioButton);
        Assert.assertEquals(semicolonRadioButton.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(tabRadioButton);
        Assert.assertEquals(tabRadioButton.isDisplayed(), true);  
        WaitTypes.waitForElementVisibilityByWebElement(importButton);
        Assert.assertEquals(importButton.isDisplayed(), true);
        WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
        Assert.assertEquals(goBackButton.isDisplayed(), true);
    }
    
    public void verifyImportOptionsDefaultStatuses() {
    	WaitTypes.waitForElementVisibilityByWebElement(browseFilesButton);
        Assert.assertEquals(browseFilesButton.isEnabled(), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseModelCategory);
        Assert.assertEquals(chooseModelCategory.getAttribute("class").contains(noDDSelectionStatus), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseTableType);
        Assert.assertEquals(chooseTableType.getAttribute("class"), noDDSelectionStatus);
        WaitTypes.waitForElementVisibilityByWebElement(tableNameTextBox);
        Assert.assertEquals(tableNameTextBox.getText(), blankEntry);
        WaitTypes.waitForElementVisibilityByWebElement(quoteRadioButton);
        Assert.assertEquals(quoteRadioButton.getAttribute("aria-checked"), "true");
        WaitTypes.waitForElementVisibilityByWebElement(singleQuoteRadioButton);
        Assert.assertEquals(singleQuoteRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(commaRadioButton);
        Assert.assertEquals(commaRadioButton.getAttribute("aria-checked"), "true");
        WaitTypes.waitForElementVisibilityByWebElement(semicolonRadioButton);
        Assert.assertEquals(semicolonRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(tabRadioButton);
        Assert.assertEquals(tabRadioButton.getAttribute("aria-checked"), "false");  
        WaitTypes.waitForElementVisibilityByWebElement(importButton);
        Assert.assertEquals(importButton.isEnabled(), false);
        WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
        Assert.assertEquals(goBackButton.isEnabled(), true);
    }
    
    public void verifyImportOptionsStatusesAfterFirstToggle() {
    	WaitTypes.waitForElementVisibilityByWebElement(browseFilesButton);
        Assert.assertEquals(browseFilesButton.isEnabled(), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseModelCategory);     
        Assert.assertEquals(chooseModelCategory.getAttribute("class").contains(selectedDDOptionStatus), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseTableType);     
        Assert.assertEquals(chooseTableType.getAttribute("class").contains(selectedDDOptionStatus), true );
        WaitTypes.waitForElementVisibilityByWebElement(tableNameTextBox);
        Assert.assertEquals(tableNameTextBox.getText(), blankEntry);
        WaitTypes.waitForElementVisibilityByWebElement(quoteRadioButton);
        Assert.assertEquals(quoteRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(singleQuoteRadioButton);
        Assert.assertEquals(singleQuoteRadioButton.getAttribute("aria-checked"), "true");
        WaitTypes.waitForElementVisibilityByWebElement(commaRadioButton);
        Assert.assertEquals(commaRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(semicolonRadioButton);
        Assert.assertEquals(semicolonRadioButton.getAttribute("aria-checked"), "true");
        WaitTypes.waitForElementVisibilityByWebElement(tabRadioButton);
        Assert.assertEquals(tabRadioButton.getAttribute("aria-checked"), "false");  
        WaitTypes.waitForElementVisibilityByWebElement(importButton);
        Assert.assertEquals(importButton.isEnabled(), false);
        WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
        Assert.assertEquals(goBackButton.isEnabled(), true);
    }
    
    public void verifyImportOptionsStatusesAfterSecondToggle() {
    	WaitTypes.waitForElementVisibilityByWebElement(browseFilesButton);
        Assert.assertEquals(browseFilesButton.isEnabled(), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseModelCategory);
        Assert.assertEquals(chooseModelCategory.getAttribute("class").contains(selectedDDOptionStatus), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseTableType);
        Assert.assertEquals(chooseTableType.getAttribute("class").contains(selectedDDOptionStatus), true );
        WaitTypes.waitForElementVisibilityByWebElement(tableNameTextBox);
        Assert.assertEquals(tableNameTextBox.getText(), blankEntry);
        WaitTypes.waitForElementVisibilityByWebElement(quoteRadioButton);
        Assert.assertEquals(quoteRadioButton.getAttribute("aria-checked"), "true");
        WaitTypes.waitForElementVisibilityByWebElement(singleQuoteRadioButton);
        Assert.assertEquals(singleQuoteRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(commaRadioButton);
        Assert.assertEquals(commaRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(semicolonRadioButton);
        Assert.assertEquals(semicolonRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(tabRadioButton);
        Assert.assertEquals(tabRadioButton.getAttribute("aria-checked"), "true");  
        WaitTypes.waitForElementVisibilityByWebElement(importButton);
        Assert.assertEquals(importButton.isEnabled(), false);
        WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
        Assert.assertEquals(goBackButton.isEnabled(), true);
    }
    
    public void verifyImportOptionsStatusesAfterThirdToggle() {
    	WaitTypes.waitForElementVisibilityByWebElement(browseFilesButton);
        Assert.assertEquals(browseFilesButton.isEnabled(), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseModelCategory);
        Assert.assertEquals(chooseModelCategory.getAttribute("class").contains(selectedDDOptionStatus), true);
        WaitTypes.waitForElementVisibilityByWebElement(chooseTableType);
        Assert.assertEquals(chooseTableType.getAttribute("class").contains(selectedDDOptionStatus), true );
        WaitTypes.waitForElementVisibilityByWebElement(tableNameTextBox);
        Assert.assertEquals(tableNameTextBox.getAttribute("class").contains(selectedDDOptionStatus), true );
        WaitTypes.waitForElementVisibilityByWebElement(quoteRadioButton);
        Assert.assertEquals(quoteRadioButton.getAttribute("aria-checked"), "true");
        WaitTypes.waitForElementVisibilityByWebElement(singleQuoteRadioButton);
        Assert.assertEquals(singleQuoteRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(commaRadioButton);
        Assert.assertEquals(commaRadioButton.getAttribute("aria-checked"), "true");
        WaitTypes.waitForElementVisibilityByWebElement(semicolonRadioButton);
        Assert.assertEquals(semicolonRadioButton.getAttribute("aria-checked"), "false");
        WaitTypes.waitForElementVisibilityByWebElement(tabRadioButton);
        Assert.assertEquals(tabRadioButton.getAttribute("aria-checked"), "false");  
        WaitTypes.waitForElementVisibilityByWebElement(importButton);
        Assert.assertEquals(importButton.isEnabled(), false);
        WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
        Assert.assertEquals(goBackButton.isEnabled(), true);
    }
    

    public void clickImportButton() throws InterruptedException {
        WaitTypes.waitUntilElementIsClickable(importButton);
        Constants.javascriptClickWebElement(importButton);
    }

    public void clickQuoteRadioButton() throws InterruptedException {
        WaitTypes.waitUntilElementIsClickable(quoteRadioButton);
        Constants.javascriptClickWebElement(quoteRadioButton);
    }

    public void clickSingleQuoteRadioButton()  throws InterruptedException{
        WaitTypes.waitUntilElementIsClickable(singleQuoteRadioButton);
        Constants.javascriptClickWebElement(singleQuoteRadioButton);
    }

    public void clickCommaRadioButton() throws InterruptedException {
        WaitTypes.waitUntilElementIsClickable(commaRadioButton);
        Constants.javascriptClickWebElement(commaRadioButton);
    }

    public void clickSemicolonRadioButton() throws InterruptedException {
        WaitTypes.waitUntilElementIsClickable(semicolonRadioButton);
        Constants.javascriptClickWebElement(semicolonRadioButton);
    }

    public void clickTabRadioButton() throws InterruptedException {
        WaitTypes.waitUntilElementIsClickable(tabRadioButton);
        Constants.javascriptClickWebElement(tabRadioButton);
    }

    public void clickBrowseFilesButton() throws Exception {
        //Constants.scrollToElementByElement(browseFilesButton);
        //browseFilesButton.click();
    	WaitTypes.clickWhenReadyByElement(browseFilesButton);
    }
    
    public void clickGoBackButton() throws Exception{
    	Constants.scrollToElementByElement(goBackButton);
    	goBackButton.click();
    	//WaitTypes.clickWhenReadyByElement(goBackButton);
    }
    
    public void clickChooseNewButton() throws Exception{
    	WaitTypes.clickWhenReadyByElement(chooseNewButton);
    }
    
    public void clickRemoveButton() throws Exception{
    	WaitTypes.waitForElementVisibilityByWebElement(removeImportButton);
    	WaitTypes.clickWhenReadyByElement(removeImportButton);
    }
    
    public void verifyImportButtonIsClickable(){
    	WaitTypes.waitUntilElementIsClickable(importButton);
    	Assert.assertEquals(importButton.isEnabled(), true);
    }
    
    public void verifyImportButtonIsDisplayedAndNotEnabled() {
        WaitTypes.waitForElementVisibilityByWebElement(importButton);
        //boolean b = !(importButton.isEnabled());
        Assert.assertEquals(importButton.isDisplayed(), true);
        Assert.assertEquals(importButton.isEnabled(), false);
    }


    public void fillInTableNameTextBox(String tableName) throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(tableNameTextBox);
    	//Constants.javascriptClickWebElement(tableNameTextBox);
        tableNameTextBox.clear();
        tableNameTextBox.sendKeys(tableName);
    }
    
    public void verifyChooseNewAndRemoveAreDisplayedAndEnabled(){
    	WaitTypes.waitForElementVisibilityByWebElement(chooseNewButton);
    	Assert.assertEquals(chooseNewButton.isDisplayed(), true);
    	Assert.assertEquals(chooseNewButton.isEnabled(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(removeImportButton);
    	Assert.assertEquals(removeImportButton.isDisplayed(), true);
    	Assert.assertEquals(removeImportButton.isEnabled(), true);   	
    }

    
    
    // ********* AutoIt - Select file & download dialog box methods **********
      
    public void DownloadTheZipFileDialogBox() throws AWTException, IOException, InterruptedException {
        //Runtime.getRuntime().exec("C:\\Workspace\\Automation\\git\\expert-models-regression-suite\\AutoITFilePicker\\ExportDownload.exe");
        Runtime.getRuntime().exec("AutoITFilePicker/ExportDownload.exe");

    }

    public void selectQACategoryTableWithQuoteAndComma() throws Exception {
        //Constants.upLoadFileViaWindowsPrompt("C:\\Workspace\\Automation\\git\\expert-models-regression-suite\\csvTableTypeFiles\\SubjectFileWithSemicolonAndSingleQuote.csv");
        //Runtime.getRuntime().exec("C:\\Workspace\\Automation\\git\\expert-models-regression-suite\\AutoITFilePicker\\CategoryTableWithComma&QuoteDefault.exe");
    	//Runtime.getRuntime().exec("AutoITFilePicker/CategoryTableWithComma&QuoteDefault.exe");
    	Runtime.getRuntime().exec("AutoITFilePicker/QACategoryTableWithQuoteAndComma.exe");
        AssertType.assertTrueIsDisplayedByText(removeImportButton, "REMOVE");
    }
      
    public void selectQACategoryTableWithQuoteAndSemicolon() throws Exception {
        Runtime.getRuntime().exec("AutoITFilePicker/QACategoryTableWithQuoteAndSemicolon.exe");
        AssertType.assertTrueIsDisplayedByText(removeImportButton, "REMOVE");
    }
    
    public void selectQACategoryTableWithQuoteAndTab() throws Exception {
        Runtime.getRuntime().exec("AutoITFilePicker/QACategoryTableWithQuoteAndTab.exe");
        AssertType.assertTrueIsDisplayedByText(removeImportButton, "REMOVE");
    }
    
    public void selectQADataTableWithSingleQuoteAndComma() throws Exception {
        Runtime.getRuntime().exec("AutoITFilePicker/QADataTableWithSingleQuoteAndComma.exe");
        AssertType.assertTrueIsDisplayedByText(removeImportButton, "REMOVE");
    }
    
    public void selectQADataTableWithSingleQuoteAndSemicolon() throws Exception {
        Runtime.getRuntime().exec("AutoITFilePicker/QADataTableWithSingleQuoteAndSemicolon.exe");
        AssertType.assertTrueIsDisplayedByText(removeImportButton, "REMOVE");
    }
    
    public void selectQADataTableWithSingleQuoteAndTab() throws Exception {
        Runtime.getRuntime().exec("AutoITFilePicker/QADataTableWithSingleQuoteAndTab.exe");
        AssertType.assertTrueIsDisplayedByText(removeImportButton, "REMOVE");
    }

    public void selectIncompleteQACategoryTableForImport() throws Exception {
        Runtime.getRuntime().exec("AutoITFilePicker/IncompleteCategoryTable.exe");
        AssertType.assertTrueIsDisplayedByText(removeImportButton, "REMOVE");
    }

    


}
