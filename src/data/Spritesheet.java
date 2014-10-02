package data;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Spritesheet {
	private Texture spriteSheet;
	
	public Spritesheet(){
		loadTextures();
	}
	
	public void loadTextures(){
		try {
			setSpriteSheet(TextureLoader
					.getTexture("PNG", ResourceLoader
							.getResourceAsStream("./res/spritesheet.png")));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Color.white.bind();
		spriteSheet.bind();
	}

	public Texture getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(Texture spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
}
