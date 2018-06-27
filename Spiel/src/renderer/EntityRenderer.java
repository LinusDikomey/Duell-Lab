package renderer;

import java.awt.Graphics;

import entities.Entity;
import entities.Player;
import logic.EntityManager;
import main.Main;

public class EntityRenderer {

	int tilesize;
	
	public EntityRenderer(int tilesize, EntityManager entityManager) {
		this.tilesize = tilesize;
	}

	public void render(Graphics g) {
		for(Entity entity : Main.logic.entityManager.entities) {
			g.drawImage(entity.getTexture(), entity.x * tilesize / 100, entity.y * tilesize / 100, tilesize, tilesize, null);
			if(entity instanceof Player) {
				Player pEntity = (Player) entity;
				if(pEntity.inventory[pEntity.selectedSlot] != null) {
					g.drawImage(pEntity.inventory[pEntity.selectedSlot].getTexture(), entity.x * tilesize / 100,  entity.y * tilesize / 100, (int) (tilesize * 0.9f), (int) (tilesize * 0.9f), null);
				}
			}
		}
	}
	
}
