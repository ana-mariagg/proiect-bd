package ro.anagrigoras;

public class Bilet {
    int id_bilet;
    int id_derulare;
    int id_sala;
    String rand;
    int loc;
    int pret;

    Bilet(int id_bilet, int id_derulare, int id_sala, String rand, int loc, int pret) {
        this.id_bilet = id_bilet;
        this.id_derulare = id_derulare;
        this.id_sala = id_sala;
        this.rand = rand;
        this.loc = loc;
        this.pret = pret;
    }
}
