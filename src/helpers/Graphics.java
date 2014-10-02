package helpers;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Graphics {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 960;

	// private String title;

	public static void initDisplay() {

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("isometric game");
			Display.create();
			Display.setVSyncEnabled(true);

		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		glEnable(GL_TEXTURE_2D);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glClearColor(0.4f, 0.4f, 1.0f, 1.0f);

		glViewport(0, 0, WIDTH, HEIGHT);
		glMatrixMode(GL_MODELVIEW);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	public static void drawTile(Texture tex, int x, int y, int width, int height) {
		int halfWidth = width / 2;
		int halfHeight = height / 2;

		int screenX = (x - y) * halfWidth;
		int screenY = (x + y) * halfHeight;
		
		tex.bind();
		glTranslatef(x, y, 0);
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(screenX, screenY);
			glTexCoord2f(1, 0);
			glVertex2f(screenX + halfWidth, screenY + halfHeight);
			glTexCoord2f(1, 1);
			glVertex2f(screenX, screenY + height);
			glTexCoord2f(0, 1);
			glVertex2f(screenX - halfWidth, screenY + halfHeight);
		glEnd();
		glLoadIdentity();
		

	}
	
	public static Texture textureLoad(String path, String fileType){
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try{
			tex = TextureLoader.getTexture(fileType, in);
		}catch(IOException e){
			e.printStackTrace();
		}
		return tex;
	}
	
	//THIS CAN PROBABLY BE TURNED INTO OVERLOADS..SOMEHOW
	
	public static Texture loadTexture(String name){
		Texture tex = textureLoad("res/" + name + ".png", "PNG");
		return tex;
	}

}
