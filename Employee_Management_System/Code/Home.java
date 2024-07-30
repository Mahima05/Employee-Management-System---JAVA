package Employee_Management_System.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {
    GradientButton addButton, viewButton, removeButton, updateButton, logoutButton, attendanceButton, salary_trackerButton;

    Home() {
        setContentPane(new ImagePanel3());
        setLayout(null);
        setResizable(false);

        JLabel headingLabel = new JLabel("Dashboard");
        headingLabel.setBounds(120, 40, 800, 50);
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
        headingLabel.setForeground(Color.WHITE);
        add(headingLabel);

        addButton = new GradientButton("ADD EMPLOYEE", "#273D41", "#659CA7"); // Example hex codes
        addButton.setBounds(100, 150, 200, 50);
        addButton.addActionListener(this);
        add(addButton);

        viewButton = new GradientButton("VIEW EMPLOYEES", "#273D41", "#659CA7"); // Example hex codes
        viewButton.setBounds(350, 150, 200, 50);
        viewButton.addActionListener(this);
        add(viewButton);

        updateButton = new GradientButton("UPDATE EMPLOYEE", "#273D41", "#659CA7"); // Example hex codes
        updateButton.setBounds(100, 250, 200, 50);
        updateButton.addActionListener(this);
        add(updateButton);

        removeButton = new GradientButton("REMOVE EMPLOYEE", "#273D41", "#659CA7"); // Example hex codes
        removeButton.setBounds(350, 250, 200, 50);
        removeButton.addActionListener(this);
        add(removeButton);

        logoutButton = new GradientButton("LOG OUT ", "#0000", "#0000"); // Example hex codes
        logoutButton.setBounds(180, 450, 200, 50);
        logoutButton.addActionListener(this);
        add(logoutButton);

        attendanceButton = new GradientButton("ATTENDANCE", "#273D41", "#659CA7"); // Example hex codes
        attendanceButton.setBounds(100, 350, 200, 50);
        attendanceButton.addActionListener(this);
        add(attendanceButton);

        salary_trackerButton = new GradientButton("TRACK SALARY", "#273D41", "#659CA7"); // Example hex codes
        salary_trackerButton.setBounds(350, 350, 200, 50);
        salary_trackerButton.addActionListener(this);
        add(salary_trackerButton);

        setSize(1200, 600);
        setLocation(150, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == viewButton) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == updateButton) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == removeButton) {
            setVisible(false);
            new RemoveEmployee();
        } else if (ae.getSource() == logoutButton) {
            setVisible(false);
            new Login();
        } else if (ae.getSource() == attendanceButton){
            setVisible(false);
            new Attendance();
        } else if (ae.getSource() == salary_trackerButton){
            setVisible(false);
            new Salary();
        }
    }

    public static void main(String args[]) {
        new Home();
    }
}

class ImagePanel3 extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensure proper painting
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Employee_Management_System/Images/Frame1.jpg"));
        g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}

class GradientButton extends JButton {
    private Color startColor;
    private Color endColor;

    public GradientButton(String text, String startHex, String endHex) {
        super(text);
        this.startColor = Color.decode(startHex);
        this.endColor = Color.decode(endHex);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        int arcWidth = 30;
        int arcHeight = 30;
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, width, height, endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);
        g2d.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        int arcWidth = 30;
        int arcHeight = 30;
        g2d.setColor(getForeground());
        g2d.drawRoundRect(0, 0, width - 1, height - 1, arcWidth, arcHeight);
        g2d.dispose();
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }
}