package Employee_Management_System.Code;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Salary extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel model;
    private JButton backButton, processSalaryButton;
    private JComboBox<String> empIdComboBox;

    Salary() {
        setContentPane(new ImagePanel("/Employee_Management_System/Images/Frame.jpg")); // Set background image
        setLayout(null);

        JLabel heading = new JLabel("Salary Tracking");
        heading.setBounds(120, 30, 500, 50);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 35));
        heading.setForeground(Color.WHITE); // Set text color to white
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
        scrollPane.setBounds(50, 120, 1100, 300);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setOpaque(false);
        tableHeader.setBackground(new Color(0, 0, 0, 0));
        tableHeader.setForeground(Color.BLACK);

        model.addColumn("Emp_ID");
        model.addColumn("Name");
        model.addColumn("Designation");
        model.addColumn("Base Salary");

        JLabel empIdLabel = new JLabel("Select Employee:");
        empIdLabel.setBounds(50, 450, 150, 30);
        empIdLabel.setFont(new Font("Abyssinica SIL", Font.PLAIN, 18));
        empIdLabel.setForeground(Color.WHITE); // Set font color
        add(empIdLabel);

        empIdComboBox = new JComboBox<>();
        empIdComboBox.setBounds(210, 450, 150, 30);
        add(empIdComboBox);

        processSalaryButton = new GradientButton("Process Salary", "#273D41", "#659CA7");
        processSalaryButton.setBounds(380, 450, 200, 30);
        processSalaryButton.addActionListener(this);
        add(processSalaryButton);

        backButton = new GradientButton("Back", "#000", "#000"); // Example hex codes
        backButton.setBounds(900, 500, 200, 40);
        backButton.addActionListener(this);
        add(backButton);

        loadEmployeeData();

        setSize(1200, 600);
        setLocation(150, 100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadEmployeeData() {
        try {
            Connect conn = new Connect();
            String query = "SELECT empId, Name, Designation, Salary FROM employee_info";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                        rs.getString("empId"),
                        rs.getString("Name"),
                        rs.getString("Designation"),
                        rs.getDouble("Salary")
                };
                model.addRow(row);
                empIdComboBox.addItem(rs.getString("empId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == processSalaryButton) {
            String empId = (String) empIdComboBox.getSelectedItem();
            if (empId != null) {
                setVisible(false);
                new CalculateSalary(empId);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an employee.");
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new Salary();
    }
}

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
