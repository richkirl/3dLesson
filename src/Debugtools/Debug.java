package Debugtools;

import org.joml.Matrix4f;

public class Debug {
    //System.out.println(matrix);
    //System.out.println("=====================================================");
    private Matrix4f matrix;
    public Debug(Matrix4f matrix){
        this.matrix = matrix;
    }
    public void prvid(){
        System.out.println("=====================================================");
        System.out.println(matrix);
        System.out.println("=====================================================");
    }
}
