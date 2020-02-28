package model.generateshapes;

import model.image.Image;
import model.image.SimpleImage;

/**
 * <p>This class extends the {@link AbstractPatternGenerate} class. Creates a Vertical rainbow of
 * the user given width of each stripe and height of the image.</p>
 */
public class GenerateVerticalRainbow extends AbstractPatternGenerate {

  private int widthOfEachStripe;
  private int heightOfImage;

  /**
   * <p>Constructor to initialize the width of each stripe and the height of the image.</p>
   *
   * @param widthOfEachStripe the width of each stripe.
   * @param heightOfImage     the height of the image
   * @throws IllegalArgumentException if the width or height is less than 1.
   */
  public GenerateVerticalRainbow(int widthOfEachStripe, int heightOfImage)
          throws IllegalArgumentException {
    super(widthOfEachStripe * 7, heightOfImage);
    this.widthOfEachStripe = widthOfEachStripe;
    this.heightOfImage = heightOfImage;
  }

  @Override
  public Image generate() {
    int[][] colors = new int[7][3];
    colors[0] = new int[]{148, 0, 211};
    colors[1] = new int[]{75, 0, 130};
    colors[2] = new int[]{0, 0, 255};
    colors[3] = new int[]{0, 255, 0};
    colors[4] = new int[]{255, 255, 0};
    colors[5] = new int[]{255, 140, 0};
    colors[6] = new int[]{255, 0, 0};
    int startWidth = 0;

    for (int i = 0; i < 7; i++) {
      int[][][] patch = createColoredPatch(this.heightOfImage, this.widthOfEachStripe, colors[i]);
      applyPatchOnImage(startWidth, startWidth + this.widthOfEachStripe,
              0, this.heightOfImage, patch);
      startWidth += this.widthOfEachStripe;
    }
    return new SimpleImage(finalImage);
  }

}

