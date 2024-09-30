package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AccountLogoutPage;
import pages.AccountPage;
import pages.LandingPage;
import pages.LoginPage;

public class Logout {
	
	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	AccountLogoutPage accountLogoutPage;
	
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
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyLogoutFromMyAccountMenu() {
		
		accountLogoutPage = landingPage.navigateToLoginPage().loginToApplication(prop.getProperty("validemail"),prop.getProperty("validpassword2")).logoutFromApplication();
		
		String expectedPageTitle = "Account Logout";
		Assert.assertEquals(driver.getTitle(),expectedPageTitle);
		
		Assert.assertTrue(accountLogoutPage.checkForLoginOption());
		
	}
	
}
