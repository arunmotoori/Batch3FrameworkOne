package temp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DemoThree {

	public static void main(String[] args) throws IOException {
		
		//Loading the properties file
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\java\\temp\\data.properties");
		FileReader fr = new FileReader(propFile);
		prop.load(fr);
		
		//Reading the data from the properties file
		System.out.println(prop.getProperty("firstname")); 
		System.out.println(prop.getProperty("lastname"));
		System.out.println(prop.getProperty("location"));
		System.out.println(prop.getProperty("experience"));
		System.out.println(prop.getProperty("working"));
		
	}

}
