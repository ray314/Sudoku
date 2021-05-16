package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class SudokuLogin extends JDialog implements ActionListener{

	private final JPanel northPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JPanel centrePanel;
	private JLabel lblPleaseLogIn;
	private JPanel buttonPane;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField password;
	private Component horizontalStrut;
	private JTextField username;
	
	public static boolean loggedIn;
	
	/*
	 * Start the dialog
	 */
	public static void start(JFrame frame)
	{
		try {
			SudokuLogin dialog = new SudokuLogin(frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * Login
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == okButton)
		{ 
			DBConnection.conn = Login.authenticate(username.getText(), password.getPassword());
			
			if (DBConnection.conn != null)
			{
				loggedIn = true;
				SudokuPuzzle.username = username.getText();
				Sudoku.btnLeaderboard.setEnabled(true); // Enable button once user is logged in
				Sudoku.lblLoggedIn.setText("Logged in as " + username.getText());
				Sudoku.lblLoggedIn.setForeground(Color.GREEN);
				JOptionPane.showMessageDialog(this, "Logged in", "Logged in", JOptionPane.INFORMATION_MESSAGE);
				this.dispose(); // Close dialog after pressing ok
			}
			else
			{
				loggedIn = false;
				JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == cancelButton)
		{
			dispose();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SudokuLogin(JFrame frame) {
		super(frame, "Login", Dialog.ModalityType.APPLICATION_MODAL);
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		northPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));
		{
			lblPleaseLogIn = new JLabel("Please log in");
			northPanel.add(lblPleaseLogIn, BorderLayout.CENTER);
		}
		{
			horizontalStrut = Box.createHorizontalStrut(142);
			northPanel.add(horizontalStrut, BorderLayout.WEST);
		}
		{
			buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Login");
				okButton.addActionListener(this);
				buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			centrePanel = new JPanel();
			getContentPane().add(centrePanel, BorderLayout.CENTER);
			centrePanel.setLayout(new FormLayout(new ColumnSpec[] {
					FormSpecs.RELATED_GAP_COLSPEC,
					FormSpecs.DEFAULT_COLSPEC,
					FormSpecs.RELATED_GAP_COLSPEC,
					FormSpecs.DEFAULT_COLSPEC,
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),
					FormSpecs.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,}));
			{
				lblUsername = new JLabel("Username");
				centrePanel.add(lblUsername, "4, 4, right, default");
			}
			{
				username = new JTextField();
				centrePanel.add(username, "6, 4, fill, default");
				username.setColumns(10);
			}
			{
				lblPassword = new JLabel("Password");
				centrePanel.add(lblPassword, "4, 6, right, default");
			}
			{
				password = new JPasswordField();
				centrePanel.add(password, "6, 6, fill, default");
				password.setColumns(10);
			}
		}
	}

}
