package main;

import renderer.Window;
import world.Board;

public class Main {

	static Board board;
	
	/*
	 * Start game
	 */
	public static void main(String[] args) {
		board = new Board();
		Window w = new Window(800, 450, true, board);
		w.setVisible(true);
		System.out.println(w.getContentPane().getSize());
	}
}