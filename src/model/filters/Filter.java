package model.filters;

import model.image.Image;

/**
 * <p>Represents a filter that can be applied on an image to get a new filtered image.</p>
 */
public interface Filter {

  /**
   * <p>Applies a filter to the image using the kernel and returns the filtered image.</p>
   *
   * @return a new instance of Filter
   * @throws IllegalArgumentException image is null
   */
  Image applyFilter(Image image) throws IllegalArgumentException;

}
