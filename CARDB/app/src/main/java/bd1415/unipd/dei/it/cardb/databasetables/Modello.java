package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Modello {

    private String codice_produzione; //PRIMARY-KEY
    private String marca; //PRIMARY-KEY
    private String nome;
    private String anno;


    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Modello";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(codice_produzione, marca)";
        params[4] = "(" + this.codice_produzione + ", '" + this.marca + "')";
        new UpdateValueInDataBase().execute(params);
    }

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
