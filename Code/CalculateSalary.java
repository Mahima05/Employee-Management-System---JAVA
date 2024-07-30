package Employee_Management;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CalculateSalary extends JFrame implements ActionListener {
    private String empId;
    private JTextArea salaryDetailsTextArea; // Changed from JLabel to JTextArea
    private JTextField bonusField, overtimeField, deductionField;
    private JButton calculateButton, processButton, backButton;
    private JComboBox<String> monthComboBox, yearComboBox;
    private JTable salaryTable;
    private DefaultTableModel tableModel;

    public CalculateSalary(String empId) {
        this.empId = empId;
        setContentPane(new ImagePanel("/Employee_Management/Images/Frame.jpg")); // Set background image
        setLayout(null);

        JLabel heading = new JLabel("Process Salary");
        heading.setBounds(120, 30, 500, 50);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 35));
        heading.setForeground(Color.WHITE); // Set text color to white
        add(heading);

        // Initialize JTextArea
        salaryDetailsTextArea = new JTextArea();
        salaryDetailsTextArea.setEditable(false);
        salaryDetailsTextArea.setLineWrap(true);
        salaryDetailsTextArea.setWrapStyleWord(true);
        salaryDetailsTextArea.setFont(new Font("Abyssinica SIL", Font.PLAIN, 20));
        salaryDetailsTextArea.setForeground(Color.WHITE); // Set text color to black
        salaryDetailsTextArea.setOpaque(false); // Make text area transparent
        JScrollPane scrollPane = new JScrollPane(salaryDetailsTextArea);
        scrollPane.setBounds(50, 100, 1100, 150);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);

        JLabel bonusLabel = new JLabel("Bonus (%):");
        bonusLabel.setBounds(50, 270, 150, 30);
        bonusLabel.setForeground(Color.WHITE); // Set text color to white
        add(bonusLabel);

        bonusField = new JTextField();
        bonusField.setBounds(200, 270, 100, 30);
        add(bonusField);

        JLabel overtimeLabel = new JLabel("Overtime (%):");
        overtimeLabel.setBounds(350, 270, 150, 30);
        overtimeLabel.setForeground(Color.WHITE); // Set text color to white
        add(overtimeLabel);

        overtimeField = new JTextField();
        overtimeField.setBounds(500, 270, 100, 30);
        add(overtimeField);

        JLabel deductionLabel = new JLabel("Deduction (%):");
        deductionLabel.setBounds(650, 270, 150, 30);
        deductionLabel.setForeground(Color.WHITE); // Set text color to white
        add(deductionLabel);

        deductionField = new JTextField();
        deductionField.setBounds(800, 270, 100, 30);
        add(deductionField);

        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setBounds(50, 320, 150, 30);
        monthLabel.setForeground(Color.WHITE); // Set text color to white
        add(monthLabel);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(200, 320, 150, 30);
        add(monthComboBox);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(400, 320, 150, 30);
        yearLabel.setForeground(Color.WHITE); // Set text color to white
        add(yearLabel);

        String[] years = {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(500, 320, 100, 30);
        add(yearComboBox);

        calculateButton = new GradientButton("Calculate", "#273D41", "#659CA7");
        calculateButton.setBounds(200, 370, 200, 40);
        calculateButton.addActionListener(this);
        calculateButton.setBackground(Color.BLACK);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Abyssinica SIL", Font.PLAIN, 20));
        add(calculateButton);

        processButton = new GradientButton("Process", "#273D41", "#659CA7");
        processButton.setBounds(450, 370, 200, 40);
        processButton.addActionListener(this);
        processButton.setBackground(Color.BLACK);
        processButton.setForeground(Color.WHITE);
        processButton.setFont(new Font("Abyssinica SIL", Font.PLAIN, 20));
        add(processButton);

        backButton = new GradientButton("Back", "#000", "#000");
        backButton.setBounds(700, 370, 200, 40);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Abyssinica SIL", Font.PLAIN, 20));
        add(backButton);

        // Initialize the JTable
        String[] columnNames = {"Month", "Year", "Base Salary", "Bonus", "Overtime", "Deductions", "Total Salary"};
        tableModel = new DefaultTableModel(columnNames, 0);
        salaryTable = new JTable(tableModel);
        salaryTable.setOpaque(false);
        ((DefaultTableCellRenderer) salaryTable.getDefaultRenderer(Object.class)).setOpaque(false);
        salaryTable.setShowGrid(true);
        salaryTable.setForeground(Color.WHITE);
        JScrollPane tableScrollPane = new JScrollPane(salaryTable);
        tableScrollPane.setBounds(50, 420, 1100, 150);
        tableScrollPane.setOpaque(false);
        tableScrollPane.getViewport().setOpaque(false);
        add(tableScrollPane);

        setSize(1200, 630);
        setLocation(150, 100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        loadSalaryDetails();
        loadSalaryRecords();
    }


    private void loadSalaryDetails() {
        try {
            Connect conn = new Connect();
            String query = "SELECT Name, Designation, Salary FROM employee_info WHERE empId = ?";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, empId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String designation = rs.getString("Designation");
                double baseSalary = rs.getDouble("Salary");
                salaryDetailsTextArea.setText("Employee ID: " + empId + "\nName: " + name +
                        "\nDesignation: " + designation + "\nBase Salary: $" + baseSalary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSalaryRecords() {
        try {
            Connect conn = new Connect();
            String query = "SELECT month, year, base_salary, bonus, overtime, deductions, total_salary " +
                    "FROM salary_records WHERE empId = ? " +
                    "ORDER BY year DESC, " +
                    "CASE month " +
                    "    WHEN 'January' THEN 1 " +
                    "    WHEN 'February' THEN 2 " +
                    "    WHEN 'March' THEN 3 " +
                    "    WHEN 'April' THEN 4 " +
                    "    WHEN 'May' THEN 5 " +
                    "    WHEN 'June' THEN 6 " +
                    "    WHEN 'July' THEN 7 " +
                    "    WHEN 'August' THEN 8 " +
                    "    WHEN 'September' THEN 9 " +
                    "    WHEN 'October' THEN 10 " +
                    "    WHEN 'November' THEN 11 " +
                    "    WHEN 'December' THEN 12 " +
                    "END";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, empId);
            ResultSet rs = pst.executeQuery();
            tableModel.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                String month = rs.getString("month");
                int year = rs.getInt("year");
                double baseSalary = rs.getDouble("base_salary");
                double bonus = rs.getDouble("bonus");
                double overtime = rs.getDouble("overtime");
                double deductions = rs.getDouble("deductions");
                double totalSalary = rs.getDouble("total_salary");

                Object[] row = {month, year, baseSalary, bonus, overtime, deductions, totalSalary};
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void calculateSalary() {
        try {
            double baseSalary = getBaseSalary();
            double bonusPercentage = Double.parseDouble(bonusField.getText());
            double overtimePercentage = Double.parseDouble(overtimeField.getText());
            double deductionPercentage = Double.parseDouble(deductionField.getText());

            double bonusAmount = baseSalary * (bonusPercentage / 100);
            double overtimeAmount = baseSalary * (overtimePercentage / 100);
            double deductionAmount = baseSalary * (deductionPercentage / 100);

            double grossSalary = baseSalary + bonusAmount + overtimeAmount - deductionAmount;

            salaryDetailsTextArea.setText("Employee ID: " + empId + "\nBase Salary: $" + baseSalary +
                    "\nBonus: $" + bonusAmount + "\nOvertime: $" + overtimeAmount +
                    "\nDeduction: $" + deductionAmount + "\nGross Salary: $" + grossSalary);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid percentages.");
        }
    }

    private double getBaseSalary() {
        double baseSalary = 0;
        try {
            Connect conn = new Connect();
            String query = "SELECT Salary FROM employee_info WHERE empId = ?";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, empId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                baseSalary = rs.getDouble("Salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return baseSalary;
    }

    private void processSalary() {
        try {
            Connect conn = new Connect();
            double baseSalary = getBaseSalary();
            double bonusPercentage = Double.parseDouble(bonusField.getText());
            double overtimePercentage = Double.parseDouble(overtimeField.getText());
            double deductionPercentage = Double.parseDouble(deductionField.getText());

            double bonusAmount = baseSalary * (bonusPercentage / 100);
            double overtimeAmount = baseSalary * (overtimePercentage / 100);
            double deductionAmount = baseSalary * (deductionPercentage / 100);

            double grossSalary = baseSalary + bonusAmount + overtimeAmount - deductionAmount;

            String name = getEmployeeName();
            String month = (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();

            String query = "INSERT INTO salary_records (empId, name, month, year, base_salary, bonus, overtime, deductions, total_salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.c.prepareStatement(query);
            pst.setString(1, empId);
            pst.setString(2, name);
            pst.setString(3, month);
            pst.setInt(4, Integer.parseInt(year));
            pst.setDouble(5, baseSalary);
            pst.setDouble(6, bonusAmount);
            pst.setDouble(7, overtimeAmount);
            pst.setDouble(8, deductionAmount);
            pst.setDouble(9, grossSalary);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salary processed and updated successfully.");
            loadSalaryRecords(); // Refresh the table after processing
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid percentages.");
        }
    }

    private String getEmployeeName() {
        String name = "";
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            calculateSalary();
        } else if (e.getSource() == processButton) {
            processSalary();
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new Salary();
        }
    }

    public static void main(String[] args) {
        // Example instantiation for testing purposes
        new CalculateSalary("1");
    }
}
