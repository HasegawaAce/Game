package fr.corentin.ui.characters;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import fr.corentin.ui.image.ImageManager;
import lombok.Getter;

/**
 * Classe représentant un Joueur dans le jeu
 * @author primael
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
	 * Initialisation du player
	 */
	public Player(ImageManager imageManager){
		this.initPlayer(imageManager);
	}
	
	/**
	 * Création du player
	 * Définition de la postion initiale
	 */
	private void initPlayer(ImageManager imageManager){
		ImageIcon ii = new ImageIcon(imageManager.getPlayer());
		image = ii.getImage();
		x = 40;
		y = 60;
	}
	
	/**
	 * Méthode de déplacement du player
	 */
	public void move(){
		x += dx;
		y += dy;
	}
	
	/**
	 * Méthode permettant de déplacer le joueur en fonction des touches enfoncées.
	 * @param event l'évènement de touche poussée
	 */
	public void keyPressed(KeyEvent event){
		//Code de la touche à l'origine de l'évènement.
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
	 * Méthode remettant à 0 les variables de déplacement lorsque la touche est relachée
	 * @param vent l'évènement de touche relachée
	 */
	public void keyReleased(KeyEvent event){
		//Code de la touche à l'origine de l'évènement.
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
