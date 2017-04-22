package fr.corentin.ui.characters.monsters;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.corentin.core.monstres.Djeen;
import fr.corentin.ui.image.ImageManager;

public class DjeenMonster extends Djeen implements MonsterUI {

	private ImageManager imageManager;
	
	private int currentCol = 0;

	private int nbCol = 3;

	private int sleepSprite = 0;

	private int currentRow = 4;
	
	@Override
	public ImageIcon getImageIcone(ImageManager imageManager) {
		this.imageManager = imageManager;
		return new ImageIcon(imageManager.getDjeen());
	}

	@Override
	public Image animationSprite(int row) {
		if (currentRow == row + 4) {
			if (sleepSprite++ > 10) {
				currentCol = (currentCol + 1) % nbCol;
				sleepSprite = 0;
			}
		} else {
			currentRow = row + 4;
			currentCol = 0;
			sleepSprite = 0;
		}

		ImageIcon ii = new ImageIcon(imageManager.getSheetMonster().crop(currentCol, currentRow, 32, 50));
		return ii.getImage();
	}

}