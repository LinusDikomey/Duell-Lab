package renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import world.Tile;

public class ImageLoader {

	private HashMap<Tile, BufferedImage> tiles = new HashMap<Tile, BufferedImage>();
	
	public BufferedImage getTileImage(Tile tile) {
		if(tiles.containsKey(tile)) {
			return tiles.get(tile);
		}else {
			if(tile.render) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("resources/textures/tiles/" + tile.imageName + ".png"));
				} catch (IOException e) {e.printStackTrace();}
				tiles.put(tile, image);
				return image;
			}else {
				tiles.put(tile, null);
				return null;
			}
		}
	}
	
	private HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	
	public BufferedImage getImage(String path) {
		if(!images.containsKey(path)) {
			try {
				images.put(path, ImageIO.read(new File("resources/textures/" + path + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return images.get(path);
	}
	
	/**
	 * Loads a PNG image inside the resources folder to the Buffer.
	 * @param path Path relative to the resources folder. File ending not required.
	 * @param name Name of the image in the Buffer used to identify it.
	 */
	
	/*public void loadImage(String path, String name) {
		try {
			BufferedImage image = ImageIO.read(new FileInputStream("resources/textures/" + path + ".png"));
			images.put(name, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
}
