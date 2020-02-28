package view;


import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class extends the JFrame class and implements the {@link ImageView} interface. Represents
 * the GUI of the program. Creates the layout of the program.
 */
public class InteractiveGUI extends JFrame implements ImageView {

  private JLabel firstGenerateImageLabel;
  private JLabel secondGenerateImageLabel;
  private JLabel operationLabel;
  private JLabel imageViewLabel;

  private JButton loadB;
  private JButton saveB;
  private JButton generateB;
  private JButton operateB;
  private JButton exitB;

  private JTextField firstGenerateImageText;
  private JTextField secondGenerateImageText;
  private JTextField operationText;

  private JComboBox<String> patterns;
  private JComboBox<String> operations;


  /**
   * Constructor of the GUI. Creates the layout and sets the attributes of the view.
   */
  public InteractiveGUI() {
    super();
    setTitle("Image Processor");
    Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
    setMinimumSize(new Dimension(1200, 800));
    int taskBarSize = scnMax.bottom;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(screen.width, screen.height - taskBarSize);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1, 2));

    //Interactions
    JPanel interactionPanel = this.createInteractionsPanel();

    //Load image options
    JPanel loadingPanel = this.createLoadingPanel();
    JPanel loadUsingPath = this.createLoadPath();
    loadingPanel.add(loadUsingPath);
    JPanel loadUsingGenerate = this.createGeneratePattern();
    JPanel generateOptionInputs = this.createPatternInputs();
    loadUsingGenerate.add(generateOptionInputs);
    loadingPanel.add(loadUsingGenerate);

    //Operations pane
    JPanel operationPanel = this.createOperationPanel();
    JPanel operationSelect = this.selectOperationsList();
    JPanel operationInput = this.createOperationsInput();
    operationPanel.add(operationSelect);
    operationPanel.add(operationInput);

    //Save Image/Exit program
    JPanel saveExitPanel = this.createSaveExitPanel();

    //Add user interactions to interactions panel
    interactionPanel.add(loadingPanel);
    interactionPanel.add(operationPanel);
    interactionPanel.add(saveExitPanel);

    //Image panel
    JPanel imagePanel = this.createImagePanel();

    //adding everything to main panel
    mainPanel.add(interactionPanel);
    mainPanel.add(imagePanel);

    //show the view
    this.add(mainPanel);
    setVisible(true);
  }


  @Override
  public void setFeatures(Features handler) {
    loadB.addActionListener(l -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG, PNG, WEBP & GIF Images", "jpg", "gif", "png", "webp");
      fileChooser.setFileFilter(filter);
      fileChooser.setAcceptAllFileFilterUsed(false);
      int retValue = fileChooser.showOpenDialog(InteractiveGUI.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File image = fileChooser.getSelectedFile();
        handler.loadImage(image.getAbsolutePath());
      }
    });

    saveB.addActionListener(l -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      int retValue = fileChooser.showSaveDialog(InteractiveGUI.this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File image = fileChooser.getSelectedFile();
        handler.saveImage(image.getAbsolutePath());
      }
    });

    patterns.addActionListener(l -> getPatternInputFields(
            String.valueOf(patterns.getSelectedItem())));

    operations.addActionListener(l -> getOperationInputFields(
            String.valueOf(operations.getSelectedItem())));

    generateB.addActionListener(l -> handler.generatePattern(
            String.valueOf(patterns.getSelectedItem()),
            new String[]{this.firstGenerateImageText.getText(),
                    this.secondGenerateImageText.getText()}));

    operateB.addActionListener(l -> handler.performImageOperation(
            String.valueOf(operations.getSelectedItem()),
            new String[]{this.operationText.getText()}));

    exitB.addActionListener(l -> handler.exitProgram());
  }

  @Override
  public void showImage(Image image) {
    this.imageViewLabel.setText("");
    this.imageViewLabel.setIcon(new ImageIcon(image));
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(new JFrame(), message, "Exception",
            JOptionPane.ERROR_MESSAGE);
  }


  /**
   * Sets the input fields according to the pattern selected by the user.
   *
   * @param pattern one of the pattern that can be generated by the program
   */
  private void getPatternInputFields(String pattern) {
    this.hideAllInputs();
    switch (pattern) {
      case "vertical rainbow":
        this.showFirstGenerateTextField("width of Stripe");
        this.showSecondGenerateTextField("height of Image");
        break;
      case "checkerboard":
        this.showFirstGenerateTextField("square size");
        break;
      case "swiss flag":
      case "greece flag":
      case "france flag":
        this.showFirstGenerateTextField("length of flag");
        break;
      default:
        this.showFirstGenerateTextField("Height of Stripe");
        this.showSecondGenerateTextField("Length of Image");
        break;
    }
  }

  /**
   * Sets the operation input fields according to the operation selected by the user that is to be
   * performed on the image.
   *
   * @param operation an image operation
   */
  private void getOperationInputFields(String operation) {
    this.hideAllInputs();
    if ("mosaic".equals(operation)) {
      this.showOperationTextField("seeds");
    } else {
      this.showOperationButton();
    }
  }

  /**
   * Displays a text field and the label of the text field for the generate pattern option.
   *
   * @param label the label of the text field
   */
  private void showFirstGenerateTextField(String label) {
    this.firstGenerateImageLabel.setText(label);
    this.firstGenerateImageLabel.setVisible(true);
    this.firstGenerateImageText.setVisible(true);
    this.generateB.setVisible(true);
  }

  /**
   * Displays a text field and the label of the text field for the generate pattern option.
   *
   * @param label the label of the text field
   */
  private void showSecondGenerateTextField(String label) {
    this.secondGenerateImageLabel.setText(label);
    this.secondGenerateImageLabel.setVisible(true);
    this.secondGenerateImageText.setVisible(true);
    this.generateB.setVisible(true);
  }

  /**
   * Displays a text field and the label of the text field for the image operation pattern.
   *
   * @param label the label of the text field
   */
  private void showOperationTextField(String label) {
    this.operationLabel.setText(label);
    this.operationLabel.setVisible(true);
    this.operationText.setVisible(true);
    this.operateB.setVisible(true);
  }

  /**
   * Displays the operation button for the image operation to be performed.
   */
  private void showOperationButton() {
    this.operateB.setVisible(true);
  }

  /**
   * Hides all the input fields when the user has not yet selected any options.
   */
  private void hideAllInputs() {
    this.firstGenerateImageLabel.setVisible(false);
    this.firstGenerateImageText.setVisible(false);
    this.generateB.setVisible(false);
    this.secondGenerateImageLabel.setVisible(false);
    this.secondGenerateImageText.setVisible(false);
    this.operationLabel.setVisible(false);
    this.operationText.setVisible(false);
    this.operateB.setVisible(false);
  }

  /**
   * Creates a panel where the image is to be shown also sets a scrollable pane where image will be
   * displayed.
   *
   * @return an panel where the image will be shown along with the scrollable pane in it
   */
  private JPanel createImagePanel() {
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image."));
    imagePanel.setLayout(new BorderLayout());
    //Add the image scroll pane
    this.imageViewLabel = new JLabel("No image to display. Please load or generate an image.",
            SwingConstants.CENTER);
    this.imageViewLabel.setForeground(Color.lightGray);
    JScrollPane imageViewer = new JScrollPane(imageViewLabel);
    imageViewer.setBorder(new EmptyBorder(10, 10, 10, 10));
    imagePanel.add(imageViewer, BorderLayout.CENTER);
    return imagePanel;
  }

  /**
   * Create a panel where the image save and exit option will reside.
   *
   * @return a panel with save and exit option inside it.
   */
  private JPanel createSaveExitPanel() {
    JPanel saveExitPanel = new JPanel();
    saveExitPanel.setBorder(BorderFactory.createTitledBorder("Save/Exit options."));
    saveExitPanel.setLayout(new GridLayout(2, 1));
    JPanel savePanel = new JPanel();
    savePanel.setBorder(BorderFactory.createTitledBorder("Save current image."));
    savePanel.setLayout(new FlowLayout());
    JLabel save = new JLabel("save image");
    this.saveB = new JButton("save");
    saveB.setActionCommand("save image");
    JPanel exitPanel = new JPanel();
    exitPanel.setBorder(BorderFactory.createTitledBorder("Exit application."));
    exitPanel.setLayout(new FlowLayout());
    JLabel exit = new JLabel("Quit the program");
    this.exitB = new JButton("exit");
    exitB.setActionCommand("quit");
    savePanel.add(save);
    savePanel.add(saveB);
    exitPanel.add(exit);
    exitPanel.add(exitB);
    saveExitPanel.add(savePanel);
    saveExitPanel.add(exitPanel);
    return saveExitPanel;
  }

  /**
   * Create a panel where the user has the generate pattern text fields and button can reside.
   *
   * @return a panel where the user has the generate pattern text fields and button can reside
   */
  private JPanel createOperationsInput() {
    JPanel operationInput = new JPanel();
    operationInput.setLayout(new FlowLayout());
    this.operationLabel = new JLabel();
    this.operationLabel.setVisible(false);
    this.operationText = new JTextField(6);
    this.operationText.setVisible(false);
    this.operateB = new JButton("perform operation");
    operateB.setActionCommand("generate pattern");
    operateB.setVisible(false);
    operationInput.add(operationLabel);
    operationInput.add(operationText);
    operationInput.add(operateB);
    return operationInput;
  }

  /**
   * Creates a panel which has list of operations from which the user can select the relevant
   * option.
   *
   * @return a panel with list of operations from which the user can select the relevant option.
   */
  private JPanel selectOperationsList() {
    JPanel operationSelect = new JPanel();
    operationSelect.setLayout(new FlowLayout());
    JLabel imageOperations = new JLabel("Image operations");
    this.operations = new JComboBox<>();
    String[] commands = new String[]{"blur","sharpen", "sepia", "greyscale", "mosaic", "dither"};
    for (String command : commands) {
      operations.addItem(command);
    }
    operations.setActionCommand("image operation");
    operationSelect.add(imageOperations);
    operationSelect.add(operations);
    return operationSelect;
  }

  /**
   * Creates an operation panel where operations on current image will be performed.
   *
   * @return the panel where the operations that can be performed on the image will be defined.
   */
  private JPanel createOperationPanel() {
    JPanel operationPanel = new JPanel();
    operationPanel.setBorder(BorderFactory.createTitledBorder("Operations on current image."));
    operationPanel.setLayout(new GridLayout(2, 1));
    return operationPanel;
  }

  /**
   * Creates a panel where the text fields and image operate button will reside.
   *
   * @return a panel where the text fields and image operate button will reside.
   */
  private JPanel createPatternInputs() {
    JPanel generateOptionInputs = new JPanel();
    generateOptionInputs.setLayout(new FlowLayout());
    this.firstGenerateImageLabel = new JLabel();
    this.firstGenerateImageLabel.setVisible(false);
    this.firstGenerateImageText = new JTextField(6);
    this.firstGenerateImageText.setVisible(false);
    this.secondGenerateImageLabel = new JLabel();
    this.secondGenerateImageLabel.setVisible(false);
    this.secondGenerateImageText = new JTextField(6);
    this.secondGenerateImageText.setVisible(false);
    JLabel generateLabel = new JLabel("generate Image");
    this.generateB = new JButton("generate");
    generateB.setActionCommand("generate pattern");
    generateLabel.setVisible(false);
    generateB.setVisible(false);
    //Add generate elements
    generateOptionInputs.add(firstGenerateImageLabel);
    generateOptionInputs.add(firstGenerateImageText);
    generateOptionInputs.add(secondGenerateImageLabel);
    generateOptionInputs.add(secondGenerateImageText);
    generateOptionInputs.add(generateLabel);
    generateOptionInputs.add(generateB);
    return generateOptionInputs;
  }

  /**
   * Set the list of patterns that the program can generate.
   *
   * @return a panel with all the patterns that the generate.
   */
  private JPanel createGeneratePattern() {
    JPanel patternGenerate = new JPanel();
    patternGenerate.setBorder(BorderFactory.createTitledBorder("Generate Patterns."));
    patternGenerate.setLayout(new GridLayout(2, 1));
    JLabel generateImage = new JLabel("generate image");

    //Add pattern dropdown
    JPanel generateOption = new JPanel();
    generateOption.setLayout(new FlowLayout());
    this.patterns = new JComboBox<>();
    String[] patternImages = new String[]{"horizontal rainbow", "vertical rainbow", "checkerboard",
      "swiss flag", "france flag", "greece flag"};
    patterns.setActionCommand("image to generate");
    for (String patternImage : patternImages) {
      patterns.addItem(patternImage);
    }
    //add generate input elements
    generateOption.add(generateImage);
    generateOption.add(patterns);
    patternGenerate.add(generateOption);
    return patternGenerate;
  }

  /**
   * Create the load panel where user will have option to load image.
   *
   * @return the panel where the user has option to load the image
   */
  private JPanel createLoadPath() {
    JPanel loadFromPath = new JPanel();
    loadFromPath.setBorder(BorderFactory.createTitledBorder("Load from path."));
    loadFromPath.setLayout(new FlowLayout());
    JLabel loadImage = new JLabel("load image");
    this.loadB = new JButton("load");
    loadB.setActionCommand("load image");
    loadFromPath.add(loadImage);
    loadFromPath.add(loadB);
    return loadFromPath;
  }

  /**
   * Panel that has options to load or generate images.
   *
   * @return a panel that has option to load or generate image
   */
  private JPanel createLoadingPanel() {
    JPanel loadingPanel = new JPanel();
    loadingPanel.setBorder(BorderFactory.createTitledBorder("Set Current image."));
    loadingPanel.setLayout(new GridLayout(2, 1));
    return loadingPanel;
  }

  /**
   * Create the panel where the user has all the interactions possible with the program.
   *
   * @return a panel where all the possible interactions panel will reside
   */
  private JPanel createInteractionsPanel() {
    JPanel interactionPanel = new JPanel();
    interactionPanel.setBorder(BorderFactory.createTitledBorder("Interactions"));
    interactionPanel.setLayout(new GridLayout(3, 1));
    return interactionPanel;
  }
}
