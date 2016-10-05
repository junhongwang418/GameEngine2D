#version 140

in vec2 position;

out vec2 pass_position;
out vec2 fromPositionVector;

uniform mat4 transformationMatrix;
uniform mat4 cameraMatrix;

uniform vec2 spotLightPosition;

void main(void){

    pass_position = position;
    vec4 worldPosition = transformationMatrix * vec4(position, 0.0, 1.0);
    fromPositionVector = worldPosition.xy - spotLightPosition;
	gl_Position = cameraMatrix * worldPosition;

}