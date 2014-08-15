package ga.shane.puzzle16;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/** @author http://www.shane.ga */
public class PuzzleSelector {
	public PuzzleSelector() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Images", PuzzleWindow.formats));
		
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selected = chooser.getSelectedFile();
			
			try {
				BufferedImage img = ImageIO.read(selected);
				new PuzzleWindow(new Grid(img));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}