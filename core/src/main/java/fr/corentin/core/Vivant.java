package fr.corentin.core;

public interface Vivant {

	int getNombrePointVie();
	
	void setNombrePointVie(int nombreVie);
	
	int getNombreMaxPointVie();
	
	void setNombreMaxPointVie(int nombreVie);
	
	default void perdrePointVie(int nombreVie){
		setNombrePointVie(getNombrePointVie() - nombreVie);
	}
	
	default void gagnerPointVie(int nombreVie){
		setNombrePointVie(getNombrePointVie() + nombreVie);
	}
	
	default void augmenterPointVie(int nombreVie){
		setNombreMaxPointVie(getNombreMaxPointVie() + nombreVie);
	}
}
