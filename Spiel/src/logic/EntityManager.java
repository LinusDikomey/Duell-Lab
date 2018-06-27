package logic;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import entities.Entity;
import entities.Player;
import toolbox.Loader;

public class EntityManager {

	Loader loader;
	public Player player1;
	public Player player2;
	
	public EntityManager(Loader loader) {
		this.loader = loader;
	}
	
	public Queue<Entity> entities = new ConcurrentLinkedQueue<Entity>();
	
	public void addPlayers() {
		player1 = new Player(0, 0, loader, 0, this);
		player2 = new Player(31 * 100, 17 * 100, loader, 1, this);
		entities.add(player1);
		entities.add(player2);
	}
}
