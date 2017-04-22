package fr.corentin.ui.characters.monsters;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.corentin.core.monstres.Gargoyle;
import fr.corentin.ui.image.ImageManager;

public class GargoyleMonster extends Gargoyle implements MonsterUI {

	private ImageManager imageManager;
	
	private int currentCol = 9;

	private int nbCol = 3;

	private int sleepSprite = 0;

	/**
	 * Ligne courante du spriteSheet
	 */
	private int currentRow = 0;
	
	@Override
	public ImageIcon getImageIcone(ImageManager imageManager) {
		this.imageManager = imageManager;
		return new ImageIcon(imageManager.getGargoyle());
	}

	@Override
	public Image animationSprite(int row) {
		if (currentRow == row) {
			if (sleepSprite++ > 10) {
				currentCol = (currentCol + 1) % nbCol + 9;
				sleepSprite = 0;
			}
		} else {
			currentRow = row;
			currentCol = 9;
			sleepSprite = 0;
		}

		ImageIcon ii = new ImageIcon(imageManager.getSheetMonster().crop(currentCol, currentRow, 32, 50));
		return ii.getImage();
	}

}