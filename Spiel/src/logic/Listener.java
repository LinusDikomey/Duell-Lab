package logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Main;
import renderer.View;

public class Listener implements MouseListener, KeyListener {
	
	public Listener() {
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(Main.logic.getView() == View.MENU) {
			int maxX = (int) Main.logic.startButton.getMaxX();
			int maxY = (int) Main.logic.startButton.getMaxY();
			if(e.getX() > Main.logic.startButton.x && e.getX() < maxX && e.getY() > Main.logic.startButton.y && e.getY() < maxY) {
				Main.logic.startGame();
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
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE && Main.logic.getView() == View.BOARD) {
			Main.logic.stopGame();
		}
		
		/*if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			logic.entityManager.player1.movementX = -20;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			logic.entityManager.player1.movementX = 20;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			logic.entityManager.player1.movementY = 20;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			logic.entityManager.player1.movementY = -20;
		}else {
			logic.entityManager.player1.movementY = 0;
		}*/
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
