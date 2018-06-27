package entities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import main.Main;

public class Item extends Entity {

	public String itemName;
	public String texturePath;
	
	public static Map<String, Item> itemList = new HashMap<String, Item>();
	
	public Item(int x, int y, String itemName) {
		super(x, y, true);
		
		if(itemList.containsKey(itemName)) {
			
			texturePath = itemList.get(itemName).texturePath;
			
		}else {
			Document d = Main.loader.loadXML(new File("resources/items/" + itemName + ".xml"));
			NodeList nl = d.getElementsByTagName("Item");
			
			Element item = Main.loader.getElement(nl);
			
			texturePath = "items/" + item.getElementsByTagName("Texture").item(0).getTextContent();
		}
		
		this.itemName = itemName;
		loadTexture(texturePath);
	}
}
