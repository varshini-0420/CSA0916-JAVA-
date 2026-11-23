import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverterApp extends JFrame implements ActionListener {

    JTextField amountField;
    JComboBox<String> fromCurrency, toCurrency;
    JLabel resultLabel;

    JButton frameButton, innerButton, anonymousButton, lambdaButton;

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setSize(550, 350);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);

        add(new JLabel("Enter Amount:"));
        amountField = new JTextField();
        add(amountField);

        String[] currencies = {"INR", "USD", "EUR"};
        add(new JLabel("From Currency:"));
        fromCurrency = new JComboBox<>(currencies);
        add(fromCurrency);

        add(new JLabel("To Currency:"));
        toCurrency = new JComboBox<>(currencies);
        add(toCurrency);

        frameButton = new JButton("Frame Listener");
        innerButton = new JButton("Inner Class");
        anonymousButton = new JButton("Anonymous Class");
        lambdaButton = new JButton("Lambda");

        add(frameButton);
        add(innerButton);
        add(anonymousButton);
        add(lambdaButton);

        resultLabel = new JLabel("Conversion result will appear here.");
        add(resultLabel);

        frameButton.addActionListener(this);
        innerButton.addActionListener(new InnerListener());

        anonymousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        lambdaButton.addActionListener(e -> convertCurrency());

        amountField.addActionListener(e -> convertCurrency());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(
                    CurrencyConverterApp.this,
                    "Thank you for using Currency Converter!"
                );
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        convertCurrency();
    }

    class InnerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            convertCurrency();
        }
    }

    void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                throw new NumberFormatException();
            }

            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double inINR;

            switch (from) {
                case "USD":
                    inINR = amount * 88;
                    break;
                case "EUR":
                    inINR = amount * 102;
                    break;
                default:
                    inINR = amount;
            }

            double result;

            switch (to) {
                case "USD":
                    result = inINR / 88;
                    break;
                case "EUR":
                    result = inINR / 102;
                    break;
                default:
                    result = inINR;
            }

            String message = String.format(
                "%.2f %s = %.2f %s",
                amount, from, result, to
            );

            resultLabel.setText(message);
            JOptionPane.showMessageDialog(this, message);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid positive amount."
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverterApp());
    }
}