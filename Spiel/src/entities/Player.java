package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import logic.IsKeyPressed;
import renderer.Loader;
import world.Tickable;

public class Player extends Entity implements Tickable {
	
	int player;
	int rotation;
	private String texturePath;
	
	public Player(int x, int y, Loader loader, int player) {
		super(x, y, loader);
		collidable = true;
		collisionBox = new Rectangle(20, 20, 60, 60);
		
		this.player = player;
		if(player == 0)
			texturePath = "gui/figur1";
		else
			texturePath = "gui/figur2";
	}

	public static final int SPEED = 10;
	
	@Override
	public BufferedImage getTexture() {
		return loader.getRotatedImage(texturePath)[rotation / 45];
	}
	
	@Override
	public void tick() {
		int xSpeed = 0;
		int ySpeed = 0;
		if(player == 1) {
			if(IsKeyPressed.pressed(KeyEvent.VK_DOWN)) {
				ySpeed += SPEED;
				rotation = 180;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_UP)) {
				ySpeed -= SPEED;
				rotation = 0;
			}
			if(IsKeyPressed.pressed(KeyEvent.VK_RIGHT)) {
				xSpeed += SPEED;
				rotation = 90;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_LEFT)) {
				xSpeed -= SPEED;
				rotation = 270;
			}
		}else {
			if(IsKeyPressed.pressed(KeyEvent.VK_S)) {
				ySpeed += SPEED;
				rotation = 180;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_W)) {
				ySpeed -= SPEED;
				rotation = 0;
			}
			if(IsKeyPressed.pressed(KeyEvent.VK_D)) {
				xSpeed += SPEED;
				rotation = 90;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_A)) {
				xSpeed -= SPEED;
				rotation = 270;
			}
		}
		if(xSpeed != 0 && ySpeed != 0) {
			xSpeed *= Math.sin(45);
			ySpeed *= Math.sin(45);
			if(xSpeed > 0 ) {
				if(ySpeed > 0) {
					rotation = 135;
				}else {
					rotation = 45;
				}
			}else {
				if(ySpeed > 0) {
					rotation = 225;
				}else {
					rotation = 315;
				}
			}
		}
		nextX += xSpeed;
		nextY += ySpeed;
	}
}