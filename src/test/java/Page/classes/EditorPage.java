package Page.classes;

import Util.AssertType;
import org.testng.Assert;
import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by will.okamuneh on 11/15/2016.
 */
public class EditorPage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    @FindBy(xpath = "//td[text()='02']")
    WebElement diaryTableSubjectCodeCell_1_1;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[1]/td[1]")
    WebElement Cell_Row_0_Col_0;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[2]/td[1]")
    WebElement Cell_Row_1_Col_0;

    @FindBy(xpath = ".//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[3]/td[1]")
    WebElement Cell_Row_2_Col_0;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[4]/td[1]")
    WebElement Cell_Row_3_Col_0;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[1]/td[2]")
    WebElement Cell_Row_0_Col_1;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[2]/td[2]")
    WebElement Cell_Row_1_Col_1;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[3]/td[2]")
    WebElement Cell_Row_2_Col_1;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[4]/td[2]")
    WebElement Cell_Row_3_Col_1;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[1]/td[3]")
    WebElement Cell_Row_0_Col_2;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[1]/td[5]")
    WebElement Cell_Row_0_Col_4;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[2]/td[3]")
    WebElement Cell_Row_1_Col_2;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[3]/td[3]")
    WebElement Cell_Row_2_Col_2;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[4]/td[3]")
    WebElement Cell_Row_3_Col_2;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[1]/td[4]")
    WebElement Cell_Row_0_Col_3;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[2]/td[4]")
    WebElement Cell_Row_1_Col_3;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[3]/td[4]")
    WebElement Cell_Row_2_Col_3;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[4]/td[4]")
    WebElement Cell_Row_3_Col_3;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[24]/td[1]")
    WebElement Cell_Row_24_Col_0;

    @FindBy(xpath = "//td[@class='htLeft current highlight'][@row='0'][@col='0']")
    WebElement firstCellSelectedByDefault;

    @FindBy(id = "table-name")
    WebElement editorTableName;

    @FindBy(id = "rows-count")
    WebElement editorRowCount;
    
    @FindBy(className = "go-back")
    WebElement goBackButton;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[2]/div/span[text()='Nutrient Code']")
    WebElement editorNutrientCodeFieldName;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[3]/div/span[text()='Food Code']")
    WebElement editorFoodCodeFieldName;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[4]/div/span[text()='Presence Probability']")
    WebElement editorPresenceProbabilityFieldName;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[5]/div/span[text()='Concentration (ppm)']")
    WebElement editorConcentrationFieldName;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[2]/div")
    WebElement diarySubjectCodeTableHeader;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[3]/div")
    WebElement diaryDayTableHeader;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[4]/div")
    WebElement diaryFoodCodeTableHeader;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[2]/div/div/div/table/thead/tr/th[5]/div")
    WebElement dairyAmountTableHeader;



    //After Ascending Sort
    @FindBy(xpath = "//td[@row='3'][@col='0'][text()='03']")
    WebElement dairyText_Row_3_Col_0;

    @FindBy(xpath = "//td[@row='1'][@col='1'][text()='8']")
    WebElement dairyText_Row_1_Col_1;

    @FindBy(xpath = "//td[@row='1'][@col='2'][text()='AH123']")
    WebElement dairyText_Row_1_Col_2;

    @FindBy(xpath = "//td[@row='2'][@col='3'][text()='15.4562']")
    WebElement dairyText_Row_2_Col_3;

    //After Descending sort
    @FindBy(xpath = "//td[@row='1'][@col='0'][text()='02']")
    WebElement dairyText_Row_1_Col_0;

    @FindBy(xpath = "//td[@row='2'][@col='1'][text()='8']")
    WebElement dairyText_Row_2_Col_1;

    @FindBy(xpath = "//td[@row='2'][@col='2'][text()='AH123']")
    WebElement dairyText_Row_2_Col_2;

    @FindBy(xpath = "//td[@row='3'][@col='3'][text()='30.7846']")
    WebElement dairyText_Row_3_Col_3;





    @FindBy(xpath = "//td[@row='1'][@col='0'][text()='02']")
    WebElement diaryTableSubjectCodeCopyCell_1_0;

    @FindBy(xpath = "//td[@row='2'][@col='0'][text()='02']")
    WebElement diaryTableSubjectCodeCopyCell_2_0;

    @FindBy(xpath = "//td[@row='3'][@col='0'][text()='02']")
    WebElement diaryTableSubjectCodeCopyCell_3_0;

    @FindBy(xpath = "//div[text()='Copy']")
    WebElement editorCopyMenuOptionLink;

    @FindBy(xpath = "//div[text()='Paste']")
    WebElement editorPasteMenuOptionLink;

    @FindBy(xpath = "//div[text()='Cut']")
    WebElement editorCutMenuOptionLink;

    @FindBy(xpath = "//div[text()='Clear']")
    WebElement editorClearMenuOptionLink;

    @FindBy(xpath = "//div[text()='Enter Distribution']")
    WebElement editorEnterDistributionMenuOptionLink;

    @FindBy(xpath = "//div[text()='Delete Field']")
    WebElement editorDeleteFieldMenuOptionLink;

    @FindBy(xpath = "//div[text()='Insert row above']")
    WebElement editorInsertRowAboveMenuOptionLink;

    @FindBy(xpath = "//div[text()='Insert row below']")
    WebElement editorInsertRowBelowMenuOptionLink;

    @FindBy(xpath = "//div[text()='Delete Row']")
    WebElement editorRemoveRowMenuOptionLink;

    @FindBy(xpath = "//div[text()='Sort Ascending']")
    WebElement editorSortAscendingMenuOptionLink;

    @FindBy(xpath = "//div[text()='Sort Descending']")
    WebElement editorSortDescendingMenuOptionLink;

    @FindBy(xpath = "//div[text()='Remove Sorting']")
    WebElement editorRemoveSortingMenuOptionLink;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[3]/div/div/div/table/tbody/tr[1]/th/div")
    WebElement RowZero;

    @FindBy(xpath = "//*[@id='handsontable-wrapper']/div[3]/div/div/div/table/tbody/tr[2]/th/div")
    WebElement RowOne;

    @FindBy(xpath = ".//*[@id='handsontable-wrapper']/div[3]/div/div/div/table/tbody/tr[2]/th/div")
    WebElement numberTwoRow;

    @FindBy(xpath = "//*[text()='Valid table']")
    WebElement validTableImportTableLabel;

    @FindBy(xpath = "//div//span[text()='Test column']")
    WebElement newColumnTestColumn;




    // Table Top Menu bar
    @FindBy(xpath = "//md-menu[@class='md-menu ng-scope _md']//button[@class='header-btn']//*[text()='File']")
    WebElement editorMainMenuFile;

    @FindBy(xpath = "//md-menu[@class='md-menu ng-scope _md']//button[@class='header-btn']//*[text()='Edit']")
    WebElement editorMainMenuEdit;

    @FindBy(xpath = "//md-menu[@class='md-menu ng-scope _md']//button[@class='header-btn']//*[text()='View']")
    WebElement editorMainMenuView;

    @FindBy(xpath = "//md-menu[@class='md-menu ng-scope _md']//button[@class='header-btn']//*[text()='Insert']")
    WebElement editorMainMenuInsert;

    @FindBy(xpath = "//div[@aria-hidden='false']//md-menu-content//md-menu-item//button//translate[text()='Field']")
    WebElement editorMainMenuInsertDropDownFieldMenu;

    @FindBy(xpath = "//div[@aria-hidden='false']//md-menu-content//md-menu-item//button//translate[text()='Row Above']")
    WebElement editorMainMenuInsertDropDownRowAboveMenu;

    @FindBy(xpath = "//div[@aria-hidden='false']//md-menu-content//md-menu-item//button//translate[text()='Row Below']")
    WebElement editorMainMenuInsertDropDownRowBelowMenu;

    @FindBy(xpath = "//div[@aria-hidden='false']//md-menu-content//md-menu-item//button//translate[text()='Delete Row']")
    WebElement editorMainMenuInsertDropDownDeleteRowMenu;

    @FindBy(xpath = "//div[@aria-hidden='false']//md-menu-content//md-menu-item//button//translate[text()='Chart']")
    WebElement editorMainMenuInsertDropDownChartMenu;


    // Insert popup window webElement
    @FindBy(xpath = "//span[text()='Give your field a name']")
    WebElement tableInsertMenuPopUpLabel;

    @FindBy(xpath = "//input[@ng-model='newColumnName']")
    WebElement tableInsertMenuPopUpInputBox;

    @FindBy(xpath = "//button[@ng-click='closeDialog(newColumnName)']//*[text()='Confirm']")
    WebElement tableInsertMenuPopUpConfirmButton;

    @FindBy(xpath = "//button[@disabled='disabled']/*[text()='Confirm']")
    WebElement tableInsertMenuPopUpDisableConfirmButton;

    @FindBy(xpath = "//button[@ng-click='cancel()']//*[text()='Cancel']")
    WebElement tableInsertMenuPopUpCancelButton;






    //Initialize page
    public EditorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    
    public void clickGoBackButton() throws Exception{
    	WaitTypes.clickWhenReadyByElement(goBackButton);
    }
    
    public void verifyUserIsOnEditorPage(){
    	WaitTypes.waitForElementVisibilityByWebElement(editorTableName);
    	Assert.assertEquals(editorTableName.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(editorMainMenuFile);
    	Assert.assertEquals(editorMainMenuFile.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(editorRowCount);
    	Assert.assertEquals(editorRowCount.isDisplayed(), true);
    	WaitTypes.waitForElementVisibilityByWebElement(goBackButton);
    	Assert.assertEquals(goBackButton.isDisplayed(), true);
    	
    }


    public void rightClickEditorSubjectCodeCellRow_0_Col_0() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_0_Col_0);
        Constants.rightClick(Cell_Row_0_Col_0);
        //Thread.sleep(10000);
    }

    public void rightClickEditorSubjectCodeCellRow_1_Col_0() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_1_Col_0);
        Constants.rightClick(Cell_Row_1_Col_0);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_2_Col_0() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_2_Col_0);
        Constants.rightClick(Cell_Row_2_Col_0);
       // Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_0_Col_1() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_0_Col_1);
        Constants.rightClick(Cell_Row_0_Col_1);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_1_Col_1() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_1_Col_1);
        Constants.rightClick(Cell_Row_1_Col_1);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_2_Col_1() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_2_Col_1);
        Constants.rightClick(Cell_Row_2_Col_1);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_0_Col_2() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_0_Col_2);
        Constants.rightClick(Cell_Row_0_Col_2);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_0_Col_4() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_0_Col_4);
        Constants.rightClick(Cell_Row_0_Col_4);

    }

    public void rightClickEditorSubjectCodeCell_Row_1_Col_2() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_1_Col_2);
        Constants.rightClick(Cell_Row_1_Col_2);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_3_Col_2() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_3_Col_2);
        Constants.rightClick(Cell_Row_3_Col_2);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_0_Col_3() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_0_Col_3);
        Constants.rightClick(Cell_Row_0_Col_3);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_1_Col_3() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_1_Col_3);
        Constants.rightClick(Cell_Row_1_Col_3);
        //Thread.sleep(2000);
    }

    public void rightClickEditorSubjectCodeCell_Row_3_Col_3() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_3_Col_3);
        Constants.rightClick(Cell_Row_3_Col_3);
        //Thread.sleep(2000);
    }


    public void rightClickEditorSubjectCodeCell_0_3() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_3_Col_0);
        Constants.rightClick(Cell_Row_3_Col_0);
    }


    public void rightClickEditorRowOne() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(RowOne);
        Constants.rightClick(RowOne);
        //Thread.sleep(2000);
    }

    public void rightClickEditorRowZero() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(RowZero);
        Constants.rightClick(RowZero);
        //Thread.sleep(2000);
    }

    public void rightClickSubjectCodeTableHead() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(diarySubjectCodeTableHeader);
        Constants.rightClick(diarySubjectCodeTableHeader);
        //Thread.sleep(2000);
    }

    public void rightClickDayTableHead() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(diaryDayTableHeader);
        Constants.rightClick(diaryDayTableHeader);
       // Thread.sleep(2000);
    }

    public void rightClickFoodCodeTableHead() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(diaryFoodCodeTableHeader);
        Constants.rightClick(diaryFoodCodeTableHeader);
        //Thread.sleep(2000);
    }

    public void rightClickAmountTableHead() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(dairyAmountTableHeader);
        Constants.rightClick(dairyAmountTableHeader);
        //Thread.sleep(2000);
    }

    public void rightClickNewTestColumn() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(newColumnTestColumn);
        WaitTypes.waitForElementVisibilityByWebElement(newColumnTestColumn);
        Constants.rightClick(newColumnTestColumn);
    }






    public void clickEditorSubjectCodeCell1() throws InterruptedException {
        WaitTypes.waitForElementVisibilityByWebElement(Cell_Row_2_Col_0);
        WaitTypes.clickWhenReadyByElement(Cell_Row_2_Col_0);
        Constants.doubleClickOnElement(Cell_Row_2_Col_0);
    }


    public void clickCopyDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorCopyMenuOptionLink);
    }

    public void clickPasteDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorPasteMenuOptionLink);

        Thread.sleep(5000);
    }

    public void clickCutDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorCutMenuOptionLink);
    }

    public void clickClearDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorClearMenuOptionLink);
    }

    public void clickEnterDistributionDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorEnterDistributionMenuOptionLink);
    }

    public void clickDeleteFieldDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorDeleteFieldMenuOptionLink);
    }

    public void clickInsertRowAboveDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorInsertRowAboveMenuOptionLink);
    }

    public void clickInsertRowBelowDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorInsertRowBelowMenuOptionLink);
    }

    public void clickRemoveRowDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorRemoveRowMenuOptionLink);
    }

    public void clickSortAscendingDropDownMenuOption() throws InterruptedException {
        Thread.sleep(3000);
        WaitTypes.clickWhenReadyByElement(editorSortAscendingMenuOptionLink);
    }

    public void clickSortDescendingDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorSortDescendingMenuOptionLink);
    }

    public void clickRemoveSortingDropDownMenuOption() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorRemoveSortingMenuOptionLink);
    }

    public void clickOnRowZero() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(RowZero);
    }

    public void clickOnRowOne() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(RowOne);
    }

    public void clickOnRowTow() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(numberTwoRow);
    }

    public void clickTablePageFileMainMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuInsertDropDownFieldMenu);
    }

    public void clickTablePageEditMainMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuEdit);
    }

    public void clickTablePageViewMainMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuView);
    }

    public void clickTablePageInsertMainMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuInsert);
    }

    public void clickInsertDropDownFieldMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuInsertDropDownFieldMenu);
    }

    public void clickInsertDropDownRowAboveMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuInsertDropDownRowAboveMenu);
    }

    public void clickInsertDropDownRowBelowMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuInsertDropDownRowBelowMenu);
    }

    public void clickInsertDropDownDeleteRowMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuInsertDropDownDeleteRowMenu);
    }

    public void clickInsertDropDownChartMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(editorMainMenuInsertDropDownChartMenu);
    }

    public void clickInsertPopUpDialogCancelButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(tableInsertMenuPopUpCancelButton);
    }

    public void clickInsertPopUpDialogConfirmButton() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(tableInsertMenuPopUpConfirmButton);
    }





    public void verifyCopyDataInCell_0_1_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(diaryTableSubjectCodeCopyCell_1_0);
    }

    public void verifyCopyDataInCell_0_2_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(diaryTableSubjectCodeCopyCell_2_0);
    }

    public void verifyCopyDataInCell_0_3_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(diaryTableSubjectCodeCopyCell_3_0);
    }

    public void verifyCopyDataInCell_Row3_Col0_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_3_Col_0);
    }

    public void verifyCopyDataInCell_Row1_Col1_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_1_Col_1);
    }

    public void verifyCopyDataInCell_Row1_Col2_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_1_Col_2);
    }

    public void verifyCopyDataInCell_Row2_Col3_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_2_Col_3);
    }

    public void verifyCopyDataInCell_Row1_Col0_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_1_Col_0);
    }

    public void verifyCopyDataInCell_Row2_Col1_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_2_Col_1);
    }

    public void verifyCopyDataInCell_Row2_Col2_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_2_Col_2);
    }

    public void verifyCopyDataInCell_Row3_Col3_IsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(dairyText_Row_3_Col_3);
    }

    public void verifyInsertPopDialogLabelIsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(tableInsertMenuPopUpLabel);
    }

    public void verifyInsertPopDialogConfirmButtonIsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(tableInsertMenuPopUpConfirmButton);
    }

    public void verifyInsertPopDialogDisableConfirmButtonIsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(tableInsertMenuPopUpDisableConfirmButton);
    }

    public void verifyInsertPopDialogCancelButtonIsDisplayed() {

        WaitTypes.waitForElementVisibilityByWebElement(tableInsertMenuPopUpCancelButton);
    }

    public void verifyValidTableImportLabelIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(validTableImportTableLabel);
    }

    public void verifyTablePageFileMainMenuIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(editorMainMenuFile);
    }

    public void verifyTablePageEditMainMenuIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(editorMainMenuEdit);
    }

    public void verifyTablePageViewMainMenuIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(editorMainMenuView);
    }

    public void verifyTablePageInsertMainMenuIsDisplayed () {
        WaitTypes.waitForElementVisibilityByWebElement(editorMainMenuInsert);
    }

    public void verifyNewTestColumnIsDisplayed () throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(newColumnTestColumn);
    }

    public void verifyFirstCellIsSelectedByDefault () throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(firstCellSelectedByDefault);
    }

    public void verifyEditorTableNameIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(editorTableName);
    }

    public void verifyEditorRowCountIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(editorRowCount);
    }

    public void verifyEditorNutrientCodeFieldNameIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(editorNutrientCodeFieldName);
    }

    public void verifyEditorFoodCodeFieldNameIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(editorFoodCodeFieldName);
    }

    public void verifyEditorPresenceProbabilityFieldNameIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(editorPresenceProbabilityFieldName);
    }

    public void verifyEditorConcentrationFieldNameIsDisplayed() throws InterruptedException {
        Thread.sleep(2000);
        WaitTypes.waitForElementVisibilityByWebElement(editorConcentrationFieldName);
    }





    public void verifyNewTestColumnIsNotDisplayed () throws Exception {
        AssertType.assertEqualsNotDisplayed(By.xpath("//div//span[text()='fg']"));

    }




    public void clearAndEnterDataIntoCell0_0 () throws InterruptedException {

        Constants.enterDataIntoTableCell(Cell_Row_0_Col_0, "01.1");
    }


    public void clickOnCell_Row_0_Col_0() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_0_Col_0);
    }

    public void clickOnCell_Row_1_Col_0() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_1_Col_0);
    }

    public void clickOnCell_Row_2_Col_0() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_2_Col_0);
    }

    public void clickOnCell_Row_0_Col_1() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_0_Col_1);
    }

    public void clickOnCell_Row_1_Col_1() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_1_Col_1);
    }

    public void clickOnCell_Row_2_Col_1() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_2_Col_1);
    }

    public void clickOnCell_Row_0_Col_2() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_0_Col_2);
    }

    public void clickOnCell_Row_1_Col_2() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_1_Col_2);
    }

    public void clickOnCell_Row_3_Col_2() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_3_Col_2);
    }

    public void clickOnCell_Row_0_Col_3() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_0_Col_3);
    }

    public void clickOnCell_Row_1_Col_3() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_1_Col_3);
    }

    public void clickOnCell_Row_3_Col_3() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(Cell_Row_3_Col_3);
    }

    public void clickOnCell_Row_24_Col_0() throws InterruptedException {

        Constants.scrollToElement(By.xpath("//*[@id='handsontable-wrapper']/div[1]/div/div/div/table/tbody/tr[24]/td[1]"));
        //WaitTypes.clickWhenReadyByElement(Cell_Row_24_Col_0);
    }






    public void enterDataInCellRow_0_Col_0 () throws InterruptedException {

        Cell_Row_0_Col_0.sendKeys("01.1");
    }

    public void enterDataInCellRow_1_Col_0 () throws InterruptedException {

        Cell_Row_0_Col_0.sendKeys("01.8");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_2_Col_0 () throws InterruptedException {

        Cell_Row_0_Col_0.sendKeys("03");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_0_Col_1 () throws InterruptedException {

        Cell_Row_0_Col_1.sendKeys("1");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_1_Col_1 () throws InterruptedException {

        Cell_Row_1_Col_1.sendKeys("8");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_2_Col_1 () throws InterruptedException {

        Cell_Row_1_Col_1.sendKeys("5");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_0_Col_2 () throws InterruptedException {

        Cell_Row_0_Col_2.sendKeys("CH001");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_1_Col_2 () throws InterruptedException {

        Cell_Row_1_Col_2.sendKeys("AH123");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_3_Col_2 () throws InterruptedException {

        Cell_Row_3_Col_2.sendKeys("ZH786");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_0_Col_3 () throws InterruptedException {

        Cell_Row_0_Col_3.sendKeys("30.784564");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_1_Col_3 () throws InterruptedException {

        Cell_Row_1_Col_3.sendKeys("15.98546");
        //Thread.sleep(5000);
    }

    public void enterDataInCellRow_3_Col_3 () throws InterruptedException {

        Cell_Row_3_Col_3.sendKeys("15.45621");
        //Thread.sleep(5000);
    }

    public void enterFieldNameInInsertPopUp(String element) throws InterruptedException {

        tableInsertMenuPopUpInputBox.sendKeys(element);

    }

}
