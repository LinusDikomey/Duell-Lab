package renderer;

import java.awt.Graphics;

import javax.swing.JLabel;

import logic.EntityManager;
import world.Board;

public class MasterRenderer extends JLabel {
	private static final long serialVersionUID = 1L;
	
	public int sizeX, sizeY;
	public BoardRenderer boardRenderer;
	public EntityRenderer entityRenderer;
	public int currentView = 0;
	
	public MasterRenderer(int sizeX, int sizeY, Board b, EntityManager entityManager) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		int tilesize = 0;
		
		if(!(sizeX * 9 / 16 > sizeY))
			tilesize = sizeX / Board.SIZE_X;
		else tilesize = sizeY / Board.SIZE_Y;
		
		boardRenderer = new BoardRenderer(sizeX, sizeY, tilesize, b);
		entityRenderer = new EntityRenderer(tilesize, entityManager);
		
	}
	
	@Override
	public void paint(Graphics g) {
		if(currentView == View.MENU) {
			boardRenderer.renderMenu(g);
		}else if(currentView == View.BOARD) {
			boardRenderer.renderGame(g);
			entityRenderer.render(g);
		}
	}
	
}
