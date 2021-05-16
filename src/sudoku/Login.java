/**
 * 
 */
package sudoku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author Justin Yeung
 * Login authentication
 */
public class Login {
	public static Connection authenticate(String username, char[] password)
	{
		
		DBConnection conn = new DBConnection(username, new String(password));
		if (DBConnection.conn != null)
		{
			return DBConnection.conn;
		}
		
		
		return null;
	}
}
