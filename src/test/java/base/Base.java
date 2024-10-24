package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	
	public Base() {
		loadPropertiesFile();
	}

	public void loadPropertiesFile() {
		 try {
			 prop = new Properties();
			 File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\properties\\projectdata.properties");
			 FileReader fr = new FileReader(propFile);
			 prop.load(fr);
		 }catch(IOException e){
			 e.printStackTrace();
		 }
	}
	
	public WebDriver openApplicationURLInBrowser(String browserName) {
		 
		 if(browserName.equals("chrome")) {
			 driver = new ChromeDriver();
		 }else if(browserName.equals("firefox")) {
			 driver = new FirefoxDriver();
		 }else if(browserName.equals("edge")) {
			 driver = new EdgeDriver();
		 }else if(browserName.equals("ie")) {
			 driver = new InternetExplorerDriver();
		 }else if(browserName.equals("safari")) {
			 driver = new SafariDriver();
		 }
	
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 driver.get(prop.getProperty("url"));
		 
		 return driver;
		
	}
	
	public String generateEmailWithTimeStamp() {
		return "amotoori"+new Date().toString().replaceAll("\\s","_").replaceAll("\\:","_")+"@gmail.com";
	}
	
}
