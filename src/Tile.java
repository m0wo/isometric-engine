import org.lwjgl.opengl.GL11;


public class Tile {
	
	private int tileHeight, tileWidth;
	
	public Tile(int width, int height){
		this.tileWidth = width;
		this.tileHeight = height;

	}
	
	public void drawTile(int x, int y){
		x *= tileWidth;
		y *= tileHeight;
		GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glVertex2f(x, y);
			GL11.glVertex2f(x + tileWidth, y);
			GL11.glVertex2f(x + tileWidth, y + tileHeight);
			GL11.glVertex2f(x, y + tileHeight);
		GL11.glEnd();
	}

}
