package Employee_Management;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

public class Attendance extends JFrame implements ActionListener {

    private JTable table;
    private DefaultTableModel model;
    private JButton addButton, backButton, viewAttendanceButton;
    private JDateChooser dateChooser;
    private JComboBox<String> statusComboBox, empIdComboBox;
    private String[] statusOptions = {"Present", "Absent"};

    public Attendance() {
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

        setTitle("Attendance Management");
        setLayout(null);
        setSize(1200, 700); // Increased height to fit all components
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

        // Set font color for table cells
        table.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 1100, 300);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setOpaque(false);
        tableHeader.setBackground(new Color(0, 0, 0, 0));
        tableHeader.setForeground(Color.BLACK);

        model.addColumn("Emp_ID");
        model.addColumn("Name");

        // Initialize components
        JLabel empIdLabel = new JLabel("Select Employee:");
        empIdLabel.setBounds(50, 380, 150, 30);
        empIdLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 18));
        empIdLabel.setForeground(Color.WHITE); // Set font color
        add(empIdLabel);

        empIdComboBox = new JComboBox<>();
        empIdComboBox.setBounds(210, 380, 150, 30);
        add(empIdComboBox);

        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(400, 380, 100, 30);
        dateLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 18));
        dateLabel.setForeground(Color.WHITE); // Set font color
        add(dateLabel);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(450, 380, 150, 30);
        add(dateChooser);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(650, 380, 100, 30);
        statusLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE); // Set font color
        add(statusLabel);

        statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setBounds(720, 380, 150, 30);
        add(statusComboBox);

        addButton = new GradientButton("Add Attendance", "#273D41", "#659CA7"); // Example hex codes
        addButton.setBounds(150, 450, 200, 40);
        addButton.addActionListener(this);
        add(addButton);

        backButton = new GradientButton("Back", "#000", "#000"); // Example hex codes
        backButton.setBounds(900, 500, 200, 40);
        backButton.addActionListener(this);
        add(backButton);

        viewAttendanceButton = new GradientButton("View Attendance", "#273D41", "#659CA7"); // Example hex codes
        viewAttendanceButton.setBounds(500, 450, 200, 40);
        viewAttendanceButton.addActionListener(this);
        add(viewAttendanceButton);

        setVisible(true);

        // Load employee data into components
        loadEmployeeData();
    }

    private void loadEmployeeData() {
        try {
            Connect conn = new Connect();
            String query = "SELECT empId, Name FROM employee_info";
            ResultSet rs = conn.s.executeQuery(query);

            // Populate table and JComboBox
            while (rs.next()) {
                Object[] row = { rs.getString("empId"), rs.getString("Name") };
                model.addRow(row);

                // Populate JComboBox with employee IDs
                empIdComboBox.addItem(rs.getString("empId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                Connect conn = new Connect();
                String empId = (String) empIdComboBox.getSelectedItem();
                java.util.Date date = dateChooser.getDate();
                if (date != null && empId != null) {
                    String formattedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
                    String status = (String) statusComboBox.getSelectedItem();

                    // Check if an entry already exists for the given empId and date
                    String checkQuery = "SELECT COUNT(*) FROM attendance WHERE Emp_ID = ? AND Date = ?";
                    PreparedStatement checkPst = conn.c.prepareStatement(checkQuery);
                    checkPst.setString(1, empId);
                    checkPst.setString(2, formattedDate);
                    ResultSet checkRs = checkPst.executeQuery();
                    checkRs.next();
                    int count = checkRs.getInt(1);

                    if (count > 0) {
                        // Update existing record
                        String updateQuery = "UPDATE attendance SET Status = ? WHERE Emp_ID = ? AND Date = ?";
                        PreparedStatement updatePst = conn.c.prepareStatement(updateQuery);
                        updatePst.setString(1, status);
                        updatePst.setString(2, empId);
                        updatePst.setString(3, formattedDate);
                        updatePst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Attendance updated successfully");
                    } else {
                        // Insert new record
                        String insertQuery = "INSERT INTO attendance (Emp_ID, Name, Date, Status) VALUES (?, ?, ?, ?)";
                        PreparedStatement insertPst = conn.c.prepareStatement(insertQuery);
                        insertPst.setString(1, empId);
                        insertPst.setString(2, getEmployeeName(empId)); // Assuming getEmployeeName() is defined
                        insertPst.setString(3, formattedDate);
                        insertPst.setString(4, status);
                        insertPst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Attendance added successfully");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an employee and a date.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Home();
        } else if (e.getSource() == viewAttendanceButton) {
            String empId = (String) empIdComboBox.getSelectedItem();
            if (empId != null) {
                setVisible(false);
                new ViewAttendance(empId);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an employee to view attendance.");
            }
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
        new Attendance();
    }
}
