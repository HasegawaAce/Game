package fr.corentin.ui.game;

import java.util.ArrayList;
import java.util.List;

import fr.corentin.core.Arme;
import fr.corentin.core.armes.Criquet;
import fr.corentin.core.armes.Mitrailleuse;

public enum Armurerie {

	CRIQUET(1),
	MITRAILLEUSE(1),
	;

	private final int levelMin;
	
	Armurerie(int level){
		this.levelMin = level;
	}
	
	public static List<Armurerie> getArme(int levelActuel){
		List<Armurerie> toReturn = new ArrayList<>();
		
		for(Armurerie armurerie : Armurerie.values()){
			if(armurerie.levelMin <= levelActuel){
				toReturn.add(armurerie);
			}
		}
		
		return toReturn;
	}
	
	public Arme getInstance(){
		switch (this) {
		case CRIQUET:
			return new Criquet();
		case MITRAILLEUSE:
			return new Mitrailleuse();
		default:
			return null;
		}
	}
}
