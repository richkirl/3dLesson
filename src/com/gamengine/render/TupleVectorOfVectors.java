package com.gamengine.render;

import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;

public class TupleVectorOfVectors {
    private int indexstring=0;
    private int indexrow=0;
    private int index1string=0;
    private int index1row=0;
    private Vector3i[] string3=new Vector3i[3];//3row
    private Vector3i[] string4=new Vector3i[4];//4row
    private Vector3i[] vvtvn=new Vector3i[3];//threecpmponent(v,vt,vn)
    List<Vector3i>[][] df = new ArrayList<Vector3i>().toArray(new List[0][0]);
    List<Vector3i>[][] df1 = new ArrayList<Vector3i>().toArray(new List[0][0]);
    public TupleVectorOfVectors(Vector3i onevec, Vector3i twovvec,Vector3i threevec){//3
                                        //x y z           //x y z           //x y z
        df[indexstring][indexrow].add(onevec);                                              //x = v
        indexrow++;                                                                         //y = vt
        df[indexstring][indexrow].add(twovvec);                                             //z = vn
        indexrow++;
        df[indexstring][indexrow].add(threevec);
        indexstring++;
    }

    public TupleVectorOfVectors(Vector3i onevec, Vector3i twovec, Vector3i threevec,Vector3i fourvec){//4
                                        //x y z          //x y z           //x y z           //x y z
        df1[index1string][index1row].add(onevec);
        index1row++;
        df1[index1string][index1row].add(twovec);
        index1row++;
        df1[index1string][index1row].add(threevec);
        index1row++;
        df1[index1string][index1row].add(fourvec);
        index1string++;
    }

    public void stdoutFOR3DEBUG(Vector3i i, Vector3i j, Vector3i k){
        System.out.printf("%d/%d/%d %d/%d/%d %d/%d/%d\n",i.x,i.y,i.z,j.x,j.y,j.z,k.x,k.y,k.z);
    }
    public void stdoutFOR4DEBUG(Vector3i i, Vector3i j, Vector3i k,Vector3i z){
        System.out.printf("%d/%d/%d %d/%d/%d %d/%d/%d %d/%d/%d\n",i.x,i.y,i.z,j.x,j.y,j.z,k.x,k.y,k.z,z.x,z.y,z.z);
    }



    public int getIndexstring() {
        return indexstring;
    }

    public int getIndexrow() {
        return indexrow;
    }

    public int getIndex1string() {
        return index1string;
    }

    public int getIndex1row() {
        return index1row;
    }

    public List<Vector3i>[][] getDf() {
        return df;
    }

    public List<Vector3i>[][] getDf1() {
        return df1;
    }
}


