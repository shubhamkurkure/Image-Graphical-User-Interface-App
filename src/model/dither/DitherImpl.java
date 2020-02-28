package model.dither;

import model.image.Image;
import model.image.SimpleImage;
import model.tranformations.GreyscaleTransform;

/**
 * <p>This class extends {@link Dither}. Given an image this class can be used to dither
 * an image into a grey-scaled dithered image using Floyd–Steinberg dithering methodology.</p>
 */
public class DitherImpl implements Dither {

  private int[][][] ditheredImage;
  private GreyscaleTransform transform;

  /**
   * Constructor for DitherImpl. Initialises the transformation to grey-scale transform.
   */
  public DitherImpl() {
    this.transform = new GreyscaleTransform();
  }


  @Override
  public Image dither(Image image) {
    Image greyScaled = this.transform.applyTransformation(image);
    int height = greyScaled.getHeight();
    int width = greyScaled.getWidth();
    this.ditheredImage = new int[3][width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int channel = 0; channel < 3; channel++) {
          this.ditheredImage[channel][j][i] = greyScaled.getPixelValue(channel, j, i);
        }
      }
    }

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int old_color = this.ditheredImage[0][j][i];
        int new_color = getNewColor(old_color);
        int errorVal = old_color - new_color;
        for (int channel = 0; channel < 3; channel++) {
          this.ditheredImage[channel][j][i] = new_color;
          applyError(channel, i, j, errorVal);
        }
      }
    }
    return new SimpleImage(this.ditheredImage);
  }

  /**
   * Converts the pixel value into either 0 or 225 depending upon whether it is close to 0 or 225.
   *
   * @param oldColor the pixel value of the old color.
   * @return a pixel value of new color.
   */
  private int getNewColor(int oldColor) {
    if (oldColor > 127) {
      return 255;
    }
    return 0;
  }

  /**
   * Applies error to the pixel of a particular row and column based upon Floyd-Steinberg algorithm,
   * after which the pixel values are clamped accordingly.
   *
   * @param channel  one of the channel in the image.
   * @param row      the row of the image.
   * @param column   the column of the image.
   * @param errorVal the error value which is the difference of the old color and the new color.
   */
  private void applyError(int channel, int row, int column, int errorVal) {
    if (column + 1 < this.ditheredImage[0].length) {
      this.ditheredImage[channel][column + 1][row] = clamp((5.0 / 16.0) * errorVal
              + this.ditheredImage[channel][column + 1][row]);
    }
    if (column + 1 < this.ditheredImage[0].length && row - 1 >= 0) {
      this.ditheredImage[channel][column + 1][row - 1] = clamp((3.0 / 16.0) * errorVal
              + this.ditheredImage[channel][column + 1][row - 1]);
    }
    if (row + 1 < this.ditheredImage[0][0].length) {
      this.ditheredImage[channel][column][row + 1] = clamp((7.0 / 16.0) * errorVal
              + this.ditheredImage[channel][column][row + 1]);
    }
    if (column + 1 < this.ditheredImage[0].length && row + 1 < this.ditheredImage[0][0].length) {
      this.ditheredImage[channel][column + 1][row + 1] = clamp((1.0 / 16.0) * errorVal
              + this.ditheredImage[channel][column + 1][row + 1]);
    }

  }

  /**
   * <p>Clamps the value of the pixel if it is greater than 255 or less than 0 or not integer.</p>
   *
   * @param value the value to be clamped
   * @return the clamped value
   */
  private int clamp(double value) {
    if (value > 255) {
      return 255;
    } else if (value < 0) {
      return 0;
    }
    return (int) value;
  }
}
