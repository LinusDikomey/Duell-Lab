package renderer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Main;
import world.Board;

public class BoardRenderer  {

	int sizeX , sizeY;
	int tilesize;
	boolean useXBoard;
	boolean useXButton;
	Board board;
	public double startButtonSize;
	public Rectangle startButtonRec;
	
	public BoardRenderer(int sizeX, int sizeY, int tilesize, Board b) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.tilesize = tilesize;
		
		useXBoard = !(sizeX * 9 / 16 > sizeY);
		board = b;
		BufferedImage startButton = Main.loader.getImage("gui/startButton");
		startButtonSize = sizeX / 2f / startButton.getWidth();
		if(startButtonSize * startButton.getHeight() > sizeY / 2) {
			startButtonSize = sizeY / 2f / startButton.getHeight();
		}
		startButtonRec = new Rectangle(sizeX / 4, sizeY / 4, (int) (startButton.getWidth() * startButtonSize), (int) (startButton.getHeight() * startButtonSize));
	}
	
	public void renderMenu(Graphics g) {
		BufferedImage startButton = Main.loader.getImage("gui/startButton");
		g.drawImage(startButton, startButtonRec.x, startButtonRec.y, startButtonRec.width, startButtonRec.height, null);
	}
	
	public void renderGame(Graphics g) {
		g.drawImage(Main.loader.getImage("tiles/ground"), 0, 0, tilesize * Board.SIZE_X, tilesize * Board.SIZE_Y, null);
		
		for(int y = 0; y < Board.SIZE_Y; y++) {
			for(int x = 0; x < Board.SIZE_X; x++) { 
				if(board.getTile(x, y).render) {
					BufferedImage currentTile = Main.loader.getTileImage(board.getTile(x, y));
					g.drawImage(currentTile, x * tilesize, y * tilesize, tilesize, tilesize, null);
				}
			}
		}
		
		if(!Main.logic.entityManager.player1.alive) {
			g.drawImage(Main.loader.getImage("gui/sieg2"), 0, 0, tilesize * Board.SIZE_X, tilesize * Board.SIZE_Y, null);
		}else if(!Main.logic.entityManager.player2.alive) {
			g.drawImage(Main.loader.getImage("gui/sieg1"), 0, 0, tilesize * Board.SIZE_X, tilesize * Board.SIZE_Y, null);
		}
	}
	
}
