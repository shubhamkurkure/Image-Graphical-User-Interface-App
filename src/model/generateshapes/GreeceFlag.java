package model.generateshapes;

import model.image.Image;
import model.image.SimpleImage;

/**
 * <p>This class extends the {@link AbstractPatternGenerate} class. Given the length of the flag,
 * it adjusts the height of the flag accordingly in 2:3 ratio.</p>
 */
public class GreeceFlag extends AbstractPatternGenerate {

  private int lengthOfFlag;
  private int heightOfFlag;

  /**
   * <p>Constructor to initialize the length and height of the flag.</p>
   *
   * @param lengthOfFlag the length of the flag
   * @throws IllegalArgumentException of the length of the flag are not multiples of 27.
   */
  public GreeceFlag(int lengthOfFlag) throws IllegalArgumentException {
    super(lengthOfFlag, lengthOfFlag * 2 / 3);
    if (lengthOfFlag % 27 != 0) {
      throw new IllegalArgumentException("length should be multiples of 27 and height "
              + "accordingly in 2:3 ratio");
    }
    this.lengthOfFlag = lengthOfFlag;
    this.heightOfFlag = lengthOfFlag * 2 / 3;
  }

  @Override
  public Image generate() {
    int[][] colors = new int[2][3];
    colors[0] = new int[]{0, 0, 255};
    colors[1] = new int[]{255, 255, 255};
    int heightOfEachStripe = heightOfFlag / 9;
    int startHeight = 0;

    for (int i = 0; i < 9; i++) {
      int[][][] patch;
      if (i == 0 || i == 4) {
        int startJ = 0;
        int endJ = this.lengthOfFlag / 27;
        for (int j = 0; j < 27; j++) {
          if (j == 4 || j == 5) {
            patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag / 27, colors[1]);
          } else {
            patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag / 27, colors[0]);
          }
          applyPatchOnImage(startJ, endJ, startHeight, startHeight + heightOfEachStripe,
                  patch);
          startJ += this.lengthOfFlag / 27;
          endJ += this.lengthOfFlag / 27;
        }
      } else if (i == 1 || i == 3) {
        int startJ = 0;
        int endJ = this.lengthOfFlag / 27;
        for (int j = 0; j < 27; j++) {
          if (j == 0 || j == 1 || j == 2 || j == 3 || j == 6 || j == 7 || j == 8 || j == 9) {
            patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag / 27, colors[0]);
          } else {
            patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag / 27, colors[1]);
          }
          applyPatchOnImage(startJ, endJ, startHeight, startHeight + heightOfEachStripe,
                  patch);
          startJ += this.lengthOfFlag / 27;
          endJ += this.lengthOfFlag / 27;
        }
      } else if (i == 2) {
        int startJ = 0;
        int endJ = this.lengthOfFlag / 27;
        for (int j = 0; j < 27; j++) {
          if (j < 10) {
            patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag / 27, colors[1]);
          } else {
            patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag / 27, colors[0]);
          }
          applyPatchOnImage(startJ, endJ, startHeight, startHeight + heightOfEachStripe,
                  patch);
          startJ += this.lengthOfFlag / 27;
          endJ += this.lengthOfFlag / 27;
        }
      } else if (i % 2 == 0) {
        patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag, colors[0]);
        applyPatchOnImage(0, this.lengthOfFlag, startHeight, startHeight + heightOfEachStripe,
                patch);
      } else {
        patch = createColoredPatch(heightOfEachStripe, this.lengthOfFlag, colors[1]);
        applyPatchOnImage(0, this.lengthOfFlag, startHeight, startHeight + heightOfEachStripe,
                patch);
      }
      startHeight += heightOfEachStripe;
    }
    return new SimpleImage(finalImage);
  }
}
