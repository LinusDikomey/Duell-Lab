package world.tiles;

import world.Tile;

public class Chest extends Tile { 

	public Chest() {
		super(1, false, true, true, "wood_chest", 2);
	}
	
	@Override
	public void onDestroy() {
		//Loot auswahl zum Spawn
	}
}