package model.generateshapes;

/**
 * <p>This class implements the {@link GeneratePattern} interface. It has methods that can be used
 * by the subclasses to generate patterns of various kinds.</p>
 */
abstract class AbstractPatternGenerate implements GeneratePattern {

  protected int[][][] finalImage;

  /**
   * <p>Constructor to initialize the dimensions of the image to be generated.</p>
   *
   * @param width  the width of the image
   * @param height the height of the image
   * @throws IllegalArgumentException if the width or height is less than 1.
   */
  protected AbstractPatternGenerate(int width, int height) throws IllegalArgumentException {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Height and width should be greater than 1.");
    }
    this.finalImage = new int[3][width][height];
  }

  /**
   * <p>Creates a colored image patch of the given height,width and color. </p>
   *
   * @param height   the height of the patch
   * @param width    the width of the patch
   * @param rgbColor the color of the patch, where the 0 index represents red, 1st index green and
   *                 2nd index blue.
   * @return the image patch with the given color height and width
   * @throws IllegalArgumentException if the height or width of patch is less than 1 or length of
   *                                  rgbColor is not 0.
   */
  protected int[][][] createColoredPatch(int height, int width, int[] rgbColor)
          throws IllegalArgumentException {

    if (height < 1 || width < 1 || rgbColor.length != 3) {
      throw new IllegalArgumentException("Invalid height and width, must be greater than 0 and "
              + "should provide three colors.");
    }

    int[][][] patch = new int[3][width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        patch[0][i][j] = rgbColor[0];
        patch[1][i][j] = rgbColor[1];
        patch[2][i][j] = rgbColor[2];
      }
    }

    return patch;
  }

  /**
   * Applies the {@code patch} on the image that is to be generated at the specified index.
   *
   * @param startRow    the starting row index of the image where the patch is to applied.
   * @param endRow      the ending row index of the image where the patch is to applied.
   * @param startColumn the starting column index of the image where the patch is to applied.
   * @param endColumn   the starting column index of the image where the patch is to applied.
   * @param patch       the {@code patch} to be applied
   * @throws IllegalArgumentException if startRow or endRow is less than 0 or if endRow or endColumn
   *                                  is less than start and end and patch is not in image format.
   */
  protected void applyPatchOnImage(int startRow, int endRow, int startColumn, int endColumn,
                                   int[][][] patch)
          throws IllegalArgumentException {

    if (startRow < 0 || endRow < startRow || startColumn < 0 || endColumn < startColumn
            || patch.length != 3) {
      throw new IllegalArgumentException("invalid inputs");
    }

    int patchY = 0;
    for (int j = startRow; j < endRow; j++) {
      int patchX = 0;
      for (int k = startColumn; k < endColumn; k++) {
        this.finalImage[0][j][k] = patch[0][patchY][patchX];
        this.finalImage[1][j][k] = patch[1][patchY][patchX];
        this.finalImage[2][j][k] = patch[2][patchY][patchX];
        patchX++;
      }
      patchY++;
    }
  }

}
