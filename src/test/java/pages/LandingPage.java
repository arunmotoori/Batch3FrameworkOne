package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	public SearchPage searchProduct(String searchTermData) {
		enterSearchTermIntoSearchBoxField(searchTermData);
		return clickOnSearchButton();
	}
	
	public LoginPage navigateToLoginPage() {
		clickOnMyAccountOption();
		return selectLoginOption();
	}
	
	public RegisterPage navigateToRegisterPage() {
		clickOnMyAccountOption();
		return selectRegisterOption();
	}
	
	public void clickOnMyAccountOption() {
		
		myAccountDropMenu.click();
	}
	
	public RegisterPage selectRegisterOption() {
		
		registerOption.click();
		return new RegisterPage(driver);
		
	}
	
	public LoginPage selectLoginOption(){
		
		loginOption.click();
		return new LoginPage(driver);
		
	}
	
	public void enterSearchTermIntoSearchBoxField(String searchTermData) {
		
		searchBoxField.sendKeys(searchTermData);
		
	}
	
	public SearchPage clickOnSearchButton() {
		
		searchButton.click();
		return new SearchPage(driver);
		
	}
	
}
