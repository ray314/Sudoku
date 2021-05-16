package sudoku;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SudokuPuzzle extends JFrame implements ActionListener, WindowListener {

	public static boolean playing;
	private JButton btnExit;
	private JButton btnSave;
	private JButton btnSubmit;
	private JPanel centrePanel; // Grid panel for all the Matrix Panels
	private JPanel contentPane;
	private Component eastHorizontonStrut;
	private JPanel eastPanel;
	private JFrame frame; // The parent frame from Sudoku class.
	private JLabel lblSudoku;
	private JLabel lblTimer;
	private MatrixPanel[] matrixPanels;
	private Component northHorizontalStrut;
	private Component northHorizontalStrut2;
	private JPanel northPanel;
	private Component northVerticalStrut;
	private JPanel saveandexitPanel;
	private SaveLoadSudoku saveLoad;
	private int solution[]; // Sudoku Solution
	private Component southHorizontonStrut;
	private JPanel southPanel;
	
	private Component southVerticalStrut;
	private JPanel submitPanel;
	private JTextField[][] sudokuMatrix;
	private TimerThread timer;
	
	private Component westHorizontonStrut;
	private JPanel westPanel;
	private String difficultyName;
	
	public static String username;
	
	// New Sudoku puzzle
	/**
	 * @wbp.parser.constructor
	 */
	public SudokuPuzzle(JFrame frame, int size, int difficulty) {
		SudokuGenerate sudoku = new SudokuGenerate(size);
		matrixPanels = new MatrixPanel[size]; // Create array of panels
		sudokuMatrix = new JTextField[size][size];
		this.frame = frame;
		
		
				
		int puzzle[][] = sudoku.generate(difficulty); // save the puzzle into a variable
		solution = sudoku.solution;
		
		switch (difficulty)
		{
		case 1:
			difficultyName = "Easy";
			break;
		case 2:
			difficultyName = "Normal";
			break;
		case 3:
			difficultyName = "Hard";
			break;
		case 4:
			difficultyName = "Very Hard";
			break;
		}
		
		
		switch (size) // Switch between different grid rows
		{
		case 9:
			centrePanel = new JPanel(new GridLayout(3,3));
			break;
		case 12:
			centrePanel = new JPanel(new GridLayout(4,3));
			break;
		}
		
		CreateMatrix(puzzle, size); // Create the matrix
		CreateFrame(); // Create the frame
	}
	
	public SudokuPuzzle(JFrame frame, JPanel matrix, JTextField[][] sudokuMatrix, int[] solution, int seconds) { // Another constructor for loading puzzle
		this.frame = frame;
		centrePanel = matrix;
		CreateFrame(); // Create the frame
		
		this.sudokuMatrix = sudokuMatrix; // Update variable
		this.solution = solution; // Update solution
		lblTimer.setText(seconds + "s"); // Update time
		timer.seconds = seconds;
	}
	
	/*
	 * Action listener
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == btnExit)
		{
			playing = false; // User no longer playing, end the timer thread
			dispose();
			frame.setVisible(true); // Set main Sudoku visibility back to true
		}
		else if (source == btnSave) // Save file
		{
			timer.pause = true; // Pause timer
			saveLoad = new SaveLoadSudoku(this);
			
			//System.out.println(Integer.parseInt(sudokuMatrix[0][0].getText()));
			saveLoad.saveSudoku(centrePanel, sudokuMatrix, solution, timer.seconds); // Save the matrix panel
			timer.resumeThread(); // Resume timer after saving
		}
		else if (source == btnSubmit)
		{
			//sudokuMatrix = matrixPanels.sudokuMatrix; // Update the variable
			Integer[] submission = ConvertSudoku.toIntArray(sudokuMatrix);

			//try {
			timer.pause = true; // Pause the time when the dialog pops up

			if (CheckSudoku.check(submission, solution) == false)
			{

				JOptionPane.showMessageDialog(this, "Incorrect board", "Incorrect", JOptionPane.ERROR_MESSAGE);
				timer.resumeThread(); // Resume the timer after the user has closed the dialog
			}
			else
			{
				if (SudokuLogin.loggedIn)
				{
					String board = new String(matrixPanels.length + "x" + matrixPanels.length);
					
					DBConnection.execute("insert into sudokutable (username, board, difficulty, time)\r\n" + 
							"values ('"+ username +"', '"+board+"', '"+difficultyName+"', '"+lblTimer.getText()+"')");
				}
				// Execute SQL query

				JOptionPane.showMessageDialog(this, "Correct board", "Correct", JOptionPane.INFORMATION_MESSAGE);
				playing = false; // User no longer playing, end the timer thread
				dispose();
				frame.setVisible(true);
			}


			/*} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} */


		}
	}
	
	// Create buttons and add listeners
	private final void CreateButtons()
	{
		btnSubmit = new JButton("Submit");
		submitPanel.add(btnSubmit);
		btnSubmit.addActionListener(this);

		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		saveandexitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 35));
		saveandexitPanel.add(btnSave);
		saveandexitPanel.add(btnExit);
	}

	/**
	 * Create the frame.
	 */
	private final void CreateFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(this); // Add window listener for default close operation
		setPreferredSize(new Dimension(1280, 720));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		CreatePanels();
		CreateButtons();
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		northHorizontalStrut = Box.createHorizontalStrut(400);
		northPanel.add(northHorizontalStrut);
			
		lblSudoku = new JLabel("Sudoku");
		lblSudoku.setFont(new Font("Tahoma", Font.BOLD, 18));
		northPanel.add(lblSudoku);
		
		northVerticalStrut = Box.createVerticalStrut(100);
		northPanel.add(northVerticalStrut);
		
		northHorizontalStrut2 = Box.createHorizontalStrut(400);
		northPanel.add(northHorizontalStrut2);
		
		lblTimer = new JLabel("0s");
		timer = new TimerThread(lblTimer); // Pass the label into TimerThread
		timer.start(); // Start the thread
		
		northPanel.add(lblTimer);
		
		southVerticalStrut = Box.createVerticalStrut(100);
		submitPanel.add(southVerticalStrut);
		
		southHorizontonStrut = Box.createHorizontalStrut(120);
		submitPanel.add(southHorizontonStrut);
		
		westHorizontonStrut = Box.createHorizontalStrut(100);
		westPanel.add(westHorizontonStrut);
		
		eastHorizontonStrut = Box.createHorizontalStrut(100);
		eastPanel.add(eastHorizontonStrut);
		
		//centrePanel = new JPanel();
		contentPane.add(centrePanel, BorderLayout.CENTER);
		//centrePanel.setLayout(new GridLayout(9, 0, 0, 0));
		
		playing = true;
		this.pack();
		this.setVisible(true); // Unhide the frame
	}
	
	/*
	 * Matrix creation
	 */
	private final void CreateMatrix(int puzzle[][], int size)
	{
		int rowStart = 0; // Starting rows and columns
		int colStart = 0;
		Extract extract; // Declare Extract class
		
		// If size = 9 then instatiate 3x3 class else, 4x3 class
		if (size == 9)
		{
			extract = new Extract3x3(puzzle, rowStart, colStart, size); // Instantiate extraction class
		}
		else if (size == 12)
		{
			extract = new Extract4x3(puzzle, rowStart, colStart, size);
		}
		else // This should never go here
		{
			extract = null; // This is to let it compile
		}
		
		// Create the matrix
		for (int i = 0; i < size; i++)
		{
			Integer[] matrix = extract.toMatrix(i); // Add the 9 or 12 numbers, passing the current index as parameter
			matrixPanels[i] = new MatrixPanel(size, i, matrix, sudokuMatrix); // Create Matrix then pass by reference to sudokuMatrix
			centrePanel.add(matrixPanels[i]); // Add the matrix panel to the centre panel.
		}
	}
	
	private final void CreatePanels()
	{
		northPanel = new JPanel();
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		submitPanel = new JPanel();
		southPanel.add(submitPanel, BorderLayout.CENTER);
		
		saveandexitPanel = new JPanel();
		southPanel.add(saveandexitPanel, BorderLayout.EAST);
		
		westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);
		
		eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * Window listener for when the user exits.
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	



}
