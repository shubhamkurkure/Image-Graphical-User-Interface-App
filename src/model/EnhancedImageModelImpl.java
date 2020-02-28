package model;

import model.dither.Dither;
import model.image.Image;
import model.mosaic.Mosaic;

/**
 * This class extends the ImageModelImpl and implements the EnhancedImageModel. It represents an
 * implementation of the EnhancedImageModel that can perform the added functionality.
 */
public class EnhancedImageModelImpl extends ImageModelImpl implements EnhancedImageModel {

  /**
   * Takes an image and initializes the ImageModelImpl with this image.
   *
   * @param image the image which is required to be enhanced
   * @throws IllegalArgumentException if the image is null
   */
  public EnhancedImageModelImpl(Image image) throws IllegalArgumentException {
    super(image);
  }

  /**
   * Constructor when images are required to be generated and not enhanced.
   */
  public EnhancedImageModelImpl() {
    //When image are required to be generated.
  }

  @Override
  public EnhancedImageModel generateDithered(Dither dither) {
    return new EnhancedImageModelImpl(dither.dither(this.image));
  }

  @Override
  public EnhancedImageModel generateMosaic(Mosaic mosaic) {
    return new EnhancedImageModelImpl(mosaic.mosaic(this.image));
  }

  @Override
  public EnhancedImageModel fromImage(Image image) {
    return new EnhancedImageModelImpl(image);
  }
}
