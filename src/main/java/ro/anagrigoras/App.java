package ro.anagrigoras;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class App extends Canvas {

    private JFrame frame;
    private static JPanel mainMenuPanel;

    private static JPanel filme;
    private static JPanel contact;
    private static JPanel profil;
    private static JPanel cos;
    private static JPanel preturi;
    private static JTabbedPane tabbedPane;
    private User user;


    App(User user) throws SQLException {
        frame = new JFrame();
        frame.setTitle("Cinema Capitol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        tabbedPane = new JTabbedPane();

        GridBagConstraints imageConstraints = new GridBagConstraints();
        imageConstraints.gridwidth = GridBagConstraints.REMAINDER;
        imageConstraints.anchor = GridBagConstraints.CENTER;
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(new Color(147, 112, 219));
        GridBagLayout layout = new GridBagLayout();
        mainMenuPanel.setLayout(layout);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("CinemaCapitol.png")));
        JLabel logoLabel = new JLabel(logo);
        mainMenuPanel.add(logoLabel, imageConstraints);

        this.user = user;

        //Profil
        {
            profil = new JPanel();
            profil.setLayout(new GridLayout(7, 1));
            profil.setBackground(new Color(147, 112, 219));
            JLabel numeLabel = new JLabel();
            numeLabel.setFont(new Font("Serif", Font.BOLD, 20));
            numeLabel.setText("Nume: ");
            JLabel numeText = new JLabel(user.getNume());
            numeText.setFont(new Font("Serif", Font.PLAIN, 20));
            JLabel prenumeLabel = new JLabel();
            prenumeLabel.setFont(new Font("Serif", Font.BOLD, 20));
            prenumeLabel.setText("Prenume: ");
            JLabel prenumeText = new JLabel(user.getPrenume());
            prenumeText.setFont(new Font("Serif", Font.PLAIN, 20));
            JLabel nrTelefonLabel = new JLabel();
            nrTelefonLabel.setFont(new Font("Serif", Font.BOLD, 20));
            nrTelefonLabel.setText("Numar de telefon: ");
            JLabel nrTelefonText = new JLabel(user.getNrTelefon());
            nrTelefonText.setFont(new Font("Serif", Font.PLAIN, 20));
            JLabel tipClientLabel = new JLabel();
            tipClientLabel.setFont(new Font("Serif", Font.BOLD, 20));
            tipClientLabel.setText("Tip client: ");
            JLabel tipClientText = new JLabel(user.getTipClient());
            tipClientText.setFont(new Font("Serif", Font.PLAIN, 20));
            JLabel usernameLabel = new JLabel();
            usernameLabel.setFont(new Font("Serif", Font.BOLD, 20));
            usernameLabel.setText("Username: ");
            JLabel usernameText = new JLabel(user.getUserName());
            usernameText.setFont(new Font("Serif", Font.PLAIN, 20));
            JLabel listaFilmeLabel = new JLabel();
            usernameLabel.setFont(new Font("Serif", Font.BOLD, 20));
            usernameLabel.setText("Lista filme: ");

            profil.add(numeLabel);
            profil.add(numeText);
            profil.add(prenumeLabel);
            profil.add(prenumeText);
            profil.add(nrTelefonLabel);
            profil.add(nrTelefonText);
            profil.add(tipClientLabel);
            profil.add(tipClientText);
            profil.add(usernameLabel);
            profil.add(usernameText);
            var bilet = user.getBilete();
                JLabel listaFilmeText = new JLabel(String.valueOf(bilet));
                listaFilmeText.setFont(new Font("Serif", Font.PLAIN, 20));
                profil.add(listaFilmeText);
                var film = user.getFilme();
                JLabel listaFilmeText2 = new JLabel(film);
                listaFilmeText2.setFont(new Font("Serif", Font.PLAIN, 20));
                profil.add(listaFilmeText2);

        }

        //Filme
        {
            filme = new JPanel(new GridLayout(6, 4));
            filme.setBackground(new Color(147, 112, 219));
            DBFunctions db = new DBFunctions();
            db.connect_to_db();
            Connection connect = db.getCon();
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM \"film\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Film film = new Film(rs.getInt("id_film"), rs.getString("titlu"), rs.getString("gen"), rs.getString("data_aparitie"));
                JLabel titluText = new JLabel(film.getTitlu());
                titluText.setFont(new Font("Serif", Font.BOLD, 20));
                JLabel genText = new JLabel(film.getGen());
                genText.setFont(new Font("Serif", Font.PLAIN, 20));
                JLabel dataText = new JLabel(film.getDataAparitie());
                dataText.setFont(new Font("Serif", Font.PLAIN, 20));
                JButton buton = new JButton("Rezerva");
                buton.addActionListener(e -> {
                    try {
                        rezerevare(film);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
                filme.add(titluText);
                filme.add(genText);
                filme.add(dataText);
                filme.add(buton);
            }
        }


        contact = new JPanel();
        Contact contact1 = new Contact();
        contact = contact1.getPanel();
        contact.setBackground(new Color(147, 112, 219));

        cos = new JPanel();
        cos.setBackground(new Color(147, 112, 219));
        cos.add(new JLabel("Coșul este gol!"));
        //Pret
        {
            preturi = new JPanel();
            preturi.setBackground(new Color(147, 112, 219));
            DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Tip Client", "Preț"}, 0);

            tableModel.addRow(new Object[]{"Adult", "20 RON"});
            tableModel.addRow(new Object[]{"Copil/Elev", "10 RON"});
            tableModel.addRow(new Object[]{"Pensionar", "15 RON"});
            tableModel.addRow(new Object[]{"Student", "12 RON"});

            JTable table = new JTable();
            table.setModel(tableModel);
            table.setRowHeight(30);
            table.setFont(new Font("Serif", Font.PLAIN, 20));
            table.getTableHeader().setFont(new Font("Serif", Font.PLAIN, 20));
            table.setBackground(new Color(147, 112, 219));
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(300, 150));
            //preturi.add(table);
            preturi.add(scrollPane);
        }

        tabbedPane.setBounds(0, 0, 1366, 760);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.setBackground(new Color(147, 112, 219));
        tabbedPane.setForeground(Color.white);
        Font font = new Font("Verdana", Font.CENTER_BASELINE, 18);
        tabbedPane.setFont(font);

        tabbedPane.add("Acasă", mainMenuPanel);
        tabbedPane.add("Filme", filme);
        tabbedPane.add("Prețuri", preturi);
        tabbedPane.add("Contact", contact);
        tabbedPane.add("Profilul meu", profil);

        frame.add(tabbedPane);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    void rezerevare(Film film) throws SQLException {
        JFrame frame = new JFrame(film.getTitlu());
        frame.setSize(400, 400);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        DBFunctions dbFunctions = new DBFunctions();
        Connection connect = dbFunctions.connect_to_db();
        PreparedStatement ps = connect.prepareStatement("SELECT * FROM \"derulare\" WHERE \"id_film\" = ? ");
        ps.setInt(1, film.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            JLabel salaLabel = new JLabel("Sala: ");
            panel.add(salaLabel);
            JLabel salaText = new JLabel(String.valueOf(rs.getInt("id_sala")));
            panel.add(salaText);
            JLabel oraLabel = new JLabel("Ora: ");
            panel.add(oraLabel);
            JLabel oraText = new JLabel(rs.getString("ora"));
            panel.add(oraText);
            JLabel dataLabel1 = new JLabel("Data: ");
            panel.add(dataLabel1);
            JLabel dataText1 = new JLabel(rs.getString("data"));
            panel.add(dataText1);
            JLabel locuriLabel = new JLabel("Loc:");
            panel.add(locuriLabel);
            String[] locuri = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
            JComboBox<String> locuriComboBox = new JComboBox<>(locuri);
            panel.add(locuriComboBox);
            JLabel randuriLabel = new JLabel("Rand: ");
            panel.add(randuriLabel);
            String[] randuri = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
            JComboBox<String> randuriComboBox = new JComboBox<>(randuri);
            panel.add(randuriComboBox);
            JButton buton1 = new JButton("Rezerva!");
            buton1.addActionListener(e1 -> {
                try {
                    PreparedStatement ps2 = connect.prepareStatement("INSERT INTO \"bilet\" (\"id_derulare\", \"id_sala\", \"rand\", \"loc\", \"pret\") VALUES (?, ?, ?, ?, ?)");
                    ps2.setInt(1, rs.getInt("id_derulare"));
                    ps2.setInt(2, rs.getInt("id_sala"));
                    ps2.setString(3, (String) randuriComboBox.getSelectedItem());
                    ps2.setInt(4, Integer.parseInt((String) Objects.requireNonNull(locuriComboBox.getSelectedItem())));
                    int pret = switch (this.user.getTipClient()) {
                        case "Adult" -> 20;
                        case "Copil/Elev" -> 10;
                        case "Pensionar" -> 15;
                        case "Student" -> 12;
                        default -> 0;
                    };
                    ps2.setInt(5, pret);
                    ps2.executeUpdate();

                    CosGUI cosGUI = new CosGUI(film, rs.getString("data"), rs.getString("ora"), String.valueOf(rs.getInt("id_sala")),
                            (String) randuriComboBox.getSelectedItem(), (String) Objects.requireNonNull(locuriComboBox.getSelectedItem()), pret + " RON");
                    cos = cosGUI.getPanel();
                    cos.setBackground(new Color(147, 112, 219));
                    tabbedPane.add("Cos", cos);

                    JOptionPane.showMessageDialog(null, "Bilet rezervat cu succes!");
                    frame.dispose();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            panel.add(buton1);
            frame.add(panel);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Nu exista bilete disponibile!");
        }
    }
}
