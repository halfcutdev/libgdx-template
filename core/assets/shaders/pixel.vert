#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;
uniform mat4 u_projTrans;
uniform vec4 u_textureSizes;
uniform vec4 u_sampleProperties;
varying LOWP vec4 v_color;
varying vec2 v_texCoords;

void main() {
    v_color = a_color;
    v_color.a = v_color.a * (255.0/254.0);

    vec2 uvSize = u_textureSizes.xy;
    float upscale = u_textureSizes.z;

    v_texCoords.x = a_texCoord0.x + (u_sampleProperties.z / upscale) / uvSize.x;
    v_texCoords.y = a_texCoord0.y + (u_sampleProperties.w / upscale) / uvSize.y;

    gl_Position = u_projTrans * a_position;
}