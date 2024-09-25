package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnMyAccountOption() {
		WebElement myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
		myAccountDropMenu.click();
	}
	
	public WebDriver selectRegisterOption() {
		WebElement registerOption = driver.findElement(By.linkText("Register"));
		registerOption.click();
		return driver;
	}
	
	public WebDriver selectLoginOption(){
		WebElement loginOption = driver.findElement(By.linkText("Login"));
		loginOption.click();
		return driver;
	}
	
	public void enterSearchTermIntoSearchBoxField(String searchTermData) {
		WebElement searchBoxField = driver.findElement(By.name("search"));
		searchBoxField.sendKeys(searchTermData);
	}
	
	public WebDriver clickOnSearchButton() {
		WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
		searchButton.click();
		return driver;
	}
	
}
