package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterFirstName(String firstNameData) {
		WebElement firstNameField = driver.findElement(By.id("input-firstname"));
		firstNameField.sendKeys(firstNameData);	
	}
	
	public void enterLastName(String lastNameData) {
		WebElement lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.sendKeys(lastNameData);
	}
	
	public void enterEmailAddress(String emailData) {
		WebElement emailField = driver.findElement(By.id("input-email"));
		emailField.sendKeys(emailData);
	}
	
	public void enterTelephoneNumber(String telephoneData) {
		WebElement telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.sendKeys(telephoneData);
	}
	
	public void enterPassword(String passwordData) {
		WebElement passwordField = driver.findElement(By.id("input-password"));
		passwordField.sendKeys(passwordData);
	}
	
	public void enterConfirmPassword(String passwordData) {
		WebElement passwordConfirmField = driver.findElement(By.id("input-confirm"));
		passwordConfirmField.sendKeys(passwordData);
	}
	
	public void selectPrivacyPolicy() {
		WebElement privacyPolicyField = driver.findElement(By.name("agree"));
		privacyPolicyField.click();
	}
	
	public void clickOnContinueButton() {
		WebElement continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		continueButton.click();
	}
	
	public void selectYesForNewsletter() {
		WebElement yesNewsletterOption = driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		yesNewsletterOption.click();
	}
	
	public String getFirstNameWarningMessage() {
		WebElement firstNameWarningMessage = driver.findElement(By.xpath("//*[@id='input-firstname']/following-sibling::div"));
		return firstNameWarningMessage.getText();
	}
	
	public String getLastNameWarningMessage() {
		WebElement lastNameWarningMessage = driver.findElement(By.xpath("//*[@id='input-lastname']/following-sibling::div"));
		return lastNameWarningMessage.getText();
	}
	
	public String getEmailWarningMessage() {
		WebElement emailWarningMessage = driver.findElement(By.xpath("//*[@id='input-email']/following-sibling::div"));
		return emailWarningMessage.getText();
	}
	
	public String getTelephoneWarningMessage() {
		WebElement telephoneWarningMessage = driver.findElement(By.xpath("//*[@id='input-telephone']/following-sibling::div"));
		return telephoneWarningMessage.getText();
	}
	
	public String getPasswordWarningMessage() {
		WebElement passwordWarningMessage = driver.findElement(By.xpath("//*[@id='input-password']/following-sibling::div"));
		return passwordWarningMessage.getText();
	}
	
	public String getPrivacyPolicyWarningMessage() {
		WebElement privacyPolicyWarningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
		return privacyPolicyWarningMessage.getText();
	}

}
