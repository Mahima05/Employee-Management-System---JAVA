package Employee_Management_System.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField educationField, guardianNameField, guardianContactField, salaryField, addressField, phoneField, emailField, designationField;
    JLabel lblAadhar, lbl_empId, lblName, lblDob;
    JButton updateButton, backButton;
    String empId;

    UpdateEmployee(String empId) {
        this.empId = empId;

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
        ImagePanel contentPane = new ImagePanel("/Employee_Management_System/Images/Frame.jpg");
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel headingLabel = new JLabel("Update Employee Detail");
        headingLabel.setBounds(120, 35, 500, 50);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
        headingLabel.setForeground(Color.WHITE);
        add(headingLabel);

        JLabel employeeNameLabel = new JLabel("Name:");
        employeeNameLabel.setBounds(30, 150, 150, 30);
        employeeNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        employeeNameLabel.setForeground(Color.WHITE);
        add(employeeNameLabel);

        lblName = new JLabel();
        lblName.setBounds(250, 150, 200, 30);
        lblName.setForeground(Color.WHITE);
        lblName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(lblName);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(520, 150, 200, 30);
        dobLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        dobLabel.setForeground(Color.WHITE);
        add(dobLabel);

        lblDob = new JLabel();
        lblDob.setBounds(760, 150, 200, 30);
        lblDob.setForeground(Color.WHITE);
        lblDob.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(lblDob);

        JLabel guardianNameLabel = new JLabel("Guardian's Name:");
        guardianNameLabel.setBounds(30, 200, 200, 30);
        guardianNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        guardianNameLabel.setForeground(Color.WHITE);
        add(guardianNameLabel);

        guardianNameField = new JTextField();
        guardianNameField.setBounds(250, 200, 200, 30);
        guardianNameField.setForeground(Color.WHITE);
        guardianNameField.setOpaque(false);
        guardianNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(guardianNameField);

        JLabel guardianContactLabel = new JLabel("Guardian's Contact:");
        guardianContactLabel.setBounds(520, 200, 200, 30);
        guardianContactLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        guardianContactLabel.setForeground(Color.WHITE);
        add(guardianContactLabel);

        guardianContactField = new JTextField();
        guardianContactField.setBounds(760, 200, 200, 30);
        guardianContactField.setForeground(Color.WHITE);
        guardianContactField.setOpaque(false);
        guardianContactField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(guardianContactField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(30, 250, 200, 30);
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        salaryLabel.setForeground(Color.WHITE);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(250, 250, 200, 30);
        salaryField.setForeground(Color.WHITE);
        salaryField.setOpaque(false);
        salaryField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(salaryField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(520, 250, 200, 30);
        addressLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        addressLabel.setForeground(Color.WHITE);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(760, 250, 200, 30);
        addressField.setForeground(Color.WHITE);
        addressField.setOpaque(false);
        addressField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(addressField);

        JLabel phoneLabel = new JLabel("Contact:");
        phoneLabel.setBounds(30, 300, 200, 30);
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        phoneLabel.setForeground(Color.WHITE);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250, 300, 200, 30);
        phoneField.setForeground(Color.WHITE);
        phoneField.setOpaque(false);
        phoneField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(520, 300, 200, 30);
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        emailLabel.setForeground(Color.WHITE);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(760, 300, 200, 30);
        emailField.setForeground(Color.WHITE);
        emailField.setOpaque(false);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(emailField);

        JLabel educationLabel = new JLabel("Highest Education:");
        educationLabel.setBounds(30, 350, 200, 30);
        educationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        educationLabel.setForeground(Color.WHITE);
        add(educationLabel);

        educationField = new JTextField();
        educationField.setBounds(250, 350, 200, 30);
        educationField.setForeground(Color.WHITE);
        educationField.setOpaque(false);
        educationField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(educationField);

        JLabel designationLabel = new JLabel("Designation:");
        designationLabel.setBounds(520, 350, 200, 30);
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        designationLabel.setForeground(Color.WHITE);
        add(designationLabel);

        designationField = new JTextField();
        designationField.setBounds(760, 350, 200, 30);
        designationField.setForeground(Color.WHITE);
        designationField.setOpaque(false);
        designationField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(designationField);

        JLabel aadharLabel = new JLabel("Aadhar Number:");
        aadharLabel.setBounds(30, 400, 200, 30);
        aadharLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        aadharLabel.setForeground(Color.WHITE);
        add(aadharLabel);

        lblAadhar = new JLabel();
        lblAadhar.setBounds(250, 400, 200, 30);
        lblAadhar.setForeground(Color.WHITE);
        lblAadhar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(lblAadhar);

        JLabel empIdLabel = new JLabel("Employee ID:");
        empIdLabel.setBounds(520, 400, 200, 30);
        empIdLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        empIdLabel.setForeground(Color.WHITE);
        add(empIdLabel);

        lbl_empId = new JLabel();
        lbl_empId.setBounds(760, 400, 200, 30);
        lbl_empId.setForeground(Color.WHITE);
        lbl_empId.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(lbl_empId);


        try {
            Connect c = new Connect();
            String query = "SELECT * FROM employee_info WHERE empId='" + empId + "';";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                lblName.setText(rs.getString("name"));
                lblDob.setText(rs.getString("dob"));
                guardianNameField.setText(rs.getString("guardianName"));
                guardianContactField.setText(rs.getString("guardianContact"));
                salaryField.setText(rs.getString("salary"));
                addressField.setText(rs.getString("address"));
                phoneField.setText(rs.getString("phone"));
                emailField.setText(rs.getString("email"));
                educationField.setText(rs.getString("educationLevel"));
                designationField.setText(rs.getString("designation"));
                lblAadhar.setText(rs.getString("aadhar"));
                lbl_empId.setText(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateButton = new GradientButton("Update Details", "#273D41", "#659CA7");
        updateButton.setBounds(300, 500, 200, 40);
        updateButton.addActionListener(this);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(updateButton);

        backButton = new GradientButton("Back", "#000", "#000");
        backButton.setBounds(600, 500, 200, 40);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(backButton);

        setSize(1200, 600);
        setLocation(150, 100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateButton) {
            String guardianName = guardianNameField.getText();
            String guardianContact = guardianContactField.getText();
            String salary = salaryField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String education = educationField.getText();
            String designation = designationField.getText();

            try {
                Connect conn = new Connect();
                String query = "UPDATE employee_info SET guardianName='" + guardianName + "',guardianContact='" + guardianContact + "',salary=" + salary + ",address='" + address + "',phone=" + phone + ",email='" + email + "',educationLevel='" + education + "',designation='" + designation + "' WHERE empId='" + empId + "';";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("error");
    }
}