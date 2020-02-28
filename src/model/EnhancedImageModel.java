package model;

import model.dither.Dither;
import model.image.Image;
import model.mosaic.Mosaic;

/**
 * This interface extends the ImageModel. It adds functionality to the existing ImageModel by
 * incorporating two new image processes.
 */
public interface EnhancedImageModel extends ImageModel {

  /**
   * <p>Generates an image that is dithered using any implementation of {@link
   * Dither}.</p>
   *
   * @param dither any implementation if {@link Dither}
   * @return an image model that has dithered image
   */
  EnhancedImageModel generateDithered(Dither dither);

  /**
   * <p>Generates an image that is an mosaic using any implementation of {@link
   * Mosaic}.</p>
   *
   * @param mosaic any implementation if {@link Mosaic}
   * @return an image model that has mosaic image
   */
  EnhancedImageModel generateMosaic(Mosaic mosaic);

  /**
   * Create new enhancedImageModel from the given image. This will be used as a factory method.
   */
  EnhancedImageModel fromImage(Image image);
}
