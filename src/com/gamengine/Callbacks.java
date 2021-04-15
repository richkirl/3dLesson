package com.gamengine;

import org.lwjgl.glfw.GLFW;

public class Callbacks {
	
	public void processInput(long window) {
		if(GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE)==GLFW.GLFW_PRESS) {
			GLFW.glfwSetWindowShouldClose(window, true);
		}
	}

}
