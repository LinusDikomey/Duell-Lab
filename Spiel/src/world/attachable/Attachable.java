package world.attachable;

public abstract class Attachable {
	
	int x, y;
	
	
	
	public Attachable(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void onDestroy(){};
	public void onBuild(){};
	public void onHit(){};
	public void onTick(){};
	
}
