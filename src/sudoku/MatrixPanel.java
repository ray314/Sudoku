/**
 * 
 */
package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Justin Yeung
 *
 */
public class MatrixPanel extends JPanel {

	public JTextField[][] sudokuMatrix;
	// public JTextField[][][] sudokuMatrix;
	/**
	 * Create matrix prefilled
	 */ 
	//public MatrixPanel(int size, int start, Integer[][][] matrix, JTextField[][][] sudokuMatrix) {
	public MatrixPanel(int size, int start, Integer[] matrix, JTextField[][] sudokuMatrix) {
		// TODO Auto-generated constructor stub
		super();
		switch (size)
		{
		case 9:
			setLayout(new GridLayout(3, 3)); // Grid layout
			break;
		case 12:
			setLayout(new GridLayout(4, 3)); // 4x3 panels
			break;
		}
		this.sudokuMatrix = sudokuMatrix;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		createMatrix(start, size, matrix);
		
	}
	
	/*
	 * Create matrix from saved file
	 */
	public MatrixPanel(LayoutManager layout)
	{
		super(layout);
	}
	//private void createMatrix(int size, Integer[][][] matrix)
	/*
	 * {
		
		// Create the matrix
		for (int m = 0; m < size; m++)
		{
		
		} 
		 
		for (int i = 0; i < size/3; i++
		{
		
		}
		for (int j = 0; j < 3 ; j++) // Inner, always less than 3
		{
			
			sudokuMatrix[m][i][j] = new JTextField();
			sudokuMatrix[m][i][j].setFont(new Font("Tahoma", Font.PLAIN, 30));
			sudokuMatrix[m][i][j].setHorizontalAlignment(JTextField.CENTER);
			
			if (matrix[m][i][j] > 0) // If matrix already has value then set it
			{
				sudokuMatrix[m][i][j].setText(Integer.toString(matrix[m][i][j]));
				sudokuMatrix[m][i][j].setEditable(false); // Don't let the player edit it.
				
			}
			else
			{
				if (n > 9) // If > 9, it'll be two digits instead of one
				{
					sudokuMatrix[m][i][j].setDocument(new JTextFieldLimit(2)); // Set character limit to 2
				}
				else
				{
					sudokuMatrix[m][i][j].setDocument(new JTextFieldLimit(1)); // Set character limit to 1
				}
				
			}
			add(sudokuMatrix[m][i][j]);
			
			
			
		}
		
	}
	 */
	private void createMatrix(int i, int n, Integer[] matrix)
	{
		
		// Create the matrix
		for (int j = 0; j < n; j++) // Inner
		{
			
			sudokuMatrix[i][j] = new JTextField();
			sudokuMatrix[i][j].setFont(new Font("Tahoma", Font.PLAIN, 30));
			sudokuMatrix[i][j].setHorizontalAlignment(JTextField.CENTER);
			
			if (matrix[j] > 0) // If matrix already has value then set it
			{
				sudokuMatrix[i][j].setText(Integer.toString(matrix[j]));
				sudokuMatrix[i][j].setEditable(false); // Don't let the player edit it.
				
			}
			else
			{
				if (n > 9) // If > 9, it'll be two digits instead of one
				{
					sudokuMatrix[i][j].setDocument(new JTextFieldLimit(2)); // Set character limit to 2
				}
				else
				{
					sudokuMatrix[i][j].setDocument(new JTextFieldLimit(1)); // Set character limit to 1
				}
				
			}
			add(sudokuMatrix[i][j]);
			
			
			
		}
		
	}

}
