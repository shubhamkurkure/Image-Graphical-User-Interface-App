package model;

import model.filters.Filter;
import model.generateshapes.GeneratePattern;
import model.image.Image;
import model.tranformations.Transform;

/**
 * <p>This class implements the ImageModel interface. It provides ways to initialize the image on
 * which the operations can be performed or a default constructor for generating computer generated
 * images.</p>
 */
public class ImageModelImpl implements ImageModel {

  protected Image image;

  /**
   * <p>Parameterized constructor to initialize an image on which the operations are meant to be
   * performed.</p>
   *
   * @param image the image on which the operations are needed to be performed.
   * @throws IllegalArgumentException if the given input is null
   */
  public ImageModelImpl(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("image cannot be null");
    }
    this.image = image;
  }

  /**
   * <p>Default constructor that will be used when images are needed to be generated.</p>
   */
  public ImageModelImpl() {
    // Used for initializing the ImageModel to generate images.
  }


  @Override
  public ImageModel applyFilter(Filter filter) {
    return new ImageModelImpl(filter.applyFilter(this.image));
  }


  @Override
  public ImageModel applyTransform(Transform transform) {
    return new ImageModelImpl(transform.applyTransformation(this.image));
  }

  @Override
  public ImageModel generateImage(GeneratePattern pattern) {
    return new ImageModelImpl(pattern.generate());
  }

  /**
   * <p>Returns the currently stored image of the image Model.</p>
   *
   * @return current image in the model class.
   * @throws IllegalStateException if the image is not initialized.
   */
  @Override
  public Image getModelImage() throws IllegalStateException {
    if (image == null) {
      throw new IllegalStateException("invalid image.");
    }
    return this.image;
  }
}
