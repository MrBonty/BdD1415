package bd1415.unipd.dei.it.cardb.databasetables;

import java.security.PrivateKey;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Privato {

    public static final String TABLE_PRIVATO = "Privato";
    // Privato Columns
    public static final String PRIVATO_PK_CF = "cf";
    public static final String PRIVATO_NOME = "nome";
    public static final String PRIVATO_COGNOME = "cognome";
    public static final String PRIVATO_TELEFONO = "telefono";
    public static final String PRIVATO_INDIRIZZO = "indirizzo";

    private String cf; //PRIMARY-KEY
    private String nome;
    private String cognome;
    private String telefono;
    private AddressType indirizzo;

    public Privato(String cf, boolean insert){
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_PRIVATO;
            params[1] = "(" + PRIVATO_PK_CF + ")";
            params[2] = "('" + cf + "')";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.cf = cf;

        if (insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_PRIVATO;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = PRIVATO_PK_CF;
        params[4] = "'"+this.cf+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_PRIVATO;
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = PRIVATO_PK_CF;
        params[4] = "'"+this.cf+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(AddressType nuovo_valore, String nome_attributo){
        String[] params = new String[5];
        params[0] = TABLE_PRIVATO;
        params[1] = nome_attributo;
        params[2] = "ROW('" + nuovo_valore.indirizzo + "', '" + nuovo_valore.numero_civico
                + "', '" + nuovo_valore.città + "', '" + nuovo_valore.provincia + "')";
        params[3] = PRIVATO_PK_CF;
        params[4] = "'"+this.cf+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public String getCf() {
        return cf;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public AddressType getIndirizzo() {
        return indirizzo;
    }

    public void setCf(String cf, boolean update) {
        this.cf = cf;
        if (update) {
            updateValueInDatabase(cf, PRIVATO_PK_CF);
        }
    }

    public void setNome(String nome, boolean update) {
        this.nome = nome;
        if (update) {
            updateValueInDatabase(nome, PRIVATO_NOME);
        }
    }

    public void setCognome(String cognome, boolean update) {
        this.cognome = cognome;
        if (update) {
            updateValueInDatabase(cognome, PRIVATO_COGNOME);
        }
    }

    public void setTelefono(String telefono, boolean update) {
        this.telefono = telefono;
        if (update) {
            updateValueInDatabase(telefono, PRIVATO_TELEFONO);
        }
    }

    public void setIndirizzo(AddressType indirizzo, boolean update) {
        this.indirizzo = indirizzo;
        if (update) {
            updateValueInDatabase(indirizzo, PRIVATO_INDIRIZZO);
        }
    }

}
