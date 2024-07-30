package Employee_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox showPasswordCheckBox;

    Login() {
        setContentPane(new ImagePanel2());
        setLayout(null);
        setResizable(false);

        // Add logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/Employee_Management/Images/logo.png"));
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logoIcon);
        logoLabel.setBounds(750, 5, 190, 150);
        add(logoLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(700, 180, 200, 50);
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        usernameLabel.setForeground(Color.WHITE);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(700, 220, 200, 40);
        usernameField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        usernameField.setBackground(getBackground());
        usernameField.setOpaque(false);
        usernameField.setForeground(Color.WHITE); // Set the color of the entered text
        add(usernameField);


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(700, 270, 200, 50);
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(700, 312, 200, 40);
        passwordField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
        passwordField.setBackground(getBackground());
        passwordField.setForeground(Color.WHITE);
        passwordField.setOpaque(false);
        passwordField.setEchoChar('*');
        add(passwordField);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(700, 360, 150, 30);
        showPasswordCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.setOpaque(false);
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });
        add(showPasswordCheckBox);

        loginButton = new JButton("LOGIN") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 32));
        loginButton.setBounds(830, 450, 200, 50);
        loginButton.addActionListener(this);
        add(loginButton);

        setSize(1200, 600);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            Connect connection = new Connect();
            if (connection.s != null) {
                String query = "SELECT * FROM Login WHERE Username = ? AND Password = ?";
                PreparedStatement pstmt = connection.c.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
                rs.close();
                pstmt.close();
            } else {
                JOptionPane.showMessageDialog(null, "Database Connection Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

class ImagePanel2 extends JPanel {
    private ImageIcon imageIcon;

    public ImagePanel2() {
        imageIcon = new ImageIcon(getClass().getResource("/Employee_Management/Images/Img_2.jpg"));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
