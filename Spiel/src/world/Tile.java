package world;

public abstract class Tile {

	protected int health;
	protected boolean walkable;
	protected boolean destroyable;
	public boolean render;
	public String imageName;
	
	
	
	public Tile(int health, boolean walkable, boolean destroyable, boolean render, String imageName) {
		this.health = health;
		this.walkable = walkable;
		this.destroyable = destroyable;
		this.render = render;
		this.imageName = imageName;
	}
	
	public void onAttack() {}
	public void onBuild() {}
	public void onDestroy() {}
}
