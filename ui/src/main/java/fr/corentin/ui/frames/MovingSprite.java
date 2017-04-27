package fr.corentin.ui.frames;

import javax.swing.JFrame;

import fr.corentin.ui.game.Game;
import fr.corentin.ui.game.Plateau;

public class MovingSprite extends JFrame {

	private static final long serialVersionUID = 1L;

	public MovingSprite() {
		initUI();
	}
	
	private void initUI() {
		add(new Plateau(1));
		
		setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGTH);
		setResizable(false);
		
		setTitle("Mon jeu à moi");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
