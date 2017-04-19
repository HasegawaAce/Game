package fr.corentin.ui.characters;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;
import lombok.Getter;

/**
 * Classe représentant un Monstre dans le jeu
 * 
 * @author Petit Gato
 *
 */
public class Monster {

	/**
	 * Direction x
	 */
	private int dx;

	/**
	 * Direction y
	 */
	private int dy;

	/**
	 * Position x
	 */
	@Getter
	private int x;

	/**
	 * Position y
	 */
	@Getter
	private int y;

	/**
	 * Image du monstre
	 */
	@Getter
	private Image image;

	/**
	 * Initialisation du monstre
	 */
	public Monster(ImageManager imageManager) {
		this.initMonster(imageManager);
	}

	/**
	 * Création du monstre Définition de la postion initiale
	 */
	private void initMonster(ImageManager imageManager) {
		ImageIcon ii = new ImageIcon(imageManager.getMonster());
		image = ii.getImage();
		Random random = new Random();
		x = random.nextInt(Game.SCREEN_WIDTH) + 1;
		y = random.nextInt(Game.SCREEN_HEIGTH) + 1;
	}

	/**
	 * Méthode de déplacement du monstre (Ramdom)
	 */
	public void move() {
		Random random = new Random();
		// 10% de chance de déplacement
		int deplacement = random.nextInt(100) + 1;

		if (deplacement <= 10) {
			dx = random.nextInt(2) == 0 ? -1 : 1;
			dy = random.nextInt(2) == 0 ? -1 : 1;
			if ((dx > 0 && x <= (Game.SCREEN_WIDTH - Game.TILE_SIZE * Game.SCALE)) || (dx < 0 && x >= 0)) {
				x += dx;
			}
			if ((dy > 0 && y < (Game.SCREEN_HEIGTH - Game.TILE_SIZE * Game.SCALE)) || (dy < 0 && y >= 0)) {
				y += dy;
			}
		} else {
			dx = 0;
			dy = 0;
		}
	}

}
