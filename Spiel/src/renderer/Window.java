package renderer;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public MasterRenderer renderer;
	public int displayX, displayY;
	
	public Window(int sizeX, int sizeY, boolean fullscreen) {
		super("Spiel");
		
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
	}
}
