package Employee_Management;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class AddEmployee extends JFrame implements ActionListener {
    private Random random = new Random();
    private int number = random.nextInt(9999);
    private JTextField nameField, guardianNameField, guardianContactField, salaryField, addressField, phoneField, emailField, designationField, aadharField;
    private JDateChooser dob;
    private JComboBox<String> education;
    private JLabel empIdLabel;
    private JButton addButton, backButton;

    AddEmployee() {

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

        JLabel headingLabel = new JLabel("Add Employee Detail");
        headingLabel.setBounds(120, 35, 500, 50);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setOpaque(false);
        add(headingLabel);

        JLabel employeeNameLabel = new JLabel("Name:");
        employeeNameLabel.setBounds(30, 150, 150, 30);
        employeeNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        employeeNameLabel.setForeground(Color.WHITE);
        employeeNameLabel.setOpaque(false);
        add(employeeNameLabel);

        nameField = new JTextField();
        nameField.setBounds(250, 150, 200, 30);
        nameField.setOpaque(false);
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(new Color(0, 0, 0, 0)); // Fully transparent background
        nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        ((AbstractDocument) nameField.getDocument()).setDocumentFilter(new AlphabeticFilter());
        add(nameField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(520, 150, 500, 30);
        dobLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        dobLabel.setForeground(Color.WHITE);
        dobLabel.setOpaque(false);
        add(dobLabel);

        dob = new JDateChooser();
        dob.setBounds(760, 150, 200, 30);
        dob.setOpaque(false);
        dob.setForeground(Color.WHITE);
        dob.setBackground(new Color(0, 0, 0, 0));
        dob.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(dob);

        JLabel guardianNameLabel = new JLabel("Guardian's Name:");
        guardianNameLabel.setBounds(30, 200, 400, 30);
        guardianNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        guardianNameLabel.setForeground(Color.WHITE);
        guardianNameLabel.setOpaque(false);
        add(guardianNameLabel);

        guardianNameField = new JTextField();
        guardianNameField.setBounds(250, 200, 200, 30);
        guardianNameField.setOpaque(false);
        guardianNameField.setForeground(Color.WHITE);
        guardianNameField.setBackground(new Color(0, 0, 0, 0));
        guardianNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        ((AbstractDocument) guardianNameField.getDocument()).setDocumentFilter(new AlphabeticFilter());
        add(guardianNameField);

        JLabel guardianContactLabel = new JLabel("Guardian's Contact:");
        guardianContactLabel.setBounds(520, 200, 200, 30);
        guardianContactLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        guardianContactLabel.setForeground(Color.WHITE);
        guardianContactLabel.setOpaque(false);
        add(guardianContactLabel);

        guardianContactField = new JTextField();
        guardianContactField.setBounds(760, 200, 200, 30);
        guardianContactField.setOpaque(false);
        guardianContactField.setForeground(Color.WHITE);
        guardianContactField.setBackground(new Color(0, 0, 0, 0));
        guardianContactField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        ((AbstractDocument) guardianContactField.getDocument()).setDocumentFilter(new DigitFilter(10));
        add(guardianContactField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(30, 250, 200, 30);
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        salaryLabel.setForeground(Color.WHITE);
        salaryLabel.setOpaque(false);
        add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(250, 250, 200, 30);
        salaryField.setOpaque(false);
        salaryField.setForeground(Color.WHITE);
        salaryField.setBackground(new Color(0, 0, 0, 0));
        salaryField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(salaryField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(520, 250, 200, 30);
        addressLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setOpaque(false);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(760, 250, 200, 30);
        addressField.setOpaque(false);
        addressField.setForeground(Color.WHITE);
        addressField.setBackground(new Color(0, 0, 0, 0));
        addressField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(addressField);

        JLabel phoneLabel = new JLabel("Contact:");
        phoneLabel.setBounds(30, 300, 200, 30);
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setOpaque(false);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250, 300, 200, 30);
        phoneField.setOpaque(false);
        phoneField.setForeground(Color.WHITE);
        phoneField.setBackground(new Color(0, 0, 0, 0));
        phoneField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        ((AbstractDocument) phoneField.getDocument()).setDocumentFilter(new DigitFilter(10));
        add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(520, 300, 200, 30);
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setOpaque(false);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(760, 300, 200, 30);
        emailField.setOpaque(false);
        emailField.setForeground(Color.WHITE);
        emailField.setBackground(new Color(0, 0, 0, 0));
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(emailField);

        JLabel educationLabel = new JLabel("Highest Education:");
        educationLabel.setBounds(30, 350, 200, 30);
        educationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        educationLabel.setForeground(Color.WHITE);
        educationLabel.setOpaque(false);
        add(educationLabel);

        String[] courses = {"12th", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        education = new JComboBox<>(courses);
        education.setBackground(Color.WHITE);
        education.setBounds(250, 350, 200, 30);
        add(education);

        JLabel designationLabel = new JLabel("Designation:");
        designationLabel.setBounds(520, 350, 200, 30);
        designationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        designationLabel.setForeground(Color.WHITE);
        designationLabel.setOpaque(false);
        add(designationLabel);

        designationField = new JTextField();
        designationField.setBounds(760, 350, 200, 30);
        designationField.setOpaque(false);
        designationField.setForeground(Color.WHITE);
        designationField.setBackground(new Color(0, 0, 0, 0));
        designationField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        add(designationField);

        JLabel aadharLabel = new JLabel("Aadhar Number:");
        aadharLabel.setBounds(30, 400, 200, 30);
        aadharLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        aadharLabel.setForeground(Color.WHITE);
        aadharLabel.setOpaque(false);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(250, 400, 200, 30);
        aadharField.setOpaque(false);
        aadharField.setForeground(Color.WHITE);
        aadharField.setBackground(new Color(0, 0, 0, 0));
        aadharField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        ((AbstractDocument) aadharField.getDocument()).setDocumentFilter(new DigitFilter(12));
        add(aadharField);

        JLabel empIdLabel = new JLabel("Employee id:");
        empIdLabel.setBounds(520, 400, 200, 30);
        empIdLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        empIdLabel.setForeground(Color.WHITE);
        empIdLabel.setOpaque(false);
        add(empIdLabel);

        this.empIdLabel = new JLabel(" " + number + " ");
        this.empIdLabel.setBounds(800, 400, 200, 30);
        this.empIdLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        this.empIdLabel.setForeground(Color.WHITE);
        this.empIdLabel.setOpaque(false);
        add(this.empIdLabel);

        addButton = new GradientButton("Add Details", "#273D41", "#659CA7");
        addButton.setBounds(300, 500, 200, 40);
        addButton.addActionListener(this);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(addButton);

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
        if (ae.getSource() == addButton) {
            String name = nameField.getText();
            String guardianName = guardianNameField.getText();
            String guardianContact = guardianContactField.getText();
            String dob = ((JTextField) this.dob.getDateEditor().getUiComponent()).getText();
            String salary = salaryField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String educationLevel = (String) education.getSelectedItem();
            String designation = designationField.getText();
            String aadhar = aadharField.getText();
            String empId = empIdLabel.getText();

            try {
                Connect connection = new Connect();
                String query = "INSERT INTO employee_info VALUES('" + empId + "','" + name + "','" + guardianName + "','" + guardianContact + "','" + dob + "'," + salary + ",'" + address + "','" + phone + "','" + email + "','" + educationLevel + "','" + designation + "'," + aadhar + ")";
                connection.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully");
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
        new AddEmployee();
    }

    // DigitFilter class to enforce digit-only input with a maximum length
    class DigitFilter extends DocumentFilter {
        private int maxLength;

        public DigitFilter(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }
            if ((fb.getDocument().getLength() + string.length()) <= maxLength && string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            if ((fb.getDocument().getLength() + text.length() - length) <= maxLength && text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
    // AlphabeticFilter class to enforce alphabetic-only input
    class AlphabeticFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }
            if (string.matches("[a-zA-Z ]*")) {  // Allows alphabets and space only
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            if (text.matches("[a-zA-Z ]*")) {  // Allows alphabets and space only
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

}
