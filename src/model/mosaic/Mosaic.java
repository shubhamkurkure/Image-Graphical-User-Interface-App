package model.mosaic;

import model.image.Image;

/**
 * Represents a mosaic type of image processing. Converts the given image to a mosaic image.
 */
public interface Mosaic {

  /**
   * Given an image converts the given image to an mosaic image and returns it.
   *
   * @param image the image to be converted to mosaic
   * @return an mosaic image
   */
  Image mosaic(Image image);
}
