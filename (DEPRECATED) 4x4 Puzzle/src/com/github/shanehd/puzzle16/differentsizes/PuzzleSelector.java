package com.github.shanehd.puzzle16.differentsizes;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/** @author https://www.github.com/ShaneHD */
public class PuzzleSelector {
	public PuzzleSelector(int size) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Images", PuzzleWindow.FORMATS));
		
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File selected = chooser.getSelectedFile();
			new PuzzleSelector(4, selected);
		}
	}
	
	public PuzzleSelector(int size, File file) {
		try {
			BufferedImage img = ImageIO.read(file);
			new PuzzleWindow(new Grid(size, img));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
