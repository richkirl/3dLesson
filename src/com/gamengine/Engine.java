package com.gamengine;

import entities.Camera;
import entities.Entity;
import models.TexturedModel;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;


import com.gamengine.render.Loader;
import models.RawModel;
import com.gamengine.render.Renderer;
import shaders.StaticShader;
import textures.Modeltexture;

import static org.lwjgl.glfw.GLFW.glfwSwapInterval;

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

		StaticShader staticshader = new StaticShader();
		Renderer renderer = new Renderer(window,staticshader);
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
		Entity entity = new Entity(texturedModel,new Vector3f(0,0,1),0,0,0,1);
		Camera camera = new Camera(window);
		while(!GLFW.glfwWindowShouldClose(window.getWindow())) {

			entity.increasePosition(0,0,-0.002f);
			camera.move(window);
			//entity.increaseRotation(0,0,1);
			callbacks.processInput(window.getWindow());
			
			renderer.prepare();

			staticshader.start();
			glfwSwapInterval(1);
			staticshader.loadviewMatrix(camera);
			renderer.render(entity,staticshader);
			staticshader.stop();
			GLFW.glfwSwapBuffers(window.getWindow());

			GLFW.glfwPollEvents();

			
		}
		staticshader.clean();
		loader.clean();
		window.destroy();
		
	}
}
