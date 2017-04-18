package fr.corentin.ui.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;

import fr.corentin.ui.game.Plateau;

public class MovingSprite extends JFrame {

	private static final long serialVersionUID = 1L;

	public MovingSprite() {
		initUI();
	}
	
	private void initUI() {
		add(new Plateau());
		
		setSize(800, 600);
		setResizable(false);
		
		setTitle("Mon jeu à moi");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				MovingSprite movingSprite = new MovingSprite();
				movingSprite.setVisible(true);
			}
		});
	}
}
