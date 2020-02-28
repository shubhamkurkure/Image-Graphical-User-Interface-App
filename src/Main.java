import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.Controller;
import controller.ControllerImpl;
import controller.InteractiveController;
import model.EnhancedImageModel;
import model.EnhancedImageModelImpl;
import view.ImageView;
import view.InteractiveGUI;

/**
 * Marks the start of the program. Starts the program according to the command received.
 */
public class Main {

  /**
   * Starts the program in GUI or script based form as per the inputs received.
   *
   * @param args the program arguments
   * @throws IllegalArgumentException if the program is tried to execute using an unknown command
   * @throws FileNotFoundException    if the file passed as a script does not exist
   */
  public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
    EnhancedImageModel model = new EnhancedImageModelImpl();
    Controller controller;
    if (args.length == 1 && args[0].equals("-interactive")) {
      ImageView view = new InteractiveGUI();
      controller = new InteractiveController(view, model);
      controller.processInput();
    } else if (args.length == 2 && args[0].equals("-script")) {
      File script = new File(args[1]);
      if (script.exists()) {
        controller = new ControllerImpl(new FileReader(args[1]), model);
        controller.processInput();
      } else {
        throw new FileNotFoundException("Invalid input file passed.");
      }
    } else {
      throw new IllegalArgumentException("program does not support this functionality.");
    }
  }
}
