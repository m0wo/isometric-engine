package data;

import static helpers.Graphics.*;

import org.newdawn.slick.opengl.Texture;



public class Tile {

	public int tileWidth, tileHeight;
	public int x, y;
	private TileType type;
	private Texture texture;

	public boolean selected = false;

	public Tile(int width, int height, int x, int y, TileType type) {
		this.tileWidth = width;
		this.tileHeight = height;
		this.x = x;
		this.y = y;
		this.type = type;
		this.texture = loadTexture(type.textureName);
	}
	
	public int getWorldX(){
		int halfWidth = tileWidth / 2;
		int screenX = (x - y) * halfWidth;
		
		return screenX;
	}
	
	public int getWorldY(){
		int halfHeight = tileHeight / 2;
		int screenY = (x + y) * halfHeight;
		
		return screenY;
	}
	
	public void draw(){
		drawTile(texture, x, y, tileWidth, tileHeight);
	}
	


}
