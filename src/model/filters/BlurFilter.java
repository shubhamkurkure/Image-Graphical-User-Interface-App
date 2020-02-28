package model.filters;

/**
 * <p>This class extends the {@link AbstractFilter}. Given an image this class can be used to blur
 * an image using a specific type of kernel.</p>
 */
public class BlurFilter extends AbstractFilter {

  /**
   * <p>Initializes the blur filter kernel that will blur the image.</p>
   */
  public BlurFilter() {
    super(new double[3][3]);
    super.kernel[0] = new double[]{0.0625, 0.125, 0.0625};
    super.kernel[1] = new double[]{0.125, 0.25, 0.125};
    super.kernel[2] = new double[]{0.0625, 0.125, 0.0625};
  }

}
