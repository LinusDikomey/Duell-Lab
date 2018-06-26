package logic;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import renderer.MasterRenderer;
import renderer.View;
import world.Board;

public class MainLogic {

	Board board;
	EntityManager entityManager;
	public MasterRenderer renderer;
	private int view = View.MENU;
	Listener listener;
	public Rectangle startButton;
	
	public MainLogic(int sizeX, int sizeY) {
		board = new Board();
		entityManager = new EntityManager();
		renderer = new MasterRenderer(sizeX, sizeY, board, entityManager);
		listener = new Listener(this);
		board.loadLevel("test");
		
		
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
		}, 0, 50);
	}
	
	public void startGame() {
		if(view == View.MENU) {
			view = View.BOARD;
		}
	}
	
	public void stopGame() {
		if(view == View.BOARD) {
			board.clearBoard();
			view = View.MENU;
		}
	}
	
	private void gameTick() {
		
	}
	
	private void menuTick() {
		
	}

	//Getters / Setters
	//-----------------------------------------------------------------------------------------
	
	public int getView() {
		return view;
	}
}
