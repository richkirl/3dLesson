package models;

public class RawModel {
	private final int vaoID;
	private final int vertexCount;
	
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
