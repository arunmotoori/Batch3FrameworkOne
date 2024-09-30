package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LandingPage;
import pages.SearchPage;

public class Search {
	
	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	SearchPage searchPage;
	
	@BeforeMethod
	public void setup() {
		
		 try {
			 prop = new Properties();
			 File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\properties\\projectdata.properties");
			 FileReader fr = new FileReader(propFile);
			 prop.load(fr);
		 }catch(IOException e){
			 e.printStackTrace();
		 }
		
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		
	}
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifySearchingForExisitingProduct() {
		
		searchPage = landingPage.searchProduct(prop.getProperty("existingproduct"));
				
		searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.displayStatusOfProduct());
		
	}
	
	@Test(priority=2)
	public void verifySearchingForNonExistingProduct() {
	
		searchPage = landingPage.searchProduct(prop.getProperty("nonexistingproduct"));
	
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getSearchResultsMessage(), expectedMessage);
		
	}
	
	@Test(priority=3)
	public void verifySearchingWithoutAnyProduct() {
		
		searchPage = landingPage.searchProduct("");
	
		String expectedMessage = "There is no product that matches the search criteria.";
		Assert.assertEquals(searchPage.getSearchResultsMessage(), expectedMessage);
		
	}

}
