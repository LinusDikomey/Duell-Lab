package entities;

import java.awt.event.KeyEvent;

import logic.IsKeyPressed;
import renderer.Loader;
import world.Tickable;

public class Player extends Entity implements Tickable {
	
	int player;
	
	public Player(int x, int y, Loader loader, int player) {
		super(x, y, loader);
		this.player = player;
		if(player == 0)
			loadTexture("gui/figur1");
		else
			loadTexture("gui/figur2");
	}

	public static final int SPEED = 10;
	
	@Override
	public void tick() {
		if(player == 1) {
			if(IsKeyPressed.pressed(KeyEvent.VK_DOWN)) {
				y += SPEED;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_UP)) {
				y -= SPEED;
			}
			if(IsKeyPressed.pressed(KeyEvent.VK_RIGHT)) {
				x += SPEED;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_LEFT)) {
				x -= SPEED;
			}
		}else {
			if(IsKeyPressed.pressed(KeyEvent.VK_S)) {
				y += SPEED;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_W)) {
				y -= SPEED;
			}
			if(IsKeyPressed.pressed(KeyEvent.VK_D)) {
				x += SPEED;
			}else if(IsKeyPressed.pressed(KeyEvent.VK_A)) {
				x -= SPEED;
			}
		}
	}

}
