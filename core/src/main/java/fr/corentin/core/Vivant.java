package fr.corentin.core;

public interface Vivant {

	int getNombrePointVie();

	void setNombrePointVie(int nombreVie);

	int getNombreMaxPointVie();

	void setNombreMaxPointVie(int nombreVie);

	default void perdrePointVie(int nombreVie) {
		setNombrePointVie(getNombrePointVie() - nombreVie);
	}

	default void gagnerPointVie(int nombreVie) {
		if (getNombrePointVie() + nombreVie <= getNombreMaxPointVie()) {
			setNombrePointVie(getNombrePointVie() + nombreVie);
		} else {
			setNombrePointVie(getNombreMaxPointVie());
		}
	}

	default void augmenterPointVie(int nombreVie) {
		setNombreMaxPointVie(getNombreMaxPointVie() + nombreVie);
	}
}
