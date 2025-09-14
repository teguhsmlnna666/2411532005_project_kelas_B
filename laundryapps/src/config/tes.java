package config;

import java.sql.*;
import javax.swing.JOptionPane;

public class tes {
	public static void main(String[] args) {
        Connection conn = Database.koneksi();
        if (conn != null) {
            JOptionPane.showMessageDialog(null, "Koneksi ke database berhasil!");
        } else {
            JOptionPane.showMessageDialog(null, "Koneksi ke database gagal!");
        }
    }

}