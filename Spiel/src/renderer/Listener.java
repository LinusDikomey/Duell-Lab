package renderer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import logic.MainLogic;

public class Listener implements MouseListener, KeyListener {

	MainLogic logic;
	
	public Listener(MainLogic l) {
		logic = l;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(logic.getView() == View.MENU) {
			int maxX = (int) logic.startButton.getMaxX();
			int maxY = (int) logic.startButton.getMaxY();
			if(e.getX() > logic.startButton.x && e.getX() < maxX && e.getY() > logic.startButton.y && e.getY() < maxY) {
				logic.startGame();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			logic.stopGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
