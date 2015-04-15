package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Compone {

    private String modello_cod_prod; //PRIMARY-KEY
    private String modello_marca; //PRIMARY-KEY
    private int pezzo; //PRIMARY-KEY

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Compone";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(modello_cod_prod, modello_marca, pezzo)";
        params[4] = "('" + this.modello_cod_prod + "', '" + this.modello_marca + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Compone";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(modello_cod_prod, modello_marca, pezzo)";
        params[4] = "('" + this.modello_cod_prod + "', '" + this.modello_marca + "', " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public String getModello_cod_prod() {
        return modello_cod_prod;
    }

    public String getModello_marca() {
        return modello_marca;
    }

    public int getPezzo() {
        return pezzo;
    }

    public void setModello_cod_prod(String modello_cod_prod) {
        this.modello_cod_prod = modello_cod_prod;
    }

    public void setModello_marca(String modello_marca) {
        this.modello_marca = modello_marca;
    }

    public void setPezzo(int pezzo) {
        this.pezzo = pezzo;
    }

}
