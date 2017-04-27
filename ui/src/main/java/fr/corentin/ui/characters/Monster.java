package fr.corentin.ui.characters;

import java.awt.Rectangle;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.ImageIcon;

import fr.corentin.core.Monstre;
import fr.corentin.ui.characters.monsters.MonsterUI;
import fr.corentin.ui.game.Bestiaire;
import fr.corentin.ui.game.Game;
import fr.corentin.ui.image.ImageManager;
import fr.corentin.ui.items.Drop;
import fr.corentin.ui.sprites.Sprite;
import lombok.Getter;

/**
 * Classe représentant un Monstre dans le jeu
 * 
 * @author Petit Gato
 *
 */
public class Monster extends Sprite  implements Observer {

	@Getter
	private Monstre monstre;
	
	/**
	 * Initialisation du monstre
	 */
	public Monster(ImageManager imageManager, int level) {
		this.initMonster(imageManager, level);
	}

	/**
	 * Création du monstre Définition de la postion initiale
	 */
	private void initMonster(ImageManager imageManager, int level) {
		Random random = new Random();
		
		setX(random.nextInt(Game.SCREEN_WIDTH) + 1);
		setY(random.nextInt(Game.SCREEN_HEIGTH) + 1);
		
		setWidth(Game.TILE_SIZE * Game.SCALE);
		setHeight(Game.TILE_SIZE * Game.SCALE);
		
		List<Bestiaire> bestiaire = Bestiaire.getMonstre(level);
		
		int rnd = new Random().nextInt(bestiaire.size());
		monstre = bestiaire.get(rnd).getInstance();
		ImageIcon ii; 
		if(monstre instanceof MonsterUI){
			ii = ((MonsterUI)monstre).getImageIcone(imageManager);
		} else {
			ii = new ImageIcon(imageManager.getMonster());
		}

		setImage(ii.getImage());
	}
	
	public Rectangle getObservationBounds(){
		
		return new Rectangle(getX() - getMonstre().getRayonObservation(),
							getY() - getMonstre().getRayonObservation(),
							getWidth() + getMonstre().getRayonObservation() * 2,
							getHeight() + getMonstre().getRayonObservation() * 2);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		//Quelle est ma position
		//le player est à gauche ou à droite?
		if(getX() > ((int[])arg)[0]){
			setDx(-1);
		} else if(getX() < ((int[])arg)[0]){
			setDx(1);
		} else {
			setDx(0);
		}
		
		if(getY() > ((int[])arg)[1]){
			setDy(-1);
		} else if(getY() < ((int[])arg)[1]){
			setDy(1);
		} else {
			setDy(0);
		}
		
	}
	
	public Drop dropObject(){
		return Drop.getDrop();
	}

	@Override
	public void animationSprite(int i) {
		if(monstre instanceof MonsterUI){
			setImage(((MonsterUI)monstre).animationSprite(i));
		}
	}
}
