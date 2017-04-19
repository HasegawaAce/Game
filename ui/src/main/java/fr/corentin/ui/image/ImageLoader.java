package fr.corentin.ui.image;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import lombok.SneakyThrows;

/** 
 * Classe permettant de charger les images.
 * @author Petit Gato
 *
 */
public class ImageLoader {

	/**
	 * M�thode permettant de charger une image
	 * @param path Chemin o� se situe l'image
	 * @return Retourne l'image
	 */
	@SneakyThrows
	public BufferedImage load(String path){
		return ImageIO.read(getClass().getResource(path));
	}
}
