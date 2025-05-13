import com.grough.cubehelix.*;

Cubehelix cubehelix;

void setup() {
  size(640, 320);
  
  cubehelix = new Cubehelix();
  
  // Create an image showing a smooth gradient
  PImage gradient = cubehelix.image(width, height/2);
  image(gradient, 0, 0);
  
  // Create an image showing an 8-color palette
  PImage palette = cubehelix.image(width, height/2, 8);
  image(palette, 0, height / 2);
}
