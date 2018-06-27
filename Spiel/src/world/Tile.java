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
		System.out.println("Erstellt: " + name);
		this.name = name;
		Document d = loader.loadXML(new File("resources/tiles/" + name + ".xml"));
		Element tile = loader.getElement(d.getElementsByTagName("Tile"));
		
		health = Integer.parseInt(tile.getElementsByTagName("Health").item(0).getTextContent());
		texture = tile.getElementsByTagName("Texture").item(0).getTextContent();
		render = Boolean.parseBoolean(tile.getElementsByTagName("Render").item(0).getTextContent());
		destroyable = Boolean.parseBoolean(tile.getElementsByTagName("Destroyable").item(0).getTextContent());
		collidable = Boolean.parseBoolean(tile.getElementsByTagName("Collidable").item(0).getTextContent());
		Element collision = (Element) tile.getElementsByTagName("CollisionBox").item(0);
		collisionBox = new Rectangle(Integer.parseInt(collision.getElementsByTagName("X").item(0).getTextContent()), Integer.parseInt(collision.getElementsByTagName("Y").item(0).getTextContent()), Integer.parseInt(collision.getElementsByTagName("Width").item(0).getTextContent()), Integer.parseInt(collision.getElementsByTagName("Height").item(0).getTextContent()));
	}
	
	private Tile(String name, int health, boolean collidable, boolean destroyable, boolean render, String texture,
			Rectangle collisionBox) {
		this.name = name;
		this.health = health;
		this.collidable = collidable;
		this.destroyable = destroyable;
		this.render = render;
		this.texture = texture;
		this.collisionBox = collisionBox;
	}
	
	public void onAttack() {}
	public void onBuild() {}
	public void onDestroy() {}

	
	
	
	public Tile clone() {
		return new Tile(name, health, collidable, destroyable, render, texture, collisionBox);
	}
	
	
}