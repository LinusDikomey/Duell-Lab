package world;

public abstract class Tile {

	protected int health = 1;
	protected boolean walkable = false;
	protected boolean destroyable = false;
	public boolean render = false;
	public String imageLocation = "";
	
	public Tile() {
		this.imageLocation = imageLocation;
	}
	
	public void onAttack() {}
	public void onBuild() {}
	public void onDestroy() {}
}
