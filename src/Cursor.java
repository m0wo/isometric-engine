import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;


public class Cursor {
	
	private int mouseX, mouseY;
	private int tileX, tileY;
	public Cursor(){
		
	}
	
	public void updateCursor(){
		mouseX = Mouse.getX();
		mouseY = 600 - Mouse.getY();
	}
	
	public void drawCursor(){
		GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2f(mouseX, mouseY);
		GL11.glVertex2f(mouseX + 64, mouseY + 32);
		GL11.glVertex2f(mouseX, mouseY + 64);
		GL11.glVertex2f(mouseX - 64, mouseY + 32);
		GL11.glEnd();
	}
	
	public void drawSqCursor(){
		GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		GL11.glVertex2f(mouseX, mouseY);
		GL11.glVertex2f(mouseX + 128, mouseY);
		GL11.glVertex2f(mouseX + 128 , mouseY + 64);
		GL11.glVertex2f(mouseX , mouseY + 64);
		GL11.glEnd();
	}
	
	public void getSelected(){
		
	}
	
	//TODO: THE PROCESS TO HIGHLIGHT THE THINGS
	//CONVERT X Y ON SCREEN BACK INTO ORTHO
	//FIND THE TILE AT CURRENT ORTHO POSITION
	//HIGHLIGHT
	
	public void highlightTile(Map map){
		tileX = (mouseX / 64 + mouseY / 32) / 2;
		tileY = (mouseY / 32 - (mouseX / 64)) / 2;
		
		//tileX = mouseX / 128;
		//tileY = mouseY / 64;
		
		for (Tile t : map.gameMap){
			if ((t.x == tileX) && (t.y == tileY)){
				
				t.selected = true;
			}
		}
		System.out.println("Mouse x: " + mouseX + " Mouse y: " + mouseY);
		System.out.println("x: " + tileX + " y: " + tileY);
	}
	
	public void highlightSqTile(Map map){
		tileX = mouseX / 128;
		tileY = mouseY / 64;
		
		for (Tile t : map.gameMap){
			if ((t.x == tileX) && (t.y == tileY)){
				
				t.selected = true;
			}
		}
		
		System.out.println("Mouse x: " + mouseX + " Mouse y: " + mouseY);
		System.out.println("x: " + tileX + " y: " + tileY);
	}
	
	
	
}
