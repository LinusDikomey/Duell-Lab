package logic;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import renderer.Listener;
import renderer.MasterRenderer;
import renderer.View;

public class MainLogic {

	MasterRenderer renderer;
	private int view = View.MENU;
	Listener listener;
	public Rectangle startButton;
	
	public MainLogic(MasterRenderer renderer) {
		this.renderer = renderer;
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
		}, 0, 50);
	}
	
	public void startGame() {
		if(view == View.MENU) {
			view = View.BOARD;
		}
	}
	
	public void stopGame() {
		if(view == View.BOARD) {
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
