package com.gamengine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;


public class Window {
	private int width=800,height=600;
	private String title="Game";
	private long window;
	
	public void create() {
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		if(window == 0) {
			System.err.println("Not Created Window");
			destroy();
			
		}
		
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
	}
	
	public void destroy() {
		GLFW.glfwTerminate();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public long getWindow() {
		return window;
	}
	
	

}
