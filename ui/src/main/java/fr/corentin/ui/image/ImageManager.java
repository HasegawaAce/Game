package fr.corentin.ui.image;

import java.awt.image.BufferedImage;

import lombok.Getter;

/**
 * Gestionnaire d'image
 * @author Petit Gato
 *
 */
public class ImageManager {

	@Getter
	private SpriteSheet sheetPlayer;
	
	@Getter
	private SpriteSheet sheetMonster;

	@Getter
	private SpriteSheet sheetHeart;
	
	@Getter
	private SpriteSheet sheetSlime;
	
	@Getter
	private SpriteSheet sheetLeeche;
	
	@Getter
	private SpriteSheet sheetMomie;
	
	@Getter
	private BufferedImage player;
	
	@Getter
	private BufferedImage monster;
	
	@Getter
	private BufferedImage bullet;
	
	@Getter
	private BufferedImage slime;
	
	@Getter
	private BufferedImage leeche;
	
	@Getter
	private BufferedImage squelette;
	
	@Getter
	private BufferedImage djeen;
	
	@Getter
	private BufferedImage momie;
	
	@Getter
	private BufferedImage ogre;
	
	@Getter
	private BufferedImage mort;
	
	@Getter
	private BufferedImage gargoyle;
	
	@Getter
	private BufferedImage heart;
	
	@Getter
	private BufferedImage fond;
	
	public ImageManager(SpriteSheet... spriteSheet){
		sheetPlayer = spriteSheet[0];
		sheetMonster = spriteSheet[1];
		sheetSlime = spriteSheet[3];
		sheetLeeche = spriteSheet[4];
		sheetMomie = spriteSheet[5];
		sheetHeart = spriteSheet[6];
		
		player = spriteSheet[0].crop(3, 2, 64, 64);
		monster = spriteSheet[1].crop(0,  0, 33, 51); 
		bullet = spriteSheet[2].crop(0, 0, 16, 16);
		slime = spriteSheet[3].crop(0, 0, 33, 35);
		leeche = spriteSheet[4].crop(0, 0, 33, 33);
		momie = spriteSheet[5].crop(0, 0, 33, 33);
		squelette = spriteSheet[1].crop(3,  0, 32, 50);
		djeen = spriteSheet[1].crop(0,  4, 32, 50);
		ogre = spriteSheet[1].crop(6,  0, 32, 50);
		mort = spriteSheet[1].crop(6,  4, 32, 50);
		gargoyle = spriteSheet[1].crop(9,  0, 32, 50);
		heart = spriteSheet[6].crop(0,  0, 24, 34);
	}

}
