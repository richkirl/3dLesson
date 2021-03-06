package com.gamengine.render;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {
	private final List<Integer> vaos = new ArrayList<Integer>();
	private final List<Integer> vbos = new ArrayList<Integer>();
	private final List<Integer> textures = new ArrayList<Integer>();

	public RawModel loadToVao(float[] positions, float[] textureCoords, int[] indices) {
		int vaoId = createVao();
		bindIndicesBuffer(indices);
		storeDataAttribList(0,3,positions);
		storeDataAttribList(1,2,textureCoords);
		unbindVao();
		return new RawModel(vaoId,positions.length);
		
	}
	
	public int loadTexture(String fileName) {
		int textureID = GL11.glGenTextures();
		Image texture = Image.loadImage("C:\\JavaGameDev\\Lwjgl3-Game-Engine-Programming-Series-starting_code\\3dLesson\\res\\"+fileName);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL30.glGenerateMipmap(textureID);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, texture.getWidth(), texture.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, texture.getImage());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,0);

		textures.add(textureID);
		return textureID;
	}
	
	public void clean() {
		for(int i : vaos) {
			GL30.glDeleteVertexArrays(i);
		}
		for(int i : vbos) {
			GL15.glDeleteBuffers(i);
		}
		for(int i : textures) {
			GL11.glDeleteTextures(i);
		}
	}
	
	public int createVao() {
		int vaoId = GL30.glGenVertexArrays();
		vaos.add(vaoId);
		GL30.glBindVertexArray(vaoId);
		return vaoId;
	}
	private void storeDataAttribList(int attribNumber,int coordinateSize, float[] data) {
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attribNumber, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	private void unbindVao() {
		GL30.glBindVertexArray(0);
	}

	private void bindIndicesBuffer(int[] indices){
		int vboId = GL15.glGenBuffers();
		vbos.add(vboId);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,vboId);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
	}

	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	
}
