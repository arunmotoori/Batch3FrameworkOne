package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.LandingPage;
import pages.LoginPage;
import util.DataUtil;
import util.MyXLSReader;

public class Login {
	
	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	
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
		 loginPage = new LandingPage(driver).navigateToLoginPage();;
	
	}
	
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1,dataProvider="loginDataSupplier")
	public void verifyLoginWithValidCredentials(HashMap<String,String> hMap) {
		
		accountPage = loginPage.loginToApplication(hMap.get("Username"),hMap.get("Password"));
		Assert.assertTrue(accountPage.displayStatusOfLogoutOption());
	
	}
	
	@DataProvider(name="loginDataSupplier")
	public Object[][] dataProviderMethodForLoginTest() {
		String excelFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\TutorialsNinja.xlsx";
		MyXLSReader myXLSReader = null;
		Object[][] data = null;
		try {
			myXLSReader = new MyXLSReader(excelFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			data = DataUtil.getTestData(myXLSReader,"LoginTest","data");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.loginToApplication(generateEmailWithTimeStamp(),prop.getProperty("invalidpassword"));
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(loginPage.getWarningMessage().contains(expectedWarning));
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
	
		loginPage.loginToApplication(generateEmailWithTimeStamp(),prop.getProperty("validpassword2"));
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(loginPage.getWarningMessage().contains(expectedWarning));
	
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
	
		loginPage.loginToApplication(getRandomValidEmail(),prop.getProperty("invalidpassword"));
				
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(loginPage.getWarningMessage().contains(expectedWarning));
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		
		loginPage.loginToApplication("","");
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(loginPage.getWarningMessage().contains(expectedWarning));
		
	}
	
	
	public String getRandomValidEmail() {
		
		String[] validEmails = {"amotooricap1@gmail.com","amotooricap2@gmail.com","amotooricap2@gmail.com",
				"amotooricap4@gmail.com","amotooricap5@gmail.com","amotooricap6@gmail.com","amotooricap7@gmail.com",
				"amotooricap8@gmail.com"};
		
		return validEmails[new Random().nextInt(8)];
		
	}
	
	public String generateEmailWithTimeStamp() {
		return "amotoori"+new Date().toString().replaceAll("\\s","_").replaceAll("\\:","_")+"@gmail.com";
	}

}
