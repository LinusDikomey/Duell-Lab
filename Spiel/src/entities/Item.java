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
	
	
	public String meleeMode;
	public int meleeDamage;
	public float meleeRange;
	public float meleeOffset;
	
	public int cooldown;
	
	public int rangedRange;
	public int rangedDamage;
	public int rangedMagazine;
	public int rangedReloadTime;
	public int rangedSpeed;
	public String projectileName;
	
	public int currentMagazine = 0;
	public int currentreload = 0;
	public boolean reloading = false;
	
	public static Map<String, Item> itemList = new HashMap<String, Item>();
	
	public Item(int x, int y, String itemName) {
		super(x, y, true);
		
		if(itemList.containsKey(itemName)) {
			
			texturePath = itemList.get(itemName).texturePath;
			meleeMode = itemList.get(itemName).meleeMode;
			meleeDamage = itemList.get(itemName).meleeDamage;
			meleeRange = itemList.get(itemName).meleeRange;
			meleeOffset = itemList.get(itemName).meleeOffset;
			cooldown = itemList.get(itemName).cooldown;
			
			rangedRange = itemList.get(itemName).cooldown;
			rangedDamage = itemList.get(itemName).rangedDamage;
			rangedMagazine = itemList.get(itemName).rangedMagazine;
			rangedReloadTime = itemList.get(itemName).rangedReloadTime;
			rangedSpeed = itemList.get(itemName).rangedSpeed;
			projectileName = itemList.get(itemName).projectileName;
			
		}else {
			Document d = Main.loader.loadXML(new File("resources/items/" + itemName + ".xml"));
			NodeList nl = d.getElementsByTagName("Item");
			
			Element item = Main.loader.getElement(nl);
			
			texturePath = "items/" + item.getElementsByTagName("Texture").item(0).getTextContent();
			cooldown = Integer.parseInt(item.getElementsByTagName("Cooldown").item(0).getTextContent());
			if(item.getElementsByTagName("Nahkampf").getLength() > 0) {
				Element melee = (Element) item.getElementsByTagName("Nahkampf").item(0);
				meleeMode = melee.getAttribute("mode");
				meleeDamage = Integer.parseInt(melee.getElementsByTagName("Damage").item(0).getTextContent());
				meleeRange = Float.parseFloat(melee.getElementsByTagName("Range").item(0).getTextContent());
				if(melee.getElementsByTagName("Offset").getLength() > 0) {
					meleeOffset = Float.parseFloat(melee.getElementsByTagName("Range").item(0).getTextContent());
				}else {
					meleeOffset = 0;
				}
			}
			
			if(item.getElementsByTagName("Fernkampf").getLength() > 0) {
				Element ranged = (Element) item.getElementsByTagName("Fernkampf").item(0);
				rangedRange = Integer.parseInt(ranged.getElementsByTagName("Range").item(0).getTextContent());
				rangedDamage = Integer.parseInt(ranged.getElementsByTagName("Damage").item(0).getTextContent());
				rangedMagazine = Integer.parseInt(ranged.getElementsByTagName("Magazine").item(0).getTextContent());
				rangedReloadTime = Integer.parseInt(ranged.getElementsByTagName("ReloadTime").item(0).getTextContent());
				rangedSpeed = Integer.parseInt(ranged.getElementsByTagName("Speed").item(0).getTextContent());
				projectileName = ranged.getElementsByTagName("ProjectileTexture").item(0).getTextContent();
			}
			
			itemList.put(itemName, this);
		}
		
		this.itemName = itemName;
		loadTexture(texturePath);
	}
}
