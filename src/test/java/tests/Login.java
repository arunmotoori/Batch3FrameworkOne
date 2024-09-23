package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {
	
	WebDriver driver;
	Properties prop;
	
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
		 driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 driver.findElement(By.linkText("Login")).click();
		 
	}
	
	
	@AfterMethod
	public void teardown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validemail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validpassword2"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
	
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("invalidpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedWarning));
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validpassword2"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedWarning));
	
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(getRandomValidEmail());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("invalidpassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedWarning));
		
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutCredentials() {
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedWarning));
		
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
