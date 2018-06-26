package toolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import world.Board;
import world.Tile;

public class SaveLoadManager {

	public static Tile[][] load(File file) {
		System.out.println("Lade: " + file.getName());
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Tile[][] tiles = new Tile[Board.SIZE_X][Board.SIZE_Y];
		
		for(int y = 0; y < Board.SIZE_Y; y++) {
			String line = "";
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] elems = line.split(",");
			for(int x = 0; x < Board.SIZE_X; x++) {
				tiles[x][y] = new Tile(elems[x]);
			}
		}
		return tiles;
	}	
	
	public static void save(Tile[][] tiles, File file) {
		PrintStream stream = null;
		try {
			stream = new PrintStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int y = 0; y < Board.SIZE_Y; y++) {
			for(int x = 0; x < Board.SIZE_X; x++) {
				stream.print(tiles[x][y].name);
				if(x < Board.SIZE_X - 1) {
					stream.print(",");
				}
			}
			stream.print("\n");
		}
		stream.close();
	}
}