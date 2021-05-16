/**
 * 
 */
package testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * @author Justin Yeung
 *
 */
public class TestMatrixPanel extends JPanel {

	JTextField[][] text = new JTextField[4][3];
	/**
	 * 
	 */
	public TestMatrixPanel() {
		// TODO Auto-generated constructor stub
		super(new GridLayout(4,3));
	}
	
	public void addFields()
	{
		for (int i = 0; i < 4; i++)
		{
			for (int i2 = 0; i2 < 3; i2++)
			{
				text[i][i2] = new JTextField();
				this.add(text[i][i2]); // add field to panel
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMatrixPanel test[] = new TestMatrixPanel[12];
		Border lineborder = BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK);
		JFrame frame = new JFrame();
		
		for (int i = 0; i < 12; i++)
		{
			test[i] = new TestMatrixPanel();
			test[i].setBorder(lineborder);
			test[i].addFields();
			frame.add(test[i]);
		}
		
		
		
		frame.setLayout(new GridLayout(4,3));
		frame.setPreferredSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame.pack();
		frame.setVisible(true);
	}

}
