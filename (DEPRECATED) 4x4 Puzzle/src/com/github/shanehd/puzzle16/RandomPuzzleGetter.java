package com.github.shanehd.puzzle16;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;

/** @author https://www.github.com/ShaneHD */
public class RandomPuzzleGetter {
	public RandomPuzzleGetter(File dir) {
		ArrayList<File> files = new ArrayList<File>();
		
		for(File cur : dir.listFiles()) {
			if(cur.isFile() && isImage(cur))
				files.add(cur);
		}
		
		Random random = new Random();
		Collections.shuffle(files);
		File use = files.get(random.nextInt(files.size()));
		BufferedImage img = null;
		
		do {	
			try {
				img = ImageIO.read(use);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} while(img == null);
		
		new PuzzleWindow(new Grid(img));
	}
	
	/**
	 * Checks if a file is an image
	 */
	private boolean isImage(File file) {
		String name = file.getName();
		
		for(String format : PuzzleWindow.FORMATS) {
			if(name.endsWith(format))
				return true;
		}
		
		return false;
	}
}
