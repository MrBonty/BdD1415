package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Fattura {

    private int id; //PRIMARY-KEY
    private int pagato = 0;
    private String azienda;
    private String privato;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Fattura";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Fattura";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public int getPagato() {
        return pagato;
    }

    public String getAzienda() {
        return azienda;
    }

    public String getPrivato() {
        return privato;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPagato(int pagato) {
        this.pagato = pagato;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public void setPrivato(String privato) {
        this.privato = privato;
    }
}
