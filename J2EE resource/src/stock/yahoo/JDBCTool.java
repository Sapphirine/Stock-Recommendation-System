package stock.yahoo;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTool {
	private String dbURL; 
	private String user; 
	private String password;
	public static void main(String args[]) {
		try{
			JDBCTool t = new JDBCTool();
			t.setURL("jdbc:mysql://localhost/stock");
			t.setUser("root");
			t.setPassword("");
		    Connection con =t.getConnection(); 
			System.out.println(con.getCatalog());
			con.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public Connection getConnection() {
		try {Class.forName("com.mysql.jdbc.Driver"); 
			return DriverManager.getConnection(dbURL, user, password);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public void setURL(String dbURL) {
		this.dbURL = dbURL; 
	}

	public void setUser(String user) {
		this.user = user; 
	}

	public void setPassword(String password) {
		this.password = password; 
	}
}
