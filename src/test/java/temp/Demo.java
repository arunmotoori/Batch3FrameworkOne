package temp;

import java.util.Date;

public class Demo {

	public String sample() {
		
		return "amotoori"+new Date().toString().replaceAll("\\s","_").replaceAll("\\:","_")+"@gmail.com";
		
	}

}
