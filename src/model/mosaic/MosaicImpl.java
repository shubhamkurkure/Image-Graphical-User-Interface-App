package model.mosaic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.image.Image;
import model.image.SimpleImage;

/**
 * This class implements the Mosaic interface. It implements the interface in order to convert an
 * image to a mosaic image.
 */
public class MosaicImpl implements Mosaic {

  private int seeds;
  private int[][][] mosaicImage;

  /**
   * Takes the no. of seeds using which the mosaic needs to be done and creates an MosaicImpl
   * object.
   *
   * @param seeds the seeds used for converting an image to mosaic
   * @throws IllegalArgumentException if the number of seeds less than 1
   */
  public MosaicImpl(int seeds) throws IllegalArgumentException {
    if (seeds < 1) {
      throw new IllegalArgumentException("invalid seed value");
    }
    this.seeds = seeds;
  }

  /**
   * Get the distance between a pixel and the seed.
   *
   * @param seedRow     the row of seed
   * @param seedColumn  the column of seed
   * @param pixelRow    the row of pixel
   * @param pixelColumn the column of pixel
   * @return the distance between these two points
   */
  private double getDistance(double seedRow, double seedColumn, double pixelRow,
                             double pixelColumn) {
    return Math.sqrt(Math.pow(seedRow - pixelRow, 2) + Math.pow(seedColumn - pixelColumn, 2));
  }

  /**
   * Gets the required number of random seed points from the image.
   *
   * @param rows the number of rows in the image
   * @param cols the number of cols in the image
   * @return the required number of seeds
   */
  private int[][] getRandomSeedPoints(int rows, int cols) {
    int[][] randomSeedPoints = new int[seeds][2];
    Random random = new Random();
    for (int i = 0; i < seeds; i++) {
      randomSeedPoints[i][0] = random.nextInt(rows);
      randomSeedPoints[i][1] = random.nextInt(cols);
    }
    return randomSeedPoints;
  }

  /**
   * Creates clusters of pixels by assigning the pixel to the closest seed from all the chosen
   * seeds.
   *
   * @param randomSeedPoints all the chosen seeds
   * @param image            the image which is to be made mosaic
   * @return clusters of pixels associated their seeds
   */
  private HashMap<Integer, ArrayList<int[]>> getClusters(int[][] randomSeedPoints, Image image) {
    HashMap<Integer, ArrayList<int[]>> clusters = new HashMap<>();
    for (int i = 0; i < seeds; i++) {
      clusters.put(i, new ArrayList<>());
    }
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        double min = Double.MAX_VALUE;
        int seedInd = 0;
        for (int k = 0; k < seeds; k++) {
          double dist = getDistance(randomSeedPoints[k][0], randomSeedPoints[k][1], i, j);
          if (dist < min) {
            min = dist;
            seedInd = k;
          }
        }
        ArrayList<int[]> points = clusters.get(seedInd);
        points.add(new int[]{i, j});
        clusters.put(seedInd, points);
      }
    }

    return clusters;
  }

  /**
   * Gets the cluster average of each cluster and assigns each point in the cluster this average.
   *
   * @param clusters the clusters having pixels with their associated seeds
   * @param image    the image to be made mosaic
   */
  private void setClusterAverage(HashMap<Integer, ArrayList<int[]>> clusters, Image image) {
    for (HashMap.Entry<Integer, ArrayList<int[]>> seed : clusters.entrySet()) {
      double red_average = 0;
      double green_average = 0;
      double blue_average = 0;
      for (int[] points : seed.getValue()) {
        red_average += image.getPixelValue(0, points[1], points[0]);
        green_average += image.getPixelValue(1, points[1], points[0]);
        blue_average += image.getPixelValue(2, points[1], points[0]);
      }
      red_average = red_average / seed.getValue().size();
      green_average = green_average / seed.getValue().size();
      blue_average = blue_average / seed.getValue().size();
      for (int[] points : seed.getValue()) {
        int row = points[0];
        int col = points[1];
        this.mosaicImage[0][col][row] = (int) red_average;
        this.mosaicImage[1][col][row] = (int) green_average;
        this.mosaicImage[2][col][row] = (int) blue_average;
      }
    }
  }

  @Override
  public Image mosaic(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("invalid image.");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    this.mosaicImage = new int[3][width][height];
    int[][] randomSeedPoints = getRandomSeedPoints(height, width);
    HashMap<Integer, ArrayList<int[]>> clusters = getClusters(randomSeedPoints, image);
    setClusterAverage(clusters, image);
    return new SimpleImage(this.mosaicImage);
  }
}
