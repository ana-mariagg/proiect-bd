package ro.anagrigoras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginGUI {

    JFrame frame;
    JPanel panel;
    JLabel userLabel;
    JLabel passwordLabel;

    User user;

    LoginGUI() throws SQLException {

        frame = new JFrame("Cinema Capitol");
        frame.setBounds(50, 50, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        panel.setLayout(null);

        userLabel = new JLabel("User name:");
        userLabel.setBounds(10, 20, 80, 30);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Parola:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        boolean success = false;
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(10, 80, 80, 25);
        submitButton.addActionListener(e -> {
            DBFunctions db;
            ResultSet rs = null;
            try {
                db = new DBFunctions();
                db.connect_to_db();
                Connection connect = db.getCon();
                PreparedStatement ps = connect.prepareStatement("SELECT * FROM \"user\" WHERE \"username\" = ? AND \"parola\" = ?");
                ps.setString(1, userText.getText());
                ps.setString(2, passwordText.getText());
                rs = ps.executeQuery();
                //make a query to select the list of films and tickets for the user
                if (rs.next()) {
                    PreparedStatement ps2 = connect.prepareStatement("SELECT * FROM \"bilet\" WHERE \"user_id\" = ?");
                    ps2.setInt(1, rs.getInt("id_user"));
                    ResultSet rs2 = ps2.executeQuery();
                    rs2.next();
                    PreparedStatement ps3 = connect.prepareStatement("SELECT * FROM \"film\" WHERE \"id_film\" = ?");
                    ps3.setInt(1, rs2.getInt("id_film"));
                    ResultSet rs3 = ps3.executeQuery();
                    rs3.next();
                    user = new User(
                            rs.getString("nume"),
                            rs.getString("prenume"),
                            rs.getString("username"),
                            rs.getString("nr_telefon"),
                            rs.getString("tip_client"),
                            rs2.getInt("id_bilet"),
                            rs3.getString("titlu"));
                    JOptionPane.showMessageDialog(null, "Bine ai venit!");
                    frame.dispose();
                    new App(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Username sau parola gresita!");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(90, 80, 100, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RegisterGUI();
            }
        });

        panel.add(submitButton);
        panel.add(registerButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public User getUser() {
        return user;
    }
}
