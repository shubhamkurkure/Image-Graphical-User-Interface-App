package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import model.EnhancedImageModel;
import view.ImageView;
import view.Features;

/**
 * This class extends the AbstractController and implements the Features interface. It represents
 * the controller for the interactive GUI version of the program.
 */
public class InteractiveController extends AbstractController implements Features {

  private ImageView view;

  /**
   * Initializes the controller by taking the instance of {@link ImageView} and model {@link
   * EnhancedImageModel}.
   *
   * @param view  any implementation of the {@link ImageView}
   * @param model any implementation of the {@link EnhancedImageModel}
   */
  public InteractiveController(ImageView view, EnhancedImageModel model) {
    super(model);
    this.view = view;
  }

  @Override
  public void processInput() {
    view.setFeatures(this);
  }

  @Override
  public void loadImage(String path) {
    try {
      this.model = this.model.fromImage(this.load(new File(path)));
      view.showImage(convertToImage(this.model.getModelImage()));
    } catch (NullPointerException e) {
      view.showError("Could not load the specified image. '" + e.getMessage() + "'");
    } catch (OutOfMemoryError e) {
      view.showError("This image is out of this program scope. Image to large to render.");
    }
  }

  @Override
  public void saveImage(String path) {
    try {
      this.save(this.model.getModelImage(), this.getImageFormat(path), path);
    } catch (IllegalArgumentException e) {
      view.showError("File extension not supported. ");
    } catch (IllegalStateException e) {
      view.showError("Invalid image to save. No image to save.");
    }

  }

  @Override
  public void performImageOperation(String operation, String[] inputs) {
    Scanner command = new Scanner(String.join(" ", inputs));
    executeCommand(command, operation);
  }

  @Override
  public void generatePattern(String pattern, String[] inputs) {
    Scanner command = new Scanner(String.join(" ", inputs));
    String patternCommandFormat = "Generate," + pattern.replaceAll("\\s", ",");
    executeCommand(command, patternCommandFormat);
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  /**
   * Executes the command given in the commandFormat argument using the scanner object that contains
   * the input for that command.
   *
   * @param inputs        the inputs for the command
   * @param commandFormat the command to execute
   */
  private void executeCommand(Scanner inputs, String commandFormat) {
    Function<Scanner, EnhancedImageModel> cmdToExecute = this.knownCommands.getOrDefault(
            commandFormat, null);
    if (cmdToExecute == null) {
      view.showError("command not defined");
    } else {
      try {
        this.model = cmdToExecute.apply(inputs);
        view.showImage(convertToImage(this.model.getModelImage()));
      } catch (IllegalArgumentException e) {
        view.showError(e.getMessage());
      } catch (NoSuchElementException e) {
        view.showError("Please enter valid integer inputs.");
      } catch (OutOfMemoryError e) {
        view.showError("This image is out of this program scope. Image to large to render.");
      }
    }
  }

  /**
   * Converts the instance of {@link model.image.Image} to an {@link Image} instance.
   *
   * @param image an instance of {@link model.image.Image}
   * @return an instance of {@link Image}
   */
  private Image convertToImage(model.image.Image image) {
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

    return outputImage;
  }
}
