package model.generateshapes;

import model.image.Image;
import model.image.SimpleImage;

/**
 * This class extends the {@link AbstractPatternGenerate} class. It creates a checkerboard with the
 * user specified square size.
 */
public class CheckerBoard extends AbstractPatternGenerate {

  private int squareSize;

  /**
   * Initializes the square size and the size of the returning checker board image.
   *
   * @param squareSize size of each square
   * @throws IllegalArgumentException if the squareSize is less than 1.
   */
  public CheckerBoard(int squareSize) throws IllegalArgumentException {
    super(squareSize * 8, squareSize * 8);
    if (squareSize < 1) {
      throw new IllegalArgumentException("square size should be greater than 1.");
    }
    this.squareSize = squareSize;
  }

  @Override
  public Image generate() {
    int startHeight = 0;
    int startWidth = 0;
    int trackColor = 1;
    for (int i = 1; i <= 64; i++) {
      int[] color;
      if (trackColor % 2 != 0) {
        color = new int[]{255, 255, 255};
      } else {
        color = new int[]{0, 0, 0};
      }
      int[][][] getPatch = createColoredPatch(this.squareSize, this.squareSize, color);
      applyPatchOnImage(startWidth, startWidth + this.squareSize, startHeight,
              startHeight + this.squareSize, getPatch);
      if (i % 8 == 0) {
        startHeight += this.squareSize;
        startWidth = 0;
      } else {
        startWidth += this.squareSize;
        trackColor++;
      }
    }
    return new SimpleImage(super.finalImage);
  }
}
