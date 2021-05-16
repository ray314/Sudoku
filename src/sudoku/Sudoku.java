package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Sudoku implements ActionListener{

	// Initialize all components.
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku window = new Sudoku();
					window.frame.pack();
					window.frame.setVisible(true);
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					
					// Testing connection of DB
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// JButtons
	private JButton btnExitGame;
	private JButton btnLogin;
	private JButton btnStartGame;
	// Button Groups
	private final ButtonGroup buttonGroupBoard = new ButtonGroup();
	private final ButtonGroup buttonGroupDifficulty = new ButtonGroup();
	// JPanels
	private JPanel northPanel;
	private JPanel buttonSouthPanel;
	private JPanel centreDifficultyPanel;
	private JPanel centrePanel;
	private JPanel centreRadioPanel;
	private JPanel southPanel;
	// Main JFrame
	private JFrame frame;
	// Labels
	private JLabel lblSudoku;
	// JRadioButtons
	private JRadioButton rdbtn12x12;
	private JRadioButton rdbtn9x9;
	private JRadioButton rdbtnEasy;
	private JRadioButton rdbtnHard;
	private JRadioButton rdbtnNormal;
	private JRadioButton rdbtnVeryHard;

	private int difficulty = 0;
	private JButton btnLoadGame;
	
	private SaveLoadSudoku load;
	private SudokuPuzzle sudoku;
	
	public static JLabel lblLoggedIn;
	public static JButton btnLeaderboard;
	

	/**
	 * Create the application.
	 */
	public Sudoku() {
		initialize();
	}
	
	// Action listener method
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		//SudokuGenerate.generate(12, 4);
		
		if (source == btnExitGame)
		{
			frame.dispose(); // Close frame when exit button is pressed
		}
		else if (source == btnLogin)
		{
			SudokuLogin.start(frame); // Open Login dialog

		}
		else if(source == btnStartGame) // Start game based on board and difficulty
		{
			if (rdbtnEasy.isSelected())
			{
				difficulty = 1;
			}
			else if (rdbtnNormal.isSelected())
			{
				difficulty = 2;
			}
			else if (rdbtnHard.isSelected())
			{
				difficulty = 3;
			}
			else if (rdbtnVeryHard.isSelected())
			{
				difficulty = 4;
			}
			
			if (rdbtn9x9.isSelected()) // 9x9 Board
			{
				sudoku = new SudokuPuzzle(frame, 9, difficulty);
				frame.setVisible(false); // Hide the window when the game starts
			}
			else if (rdbtn12x12.isSelected()) // 12x12 Board
			{
				sudoku = new SudokuPuzzle(frame, 12, difficulty);
				frame.setVisible(false); // Hide the window when the game starts
			}
		}
		else if (source == btnLoadGame) // Load saved game
		{
			JPanel matrixPanel = load.openSudoku(); // Load matrix
			int[] solution = load.readSolution(); // Load solution
			JTextField[][] sudokuMatrix = load.readMatrix();
			int seconds = load.getTime();
			sudoku = new SudokuPuzzle(frame, matrixPanel, sudokuMatrix, solution, seconds);
		}
		else if (source == btnLeaderboard)
		{
			LeaderboardFrame board = new LeaderboardFrame();
			board.setVisible(true);
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// JFrame
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Load Game
		load = new SaveLoadSudoku(frame);
		
		// South Panel
		southPanel = new JPanel();
		frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		buttonSouthPanel = new JPanel();
		southPanel.add(buttonSouthPanel);
		
		// Buttons
		btnLogin = new JButton("Login");
		buttonSouthPanel.add(btnLogin);
		
		btnStartGame = new JButton("Start Game");
		buttonSouthPanel.add(btnStartGame);
		//btnStartGame.setEnabled(false);
		btnStartGame.addActionListener(this);
		
		btnLoadGame = new JButton("Load Game");
		buttonSouthPanel.add(btnLoadGame);
		btnLoadGame.addActionListener(this);
		
		btnExitGame = new JButton("Exit Game");
		buttonSouthPanel.add(btnExitGame);
		
		btnLeaderboard = new JButton("Leaderboard");
		btnLeaderboard.setEnabled(false);
		buttonSouthPanel.add(btnLeaderboard);
		btnLeaderboard.addActionListener(this);
		
		btnExitGame.addActionListener(this);
		btnLogin.addActionListener(this);
		
		// North Panel
		northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		// Label
		lblSudoku = new JLabel("Welcome to Sudoku");
		lblSudoku.setFont(new Font("Tahoma", Font.BOLD, 18));
		northPanel.add(lblSudoku);
		
		// Centre Panel
		centrePanel = new JPanel();
		frame.getContentPane().add(centrePanel, BorderLayout.CENTER);
		centrePanel.setLayout(new BorderLayout(0, 0));
		
		// Centre Radio Panel
		centreRadioPanel = new JPanel();
		centrePanel.add(centreRadioPanel, BorderLayout.NORTH);
		
		
		// Radio Buttons
		rdbtn9x9 = new JRadioButton("9x9 Board");
		rdbtn9x9.setSelected(true);
		buttonGroupBoard.add(rdbtn9x9);
		rdbtn9x9.addActionListener(this);
		centreRadioPanel.add(rdbtn9x9);
		
		rdbtn12x12 = new JRadioButton("12x12 Board");
		buttonGroupBoard.add(rdbtn12x12);
		rdbtn12x12.addActionListener(this);
		centreRadioPanel.add(rdbtn12x12);
		
		// Centre difficulty panel
		centreDifficultyPanel = new JPanel();
		centrePanel.add(centreDifficultyPanel, BorderLayout.CENTER);
		
		// Radio buttons difficulty
		rdbtnEasy = new JRadioButton("Easy");
		buttonGroupDifficulty.add(rdbtnEasy);
		centreDifficultyPanel.add(rdbtnEasy);
		rdbtnEasy.addActionListener(this);
		
		rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setSelected(true);
		buttonGroupDifficulty.add(rdbtnNormal);
		centreDifficultyPanel.add(rdbtnNormal);
		rdbtnNormal.addActionListener(this);
		
		rdbtnHard = new JRadioButton("Hard");
		buttonGroupDifficulty.add(rdbtnHard);
		centreDifficultyPanel.add(rdbtnHard);
		rdbtnHard.addActionListener(this);
		
		rdbtnVeryHard = new JRadioButton("Very Hard");
		buttonGroupDifficulty.add(rdbtnVeryHard);
		centreDifficultyPanel.add(rdbtnVeryHard);
		
		lblLoggedIn = new JLabel("Not logged in");
		lblLoggedIn.setForeground(Color.RED);
		lblLoggedIn.setHorizontalAlignment(SwingConstants.CENTER);
		centrePanel.add(lblLoggedIn, BorderLayout.SOUTH);
		rdbtnVeryHard.addActionListener(this);
	}
}
