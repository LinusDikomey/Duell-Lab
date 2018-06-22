package renderer;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import world.Board;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private MasterRenderer r;
	
	public Window(int sizeX, int sizeY, boolean fullscreen, Board b) {
		super("Spiel");
		int displayX, displayY;
		
		if(fullscreen) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			sizeX = screenSize.width;
			sizeY = screenSize.height;
			setUndecorated(true);
			
			displayX = sizeX;
			displayY = sizeY;
		}else {
			displayX = sizeX - 18;
			displayY = sizeY - 47;
		}
		
		setSize(sizeX, sizeY);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r = new MasterRenderer(displayX, displayY, b);
		this.add(r);
	}
}
