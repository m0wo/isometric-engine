import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	private int width, height;
	private String title;

	private Tile tile;
	private Random rand;

	public Game(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.tile = new Tile(128, 64);

		rand = new Random();
		initDisplay();
		run();
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

		//GL11.glColor3f(0.5f, 0.5f, 1.0f);

		for (int y = -height; y < height; y++) {
			for (int x = -width; x < width; x++) {
				if (rand.nextInt((10 - 1) + 1) >= 5) {
					tile.drawIso(x, y, "grass");
				}else{
					tile.drawIso(x, y, "water");
				}
			}
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
