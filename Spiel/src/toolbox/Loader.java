package toolbox;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import world.Tile;

public class Loader {

	private HashMap<Tile, BufferedImage> tiles = new HashMap<Tile, BufferedImage>();
	
	DocumentBuilder builder;
	
	public Loader() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getTileImage(Tile tile) {
		if(tiles.containsKey(tile)) {
			return tiles.get(tile);
		}else {
			if(tile.render) {
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("resources/textures/tiles/" + tile.texture + ".png"));
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
	
	private HashMap<String, BufferedImage[]> rotatedImages = new HashMap<String, BufferedImage[]>();
	
	public BufferedImage[] getRotatedImage(String path) {
		if(!rotatedImages.containsKey(path)) {
			try {
				BufferedImage img = ImageIO.read(new File("resources/textures/" + path + ".png"));
				BufferedImage[] rotated = new BufferedImage[8];
				for(int i = 0; i < 8; i++) {
					rotated[i] = rotateImage(img, i * 45);
				}
				rotatedImages.put(path, rotated);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return rotatedImages.get(path);
	}
	
	public static BufferedImage rotateImage(BufferedImage img, int rotation) {
	int w = img.getWidth();
	int h = img.getHeight();
	BufferedImage newImage = new BufferedImage(w, h, img.getType());
	Graphics2D g2 = newImage.createGraphics();
	g2.rotate(Math.toRadians(rotation), w/2, h/2);
	g2.drawImage(img,null,0,0);
	return newImage;
	}
	
	/**
	 * Loads a PNG image inside the resources folder to the Buffer.
	 * @param path Path relative to the resources folder. File ending not required.
	 * @param name Name of the image in the Buffer used to identify it.
	 */
	
	public Document loadXML(File file) {
		try {
			Document d = builder.parse(file);
			return d;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Element getElement(NodeList nl) {
		for(int i = 0; i < nl.getLength(); i++) {
			if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
				return (Element) nl.item(i);
			}
		}
		return null;
	}

	public Map<Integer, String> getLoot(String lootName) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
