package main;

import logic.Listener;
import logic.MainLogic;
import renderer.Window;
import toolbox.Key;

public class Main {

	
	/**
	 * Startet das Spiel
	 * @param args
	 */
	
	public static void main(String[] args) {
		Window w = new Window(800, 450, true);
		
		Key.initialize();
		MainLogic logic = new MainLogic(w.displayX, w.displayY);
		Listener listener = new Listener(logic);
		w.addMouseListener(listener);
		w.addKeyListener(listener);
		w.add(logic.renderer);
		w.setVisible(true);
		
		logic.board.loadLevel("level");
		logic.startGameLoop();
	}
}