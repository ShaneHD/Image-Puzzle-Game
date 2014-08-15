package ga.shane.puzzle16;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** 
 * Splices up a {@link BufferedImage} into a 4x4 grid
 * 
 * @author http://www.shane.ga 
*/
public class Grid {
	private final BufferedImage image;
	public final BufferedImage[] pieces = new BufferedImage[16];
	
	public Grid(BufferedImage image) {
		this.image = image;
		
		//Get the images width
		final int width = image.getWidth();
		//And its height
		final int height = image.getHeight();
				
		//Initialise the splice variables
		int x = 0;
		int y = 0;
		final int w = width / 4;
		final int h = height / 4;
	
		//Iterate from 0 to 16
		for(int i = 0; i < 16; i++) {
			if(i != 0 && i % 4 == 0) {
				x = 0;
				y+= h;				
			}
			
			pieces[i] = image.getSubimage(x, y, w, h);
			
			System.out.println(x);
			x+= w;
		}
		
		JFrame f = new JFrame();
		f.setSize(650, 500);
		f.setAlwaysOnTop(true);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLayout(new GridLayout(4, 4));
		
		for(BufferedImage piece : pieces) {
			f.add(new JLabel(new ImageIcon(piece)));
		}
	}
}
