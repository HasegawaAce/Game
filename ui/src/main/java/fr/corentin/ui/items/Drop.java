package fr.corentin.ui.items;

import java.util.Random;

public enum Drop {

	HEART, 
	ARME;

	public static Drop getDrop(){
		Random random = new Random();
		
		int randomHeart = random.nextInt(100);
		int randomArme = random.nextInt(100);
		
		if(randomHeart <= 5){
			return Drop.HEART;
		}
		
//		if(randomArme <= 1) {
//			return Drop.ARME;
//		}
		
		return null;
	}
}
