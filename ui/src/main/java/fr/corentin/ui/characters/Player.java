package fr.corentin.ui.characters;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;
import lombok.Getter;

/**
 * Classe représentant un Joueur dans le jeu
 * 
 * @author Petit Gato
 *
 */
public class Player {

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
	 * Image du joueur
	 */
	@Getter
	private Image image;

	/**
	 * Colonne courante du spriteSheet
	 */
	private int currentCol = 3;

	private int nbCol = 7;

	private int sleepSprite = 0;

	/**
	 * Ligne courante du spriteSheet
	 */
	private int currentRow = 2;

	private ImageManager imageManager;

	/**
	 * Initialisation du player
	 */
	public Player(ImageManager imageManager) {
		this.imageManager = imageManager;
		this.initPlayer(imageManager);
	}

	/**
	 * Création du player Définition de la postion initiale
	 */
	private void initPlayer(ImageManager imageManager) {
		ImageIcon ii = new ImageIcon(imageManager.getPlayer());
		image = ii.getImage();
		x = 40;
		y = 60;
	}

	/**
	 * Méthode de déplacement du player
	 */
	public void move() {

		if ((dx > 0 && x <= (Game.SCREEN_WIDTH - Game.TILE_SIZE * Game.SCALE)) || (dx < 0 && x >= 0)) {
			x += dx;
			if (dx > 0) {
				animationSprite(3);
			}
			if (dx < 0) {
				animationSprite(1);
			}
		}
		if ((dy > 0 && y < (Game.SCREEN_HEIGTH - Game.TILE_SIZE * Game.SCALE)) || (dy < 0 && y >= 0)) {
			y += dy;
			if (dy > 0) {
				animationSprite(2);
			}
			if (dy < 0) {
				animationSprite(0);
			}
		}
	}

	private void animationSprite(int row) {
		if (currentRow == row) {
			if (sleepSprite++ > 10) {
				currentCol = (currentCol + 1) % nbCol;
				sleepSprite = 0;
			}
		} else {
			currentRow = row;
			currentCol = 3;
			sleepSprite = 0;
		}

		ImageIcon ii = new ImageIcon(imageManager.getSheetPlayer().crop(currentCol, currentRow, 64, 64, 64, 64));
		image = ii.getImage();
	}

	/**
	 * Méthode permettant de déplacer le joueur en fonction des touches
	 * enfoncées.
	 * 
	 * @param event
	 *            l'évènement de touche poussée
	 */
	public void keyPressed(KeyEvent event) {
		// Code de la touche à l'origine de l'évènement.
		int key = event.getKeyCode();

		switch (key) {
		case KeyEvent.VK_LEFT:
			dx = -1;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 1;
			break;
		case KeyEvent.VK_UP:
			dy = -1;
			break;
		case KeyEvent.VK_DOWN:
			dy = 1;
			break;
		default:
			break;
		}
	}

	/**
	 * Méthode remettant à 0 les variables de déplacement lorsque la touche est
	 * relachée
	 * 
	 * @param vent
	 *            l'évènement de touche relachée
	 */
	public void keyReleased(KeyEvent event) {
		// Code de la touche à l'origine de l'évènement.
		int key = event.getKeyCode();

		switch (key) {
		case KeyEvent.VK_LEFT:
			dx = 0;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 0;
			break;
		case KeyEvent.VK_UP:
			dy = 0;
			break;
		case KeyEvent.VK_DOWN:
			dy = 0;
			break;
		default:
			break;
		}
	}
}
