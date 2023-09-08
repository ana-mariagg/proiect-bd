package ro.anagrigoras;

public class User {
    private String nume;
    private String prenume;
    private String userName;
    private String nrTelefon;
    private String tipClient;
    private int bilete;
    private String filme;

    User(String nume, String prenume, String userName, String nr_telefon, String tip_client, int bilete, String filme) {
        this.nume = nume;
        this.prenume = prenume;
        this.userName = userName;
        this.nrTelefon = nr_telefon;
        this.tipClient = tip_client;
        this.bilete = bilete;
        this.filme = filme;
    }


    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getUserName() {
        return userName;
    }

    public int getBilete() {
        return bilete;
    }

    public String getFilme() {
        return filme;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public String getTipClient() {
        return tipClient;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
