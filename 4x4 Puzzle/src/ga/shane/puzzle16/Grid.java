package ga.shane.puzzle16;

import java.awt.image.BufferedImage;

/** 
 * Splices up a {@link BufferedImage} into a 4x4 grid
 * 
 * @author http://www.shane.ga 
*/
public class Grid {
	private final BufferedImage image;
	public final BufferedImage[] pieces = new BufferedImage[16];
	private final BufferedImage[] normalOrder = new BufferedImage[16];
	
	public Grid(BufferedImage image) {
		this.image = image;
		
//		Get the images width
		final int width = image.getWidth();
//		And its height
		final int height = image.getHeight();
		
//		Initialise the splice variables
		int x = 0;
		int y = 0;
//		Width and height to use when splicing
		final int w = width / 4;
		final int h = height / 4;
	
//		Iterate from 0 to 16
		for(int i = 0; i < 16; i++) {
			if(i != 0 && i % 4 == 0) {
				x = 0;
				y+= h;				
			}
			
			pieces[i] = image.getSubimage(x, y, w, h);
			
			x+= w;
		}
		
		for(int i = 0; i < pieces.length; i++)
			normalOrder[i] = pieces[i];		
	}
	
	/**
	 * @return The pieces in their starting order
	 */
	public BufferedImage[] getPiecesInNormalOrder() {
		return normalOrder;
	}
}
