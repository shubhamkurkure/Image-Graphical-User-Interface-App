package model.generateshapes;

import model.image.Image;
import model.image.SimpleImage;

/**
 * <p>This class extends the {@link AbstractPatternGenerate} class. Creates a Horizontal rainbow of
 * the user given height of each stripe and length of the image.</p>
 */
public class GenerateHorizontalRainbow extends AbstractPatternGenerate {

  private int heightOfEachStripe;
  private int lengthOFImage;

  /**
   * <p>Constructor to initialize the height of each stripe and length of the image.</p>
   *
   * @param heightOfEachStripe the height of each stripe
   * @param lengthOFImage      the length of the image
   * @throws IllegalArgumentException if the height of each stripe is less than 1 or the length of
   *                                  the image is less than 1.
   */
  public GenerateHorizontalRainbow(int heightOfEachStripe, int lengthOFImage)
          throws IllegalArgumentException {
    super(lengthOFImage, heightOfEachStripe * 7);
    this.heightOfEachStripe = heightOfEachStripe;
    this.lengthOFImage = lengthOFImage;
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
    int startHeight = 0;

    for (int i = 0; i < 7; i++) {
      int[][][] patch = createColoredPatch(this.heightOfEachStripe, this.lengthOFImage, colors[i]);
      applyPatchOnImage(0, this.lengthOFImage, startHeight,
              startHeight + this.heightOfEachStripe, patch);
      startHeight += this.heightOfEachStripe;
    }
    return new SimpleImage(super.finalImage);
  }
}

