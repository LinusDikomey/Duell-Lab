package renderer;

import java.awt.Color;
import java.awt.Graphics;

import world.Board;

public class BoardRenderer  {

	int sizeX , sizeY;
	int tileSize;
	boolean useX;
	Board board;
	
	public BoardRenderer(int sizeX, int sizeY, Board b) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		useX = !(sizeX * (9 / 16) > sizeY);
		board = b;
	}
	
	public void render(Graphics g) {
		if(useX)
			tileSize = sizeX / board.SIZE_X;
		else tileSize = sizeY / board.SIZE_Y;
		
		boolean test = false;
		
		for(int y = 0; y < board.SIZE_Y; y++) {
			for(int x = 0; x < board.SIZE_X; x++) {
				//BufferedImage b = null; Insert image Loader
				test = !test;
				if(test) {
					g.setColor(Color.WHITE);
				}else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
			}
			test = !test;
		}
	}
	
}
