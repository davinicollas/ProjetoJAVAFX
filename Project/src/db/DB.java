package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static Connection con = null;
	public static Connection getConnection() {
		
		if(con == null) {
			try {
				 String serverName = "localhost";
				 String mydatabase = "javafx";
				 String url ="jdbc:mysql://"+ serverName +"/"+ mydatabase;
				 String user = "root";
				 String password = "12345";
				 con = DriverManager.getConnection(url , user, password);	
			}catch(SQLException e) {
			 throw new DbExcepiton("Erro de conexão: " + e.getMessage());
				
			}
		}
		return con;
		
	}
	
	public static void CloseConection () {
		if(con != null) {
			try {
				con.close();
			}catch(SQLException e) {
				 throw new DbExcepiton("Erro ao fechar a conexão: " + e.getMessage());

			}
		}
	}
	
	public static void CloseStatement (Statement st) {
		if(st != null) {
			try {
				st.close();
			}catch(SQLException e) {
				 throw new DbExcepiton("Erro ao fechar o Statement: " + e.getMessage());

			}
		}
	}
	
	public static void CloseResult(ResultSet rs) {
		if(rs != null) {
			try{
				rs.close();
			}catch(SQLException e) {
				 throw new DbExcepiton("Erro ao fechar o ResultSet: " + e.getMessage());
			}	
		}
		
	}
	

}
