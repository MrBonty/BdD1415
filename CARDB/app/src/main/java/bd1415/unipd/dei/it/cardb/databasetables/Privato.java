package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

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

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Privato";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "cf";
        params[4] = "'"+this.cf+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Privato";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "cf";
        params[4] = "'"+this.cf+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(AddressType nuovo_valore, String nome_attributo){
        String[] params = new String[5];
        params[0] = "Privato";
        params[1] = nome_attributo;
        params[2] = "ROW('" + nuovo_valore.indirizzo + "', '" + nuovo_valore.numero_civico
                + "', '" + nuovo_valore.città + "', '" + nuovo_valore.provincia + "')";
        params[3] = "cf";
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

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setIndirizzo(AddressType indirizzo) {
        this.indirizzo = indirizzo;
    }

}
