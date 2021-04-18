package entities;

import com.gamengine.Window;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position = new Vector3f(0,0,0);
    private float pith;
    private float yaw;
    private float roll;
    public Camera(Window window){}
    public void move(Window window){
       if(GLFW.glfwGetKey(window.getWindow(),GLFW.GLFW_KEY_W)==GLFW.GLFW_PRESS){
            position.z-=0.02f;
       }
        if(GLFW.glfwGetKey(window.getWindow(),GLFW.GLFW_KEY_D)==GLFW.GLFW_PRESS){
            position.x+=0.02f;
        }
        if(GLFW.glfwGetKey(window.getWindow(),GLFW.GLFW_KEY_A)==GLFW.GLFW_PRESS){
            position.x-=0.02f;
        }
        if(GLFW.glfwGetKey(window.getWindow(),GLFW.GLFW_KEY_S)==GLFW.GLFW_PRESS){
            position.z+=0.02f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPith() {
        return pith;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
