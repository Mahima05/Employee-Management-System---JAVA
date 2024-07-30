package Employee_Management_System.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FirstPage extends JFrame implements ActionListener {
    private JButton clickHereButton;
    private ImageIcon backgroundImage;
    private ImageIcon logoImage;

    public FirstPage() {
        setTitle("Employee Management");
        setSize(1200, 600);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/Employee_Management_System/Images/bg.jpg"));

        // Load the logo image
        logoImage = new ImageIcon(getClass().getResource("/Employee_Management_System/Images/logo.png"));

        // Create a custom panel to paint the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Use null layout to set bounds manually

        // Create and position the logo
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(logoImage);
        logoLabel.setBounds(450, 10, logoImage.getIconWidth(), logoImage.getIconHeight());
        backgroundPanel.add(logoLabel);

        // Create and position the welcome label
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 42));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(500, 200, 300, 40);
        backgroundPanel.add(welcomeLabel);

        // Create and position the Employee_managemnet_Sys label
        JLabel EmplymanageSys = new JLabel("Employee Managemant System");
        EmplymanageSys.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        EmplymanageSys.setForeground(Color.WHITE);
        EmplymanageSys.setBounds(300, 280, 1000, 80);
        backgroundPanel.add(EmplymanageSys);

        clickHereButton = new JButton("CLICK HERE TO LOGIN") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE); // Set the border color
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // Draw a rounded rectangle border
            }
        };
        clickHereButton.setOpaque(false);
        clickHereButton.setContentAreaFilled(false);
        clickHereButton.setBorder(BorderFactory.createEmptyBorder());
        clickHereButton.setForeground(Color.WHITE);
        clickHereButton.setFont(new Font("Arial", Font.BOLD, 18));
        clickHereButton.setBounds(441, 440, 300, 70);
        clickHereButton.addActionListener(this);
        backgroundPanel.add(clickHereButton);
        // Add the custom panel to the frame
        add(backgroundPanel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FirstPage());
    }
}