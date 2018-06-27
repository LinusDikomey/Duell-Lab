package toolbox;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Key {

	private static volatile HashMap<Integer, Boolean> keysPressed = new HashMap<Integer, Boolean>();
	
	public static boolean isPressed(int key) {
		synchronized (Key.class) {
			if(!keysPressed.containsKey(key)) {
				return false;
			}
			return keysPressed.get(key);
		}
	}
	
	public static void initialize() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			 @Override
		        public boolean dispatchKeyEvent(KeyEvent ke) {
		            synchronized (Key.class) {
		                switch (ke.getID()) {
		                case KeyEvent.KEY_PRESSED:
		                	keysPressed.put(ke.getKeyCode(), true);
		                    break;

		                case KeyEvent.KEY_RELEASED:
		                    keysPressed.put(ke.getKeyCode(), false);
		                    break;
		                }
		                return false;
		            }
		        }
		    });
		}
}
