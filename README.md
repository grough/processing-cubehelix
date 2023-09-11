# Cubehelix Color Scheme for Processing

This library provides a version of the [cubehelix color scheme](https://www.mrao.cam.ac.uk/~dag/CUBEHELIX/) for the Processing creative coding environment.

![Cubehelix gradient with default settings](gradient-example.png)

## Get Started

1. Install [Processing](https://processing.org)
2. Go to this repository's [releases page](https://github.com/grough/processing-cubehelix/releases) and download the latest `Cubehelix.zip` file
3. Extract the zip file to the "libraries" sub-folder of your Processing documents folder
4. Launch Processing, go to _File → Examples_, and browse the Cubehelix examples

## Usage

```processing
// Import the library
import grough.cubehelix.*;

// Create an instance
Cubehelix h = new Cubehelix();

// Get some colors
color black = h.color(0);
color white = h.color(1);
color other = h.color(0.667);

// Customize color scheme parameters (defaults shown)
h.start(0.5).rotations(1.5).hue(1.0).gamma(1.0);

// Get an array of equally spaced colors
color[] palette = h.array(16);

// Get an image of a gradient
PImage gradientImage = h.image(200, 100);

// Get an image of a palette containing a given number of colors
PImage paletteImage = h.image(200, 100, 16);
```
