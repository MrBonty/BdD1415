package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Pezzo {

    private int id; //PRIMARY-KEY
    private String descrizione;
    private int numero_totale_pezzi;
    private float prezzo_vendita;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Pezzo";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Pezzo";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(float nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Pezzo";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "id";
        params[4] = ""+this.id;
        new UpdateValueInDataBase().execute(params);
    }

    public int getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getNumero_totale_pezzi() {
        return numero_totale_pezzi;
    }

    public float getPrezzo_vendita() {
        return prezzo_vendita;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setNumero_totale_pezzi(int numero_totale_pezzi) {
        this.numero_totale_pezzi = numero_totale_pezzi;
    }

    public void setPrezzo_vendita(float prezzo_vendita) {
        this.prezzo_vendita = prezzo_vendita;
    }

}

