package grough.cubehelix;

import processing.core.*;


public class Cubehelix {

	public final static String VERSION = "##library.prettyVersion##";

	float start = 0.5f;
	float rotations = 1.5f;
	float hue = 1.0f; // "hue" is a misnomer of "saturation" maintained from the original
						// implementation
	float gamma = 1.0f;

	/**
	 * Set the start color.
	 * 
	 * @param value the start value
	 */
	public Cubehelix start(float value) {
		start = value;
		return this;
	}

	/**
	 * Set the number of R->G->B rotations that are made from the start to the end
	 * of the color scheme.
	 * 
	 * @param value number of rotations
	 */
	public Cubehelix rotations(float value) {
		rotations = value;
		return this;
	}

	/**
	 * An alias of the `saturation` method. Included for compatibility with the
	 * original cubehelix scheme, which uses "hue" as a misnomer for saturation.
	 * 
	 * @param value "hue" (misnomer for saturation)
	 */
	public Cubehelix hue(float value) {
		hue = value;
		return this;
	}

	/**
	 * Set the gamma value, used to emphasize low or high intensity values.
	 * 
	 * @param value gamma
	 */
	public Cubehelix gamma(float value) {
		gamma = value;
		return this;
	}

	/**
	 * Convert ARGB color components to an integer.
	 * 
	 * @param alpha alpha value
	 * @param red   red
	 * @param green Green
	 * @param blue  Blue
	 */
	private int makeColor(int alpha, int red, int green, int blue) {
		alpha = alpha << 24;
		red = red << 16;
		green = green << 8;
		return alpha | red | green | blue;
	}

	// Compute the color for a value between 0..1
	private int cubehelix(double x) {
		double angle = 2 * Math.PI * (start / 3.0 + 1 + rotations * x);
		x = Math.pow(x, gamma);
		double amp = hue * x * (1 - x) / 2.0;
		double r = x + amp * (-0.14861 * Math.cos(angle) + 1.78277 * Math.sin(angle));
		double g = x + amp * (-0.29227 * Math.cos(angle) - 0.90649 * Math.sin(angle));
		double b = x + amp * (1.97294 * Math.cos(angle));
		return makeColor(255, (int) (255 * Math.max(Math.min(r, 1.0), 0.0)),
				(int) (255 * Math.max(Math.min(g, 1.0), 0.0)), (int) (255 * Math.max(Math.min(b, 1.0), 0.0)));
	}

	/**
	 * Return a color for a given input in the range 0..1
	 * 
	 * @param x domain?
	 */
	public int color(float x) {
		if (x < 0) {
			x = 0;
		}
		if (x > 1) {
			x = 1;
		}
		return cubehelix(x);
	}

	/**
	 * Get an array of equally spaced color values.
	 * 
	 * @param size the size of the array
	 */
	public int[] array(int size) {
		// TODO Include full range
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = cubehelix(i / (float) (size - 1));
		}
		return a;
	}

	/**
	 * Get an image of the gradient
	 * 
	 * @param width  image width
	 * @param height image height
	 */
	public PImage image(int width, int height) {
		PImage image = new PImage(width, height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				image.set(x, y, cubehelix(x / (float) width));
			}
		}
		return image;
	}

	/**
	 * Get an image of a palette
	 * 
	 * @param width       image width
	 * @param height      image height
	 * @param paletteSize number of colors in the image
	 */
	public PImage image(int width, int height, int paletteSize) {
		PImage image = new PImage(width, height);
		int[] palette = array(paletteSize);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int i = (int) (paletteSize * x / (float) width);
				image.set(x, y, palette[i]);

			}
		}
		return image;
	}

	/**
	 * Get an image of a palette
	 */
	public String toString() {
		return " start: " + start + " rotations: " + rotations + " hue: " + hue + " gamma: " + gamma;
	}

	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
}
