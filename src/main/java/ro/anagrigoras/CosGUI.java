package ro.anagrigoras;

import javax.swing.*;
import java.awt.*;

public class CosGUI {
    JPanel panel;

    CosGUI(Film film, String data, String ora, String sala, String rand, String loc, String pret) {

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        JLabel filmLabel = new JLabel("Film: " + film.getTitlu());
        panel.add(filmLabel);
        JLabel dataLabel = new JLabel("Data: " + data);
        panel.add(dataLabel);
        JLabel oraLabel = new JLabel("Ora: " + ora);
        panel.add(oraLabel);
        JLabel salaLabel = new JLabel("Sala: " + sala);
        panel.add(salaLabel);
        JLabel randLabel = new JLabel("Rand: " + rand);
        panel.add(randLabel);
        JLabel locLabel = new JLabel("Loc: " + loc);
        panel.add(locLabel);
        JLabel pretLabel = new JLabel("Pret: " + pret);
        panel.add(pretLabel);
        JButton buton = new JButton("Plateste!");
        buton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Plata a fost efectuata cu succes!");
            panel.removeAll();
            panel.revalidate();
            panel.repaint();
            panel.add(new JLabel("Cosul este gol!"));
        });
        panel.add(buton);
        panel.setVisible(true);
    }

    JPanel getPanel() {
        return panel;
    }
}
