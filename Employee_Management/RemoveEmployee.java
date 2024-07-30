package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice empIdChoice;
    JButton deleteButton, backButton;

    RemoveEmployee() {
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

        ImagePanel contentPane = new ImagePanel("/Employee_Management/Images/Frame.jpg");
        contentPane.setLayout(null);
        setContentPane(contentPane);

        setLayout(null);
        setSize(1200, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(150, 100);

        JLabel empIdLabel = new JLabel("Employee ID:");
        empIdLabel.setBounds(310, 80, 200, 50);
        empIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        empIdLabel.setForeground(Color.WHITE);  // Set font color
        add(empIdLabel);

        empIdChoice = new Choice();
        empIdChoice.setBounds(590, 90, 100, 50);
        empIdChoice.setFont(new Font("Times New Roman", Font.PLAIN, 25)); // Set the font
        add(empIdChoice);

        try {
            Connect c = new Connect();
            String query = "SELECT * FROM employee_info;";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                empIdChoice.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(400, 150, 150, 50);
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        nameLabel.setForeground(Color.WHITE);  // Set font color
        add(nameLabel);

        JLabel lblName = new JLabel();
        lblName.setBounds(590, 150, 250, 50);
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblName.setForeground(Color.WHITE);  // Set font color
        add(lblName);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(400, 225, 150, 50);
        phoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        phoneLabel.setForeground(Color.WHITE);  // Set font color
        add(phoneLabel);

        JLabel lblPhone = new JLabel();
        lblPhone.setBounds(590, 225, 250, 50);
        lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblPhone.setForeground(Color.WHITE);  // Set font color
        add(lblPhone);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(400, 300, 150, 50);
        emailLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        emailLabel.setForeground(Color.WHITE);  // Set font color
        add(emailLabel);

        JLabel lblEmail = new JLabel();
        lblEmail.setBounds(590, 300, 450, 50);
        lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblEmail.setForeground(Color.WHITE);  // Set font color
        add(lblEmail);

        try {
            Connect c = new Connect();
            String query = "SELECT * FROM employee_info WHERE empId='" + empIdChoice.getSelectedItem() + "';";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                lblName.setText(rs.getString("name"));
                lblPhone.setText(rs.getString("phone"));
                lblEmail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        empIdChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Connect c = new Connect();
                    String query = "SELECT * FROM employee_info WHERE empId='" + empIdChoice.getSelectedItem() + "';";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        lblName.setText(rs.getString("name"));
                        lblPhone.setText(rs.getString("phone"));
                        lblEmail.setText(rs.getString("email"));
                    }
                } catch (Exception ie) {
                    ie.printStackTrace();
                }
            }
        });

        deleteButton = new GradientButton("Remove", "#8b0000", "#8b0000"); // Example hex codes
        deleteButton.setBounds(300, 450, 200, 40);
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new GradientButton("Back", "#000", "#000"); // Example hex codes
        backButton.setBounds(600, 450, 200, 40);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            try {
                Connect c = new Connect();
                String query = "DELETE FROM employee_info WHERE empId='" + empIdChoice.getSelectedItem() + "';";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Information deleted successfully");
                setVisible(false);
                new Home();
            } catch (Exception x) {
                x.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}