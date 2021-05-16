package sudoku;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class SudokuPuzzle extends JFrame implements ActionListener, ArrayCopy {

	private JPanel contentPane;
	private Component southHorizontonStrut;
	private JButton btnSubmit;
	private JPanel saveandexitPanel;
	private JButton btnSave;
	private JButton btnExit;
	private JPanel submitPanel;
	private JPanel southPanel;
	private JLabel lblSudoku;
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JPanel centrePanel; // Grid panel for all the Matrix Panels
	private MatrixPanel[] matrixPanels;
	private JTextField[][] sudokuMatrix;
	private Component eastHorizontonStrut;
	private Component westHorizontonStrut;
	private Component northVerticalStrut;
	private Component southVerticalStrut;
	
	private JFrame frame; // The parent frame from Sudoku class.
	private SaveLoadSudoku saveLoad;
	private int solution[][]; // Sudoku Solution
	
	
	/*
	 * Copies the sudoku array
	 */
	@Override
	public void arrayCopy(int[][] source, int srcPos, int destPos, int[][] dest, int size)
	{
		for (int j = 0; j < size; j++)
		{
				dest[0][j] = source[srcPos][j];
		}
		
	}
	
	/*
	 * Action listener
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == btnExit)
		{
			dispose();
			frame.setVisible(true); // Set main Sudoku visibility back to true
		}
		else if (source == btnSave) // Save file
		{
			
			//System.out.println(Integer.parseInt(sudokuMatrix[0][0].getText()));
			//saveLoad.saveSudoku(centrePanel, solution); // Save the matrix panel
		}
		else if (source == btnSubmit)
		{
			//sudokuMatrix = centrePanel.sudokuMatrix; // Update the variable
			int[][] submission = ConvertSudoku.toIntArray(sudokuMatrix);
			if (CheckSudoku.check(submission, solution) == false)
			{
				JOptionPane.showMessageDialog(this, "Incorrect board", "Incorrect", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Correct board", "Correct", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Create the frame.
	 */
	private final void CreateFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		lblSudoku = new JLabel("Sudoku");
		lblSudoku.setFont(new Font("Tahoma", Font.BOLD, 18));
		northPanel.add(lblSudoku);
		
		northVerticalStrut = Box.createVerticalStrut(100);
		northPanel.add(northVerticalStrut);
		
		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		submitPanel = new JPanel();
		southPanel.add(submitPanel, BorderLayout.CENTER);
		
		southVerticalStrut = Box.createVerticalStrut(100);
		submitPanel.add(southVerticalStrut);
		
		southHorizontonStrut = Box.createHorizontalStrut(120);
		submitPanel.add(southHorizontonStrut);
		
		btnSubmit = new JButton("Submit");
		submitPanel.add(btnSubmit);
		btnSubmit.addActionListener(this);
		
		saveandexitPanel = new JPanel();
		southPanel.add(saveandexitPanel, BorderLayout.EAST);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		saveandexitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 35));
		saveandexitPanel.add(btnSave);
		saveandexitPanel.add(btnExit);
		
		westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		
		westHorizontonStrut = Box.createHorizontalStrut(100);
		westPanel.add(westHorizontonStrut);
		
		eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
		
		eastHorizontonStrut = Box.createHorizontalStrut(100);
		eastPanel.add(eastHorizontonStrut);
		
		//centrePanel = new JPanel();
		contentPane.add(centrePanel, BorderLayout.CENTER);
		//centrePanel.setLayout(new GridLayout(9, 0, 0, 0));
		
		this.setVisible(true); // Unhide the frame
	}
	
	public SudokuPuzzle(JFrame frame, int n, int difficulty) {
		SudokuGenerate sudoku = new SudokuGenerate(n);
		matrixPanels = new MatrixPanel[n]; // Create array of panels
		this.frame = frame;
		
		saveLoad = new SaveLoadSudoku(this);
		
		
				
		int puzzle[][] = sudoku.generate(difficulty); // save the puzzle into a variable
		solution = sudoku.solution;
		
		
		switch (n) // Switch between different grid rows
		{
		case 9:
			centrePanel = new JPanel(new GridLayout(3,3));
			break;
		case 12:
			centrePanel = new JPanel(new GridLayout(4,3));
			break;
		}
		
		// Create the matrix
		for (int i = 0; i < n; i++)
		{
			int[][] copyPuzzle = new int[1][n]; // Copy the inner array
			arrayCopy(puzzle, i, i, copyPuzzle, n); // Source, SourcePos, DestPos, Dest
			matrixPanels[i] = new MatrixPanel(n, copyPuzzle);
			centrePanel.add(matrixPanels[i]);
		}
		//sudokuMatrix = centrePanel.sudokuMatrix; // Save the matrix into a variable
		CreateFrame(); // Create the frame
	}
	
	public SudokuPuzzle(JFrame frame, MatrixPanel puzzle, int[][] solution) { // Another constructor for loading puzzle
		this.frame = frame;
		saveLoad = new SaveLoadSudoku(this);
		centrePanel = puzzle;
		CreateFrame(); // Create the frame
		
		//sudokuMatrix = centrePanel.sudokuMatrix; // Update variable
		this.solution = solution; // Update solution
		
		
		// Create the matrix 
		/*
		for (int i = 0; i < n; i++) // Outer
		{
			for (int j = 0; j < n; j++) // Inner
			{	
				sudokuMatrix[i][j] = puzzle[i][j];
				centrePanel.add(sudokuMatrix[i][j]); // Load saved JTextFields
				//sudokuMatrix[i][j].setColumns(10);
				
			}
		} */
	}

}
