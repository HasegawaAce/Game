package fr.corentin.ui.sprites;

import javax.swing.ImageIcon;

import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe gérant le projectile du player
 * @author Petit Gato
 *
 */

public class Bullet extends Sprite {

	private boolean visible = true;

	@Override
	public void move() {
		x+=dx+1;
	}
	
	public Bullet(ImageManager imageManager, int x, int y){
		initBullet(imageManager, x, y);
	}
	
	private void initBullet(ImageManager imageManager, int x, int y) {
		ImageIcon ii = new ImageIcon(imageManager.getBullet());
		image = ii.getImage();
		this.x = x;
		this.y = y;
	}
	 
	/**
	 * Direction x
	 */
	@Setter
	protected int dx;

	/**
	 * Direction y
	 */
	protected int dy;

	/**
	 * Position x
	 */
	@Getter
	protected int x;

	/**
	 * Position y
	 */
	@Getter
	protected int y;
	

	
	
}
