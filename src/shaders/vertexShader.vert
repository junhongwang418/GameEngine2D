#version 400 core

in vec2 position;
in vec2 textureCoords;

out vec3 color;
out vec2 pass_textureCoords;

uniform mat4 transfrmationMatrix;

void main(void) {
    gl_Position = vec4(position, 0, 1);
    color = vec3(position.x+0.5, 1.0, position.y+0.5);
    pass_textureCoords = textureCoords;
}

