package model.filters;

/**
 * <p>This class extends the {@link AbstractFilter} class. It represents a class that can sharpen
 * an image using a sharpen filter kernel using kernel of specific type.</p>
 */
public class SharpenFilter extends AbstractFilter {

  /**
   * <p>Constructor to initialize the sharpen kernel that will sharpen the image.</p>
   */
  public SharpenFilter() {
    super(new double[5][5]);
    super.kernel[0] = new double[]{-0.125, -0.125, -0.125, -0.125, -0.125};
    super.kernel[1] = new double[]{-0.125, 0.25, 0.25, 0.25, -0.125};
    super.kernel[2] = new double[]{-0.125, 0.25, 1, 0.25, -0.125};
    super.kernel[3] = new double[]{-0.125, 0.25, 0.25, 0.25, -0.125};
    super.kernel[4] = new double[]{-0.125, -0.125, -0.125, -0.125, -0.125};
  }


}
