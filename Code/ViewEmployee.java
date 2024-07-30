package Employee_Management;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class ViewEmployee extends JFrame implements ActionListener {

    private JTable table;
    private Choice employeeIdChoice;
    private JButton searchButton, printButton, updateButton, backButton;

    ViewEmployee() {
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

        JLabel searchLabel = new JLabel("Search by Employee Id");
        searchLabel.setBounds(20, 20, 150, 30);
        searchLabel.setForeground(Color.WHITE);  // Set text color to white
        contentPane.add(searchLabel);

        employeeIdChoice = new Choice();
        employeeIdChoice.setBounds(180, 20, 150, 30);
        add(employeeIdChoice);

        try {
            Connect connection = new Connect();
            ResultSet rs = connection.s.executeQuery("SELECT * FROM employee_info");  // statement lega sql se
            while (rs.next()) {
                employeeIdChoice.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        table.setOpaque(false);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
        table.setShowGrid(true); // Show grid lines
        table.setGridColor(Color.WHITE); // Set grid line color to white
        table.setForeground(Color.WHITE); // Set font color for table cells

        // Set table header transparent
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setOpaque(false);
        tableHeader.setBackground(new Color(0, 0, 0, 0));
        tableHeader.setForeground(Color.BLACK); // Set header font color

        try {
            Connect connection = new Connect();
            ResultSet rs = connection.s.executeQuery("SELECT * FROM employee_info");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 1180, 458);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);

        searchButton = new GradientButton("Search", "#273D41", "#659CA7"); // Example hex codes
        searchButton.setBounds(20, 70, 150, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        printButton = new GradientButton("Print", "#273D41", "#659CA7"); // Example hex codes
        printButton.setBounds(180, 70, 150, 30);
        printButton.addActionListener(this);
        add(printButton);

        updateButton = new GradientButton("Update", "#273D41", "#659CA7"); // Example hex codes
        updateButton.setBounds(340, 70, 150, 30);
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new GradientButton("Back", "#273D41", "#659CA7"); // Example hex codes
        backButton.setBounds(500, 70, 150, 30);
        backButton.addActionListener(this);
        add(backButton);

        setSize(1210, 600);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String query = "SELECT * FROM employee_info WHERE empId = '" + employeeIdChoice.getSelectedItem() + "'";
            try {
                Connect connection = new Connect();
                ResultSet rs = connection.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));  // sql ke andar ek package hai dbutils
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == updateButton) {
            setVisible(false);
            new UpdateEmployee(employeeIdChoice.getSelectedItem());
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}