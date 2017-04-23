package fr.corentin.ui.items;

import javax.swing.ImageIcon;

import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;

public class HeartDrop extends ItemDrop {


	private ImageManager imageManager;

	private int currentCol = 0;

	private int nbCol = 12;

	private int sleepSprite = 0;

	private int currentRow = 0;

	public HeartDrop(ImageManager imageManager, int x, int y) {
		this.imageManager = imageManager;
		
		setX(x + (Game.TILE_SIZE * Game.SCALE) /2);
		setY(y + (Game.TILE_SIZE * Game.SCALE) /2);
		
		setXOffset(0);
		setYOffset(0);
		
		setWidth(24);
		setHeight(24);
				
		ImageIcon ii = new ImageIcon(imageManager.getHeart());
		setImage(ii.getImage());
		
	}

	@Override
	public void animationSprite(int row) {

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

		ImageIcon ii = new ImageIcon(imageManager.getSheetHeart().crop(currentCol, currentRow, 24, 34));
		setImage(ii.getImage());

	}


}
