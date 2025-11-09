package config;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Database {
	public static Connection koneksi() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn =
					DriverManager.getConnection("jdbc:mysql://localhost/laundry_apps", "root", "");
//			JOptionPane.showMessageDialog(null, "Koneksi Berhasil!");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String [] args) {
		Database.koneksi()
;	}
}
