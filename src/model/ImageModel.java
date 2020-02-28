package model;

import model.filters.Filter;
import model.generateshapes.GeneratePattern;
import model.image.Image;
import model.tranformations.Transform;

/**
 * <p>This interface talks with the controller and performs the functions accordingly. It works on
 * an image to apply filters and transformations to it and also generates various kind of
 * images.</p>
 */
public interface ImageModel {

  /**
   * <p>Applies any kind of {@link Filter} and returns a new ImageModel with the filter applied
   * on the image.</p>
   *
   * @param filter an implementation of {@link Filter}
   * @return new Image Model with the filter applied
   */
  ImageModel applyFilter(Filter filter);

  /**
   * <p>Applies any kind of {@link Transform} and returns a new ImageModel with the transformation
   * applied on the image.</p>
   *
   * @param transform an implementation of {@link Transform}
   * @return a new Image Model with the transformation applied
   */
  ImageModel applyTransform(Transform transform);

  /**
   * <p>Generates an image of a specified type using any implementation of {@link
   * GeneratePattern}.</p>
   *
   * @param pattern any implementation of {@link GeneratePattern}
   * @return an image model with the desired pattern
   */
  ImageModel generateImage(GeneratePattern pattern);

  /**
   * <p>Returns the currently stored image of the Image Model.</p>
   *
   * @return current image in the model class.
   */
  Image getModelImage();
}
