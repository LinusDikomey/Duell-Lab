package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Main;
import world.Tickable;

public abstract class Entity implements Tickable {

	public int x, y;
	public int nextX, nextY;
	private BufferedImage texture;
	public Rectangle collisionBox = new Rectangle(20, 20, 80, 80);
	boolean collidable = false;
	
	public Entity(int x, int y, boolean collidable) {
		this.x = x;
		this.y = y;
		this.collidable = collidable;
	}
	
	public boolean moveX = false, moveY = false;
	
	public boolean collidesWith(Rectangle r) {
		Rectangle collisionRect = new Rectangle(x + collisionBox.x, y + collisionBox.y, collisionBox.width, collisionBox.height);
		return collisionRect.intersects(r);
	}
	
	public void checkCollision(Rectangle r) {
		if(collidable) {
			Rectangle collisionRectX = new Rectangle(nextX + collisionBox.x, y + collisionBox.y, collisionBox.width, collisionBox.height);
			if(collisionRectX.intersects(r)) {
				moveX = false;
			}
			Rectangle collisionRectY = new Rectangle(x + collisionBox.x, nextY + collisionBox.y, collisionBox.width, collisionBox.height);
			if(collisionRectY.intersects(r)) {
				moveY = false;
			}
		}
	}
	
	protected void loadTexture(String name) {
		texture = Main.loader.getImage(name);
	}
	
	@Override
	public void tick() {
		
	}
	
	public BufferedImage getTexture() {
		return texture;
	}

	public void move() {
		if(moveX) {
			x = nextX;
		}
		if(moveY) {
			y = nextY;
		}
		moveX = true;
		moveY = true;
		nextX = x;
		nextY = y;
	}
}
