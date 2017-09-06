package Page.classes;

import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by will.okamuneh on 8/31/2016.
 */
public class MyEMDataPage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();


    // This page factory should be deleted once all references has been removed - not necessary

    @FindBy(xpath = "//a[text()='My EM Data™']")
    WebElement myEMDataLink;

    @FindBy(xpath = "//h2[text()=' My EM Data™ ']")
    WebElement myEMDataLabel;

    @FindBy(xpath = "//p[text()='CARES']")
    WebElement myEMDataCaresFolderLink;

    @FindBy(xpath = "//p[text()='Sales Pipeline']")
    WebElement myEMDataSalesPipelineFolderLink;

    @FindBy(xpath = "//p[text()='Sugar Consumption']")
    WebElement myEMDataSugarConsumptionFolderLink;

    @FindBy(xpath = "//*[@id='fs-rc-menu-copy-to']")
    WebElement rightClickCopyToLink;

    @FindBy(xpath = "//*[@id='fs-rc-menu-rerun']")
    WebElement rightClickOpenInTabLink;

    @FindBy(xpath = "//*[@id='fs-burger-menu-cares']/md-menu[1]/md-icon")
    WebElement caresHamburgerLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-copy-to-btn']")
    WebElement hamburgerCopyToLink;

    @FindBy(xpath = "//div[@aria-hidden='false']/md-menu-content/md-menu-item//button[@id='fs-rowmenu-details-btn']")
    WebElement hamburgerMoreDetailsLink;

    @FindBy(xpath = "//*[@id='fs-batch-actions']/button")
    WebElement batchActionCopyTo;

    @FindBy(xpath = "//p[text()='NHANES Two Day']")
    WebElement myEMDataNHANESTwoDaysFolderLink;

    @FindBy(xpath = "//md-icon[@md-svg-icon='dataset_folder']")
    WebElement myEMDataDataSetFolderIcon;

    @FindBy(xpath = "//p[text()='2012 (Dataset)']")
    WebElement myEM2012DataSetLink;

    @FindBy(xpath = "//h2[text()=' 2012 (Dataset) ']")
    WebElement myEM2012DataSetLabel;

    @FindBy(xpath = "//*[text()='You have no files to list']")
    WebElement folderYouHaveNoFilesToList;

    @FindBy(xpath = "//*[@id='fs-burger-menu-2012-dataset']/md-menu[1]/md-icon")
    WebElement dataSetHamburgerMenu;

    @FindBy(xpath = "//*[@id='fs-rowmenu-details-btn']")
    WebElement dataSetHamburgerMoreDetailsMenu;

    @FindBy(xpath = "//h1[text()='2012 (Dataset)']")
    WebElement dataSetHamburgerMoreDetailsMenu2012DataSet;

    @FindBy(xpath = "//h1[text()='Subject']")
    WebElement dataSetHamburgerMoreDetailsMenuSubjectTable;

    @FindBy(xpath = ".//*[@id='fs-viewer']/md-toolbar/div[2]/md-icon")
    WebElement closeMoreDetailsInfoBox;

    @FindBy(xpath = "//div[text()='folder']")
    WebElement moreDetailsFolderType;

    @FindBy(xpath = "//div[text()='table']")
    WebElement moreDetailsTableType;

    @FindBy(xpath = "//div[text()='emadmin@cremeglobal.com']")
    WebElement moreDetailsOwner;

    @FindBy(xpath = "//*[text()='folder']")
    WebElement folderType;

    @FindBy(xpath = "//*[@id='fs-header-wrapper']/div/div[2]/div[2]/a/md-icon")
    WebElement dataSetMoreInfoIcon;

    @FindBy(xpath = "//div[text()='This information is static and does not update when changes are made to a dataset']")
    WebElement dataSetWarningInfo;

    @FindBy(xpath = "//div[@class='md-subhead ds-description ng-binding']")
    WebElement dataSetFolderDescription;

    @FindBy(xpath = "//*[@id='ds-header']/md-toolbar/div/div[1]/button")
    WebElement dataSetMoreInfoBackArrowButton;

    @FindBy(xpath = "//*[text()='Details']")
    WebElement dataSetMoreInfoDetailLabel;

    @FindBy(xpath = "//*[text()='TABLES']")
    WebElement dataSetMoreInfoTablesLabel;

    @FindBy(xpath = "//*[@id='fs-burger-menu-subject']/md-menu[1]/md-icon")
    WebElement dataSetSubjectHamburgerTable;




    public MyEMDataPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    public void clickMainPageMyEMDataLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(myEMDataLink);
    }

    public void clickMainPageMyEMDataCaresFolderLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(myEMDataCaresFolderLink);
    }

    public void clickMainPageMyEMDataSugarConsumptionLink() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(myEMDataSalesPipelineFolderLink);
    }

    public void clickDataSetHamburgerMenu() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(dataSetHamburgerMenu);
    }

    public void clickOnMyEMDataCaresFolderHamburgerMenu() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(myEMDataCaresFolderLink);
        Constants.mouseHoover(myEMDataCaresFolderLink);
        WaitTypes.waitForElementVisibilityByWebElement(caresHamburgerLink);
        WaitTypes.clickWhenReadyByElement(caresHamburgerLink);
    }

    public void clickDataSetHamburgerMoreDetailsMenu() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(dataSetHamburgerMoreDetailsMenu);
    }

    public void clickDataSetMoreInfoIcon() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(dataSetMoreInfoIcon);
    }

    public void clickDataSetMoreInfoBackArrowButton() throws InterruptedException{
        WaitTypes.clickWhenReadyByElement(dataSetMoreInfoBackArrowButton);
    }

    public void clickDataSetSubjectHamburgerLink() throws InterruptedException {
        WaitTypes.clickWhenReadyByElement(dataSetSubjectHamburgerTable);
    }





    public void rightClickCaresFolder() {
        WaitTypes.waitForElementVisibilityByWebElement(myEMDataCaresFolderLink);
        Constants.rightClick(myEMDataCaresFolderLink);
    }




    public void openNHANESTwoDayFolder() {
        WaitTypes.waitForElementVisibilityByWebElement(myEMDataNHANESTwoDaysFolderLink);
        Constants.doubleClickOnElement(myEMDataNHANESTwoDaysFolderLink);
    }

    public void open2012DataSet() {
        WaitTypes.waitForElementVisibilityByWebElement(myEM2012DataSetLink);
        Constants.doubleClickOnElement(myEM2012DataSetLink);
    }





    public void verifyCopyToBatchIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(batchActionCopyTo);
    }

    public void verifyCopyToRightClickIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickCopyToLink);
    }

    public void verifyOpenInRightClickIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(rightClickOpenInTabLink);
    }

    public void verifyCopyToHamburgerIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerCopyToLink);
    }

    public void verifyMoreDetailsHamburgerIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(hamburgerMoreDetailsLink);
    }

    public void verifyMyEMDataPage() {
        WaitTypes.waitForElementVisibilityByWebElement(myEMDataLabel);
    }

    public void verifyDataSetFolderIconIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(myEMDataDataSetFolderIcon);
    }

    public void verify2012DataSetPage() {
        WaitTypes.waitForElementVisibilityByWebElement(myEM2012DataSetLabel);
    }

    public void verifyDataSetHamburgerMoreDetailsMenuIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(dataSetHamburgerMoreDetailsMenu);
    }

    public void verifyHamburgerMoreDetailsOptionsAreDisplayed() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(dataSetHamburgerMoreDetailsMenu2012DataSet);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsFolderType);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsOwner);
        WaitTypes.clickWhenReadyByElement(closeMoreDetailsInfoBox);
    }

    public void verifyHamburgerMoreDetailsOptionsForSubjectTableAreDisplayed() throws InterruptedException{
        WaitTypes.waitForElementVisibilityByWebElement(dataSetHamburgerMoreDetailsMenuSubjectTable);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsTableType);
        WaitTypes.waitForElementVisibilityByWebElement(moreDetailsOwner);
        WaitTypes.clickWhenReadyByElement(closeMoreDetailsInfoBox);
    }

    public void verifyDataSetMoreInfoIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(dataSetMoreInfoIcon);
    }

    public void verifyDataSetWarningInfoIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(dataSetWarningInfo);
    }

    public void verifyDataSetDescriptionInfoIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(dataSetFolderDescription);
    }

    public void verifyDataSetDetailsLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(dataSetMoreInfoDetailLabel);
    }

    public void verifyDataSetTablesLabelIsDisplayed() {
        WaitTypes.waitForElementVisibilityByWebElement(dataSetMoreInfoTablesLabel);
    }






    public void verifyCopyFunctionIsNotDisplayedInDropDown() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[@id='fs-rc-menu-copy']")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }

    public void verifyMoveFunctionIsNotDisplayedInDropDown() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[@id='fs-rc-menu-move']")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }

    public void verifyDataSetIconIsNotDisplayedInDropDown() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//md-icon[@md-svg-icon='dataset_folder']")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }

    public void verifyYouHaveNoFilesToListIsNOTDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[text()='You have no files to list']")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }

    public void verifyFolderIsNOTDisplayed() {
        Boolean isNOTPresent = driver.findElements(By.xpath("//*[text()='folder']")).size() < 1;
        Assert.assertTrue(isNOTPresent);
    }



}
