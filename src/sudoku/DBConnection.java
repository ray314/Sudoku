/**
 * 
 */
package sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Justin Yeung
 *
 */
public class DBConnection {
	private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String JDBC_URL = "jdbc:derby:SudokuDB";

	public static Connection conn;
	
	public DBConnection(String username, String password) {
		System.out.println(username + " " + password);
		try {
			conn = DriverManager.getConnection(JDBC_URL, username, password);
			
			if (conn != null)
			{
				System.out.println("Connection Successful");
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			System.err.println(e.getMessage());
		}
	}
	
	public static void execute(String query)
	{
		try {
			Statement s = conn.createStatement();
			s.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		DBConnection dbc = new DBConnection("user", "test");
		//String query = "select * from sudokutable";
		try {
			Statement s = conn.createStatement();
			//s.executeUpdate("insert into sudokutable (username, board, difficulty, time)\r\n" + 
			//		"values ('user', '9x9', 'Easy', '3 hours')");
			
			s.addBatch("insert into sudokutable (username, board, difficulty, time)\r\n" + 
					"values ('user', '9x9', 'Easy', '4 hours')");
			s.addBatch("insert into sudokutable (username, board, difficulty, time)\r\n" + 
					"values ('user', '9x9', 'Easy', '5 hours')");
			s.addBatch("insert into sudokutable (username, board, difficulty, time)\r\n" + 
					"values ('user', '9x9', 'Easy', '6 hours')");
			s.executeBatch();
			ResultSet rs = s.executeQuery("select userID, username, board, difficulty, time from sudokutable");
			
			while(rs.next())
			{
				System.out.println(rs.getInt("userID"));
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("board"));
				System.out.println(rs.getString("difficulty"));
				System.out.println(rs.getString("time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
