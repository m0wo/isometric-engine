import org.lwjgl.opengl.GL11;

public class Tile {

	private int tileHeight, tileWidth;

	public Tile(int width, int height) {
		this.tileWidth = width;
		this.tileHeight = height;

	}

	public void drawTile(int x, int y) {
		int screenX = x * tileWidth;
		int screenY = y * tileHeight;
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2f(screenX, screenY);
		GL11.glVertex2f(screenX + tileWidth, screenY);
		GL11.glVertex2f(screenX + tileWidth, screenY + tileHeight);
		GL11.glVertex2f(screenX, screenY + tileHeight);
		GL11.glEnd();
	}

	public void drawIso(int x, int y, String type) {
		int screenX = (x - y) * (tileWidth / 2);
		int screenY = (x + y) * (tileHeight / 2);

		if (type.equals("grass")){
			GL11.glColor3f(0.0f, 1.0f, 0.0f);
		}else if (type.equals("water")){
			GL11.glColor3f(0.0f, 0.0f, 1.0f);
		}

		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glVertex2f(screenX, screenY);
		GL11.glVertex2f(screenX + (tileWidth / 2), screenY + (tileHeight / 2));
		GL11.glVertex2f(screenX, screenY + tileHeight);
		GL11.glVertex2f(screenX - (tileWidth / 2), screenY + (tileHeight / 2));
		GL11.glEnd();
	}
}
