package model.image;

/**
 * <p>This class implements the {@link Image} interface. It stores the image as a 3D array, which
 * has 3 channels and each channel having rows and columns that specifies the pixel at that row and
 * column.</p>
 */
public class SimpleImage implements Image {

  private final int[][][] image;

  /**
   * <p>Takes a 3D array that has 3 channels and each channel having rows and columns that
   * specifies pixel value at each row and column.</p>
   *
   * @param image an 3D array representing an images
   * @throws IllegalArgumentException If the image does not have 3 channel or has row or column less
   *                                  than 1 value.
   */
  public SimpleImage(int[][][] image) throws IllegalArgumentException {
    if (image.length != 3 || image[0].length < 1 || image[0][0].length < 1) {
      throw new IllegalArgumentException("invalid image.");
    }
    this.image = image;
  }

  @Override
  public int getHeight() {
    return this.image[0][0].length;
  }

  @Override
  public int getWidth() {
    return this.image[0].length;
  }

  @Override
  public int getPixelValue(int channel, int column, int row) throws IllegalArgumentException {
    if (channel > 2 || channel < 0 || column < 0 || column > this.getWidth()
            || row < 0 || row > this.getHeight()) {
      throw new IllegalArgumentException("pixel not present at this location, invalid location.");
    }
    return this.image[channel][column][row];
  }
}
