package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		
	}
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifySearchingForExisitingProduct() {
		
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		
	}
	
	@Test(priority=2)
	public void verifySearchingForNonExistingProduct() {
		
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText(), expectedMessage);
		
	}
	
	@Test(priority=3)
	public void verifySearchingWithoutAnyProduct() {
		
		driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
		
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).getText(), expectedMessage);
		
	}

}
