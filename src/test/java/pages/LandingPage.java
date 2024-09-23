package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	WebElement myAccountDropMenu = driver.findElement(By.xpath("//span[text()='My Account']"));
	
	WebElement registerOption = driver.findElement(By.linkText("Register"));
	
	public void clickOnMyAccountOption() {
		myAccountDropMenu.click();
	}
	
	public WebDriver selectRegisterOption() {
		registerOption.click();
		return driver;
	}
	
}
