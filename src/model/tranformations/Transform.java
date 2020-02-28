package model.tranformations;

import model.image.Image;

/**
 * <p>Used to apply a specific type of transformation on an given image. Uses a Linear
 * transformation matrix to transform the image.</p>
 */
public interface Transform {

  /**
   * <p>Applies the transformation on the image and returns the transformed image.</p>
   *
   * @return a new transformed image
   * @throws IllegalArgumentException the image is null
   */
  Image applyTransformation(Image image) throws IllegalArgumentException;

}
