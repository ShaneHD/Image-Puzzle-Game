package com.github.shanehd.puzzlegame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;

import com.github.shanehd.utilities.FileUtils;

/** @author https://www.github.com/ShaneHD */
public class RandomPuzzleGetter {
	public RandomPuzzleGetter(int size, File dir) {
		ArrayList<File> files = new ArrayList<File>();
		
		for(File cur : dir.listFiles()) {
			if(cur.isFile() && FileUtils.isImage(cur))
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
		
		new PuzzleJFrame(new Grid(size, img));
	}
}
