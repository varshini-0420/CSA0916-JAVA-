import javax.swing.*;
import java.awt.*;

public class LibrarySearchApp extends JFrame {

    JTextField searchField;
    JComboBox<String> categoryBox;
    JTextArea resultsArea;
    JLabel statusLabel;

    public LibrarySearchApp() {

        setTitle("Campus Library Search System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // TOP PANEL
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
                "CAMPUS LIBRARY SEARCH",
                JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 22));
        topPanel.add(title, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout());

        searchField = new JTextField(20);

        String[] categories = {
                "All Categories",
                "Programming",
                "Database",
                "Networking",
                "Mathematics"
        };

        categoryBox = new JComboBox<>(categories);

        JButton searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(categoryBox);
        searchPanel.add(searchButton);

        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // CENTER RESULTS AREA
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(resultsArea);

        add(scrollPane, BorderLayout.CENTER);

        // BOTTOM PANEL
        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton reserveButton = new JButton("Reserve");

        bottomPanel.add(prevButton);
        bottomPanel.add(nextButton);
        bottomPanel.add(reserveButton);

        // STATUS LABEL
        statusLabel = new JLabel(
                "Welcome! Search for library books."
        );

        JPanel footer = new JPanel(new BorderLayout());
        footer.add(bottomPanel, BorderLayout.CENTER);
        footer.add(statusLabel, BorderLayout.SOUTH);

        add(footer, BorderLayout.SOUTH);

        // SEARCH BUTTON EVENT
        searchButton.addActionListener(e -> {

            String keyword = searchField.getText().trim();
            String category = (String) categoryBox.getSelectedItem();

            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter a book name or keyword."
                );
                return;
            }

            resultsArea.setText(
                    "SEARCH RESULTS\n\n"
                    + "1. Java Programming\n"
                    + "2. Advanced Java Concepts\n"
                    + "3. Java Application Development\n\n"
                    + "Keyword: " + keyword
                    + "\nCategory: " + category
            );

            statusLabel.setText("Search completed successfully.");
        });

        // PREVIOUS BUTTON EVENT
        prevButton.addActionListener(e -> {
            statusLabel.setText("Previous page selected.");
        });

        // NEXT BUTTON EVENT
        nextButton.addActionListener(e -> {
            statusLabel.setText("Next page selected.");
        });

        // RESERVE BUTTON EVENT
        reserveButton.addActionListener(e -> {

            if (resultsArea.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please search for a book first."
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Book reservation request submitted!"
                );

                statusLabel.setText("Reservation request submitted.");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LibrarySearchApp();
        });
    }
}