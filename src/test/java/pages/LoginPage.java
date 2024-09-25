package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterEmailAddress(String emailAddressData) {
		WebElement emailField = driver.findElement(By.id("input-email"));
		emailField.sendKeys(emailAddressData);
	}
	
	public void enterPassword(String passwordData) {
		WebElement passwordField = driver.findElement(By.id("input-password"));
		passwordField.sendKeys(passwordData);
	}
	
	public WebDriver clickOnLoginButton() {
		WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));
		loginButton.click();
		return driver;
	}
	
	public String getWarningMessage() {
		WebElement warningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
		return warningMessage.getText();
	}

}
