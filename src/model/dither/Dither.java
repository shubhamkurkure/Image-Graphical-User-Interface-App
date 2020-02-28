package model.dither;

import model.image.Image;

/**
 * <p>Dithers a given image. Uses Floyd–Steinberg dithering methodology to produce the final
 * image.</p>
 */
public interface Dither {
  /**
   * Applies greyscale dithering on the given image using Floyd–Steinberg dithering technique.
   *
   * @param image the image which needs to be dithered.
   * @return a new dithered image.
   */
  Image dither(Image image);
}
