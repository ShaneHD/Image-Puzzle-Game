package ga.shane.puzzle16;

import java.awt.image.BufferedImage;

/** 
 * Splices up a {@link BufferedImage} into a 4x4 grid
 * 
 * @author http://www.shane.ga 
*/
class Grid {
	/** The pieces of the grid in {@link BufferedImage} form */
	public final BufferedImage[] pieces = new BufferedImage[16];
	private final BufferedImage[] normalOrder = new BufferedImage[16];
	
	public Grid(BufferedImage image) {		
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
//			If at the end of the row, go to the next
			if(i != 0 && i % 4 == 0) {
				x = 0;
				y+= h;				
			}
			
//			Create the piece
			pieces[i] = image.getSubimage(x, y, w, h);
			
//			Increment to next column
			x+= w;
		}
		
//		Populate the default order array
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
