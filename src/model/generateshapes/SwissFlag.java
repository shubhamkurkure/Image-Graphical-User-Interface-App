package model.generateshapes;

import model.image.Image;
import model.image.SimpleImage;

/**
 * <p>This class extends the {@link AbstractPatternGenerate} class. Creates a Switzerland flag of
 * the user given size of 1:1 ratio.</p>
 */
public class SwissFlag extends AbstractPatternGenerate {

  private int heightOfFlag;

  /**
   * <p>Constructor to initialize the size of the flag.</p>
   *
   * @param heightOfFlag the height of the flag
   * @throws IllegalArgumentException if height are not in multiples of 5
   */
  public SwissFlag(int heightOfFlag) throws IllegalArgumentException {
    super(heightOfFlag, heightOfFlag);
    if (heightOfFlag % 5 != 0) {
      throw new IllegalArgumentException("invalid inputs, should be greater than 5 and "
              + "in 1:1 ratio.");
    }
    this.heightOfFlag = heightOfFlag;
  }

  @Override
  public Image generate() {
    int startHeight = 0;
    int startWidth = 0;
    int patchSize = this.heightOfFlag / 5;
    for (int i = 1; i <= 25; i++) {
      int[] color;
      if (i == 8 || i == 12 || i == 13 || i == 14 || i == 18) {
        color = new int[]{255, 255, 255};
      } else {
        color = new int[]{255, 0, 0};
      }
      int[][][] patch = createColoredPatch(patchSize, patchSize, color);
      applyPatchOnImage(startWidth, startWidth + patchSize, startHeight,
              startHeight + patchSize, patch);
      if (i % 5 == 0) {
        startHeight += patchSize;
        startWidth = 0;
      } else {
        startWidth += patchSize;
      }
    }
    return new SimpleImage(finalImage);
  }

}


