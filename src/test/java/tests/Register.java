package tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LandingPage;
import pages.RegisterPage;

public class Register {
	
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccountOption();
		driver = landingPage.selectRegisterOption();

	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisterAccountUsingMandatoryFields() {
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(prop.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedTitle = "Your Account Has Been Created!";
		Assert.assertEquals(driver.getTitle(),expectedTitle);
	
	}
	
	@Test(priority=2)
	public void verifyRegisterAccountUsingAllFields() {
				
		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstname"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastname"));
		driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validpassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedTitle = "Your Account Has Been Created!";
		Assert.assertEquals(driver.getTitle(),expectedTitle);
		
	}
	
	@Test(priority=3)
	public void verifyRegisterAccountWithoutAnyDetails() {
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-firstname']/following-sibling::div")).getText(), expectedFirstNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-lastname']/following-sibling::div")).getText(), expectedLastNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-email']/following-sibling::div")).getText(), expectedEmailWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-telephone']/following-sibling::div")).getText(), expectedTelephoneWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='input-password']/following-sibling::div")).getText(), expectedPasswordWarning);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText().contains(expectedPrivacyPolicyWarning));
		
	}
	
	public String generateEmailWithTimeStamp() {
		return "amotoori"+new Date().toString().replaceAll("\\s","_").replaceAll("\\:","_")+"@gmail.com";
	}
	

}
