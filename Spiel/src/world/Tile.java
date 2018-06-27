package world;

import java.awt.Rectangle;
import java.io.File;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.Main;

public class Tile {
	
	public String name;
	public int health;
	public boolean collidable;
	protected boolean destroyable;
	public boolean render;
	public String texture;
	public Rectangle collisionBox = new Rectangle(0, 0, 100, 100);
	
	private static HashMap<String, Tile> tileList = new HashMap<String, Tile>();
	
	public Tile(String name) {
		if(tileList.containsKey(name)) {
			this.name = name;
			this.health = tileList.get(name).health;
			this.collidable = tileList.get(name).collidable;
			this.destroyable = tileList.get(name).destroyable;
			this.render = tileList.get(name).render;
			this.texture = tileList.get(name).texture;
			this.collisionBox = tileList.get(name).collisionBox;
		}else {
			this.name = name;
			Document d = Main.loader.loadXML(new File("resources/tiles/" + name + ".xml"));
			Element tile = Main.loader.getElement(d.getElementsByTagName("Tile"));
			
			health = Integer.parseInt(tile.getElementsByTagName("Health").item(0).getTextContent());
			texture = tile.getElementsByTagName("Texture").item(0).getTextContent();
			render = Boolean.parseBoolean(tile.getElementsByTagName("Render").item(0).getTextContent());
			destroyable = Boolean.parseBoolean(tile.getElementsByTagName("Destroyable").item(0).getTextContent());
			collidable = Boolean.parseBoolean(tile.getElementsByTagName("Collidable").item(0).getTextContent());
			Element collision = (Element) tile.getElementsByTagName("CollisionBox").item(0);
			collisionBox = new Rectangle(Integer.parseInt(collision.getElementsByTagName("X").item(0).getTextContent()), Integer.parseInt(collision.getElementsByTagName("Y").item(0).getTextContent()), Integer.parseInt(collision.getElementsByTagName("Width").item(0).getTextContent()), Integer.parseInt(collision.getElementsByTagName("Height").item(0).getTextContent()));
		}
	}
	
	public void onAttack() {}
	public void onBuild() {}
	public void onDestroy() {}	
}