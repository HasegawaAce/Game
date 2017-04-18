package fr.corentin.ui.image;

import java.awt.image.BufferedImage;

import lombok.Getter;

/**
 * Gestionnaire d'image
 * @author primael
 *
 */
public class ImageManager {

	@Getter
	private BufferedImage player;
	
	public ImageManager(SpriteSheet spriteSheet){
		player = spriteSheet.crop(0, 0, 16, 16);
	}
}
