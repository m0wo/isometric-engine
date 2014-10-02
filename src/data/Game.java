package data;

import static helpers.Graphics.*;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;



public class Game {
	private Map map;

	private long lastFrame;

	private int fps;
	private long lastFPS;
	
	int[][] testMap = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

	};
	
	
	public Game() {
		initDisplay();
		
		getDelta();
		lastFPS = getTime();
		map = new Map(testMap);
		run();
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		map.draw();
		
		Display.update();
		Display.sync(60);
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public void tick(int delta) {
		// timing goes here

		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_MINUS) {
				if (Keyboard.getEventKeyState()) {

				} else {
					map.zoomOut();

				}
			} else if (Keyboard.getEventKey() == Keyboard.KEY_EQUALS) {
				if (Keyboard.getEventKeyState()) {

				} else {
					map.zoomIn();
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			map.shiftRight();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			map.shiftLeft();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			map.shiftUp();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {

			map.shiftDown();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
		}

		
		updateFPS();

	}

	public void run() {
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			tick(delta);
			render();

		}
	}

}
