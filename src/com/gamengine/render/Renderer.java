package com.gamengine.render;

import com.gamengine.Window;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import shaders.StaticShader;
import toolbox.Maths;

import static toolbox.Maths.createTransformationMatrix;

public class Renderer {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;

	private Matrix4f projectionMatrix;
	public Renderer(Window window ,StaticShader shader){
		createProjectionMatrix(window);
		shader.start();
		shader.loadprojectionMatrix(projectionMatrix);
		shader.stop();
	}
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		//GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
	}
	public void render(Entity entity, StaticShader shader) {
		TexturedModel model = entity.getModel();
		RawModel rawModel = model.getRawmodel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);

		Matrix4f transformationMatrix = createTransformationMatrix(entity.getPosition(),entity.getRotX(),entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadtransformationMatrix(transformationMatrix);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,model.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES,rawModel.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);

		GL30.glBindVertexArray(0);
	}

	private void createProjectionMatrix(Window window){

		float aspectRatio = (float) window.getWidth()/(float) window.getHeight();
		float y_scale = (float) ((1f/Math.tan(Math.toRadians(FOV/2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		projectionMatrix = new Matrix4f();
		projectionMatrix.m00(x_scale);
		projectionMatrix.m11(y_scale);
		projectionMatrix.m22(-((FAR_PLANE + NEAR_PLANE)/frustum_length));
		projectionMatrix.m23(-1);
		projectionMatrix.m32(-((2*NEAR_PLANE)/frustum_length));
		projectionMatrix.m33(1);
		//Matrix4f
	}
}
