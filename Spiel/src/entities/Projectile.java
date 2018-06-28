package entities;

import main.Main;

public class Projectile {

	public String texture;
	public int x, y, damage, speed, rotation;
	
	boolean delete = false;
	
	public Projectile(String texture, int x, int y, int damage, int speed, int rotation) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.speed = speed;
		this.rotation = rotation;
	}
	
	public void tick() {
		x += (int) (Math.sin(Math.toRadians(rotation)) * speed);
		y += (int) (-Math.cos(Math.toRadians(rotation)) * speed);
		if(Main.logic.board.getTile(x / 100, y/ 100).collidable) {
			delete = true;
		}
	}
	
}
