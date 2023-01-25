package grough.cubehelix;

import processing.core.*;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package
 * 'template' to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder
 * 'examples' will automatically include the example in the javadoc.)
 *
 * @example Hello
 */

public class Cubehelix {

	public final static String VERSION = "##library.prettyVersion##";

	float start = 0.5f;
	float rotations = 1.5f;
	float saturation = 1.0f;
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
	 * Set the saturation parameter, which controls how saturated the colors are.
	 * 
	 * @param value saturation
	 */
	public Cubehelix saturation(float value) {
		saturation = value;
		return this;
	}

	/**
	 * An alias of the `saturation` method. Included for compatibility with the
	 * original cubehelix scheme, which uses "hue" as a misnomer for saturation.
	 * 
	 * @param value "hue" (misnomer for saturation)
	 */
	public Cubehelix hue(float value) {
		return saturation(value);
	}

	/**
	 * Set the gamma value, used to emphasize low or high intensity values.
	 * 
	 * @param value the gamma
	 */
	public Cubehelix gamma(float value) {
		gamma = value;
		return this;
	}

	// Convert ARGB color components to an integer.
	private int makeColor(int a, int r, int g, int b) {
		a = a << 24;
		r = r << 16;
		g = g << 8;
		return a | r | g | b;
	}

	// Compute the color for a value between 0..1
	private int cubehelix(double x) {
		double angle = 2 * Math.PI * (start / 3.0 + 1 + rotations * x);
		x = Math.pow(x, gamma);
		double amp = saturation * x * (1 - x) / 2.0;
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
			a[i] = cubehelix(i / (float) size);
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
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
}
