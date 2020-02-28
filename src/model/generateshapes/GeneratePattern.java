package model.generateshapes;

import model.image.Image;

/**
 * <p>Generates pattern of different types. Instead of loading an image this interface can be used
 * to develop images of various patterns.</p>
 */
public interface GeneratePattern {

  /**
   * Generates the desired pattern and returns the generated pattern as an image.
   *
   * @return the generated pattern image
   */
  Image generate();
}
