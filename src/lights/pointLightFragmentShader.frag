#version 140

in vec2 pass_position;
out vec4 out_Color;

uniform vec3 color;
uniform float attenuationType;

void main(void){

    float distance = length(pass_position);

    out_Color = vec4(color, 0.5 * (1.0 - distance));



}