package model.filters;

import model.image.Image;
import model.image.SimpleImage;

/**
 * <p>This class implements the filter interface. Represents an abstract filter that applies a
 * filter of specified type using a kernel on an image.</p>
 */
public abstract class AbstractFilter implements Filter {

  protected double[][] kernel;

  /**
   * <p>Constructor that takes a kernel that represents the type of filtering to be applied.</p>
   */
  protected AbstractFilter(double[][] kernel) {
    this.kernel = kernel;
  }

  @Override
  public Image applyFilter(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("invalid image.");
    }
    int height = image.getHeight();
    int width = image.getWidth();
    int[][][] filteredImage = new int[3][width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        filteredImage[0][i][j] = applyKernelToPixel(0, i, j, image);
        filteredImage[1][i][j] = applyKernelToPixel(1, i, j, image);
        filteredImage[2][i][j] = applyKernelToPixel(2, i, j, image);
      }
    }

    return new SimpleImage(filteredImage);
  }

  /**
   * <p>Applies the kernel to a specific pixel, clamps the value and returns its value that is the
   * result of applying the kernel.</p>
   *
   * @param channel     the channel on which the kernel is to be applied.
   * @param pixelColumn the column where the pixel is located
   * @param pixelRow    the row where the pixel is located
   * @return the result of applying the kernel to the pixel
   */
  private int applyKernelToPixel(int channel, int pixelColumn, int pixelRow, Image image) {
    int kernelSize = this.kernel.length;
    int kernelCenter = kernelSize / 2;
    int height = image.getHeight();
    int width = image.getWidth();

    double pixelFilter = 0;
    for (int i = 0; i < kernelSize; i++) {
      for (int j = 0; j < kernelSize; j++) {
        int rowOffset = pixelRow - kernelCenter + i;
        int columnOffset = pixelColumn - kernelCenter + j;
        if (rowOffset < 0 || columnOffset < 0 || rowOffset >= height || columnOffset >= width) {
          pixelFilter += 0;
        } else {
          pixelFilter += image.getPixelValue(channel, columnOffset, rowOffset)
                  * kernel[i][j];
        }
      }
    }

    return clamp(pixelFilter);
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
