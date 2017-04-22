package fr.corentin.ui.characters.monsters;

import javax.swing.ImageIcon;

import fr.corentin.core.monstres.Momie;
import fr.corentin.ui.image.ImageManager;

public class MomieMonster extends Momie implements MonsterUI {

	@Override
	public ImageIcon getImageIcone(ImageManager imageManager) {
		return new ImageIcon(imageManager.getMomie());
	}

}

