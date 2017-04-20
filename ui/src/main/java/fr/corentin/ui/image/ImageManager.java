package fr.corentin.ui.image;

import java.awt.image.BufferedImage;

import lombok.Getter;
import lombok.Setter;

/**
 * Gestionnaire d'image
 * @author Petit Gato
 *
 */
public class ImageManager {

	@Getter
	private SpriteSheet sheetPlayer;
	
	@Getter
//	@Setter
	private BufferedImage player;
	
	@Getter
	private BufferedImage monster;
	
	@Getter
	private BufferedImage bullet;
	
	public ImageManager(SpriteSheet... spriteSheet){
		sheetPlayer = spriteSheet[0];
		player = spriteSheet[0].crop(3, 2, 60, 65, 60, 60);
		monster = spriteSheet[1].crop(0,  0, 26, 40, 26, 40); 
		bullet = spriteSheet[2].crop(0, 0, 16, 16, 16, 16);
	}
	
}
