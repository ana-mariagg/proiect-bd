package ro.anagrigoras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterGUI extends JFrame {

    JPanel panel;
    JLabel user_label, password_label, message, firstName_label, lastName_label, nr_telefon_label, tip_client_label;
    JTextField userName_text, firstName_text, lastName_text, nr_telefon_text, tip_client_text;
    JPasswordField password_text;
    JButton submit, cancel;

    RegisterGUI() {
        //First Name label
        firstName_label = new JLabel();
        firstName_label.setText("Nume: ");
        firstName_text = new JTextField();
        //Last Name label
        lastName_label = new JLabel();
        lastName_label.setText("Prenume: ");
        lastName_text = new JTextField();
        //Telephone label
        nr_telefon_label = new JLabel();
        nr_telefon_label.setText("Numar de telefon: ");
        nr_telefon_text = new JTextField();
        //Client type label
        tip_client_label = new JLabel();
        tip_client_label.setText("Tip client: ");
        tip_client_text = new JTextField();
        // Username Label
        user_label = new JLabel();
        user_label.setText("User Name: ");
        userName_text = new JTextField();
        // Password Label
        password_label = new JLabel();
        password_label.setText("Parola: ");
        password_text = new JPasswordField();
        // Submit
        submit = new JButton("Register");
        submit.addActionListener(e -> {
            dispose();
            DBFunctions db;
            try {
                db = new DBFunctions();
                db.connect_to_db();
                Connection connection = db.getCon();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO \"user\" (\"nume\", \"prenume\", \"username\", \"nr_telefon\", \"tip_client\", \"active\", \"parola\") VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, lastName_text.getText());
                ps.setString(2, firstName_text.getText());
                ps.setString(3, userName_text.getText());
                ps.setString(4, nr_telefon_text.getText());
                ps.setString(5, tip_client_text.getText());
                ps.setBoolean(6, true);
                ps.setString(7, password_text.toString());
                int rows_updated = ps.executeUpdate();
                if (rows_updated == 1) {
                    User user = new User(
                            lastName_text.getText(),
                            firstName_text.getText(),
                            userName_text.getText(),
                            nr_telefon_text.getText(),
                            tip_client_text.getText(),
                            0,
                            null);
                    JOptionPane.showMessageDialog(null, "Utilizator inregistrat cu succes!");
                    new App(user);
                } else {
                    message.setText("Inregistrare esuata!");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel = new JPanel(new GridLayout(7, 1));
        panel.add(firstName_label);
        panel.add(firstName_text);
        panel.add(lastName_label);
        panel.add(lastName_text);
        panel.add(nr_telefon_label);
        panel.add(nr_telefon_text);
        panel.add(tip_client_label);
        panel.add(tip_client_text);
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel, BorderLayout.CENTER);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Cinema Capitol");
        setSize(700, 500);
        setVisible(true);
    }
}
