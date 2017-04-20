package fr.corentin.ui.sprites;

import javax.swing.ImageIcon;

import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;

/**
 * Classe gérant le projectile du player
 * @author Petit Gato
 *
 */

public class Bullet extends Sprite {

	private int vitesse;
	
	@Override
	public void move() {
		setX(getX() + getDx() * vitesse);
		setY(getY() + getDy() * vitesse);
	}
	
	public Bullet(ImageManager imageManager, int x, int y, int vitesse){
		initBullet(imageManager, x, y, vitesse);
	}
	
	private void initBullet(ImageManager imageManager, int x, int y, int vitesse) {
		ImageIcon ii = new ImageIcon(imageManager.getBullet());
		setImage(ii.getImage());
		setX(x);
		setY(y);
		setWidth(Game.TILE_SIZE);
		setHeight(Game.TILE_SIZE);
		this.vitesse = vitesse;
	}	
}
