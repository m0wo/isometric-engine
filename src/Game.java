import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	private int width, height;
	private String title;

	private Random rand;

	private List<Tile> map;

	public Game(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;

		map = new ArrayList<Tile>();

		rand = new Random();
		initDisplay();
		generateMap();
		run();
	}

	public void generateMap() {
		for (int y = -32; y < 32; y++) {
			for (int x = -32; x < 32; x++) {
				if (rand.nextInt((10 - 1) + 1) >= 5) {
					map.add(new Tile(128, 64, x, y, "grass"));
				} else {
					map.add(new Tile(128, 64, x, y, "water"));
				}
			}
		}
	}

	public void initDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();

		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		GL11.glOrtho(0, width, height, 0, 1, -1);

	}

	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		for (Tile t : map) {
			t.drawTile();
		}

		Display.update();
	}

	public void tick() {

	}

	public void run() {
		while (!Display.isCloseRequested()) {
			render();
			tick();
		}
	}

}
