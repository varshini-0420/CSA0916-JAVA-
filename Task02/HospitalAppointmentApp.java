import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HospitalAppointmentApp extends JFrame {

    JComboBox<String> departmentBox;
    JComboBox<String> doctorBox;
    JTextField patientField;
    JTextField dateField;
    JLabel statusLabel;

    HospitalAppointmentApp() {

        setTitle("Hospital Appointment Booking");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel(
                "HOSPITAL OUTPATIENT APPOINTMENT",
                JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));

        form.add(new JLabel("Patient Name:"));
        patientField = new JTextField();
        form.add(patientField);

        form.add(new JLabel("Department:"));

        String[] departments = {
                "Select Department",
                "Cardiology",
                "Neurology",
                "Orthopaedics",
                "Dermatology"
        };

        departmentBox = new JComboBox<>(departments);
        form.add(departmentBox);

        form.add(new JLabel("Doctor:"));
        doctorBox = new JComboBox<>();
        doctorBox.addItem("Select Doctor");
        form.add(doctorBox);

        form.add(new JLabel("Appointment Date (DD-MM-YYYY):"));
        dateField = new JTextField();
        form.add(dateField);

        JButton bookButton = new JButton("Book Appointment");
        JButton clearButton = new JButton("Clear");

        form.add(bookButton);
        form.add(clearButton);

        add(form, BorderLayout.CENTER);

        statusLabel = new JLabel("Enter appointment details.",
                JLabel.CENTER);

        add(statusLabel, BorderLayout.SOUTH);

        // Refresh doctors when department changes
        departmentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                doctorBox.removeAllItems();

                String department =
                        (String) departmentBox.getSelectedItem();

                if (department == null ||
                        department.equals("Select Department")) {

                    doctorBox.addItem("Select Doctor");

                } else if (department.equals("Cardiology")) {

                    doctorBox.addItem("Dr. Arun");
                    doctorBox.addItem("Dr. Priya");

                } else if (department.equals("Neurology")) {

                    doctorBox.addItem("Dr. Kumar");
                    doctorBox.addItem("Dr. Meena");

                } else if (department.equals("Orthopaedics")) {

                    doctorBox.addItem("Dr. Ravi");
                    doctorBox.addItem("Dr. Divya");

                } else if (department.equals("Dermatology")) {

                    doctorBox.addItem("Dr. Anitha");
                    doctorBox.addItem("Dr. Suresh");
                }
            }
        });

        // Book appointment
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String patient = patientField.getText().trim();
                String department =
                        (String) departmentBox.getSelectedItem();
                String doctor =
                        (String) doctorBox.getSelectedItem();
                String date = dateField.getText().trim();

                if (patient.isEmpty()) {
                    showError("Enter patient name.");
                    return;
                }

                if (department == null ||
                        department.equals("Select Department")) {
                    showError("Select a department.");
                    return;
                }

                if (doctor == null ||
                        doctor.equals("Select Doctor")) {
                    showError("Select a doctor.");
                    return;
                }

                if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    showError("Enter date in DD-MM-YYYY format.");
                    return;
                }

                String message =
                        "Appointment Booked Successfully!\n"
                        + "Patient: " + patient
                        + "\nDepartment: " + department
                        + "\nDoctor: " + doctor
                        + "\nDate: " + date;

                statusLabel.setText("Appointment booked successfully.");

                JOptionPane.showMessageDialog(
                        HospitalAppointmentApp.this,
                        message,
                        "Appointment Confirmation",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // Clear fields
        clearButton.addActionListener(e -> {
            patientField.setText("");
            departmentBox.setSelectedIndex(0);
            doctorBox.removeAllItems();
            doctorBox.addItem("Select Doctor");
            dateField.setText("");
            statusLabel.setText("Enter appointment details.");
        });

        setVisible(true);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Validation Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new HospitalAppointmentApp()
        );
    }
}