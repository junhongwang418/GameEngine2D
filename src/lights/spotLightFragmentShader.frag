#version 140

in vec2 pass_position;
in vec2 fromPositionVector;
out vec4 out_Color;

uniform vec3 color;
uniform float attenuationType;
uniform float coneAngle;
uniform vec2 direction;


void main(void){

    float distance = length(pass_position);

    vec2 unitDirection = normalize(direction);
    vec2 unitFromPosition = normalize(fromPositionVector);
    float dDotf = dot(unitDirection, unitFromPosition);

    float visibility = 0;

    if (dDotf > cos(3.14*coneAngle/180/2)) {
        visibility = 0.5 * (1.0 - distance);
    }
    out_Color = vec4(color, visibility);





}