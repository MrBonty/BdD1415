package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Fornitore {

    public static final String TABLE_FORNITORE = "Fornitore";
    // Fornitore Columns
    public static final String FORNITORE_PK_PIVA = "piva";
    public static final String FORNITORE_NOME = "nome";
    public static final String FORNITORE_TELEFONO = "telefono";
    public static final String FORNITORE_IBAN = "iban";
    public static final String FORNITORE_INDIRIZZO = "indirizzo";

    private String piva; //PRIMARY-KEY
    private String nome;
    private String telefono;
    private AddressType indirizzo;
    private String iban;

    public Fornitore(String piva, boolean insert){
        if(insert) {
            String[] params = new String[4];
            params[0] = TABLE_FORNITORE;
            params[1] = "(" + FORNITORE_PK_PIVA + ")";
            params[2] = "('" + piva + "')";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.piva = piva;

        if(insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_FORNITORE;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = FORNITORE_PK_PIVA;
        params[4] = "'"+this.piva+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_FORNITORE;
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = FORNITORE_PK_PIVA;
        params[4] = "'" + this.piva + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(AddressType nuovo_valore, String nome_attributo){
        String[] params = new String[5];
        params[0] = TABLE_FORNITORE;
        params[1] = nome_attributo;
        params[2] = "ROW('" + nuovo_valore.indirizzo + "', '" + nuovo_valore.numero_civico
                + "', '" + nuovo_valore.città + "', '" + nuovo_valore.provincia + "')";
        params[3] = FORNITORE_PK_PIVA;
        params[4] = "'"+this.piva+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public String getPiva() {
        return piva;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public AddressType getIndirizzo() {
        return indirizzo;
    }

    public String getIban() {
        return iban;
    }

    public void setPiva(String piva, boolean update) {
        this.piva = piva;
        if(update) {
            updateValueInDatabase(piva, FORNITORE_PK_PIVA);
        }
    }

    public void setNome(String nome, boolean update) {
        this.nome = nome;
        if(update) {
            updateValueInDatabase(nome, FORNITORE_NOME);
        }
    }

    public void setTelefono(String telefono, boolean update) {
        this.telefono = telefono;
        if(update) {
            updateValueInDatabase(telefono, FORNITORE_TELEFONO);
        }
    }

    public void setIndirizzo(AddressType indirizzo, boolean update) {
        this.indirizzo = indirizzo;
        if(update) {
            updateValueInDatabase(indirizzo, FORNITORE_INDIRIZZO);
        }
    }

    public void setIban(String iban, boolean update) {
        this.iban = iban;
        if(update) {
            updateValueInDatabase(iban, FORNITORE_IBAN);
        }
    }

}
