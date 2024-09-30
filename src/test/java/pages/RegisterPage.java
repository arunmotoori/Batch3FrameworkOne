package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsletterOption;
	
	@FindBy(xpath="//*[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath="//*[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath="//*[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMessage;
	
	@FindBy(xpath="//*[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath="//*[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMessage;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarningMessage;
	
	public void registerAnAccount(String firstNameData,String lastNameData,String emailData,String telephoneData,String passwordData,boolean newsletter,boolean details) {
		enterFirstName(firstNameData);
		enterLastName(lastNameData);
		enterEmailAddress(emailData);
		enterTelephoneNumber(telephoneData);
		enterPassword(passwordData);
		enterConfirmPassword(passwordData);
		if(newsletter) {
			selectYesForNewsletter();
		}
		if(details) {
			selectPrivacyPolicy();
		}
		clickOnContinueButton();		
	}
	

	public void enterFirstName(String firstNameData) {
		firstNameField.sendKeys(firstNameData);	
	}
	
	public void enterLastName(String lastNameData) {
		
		lastNameField.sendKeys(lastNameData);
	}
	
	public void enterEmailAddress(String emailData) {
		
		emailField.sendKeys(emailData);
	}
	
	public void enterTelephoneNumber(String telephoneData) {
		
		telephoneField.sendKeys(telephoneData);
	}
	
	public void enterPassword(String passwordData) {
		
		passwordField.sendKeys(passwordData);
	}
	
	public void enterConfirmPassword(String passwordData) {
		
		passwordConfirmField.sendKeys(passwordData);
	}
	
	public void selectPrivacyPolicy() {
		
		privacyPolicyField.click();
	}
	
	public void clickOnContinueButton() {
		
		continueButton.click();
	}
	
	public void selectYesForNewsletter() {
		
		yesNewsletterOption.click();
	}
	
	public String getFirstNameWarningMessage() {
		
		return firstNameWarningMessage.getText();
	}
	
	public String getLastNameWarningMessage() {
		
		return lastNameWarningMessage.getText();
	}
	
	public String getEmailWarningMessage() {
		
		return emailWarningMessage.getText();
	}
	
	public String getTelephoneWarningMessage() {
		
		return telephoneWarningMessage.getText();
	}
	
	public String getPasswordWarningMessage() {
		
		return passwordWarningMessage.getText();
	}
	
	public String getPrivacyPolicyWarningMessage() {
		
		return privacyPolicyWarningMessage.getText();
	}

}
