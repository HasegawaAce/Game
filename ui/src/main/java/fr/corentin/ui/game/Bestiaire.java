package fr.corentin.ui.game;

import java.util.ArrayList;
import java.util.List;

import fr.corentin.core.Monstre;
import fr.corentin.ui.characters.monsters.DemonMonster;
import fr.corentin.ui.characters.monsters.DjeenMonster;
import fr.corentin.ui.characters.monsters.GargoyleMonster;
import fr.corentin.ui.characters.monsters.LeecheMonster;
import fr.corentin.ui.characters.monsters.MomieMonster;
import fr.corentin.ui.characters.monsters.MortMonster;
import fr.corentin.ui.characters.monsters.OgreMonster;
import fr.corentin.ui.characters.monsters.SlimeMonster;
import fr.corentin.ui.characters.monsters.SqueletteMonster;

public enum Bestiaire {

	SLIME(1),
	DEMON(1),
	LEECHE(1),
	MOMIE(1),
	SQUELETTE(1),
	DJEEN(1),
	OGRE(1),
	MORT(1),
	GARGOYLE(1),
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
			return new DemonMonster();
		case LEECHE:
			return new LeecheMonster();
		case MOMIE:
			return new MomieMonster();
		case SQUELETTE:
			return new SqueletteMonster();
		case DJEEN:
			return new DjeenMonster();
		case OGRE:
			return new OgreMonster();
		case MORT:
			return new MortMonster();
		case GARGOYLE:
			return new GargoyleMonster();
		default:
			return null;
		}
	}
}
