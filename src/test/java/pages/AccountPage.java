package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean displayStatusOfLogoutOption() {
		WebElement logoutOption = driver.findElement(By.linkText("Logout"));	
		return logoutOption.isDisplayed();
	}

}
