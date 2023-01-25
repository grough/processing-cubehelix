import grough.cubehelix.*;

Cubehelix ch;

void setup() {
  size(640, 320);
  ch = new Cubehelix();
  ch.start(0.5).rotations(1.5).hue(1.0).gamma(1.0);
  for (int i = 0; i < width; i++) {
    float x = i / float(width);
    stroke(ch.read(x));
    line(i, 0, i, height);
  }
}
