package ga.shane.puzzle16;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** @author http://www.shane.ga */
public class PuzzleWindow extends JFrame implements MouseListener {
	private final Grid grid;
	private int takenOutIndex;
	private BufferedImage takenOut;
	private JLabel takenOutLabel;
	private final Random random = new Random();
	private final ArrayList<JLabel> labels = new ArrayList<JLabel>();
	
	public PuzzleWindow(Grid grid) {
		this.grid = grid;
		setLayout(new GridLayout(4, 4));

		BufferedImage[] pieces = randomisePieces();
		setupBoard();
		
		//setResizable(false);
		setTitle("Shane's 4x4 puzzle game");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Randomise the locations of the pieces, and  remove one piece<br>
	 * {@link #takenOut}
	 */
	private BufferedImage[] randomisePieces() {
		List<BufferedImage> all = Arrays.asList(grid.pieces);
		
		Collections.shuffle(all);
		
		int i = random.nextInt(grid.pieces.length);
		takenOut = grid.pieces[i];	
		takenOutIndex = i;
		grid.pieces[i] = null;
			
		return grid.pieces;
	}
	
	private void setupBoard() {
		for(JLabel label : labels)
			remove(label);
		
		labels.clear();
		
		for(BufferedImage piece : grid.pieces) {
			if(piece == null) {
				takenOutLabel = new JLabel(new ImageIcon(takenOut));
				takenOutLabel.setVisible(false);
				add(takenOutLabel);
				takenOutLabel.addMouseListener(this);
				labels.add(takenOutLabel);
				
				continue;
			}
			
			JLabel label = new JLabel(new ImageIcon(piece));
			label.addMouseListener(this);
			add(label);
			labels.add(label);
		}
		
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel clicked = (JLabel) e.getSource();
		BufferedImage img = (BufferedImage) ((ImageIcon) clicked.getIcon()).getImage();
		
		if(!clicked.isVisible())
			return;
		
		int index = labels.indexOf(clicked);
		
		try {
			if(grid.pieces[index - 1] == null) {
				grid.pieces[index - 1] = img;
				grid.pieces[index] = null;
				
				setupBoard();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
