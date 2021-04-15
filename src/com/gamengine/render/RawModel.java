package com.gamengine.render;

public class RawModel {
	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoid, int vertexcount) {
		this.vaoID = vaoid;
		this.vertexCount = vertexcount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
	
	
	
}
