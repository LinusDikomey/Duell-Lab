package logic;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import entities.Entity;
import entities.Player;
import entities.Projectile;
import renderer.MasterRenderer;
import renderer.View;
import toolbox.Key;
import world.Board;
import world.Tile;

public class MainLogic {

	public Board board;
	public EntityManager entityManager;
	public MasterRenderer renderer;
	private int view = View.MENU;
	Listener listener;
	public Rectangle startButton;
	
	public Rectangle debug;
	
	public MainLogic(int sizeX, int sizeY) {
		board = new Board();
		entityManager = new EntityManager();
		renderer = new MasterRenderer(sizeX, sizeY, board, entityManager);
		listener = new Listener();
		
		startButton = renderer.boardRenderer.startButtonRec;
	}
	
	public void startGameLoop() {
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(view == View.BOARD) {
					gameTick();
				}else if(view == View.MENU) {
					menuTick();
				}
				renderer.currentView = view;
				renderer.repaint();
			}
		}, 0, 25);
	}
	
	public void startGame() {
		board.loadLevel("level");
		entityManager.addPlayers();
		if(view == View.MENU) {
			view = View.BOARD;
		}
	}
	
	public void stopGame() {
		if(view == View.BOARD) {
			entityManager.entities.clear();
			board.clearBoard();
			view = View.MENU;
		}
	}
	
	private void gameTick() {
		for(Entity entity : entityManager.entities) {

			entity.tick();
			entity.checkCollision(new Rectangle(0, -100, Board.SIZE_X * 100, 100));
			entity.checkCollision(new Rectangle(-100, 0, 100, Board.SIZE_Y * 100));
			entity.checkCollision(new Rectangle(Board.SIZE_X * 100, 0, 200, Board.SIZE_Y * 100));
			entity.checkCollision(new Rectangle(0, Board.SIZE_Y * 100, Board.SIZE_X * 100, 200));
			
			for(int y = 0; y < Board.SIZE_Y; y++) {
				for(int x = 0; x < Board.SIZE_X; x++) {
					if(board.tiles[x][y].collidable) {
						Rectangle box = board.tiles[x][y].collisionBox;
						Rectangle rect = new Rectangle(x * 100 + box.x, y * 100 + box.y, box.width, box.height);
						entity.checkCollision(rect);
						if(entity instanceof Player) {
							Player pEntity = (Player) entity;
							if(! pEntity.alive) {
								entityManager.entities.remove(entity);
							}
						}
					}
				}
			}
			entity.move();
		}
		for(Projectile projectile : entityManager.projectiles) {
			projectile.tick();
			
			for(int y = 0; y < Board.SIZE_Y; y++) {
				for(int x = 0; x < Board.SIZE_X; x++) {
					if(board.tiles[x][y].collidable) {
						Rectangle box = board.tiles[x][y].collisionBox;
						Rectangle rect = new Rectangle(x * 100 + box.x, y * 100 + box.y, box.width, box.height);
						if (projectile.collidesWith(rect) && board.getTile(x, y).destroyable) {
							board.getTile(x, y).damage(projectile.damage);
							if(board.getTile(x, y).health <= 0) {
								board.setTile(x, y, new Tile("empty", x, y));
							}
							projectile.delete = true;
						}
						if(entityManager.player1.collidesWith(new Rectangle(projectile.x - 1, projectile.y - 1, 3, 3))) {
							entityManager.player1.damage(projectile.damage);
							projectile.delete = true;
						}
						if(entityManager.player2.collidesWith(new Rectangle(projectile.x - 1, projectile.y - 1, 3, 3))) {
							entityManager.player2.damage(projectile.damage);
							projectile.delete = true;
						}
					}
					
					if(projectile.delete) {
						entityManager.projectiles.remove(projectile);
					}
				}
			}
		}
		
		for(int y = 0; y < Board.SIZE_Y; y++) {
			for(int x = 0; x < Board.SIZE_X; x++) {
				board.getTile(x, y).onTick();
			}
		}
	}
	
	private void menuTick() {
		if(Key.isPressed(KeyEvent.VK_ENTER)) {
			startGame();
		}
	}
	
	public void doDamage(int damage, Rectangle area, Player damager) {
		debug = area;
		for(Entity entity : entityManager.entities) {
			if(entity instanceof Player && entity.collidesWith(area)) {
				Player pEntity = (Player) entity;
				if(pEntity != damager) {
					pEntity.damage(damage);
				}
			}
		}
		
		for(int y = 0; y < Board.SIZE_Y; y++) {
			for(int x = 0; x < Board.SIZE_X; x++) {
				Tile t = board.getTile(x, y);
				Rectangle tileCol = new Rectangle((x * 100) + t.collisionBox.x, (y * 100) + t.collisionBox.y, t.collisionBox.width, t.collisionBox.height);
				if(tileCol.intersects(area) && t.destroyable) {
					t.damage(damage);
					if(t.health <= 0) {
						board.setTile(x, y, new Tile("empty", x, y));
					}
					
				}
			}
		}
	}

	//Getters / Setters
	//-----------------------------------------------------------------------------------------
	
	public int getView() {
		return view;
	}
}
