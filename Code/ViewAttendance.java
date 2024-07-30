package Employee_Management;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class ViewAttendance extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton backButton, updateButton;
    private JDateChooser updateDateChooser;
    private JComboBox<String> updateStatusComboBox;
    private String[] statusOptions = {"Present", "Absent"};
    private String empId;

    public ViewAttendance(String empId) {
        this.empId = empId;

        // Custom panel for background image
        class ImagePanel extends JPanel {
            private Image backgroundImage;

            public ImagePanel(String fileName) {
                try {
                    backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        }

        ImagePanel contentPane = new ImagePanel("/Employee_Management/Images/bg.jpg");
        contentPane.setLayout(null);
        setContentPane(contentPane);

        setTitle("View Attendance");
        setLayout(null);
        setSize(1200, 600);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JLabel heading = new JLabel("Employee Attendance");
        heading.setBounds(50, 20, 500, 30);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(Color.WHITE); // Set font color to stand out against background
        add(heading);

        model = new DefaultTableModel();
        table = new JTable(model);

        // Make the table transparent
        table.setOpaque(false);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
        table.setShowGrid(true); // Show grid lines
        table.setGridColor(Color.WHITE); // Set grid color to white for visibility

        // Set font color for table cells
        table.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 1100, 400);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setOpaque(false);
        tableHeader.setBackground(new Color(0, 0, 0, 0));
        tableHeader.setForeground(Color.BLACK);

        model.addColumn("Emp_ID");
        model.addColumn("Name");
        model.addColumn("Date");
        model.addColumn("Status");

        // Initialize components for updating attendance
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 500, 100, 30);
        dateLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 18));
        dateLabel.setForeground(Color.WHITE);
        add(dateLabel);

        updateDateChooser = new JDateChooser();
        updateDateChooser.setBounds(150, 500, 150, 30);
        add(updateDateChooser);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(320, 500, 100, 30);
        statusLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);
        add(statusLabel);

        updateStatusComboBox = new JComboBox<>(statusOptions);
        updateStatusComboBox.setBounds(400, 500, 150, 30);
        add(updateStatusComboBox);

        updateButton = new JButton("Update Attendance");
        updateButton.setBounds(600, 500, 200, 40);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAttendance();
            }
        });
        add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(850, 500, 200, 40);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Attendance();
            }
        });
        add(backButton);

        loadAttendanceData();

        setVisible(true);
    }

    private void loadAttendanceData() {
        // Clear existing rows in the model
        model.setRowCount(0);

        try {
            Connect conn = new Connect();
            String query = "SELECT a.Emp_ID, e.Name, a.Date, a.Status " +
                    "FROM attendance a " +
                    "JOIN employee_info e ON a.Emp_ID = e.empId " +
                    "WHERE a.Emp_ID = ? " +
                    "ORDER BY a.Date";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, empId);
            ResultSet rs = pst.executeQuery();

            // Populate table with attendance data
            while (rs.next()) {
                String empID = rs.getString("Emp_ID");
                String name = rs.getString("Name");
                Date date = rs.getDate("Date");
                String status = rs.getString("Status");

                Object[] row = {
                        empID,
                        name,
                        date,
                        status
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAttendance() {
        java.util.Date date = updateDateChooser.getDate();
        if (date != null) {
            String formattedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
            String status = (String) updateStatusComboBox.getSelectedItem();

            try {
                Connect conn = new Connect();
                String checkQuery = "SELECT COUNT(*) FROM attendance WHERE Emp_ID = ? AND Date = ?";
                PreparedStatement checkPst = conn.c.prepareStatement(checkQuery);
                checkPst.setString(1, empId);
                checkPst.setString(2, formattedDate);
                ResultSet rs = checkPst.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    // Update existing record
                    String updateQuery = "UPDATE attendance SET Status = ? WHERE Emp_ID = ? AND Date = ?";
                    PreparedStatement updatePst = conn.c.prepareStatement(updateQuery);
                    updatePst.setString(1, status);
                    updatePst.setString(2, empId);
                    updatePst.setString(3, formattedDate);
                    updatePst.executeUpdate();
                } else {
                    // Insert new record if not exists
                    String insertQuery = "INSERT INTO attendance (Emp_ID, Name, Date, Status) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertPst = conn.c.prepareStatement(insertQuery);
                    insertPst.setString(1, empId);
                    insertPst.setString(2, getEmployeeName(empId)); // Assuming getEmployeeName() is defined
                    insertPst.setString(3, formattedDate);
                    insertPst.setString(4, status);
                    insertPst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Attendance updated successfully");
                loadAttendanceData(); // Refresh the table
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a date.");
        }
    }

    // Utility method to get employee name by empId
    private String getEmployeeName(String empId) {
        String name = null;
        try {
            Connect conn = new Connect();
            String query = "SELECT Name FROM employee_info WHERE empId = ?";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, empId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                name = rs.getString("Name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static void main(String[] args) {
        new ViewAttendance("1"); // Pass a test Emp_ID value
    }
}
