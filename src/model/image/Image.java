package model.image;

/**
 * <p>Represents an stored image from which its attributes and pixel values can be fetched. Each
 * pixel has a position in the image (row, column) and a color which depends on the values in the
 * channels.</p>
 */
public interface Image {

  /**
   * <p>Returns the height of this image.</p>
   *
   * @return the height of this image
   */
  int getHeight();

  /**
   * <p> Returns the width/length of this image.</p>
   *
   * @return the width/length of this image
   */
  int getWidth();

  /**
   * <p>Returns the value stored at the specified channel, column and row in this image.</p>
   * <p>Channels:<ul>
   * <li>0 : Red channel.</li>
   * <li>1 : Green channel.</li>
   * <li>2 : Blue channel.</li>
   * </ul></p>
   *
   * @param channel a specific channel
   * @param column  a specific column in the image
   * @param row     a specific row in the image
   * @return pixel value at the specified channel, row and column
   * @throws IllegalArgumentException if the channel is not between [0, 2] or the column and row are
   *                                  value greater than the width and height of the image or less
   *                                  than 0
   */
  int getPixelValue(int channel, int column, int row) throws IllegalArgumentException;
}
