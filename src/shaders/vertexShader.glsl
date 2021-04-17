#version 460 core

in vec3 position;
in vec2 textureCoords;

out vec3 colour;
out vec2 pass_textureCoords;
uniform mat4 transformationMatrix;

void main() {
    gl_Position = transformationMatrix * vec4(position,1.0);
    pass_textureCoords = textureCoords;
    colour = vec3(position.x,1.0,position.y);
}
