#version 140

in vec2 position;

out vec2 pass_position;

uniform mat4 transformationMatrix;
uniform mat4 cameraMatrix;

void main(void){

    pass_position = position;
	gl_Position = cameraMatrix * transformationMatrix * vec4(position, 0.0, 1.0);

}