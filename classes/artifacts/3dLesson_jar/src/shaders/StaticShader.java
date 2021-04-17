package shaders;

import entities.Camera;
import org.joml.Matrix4f;
import toolbox.Maths;

public class StaticShader extends ShaderProgram{
    private static final  String VERTEX="src/shaders/vertexShader.glsl";
    private static final String FRAGMENT="src/shaders/fragmentShader.glsl";
    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
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
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    public void loadtransformationMatrix(Matrix4f matrix){
        super.loadmatrix(location_transformationMatrix,matrix);
    }
    public void loadprojectionMatrix(Matrix4f projection){
        super.loadmatrix(location_projectionMatrix,projection);
    }
    public void loadviewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadmatrix(location_viewMatrix,viewMatrix);
    }
}
