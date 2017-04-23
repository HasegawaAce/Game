package fr.corentin.ui.items;

import fr.corentin.ui.image.ImageManager;
import fr.corentin.ui.sprites.Sprite;

public abstract class ItemDrop extends Sprite {

	protected ImageManager imageManager;
	
	@Override
	public void move() {
		this.animationSprite(0);
	}
}
