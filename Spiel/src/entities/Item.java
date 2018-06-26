package entities;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import renderer.Loader;

public class Item extends Entity {

	public Item(int x, int y, String itemName, Loader loader) {
		super(x, y, loader);
		Document d = loader.loadXML(new File("resources/items/" + itemName + ".xml"));
		NodeList nl = d.getElementsByTagName("Item");
		Element item = loader.getElement(nl);
		String textureName = item.getElementsByTagName("Texture").item(0).getTextContent();
		loadTexture("items/" + textureName);
	}
}
