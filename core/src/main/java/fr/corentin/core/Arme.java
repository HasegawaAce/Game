package fr.corentin.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Arme {

	private String nom;

	private int nombreDegat;
	
	// temps entre les tirs en ms
	private int coolDown;
	
	// temps de creation d'une balle en ms
	private int timeBulletCreate;
	
	private int nombreBalle;
	
	private int nombreBalleMax;
	
	private int vitesse;
	
	public void addBalle(){
		if(nombreBalle < nombreBalleMax){
			nombreBalle++;
		}
	}
}
