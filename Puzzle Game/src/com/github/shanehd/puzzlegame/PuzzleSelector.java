package com.github.shanehd.puzzlegame;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.shanehd.utilities.ImageUtils;

/** @author https://www.github.com/ShaneHD */
public class PuzzleSelector {
	public PuzzleSelector(int size) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Images", ImageUtils.FORMATS));
		
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selected = chooser.getSelectedFile();
			new PuzzleSelector(size, selected);
		}
	}
	
	public PuzzleSelector(int size, File file) {
		try {
			BufferedImage img = ImageIO.read(file);
			new PuzzleJFrame(new Grid(size, img));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
