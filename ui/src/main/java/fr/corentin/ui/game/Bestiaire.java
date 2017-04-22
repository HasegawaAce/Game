package fr.corentin.ui.game;

import java.util.ArrayList;
import java.util.List;

import fr.corentin.core.Monstre;
import fr.corentin.core.monstres.Demon;
import fr.corentin.ui.characters.monsters.LeecheMonster;
import fr.corentin.ui.characters.monsters.MomieMonster;
import fr.corentin.ui.characters.monsters.SlimeMonster;

public enum Bestiaire {

	SLIME(1),
	DEMON(4),
	LEECHE(5),
	MOMIE(2)
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
			return new LeecheMonster();
		case MOMIE:
			return new MomieMonster();
		default:
			return null;
		}
	}
}
