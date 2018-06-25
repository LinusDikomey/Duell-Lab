package world;

import java.io.File;

import logic.SaveLoadManager;
import world.tiles.EmptyTile;

public class Board {

	public final static int SIZE_X = 32;
	public final static int SIZE_Y = 18;
	
	public Tile[][] tiles = new Tile[SIZE_X][SIZE_Y];
	
	public Board() {
		for(int y = 0; y < SIZE_Y; y++) {
			for(int x = 0; x < SIZE_X; x++) {
				tiles[x][y] = new EmptyTile();
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public void setTile(int x, int y, Tile t) {
		tiles[x][y] = t;
	}

	public void loadLevel(String name) {
		tiles = SaveLoadManager.load(new File("resources/levels/" + name + ".lvl"));
	}
}
