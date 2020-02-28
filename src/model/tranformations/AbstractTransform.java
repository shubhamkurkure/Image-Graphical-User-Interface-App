package model.tranformations;

import model.image.Image;
import model.image.SimpleImage;

/**
 * <p>Abstract Transform class that implements the {@link Transform} interface. Implements the
 * applyTransformation method for the subclasses based on the transformation matrix.</p>
 */
public abstract class AbstractTransform implements Transform {

  protected double[][] transformMatrix;

  /**
   * <p>Constructor to initialize the image on which the transformation is to be performed.</p>
   */
  protected AbstractTransform() {
    this.transformMatrix = new double[3][3];
  }

  @Override
  public Image applyTransformation(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("invalid image.");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] transformedImage = new int[3][width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        transformedImage[0][i][j] = transformAPixel(0, i, j, image);
        transformedImage[1][i][j] = transformAPixel(1, i, j, image);
        transformedImage[2][i][j] = transformAPixel(2, i, j, image);
      }
    }
    return new SimpleImage(transformedImage);
  }

  /**
   * Applies the linear transformation matrix to a pixel {@code clamp} it's value and returns the
   * transformed value of that pixel.
   *
   * @param channel     one of the channel in the image
   * @param pixelColumn the column of the pixel
   * @param pixelRow    the row of the location
   * @return the transformed value of the pixel
   */
  private int transformAPixel(int channel, int pixelColumn, int pixelRow, Image image) {
    double[] linearTransformValues = this.transformMatrix[channel];
    int red = image.getPixelValue(0, pixelColumn, pixelRow);
    int green = image.getPixelValue(1, pixelColumn, pixelRow);
    int blue = image.getPixelValue(2, pixelColumn, pixelRow);

    double retVal = linearTransformValues[0] * red + linearTransformValues[1] * green
            + linearTransformValues[2] * blue;
    return clamp(retVal);
  }

  /**
   * <p>Clamp value of a pixel if it is less than 0 or greater than 255 or it is not a integer.</p>
   *
   * @param value the value to be clamped
   * @return clamped value
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
