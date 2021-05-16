/**
 * 
 */
package sudoku;

import java.util.ArrayList;

import javax.swing.JTextField;

/**
 * @author Justin Yeung
 * Class for converting Sudoku into 1D int array
 */
public class ConvertSudoku {

	public static Integer[] toIntArray(JTextField[][] matrix)
	{
		int n = matrix.length;
		int puzzle[][] = new int[n][n]; // 2D array
		Integer finalMatrix[] = new Integer[n*n]; // 1D array
		ArrayList<Integer> list = new ArrayList<Integer>();
		Extract extract; // Extraction class
		
		for (int i = 0; i < n; i++) // Outer
		{
			for (int j = 0; j < n; j++) // Inner
			{
				
				if (!matrix[i][j].getText().isEmpty()) // If matrix already has value then set it
				{
					//System.out.println(matrix[i][j].getText());
					puzzle[i][j] = Integer.parseInt(matrix[i][j].getText());
				}
			}
		}
		
		if (n == 9) // Instantiate class based on matrix size
		{
			extract = new Extract3x3(puzzle, 0, 0, n);
		}
		else if (n == 12)
		{
			extract = new Extract4x3(puzzle, 0, 0, n);
		}
		else // This should not happen, just to make the compiler recognize that it is initialized
		{
			extract = null;
		}
		
		for (int i = 0; i < n; i++) // Add modified matrices
		{
			finalMatrix = extract.toMatrix(i);
			for (int i2 = 0; i2 < n; i2++)
			{
				list.add(finalMatrix[i2]); // Add values to a list
			}
		}
		
		finalMatrix = list.toArray(new Integer[n*n]);
		
		return finalMatrix;
	}
}
