# An Image Processing Application
---


This program can be used to load images (of JPG, BMP, PNG, WEBP and GIF format), perform operations on them, generate patterns and then save the modified and generated images. It stores images as a 3D array with 3 channels representing the RGB values. Each channel has a row and column where a (row, column) represents a specific color value. The following functionality has been incorporated in this model: 
##### Filters:
  - Apply blur filter to the image.
  - Apply sharpen filter to the image.
  
##### Transformations:
  - Apply greyscale transformation to the image.
  - Apply sepia transformation to the image.

##### Generation of Patterns:
  - Generate a checkerboard.
  - Generate a horizontal rainbow. 
  - Generate a vertical rainbow.
  - Generate the flag of France.
  - Generate the flag of Greece.
  - Generate the flag of Switzerland.

##### Dither:
  - Converts an image into a grey-scaled dithered image using Floyd–Steinberg dithering algorithm.
  
##### Mosaic:
  - Converts an image into a mosiaic image using clustering methodology.


## Using the Image Processing Model directly
---


#### Initialization

  - If operations are required on a particular image, then it needs to be loaded using the *load* method in the driver. Now, the *ImageModel* needs to be initlialized with this loaded image.
  - If a pattern is required to be genrerated, then a default constructor of the ImageModel should be used.
 
```sh
Image imageOps = load(new File("res/nature.jpg")); // load the image
ImageModel model = new ImageModelImpl(imageOps); //initialize model with image
ImageModel model2 = new ImageModelImpl(); //Default model constructor
```
#### Applying Operations On The Image

There are two types of operations that can be performed on an image:

- **Filter**


| Type of Filter | Description |
| ------ | ------ |
| Blur | This applies the blur filter to every channel of every pixel and produces the output image. Thus blurring the resulting image. |
| Sharpen | This applies the sharpen filter to every channel of every pixel and produces the output image. Thus sharpening the resulting image.|

```sh
ImageModel blur = model.applyFiler(new BlurFilter()); // to blur the image
ImageModel sharpen = model.applyFiler(new SharpenFilter()); //sharpen the image
```

- **Transform**


| Type of Transformation | Description |
| ------ | ------ |
| Greyscale | This applies the greyscale transformation to every channel of every pixel and produces the output image. Thus grey-scaling the resulting image. |
| Sepia tone | This applies the sepia tone filter to every channel of every pixel and produces the output image. Thus sepia-toning the resulting image.|
 
```sh
ImageModel grey = model.applyTransform(new GreyscaleTransform()); // greyscale
ImageModel sepia = model.applyTransform(new SepiaToneTransform()); //sepia 
```

- **Generating Images**

There are six types of images that can be generated:

| Type | Description |
| ------ | ------ |
| Checkerboard | Generates a checkerboard with the user specified square size. |
| Horizontal Rainbow | Generates a Horizontal rainbow of the user given height of each stripe and length of the image.|
| Vertical Rainbow | Generates a Vertical rainbow of the user given width of each stripe and height of the image. |
| France Flag | Generates a France flag of the user specified size. |
| Greece Flag | Generates a Greece flag of the user specified size. |
| Swiss Flag | Generates a Swiss flag of the user specified size. |

```sh
ImageModel horRainbow = genModel.generateImage(
            new GenerateHorizontalRainbow(49, 343));//horizontal rainbow
ImageModel verRainbow = genModel.generateImage(
            new GenerateVerticalRainbow(49, 343));//vertical rainbow
ImageModel checkerBoardImage = genModel.generateImage(new CheckerBoard(64));//checkerboard
ImageModel franceFlag = genModel.generateImage(new FranceFlag(1215));//france flag
ImageModel swissFlag = genModel.generateImage(new SwissFlag(1215));//swiss flag
ImageModel greeceFlag = genModel.generateImage(new GreeceFlag(1215));//greece flag
```

- **EnhancedImageModel**

In addition to the functionalities offered by the *ImageModel* the Enhanced ImageModel also offers dithering of an image and converting an image into mosaic.

- **Initialising the EnhancedImageModel**

```sh
    EnhancedImageModel model = new EnhancedImageModelImpl(image); // to initialise the model with image.
    EnhancedImageModel model = new EnhancedImageModelImpl(); // to initialise the model for generating images.
```

- **Dithering**

Given an image, this can be used to dither an image into a grey-scaled dithered image using Floyd–Steinberg dithering methodology.
```sh
    EnhancedImageModel imageModel = model.generateDithered(new DitherImpl()); // to dither the image
```

- **Mosaic**

Given an image, this can be used to convert an image into a mosaic image. 
```sh
    EnhancedImageModel imageModel = model.generateMosaic(new MosaicImpl(3)); // to convert into mosaic
```

## List of commands
---

