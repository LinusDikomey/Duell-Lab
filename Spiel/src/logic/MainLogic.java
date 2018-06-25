package logic;

import renderer.MasterRenderer;

public class MainLogic {

	boolean gameRunning = false;
	MasterRenderer renderer;
	
	
	public MainLogic(MasterRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void startGameLoop() {
		while(true) {
			if(gameRunning) {
				gameTick();
			}else {
				menuTick();
			}
		}
	}
	
	private void gameTick() {
		
	}
	
	private void menuTick() {
		
	}
}
