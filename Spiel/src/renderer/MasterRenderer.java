package renderer;

import java.awt.Graphics;

import javax.swing.JLabel;

import world.Board;

public class MasterRenderer extends JLabel {
	private static final long serialVersionUID = 1L;
	
	int sizeX, sizeY;
	BoardRenderer boardRenderer;
	public int view = 1;
	
	public MasterRenderer(int sizeX, int sizeY, Board b) {
		ImageLoader loader = new ImageLoader();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		boardRenderer = new BoardRenderer(sizeX, sizeY, b, loader);
	}
	
	@Override
	public void paint(Graphics g) {
		if(view == View.MENU) {
			boardRenderer.renderMenu(g);
		}else if(view == View.BOARD) {
			boardRenderer.renderGame(g);
		}
	}
	
}
