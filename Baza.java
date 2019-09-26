package zavrsniProjekat;

import java.sql.*;
import java.util.*;

public class Baza {
	private static Connection con;
	private static String connectionString;
	public Baza(String connectionString) {
		this.connectionString=connectionString;
	}
	public void connect() {
		try {
			con = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void disconnect() {
		
			try {
				if (con!=null&& !con.isClosed()) {
				con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public HashMap<String,String> getRecnik() {
		HashMap<String,String> recnik = new HashMap<>();
		Statement stm;
		ResultSet rs;
		
			try {
				stm = con.createStatement();
				rs = stm.executeQuery("SELECT word, definition from entries");
				while (rs.next()) {
				recnik.put(rs.getString(1), rs.getString(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return recnik;
	}
	protected boolean insertNew(ArrayList<String> x) throws SQLException {
		String sum = "INSERT INTO newEntries (name, definition) VALUES ";
		
		Statement stm = con.createStatement();
		for (String el: x) {
				sum+= "(\""+el+"\","+"\"undefined\""+"),";
						
			
		}
		System.out.println(sum);
		stm.execute(sum.substring(0,sum.length()-1));
		return true;
	}
	}

