import model.EnhancedImageModel;
import model.ImageModel;
import model.dither.Dither;
import model.filters.Filter;
import model.generateshapes.GeneratePattern;
import model.image.Image;
import model.image.SimpleImage;
import model.mosaic.Mosaic;
import model.tranformations.Transform;

/**
 * This class is a mock model for the controller.
 */
public class MockModel implements EnhancedImageModel {

  private StringBuilder log;

  /**
   * Constructor for the mock model.
   *
   * @param log the log to be maintained for the method calls.
   */
  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public EnhancedImageModel generateDithered(Dither dither) {
    System.out.println(log.toString());
    return new MockModel(log.append("dither"));
  }

  @Override
  public EnhancedImageModel generateMosaic(Mosaic mosaic) {
    return new MockModel(log.append("mosaic done "));
  }

  @Override
  public EnhancedImageModel fromImage(Image image) {
    return new MockModel(log);
  }

  @Override
  public ImageModel applyFilter(Filter filter) {
    return new MockModel(log.append("filter done "));
  }

  @Override
  public ImageModel applyTransform(Transform transform) {
    return new MockModel(log.append("transform "));
  }

  @Override
  public ImageModel generateImage(GeneratePattern pattern) {
    return new MockModel(log.append("pattern generated "));
  }

  @Override
  public Image getModelImage() {
    log.append("got the model ");
    return new SimpleImage(new int[3][1][1]);
  }
}
