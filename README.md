# Cubehelix Color Scheme for Processing

The **processing-cubehelix** library is an implementation of Dave Green's [cubehelix color scheme](https://www.mrao.cam.ac.uk/~dag/CUBEHELIX/) for the Processing creative coding environment.

## Get Started

1. Install [Processing](https://processing.org)
2. Go to this library's [releases page](https://github.com/grough/processing-cubehelix/releases) and download the latest `Cubehelix.zip` file
3. Extract the zip file to the "library" folder in your Processing documents folder
3. Launch Processing, go to *File → Examples*, and browse the Cubehelix examples

## Usage

```processing
// Import the library
import grough.cubehelix.*;

// Instantiate the class
Cubehelix h = new Cubehelix();

// Get a color in the range 0..1
color c1 = h.color(0);
color c2 = h.color(5/8.0);
color c3 = h.color(1);

// Change the parameters (defaults shown)
h.start(0.5).rotations(1.5).hue(1.0).gamma(1.0);

// Get an array of colors
color[] palette = h.array(8);

// Get an image of the gradient
PImage img = h.image(200, 100);
```
