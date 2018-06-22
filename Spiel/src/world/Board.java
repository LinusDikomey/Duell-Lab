package world;

import world.tiles.EmptyTile;

public class Board {

	public final int SIZE_X = 32;
	public final int SIZE_Y = 18;
	
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
}
