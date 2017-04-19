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

/**
 * Classe repr�sentant le plateau de jeu
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
	 * M�thode cr�ant le plateau Ajout de l'�couteur des actions D�finition du
	 * fond Insertion du joueur Initialisation du timer
	 */
	private void initPlateau() {

		addKeyListener(new Adapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		
		ImageLoader imageLoader = new ImageLoader();		
		BufferedImage bufferedImage = imageLoader.load("/SpriteSheet.png");
		BufferedImage bufferedImageMonster = imageLoader.load("/monsterSpriteSheet.gif");
		
		SpriteSheet spriteSheet = new SpriteSheet(bufferedImage);
		SpriteSheet spriteSheetMonster = new SpriteSheet(bufferedImageMonster);
		
		imageManager = new ImageManager(spriteSheet, spriteSheetMonster);
		
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

        //Synchronization sur le syst�me de fen�tre de la machine
        Toolkit.getDefaultToolkit().sync();
    }

	/**
	 * M�thode permettant de lancer les actions
	 */
	public void actionPerformed(ActionEvent e) {
		player.move();
		monsters.stream().forEach(monster -> monster.move());
		// On demande � redessiner le monde
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
	 * Adaptateur des actions de touches li�s au personnage.
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
