package fr.corentin.ui.characters.monsters;

import javax.swing.ImageIcon;

import fr.corentin.core.monstres.Leeche;
import fr.corentin.ui.image.ImageManager;

public class LeecheMonster extends Leeche implements MonsterUI {

	@Override
	public ImageIcon getImageIcone(ImageManager imageManager) {
		return new ImageIcon(imageManager.getLeeche());
	}

}
