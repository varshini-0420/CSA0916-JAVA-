import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// SERVICE CLASS - Handles payment validation and processing
class FeeService {

    public String processPayment(String name, String regNo,
                                 String amountText, String paymentMode) {

        if (name.trim().isEmpty()) {
            return "Error: Student name cannot be empty.";
        }

        if (regNo.trim().isEmpty()) {
            return "Error: Register number cannot be empty.";
        }

        if (amountText.trim().isEmpty()) {
            return "Error: Fee amount cannot be empty.";
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException e) {
            return "Error: Enter a valid fee amount.";
        }

        if (amount <= 0) {
            return "Error: Fee amount must be greater than zero.";
        }

        if (paymentMode == null || paymentMode.isEmpty()) {
            return "Error: Select a payment mode.";
        }

        return "Payment Successful!\n"
                + "Student Name: " + name
                + "\nRegister Number: " + regNo
                + "\nAmount: Rs. " + amount
                + "\nPayment Mode: " + paymentMode;
    }
}

// GUI CLASS - Handles the user interface and events
public class FeePaymentApp extends JFrame {

    JTextField nameField, regField, amountField;
    JComboBox<String> paymentMode;
    JLabel statusLabel;

    String lastReceipt = "";

    FeeService feeService = new FeeService();

    FeePaymentApp() {

        setTitle("Student Fee Payment System");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "STUDENT FEE PAYMENT",
                JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Register Number:"));
        regField = new JTextField();
        formPanel.add(regField);

        formPanel.add(new JLabel("Fee Amount:"));
        amountField = new JTextField();
        formPanel.add(amountField);

        formPanel.add(new JLabel("Payment Mode:"));
        String[] modes = {"Select", "UPI", "Card", "Cash"};
        paymentMode = new JComboBox<>(modes);
        formPanel.add(paymentMode);

        JButton payButton = new JButton("Pay Fee");
        JButton receiptButton = new JButton("View Receipt");

        formPanel.add(payButton);
        formPanel.add(receiptButton);

        add(formPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Enter student payment details.",
                JLabel.CENTER);

        add(statusLabel, BorderLayout.SOUTH);

        // Separate listener for Pay button
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String result = feeService.processPayment(
                        nameField.getText(),
                        regField.getText(),
                        amountField.getText(),
                        (String) paymentMode.getSelectedItem()
                );

                statusLabel.setText(result.replace("\n", " | "));

                if (result.startsWith("Payment Successful!")) {
                    lastReceipt = result;
                    JOptionPane.showMessageDialog(
                            FeePaymentApp.this,
                            result,
                            "Payment Status",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            FeePaymentApp.this,
                            result,
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Separate listener for Receipt button
        receiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (lastReceipt.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            FeePaymentApp.this,
                            "No payment receipt available.",
                            "Receipt",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            FeePaymentApp.this,
                            lastReceipt,
                            "Payment Receipt",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new FeePaymentApp();
        });
    }
}