package main;

import logic.Listener;
import logic.MainLogic;
import renderer.Window;
import toolbox.Key;
import toolbox.Loader;

public class Main {

	public static MainLogic logic;
	public static Loader loader;
	
	/**
	 * Startet das Spiel
	 * @param args
	 */
	
	public static void main(String[] args) {
		Window w = new Window(800, 450, true);
		
		Key.initialize();
		loader = new Loader();
		logic = new MainLogic(w.displayX, w.displayY);
		Listener listener = new Listener();
		w.addMouseListener(listener);
		w.addKeyListener(listener);
		w.add(logic.renderer);
		w.setVisible(true);

		logic.startGameLoop();
	}
}