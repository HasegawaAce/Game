package fr.corentin.ui.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
import javax.swing.Timer;

import fr.corentin.ui.characters.Monster;
import fr.corentin.ui.characters.Player;
import fr.corentin.ui.image.ImageLoader;
import fr.corentin.ui.image.ImageManager;
import fr.corentin.ui.image.SpriteSheet;
import fr.corentin.ui.material.sounds.Sound;
import fr.corentin.ui.sprites.Bullet;
import fr.corentin.ui.sprites.Sprite;
import lombok.SneakyThrows;

/**
 * Classe représentant le plateau de jeu
 * 
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

	private boolean music = true;
	
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
		BufferedImage bufferedImageSlime = imageLoader.load("/slime.png");

		SpriteSheet spriteSheet = new SpriteSheet(bufferedImage);
		SpriteSheet spriteSheetMonster = new SpriteSheet(bufferedImageMonster);
		SpriteSheet spriteSheetBullet = new SpriteSheet(bufferedImageBullet);
		SpriteSheet spriteSheetSlime = new SpriteSheet(bufferedImageSlime);

		imageManager = new ImageManager(spriteSheet, spriteSheetMonster, spriteSheetBullet, spriteSheetSlime);

		player = new Player(imageManager);
		monsters = new ArrayList<>();

		for (int i = 0; i < Game.MONSTERS; i++) {
			monsters.add(new Monster(imageManager, 1));
		}

		timer = new Timer(DELAY, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);

		// Synchronization sur le système de fenêtre de la machine
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
		// deplace tout les éléments "bullet" de la liste
		player.getBullets().stream().forEach(bullet -> bullet.move());

		// Recherche collisions
		rechercherCollisions();

		// On demande à redessiner le monde
		repaint();
	}

	private void rechercherCollisions() {
		Rectangle playerHitBox = player.getBounds();

		for (Monster monster : monsters) {
			Rectangle monsterHitBox = monster.getBounds();
			Rectangle monsterObservationBox = monster.getObservationBounds();
			
			if (playerHitBox.intersects(monsterHitBox)) {
				player.setX(player.getX() + player.getDx() * -50);
				player.lostLife(1);
			}
			
			if(playerHitBox.intersects(monsterObservationBox)){
				if(music){
					playRun();
				}
			}
		}

		for (Bullet bullet : player.getBullets()) {
			Rectangle bulletHitBox = bullet.getBounds();

			for (Monster monster : monsters) {
				Rectangle monsterHitBox = monster.getBounds();
				
				
				if (bulletHitBox.intersects(monsterHitBox)) {
					monster.getMonstre().perdrePointVie(
							player.getJoueur().getArme().getNombreDegat() * monster.getMonstre().getFaiblesse());
					if (monster.getMonstre().getNombreVie() <= 0) {
						monster.setVisible(false);
					}
					bullet.setVisible(false);
				}
				
				
			}
		}
	}

	private void playRun() {
		
		Runnable sample = new Runnable() {
			@SneakyThrows
			public void run() {
				Sound sound = new Sound("/run.mp3");
				sound.play();
			}
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
				
		executor.execute(sample);
		
		music = false;
	}

	/**
	 * Permet de dessiner le joueur sur le plateau
	 * 
	 * @param g
	 *            le context graphique du joueur
	 */
	private void doDrawing(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		doDrawingPlayer((Graphics2D) g);

		doDrawingMonster((Graphics2D) g);

		doDrawingBullet((Graphics2D) g);
		// player.getBullets().stream().forEach(bullet ->
		// g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(),
		// Game.TILE_SIZE, Game.TILE_SIZE, this));

		g2d.setColor(Color.WHITE);

		g2d.drawString("bullets : " + player.getJoueur().getArme().getNombreBalle() + " / "
				+ player.getJoueur().getArme().getNombreBalleMax(), 10, 20);
		g2d.drawString(
				"live : " + player.getJoueur().getNombrePointVie() + " / " + player.getJoueur().getNombreMaxPointVie(),
				200, 20);
	}

	private void doDrawingBullet(Graphics2D g2d) {
		for (int i = 0; i < player.getBullets().size(); i++) {
			Bullet bullet = player.getBullets().get(i);

			if (bullet.isVisible()) {
				g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), Game.TILE_SIZE, Game.TILE_SIZE, this);
				if (Game.DEBUG) {
					debugBox(bullet, g2d);
				}
			} else {
				player.getBullets().remove(i);
			}
		}
	}

	private void doDrawingPlayer(Graphics2D g2d) {
		g2d.drawImage(player.getImage(), player.getX(), player.getY(), Game.TILE_SIZE * Game.SCALE,
				Game.TILE_SIZE * Game.SCALE, this);

		if (Game.DEBUG) {
			debugBox(player, g2d);
		}
	}

	private void doDrawingMonster(Graphics2D g2d) {
		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);

			if (monster.isVisible()) {

				g2d.drawImage(monster.getImage(), monster.getX(), monster.getY(), Game.TILE_SIZE * Game.SCALE,
						Game.TILE_SIZE * Game.SCALE, this);

				// Barre de vie
				g2d.setColor(Color.RED);
				// g2d.setStroke(new
				// BasicStroke(monster.getMonstre().getNombreVieMax()/10 + 1));

				int origineX = (monster.getWidth() - monster.getMonstre().getNombreVie()) / 2;
				g2d.drawLine(monster.getX() + origineX, monster.getY() - 10,
						monster.getX() + origineX + monster.getMonstre().getNombreVie(), monster.getY() - 10);

				if (Game.DEBUG) {
					g2d.setColor(Color.BLUE);
					g2d.drawRect(monster.getX() - monster.getMonstre().getRayonObservation(),
							monster.getY() - monster.getMonstre().getRayonObservation(),
							monster.getWidth() + monster.getMonstre().getRayonObservation() * 2,
							monster.getHeight() + monster.getMonstre().getRayonObservation() * 2);

					g2d.setStroke(new BasicStroke(1));
					g2d.setColor(Color.WHITE);
					g2d.drawString(monster.getMonstre().getNom(), monster.getX(), monster.getY() - 20);
					debugBox(monster, g2d);
				}

			} else {
				monsters.remove(i);
			}
		}
	}

	private void debugBox(Sprite sprite, Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.drawRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		g2d.setColor(Color.red);
		g2d.drawRect(sprite.getBounds().x, sprite.getBounds().y, sprite.getBounds().width, sprite.getBounds().height);

	}

	/**
	 * Adaptateur des actions de touches liés au personnage.
	 * 
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
