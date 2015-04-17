package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Lavora_a {

    public static final String TABLE_LAVORA_A = "Lavora_a";
    // Lavora_a Columns
    public static final String LAVORA_A_PK_PERSONALE = "personale";
    public static final String LAVORA_A_PK_LAVORO = "lavoro";
    public static final String LAVORA_A_STRAORDINARI = "straordinari";
    public static final String LAVORA_A_ORE_LAVORO = "ore_lavoro";

    private String personale; //PRIMARY-KEY //FOREING KEY
    private int lavoro; //PRIMARY-KEY //FOREING KEY
    private int straordinari;
    private int ore_lavoro;

    public Lavora_a(String personale, int lavoro, boolean insert){
        if(insert) {
            String[] params = new String[4];
            params[0] = TABLE_LAVORA_A;
            params[1] = "(" + LAVORA_A_PK_PERSONALE + ", " + LAVORA_A_PK_LAVORO + ")";
            params[2] = "('" + personale + "', " + lavoro + ")";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.lavoro = lavoro;
        this.personale = personale;

        if(insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_LAVORA_A;
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "(" + LAVORA_A_PK_PERSONALE + ", " + LAVORA_A_PK_LAVORO + ")";
        params[4] = "(" + this.personale + ", '" + this.lavoro + "')";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_LAVORA_A;
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(" + LAVORA_A_PK_PERSONALE + ", " + LAVORA_A_PK_LAVORO + ")";
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

    public void setPersonale(String personale, boolean update) {
        this.personale = personale;
        if(update) {
            updateValueInDatabase(personale, LAVORA_A_PK_PERSONALE);
        }
    }

    public void setLavoro(int lavoro, boolean update) {
        this.lavoro = lavoro;
        if(update) {
            updateValueInDatabase(lavoro, LAVORA_A_PK_LAVORO);
        }
    }

    public void setOre_lavoro(int ore_lavoro, boolean update) {
        this.ore_lavoro = ore_lavoro;
        if(update) {
            updateValueInDatabase(ore_lavoro, LAVORA_A_ORE_LAVORO);
        }
    }

    public void setStraordinari(int straordinari, boolean update) {
        this.straordinari = straordinari;
        if (update) {
            updateValueInDatabase(straordinari, LAVORA_A_STRAORDINARI);
        }
    }
}
