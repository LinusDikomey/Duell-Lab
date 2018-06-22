package world.tiles;

import world.Tile;

public class Wall extends Tile {

	public Wall() {
		render = true;
		imageLocation = "images/placeholder1";
		health = 10;
		walkable = false;
		destroyable = true;
	}
}
