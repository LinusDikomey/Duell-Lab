package world.tiles;

import world.Tile;

public class Chest extends Tile { 

	public Chest() {
		super(1, false, true, true, "placeholder3");
	}
	
	@Override
	public void onDestroy() {
		//Loot auswahl zum Spawn
	}
}


