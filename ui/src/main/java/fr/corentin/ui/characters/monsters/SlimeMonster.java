package fr.corentin.ui.characters.monsters;

import javax.swing.ImageIcon;

import fr.corentin.core.monstres.Slime;
import fr.corentin.ui.image.ImageManager;

public class SlimeMonster extends Slime implements MonsterUI {

	@Override
	public ImageIcon getImageIcone(ImageManager imageManager) {
		return new ImageIcon(imageManager.getSlime());
	}

}
