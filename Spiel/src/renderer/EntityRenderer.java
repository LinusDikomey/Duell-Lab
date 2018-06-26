package renderer;

import java.awt.Graphics;

import entities.Entity;
import logic.EntityManager;

public class EntityRenderer {

	int tilesize;
	EntityManager entityManager;
	
	public EntityRenderer(int tilesize, EntityManager entityManager) {
		this.entityManager = entityManager;
		this.tilesize = tilesize;
	}

	public void render(Graphics g) {
		for(Entity entity : entityManager.entities) {
			g.drawImage(entity.getTexture(), entity.x * tilesize / 100, entity.y * tilesize / 100, tilesize, tilesize, null);
		}
	}
	
}
