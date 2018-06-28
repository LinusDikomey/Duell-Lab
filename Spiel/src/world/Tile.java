package world;

import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import main.Main;
import world.attachable.Attachable;
import world.attachable.Chest;

public class Tile {
	
	public String name;
	public int health;
	public boolean collidable;
	public boolean destroyable;
	public boolean render;
	public String texture;
	public Rectangle collisionBox = new Rectangle(0, 0, 100, 100);
	public List<Attachable> attached = new ArrayList<Attachable>();
	public boolean wasDamaged = false;
	
	private static HashMap<String, Tile> tileList = new HashMap<String, Tile>();
	
	public Tile(String name, int x, int y) {
		if(tileList.containsKey(name)) {
			this.name = name;
			this.health = tileList.get(name).health;
			this.collidable = tileList.get(name).collidable;
			this.destroyable = tileList.get(name).destroyable;
			this.render = tileList.get(name).render;
			this.texture = tileList.get(name).texture;
			this.collisionBox = tileList.get(name).collisionBox;
			attached = tileList.get(name).attached.subList(0, tileList.get(name).attached.size());
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
			
			if(tile.getElementsByTagName("Attached").getLength() != 0) {
				NodeList attachedElems = tile.getElementsByTagName("Attached").item(0).getChildNodes();
				for(int i = 0; i < attachedElems.getLength(); i++) {
					if(attachedElems.item(i).getNodeType() != Node.ELEMENT_NODE) {
						continue;
					}
					switch(attachedElems.item(i).getNodeName()) {
					case "Chest":
						attached.add(new Chest(x, y, attachedElems.item(i).getTextContent()));
						break;
						default:
							System.out.println("Error, invalid attachable in: " + name + ", name: " + attachedElems.item(i).getNodeName());
							break;
					}
				}
			}
			
		}
	}
	
	public void damage(int damage) {
		if(!wasDamaged) {
			health -= damage;
			wasDamaged = true;
			onHit();
			if(health <= 0) {
				onDestroy();
			}
		}
	}
	
	public void onHit() {
		for(Attachable a : attached) {
			a.onHit();
		}
	}
	public void onBuild() {
		for(Attachable a : attached) {
			a.onBuild();
		}
	}
	public void onDestroy() {
		for(Attachable a : attached) {
			a.onDestroy();
		}
	}	
	public void onTick() {
		wasDamaged = false;
		for(Attachable a : attached) {
			a.onTick();
		}
	}
}