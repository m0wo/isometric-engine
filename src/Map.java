import java.util.ArrayList;
import java.util.Random;

public class Map {

	private int size;

	private int ySize;
	private int xSize;

	private int[][] testMap;
	public ArrayList<Tile> gameMap;

	public ArrayList<Tile> baseTiles;
	public Spritesheet spriteSheet;

	public Map() {

		testMap = new int[][] {
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

		};
		size = testMap.length;

		ySize = testMap.length;
		xSize = testMap[0].length;

		// System.out.println(xSize);
		// System.out.println(ySize);

		baseTiles = new ArrayList<Tile>();
		spriteSheet = new Spritesheet();
		gameMap = new ArrayList<Tile>();
		//genMap();
		randMap();
	}

	public void genMap() {

		// TODO: load from file the map data

		for (int y = 0; y < ySize; y++) {

			for (int x = 0; x < xSize; x++) {

				if (testMap[y][x] == 1)
					gameMap.add(new Tile(64, 32, x, y, "grass", spriteSheet));
				if (testMap[y][x] == 0)
					gameMap.add(new Tile(64, 32, x, y, "water", spriteSheet));
			}
		}
	}

	public void randMap() {
		Random rand = new Random();
		for (int y = -64; y < 64; y++) {
			for (int x = -64; x < 64; x++) {
				if (rand.nextInt((10 - 1) + 1) >= 5) {
					gameMap.add(new Tile(64, 32, x, y, "grass", spriteSheet));
				} else {
					gameMap.add(new Tile(64, 32, x, y, "water", spriteSheet));
				}
			}
		}
	}

	public void drawMap() {
		for (Tile t : gameMap) {
			t.drawTile();

		}
	}


	public void drawCamMap(Camera cam) {
		for (Tile t : gameMap) {
			if (((t.getWorldX() >= cam.x - 100) && (t.getWorldY() >= cam.y - 100))
					&& ((t.getWorldX() <= cam.x +  (cam.size + 100)) && (t.getWorldY() <= cam.y
							+ (cam.size + 100)))) {
				t.drawTexTile();
				
				if (t.selected){
					System.out.println(t.getWorldX() + " " + t.getWorldY());
				}
			}
			
		}
	}
	
	

	public void drawTexMap() {
		for (Tile t : gameMap) {
			t.drawTexTile();
			// System.out.println(t.x + " " + " " + t.y);

		}
	}

	public void zoomIn() {
		for (Tile t : gameMap) {
			t.tileWidth = t.tileWidth * 2;
			t.tileHeight = t.tileHeight * 2;
			System.out.println("Current tile res: " + t.tileWidth + "*"
					+ t.tileHeight);
		}

	}

	public void zoomOut() {
		for (Tile t : gameMap) {
			t.tileWidth = t.tileWidth / 2;
			t.tileHeight = t.tileHeight / 2;
		}
	}

	public void drawSqMap() {
		for (Tile t : gameMap) {
			t.drawSqTile();
		}
	}

	public void shiftRight() {
		for (Tile t : gameMap) {
			t.x--;
		}
	}

	public void shiftLeft() {
		for (Tile t : gameMap) {
			t.x++;
		}
	}

	public void shiftUp() {
		for (Tile t : gameMap) {
			t.y++;
		}
	}

	public void shiftDown() {
		for (Tile t : gameMap) {
			t.y--;
		}
	}

}
