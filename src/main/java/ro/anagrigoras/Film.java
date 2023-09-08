package ro.anagrigoras;

public class Film {
    private String titlu;
    private String gen;
    private String data_aparitie;
    private int id;

    Film(int id, String titlu, String gen, String data_aparitie) {
        this.id = id;
        this.titlu = titlu;
        this.gen = gen;
        this.data_aparitie = data_aparitie;
    }

    String getTitlu() {
        return titlu;
    }

    void setTitlu(String Titlu) {
        this.titlu = Titlu;
    }

    String getGen() {
        return gen;
    }

    void setGen(String Gen) {
        this.gen = Gen;
    }

    String getDataAparitie() {
        return data_aparitie;
    }

    void setDataAparitie(String Data_aparitie) {
        this.data_aparitie = Data_aparitie;
    }

    int getId() {
        return id;
    }
}
