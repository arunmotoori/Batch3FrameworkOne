package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	
	WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean displayStatusOfProduct() {
		WebElement existingProduct = driver.findElement(By.linkText("HP LP3065"));
		return existingProduct.isDisplayed();
	}
	
	public String getSearchResultsMessage() {
		WebElement searchResultsMessage = driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p"));
		return searchResultsMessage.getText();
	}

}
