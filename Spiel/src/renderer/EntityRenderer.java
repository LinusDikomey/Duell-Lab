package renderer;

import java.awt.Graphics;

import entities.Entity;
import logic.EntityManager;

public class EntityRenderer {

	int tilesize;
	ImageLoader loader;
	EntityManager entityManager;
	
	public EntityRenderer(int tilesize, EntityManager entityManager, ImageLoader loader) {
		this.loader  = loader;
		this.entityManager = entityManager;
	}

	public void render(Graphics g) {
		for(Entity entity : entityManager.entities) {
			
		}
	}
	
}
