package latihan2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DownloadManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DownloadManager frame = new DownloadManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DownloadManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Download Manager App");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(131, 22, 178, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("File 1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(78, 68, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(140, 68, 216, 23);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel_1_1 = new JLabel("File 2");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(78, 106, 48, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setBounds(140, 102, 216, 23);
		contentPane.add(progressBar_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("File 3");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(78, 140, 48, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JProgressBar progressBar_1_1 = new JProgressBar();
		progressBar_1_1.setBounds(140, 136, 216, 23);
		contentPane.add(progressBar_1_1);
		
		JButton btnNewButton = new JButton("Downloading");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bar 1
				new Thread(() -> {
				    for (int i = 0; i <= 100; i += 25) {
				        progressBar.setValue(i);
				        try {
				            Thread.sleep(500);
				        } catch (InterruptedException ex) {
				            ex.printStackTrace();
				        }
				    }
				}).start();
				// bar 2
				new Thread(() -> {
				    for (int i = 10; i <= 100; i += 30) {
				        progressBar_1.setValue(i);
				        try {
				            Thread.sleep(500); 
				        } catch (InterruptedException ex) {
				            ex.printStackTrace();
				        }
				    }
				}).start();
				// bar 3
				new Thread(() -> {
				    for (int i = 10; i <= 100; i += 10) {
				        progressBar_1_1.setValue(i);
				        try {
				            Thread.sleep(500);
				        } catch (InterruptedException ex) {
				            ex.printStackTrace();
				        }
				    }
				}).start();


			    btnNewButton.setEnabled(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(249, 183, 107, 23);
		contentPane.add(btnNewButton);
	}
}
