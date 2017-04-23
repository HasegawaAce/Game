package fr.corentin.ui.items;

import fr.corentin.ui.image.ImageManager;

public class ItemsFactory {

	public static ItemDrop createItem(Drop drop, ImageManager imageManager, int x, int y){
		switch (drop) {
		case HEART:
			return new HeartDrop(imageManager, x, y);
		default:
			break;
		}
		
		return null;
	}
}
