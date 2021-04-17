package shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{
    private static final  String VERTEX="src/shaders/vertexShader.glsl";
    private static final String FRAGMENT="src/shaders/fragmentShader.glsl";
    private int location_transformationMatrix;
    public StaticShader() {
        super(VERTEX, FRAGMENT);

    }
    @Override
    protected void bindAttrib(){

        super.bindAttrib(0,"position");
        super.bindAttrib(1,"textureCoords");
    }
    @Override
    protected void getAllUniformLocations(){
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    public void loadtransformationMatrix(Matrix4f matrix){
        super.loadmatrix(location_transformationMatrix,matrix);
    }
}
