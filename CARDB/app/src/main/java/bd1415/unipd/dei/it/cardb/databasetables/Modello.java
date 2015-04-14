package bd1415.unipd.dei.it.cardb.databasetables;

public class Modello {

    private String codice_produzione; //PRIMARY-KEY
    private String marca; //PRIMARY-KEY
    private String nome;
    private String anno;

    //TODO -- mancano i metodi per l'update.

    public String getCodice_produzione() {
        return codice_produzione;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nome;
    }

    public String getAnno() {
        return anno;
    }

    public void setCodice_produzione(String codice_produzione) {
        this.codice_produzione = codice_produzione;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }
}
