package view;

/**
 * Represents the features of the program. It encapsulates the high level functionality of the
 * program.
 */
public interface Features {

  /**
   * Loads the image present in the current path and takes other corresponding actions.
   *
   * @param path the path where the image is stored.
   */
  void loadImage(String path);

  /**
   * Save the image that is currently being worked on at the given path.
   *
   * @param path the path where the image needs to be stored
   */
  void saveImage(String path);

  /**
   * Performs the operation specified on the current image with the given inputs.
   *
   * @param operation the operation to be performed
   * @param inputs    the inputs using which the operation needs to be performed
   */
  void performImageOperation(String operation, String[] inputs);

  /**
   * Generates the pattern specified using the inputs given in the arguments.
   *
   * @param pattern the pattern to be generated
   * @param inputs  the inputs using which the pattern needs to be generated
   */
  void generatePattern(String pattern, String[] inputs);

  /**
   * Stops the executing program.
   */
  void exitProgram();

}
