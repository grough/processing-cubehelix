import grough.cubehelix.*;

Cubehelix cubehelix;
int numberOfColors = 16;

void setup() {
  size(640, 320);
  noStroke();
  
  cubehelix = new Cubehelix();
  color[] palette = cubehelix.array(numberOfColors);
  
  for (int i = 0; i < numberOfColors; i++) {
    fill(palette[i]);
    int x = i * width / numberOfColors;
    rect(x, 0, x + width / numberOfColors, height);
  }
}
