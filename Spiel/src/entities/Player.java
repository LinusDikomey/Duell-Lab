package entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import main.Main;
import toolbox.Key;
import world.Board;
import world.Tickable;

public class Player extends Entity implements Tickable {

	public int player;
	int rotation;
	private String texturePath;
	public Item[] inventory = new Item[3];
	public int selectedSlot = 0;
	private int holdTime = 0;
	public int health = 20;
	public boolean alive = true;
	private boolean wasDamaged = false;

	public int useDelay = 0;

	public Player(int x, int y, int player) {
		super(x, y, true);

		collisionBox = new Rectangle(20, 20, 60, 60);

		this.player = player;
		if (player == 0)
			texturePath = "gui/figur1";
		else
			texturePath = "gui/figur2";
	}

	public static final int SPEED = 10;

	@Override
	public BufferedImage getTexture() {
		return Main.loader.getRotatedImage(texturePath)[rotation / 45];
	}

	@Override
	public void tick() {
		wasDamaged = false;
		int xSpeed = 0;
		int ySpeed = 0;
		if (!alive)
			return;

		boolean useItem = false;
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
				if (Key.isPressed(KeyEvent.VK_NUMPAD0)) {
					useItem = true;
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
				if (Key.isPressed(KeyEvent.VK_SPACE)) {
					useItem = true;
				}
			}
		}

		if (holdTime >= 40) {
			if (inventory[selectedSlot] == null) {
				for (Entity entity : Main.logic.entityManager.entities) {
					if (entity.getClass() == Item.class) {
						if (entity.collidesWith(new Rectangle(x + collisionBox.x, y + collisionBox.y,
								collisionBox.width, collisionBox.height))) {
							inventory[selectedSlot] = (Item) entity;
							Main.logic.entityManager.entities.remove(entity);
							break;
						}
					}
				}
			} else {
				Main.logic.entityManager.entities.add(new Item(x, y, inventory[selectedSlot].itemName));
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
		if (useDelay > 0) {
			xSpeed *= 0.9f;
			ySpeed *= 0.9f;
		}
		nextX += xSpeed;
		nextY += ySpeed;
		if (useItem && useDelay == 0) {
			Item item = inventory[selectedSlot];
			if (item == null) {
				useDelay = 20;
				int damX = x + 50 + (int) (Math.sin(Math.toRadians(rotation)) * 100);
				int damY = y + 50 + (int) (-Math.cos(Math.toRadians(rotation)) * 100);
				Main.logic.doDamage(1, new Rectangle(damX - 50, damY - 50, 100, 100), this);

			} else {
				if (item.meleeMode != null) {
					useDelay = item.cooldown;
					if (item.meleeMode.equals("radius")) {
						int damX = x + 50 + (int) (Math.sin(Math.toRadians(rotation)) * item.meleeOffset * 100);
						int damY = y + 50 + (int) (-Math.cos(Math.toRadians(rotation)) * item.meleeOffset * 100);
						Main.logic.doDamage(item.meleeDamage,
								new Rectangle(damX - 50 - (int) (item.meleeRange * 100),
										damY - 50 - (int) (item.meleeRange * 100), 100 + (int) (item.meleeRange * 200),
										+100 + (int) (item.meleeRange * 200)),
								this);

					} else if (item.meleeMode.equals("front")) {
						int damX = x + 50;
						int damY = y + 50;
						for (int i = 0; i < item.meleeRange * 4; i++) {
							damX += (int) (Math.sin(Math.toRadians(rotation)) * 25);
							damY += (int) (-Math.cos(Math.toRadians(rotation)) * 25);
							Main.logic.doDamage(item.meleeDamage, new Rectangle(damX - 25, damY - 25, 50, 50), this);
							if(damX > 0 && damX < Board.SIZE_X * 100 && damY > 0 && damY < Board.SIZE_Y * 100) {
								if (Main.logic.board.getTile(damX / 100, damY / 100).collidable) {
									break;
								}
							}
						}
					} else {
						System.out.println("Error, invalid melee damage type!");
					}
				}
				
				if(item.rangedDamage != 0) {
					if(item.rangedMagazine > 0) {
						int projX = x + 50 + (int) (Math.sin(Math.toRadians(rotation)) * 50);
						int projY = y + 50 + (int) (-Math.cos(Math.toRadians(rotation)) * 50);
						Main.logic.entityManager.projectiles.add(new Projectile(item.projectileName, projX, projY, item.rangedDamage, item.rangedSpeed, rotation));
						useDelay = item.cooldown;
					}
				}
			}

		} else if (useDelay > 0) {
			useDelay--;
		}
	}

	public void damage(int points) {
		if (wasDamaged) {
			return;
		}
		wasDamaged = true;
		health -= points;
		if (health < 1) {
			alive = false;
		}
	}
}