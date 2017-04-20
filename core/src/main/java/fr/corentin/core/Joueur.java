package fr.corentin.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Joueur implements Vivant {

	private String nom;
	
	private int nombreVie;
	
	private int nombreVieMax;
	
	private Arme arme;

	@Override
	public int getNombrePointVie() {
		return nombreVie;
	}

	@Override
	public void setNombrePointVie(int nombreVie) {
		this.nombreVie = nombreVie;		
	}

	@Override
	public int getNombreMaxPointVie() {
		return nombreVieMax;
	}

	@Override
	public void setNombreMaxPointVie(int nombreVie) {
		this.nombreVieMax = nombreVie;		
	}
	
}
