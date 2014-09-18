import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	private int width, height;
	private String title;

	private Random rand;

	// private List<Tile> map;
	private Map map;

	private long lastFrame;

	private int fps;
	private long lastFPS;
	private int mouseX, mouseY;
	private Pointer pointer;

	public Game(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;

		pointer = new Pointer();
		map = new Map();
		// map = new ArrayList<Tile>();

		rand = new Random();
		initDisplay();
		// generateMap();
		getDelta();
		lastFPS = getTime();
		run();
	}

	/*
	 * public void generateMap() { for (int y = -32; y < 32; y++) { for (int x =
	 * -32; x < 32; x++) { if (rand.nextInt((10 - 1) + 1) >= 5) { map.add(new
	 * Tile(128, 64, x, y, "grass")); } else { map.add(new Tile(128, 64, x, y,
	 * "water")); } } } }
	 */
	public void initDisplay() {

		Mouse.setGrabbed(true);

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

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		map.drawMap();
		// pointer.drawCursor();
		Display.update();
		Display.sync(60);
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void tick(int delta) {
		// timing goes here

		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			map.shiftRight();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			map.shiftLeft();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			map.shiftUp();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			map.shiftDown();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_EQUALS)) {
			map.zoomIn();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_MINUS)) {
			map.zoomOut();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
		}

		pointer.updateCursor();
		pointer.highlightTile(map);
		updateFPS();

	}

	public void run() {
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			tick(delta);
			render();

		}
	}

}
