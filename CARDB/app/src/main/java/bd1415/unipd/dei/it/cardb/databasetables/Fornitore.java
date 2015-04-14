package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Fornitore {

    private String piva; //PRIMARY-KEY
    private String nome;
    private String telefono;
    private AddressType indirizzo;
    private String iban;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Fonitore";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "piva";
        params[4] = "'"+this.piva+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Fornitore";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "piva";
        params[4] = "'" + this.piva + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(AddressType nuovo_valore, String nome_attributo){
        String[] params = new String[5];
        params[0] = "Fornitore";
        params[1] = nome_attributo;
        params[2] = "ROW('" + nuovo_valore.indirizzo + "', '" + nuovo_valore.numero_civico
                + "', '" + nuovo_valore.città + "', '" + nuovo_valore.provincia + "')";
        params[3] = "piva";
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

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setIndirizzo(AddressType indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

}
