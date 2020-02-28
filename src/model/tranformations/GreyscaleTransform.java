package model.tranformations;

/**
 * <p>This class extends the {@link AbstractTransform}.Converts the given image to a grey scale
 * image using a linear matrix transformation.</p>
 */
public class GreyscaleTransform extends AbstractTransform {

  /**
   * <p>Constructor of the Grey Scale transform. Initializes the linear matrix that converts the
   * given image to grey scale image.</p>
   */
  public GreyscaleTransform() {
    super();
    super.transformMatrix[0] = new double[]{0.2126, 0.7152, 0.0722};
    super.transformMatrix[1] = new double[]{0.2126, 0.7152, 0.0722};
    super.transformMatrix[2] = new double[]{0.2126, 0.7152, 0.0722};
  }


}
