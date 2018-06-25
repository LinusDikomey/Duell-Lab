package main;

import logic.MainLogic;
import renderer.Window;
import world.Board;

public class Main {

	static Board board;
	
	/**
	 * Startet das Spiel
	 * @param args
	 */
	public static void main(String[] args) {
		board = new Board();
		Window w = new Window(800, 450, true, board);
		w.setVisible(true);
		
		MainLogic logic = new MainLogic(w.renderer);
		logic.startGameLoop();
	}
}