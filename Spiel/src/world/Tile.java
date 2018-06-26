package world;

import java.awt.Rectangle;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import toolbox.Loader;

public class Tile {

	private static Loader loader = new Loader();
	
	public String name;
	public int health;
	public boolean collidable;
	protected boolean destroyable;
	public boolean render;
	public String texture;
	public Rectangle collisionBox = new Rectangle(0, 0, 100, 100);
	
	public Tile(String name) {
		this.name = name;
		Document d = loader.loadXML(new File("resources/tiles/" + name + ".xml"));
		Element tile = loader.getElement(d.getElementsByTagName("Tile"));
		
		health = Integer.parseInt(tile.getElementsByTagName("Health").item(0).getTextContent());
		texture = tile.getElementsByTagName("Texture").item(0).getTextContent();
		render = Boolean.parseBoolean(tile.getElementsByTagName("Render").item(0).getTextContent());
		destroyable = Boolean.parseBoolean(tile.getElementsByTagName("Destroyable").item(0).getTextContent());
		collidable = Boolean.parseBoolean(tile.getElementsByTagName("Collidable").item(0).getTextContent());
	}
	
	public void onAttack() {}
	public void onBuild() {}
	public void onDestroy() {}

	
	
}