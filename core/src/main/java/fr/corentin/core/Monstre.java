package fr.corentin.core;

import javax.swing.ImageIcon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Monstre implements Vivant {

	private String nom;

	private int nombreVie;

	private int nombreVieMax;

	private int nombreDegat;
	
	private int faiblesse;
	
	private int rayonObservation;
	
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
	
	public ImageIcon getImageIcone(){
		return null;
	}
	
}
