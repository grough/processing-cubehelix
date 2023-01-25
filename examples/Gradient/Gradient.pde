import grough.cubehelix.*;

Cubehelix cubehelix;

void setup() {
  size(640, 320);
  
  cubehelix = new Cubehelix();
  cubehelix.start(0.5).rotations(1.5).hue(1.0).gamma(1.0);
  
  for (int i = 0; i < width; i++) {
    float x = i / float(width);
    stroke(cubehelix.color(x));
    line(i, 0, i, height);
  }
}
