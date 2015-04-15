package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Lavora_a {

    private String personale; //PRIMARY-KEY
    private int lavoro; //PRIMARY-KEY
    private int straordinari;
    private int ore_lavoro;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Lavora_a";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(personale, lavoro)";
        params[4] = "(" + this.personale + ", '" + this.lavoro + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Lavora_a";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(personale, lavoro)";
        params[4] = "(" + this.personale + ", '" + this.lavoro + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public String getPersonale() {
        return personale;
    }

    public int getLavoro() {
        return lavoro;
    }

    public int getOre_lavoro() {
        return ore_lavoro;
    }

    public int getStraordinari() {
        return straordinari;
    }

    public void setPersonale(String personale) {
        this.personale = personale;
    }

    public void setLavoro(int lavoro) {
        this.lavoro = lavoro;
    }

    public void setOre_lavoro(int ore_lavoro) {
        this.ore_lavoro = ore_lavoro;
    }

    public void setStraordinari(int straordinari) {
        this.straordinari = straordinari;
    }
}
