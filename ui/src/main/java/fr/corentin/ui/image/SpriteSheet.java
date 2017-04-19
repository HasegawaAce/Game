package fr.corentin.ui.image;

import java.awt.image.BufferedImage;

/** 
 *  Classe représentant la feuille de sprite
 * @author Petit Gato
 *
 */
public class SpriteSheet {

	private BufferedImage sheet;
	
	/**
	 * Constructeur de la classe
	 * @param sheet feuille de sprite
	 */
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
	}
	
	/**
	 * Méthode permettant de récupérer un sprite du sprite sheet.
	 * @param col colonne du sprite (commence à 0)
	 * @param row ligne du sprite (commence à 0)
	 * @param widht largeur du sprite
	 * @param height hauteur du sprite
	 * @return L'image cropper
	 */
	public BufferedImage crop(int col, int row, int colsize, int rowsize, int widht, int height){
		return sheet.getSubimage(col * colsize, row * rowsize, widht, height);
	}
}
