package world.tiles;

import world.Tile;

public class EmptyTile extends Tile {
	
	public EmptyTile() {
		destroyable = false;
		walkable = true;
	}

}
