package model.tranformations;

/**
 * <p>This class extends the {@link AbstractTransform}.Converts the given image to a sepia toned
 * image using a linear matrix transformation.</p>
 */
public class SepiaToneTransform extends AbstractTransform {

  /**
   * <p>Constructor of the Sepia toned transform. Initializes the linear matrix that converts the
   * given image to sepia toned image.</p>
   */
  public SepiaToneTransform() {
    super();
    super.transformMatrix[0] = new double[]{0.393, 0.769, 0.189};
    super.transformMatrix[1] = new double[]{0.349, 0.686, 0.168};
    super.transformMatrix[2] = new double[]{0.272, 0.534, 0.131};
  }

}
