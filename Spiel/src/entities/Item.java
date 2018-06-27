package entities;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import main.Main;

public class Item extends Entity {

	public String itemName;
	
	public Item(int x, int y, String itemName) {
		super(x, y, true);
		
		this.itemName = itemName;
		
		Document d = Main.loader.loadXML(new File("resources/items/" + itemName + ".xml"));
		NodeList nl = d.getElementsByTagName("Item");
		
		Element item = Main.loader.getElement(nl);
		
		String textureName = item.getElementsByTagName("Texture").item(0).getTextContent();
		
		loadTexture("items/" + textureName);
	}
}
