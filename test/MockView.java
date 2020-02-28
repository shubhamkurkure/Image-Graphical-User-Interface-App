import java.awt.Image;

import view.Features;
import view.ImageView;

/**
 * This class is the mock view for the controller.
 */
public class MockView implements ImageView {

  private StringBuilder log;

  /**
   * Constructor for the mock view.
   *
   * @param log the log to be maintained for the calls
   */
  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void setFeatures(Features operations) {
    log.append("features callback set ");
  }

  @Override
  public void showImage(Image image) {
    log.append("Show image called ");
  }

  @Override
  public void showError(String message) {
    log.append("error msg called ");
  }
}
