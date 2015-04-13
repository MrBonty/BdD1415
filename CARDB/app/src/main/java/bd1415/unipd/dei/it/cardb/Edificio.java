package bd1415.unipd.dei.it.cardb;

public class Edificio {

    private int id;
    private String tipologia;
    private AddressType indirizzo;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Edificio";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Edficio";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public String getTipologia() {
        return tipologia;
    }

    public AddressType getIndirizzo() {
        return indirizzo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setInditizzo(AddressType indirizzo) {
        this.indirizzo = indirizzo;
    }

}