package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	WebElement firstNameField = driver.findElement(By.id("input-firstname"));
	WebElement lastNameField = driver.findElement(By.id("input-lastname"));
	WebElement emailField = driver.findElement(By.id("input-email"));
	WebElement telephoneField = driver.findElement(By.id("input-telephone"));
	WebElement passwordField = driver.findElement(By.id("input-password"));
	WebElement passwordConfirmField = driver.findElement(By.id("input-confirm"));
	WebElement privacyPolicyField = driver.findElement(By.name("agree"));
	WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
	
	public void enterFirstName(String firstNameData) {
		firstNameField.sendKeys(firstNameData);	
	}

}
