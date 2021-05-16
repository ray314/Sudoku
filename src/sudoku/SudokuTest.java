/**
 * 
 */
package sudoku;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//import junit.framework.Assert;

/**
 * @author Justin Yeung
 *
 */
public class SudokuTest {

	SudokuGenerate generate;
	SudokuGenerate generate12x12;
	SaveLoadSudoku saveload;
	MatrixPanel[] matrixPanels;
	JPanel 		panel;
	JTextField[][] sudokuMatrix;
	
	/**
	 * @throws java.lang.Exception
	 */

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		generate = new SudokuGenerate(9);
		generate12x12 = new SudokuGenerate(12);
		saveload = new SaveLoadSudoku(new JFrame());
		matrixPanels = new MatrixPanel[9];
		sudokuMatrix = new JTextField[9][9];
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	// Check if the matrix is a valid sudoku
	@Test
	public void test() {
		int[][] matrix = generate.generate(1);
		boolean valid;
		
		valid = AbstractSudokuSolver.validateSudoku(matrix);
		Assert.assertEquals(true, valid);
	}
	
	@Test
	public void test2() {
		int[][] matrix = generate12x12.generate(1);
		boolean valid;
		
		valid = AbstractSudokuSolver.validateSudoku(matrix);
		Assert.assertEquals(true, valid);
	}
	
	@Test
	public void testSaveLoad()
	{
		int[][] puzzle = generate.generate(1);
		int rowStart = 0; // Starting rows and columns
		int colStart = 0;
		Extract3x3 extract = new Extract3x3(puzzle, rowStart, colStart, 9); // Instantiate extraction class
		panel = new JPanel();
		JPanel loadPanel;
		// Create the matrix
		for (int i = 0; i < 9; i++)
		{
			Integer[] matrix = extract.toMatrix(i); // Add the 9 or 12 numbers, passing the current index as parameter
			matrixPanels[i] = new MatrixPanel(9, i, matrix, sudokuMatrix); // Create Matrix then pass by reference to sudokuMatrix
			panel.add(matrixPanels[i]);
		}
		
		saveload.saveSudoku(panel, sudokuMatrix, generate.solution, 0); // Call the method
		loadPanel = saveload.openSudoku(); // Load the panel
		
		
		Assert.assertTrue(loadPanel != null); // Asset that the file is not null
		Assert.assertSame(panel, loadPanel); //TODO: Override equals method in panel
	}

}
