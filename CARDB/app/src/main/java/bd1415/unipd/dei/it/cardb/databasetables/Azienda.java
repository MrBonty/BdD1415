package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Azienda {

    public static final String TABLE_AZIENDA = "Azienda";
    // Azienda Columns
    public static final String AZIENDA_PK_PIVA = "piva";
    public static final String AZIENDA_NOME = "nome";
    public static final String AZIENDA_TELEFONO = "telefono";
    public static final String AZIENDA_INDIRIZZO = "indirizzo";

    private String piva; //PRIMARY-KEY
    private String nome;
    private String telefono;
    private AddressType indirizzo;

    public Azienda(String piva){
        String[] params = new String[4];
        params[0] = TABLE_AZIENDA;
        params[1] = "(" + AZIENDA_PK_PIVA  + ")";
        params[2] = "('"+ piva +"')";
        params[3] = ";";
        new InsertInDataBase().execute(params);

        this.piva = piva;

        while (!Util.isSet());
        Util.setToNull();
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_AZIENDA;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = AZIENDA_PK_PIVA;
        params[4] = "'"+this.piva+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_AZIENDA;
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = AZIENDA_PK_PIVA;
        params[4] = "'" + this.piva + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(AddressType nuovo_valore, String nome_attributo){
        String[] params = new String[5];
        params[0] = TABLE_AZIENDA;
        params[1] = nome_attributo;
        params[2] = "ROW('" + nuovo_valore.indirizzo + "', '" + nuovo_valore.numero_civico
                + "', '" + nuovo_valore.città + "', '" + nuovo_valore.provincia + "')";
        params[3] = AZIENDA_PK_PIVA;
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

    public void setPiva(String piva) {
        this.piva = piva;
        updateValueInDatabase(piva, AZIENDA_PK_PIVA);
    }

    public void setNome(String nome) {
        this.nome = nome;
        updateValueInDatabase(nome, AZIENDA_NOME);
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
        updateValueInDatabase(telefono , AZIENDA_TELEFONO);
    }

    public void setIndirizzo(AddressType indirizzo) {
        this.indirizzo = indirizzo;
        updateValueInDatabase(indirizzo , AZIENDA_INDIRIZZO);
    }

}
