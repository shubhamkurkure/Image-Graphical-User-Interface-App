package model.generateshapes;

import model.image.Image;
import model.image.SimpleImage;

/**
 * This class extends the {@link AbstractPatternGenerate} class. It creates a france flag of the
 * user specified size.
 */
public class FranceFlag extends AbstractPatternGenerate {

  private int length;
  private int height;

  /**
   * Constructor to initialize the flag size of france. Given the length of the flag, it adjusts the
   * height accordingly in 2:3 ratio.
   *
   * @param length the length of the flag.
   * @throws IllegalArgumentException if the length of the flag not in multiples of 3.
   */
  public FranceFlag(int length) throws IllegalArgumentException {
    super(length, length / 3 * 2);
    if (length % 3 != 0) {
      throw new IllegalArgumentException("length should be in multiples of 3.");
    }
    this.length = length;
    this.height = length / 3 * 2;
  }

  @Override
  public Image generate() {
    int[][] colors = new int[3][3];
    colors[0] = new int[]{0, 0, 255};
    colors[1] = new int[]{255, 255, 255};
    colors[2] = new int[]{255, 0, 0};
    int lengthOfEachStripe = this.length / 3;
    int startY = 0;

    for (int i = 0; i < 3; i++) {
      int[][][] patch = createColoredPatch(this.height, lengthOfEachStripe, colors[i]);
      applyPatchOnImage(startY, startY + lengthOfEachStripe, 0, this.height, patch);
      startY += lengthOfEachStripe;
    }
    return new SimpleImage(finalImage);
  }

}

