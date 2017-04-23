package fr.corentin.ui.characters.monsters;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.corentin.ui.image.ImageManager;

public interface MonsterUI {

	ImageIcon getImageIcone(ImageManager imageManager);
	
	Image animationSprite(int i);
	
}
