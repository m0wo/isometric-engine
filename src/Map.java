import java.util.ArrayList;
import java.util.Random;

public class Map {

	private int size;

	private int ySize;
	private int xSize;

	private int[][] testMap;
	public ArrayList<Tile> gameMap;

	public Map() {

		testMap = new int[][] {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

		};
		size = testMap.length;

		ySize = testMap.length;
		xSize = testMap[0].length;

		//System.out.println(xSize);
		//System.out.println(ySize);
		gameMap = new ArrayList<Tile>();
		//genMap();
		randMap();
	}

	public void genMap() {
		
		// TODO: load from file the map data

		for (int y = 0; y < ySize; y++) {

			for (int x = 0; x < xSize; x++) {

				if (testMap[y][x] == 1)
					gameMap.add(new Tile(128, 64, x, y, "grass"));
				if (testMap[y][x] == 0)
					gameMap.add(new Tile(128, 64, x, y, "water"));
			}
		}

		/*
		 * for (int y = 0; y < size; y++){ for (int x = 0; x < size; x++){
		 * if(testMap[x][y] == 1){ gameMap.add(new Tile(128, 64, x, y,
		 * "grass")); }else if(testMap[x][y] == 0){ gameMap.add(new Tile(128,
		 * 64, x, y, "water")); } } }
		 */

	}
	
	
	public void randMap() {
		Random rand = new Random();
		for (int y = -32; y < 32; y++) {
			for (int x = -32; x < 32; x++) {
				if (rand.nextInt((10 - 1) + 1) >= 5) {
					gameMap.add(new Tile(128, 64, x, y, "grass"));
				} else {
					gameMap.add(new Tile(128, 64, x, y, "water"));
				}
			}
		}
	}

	public void drawMap() {
		for (Tile t : gameMap) {
			t.drawTile();
		}
	}

	public void shiftRight() {
		for (Tile t : gameMap) {
			t.x++;
		}
	}

	public void shiftLeft() {
		for (Tile t : gameMap) {
			t.x--;
		}
	}

	public void shiftUp() {
		for (Tile t : gameMap) {
			t.y--;
		}
	}

	public void shiftDown() {
		for (Tile t : gameMap) {
			t.y++;
		}
	}

}
