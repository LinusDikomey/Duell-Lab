package logic;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import renderer.Loader;

public class EntityManager {

	Loader loader;
	
	public EntityManager(Loader loader) {
		this.loader = loader;
	}
	
	public List<Entity> entities = new ArrayList<Entity>();
}
