
import java.io.File;
import javax.swing.UIManager;

import com.github.shanehd.puzzle16.RandomPuzzleGetter;


/** @author https://www.github.com/ShaneHD */
public class Main {
	public static void main(String[] args) throws Exception {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
		
		new RandomPuzzleGetter(new File("images"));
	}
}
