package fr.corentin.ui.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import fr.corentin.ui.characters.Monster;
import fr.corentin.ui.characters.Player;
import fr.corentin.ui.image.ImageLoader;
import fr.corentin.ui.image.ImageManager;
import fr.corentin.ui.image.SpriteSheet;
import fr.corentin.ui.sprites.Bullet;

/**
 * Classe représentant le plateau de jeu
 * @author Petit Gato
 *
 */
public class Plateau extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;

	private Player player;
	
	private List<Monster> monsters;

	private final int DELAY = 10;
	
	private ImageManager imageManager;
	
	/**
	 * Constructeur de la classe
	 */
	public Plateau() {
		initPlateau();
	}

	/**
	 * Méthode créant le plateau Ajout de l'écouteur des actions Définition du
	 * fond Insertion du joueur Initialisation du timer
	 */
	private void initPlateau() {

		addKeyListener(new Adapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		
		ImageLoader imageLoader = new ImageLoader();		
		BufferedImage bufferedImage = imageLoader.load("/SpriteSheet.png");
		BufferedImage bufferedImageMonster = imageLoader.load("/monsterSpriteSheet.gif");
		BufferedImage bufferedImageBullet = imageLoader.load("/bulletSpriteSheet.png");
		
		SpriteSheet spriteSheet = new SpriteSheet(bufferedImage);
		SpriteSheet spriteSheetMonster = new SpriteSheet(bufferedImageMonster);
		SpriteSheet spriteSheetBullet = new SpriteSheet(bufferedImageBullet);
		
		imageManager = new ImageManager(spriteSheet, spriteSheetMonster, spriteSheetBullet);
		
		player = new Player(imageManager);
		monsters = new ArrayList<>();
		
		for(int i = 0; i < Game.MONSTERS ; i++){
			monsters.add(new Monster(imageManager));
		}
		
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        //Synchronization sur le système de fenêtre de la machine
        Toolkit.getDefaultToolkit().sync();
    }

	/**
	 * Méthode permettant de lancer les actions
	 */
	public void actionPerformed(ActionEvent e) {
		
		// deplace le joueur
		player.move();
		// deplace tout les éléments "monstre" de la liste
		monsters.stream().forEach(monster -> monster.move());
		//deplace tout les éléments "bullet" de la liste
		player.getBullets().stream().forEach(bullet -> bullet.move());
		// On demande à redessiner le monde
		repaint();
	}

	/**
	 * Permet de dessiner le joueur sur le plateau
	 * @param g le context graphique du joueur
	 */
	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(player.getImage(), player.getX(), player.getY(), Game.TILE_SIZE * Game.SCALE, Game.TILE_SIZE * Game.SCALE, this);
		
		monsters.stream().forEach(monster -> g2d.drawImage(monster.getImage(), monster.getX(), monster.getY(), Game.TILE_SIZE * Game.SCALE, Game.TILE_SIZE * Game.SCALE, this));
		
	}

	/**
	 * Adaptateur des actions de touches liés au personnage.
	 * @author Petit Gato
	 *
	 */
	private class Adapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

	}
	

}
