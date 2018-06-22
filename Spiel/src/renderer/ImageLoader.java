package renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import world.Tile;

public class ImageLoader {

	private HashMap<Tile, BufferedImage> tiles = new HashMap<Tile, BufferedImage>();
	
	public BufferedImage getImage(Tile tile) {
		if(tiles.containsKey(tile)) {
			return tiles.get(tile);
		}else {
			if(tile.render) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("tile.imageLocation"));
				} catch (IOException e) {e.printStackTrace();}
				tiles.put(tile, image);
				return image;
			}else {
				tiles.put(tile, null);
				return null;
			}
		}
	}
	
}
