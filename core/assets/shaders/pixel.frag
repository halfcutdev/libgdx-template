#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform vec4 u_textureSizes;
uniform vec4 u_sampleProperties;

void main()
{
    vec2 uv = v_texCoords;
    vec2 uvSize = u_textureSizes.xy;

    float upscale = u_textureSizes.z;

    float dU = (1.0 / upscale) / uvSize.x;
    float dV = (1.0 / upscale) / uvSize.y;

    vec4 c0 = texture2D(u_texture, uv);
    vec4 c1 = texture2D(u_texture, uv + vec2(dU, 0));
    vec4 c2 = texture2D(u_texture, uv + vec2(0, dV));
    vec4 c3 = texture2D(u_texture, uv + vec2(dU, dV));

    float subU = u_sampleProperties.x;
    float subV = u_sampleProperties.y;

    float w0 = 1.0 - subU;
    float w1 = subU;
    float w2 = 1.0 - subV;
    float w3 = subV;

    vec4 bilinear = c0 * w0 * w2 + c1 * w1 * w2 + c2 * w0 * w3 + c3 * w1 * w3;

    gl_FragColor = bilinear;
}