uniform float Time;

void main() {
    vec2 uv = gl_FragCoord.xy / resolution;

    float angle = Time * 0.8;
    uv = vec2(
    cos(angle) * (uv.x - 0.5) + sin(angle) * (uv.y - 0.5) + 0.5,
    -sin(angle) * (uv.x - 0.5) + cos(angle) * (uv.y - 0.5) + 0.5
    );

    vec3 noise = texture(noise, uv * 5.0).rgb;
    float stars = pow(noise.r, 8.0) * smoothstep(0.7, 1.0, sin(Time*3.0));

    vec3 color = mix(
    vec3(0.3, 0.1, 0.6),
    vec3(0.1, 0.0, 0.3),
    fract(Time*0.5)
    );

    gl_FragColor = vec4(color * (stars + 0.2), 1.0);
}