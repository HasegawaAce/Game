package fr.corentin.ui.game;

import java.awt.EventQueue;

import fr.corentin.ui.frames.MovingSprite;

public class Game {

	/**
	 * delimite la largeur de la fenetre 
	 */
	public static final int SCREEN_WIDTH = 1000; 
	
	/**
	 * delimite la heuteur de la fenetre 
	 */
	public static final int SCREEN_HEIGTH = 700; 
	
	/**
	 * taille de personnage de la fueille de sprite (spritesheet) 
	 */
	public static final int TILE_SIZE = 16; 
	
	public static final int SCALE = 4;

	public static final int MONSTERS = 10;

	public static final boolean DEBUG = true; 
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				MovingSprite movingSprite = new MovingSprite();
				movingSprite.setVisible(true);
			}
		});
	}
}
