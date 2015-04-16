package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class R8 {

    private int lavoro; //PRIMARY-KEY
    private int manutenzione; //PRIMARY-KEY

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "R8";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(lavoro, manutenzione)";
        params[4] = "("+this.lavoro + ", " + this.manutenzione + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public int getLavoro() {
        return lavoro;
    }

    public int getManutenzione() {
        return manutenzione;
    }

    public void setLavoro(int lavoro) {
        this.lavoro = lavoro;
    }

    public void setManutenzione(int manutenzione) {
        this.manutenzione = manutenzione;
    }
}
