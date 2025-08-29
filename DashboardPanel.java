package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class DashboardPanel extends JPanel {

    private BufferedImage backgroundImage;

    public DashboardPanel() {
        setLayout(new BorderLayout());

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("img/img6.jpg")); // Background image
        } catch (IOException e) {
            System.out.println("Background image not found.");
        }

        setOpaque(false); // So background is visible

        // === Top Title ===
        JLabel title = new JLabel("EduNexUs Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 64)); // Large title
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // === Center Grid for Info Cards ===
        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        centerPanel.setBackground(new Color(0, 0, 0, 0)); // Transparent background

        // --- Top Courses Box ---
        JPanel popularCourses = createCard("Top Courses", new String[]{
                "Java Programming", "Database Systems", "Software Engineering", "Web development", "Data structures"
        }, new Color(255, 249, 230));

        // --- Activity Overview Box ---
        JPanel currentActivity = createCard("Activity Overview", new String[]{
                "Total Students: 12K+", "Courses Completed: 45K+", "Certificates Issued: 9K+"
        }, new Color(230, 248, 255));
        currentActivity.add(createChartPanel(), BorderLayout.SOUTH);

        // --- Featured Instructors Box ---
        JPanel instructors = createCard("Featured Instructors", new String[]{
                "Dr. Nimal Perera", "Ms. Anusha Silva", "Mr. Kausn Jayasooriya", "Prof. Chamara Fernado", "Mr. Saman Weerasinghe"
        }, new Color(230, 255, 250));

        centerPanel.add(popularCourses);
        centerPanel.add(currentActivity);
        centerPanel.add(instructors);

        add(centerPanel, BorderLayout.CENTER);

        // === Bottom Banner ===
        try {
            BufferedImage image = ImageIO.read(new File("img/dashboard_banner.png")); // Adjust path
            Image scaled = image.getScaledInstance(850, 300, Image.SCALE_SMOOTH); 
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
            add(imageLabel, BorderLayout.SOUTH);
        } catch (IOException e) {
            JLabel error = new JLabel("Banner image not found", SwingConstants.CENTER);
            error.setFont(new Font("Segoe UI", Font.BOLD, 20));
            error.setForeground(Color.RED);
            error.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
            add(error, BorderLayout.SOUTH);
        }
    }

    // === Create Card Panel ===
    private JPanel createCard(String title, String[] items, Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setPreferredSize(new Dimension(100, 120)); // Smaller box size

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 36)); // Bold and large
        heading.setForeground(new Color(33, 43, 54));
        panel.add(heading, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(bgColor);

        for (String item : items) {
            JLabel label = new JLabel("• " + item);
            label.setFont(new Font("Segoe UI", Font.BOLD, 30)); // Bold and large
            label.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
            contentPanel.add(label);
        }

        panel.add(contentPanel, BorderLayout.CENTER);
        return panel;
    }

    // === Chart Drawing Panel ===
    private JPanel createChartPanel() {
        JPanel chart = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(93, 173, 226)); // Light blue bars
                g.fillRect(10, getHeight() - 30, 15, 25);
                g.fillRect(35, getHeight() - 60, 15, 55);
                g.fillRect(60, getHeight() - 45, 15, 40);
                g.fillRect(85, getHeight() - 75, 15, 65);
            }
        };
        chart.setPreferredSize(new Dimension(150, 100));
        chart.setBackground(Color.WHITE);
        chart.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        return chart;
    }

    // === Background Image Drawer ===
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
