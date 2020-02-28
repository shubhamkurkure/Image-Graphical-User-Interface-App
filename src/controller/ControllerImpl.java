package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

import model.EnhancedImageModel;

/**
 * This class implements the Controller interface. It takes the input from the user processes and
 * commands the model what in order to process a input. Also performs I/O  operations by loading and
 * saving the images.
 */
public class ControllerImpl extends AbstractController implements Controller {

  private Readable in;

  /**
   * Takes an Readable object and an EnhancedImageModel object to setup the controller in order to
   * process the inputs specified in the Readable object using the EnhancedImageModel.
   *
   * @param model an implementation of EnhancedImageModel
   * @param in    an readable object
   */
  public ControllerImpl(Readable in, EnhancedImageModel model) {
    super(model);
    this.in = in;
  }

  /**
   * Processes the input present in the readable object to execute the commands by calling
   * appropriate command.
   *
   * @throws IllegalArgumentException if the input has inputs that cannot be executed by the model
   *                                  or has unknown image format
   * @throws InputMismatchException   if the given inputs are of different type of size than the
   *                                  required arguments
   * @throws FileNotFoundException    if the file to be loaded cannot be found
   */
  @Override
  public void processInput() throws IllegalArgumentException, InputMismatchException,
          FileNotFoundException {
    Scanner scanner = new Scanner(this.in);
    while (scanner.hasNext()) {
      String input = scanner.next();
      switch (input) {
        case "q":
          return;
        case "load:":
          File imageFile = new File(scanner.next());
          if (imageFile.exists()) {
            this.model = this.model.fromImage(load(imageFile));
          } else {
            throw new FileNotFoundException("File does not exist");
          }
          break;
        case "save:":
          String path = scanner.next();
          this.save(this.model.getModelImage(), getImageFormat(path), path);
          break;
        default:
          Function<Scanner, EnhancedImageModel> getCommand = knownCommands.getOrDefault(
                  input, null);
          if (getCommand == null) {
            throw new IllegalArgumentException("command not defined.");
          } else {
            this.model = getCommand.apply(scanner);
          }
          break;
      }
    }

  }

}
