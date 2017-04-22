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

	/**
	 * Méthode de déplacement du monstre (Ramdom)
	 */
	@Override
	public void move() {
		Random random = new Random();
		// 10% de chance de déplacement
		int deplacement = random.nextInt(100) + 1;

		if (deplacement <= 10) {
			setDx(random.nextInt(2) == 0 ? -1 : 1);
			setDy(random.nextInt(2) == 0 ? -1 : 1);
			if ((getDx() > 0 && getX() <= (Game.SCREEN_WIDTH - Game.TILE_SIZE * Game.SCALE)) || (getDx() < 0 && getX() >= 0)) {
				setX(getX() + getDx());
			}
			if ((getDy() > 0 && getY() < (Game.SCREEN_HEIGTH - Game.TILE_SIZE * Game.SCALE)) || (getDy() < 0 && getY() >= 0)) {
				setY(getY() + getDy());
			}
		} else {
			setDx(0);
			setDy(0);
		}
	}
	
	public Rectangle getObservationBounds(){
		
		return new Rectangle(getX() - getMonstre().getRayonObservation(),
							getY() - getMonstre().getRayonObservation(),
							getWidth() + getMonstre().getRayonObservation() * 2,
							getHeight() + getMonstre().getRayonObservation() * 2);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}
}
