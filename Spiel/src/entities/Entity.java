package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import renderer.Loader;
import world.Tickable;

public abstract class Entity implements Tickable {

	public int x, y;
	public int lastX, lastY;
	private BufferedImage texture;
	protected Loader loader;
	public Rectangle collisionBox = new Rectangle(0, 0, 100, 100);
	boolean collidable = true;
	
	public Entity(int x, int y, Loader loader) {
		this.x = x;
		this.y = y;
		this.loader = loader;
	}
	
	public void checkCollision(Rectangle r) {
		if(collidable) {
			Rectangle collisionRect = new Rectangle(x + collisionBox.x, y + collisionBox.y, collisionBox.width, collisionBox.height);
			if(collisionRect.intersects(r));
		}
	}
	
	protected void loadTexture(String name) {
		try {
			texture = ImageIO.read(new File("resources/textures/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tick() {
		saveCoords();
	}
	
	/**
	 * Sollte am Ende jedes Ticks aufgerufen werden
	 */
	private void saveCoords() {
		lastX = x;
		lastY = y;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
}
