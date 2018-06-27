package logic;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import entities.Entity;
import entities.Player;

public class EntityManager {

	public Player player1;
	public Player player2;

	public Queue<Entity> entities = new ConcurrentLinkedQueue<Entity>();
	
	public void addPlayers() {
		player1 = new Player(0, 0, 0);
		player2 = new Player(31 * 100, 17 * 100, 1);
		entities.add(player1);
		entities.add(player2);
	}
}
