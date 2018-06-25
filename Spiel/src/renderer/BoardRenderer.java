package renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import world.Board;

public class BoardRenderer  {

	int sizeX , sizeY;
	int tileSize;
	boolean useXBoard;
	boolean useXButton;
	Board board;
	private ImageLoader loader;
	public double startButtonSize;
	public Rectangle startButtonRec;
	
	public BoardRenderer(int sizeX, int sizeY, Board b, ImageLoader loader) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.loader = loader;
		
		useXBoard = !(sizeX * 9 / 16 > sizeY);
		board = b;
		loader.loadImage("tiles/ground", "ground");
		loader.loadImage("gui/startButton", "startButton");
		BufferedImage startButton = loader.getImage("startButton");
		startButtonSize = sizeX / 2f / startButton.getWidth();
		if(startButtonSize * startButton.getHeight() > sizeY / 2) {
			startButtonSize = sizeY / 2f / startButton.getHeight();
		}
		startButtonRec = new Rectangle(sizeX / 4, sizeY / 4, (int) (startButton.getWidth() * startButtonSize), (int) (startButton.getHeight() * startButtonSize));
	}
	
	public void renderMenu(Graphics g) {
		BufferedImage startButton = loader.getImage("startButton");
		g.drawImage(startButton, startButtonRec.x, startButtonRec.y, startButtonRec.width, startButtonRec.height, null);
	}
	
	public void renderGame(Graphics g) {
		g.drawImage(loader.getImage("ground"), 0, 0, sizeX, sizeY, null);
		if(useXBoard)
			tileSize = sizeX / board.SIZE_X;
		else tileSize = sizeY / board.SIZE_Y;
		
		for(int y = 0; y < board.SIZE_Y; y++) {
			for(int x = 0; x < board.SIZE_X; x++) { 
				if(board.getTile(x, y).render) {
					System.out.println("-");
					BufferedImage currentTile = loader.getTileImage(board.getTile(x, y));
					g.drawImage(currentTile, x * tileSize, y * tileSize, tileSize, tileSize, null);
				}
			}
		}
	}
	
}
