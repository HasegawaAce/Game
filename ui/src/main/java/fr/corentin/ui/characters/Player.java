package fr.corentin.ui.characters;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import fr.corentin.ui.image.ImageManager;
import lombok.Getter;

/**
 * Classe repr�sentant un Joueur dans le jeu
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
	 * Cr�ation du player
	 * D�finition de la postion initiale
	 */
	private void initPlayer(ImageManager imageManager){
		ImageIcon ii = new ImageIcon(imageManager.getPlayer());
		image = ii.getImage();
		x = 40;
		y = 60;
	}
	
	/**
	 * M�thode de d�placement du player
	 */
	public void move(){
		x += dx;
		y += dy;
	}
	
	/**
	 * M�thode permettant de d�placer le joueur en fonction des touches enfonc�es.
	 * @param event l'�v�nement de touche pouss�e
	 */
	public void keyPressed(KeyEvent event){
		//Code de la touche � l'origine de l'�v�nement.
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
	 * M�thode remettant � 0 les variables de d�placement lorsque la touche est relach�e
	 * @param vent l'�v�nement de touche relach�e
	 */
	public void keyReleased(KeyEvent event){
		//Code de la touche � l'origine de l'�v�nement.
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
