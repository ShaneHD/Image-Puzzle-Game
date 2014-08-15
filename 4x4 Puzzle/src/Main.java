import ga.shane.puzzle16.Grid;

import java.io.File;

import javax.imageio.ImageIO;


/** @author http://www.shane.ga */
public class Main {
	public static void main(String[] args) throws Exception {
		Grid grid = new Grid(ImageIO.read(new File("car.jpg")));
	}
}
