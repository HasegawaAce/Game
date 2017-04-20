package fr.corentin.ui.characters;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;
import fr.corentin.ui.sprites.Bullet;
import fr.corentin.ui.sprites.Sprite;
import lombok.Getter;

/**
 * Classe repr�sentant un Joueur dans le jeu
 * 
 * @author Petit Gato
 *
 */
public final class Player extends Sprite {

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
	
	@Getter 
	private List<Bullet> bullets = new ArrayList<>();

	/**
	 * Initialisation du player
	 */
	public Player(ImageManager imageManager) {
		this.imageManager = imageManager;
		this.initPlayer(imageManager);
	}

	/**
	 * Cr�ation du player D�finition de la postion initiale
	 */
	private void initPlayer(ImageManager imageManager) {
		ImageIcon ii = new ImageIcon(imageManager.getPlayer());
		image = ii.getImage();
		x = 40;
		y = 60;
	}

	/**
	 * M�thode de d�placement du player
	 */
	@Override
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
	 * M�thode permettant de d�placer le joueur en fonction des touches
	 * enfonc�es.
	 * 
	 * @param event
	 *            l'�v�nement de touche pouss�e
	 */
	public void keyPressed(KeyEvent event) {
		// Code de la touche � l'origine de l'�v�nement.
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
		case KeyEvent.VK_SPACE:
			fire();
			break;
		default:
			break;
		}
	}

	/**
	 * M�thode remettant � 0 les variables de d�placement lorsque la touche est
	 * relach�e
	 * 
	 * @param vent
	 *            l'�v�nement de touche relach�e
	 */
	public void keyReleased(KeyEvent event) {
		// Code de la touche � l'origine de l'�v�nement.
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
	
	private void fire(){
		Bullet bullet = new Bullet(imageManager, x + (Game.TILE_SIZE * Game.SCALE)/2, y + (Game.TILE_SIZE * Game.SCALE)/2);
		bullet.setDx(1);
		bullets.add(bullet);
	}
}
