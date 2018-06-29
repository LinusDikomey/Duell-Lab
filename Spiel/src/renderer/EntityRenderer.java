package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import logic.EntityManager;
import main.Main;

public class EntityRenderer {

	int tilesize;
	
	public EntityRenderer(int tilesize, EntityManager entityManager) {
		this.tilesize = tilesize;
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		Rectangle r = Main.logic.debug;
		if(r != null) {
			g.drawRect((int) ((r.x / 100f) * tilesize) , (int) ((r.y / 100f) * tilesize), (int) ((r.width / 100f) * tilesize), (int)  ((r.height / 100f) * tilesize));
		}
		
		for(Entity entity : Main.logic.entityManager.entities) {
			g.drawImage(entity.getTexture(), entity.x * tilesize / 100, entity.y * tilesize / 100, tilesize, tilesize, null);
			if(entity instanceof Player) {
				Player pEntity = (Player) entity;
				if(pEntity.inventory[pEntity.selectedSlot] != null) {
					g.drawImage(pEntity.inventory[pEntity.selectedSlot].getTexture(), entity.x * tilesize / 100,  entity.y * tilesize / 100, (int) (tilesize * 0.9f), (int) (tilesize * 0.9f), null);
				}
				int pointer = 1;
				if(pEntity.player == 1) {
					pointer += 25;
				}
				g.setColor(Color.BLUE);
				g.fillRect(0 + pointer * tilesize, 0, (int) ((pEntity.useDelay / 400f) * tilesize * 10), tilesize);
				int health = pEntity.health;
				for(int p = pointer; p < pointer + 5; p++) {
					if(health >= 4) {
						g.drawImage(Main.loader.getImage("gui/herz4"), p * tilesize, 0, tilesize, tilesize, null);
						health -= 4;
					} else if(health >= 3) {
						g.drawImage(Main.loader.getImage("gui/herz3"), p * tilesize, 0, tilesize, tilesize, null);
						health -= 3;
					}else if(health >= 2) {
						g.drawImage(Main.loader.getImage("gui/herz2"), p * tilesize, 0, tilesize, tilesize, null);
						health -= 2;
					}else if(health >= 1) {
						g.drawImage(Main.loader.getImage("gui/herz1"), p * tilesize, 0, tilesize, tilesize, null);
						health -= 1;
					}else {
						g.drawImage(Main.loader.getImage("gui/herz0"), p * tilesize, 0, tilesize, tilesize, null);
					}
				}
			}
		}
		
		for(Projectile p : Main.logic.entityManager.projectiles) {
			g.drawImage(Main.loader.getImage("items/" + p.texture), p.x * tilesize / 100 -25, p.y * tilesize / 100 -25, 50, 50, null);

		}
	}	
}
