package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;


import model.EnhancedImageModel;
import model.ImageFormats;
import model.dither.DitherImpl;
import model.filters.BlurFilter;
import model.filters.SharpenFilter;
import model.generateshapes.CheckerBoard;
import model.generateshapes.FranceFlag;
import model.generateshapes.GenerateHorizontalRainbow;
import model.generateshapes.GenerateVerticalRainbow;
import model.generateshapes.GreeceFlag;
import model.generateshapes.SwissFlag;
import model.image.Image;
import model.image.SimpleImage;
import model.mosaic.MosaicImpl;
import model.tranformations.GreyscaleTransform;
import model.tranformations.SepiaToneTransform;

/**
 * This class extends the Controller interface.Represents an abstract controller, containing the
 * common methods and instance variables that is useful to all the controllers.
 */
public abstract class AbstractController implements Controller {

  protected EnhancedImageModel model;
  protected Map<String, Function<Scanner, EnhancedImageModel>> knownCommands;

  /**
   * Abstract Controller constructor,which initializes the model and the commands that can be
   * performed by the program.
   *
   * @param model the model that will be used by the controller
   */
  public AbstractController(EnhancedImageModel model) {
    this.model = model;
    this.knownCommands = new HashMap<>();
    knownCommands.put("blur", s -> this.model.fromImage(this.model.applyFilter(
            new BlurFilter()).getModelImage()));
    knownCommands.put("sharpen", s -> this.model.fromImage(this.model.applyFilter(
            new SharpenFilter()).getModelImage()));
    knownCommands.put("sepia", s -> this.model.fromImage(this.model.applyTransform(
            new SepiaToneTransform()).getModelImage()));
    knownCommands.put("greyscale", s -> this.model.fromImage(this.model.applyTransform(
            new GreyscaleTransform()).getModelImage()));
    knownCommands.put("Generate,checkerboard", s -> this.model.fromImage(this.model.generateImage(
            new CheckerBoard(s.nextInt())).getModelImage()));
    knownCommands.put("Generate,greece,flag", s -> this.model.fromImage(this.model.generateImage(
            new GreeceFlag(s.nextInt())).getModelImage()));
    knownCommands.put("Generate,france,flag", s -> this.model.fromImage(this.model.generateImage(
            new FranceFlag(s.nextInt())).getModelImage()));
    knownCommands.put("Generate,swiss,flag", s -> this.model.fromImage(this.model.generateImage(
            new SwissFlag(s.nextInt())).getModelImage()));
    knownCommands.put("Generate,horizontal,rainbow", s -> this.model.fromImage(this.model
            .generateImage(new GenerateHorizontalRainbow(s.nextInt(), s.nextInt()))
            .getModelImage()));
    knownCommands.put("Generate,vertical,rainbow", s -> this.model.fromImage(this.model
            .generateImage(new GenerateVerticalRainbow(s.nextInt(), s.nextInt())).getModelImage()));
    knownCommands.put("mosaic", s -> this.model.generateMosaic(new MosaicImpl(s.nextInt())));
    knownCommands.put("dither", s -> this.model.generateDithered(new DitherImpl()));
  }

  /**
   * Returns an object of {@link Image} type, given an file that has the image path.
   *
   * @param image the image to be loaded
   * @return instance of Image
   */
  protected Image load(File image) {
    BufferedImage loadedImage;

    try {
      loadedImage = ImageIO.read(image);
      int y = loadedImage.getHeight();
      int x = loadedImage.getWidth();

      int[][][] loadImage = new int[3][x][y];

      for (int i = 0; i < x; i++) {
        for (int j = 0; j < y; j++) {
          int clr = loadedImage.getRGB(i, j);
          loadImage[0][i][j] = (clr >> 16) & 0xFF;
          loadImage[1][i][j] = (clr >> 8) & 0xFF;
          loadImage[2][i][j] = (clr) & 0xFF;
        }
      }

      return new SimpleImage(loadImage);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new SimpleImage(new int[3][1][1]);
  }


  /**
   * Given an object of image type, the path and format of the image, this method saves the image to
   * specified path.
   *
   * @param image       the image to be saved
   * @param imageFormat the format of the image
   * @param path        the path where the image is to be saved
   */
  protected void save(Image image, ImageFormats imageFormat, String path) {
    int x = image.getWidth();
    int y = image.getHeight();
    BufferedImage outputImage = new BufferedImage(x, y, BufferedImage.TYPE_3BYTE_BGR);

    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {
        int red = image.getPixelValue(0, i, j);
        int green = image.getPixelValue(1, i, j);
        int blue = image.getPixelValue(2, i, j);
        int rgb = ((0x0ff) << 24) | ((red & 0x0ff) << 16) | ((green & 0x0ff) << 8) | (blue & 0x0ff);
        outputImage.setRGB(i, j, rgb);
      }
    }

    File outputFile = new File(path);
    try {
      ImageIO.write(outputImage, imageFormat.toString(), outputFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Given the filename that needs to be stored, this methods extracts the image format and returns
   * an {@link ImageFormats} type from the filename.
   *
   * @param filename the given name of the file to be stored
   * @return an ImageFormats type
   * @throws IllegalArgumentException if the extracted format type does not match any known
   *                                  ImageFormats type
   */
  protected ImageFormats getImageFormat(String filename) throws IllegalArgumentException {
    return ImageFormats.valueOf(filename.substring(filename.indexOf(".") + 1));
  }
}
