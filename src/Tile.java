import org.lwjgl.opengl.GL11;

public class Tile {

	private int tileWidth, tileHeight;
	private int halfWidth, halfHeight;
	public int x, y;
	private String type;
	public boolean selected = false;

	public Tile(int width, int height, int x, int y, String type) {
		this.tileWidth = width;
		this.tileHeight = height;
		this.x = x;
		this.y = y;
		this.type = type;

		halfWidth = tileWidth / 2;
		halfHeight = tileHeight / 2;
	}

	public void drawTile() {
		int screenX = (x - y) * halfWidth;
		int screenY = (x + y) * halfHeight;

		if (type.equals("grass")) {
			GL11.glColor4f(0.0f, 0.8f, 0.0f, 1.0f);
		} else if (type.equals("water")) {
			GL11.glColor4f(0.0f, 0.0f, 1.0f, 0.1f);
		} 
		
		if (selected){
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
