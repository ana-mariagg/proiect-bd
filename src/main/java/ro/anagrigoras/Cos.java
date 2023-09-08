package ro.anagrigoras;

public class Cos {

    Film film;
    String data;
    String ora;
    String sala;
    String rand;
    String loc;
    String pret;

    Cos(Film film, String data, String ora, String sala, String rand, String loc, String pret) {
        this.film = film;
        this.data = data;
        this.ora = ora;
        this.sala = sala;
        this.rand = rand;
        this.loc = loc;
        this.pret = pret;
    }

    Film getFilm() {
        return film;
    }

    String getData() {
        return data;
    }

    String getOra() {
        return ora;
    }

    String getSala() {
        return sala;
    }

    String getRand() {
        return rand;
    }

    String getLoc() {
        return loc;
    }

    String getPret() {
        return pret;
    }
}
