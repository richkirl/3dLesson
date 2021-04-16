package com.gamengine;

import models.TexturedModel;
import org.lwjgl.glfw.GLFW;


import com.gamengine.render.Loader;
import models.RawModel;
import com.gamengine.render.Renderer;
import shaders.StaticShader;
import textures.Modeltexture;

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
		StaticShader statics = new StaticShader();
		float[] vertices = {
	    		-0.5f, 0.5f, 0.0f, //
	    		-0.5f, -0.5f, 0.0f, //
	    		0.5f, -0.5f, 0.0f,  //
				0.5f, 0.5f, 0.0f   //

	    };
		int[] indices = {
				0,1,3, //
				3,1,2  //
		};

		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};

	    RawModel model = loader.loadToVao(vertices,textureCoords,indices);
		Modeltexture texture = new Modeltexture(loader.loadTexture("grass1.png"));
		TexturedModel texturedModel = new TexturedModel(model,texture);
		while(!GLFW.glfwWindowShouldClose(window.getWindow())) {
			
			callbacks.processInput(window.getWindow());
			
			renderer.prepare();
			statics.start();
			renderer.render(texturedModel);
			statics.stop();
			GLFW.glfwSwapBuffers(window.getWindow());
			GLFW.glfwPollEvents();
			
		}
		statics.clean();
		loader.clean();
		window.destroy();
		
	}
}
