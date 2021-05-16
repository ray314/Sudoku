/**
 * 
 */
package sudoku;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 * @author Justin Yeung
 *
 */
public class Leaderboard extends AbstractTableModel {

	private String[] columnNames = {
			"Username",
			"Board",
			"Difficulty",
			"Time"
	};
	private Object[][] data;
	private ArrayList<String[]> rowData = new ArrayList<String[]>(); // Create an array list of string arrays
	
	private Connection conn = DBConnection.conn;
	/**
	 * 
	 */
	public Leaderboard(JFrame frame) {
		// TODO Auto-generated constructor stub
		
		try {
			int rowDataSize;
			
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select username, board, difficulty, time from sudokutable");
			
			while (rs.next())
			{
				String[] colData = new String[4];
				colData[0] = rs.getString("username"); // Add all data to column
				colData[1] = rs.getString("board");
				colData[2] = rs.getString("difficulty");
				colData[3] = rs.getString("time");
				rowData.add(colData); // Add column data to row
			}
			rowDataSize = rowData.size();
			
			data = new Object[rowDataSize][4]; // four columns
			
			for (int i = 0; i < rowDataSize; i++) // Nested for loop to add all data to the object
			{
				for (int j = 0; j < 4; j++)
				{
					data[i][j] = rowData.get(i)[j]; // Return data from colData in rowData
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame, "Error loading leaderboard", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
	public boolean isCellEditable(int row, int col) {
        //Make cells not editable
		return false;
    }

}
