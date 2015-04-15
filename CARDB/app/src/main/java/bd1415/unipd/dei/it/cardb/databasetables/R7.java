package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class R7 {

    private int lavoro; //PRIMARY-KEY
    private int guasto; //PRIMARY-KEY

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "R7";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(lavoro, guasto)";
        params[4] = "("+this.lavoro + ", " + this.guasto + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public void setGuasto(int guasto) {
        this.guasto = guasto;
    }

    public void setLavoro(int lavoro) {
        this.lavoro = lavoro;
    }

    public int getGuasto() {
        return guasto;
    }

    public int getLavoro() {
        return lavoro;
    }
}
