package ga.shane.puzzle16;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** @author http://www.shane.ga */
public class Test extends JFrame {
	public Test(BufferedImage[] pieces) {
		setLayout(new GridLayout(4, 4));
		
		for(BufferedImage piece : pieces)
			add(new JLabel(new ImageIcon(piece)));
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
}
