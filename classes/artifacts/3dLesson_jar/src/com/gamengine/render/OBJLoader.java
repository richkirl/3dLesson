package com.gamengine.render;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {
    public static void loadObjModel(String fileName, Loader loader){
        FileReader fr = null;
        try{
            fr = new FileReader(new File("res/"+fileName+".obj"));
        }catch(FileNotFoundException e){
            System.err.println("Couldnt load OBJ file!"+fileName);
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        float[] verticesArray = null;
        float[] normalArray = null;
        float[] textureArray = null;
        int[] indicesArray = null;
        try{
            while(true) {
                line = reader.readLine();
                String[] currentLine = line.split(" ");
                if (line.startsWith("v ")) {
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    vertices.add(vertex);
                }
                else if(line.startsWith("vt ")){
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                }
                else if(line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                }
                else if(line.startsWith("f ")){
                    textureArray = new float[vertices.size()*2];
                    normalArray = new float[vertices.size()*3];
                    break;
                }
            }
            while(line!=null){
                if(!line.startsWith("f ")){
                    line = reader.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return;
    }
    private static void processvertex(String[] vertexData,
                                      List<Integer> indices,
                                      List<Vector2f> textures,
                                      List<Vector2f> normals,
                                      float[] textureArray,
                                      float[] normalsArray){
        int currentVertexPointer = Integer.parseInt(vertexData[0])-1;
        indices.add(currentVertexPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1])-1);
        textureArray[currentVertexPointer*2] = currentTex.x;
        textureArray[currentVertexPointer*2+1] = 1 - currentTex.y;
        Vector2f currentNorm = normals.get(Integer.parseInt(vertexData[2])-1);
        normalsArray[currentVertexPointer*3] = currentNorm.x;
        normalsArray[currentVertexPointer*3+1] = currentNorm.y;
        //normalsArray[currentVertexPointer*3+2] = currentNorm.z;




    }

}
