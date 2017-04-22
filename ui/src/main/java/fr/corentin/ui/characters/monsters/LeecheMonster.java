package fr.corentin.ui.characters.monsters;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.corentin.core.monstres.Leeche;
import fr.corentin.ui.image.ImageManager;

public class LeecheMonster extends Leeche implements MonsterUI {

	private ImageManager imageManager;
	
	private int currentCol = 0;

	private int nbCol = 3;

	private int sleepSprite = 0;

	/**
	 * Ligne courante du spriteSheet
	 */
	private int currentRow = 0;
	
	@Override
	public ImageIcon getImageIcone(ImageManager imageManager) {
		this.imageManager = imageManager;
		return new ImageIcon(imageManager.getLeeche());
	}

	@Override
	public Image animationSprite(int row) {
		if (currentRow == row) {
			if (sleepSprite++ > 10) {
				currentCol = (currentCol + 1) % nbCol;
				sleepSprite = 0;
			}
		} else {
			currentRow = row;
			currentCol = 0;
			sleepSprite = 0;
		}

		ImageIcon ii = new ImageIcon(imageManager.getSheetLeeche().crop(currentCol, currentRow, 33, 33));
		return ii.getImage();
	}

}
