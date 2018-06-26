package entities;

import renderer.Loader;
import world.Tickable;

public class Player extends Entity implements Tickable {
	
	int movementX = 0, movementY = 0;
	
	public Player(int x, int y, Loader loader, int player) {
		super(x, y, loader);
		if(player == 0)
			loadTexture("gui/figur1");
		else
			loadTexture("gui/figur2");
	}
	
	public void setMovement(int x, int y) {
		movementX = x;
		movementY = y;
	}

	@Override
	public void tick() {
		x += movementX;
		y += movementY;
	}

}
