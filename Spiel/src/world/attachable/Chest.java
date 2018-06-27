package world.attachable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import entities.Item;
import main.Main;

public class Chest extends Attachable {

	String lootName;
	
	public Chest(int x, int y, String lootName) {
		super(x, y);
		this.lootName = lootName;
	}
	
	@Override
	public void onDestroy() {
		
		Map<Integer, String> loot = Main.loader.getLoot(lootName);
		int size = 0;
		for(Integer i : loot.keySet()) {
			if ( i > size ) {
				size = i;
				
			}
		}
		
		Random rloot = new Random();
		
		int randomnum = rloot.nextInt(size);
		List<Integer> elements = new ArrayList<Integer>();
		
		
		for(Integer i : loot.keySet()) {
			if (i >randomnum) {
				elements.add(i);
			}
		}
		
		int smallest = size;
		for(Integer i : elements) {
			if ( i < smallest ) {
				smallest = i;
				
			}
		}
		String item = loot.get(smallest);
		
		Main.logic.entityManager.entities.add(new Item(x * 100, y * 100, item));
	}
	
}
