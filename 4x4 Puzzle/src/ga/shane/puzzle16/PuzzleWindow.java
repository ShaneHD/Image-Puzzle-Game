package ga.shane.puzzle16;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** @author http://www.shane.ga */
public class PuzzleWindow extends JFrame {
	private final Grid grid;
	
	public PuzzleWindow(Grid grid) {
		this.grid = grid;
		
		setLayout(new GridLayout(4, 4));
		
		for(BufferedImage piece : grid.pieces)
			add(new JLabel(new ImageIcon(piece)));
		
		setTitle("Shane's 4x4 puzzle game");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
}
