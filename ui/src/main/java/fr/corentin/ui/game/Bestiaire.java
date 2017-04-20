package fr.corentin.ui.game;

import java.util.ArrayList;
import java.util.List;

import fr.corentin.core.Monstre;
import fr.corentin.core.monstres.Demon;
import fr.corentin.core.monstres.Leeche;
import fr.corentin.ui.characters.monsters.SlimeMonster;

public enum Bestiaire {

	SLIME(1),
	DEMON(4),
	LEECHE(3),
	;
	
	private final int levelMin;
	
	Bestiaire(int level){
		this.levelMin = level;
	}
	
	public static List<Bestiaire> getMonstre(int levelActuel){
		List<Bestiaire> toReturn = new ArrayList<>();
		
		for(Bestiaire bestiaire : Bestiaire.values()){
			if(bestiaire.levelMin <= levelActuel){
				toReturn.add(bestiaire);
			}
		}
		
		return toReturn;
	}
	
	public Monstre getInstance(){
		switch (this) {
		case SLIME:
			return new SlimeMonster();
		case DEMON:
			return new Demon();
		case LEECHE:
			return new Leeche();
		default:
			return null;
		}
	}
}
