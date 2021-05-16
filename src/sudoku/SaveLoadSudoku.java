/**
 * 
 */
package sudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Justin Yeung
 * Save and load Sudoku files
 */
public class SaveLoadSudoku {
	
	private JPanel puzzle; // Puzzle
	private int[] solution; // Solution
	private String filename; // Filename
	private Integer seconds;
	private JFrame frame; // Parent frame
	private JTextField[][] sudokuMatrix; // Text field matrix
	
	public SaveLoadSudoku(JFrame frame)
	{
		this.frame = frame; // Store the parent frame
	}
	
	public final String getPath()
	{
		return filename;
	}
	
	public final int getTime()
	{
		return seconds.intValue();
	}
	
	/*
	 * Save method
	 */
	public final void saveSudoku(JPanel puzzle, JTextField[][] sudokuMatrix, int[] solution, int seconds)
	{
		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .sud files", "sud");
		chooser.setAcceptAllFileFilterUsed(false); // Don't accept all file types
		chooser.addChoosableFileFilter(restrict);
		chooser.setName("Save As");
		int r = chooser.showSaveDialog(frame);
		
		
		if (r == JFileChooser.APPROVE_OPTION) // Check if save is chosen
		{
			filename = chooser.getSelectedFile().getAbsolutePath() + ".sud"; // Get the filename
			this.puzzle = puzzle;
			this.solution = solution;
			this.sudokuMatrix = sudokuMatrix;
			this.seconds = seconds;
			writeSudoku();
		}
	}
	/*
	 * Load method
	 */
	public final JPanel openSudoku()
	{
		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .sud files", "sud");
		chooser.setAcceptAllFileFilterUsed(false); // Don't accept all file types
		chooser.addChoosableFileFilter(restrict);
		chooser.setName("Load Sudoku puzzle");
		int r = chooser.showOpenDialog(frame);
		
		
		if (r == JFileChooser.APPROVE_OPTION) // Check if load is chosen
		{
			filename = chooser.getSelectedFile().getAbsolutePath(); // Get the filename
			
			return loadSudoku(); // Load the sudoku
		}
		return null;
	}
	
	public final int[] readSolution()
	{
		return solution;
	}
	
	public final JTextField[][] readMatrix()
	{
		return sudokuMatrix;
	}
	
	private final void writeSudoku()
	{
		try {
			FileOutputStream fos = new FileOutputStream(filename); // Create Streams
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			// In the order of puzzle - sudokuMatrix - solution
			oos.writeObject(puzzle);
			oos.writeObject(sudokuMatrix);
			oos.writeObject(solution);
			oos.writeObject(seconds);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Error: File not found.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Error: Unknown I/O error", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private final JPanel loadSudoku()
	{
		try {
			FileInputStream fis = new FileInputStream(filename); // Create Streams
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			puzzle = (JPanel) ois.readObject();
			sudokuMatrix = (JTextField[][]) ois.readObject();
			solution = (int[]) ois.readObject();
			seconds = (Integer) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Error: File not found.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Error: Unknown I/O error", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Error: Class not found.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		frame.setVisible(false); // Hide parent window after Sudoku has been loaded
		
		return puzzle;
	}
		
}
