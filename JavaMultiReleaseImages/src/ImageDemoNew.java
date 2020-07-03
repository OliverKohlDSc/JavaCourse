import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.image.MultiResolutionImage;
import java.awt.image.BaseMultiResolutionImage;
import javax.imageio.ImageIO;

import java.util.List;
import java.util.ArrayList;

public class ImageDemoNew {
	public static Image getMRImage(double width, double height) throws MalformedURLException, IOException {
		List<String> imageUrls = List.of("/images/small.png", "/images/high.png", "/images/uhigh.png");
		List<Image> images = new ArrayList<>();
		
		for (String url : imageUrls) {
			images.add(ImageIO.read(new URL(url)));
		}
		
		// Read all images in our MRI
		MultiResolutionImage mri = new BaseMultiResolutionImage(images.toArray(new Image[0]));
		
		List<Image> variants = mri.getResolutionVariants();
		
		System.out.println("The number of loaded images is: " + variants.size());
		
		for (Image image : variants) {
			System.out.println(image);
		}
		
		
		return mri.getResolutionVariant(width, height);
		
		/*
		// get resolution specific image variants
		Image variant1 = mri.getResolutionVariant(130, 40);
		System.out.format("Image for destination [%d, %d]: [%d, %d]", 130, 40, 
				variant1.getWidth(null), variant1.getHeight(null));
		
		Image variant2 = mri.getResolutionVariant(700, 380);
		System.out.format("Image for destination [%d, %d]: [%d, %d]", 700, 380, 
				variant2.getWidth(null), variant2.getHeight(null));
		
		Image variant3 = mri.getResolutionVariant(1024, 768);
		System.out.format("Image for destination [%d, %d]: [%d, %d]", 1024, 768, 
				variant3.getWidth(null), variant3.getHeight(null));
		*/
	}
}
