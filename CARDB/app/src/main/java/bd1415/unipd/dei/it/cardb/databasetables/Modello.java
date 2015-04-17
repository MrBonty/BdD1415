package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Modello {

    public static final String TABLE_MODELLO = "Modello";
    // Modello Columns
    public static final String MODELLO_PK_CODICE_PRODUZIONE = "codice_poduzione";
    public static final String MODELLO_PK_MARCA = "marca";
    public static final String MODELLO_NOME = "nome";
    public static final String MODELLO_ANNO = "anno";

    private String codice_produzione; //PRIMARY-KEY
    private String marca; //PRIMARY-KEY
    private String nome;
    private String anno;

    public Modello(String codice_produzione, String marca){
        String[] params = new String[4];
        params[0] = TABLE_MODELLO;
        params[1] = "(" + MODELLO_PK_CODICE_PRODUZIONE + ", " + MODELLO_PK_MARCA + ")";
        params[2] = "('" +  codice_produzione + "', '" + marca + "')";
        params[3] = ";";
        new InsertInDataBase().execute(params);

        this.codice_produzione = codice_produzione;
        this.marca = marca;

        while (!Util.isSet());
        Util.setToNull();
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_MODELLO;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(" + MODELLO_PK_CODICE_PRODUZIONE + ", " + MODELLO_PK_MARCA + ")";
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
        updateValueInDatabase(codice_produzione, MODELLO_PK_CODICE_PRODUZIONE);
    }

    public void setMarca(String marca) {
        this.marca = marca;
        updateValueInDatabase(marca, MODELLO_PK_MARCA);
    }

    public void setNome(String nome) {
        this.nome = nome;
        updateValueInDatabase(nome, MODELLO_NOME);
    }

    public void setAnno(String anno) {
        this.anno = anno;
        updateValueInDatabase(anno, MODELLO_ANNO);
    }
}
