package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.LandingPage;
import pages.RegisterPage;

public class Register extends Base {
	
	RegisterPage registerPage;
	
	@BeforeMethod
	public void setup() {
		 
		new Register();
		driver = openApplicationURLInBrowser(prop.getProperty("browser"));
		registerPage = new LandingPage(driver).navigateToRegisterPage();

	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountUsingMandatoryFields() {
	
		registerPage.registerAnAccount(prop.getProperty("firstname"), prop.getProperty("lastname"),generateEmailWithTimeStamp(),prop.getProperty("telephone"),prop.getProperty("validpassword"),false,true);
	   		
		String expectedTitle = "Your Account Has Been Created!";
		Assert.assertEquals(driver.getTitle(),expectedTitle);
	
	}
	
	@Test(priority=2)
	public void verifyRegisterAccountUsingAllFields() {
	     
		registerPage.registerAnAccount(prop.getProperty("firstname"), prop.getProperty("lastname"),generateEmailWithTimeStamp(),prop.getProperty("telephone"),prop.getProperty("validpassword"),true,true);
		
		String expectedTitle = "Your Account Has Been Created!";
		Assert.assertEquals(driver.getTitle(),expectedTitle);
		
	}
	
	@Test(priority=3)
	public void verifyRegisterAccountWithoutAnyDetails() {
	
		registerPage.registerAnAccount("","","","","",false,false);

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		
		Assert.assertEquals(registerPage.getFirstNameWarningMessage(), expectedFirstNameWarning);
		Assert.assertEquals(registerPage.getLastNameWarningMessage(), expectedLastNameWarning);
		Assert.assertEquals(registerPage.getEmailWarningMessage(), expectedEmailWarning);
		Assert.assertEquals(registerPage.getTelephoneWarningMessage(), expectedTelephoneWarning);
		Assert.assertEquals(registerPage.getPasswordWarningMessage(), expectedPasswordWarning);
		Assert.assertTrue(registerPage.getPrivacyPolicyWarningMessage().contains(expectedPrivacyPolicyWarning));
		
	}
	
	
	

}
