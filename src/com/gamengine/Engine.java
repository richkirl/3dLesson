package com.gamengine;



import com.gamengine.render.*;
import entities.Camera;
import entities.Entity;
import models.TexturedModel;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;


import models.RawModel;
import shaders.StaticShader;
import textures.Modeltexture;

import java.io.FileNotFoundException;

import static org.lwjgl.glfw.GLFW.glfwSwapInterval;

public class Engine {
	Window window = new Window();
	Callbacks callbacks = new Callbacks();
	//private com.gamengine.render.OBJLoader OBJLoader;

	public void init() {
		GLFW.glfwInit();
	    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
	    GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 6);
	    GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
	    window.create();
	    
	    
	}

	public void run() throws FileNotFoundException {

		init();
		loop();
		
	}

	public void loop() throws FileNotFoundException {
		Loader loader = new Loader();

		StaticShader staticshader = new StaticShader();
		Renderer renderer = new Renderer(window,staticshader);
		float[] vertices = {
				-0.5f,0.5f,0,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,0.5f,0,

				-0.5f,0.5f,1,
				-0.5f,-0.5f,1,
				0.5f,-0.5f,1,
				0.5f,0.5f,1,

				0.5f,0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1,
				0.5f,0.5f,1,

				-0.5f,0.5f,0,
				-0.5f,-0.5f,0,
				-0.5f,-0.5f,1,
				-0.5f,0.5f,1,

				-0.5f,0.5f,1,
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,0.5f,1,

				-0.5f,-0.5f,1,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1

		};

		float[] textureCoords = {

				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0


		};

		int[] indices = {
				0,1,3,
				3,1,2,
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};

	    //RawModel model = loader.loadToVao(vertices,textureCoords,indices);
		//RawModel model = OBJFileLoader.loadOBJ("untitled1");

		//Modeltexture texture = new Modeltexture(loader.loadTexture("grass1.png"));
		//RawModel model = OBJLoader.loadObjModel("untitled4",loader);
		//TexturedModel texturedModel = new TexturedModel(model,texture);
		//Entity entity = new Entity(texturedModel,new Vector3f(0,0,-10),0,0,0,1);
		OBJl test = new OBJl();
		Camera camera = new Camera(window);
		while(!GLFW.glfwWindowShouldClose(window.getWindow())) {

			//entity.increasePosition(0,0,-0.002f);
			camera.move(window);
			//entity.increaseRotation(0,1,0);
			callbacks.processInput(window.getWindow());
			
			renderer.prepare();

			staticshader.start();
			glfwSwapInterval(1);
			staticshader.loadviewMatrix(camera);
			//renderer.render(entity,staticshader);
			staticshader.stop();
			GLFW.glfwSwapBuffers(window.getWindow());

			GLFW.glfwPollEvents();

			
		}
		staticshader.clean();
		loader.clean();
		window.destroy();
		
	}
}
