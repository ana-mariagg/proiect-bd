package ro.anagrigoras;

import javax.swing.*;
import java.awt.*;

public class Contact {

    private JPanel panel;

    Contact() {
        panel = new JPanel(new GridLayout(3, 2));

        JLabel localizare = new JLabel("Localizare: ");
        localizare.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(localizare);
        JLabel localizare2 = new JLabel("Strada Republicii 3, Municipiul Brasov");
        localizare2.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(localizare2);

        JLabel telefon = new JLabel("Telefon: ");
        telefon.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(telefon);
        JLabel telefon2 = new JLabel("0234589657");
        telefon2.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(telefon2);

        JLabel email = new JLabel("Email: ");
        email.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(email);
        JLabel email2 = new JLabel("office@cinema.capitol.ro");
        email2.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(email2);
    }

    JPanel getPanel() {
        return panel;
    }
}
