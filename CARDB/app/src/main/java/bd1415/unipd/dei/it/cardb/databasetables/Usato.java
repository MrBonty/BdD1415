package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Usato {

    private int lavoro; //PRIMARY-KEY
    private int pezzo; //PRIMARY-KEY
    private int numero_pezzi;

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Usato";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "(lavoro, pezzo)";
        params[4] = "("+this.lavoro + ", " + this.pezzo + ")";
        new UpdateValueInDataBase().execute(params);
    }

    public int getLavoro() {
        return lavoro;
    }

    public int getPezzo() {
        return pezzo;
    }

    public int getNumero_pezzi() {
        return numero_pezzi;
    }

    public void setNumero_pezzi(int numero_pezzi) {
        this.numero_pezzi = numero_pezzi;
    }

    public void setPezzo(int pezzo) {
        this.pezzo = pezzo;
    }

    public void setLavoro(int lavoro) {
        this.lavoro = lavoro;
    }



}
