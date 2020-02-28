import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import controller.Controller;
import controller.InteractiveController;
import model.EnhancedImageModel;
import view.Features;
import view.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Controller for the GUI.
 */
public class InteractiveControllerTest {

  private StringBuilder modelLog;
  private EnhancedImageModel model;
  private StringBuilder viewLog;
  private ImageView view;
  private Features controller;

  @Before
  public void setup() {
    modelLog = new StringBuilder();
    model = new MockModel(modelLog);
    viewLog = new StringBuilder();
    view = new MockView(viewLog);
    controller = new InteractiveController(view, model);
  }

  @Test
  public void testControllerInitializesTheView() throws FileNotFoundException {
    Controller controllerInit = new InteractiveController(view, model);
    controllerInit.processInput();
    assertEquals("features callback set ", viewLog.toString());
  }

  @Test
  public void testControllerLoadImage() throws FileNotFoundException {
    controller.loadImage("this file");
    assertEquals("Show image called ", viewLog.toString());
  }

  @Test
  public void testControllerSaveImage() throws FileNotFoundException {
    controller.saveImage("res/beach.jpg");
    assertEquals("got the model ", modelLog.toString());
  }

  @Test
  public void testControllerGeneratePattern() throws FileNotFoundException {
    controller.generatePattern("checkerboard", new String[]{"8"});
    assertEquals("pattern generated got the model got the model ", modelLog.toString());
    assertEquals("Show image called ", viewLog.toString());
  }

  @Test
  public void testControllerImageOperation() throws FileNotFoundException {
    controller.performImageOperation("blur", new String[]{""});
    assertEquals("filter done got the model got the model ", modelLog.toString());
    assertEquals("Show image called ", viewLog.toString());
  }

}