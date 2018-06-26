package entities;

import java.awt.image.BufferedImage;

import renderer.ImageLoader;

public abstract class Entity {

	int x, y;
	BufferedImage texture;
	
	public Entity(int x, int y, String texture, ImageLoader loader) {
		this.x = x;
		this.y = y;
		this.texture = loader.getImage("items/" + texture);
	}
	
}
