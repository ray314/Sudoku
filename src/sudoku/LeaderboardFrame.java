/**
 * 
 */
package sudoku;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * @author Justin Yeung
 *
 */
public class LeaderboardFrame extends JFrame implements ActionListener {

	private JPanel southPanel;
	private JButton closeButton;
	private JTable table;
	/**
	 * 
	 */
	public LeaderboardFrame() {
		// TODO Auto-generated constructor stub
		table = new JTable(new Leaderboard(this)); // Create leaderboard table
		JScrollPane scrollPane = new JScrollPane(table);
		JLabel title = new JLabel("Leaderboard", JLabel.CENTER);
		table.setRowHeight(30);
				
				
		title.setFont(new Font("Arial", Font.BOLD, 24));
		//title.setForeground(fg);
		
		createPanel();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set Default Close Operation
		getContentPane().add(title, BorderLayout.PAGE_START);
		getContentPane().add(scrollPane, BorderLayout.CENTER); // Add table to frame
		getContentPane().add(southPanel, BorderLayout.PAGE_END);
		setPreferredSize(new Dimension(1280, 720));
		pack(); // Size the frame
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == closeButton)
		{
			dispose(); // Close frame when pressed
		}
	}
	
	private void createPanel()
	{
		southPanel = new JPanel(new FlowLayout());
		closeButton = new JButton("Close");
		
		southPanel.add(closeButton);
		closeButton.addActionListener(this);
	}

}
