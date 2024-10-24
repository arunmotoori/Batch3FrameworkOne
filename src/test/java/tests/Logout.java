package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountLogoutPage;
import pages.AccountPage;
import pages.LandingPage;
import pages.LoginPage;

public class Logout extends Base {
	
	public WebDriver driver;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	AccountLogoutPage accountLogoutPage;
	
	@BeforeMethod
	public void setup() {
		
		new Logout();
		driver = openApplicationURLInBrowser(prop.getProperty("browser"));
		landingPage = new LandingPage(driver);
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyLogoutFromMyAccountMenu() {
		
		int i = 1;
		
		if(i==1) {
			throw new SkipException("The value of i is 1 and hence this test got skipped");
		}
		
		accountLogoutPage = landingPage.navigateToLoginPage().loginToApplication(prop.getProperty("validemail"),prop.getProperty("validpassword2")).logoutFromApplication();
		
		String expectedPageTitle = "Account Logout";
		Assert.assertEquals(driver.getTitle(),expectedPageTitle);
		
		Assert.assertTrue(accountLogoutPage.checkForLoginOption());
		
	}
	
}
