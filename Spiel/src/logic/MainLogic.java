package logic;

import java.util.Timer;
import java.util.TimerTask;

import renderer.MasterRenderer;
import renderer.View;

public class MainLogic {

	MasterRenderer renderer;
	
	
	public MainLogic(MasterRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void startGameLoop() {
		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if(renderer.view == View.BOARD) {
					gameTick();
				}else if(renderer.view == View.MENU) {
					menuTick();
				}
				
			}
		}, 0, 50);
	}
	
	private void gameTick() {
		renderer.repaint();
	}
	
	private void menuTick() {
		
	}
}
