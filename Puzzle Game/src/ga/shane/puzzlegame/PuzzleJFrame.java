package ga.shane.puzzlegame;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** @author https://www.github.com/ShaneHD */
public class PuzzleJFrame extends JFrame implements MouseListener {
	private final Grid grid;
	private final ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private JLabel removed;
	private boolean init = true;
	
	public PuzzleJFrame(Grid grid) {
		this.grid = grid;
//		Give this frame a grid layout
		setLayout(new GridLayout(grid.size, grid.size));
		
//		Set up the grid for the first time
		grid.randomise();
		setup();
		
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	private void addGrid() {
//		Iterate through all pieces in the grid
		for(BufferedImage piece : grid.pieces) {
//			If the piece has been removed
			if(piece == null) {
				removed = new JLabel(new ImageIcon(grid.removedPiece));
				add(removed);
				labels.add(removed);
				removed.setVisible(false);
				continue;
			}
			
//			Create the piece in JLabel form
			JLabel label = new JLabel(new ImageIcon(piece));
			label.addMouseListener(this);
			add(label);
			labels.add(label);
		}
		
		revalidate();
		repaint();
	}
	
	/**
	 * Sets up the puzzle
	 */
	private void setup() {
//		Remove any previously added pieces
		for(JLabel label : labels)
			remove(label);
		
		labels.clear();
		setTitle("Shane's puzzle game [" + grid.size + "x" + grid.size + "] [" + grid.moveCount() + " moves]");
		
//		When the puzzle is solved
		if(grid.solved()) {
			setTitle(getTitle() + " - You've won!");
			addGrid();
			
			for(JLabel label : labels)
				label.removeMouseListener(this);
			
			removed.setVisible(true);
			return;
		} else if(init)
			init = false;
		
		addGrid();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		Make sure the left mouse button is being pressed
		if(e.getButton() != 1)
			return;
		
//		The piece that's been clicked
		JLabel clicked = (JLabel) e.getSource();
//		Its index
		int index = labels.indexOf(clicked);
		
		grid.move(index);
		setup();
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
