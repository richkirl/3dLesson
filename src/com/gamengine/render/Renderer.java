package com.gamengine.render;

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
	public void prepare() {
		//GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
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
}
