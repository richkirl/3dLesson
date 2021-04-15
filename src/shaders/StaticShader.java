package shaders;

public class StaticShader extends ShaderProgram{
    private static final  String VERTEX="src/shaders/vertexShader.glsl";
    private static final String FRAGMENT="src/shaders/fragmentShader.glsl";
    public StaticShader() {
        super(VERTEX, FRAGMENT);
    }
    @Override
    protected  void bindAttrib(){
        super.bindAttrib(0,"position");
    }
}
