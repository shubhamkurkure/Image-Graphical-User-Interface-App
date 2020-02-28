package controller;

import java.io.FileNotFoundException;

/**
 * Represents a controller for processing the input and communicating with the model in order to
 * execute the commands present in the input.
 */
public interface Controller {

  /**
   * Processes the input taken by the controller and executes the commands present in the input by
   * calling appropriate command.
   *
   * @throws FileNotFoundException if the given model has an image and the image does not exist.
   */
  void processInput() throws FileNotFoundException;
}
