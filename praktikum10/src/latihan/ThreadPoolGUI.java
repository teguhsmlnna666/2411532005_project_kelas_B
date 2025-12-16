package latihan;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolGUI extends JFrame {
    private JTextField threadCountField;
    private JTextField taskCountField;
    private JButton btnStart;
    private JButton btnClear;
    private JTextArea logArea;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JLabel lblStatus;
    private ExecutorService threadPool;
    
	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThreadPoolGUI().setVisible(true);
        });
    }

	/**
	 * Create the frame.
	 */

    public ThreadPoolGUI() {
        setTitle("Aplikasi ThreadPool dengan GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        initComponents();
    }

    private void initComponents() {
        JLabel lblThread = new JLabel("Jumlah Thread:");
        lblThread.setBounds(20, 20, 100, 30);
        getContentPane().add(lblThread);

        threadCountField = new JTextField();
        threadCountField.setBounds(120, 20, 50, 30);
        getContentPane().add(threadCountField);

        JLabel lblTask = new JLabel("Jumlah Tugas:");
        lblTask.setBounds(190, 20, 100, 30);
        getContentPane().add(lblTask);

        taskCountField = new JTextField();
        taskCountField.setBounds(290, 20, 50, 30);
        getContentPane().add(taskCountField);

        btnStart = new JButton("Mulai Proses");
        btnStart.setBounds(360, 20, 120, 30);
        btnStart.addActionListener(e -> startProcessing());
        getContentPane().add(btnStart);

        btnClear = new JButton("Bersihkan Log");
        btnClear.setBounds(490, 20, 120, 30);
        btnClear.addActionListener(e -> clearLog());
        getContentPane().add(btnClear);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane taskScrollPane = new JScrollPane(taskList);
        taskScrollPane.setBorder(BorderFactory.createTitledBorder("Status Tugas"));
        taskScrollPane.setBounds(20, 70, 250, 450);
        getContentPane().add(taskScrollPane);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logArea);
        logScrollPane.setBorder(BorderFactory.createTitledBorder("Log Aktivitas"));
        logScrollPane.setBounds(290, 70, 470, 450);
        getContentPane().add(logScrollPane);

        lblStatus = new JLabel("Siap untuk proses baru.");
        lblStatus.setBorder(BorderFactory.createEtchedBorder());
        lblStatus.setBounds(0, 535, 800, 30);
        getContentPane().add(lblStatus);
    }

    private void startProcessing() {
        try {
            int threadCount = Integer.parseInt(threadCountField.getText());
            int taskCount = Integer.parseInt(taskCountField.getText());
            if (threadCount < 1 || taskCount < 1) {
                JOptionPane.showMessageDialog(this,
                    "Jumlah thread dan tugas harus lebih dari 0!",
                    "Input Tidak Valid",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            taskListModel.clear();
            logArea.append("=== Memulai Proses Baru ===\n");
            logArea.append("ThreadPool dibuat dengan " + threadCount + " worker threads\n\n");
            lblStatus.setText(" Memproses " + taskCount + " tugas dengan " + threadCount + " threads...");
            threadPool = Executors.newFixedThreadPool(threadCount);
            for (int i = 1; i <= taskCount; i++) {
                taskListModel.addElement("Task #" + i + " - Waiting");
            }
            for (int i = 1; i <= taskCount; i++) {
                Task task = new Task(i, logArea, taskListModel);
                threadPool.execute(task);
            }
            new Thread(() -> {
                threadPool.shutdown();
                try {
                    if (threadPool.awaitTermination(5, TimeUnit.MINUTES)) {
                        SwingUtilities.invokeLater(() -> {
                            logArea.append("\n=== Semua tugas selesai ===\n");
                            lblStatus.setText(" Semua tugas selesai!");
                            btnStart.setEnabled(true);
                        });
                    }
                } catch (InterruptedException e) {
                    threadPool.shutdownNow();
                }
            }).start();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Masukkan angka yang valid!",
                "Input Tidak Valid",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearLog() {
        logArea.setText("");
        taskListModel.clear();
        lblStatus.setText(" Log dibersihkan. Siap untuk proses baru.");
        threadCountField.setText("");
        taskCountField.setText("");
    }

}