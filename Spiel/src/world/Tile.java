package world;

public abstract class Tile {

	protected int health;
	protected boolean walkable;
	protected boolean destroyable;
	public boolean render;
	public String imageName;
	public int id;
	
	
	public Tile(int health, boolean walkable, boolean destroyable, boolean render, String imageName, int id) {
		this.health = health;
		this.walkable = walkable;
		this.destroyable = destroyable;
		this.render = render;
		this.imageName = imageName;
		this.id = id;
	}
	
	public void onAttack() {}
	public void onBuild() {}
	public void onDestroy() {}
}
