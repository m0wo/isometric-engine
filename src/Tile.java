import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Tile {

	public int tileWidth, tileHeight;
	public int x, y;
	private String type;
	private Texture grassTexture, waterTexture, spriteSheet;
	public boolean selected = false;

	public Tile(int width, int height, int x, int y, String type) {
		this.tileWidth = width;
		this.tileHeight = height;
		this.x = x;
		this.y = y;
		this.type = type;
		loadTexture();

	}

	public void loadTexture() {
		try {
			grassTexture = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("./res/grass.png"));
			waterTexture = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("./res/water.png"));

			spriteSheet = TextureLoader
					.getTexture("PNG", ResourceLoader
							.getResourceAsStream("./res/spritesheet.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// IF TYPE = GRASS, BIND GRASS, ELSE, BIND WATER

		/*
		 * if (type.equals("grass")) { Color.white.bind(); grassTexture.bind();
		 * } else if (type.equals("water")) { Color.white.bind();
		 * waterTexture.bind(); }
		 */

		Color.white.bind();
		spriteSheet.bind();

	}

	public void drawSqTile() {
		int screenX = x * tileWidth;
		int screenY = y * tileHeight;

		if (type.equals("grass")) {
			GL11.glColor4f(0.0f, 0.8f, 0.0f, 1.0f);
		} else if (type.equals("water")) {
			GL11.glColor4f(0.0f, 0.0f, 1.0f, 0.1f);
		}

		if (selected) {
			GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		}

		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(screenX, screenY);
		GL11.glVertex2f(screenX + tileWidth, screenY);
		GL11.glVertex2f(screenX + tileWidth, screenY + tileHeight);
		GL11.glVertex2f(screenX, screenY + tileHeight);
		GL11.glEnd();

	}

	public void drawTexTile() {
		int halfWidth = tileWidth / 2;
		int halfHeight = tileHeight / 2;
		int screenX = (x - y) * halfWidth;
		int screenY = (x + y) * halfHeight;

		if (type.equals("grass")) {
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0.5f, 0);
			GL11.glVertex2f(screenX, screenY);
			GL11.glTexCoord2f(1.0f, 0);
			GL11.glVertex2f(screenX + halfWidth, screenY + halfHeight);
			GL11.glTexCoord2f(1.0f, 0.5f);
			GL11.glVertex2f(screenX, screenY + tileHeight);
			GL11.glTexCoord2f(0.5f, 0.5f);
			GL11.glVertex2f(screenX - halfWidth, screenY + halfHeight);
			GL11.glEnd();
		}else if (type.equals("water")){
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex2f(screenX, screenY);
			GL11.glTexCoord2f(0.5f, 0);
			GL11.glVertex2f(screenX + halfWidth, screenY + halfHeight);
			GL11.glTexCoord2f(0.5f, 0.5f);
			GL11.glVertex2f(screenX, screenY + tileHeight);
			GL11.glTexCoord2f(0, 0.5f);
			GL11.glVertex2f(screenX - halfWidth, screenY + halfHeight);
			GL11.glEnd();
		}
		
		if (selected) {
			if (type.equals("grass")) {
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0.5f, 0.5f);
				GL11.glVertex2f(screenX, screenY);
				GL11.glTexCoord2f(1.0f, 0.5f);
				GL11.glVertex2f(screenX + halfWidth, screenY + halfHeight);
				GL11.glTexCoord2f(1.0f, 1.0f);
				GL11.glVertex2f(screenX, screenY + tileHeight);
				GL11.glTexCoord2f(0.5f, 1.0f);
				GL11.glVertex2f(screenX - halfWidth, screenY + halfHeight);
				GL11.glEnd();
			}else if (type.equals("water")){
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0, 0.5f);
				GL11.glVertex2f(screenX, screenY);
				GL11.glTexCoord2f(0.5f, 0.5f);
				GL11.glVertex2f(screenX + halfWidth, screenY + halfHeight);
				GL11.glTexCoord2f(0.5f, 1.0f);
				GL11.glVertex2f(screenX, screenY + tileHeight);
				GL11.glTexCoord2f(0, 1.0f);
				GL11.glVertex2f(screenX - halfWidth, screenY + halfHeight);
				GL11.glEnd();
			}
		}
		
	}

	public void drawTile() {
		int halfWidth = tileWidth / 2;
		int halfHeight = tileHeight / 2;
		int screenX = (x - y) * halfWidth;
		int screenY = (x + y) * halfHeight;

		if (type.equals("grass")) {
			GL11.glColor4f(0.0f, 0.8f, 0.0f, 1.0f);
		} else if (type.equals("water")) {
			GL11.glColor4f(0f, 0.4f, 1.0f, 1.0f);
		}

		if (selected) {
			GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		}

		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(screenX, screenY);
		GL11.glVertex2f(screenX + halfWidth, screenY + halfHeight);
		GL11.glVertex2f(screenX, screenY + tileHeight);
		GL11.glVertex2f(screenX - halfWidth, screenY + halfHeight);
		GL11.glEnd();
	}
}