| Command | Description | Example |
| ------ | ------ | ------ |
| load: <imagePath> | This command loads the image present at the specified path. | load: beach.jpg |
| save: <imagePath> | This command saves the image at the specified path. | save: savedBeach.jpg |
| blur | This command blurs the loaded image. | blur |
| sharpen | This command sharpens the loaded image. | sharpen |
| sepia | This command transforms the loaded image into a sepia tone image. | sepia |
| greyscale | This command transforms the loaded image into a grey scale image. | greyscale |
| Generate,checkerboard <sizeOfSquare>| This command generates a checkerboard with the specified size. | Generate,checkerboard 64 |
| Generate,greece,flag <lengthOfFlag>| This command generates the Greece flag with the specified size. | Generate,greece,flag 27 |
| Generate,france,flag <lengthOfFlag>| This command generates the France flag with the specified size. | Generate,france,flag 27 |
| Generate,swiss,flag <lengthOfFlag>| This command generates the Swiss flag with the specified size. | Generate,france,swiss 27 |
| Generate,horizontal,rainbow <heightOfStripe> <lengthOfRainbow>| This command generates a horizontal rainbow with the specified size. | Generate,horizontal,rainbow 49 343 |
| Generate,vertical,rainbow <lengthOfStrip> <heightOfRainbow>| This command generates a vertical rainbow with the specified size. | Generate,horizontal,rainbow 343 49|
| dither | This command converts the loaded image into a dithered image. | dither |
| mosaic | This command converts the loaded image into a mosaic image. | mosaic |
  
  
- **Example Script**

```sh
load: beach.png
dither
save: beach-dither.png
load: beach.png
blur
save: beach-blur.png
sepia
save: beach-blur-sepia.png
Generate,checkerboard 64
save: checkerboard.png
...
```



## Using the program via Controller
---

 - Initialise the EnhancedImageModel.
 - In the *Run/Debug Configurations* dialog box, type "-script path-of-file".
 - Create any implementation of Readable interface according to the use case.
 - Initialise the controller by passing the readable object.
 - Call the *processInput* method of the controller and pass the model as its argument.
 Note: The commands in the readable object should be seperated by spaces.
 
 
## Using the program via InteractiveController
---

 - In Intellij, from the *Run* dropdown, select *Edit Configurations*.
 - In the *Run/Debug Configurations* dialog box, type "-interactive".
 - Create any implementation of ImageView interface.
 - Create any implementation of EnhancedImageModel interface.
 - Initialise the controller by passing the view and model created above.
 - Call the *processInput* method of the controller.
 
## Using the GUI
---

 - The image can be loaded using the *Load* button. This will open a dialog box of file explorer which will let you select the image to be loaded. (Only jpeg, png, webp and gif images are supported)
 - To generate patterns, you need to select an option from the drop down menu in the *Generate Patterns* section. Upon selection of an option, depending upon the requirements, addiontional input text labels to take parameters will be displayed. Finally, clicking on the "Generate" button will generate the required pattern.
 - To perform operations on the current image, you need to select an option from the drop down menu in the *Options on current image* section. Upon selecting an option, depending upon the requirements, addiontional input text labels to take parameters will be displayed. Finally, clicking on the "perform operation" button will perform the selected operation.
 - The image can be saved using the *Save* button. This will open a dialog box of file explorer and let you select the loaction where the image can be saved. (Only jpeg, png, webp and gif images extensions are allowed.)
 - To terminate the program, use the exit button.
 - The image on which the current operations are being performed will be shown in the panel to the right, namely in the current image pane. If the image size is larger than the size of the pane, scrollbar will appear accordingly.
 (Note : For the program to run perfectly, the minimum screen size required is 1200x800).

## Using the program via JAR
---

  - To run the program using a script file, open the termial in the directory containing the JAR and type:
  
  ```sh
java -jar Program.jar -script path-of-script-file:
```

*input.txt* should contain the commands as specified in the *Example Script* above.


  - To run the program using GUI, open the termial in the directory containing the JAR and type:
  
  ```sh
java -jar Program.jar -interactive
```

## Design changes and justifications
---

We made a new interface for the GUI implementation named ImageView, this interface was implemented by InteractiveGUI. This provides the functionality to show the image, show errors and set the features of the program. For specifiying the features  of the program we created a new interface named Features. This interface consists of all the features that our program exposes. This Features interface was then implemented by InteractiveController. As there was considerable amount of logic that could be abstracted, we created an AbstractController with all the common logic. Also to reduce the coupling between the model and the controller we implemented the factory design paradigm.

## Citations
---
**Image 1:** 
- Name in this program: *beach.jpg*
- Original name: *Nago_beach.jpg*
- Author: *kumon*
- Permissions: cc-by-2.0
- License link: https://commons.wikimedia.org/wiki/File:Nago_beach.jpg 

**Image 2:**
- Name in this program: *nature.jpg*
- Original name: *nature.jpg*
- Author: *Mojeeb Ahmed Ghazi*
- License link: https://commons.wikimedia.org/wiki/File:Beauti_of_nature.jpg 

