package logic;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import entities.Entity;
import entities.Item;
import renderer.Loader;
import renderer.MasterRenderer;
import renderer.View;
import world.Board;
import world.Tickable;

public class MainLogic {

	public Board board;
	public EntityManager entityManager;
	public MasterRenderer renderer;
	private int view = View.MENU;
	Listener listener;
	public Rectangle startButton;
	
	public MainLogic(int sizeX, int sizeY) {
		Loader loader = new Loader();
		board = new Board();
		entityManager = new EntityManager(loader);
		renderer = new MasterRenderer(sizeX, sizeY, board, entityManager, loader);
		listener = new Listener(this);
		
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
		entityManager.entities.add(new Item(0, 0, "Bogen", entityManager.loader));
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
			for(int y = 0; y < board.SIZE_Y; y++) {
				
			}
		}
	}
	
	private void menuTick() {
		
	}

	//Getters / Setters
	//-----------------------------------------------------------------------------------------
	
	public int getView() {
		return view;
	}
}
