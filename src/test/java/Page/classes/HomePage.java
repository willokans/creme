package Page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util.Constants;
import Util.EMDriverSingleton;
import Util.WaitTypes;

/**
 * Created by will.okanumeh on 6/3/2016.
 */
public class HomePage {

    final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
    
    
    @FindBy(xpath = "//*[text()='FILES']")
    WebElement filesLink;
    
    @FindBy(xpath = ".//*[@id='main-wrapper']/ng-include/md-toolbar/div/a[2]")
    WebElement ModelsLink;
    
    @FindBy(xpath = ".//*[@id='main-wrapper']/ng-include/md-toolbar/div/a[3]")
    WebElement ActivityLink;
    
    @FindBy(xpath = ".//*[@id='fs-aside']/div/ul[1]/li[1]/a")
    WebElement sideBarMyData;
    
    @FindBy(xpath = ".//*[@id='fs-aside']/div/ul[1]/li[2]/a")
    WebElement sideBarSharedData;
    
    @FindBy(xpath = ".//*[@id='fs-aside']/div/ul[1]/li[3]/a")
    WebElement sideBarMyEMData;
    
    @FindBy(xpath = ".//*[@id='fs-aside']/div/ul[2]/li[1]/a/translate")
    WebElement sideBarRecent;
    
    @FindBy(xpath = ".//*[@id='fs-aside']/div/ul[2]/li[2]/a/translate")
    WebElement sideBarTrash;
    
    @FindBy(xpath = ".//*[@id='fs-header-wrapper']/div/div[1]/div/div[1]/div/h2")
    WebElement mainPageMyData;
    
    @FindBy(xpath = ".//*[@id='main-wrapper']/ng-include/md-toolbar/div/user-icon/md-menu/button")
    WebElement mainMenuButton;

    @FindBy(xpath = "//*[text()='My Profile']")
    WebElement mainMenuMyProfileLink;
    
    //@FindBy(xpath = ".//*[@id='menu_container_1']/md-menu-content/md-menu-item[2]/a/div/p")
    @FindBy(xpath = "//p[text()='Account']")
    WebElement accountLink;
    
    @FindBy(xpath = ".//*[@id='menu_container_1']/md-menu-content/md-menu-item[3]/a")
    WebElement helpLink;
    
    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logoutLink;
    
    
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    public void loggingOut() throws Exception {
    	Constants.refreshPage();
    	Thread.sleep(5000);
        WaitTypes.waitForElementVisibilityByWebElement(mainMenuButton);
    	mainMenuButton.click();
        WaitTypes.waitForElementVisibilityByWebElement(logoutLink);
        Thread.sleep(1000);
        WaitTypes.clickWhenReadyByElement(logoutLink);

    }
    
    public void verifyHomePage() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(mainMenuButton);
    	mainMenuButton.isDisplayed();
    }

    // To Be Deleted
    /*public void clickOnAccountsLink() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(accountLink);
        accountLink.click();
    }*/

    public void navigateToAccountPage() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(mainMenuButton);
        mainMenuButton.click();
        WaitTypes.clickWhenReadyByElement(accountLink);
    }

    public void navigateToMyProfilePage() throws Exception {
        WaitTypes.waitForElementVisibilityByWebElement(mainMenuButton);
        mainMenuButton.click();
        Thread.sleep(2000);
        WaitTypes.clickWhenReadyByElement(mainMenuMyProfileLink);
    }

}