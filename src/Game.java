import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	private int width, height;
	private String title;
	private int tileWidth;
	private int tileHeight;

	public Game(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.tileHeight = 64;
		this.tileWidth = 64;
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

	public void drawBox(int x, int y) {
		GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + tileWidth, y);
			GL11.glVertex2f(x + tileWidth, y + tileHeight);
			GL11.glVertex2f(x, y + tileHeight);
		GL11.glEnd();
	}

	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		GL11.glColor3f(0.5f, 0.5f, 1.0f);
		
		
		for(int y = 0; y < 2; y++){
			for(int x = 0; x < 2; x++){
				drawBox(x * tileWidth, y * tileHeight);
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
