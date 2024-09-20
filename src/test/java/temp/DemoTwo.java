package temp;

import java.util.Random;

public class DemoTwo {

	public static void main(String[] args) {
		
		String[] validEmails = {"amotooricap1@gmail.com","amotooricap2@gmail.com","amotooricap2@gmail.com",
				"amotooricap4@gmail.com","amotooricap5@gmail.com","amotooricap6@gmail.com","amotooricap7@gmail.com",
				"amotooricap8@gmail.com"};
		
		Random r = new Random();
		
		int randomIndex = r.nextInt(8);
		
		//return validEmails[randomIndex];

	}

}
