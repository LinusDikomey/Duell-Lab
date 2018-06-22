package renderer;

import java.awt.Graphics;

import javax.swing.JLabel;

import world.Board;

public class MasterRenderer extends JLabel {
	private static final long serialVersionUID = 1L;
	
	int sizeX, sizeY;
	BoardRenderer boardRenderer;
	
	public MasterRenderer(int sizeX, int sizeY, Board b) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		boardRenderer = new BoardRenderer(sizeX, sizeY, b);
	}
	
	@Override
	public void paint(Graphics g) {
		boardRenderer.render(g);
	}
	
}
