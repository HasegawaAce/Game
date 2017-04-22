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
	private BufferedImage momie;
	
	public ImageManager(SpriteSheet... spriteSheet){
		sheetPlayer = spriteSheet[0];
		player = spriteSheet[0].crop(3, 2, 64, 64, 64, 64);
		monster = spriteSheet[1].crop(0,  0, 26, 40, 26, 40); 
		bullet = spriteSheet[2].crop(0, 0, 16, 16, 16, 16);
		slime = spriteSheet[3].crop(0, 0, 33, 35, 33, 35);
		leeche = spriteSheet[4].crop(0, 0, 33, 33, 33, 33);
		momie = spriteSheet[5].crop(0, 0, 33, 33, 33, 33);
	}
	
}
