package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import logic.EntityManager;
import toolbox.Key;
import toolbox.Loader;
import world.Tickable;

public class Player extends Entity implements Tickable {

	int player;
	int rotation;
	private String texturePath;
	EntityManager entityManager;
	public Item[] inventory = new Item[3];
	public int selectedSlot = 0;
	private int holdTime = 0;

	public Player(int x, int y, Loader loader, int player, EntityManager entityManager) {
		super(x, y, true, loader);

		collisionBox = new Rectangle(20, 20, 60, 60);

		this.entityManager = entityManager;
		this.player = player;
		if (player == 0)
			texturePath = "gui/figur1";
		else
			texturePath = "gui/figur2";
	}

	public static final int SPEED = 10;

	@Override
	public BufferedImage getTexture() {
		return loader.getRotatedImage(texturePath)[rotation / 45];
	}

	@Override
	public void tick() {
		int xSpeed = 0;
		int ySpeed = 0;
		if (player == 1) {
			if (Key.isPressed(KeyEvent.VK_NUMPAD1)) {
				if (!(selectedSlot == 0)) {
					selectedSlot = 0;
					holdTime = 0;
				}
				holdTime++;
			} else if (Key.isPressed(KeyEvent.VK_NUMPAD2)) {
				if (!(selectedSlot == 1)) {
					selectedSlot = 1;
					holdTime = 0;
				}
				holdTime++;
			} else if (Key.isPressed(KeyEvent.VK_NUMPAD3)) {
				if (!(selectedSlot == 2)) {
					selectedSlot = 2;
					holdTime = 0;
				}
				holdTime++;
			} else {
				holdTime = 0;

				if (Key.isPressed(KeyEvent.VK_DOWN)) {
					ySpeed += SPEED;
					rotation = 180;
				} else if (Key.isPressed(KeyEvent.VK_UP)) {
					ySpeed -= SPEED;
					rotation = 0;
				}
				if (Key.isPressed(KeyEvent.VK_RIGHT)) {
					xSpeed += SPEED;
					rotation = 90;
				} else if (Key.isPressed(KeyEvent.VK_LEFT)) {
					xSpeed -= SPEED;
					rotation = 270;
				}
			}
		} else {
			if (Key.isPressed(KeyEvent.VK_1)) {
				if (!(selectedSlot == 0)) {
					selectedSlot = 0;
					holdTime = 0;
				}
				holdTime++;
			} else if (Key.isPressed(KeyEvent.VK_2)) {
				if (!(selectedSlot == 1)) {
					selectedSlot = 1;
					holdTime = 0;
				}
				holdTime++;
			} else if (Key.isPressed(KeyEvent.VK_3)) {
				if (!(selectedSlot == 2)) {
					selectedSlot = 2;
					holdTime = 0;
				}
				holdTime++;
			} else {
				holdTime = 0;

				if (Key.isPressed(KeyEvent.VK_S)) {
					ySpeed += SPEED;
					rotation = 180;
				} else if (Key.isPressed(KeyEvent.VK_W)) {
					ySpeed -= SPEED;
					rotation = 0;
				}
				if (Key.isPressed(KeyEvent.VK_D)) {
					xSpeed += SPEED;
					rotation = 90;
				} else if (Key.isPressed(KeyEvent.VK_A)) {
					xSpeed -= SPEED;
					rotation = 270;
				}
			}
		}

		if (holdTime >= 30) {
			if (inventory[selectedSlot] == null) {
				for (Entity entity : entityManager.entities) {
					if (entity.getClass() == Item.class) {
						if (entity.collidesWith(new Rectangle(x + collisionBox.x, y + collisionBox.y,
								collisionBox.width, collisionBox.height))) {
							inventory[selectedSlot] = (Item) entity;
							entityManager.entities.remove(entity);
							break;
						}
					}
				}
			} else {
				entityManager.entities.add(new Item(x, y, inventory[selectedSlot].itemName, loader));
				inventory[selectedSlot] = null;
			}
			holdTime = 0;
		}

		if (xSpeed != 0 && ySpeed != 0) {
			xSpeed *= Math.sin(45);
			ySpeed *= Math.sin(45);
			if (xSpeed > 0) {
				if (ySpeed > 0) {
					rotation = 135;
				} else {
					rotation = 45;
				}
			} else {
				if (ySpeed > 0) {
					rotation = 225;
				} else {
					rotation = 315;
				}
			}
		}
		nextX += xSpeed;
		nextY += ySpeed;
	}
}