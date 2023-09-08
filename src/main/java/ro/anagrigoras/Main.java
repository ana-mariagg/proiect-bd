package ro.anagrigoras;

import javax.swing.*;
import java.sql.*;

public class Main {

    private static void initUI() throws Exception {
        new LoginGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() //new Thread()
        {
            public void run() {
                try {
                    initUI();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}