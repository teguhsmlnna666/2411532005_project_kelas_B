package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.Database;
import model.Customer;

public class CustomerRepo implements CustomerDAO {
    
    private Connection connection;
    final String insert = "INSERT into customer (id, nama, alamat, nomorhp) VALUES (?,?,?,?);";
    final String select = "SELECT * FROM customer;";
    final String delete = "DELETE FROM customer WHERE id=?;";
    final String update = "UPDATE customer SET nama=?, alamat=?, nomorhp=? WHERE id=?;";
    
    public CustomerRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Customer customer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, customer.getId());
            st.setString(2, customer.getNama());
            st.setString(3, customer.getAlamat());
            st.setString(4, customer.getNomorhp());
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (st != null) st.close(); } catch(SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public List<Customer> show() {
        List<Customer> ls = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString("id"));
                c.setNama(rs.getString("nama"));
                c.setAlamat(rs.getString("alamat"));
                c.setNomorhp(rs.getString("nomorhp"));
                ls.add(c);
            }
        } catch(SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (st != null) st.close(); } catch(SQLException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, customer.getNama());
            st.setString(2, customer.getAlamat());
            st.setString(3, customer.getNomorhp());
            st.setString(4, customer.getId());
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (st != null) st.close(); } catch(SQLException e) { e.printStackTrace(); }
        }
    }
}
