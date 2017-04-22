package fr.corentin.ui.characters;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import fr.corentin.core.Joueur;
import fr.corentin.ui.game.Armurerie;
import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;
import fr.corentin.ui.sprites.Bullet;
import fr.corentin.ui.sprites.Sprite;
import lombok.Getter;

/**
 * Classe représentant un Joueur dans le jeu
 * 
 * @author Petit Gato
 *
 */
public final class Player extends Sprite{

	@Getter
	private Map<String, Long> cooldowns = new HashMap<>();
	
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
	
	@Getter
	private Joueur joueur;
	
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
		setImage(ii.getImage());
		setX(40);
		setY(60);

		setXOffset(15);
		setYOffset(10);

		setWidth(Game.TILE_SIZE * Game.SCALE);
		setHeight(Game.TILE_SIZE * Game.SCALE);

		joueur = new Joueur();
		joueur.setArme(Armurerie.MITRAILLEUSE.getInstance());
		
		cooldowns.put("tir", 0l);
		//cooldowns.put("create", 0l);
		
		joueur.setNombreVie(6);
		joueur.setNombreVieMax(6);
	}

	/**
	 * Méthode de déplacement du player
	 */
	@Override
	public void move() {
		super.move();
		
		if(cooldowns.containsKey("create") && System.currentTimeMillis() - cooldowns.get("create") > joueur.getArme().getTimeBulletCreate()){
			joueur.getArme().addBalle();
			if(joueur.getArme().getNombreBalle() == joueur.getArme().getNombreBalleMax()){
				cooldowns.remove("create");
			} else {
				cooldowns.put("create", System.currentTimeMillis());
			}
		}
		
		int position[] = {this.getX(), this.getY()};
		
		setChanged();
		
		this.notifyObservers(position);

	}

	public void animationSprite(int row) {
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

		ImageIcon ii = new ImageIcon(imageManager.getSheetPlayer().crop(currentCol, currentRow, 64, 64));
		setImage(ii.getImage());
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
			setDx(-2);
			break;
		case KeyEvent.VK_RIGHT:
			setDx(2);
			break;
		case KeyEvent.VK_UP:
			setDy(-2);
			break;
		case KeyEvent.VK_DOWN:
			setDy(2);
			break;
		case KeyEvent.VK_SPACE:
			fire();
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
			setDx(0);
			break;
		case KeyEvent.VK_RIGHT:
			setDx(0);
			break;
		case KeyEvent.VK_UP:
			setDy(0);
			break;
		case KeyEvent.VK_DOWN:
			setDy(0);
			break;
		default:
			break;
		}
	}

	private void fire() {

		if (joueur.getArme().getNombreBalle() > 0 && System.currentTimeMillis() - cooldowns.get("tir") > joueur.getArme().getCoolDown()) {
			joueur.getArme().setNombreBalle(joueur.getArme().getNombreBalle() - 1);

			Bullet bullet = new Bullet(imageManager, getX() + (Game.TILE_SIZE * Game.SCALE) / 2,
					getY() + (Game.TILE_SIZE * Game.SCALE) / 2, joueur.getArme().getVitesse());
			if (getDx() == 0 && getDy() == 0) {
				bullet.setDx(getLastDx());
				bullet.setDy(getLastDy());
			} else {
				bullet.setDx(getDx());
				bullet.setDy(getDy());
			}
			
			bullets.add(bullet);
			cooldowns.put("tir", System.currentTimeMillis());
			
			if(!cooldowns.containsKey("create")){
				cooldowns.put("create", System.currentTimeMillis());
			}
		}
	}

	public void lostLife(int nbrVie) {
		this.joueur.setNombreVie(this.joueur.getNombreVie() - nbrVie);
		if (this.joueur.getNombreVie() <= 0) {
			this.setVisible(false);
		}
	}	
}

