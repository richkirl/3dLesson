package com.gamengine;

import org.lwjgl.glfw.GLFW;


import com.gamengine.render.Loader;
import com.gamengine.render.RawModel;
import com.gamengine.render.Renderer;

public class Engine {
	Window window = new Window();
	Callbacks callbacks = new Callbacks();
	
	public void init() {
		GLFW.glfwInit();
	    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
	    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 6);
	    GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
	    window.create();
	    
	    
	}

	public void run() {
		
		init();
		loop();
		
	}

	public void loop() {
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		float[] vertices = {
	    		-0.5f, 0.5f, 0.0f,
	    		-0.5f, -0.5f, 0.0f,
	    		0.5f, -0.5f, 0.0f,
				0.5f, 0.5f, 0.0f,
	    };
		int[] indices = {
				0,1,3,
				3,1,2
		};

	    RawModel model = loader.loadToVao(vertices,indices);
		
		while(!GLFW.glfwWindowShouldClose(window.getWindow())) {
			
			callbacks.processInput(window.getWindow());
			
			renderer.prepare();
			
			renderer.render(model);
			GLFW.glfwSwapBuffers(window.getWindow());
			GLFW.glfwPollEvents();
			
		}
		loader.clean();
		window.destroy();
		
	}
}
