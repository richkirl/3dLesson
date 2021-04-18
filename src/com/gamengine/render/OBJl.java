package com.gamengine.render;
import org.joml.Vector2f;
import org.joml.Vector3f;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class OBJl {
    int l = 0;
    boolean patch = false;
    int i = 0;
    List<Vector3f> v = new ArrayList<Vector3f>();
    List<Vector2f> vt = new ArrayList<Vector2f>();
    List<Vector3f> vn = new ArrayList<Vector3f>();
    List<Integer> ftd = new ArrayList<Integer>();
    float[] f = new float[4];
    String[] strv, strvt, strvn, strf;
    String line = "";
    public OBJl() {
        try (BufferedReader reader = new BufferedReader(new FileReader("res/untitled3.obj"))) {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("v ")) {
                    v.add(checksymbolvnv(3, "v ", line, strv));
                } else if (line.startsWith("vt ")) {
                    strvt = line.split(" ");
                    vt.add(new Vector2f(Float.parseFloat(strvt[1]), Float.parseFloat(strvt[2])));
                } else if (line.startsWith("vn ")) {
                    vn.add(checksymbolvnv(3, "vn ", line, strvn));
                } else if (line.startsWith("f ")) {
                    checkrepeat(line);
                    strf = line.split(" ");
                    if (i == 6) {
                        checksixSlash();//3
                    }
                    if (i == 8) {
                        chekeightSlash();//4
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getL() {
        return l;
    }
    public void checkrepeat(String line) {
        if (this.patch == false) {
            //int i = 0;
            Pattern p = Pattern.compile("/");
            Matcher m = p.matcher(line);
            while (m.find()) {
                i++;
            }
            this.patch = true;
            System.out.println(i);
        }
    }
    public Vector3f checksymbolvnv(int key, String sym, String line, String[] dest) {
        if (line.startsWith(sym)) {
            dest = line.split(" ");
            for (int i = 1; i <= key; i++) {
                f[i] = Float.parseFloat(dest[i]);
                if(i==key) {
                    return (new Vector3f(f[1], f[2], f[3]));
                }
            }
        }
        return null;
    }
    public void checksixSlash(){
        for(int i =1;i<=3;i++){
            strv = strf[i].split("/");
            for(int j=0;j<=2;j++){
                ftd.add(Integer.parseInt(strv[j]));
            }
        }
    }
    public void chekeightSlash(){
        for(int i =1;i<=4;i++){
            strv = strf[i].split("/");
            for(int j=0;j<=2;j++){
                ftd.add(Integer.parseInt(strv[j]));
            }
        }
    }
}
