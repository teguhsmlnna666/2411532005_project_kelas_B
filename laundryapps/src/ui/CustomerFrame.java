package ui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import model.Customer;
import model.CustomerBuilder;
import table.TableCustomer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCustomerName;
	private JTextField txtCustomerAddress;
	private JTextField txtCustomerPhone;
	private JTextField txtCustomerEmail;
	private JTable tableCustomers;
	
	public String id;
	List<Customer> ls;
	CustomerRepo customerRepo = new CustomerRepo();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void loadTable() {
		ls = customerRepo.show();
		TableCustomer tc = new TableCustomer(ls);
		tableCustomers.setModel(tc);
		tableCustomers.getTableHeader().setVisible(true);
	}
	
	public void reset() {
		txtCustomerName.setText("");
		txtCustomerAddress.setText("");
		txtCustomerPhone.setText("");
		txtCustomerEmail.setText("");
	}

	/**
	 * Create the frame.
	 */
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setBounds(48, 41, 48, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(48, 64, 48, 14);
		contentPane.add(lblAlamat);
		
		JLabel lblNo = new JLabel("No HP");
		lblNo.setBounds(48, 89, 48, 14);
		contentPane.add(lblNo);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(48, 113, 48, 14);
		contentPane.add(lblEmail);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(106, 38, 257, 20);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		
		txtCustomerAddress = new JTextField();
		txtCustomerAddress.setColumns(10);
		txtCustomerAddress.setBounds(106, 61, 257, 20);
		contentPane.add(txtCustomerAddress);
		
		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setColumns(10);
		txtCustomerPhone.setBounds(106, 86, 257, 20);
		contentPane.add(txtCustomerPhone);
		
		txtCustomerEmail = new JTextField();
		txtCustomerEmail.setColumns(10);
		txtCustomerEmail.setBounds(106, 110, 257, 20);
		contentPane.add(txtCustomerEmail);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = new CustomerBuilder() 
					.setNama(txtCustomerName.getText())
					.setAlamat(txtCustomerAddress.getText())
					.setHp(txtCustomerPhone.getText())
					.setEmail(txtCustomerEmail.getText())
					.build();
				
				customerRepo.save(customer);
				reset();
				loadTable();
			}
		});
		btnSimpan.setBounds(48, 138, 79, 23);
		contentPane.add(btnSimpan);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				tableCustomers.clearSelection();
				id = null;
			}
		});
		btnBatal.setBounds(135, 138, 79, 23);
		contentPane.add(btnBatal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 172, 339, 362);
		contentPane.add(scrollPane);
		
		tableCustomers = new JTable();
		scrollPane.setViewportView(tableCustomers);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer cs = new CustomerBuilder()
		                .setId(id)
		                .setNama(txtCustomerName.getText())
		                .setEmail(txtCustomerEmail.getText())
		                .setAlamat(txtCustomerAddress.getText())
		                .setHp(txtCustomerPhone.getText())
		                .build();

			 customerRepo.update(cs);
		     reset();
		     loadTable();
			}
		});
		btnUpdate.setBounds(221, 138, 79, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerRepo.delete(id);

			    reset();
			    loadTable();
			}
		});
		btnDelete.setBounds(308, 138, 79, 23);
		contentPane.add(btnDelete);
		
		JLabel lblNewLabel_1 = new JLabel("Customer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(158, 13, 107, 14);
		contentPane.add(lblNewLabel_1);
		
		tableCustomers.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = tableCustomers.getSelectedRow();

		        if (row != -1) {
		            id = tableCustomers.getValueAt(row, 0).toString();
		            txtCustomerName.setText(tableCustomers.getValueAt(row, 1).toString());
		            txtCustomerEmail.setText(tableCustomers.getValueAt(row, 2).toString());
		            txtCustomerAddress.setText(tableCustomers.getValueAt(row, 3).toString());
		            txtCustomerPhone.setText(tableCustomers.getValueAt(row, 4).toString());
		        }
		    }
		});
	}
}
