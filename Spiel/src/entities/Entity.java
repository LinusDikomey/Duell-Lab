package entities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import renderer.Loader;

public abstract class Entity {

	public int x, y;
	private BufferedImage texture;
	
	public Entity(int x, int y, Loader loader) {
		this.x = x;
		this.y = y;
	}
	
	protected void loadTexture(String name) {
		try {
			texture = ImageIO.read(new File("resources/textures/items/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
}
