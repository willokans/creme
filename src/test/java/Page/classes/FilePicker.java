package Page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util.EMDriverSingleton;
import Util.WaitTypes;

public class FilePicker {
	final static int WAIT_TIME_OUT=50;
    private WebDriver driver = EMDriverSingleton.getFireFoxInstanceDriver();
    
    // File picker page elements
    @FindBy(id = "filepicker")
    WebElement filePickerOverlay;
    
    @FindBy(id = "fp-file-selector-my-em-data")
    WebElement myEMDataFolder;
    
    @FindBy(id = "fp-file-selector-qa")
    WebElement qaFolder;
    
    @FindBy(id = "fp-file-selector-category2")
    WebElement category2Table;
    
    @FindBy(id = "fp-file-selector-category1")
    WebElement category1Table;
    
    @FindBy(xpath = "//div[@id='filepicker']//button/span[text()='Select']")
    WebElement selectButton;
    
    // Initialize Page Elements
    public FilePicker(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    
    // File picker methods
    public void clickmyEMDataFolder() throws Exception{
    	WaitTypes.clickWhenReadyByElement(myEMDataFolder);
    }
    
    public void clickQAFolder() throws Exception{
    	WaitTypes.clickWhenReadyByElement(qaFolder);
    }
    
    public void clickCategory1Table() throws Exception{
    	WaitTypes.clickWhenReadyByElement(category1Table);
    }
    
    public void clickCategory2Table() throws Exception{
    	WaitTypes.clickWhenReadyByElement(category2Table);
    }
    
    public void clickSelectButton() throws Exception{
        WaitTypes.clickWhenReadyByElement(selectButton);
    }
    
}
   


