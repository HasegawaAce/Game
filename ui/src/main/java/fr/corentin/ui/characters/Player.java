package fr.corentin.ui.characters;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import fr.corentin.core.Joueur;
import fr.corentin.ui.game.Armurerie;
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

	private int lastDx;

	private int lastDy = 1;

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
	 * Cr�ation du player D�finition de la postion initiale
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
	 * M�thode de d�placement du player
	 */
	@Override
	public void move() {

		if ((getDx() > 0 && getX() <= (Game.SCREEN_WIDTH - Game.TILE_SIZE * Game.SCALE))
				|| (getDx() < 0 && getX() >= 0)) {
			setX(getX() + getDx());
			if (getDx() > 0) {
				animationSprite(3);
			}
			if (getDx() < 0) {
				animationSprite(1);
			}
			if (getDy() == 0) {
				lastDx = getDx();
				lastDy = 0;
			}
		}
		if ((getDy() > 0 && getY() < (Game.SCREEN_HEIGTH - Game.TILE_SIZE * Game.SCALE))
				|| (getDy() < 0 && getY() >= 0)) {
			setY(getY() + getDy());
			if (getDy() > 0) {
				animationSprite(2);
			}
			if (getDy() < 0) {
				animationSprite(0);
			}
			if (getDx() == 0) {
				lastDx = 0;
				lastDy = getDy();
			}
		}
		
		if(cooldowns.containsKey("create") && System.currentTimeMillis() - cooldowns.get("create") > joueur.getArme().getTimeBulletCreate()){
			joueur.getArme().addBalle();
			if(joueur.getArme().getNombreBalle() == joueur.getArme().getNombreBalleMax()){
				cooldowns.remove("create");
			} else {
				cooldowns.put("create", System.currentTimeMillis());
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
		setImage(ii.getImage());
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
			setDx(-1);
			break;
		case KeyEvent.VK_RIGHT:
			setDx(1);
			break;
		case KeyEvent.VK_UP:
			setDy(-1);
			break;
		case KeyEvent.VK_DOWN:
			setDy(1);
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
				bullet.setDx(lastDx);
				bullet.setDy(lastDy);
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

	@Override
	public synchronized void addObserver(Observer arg0) {
		// TODO Auto-generated method stub
		super.addObserver(arg0);
	}

	@Override
	public synchronized void deleteObserver(Observer arg0) {
		// TODO Auto-generated method stub
		super.deleteObserver(arg0);
	}

	@Override
	public void notifyObservers(Object arg0) {
		// TODO Auto-generated method stub
		super.notifyObservers(arg0);
	}
	
	
}

