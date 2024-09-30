package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Logout")
	private WebElement logoutOption;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//li[@class='dropdown open']//li/a[text()='Logout']")
	private WebElement logoutOptionUnderMyAccountMenu;
	
	public AccountLogoutPage logoutFromApplication() {
		clickOnMyAccountDropMenu();
		return selectLogoutOptionFromMyAccountMenu();
	}
	
	public boolean displayStatusOfLogoutOption() {
		
		return logoutOption.isDisplayed();
		
	}
	
	public void clickOnMyAccountDropMenu() {
		myAccountDropMenu.click();
	}
	
	public AccountLogoutPage selectLogoutOptionFromMyAccountMenu() {
		logoutOptionUnderMyAccountMenu.click();
		return new AccountLogoutPage(driver);
	}

}
