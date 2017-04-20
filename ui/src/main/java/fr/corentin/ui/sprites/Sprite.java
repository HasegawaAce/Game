package fr.corentin.ui.sprites;

import java.awt.Image;

import lombok.Getter;

public abstract class Sprite {

	/**
	 * Direction x
	 */
	protected int dx;

	/**
	 * Direction y
	 */
	protected int dy;

	/**
	 * Position x
	 */
	@Getter
	protected int x;

	/**
	 * Position y
	 */
	@Getter
	protected int y;
	
	/**
	 * Image du sprite
	 */
	@Getter
	protected Image image;
	
	public abstract void move();
}
