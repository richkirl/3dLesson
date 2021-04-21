package com.gamengine.render;
import models.RawModel;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class OBJLoader {
    String line;
    String[] strv,strvt,strvn,strf,strf1;
    List<Vector3f> vertices = new ArrayList<Vector3f>();
    List<Vector2f> vtextur = new ArrayList<Vector2f>();
    List<Vector3f> vnormal = new ArrayList<Vector3f>();
    float[] vertpos,verttex,vertnorm = null;
    int[] indicesArray = null;
    float[] verticesArray = null;
    float[] normalsArray = null;
    float[] textureArray = null;
    //int[] indicesArray = null;
    List<Integer> indices = new ArrayList<Integer>();
    public RawModel loadobjmodel(String filename,Loader loader){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ")) {
                    strv = line.split(" ");
                    System.out.println(strv[1] + strv[2] + strv[3]);
                    vertices.add(new Vector3f(Float.parseFloat(strv[1]), Float.parseFloat(strv[2]), Float.parseFloat(strv[3])));
                } else if (line.startsWith("vt ")) {
                    strvt = line.split(" ");
                    System.out.println(strvt[1] + strvt[2]);
                    vtextur.add(new Vector2f(Float.parseFloat(strvt[1]), Float.parseFloat(strvt[2])));
                } else if (line.startsWith("vn ")) {
                    strvn = line.split(" ");
                    System.out.println(strvn[1] + strvn[2] + strvn[3]);
                    vnormal.add(new Vector3f(Float.parseFloat(strvn[1]), Float.parseFloat(strvn[2]), Float.parseFloat(strvn[3])));
                } else if (line.startsWith("f ")) {
                    textureArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    strf = line.split(" ");
                    for (int i = 1; i < strf.length; i++) {
                        strf1 = strf[i].split("/");
                        for (int j = 0; j != 3; j++) {
                            if (j == 2) {
                                int currentVertexPointer = Integer.parseInt(strf1[0]) - 1;
                                indices.add(currentVertexPointer);
                                Vector2f currentTex = vtextur.get(Integer.parseInt(strf1[1]) - 1);
                                textureArray[currentVertexPointer] = currentTex.x;
                                textureArray[currentVertexPointer + 1] = 1 - currentTex.y;
                                Vector3f currentNorm = vnormal.get(Integer.parseInt(strf1[2]) - 1);
                                normalsArray[currentVertexPointer] = currentNorm.x;
                                normalsArray[currentVertexPointer + 1] = currentNorm.y;
                                normalsArray[currentVertexPointer + 2] = currentNorm.z;
                                verticesArray = new float[vertices.size() * 3];
                                indicesArray = new int[indices.size()];
                            }
                        }
                    }
                }
            }
            reader.close();
            }
        catch (IOException e) {
            e.printStackTrace();
        }
        int vertexPointer = 0;
        for (Vector3f vertex : vertices) {
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }
        for (int i = 0; i < indices.size(); i++) {
            indicesArray[i] = indices.get(i);
        }
        for (int i = 0; i < indices.size(); i++) {
            indicesArray[i] = indices.get(i);
        }
        return loader.loadToVao(verticesArray, textureArray, indicesArray);
    }
}
