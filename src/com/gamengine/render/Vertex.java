package com.gamengine.render;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {
    Vector3f position;
    Vector3f normal;
    Vector2f nexCoords;

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public Vector2f getNexCoords() {
        return nexCoords;
    }
}
