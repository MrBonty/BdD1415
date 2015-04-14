package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Personale {

    private String cf; //PRIMARY-KEY
    private String nome;
    private String cognome;
    private String telefono;
    private AddressType indirizzo;
    private String iban;
    private String contratto;
    private int responsabile = 0;
    private int edificio;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Personale";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "cf";
        params[4] = "'"+this.cf+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Personale";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "cf";
        params[4] = "'"+this.cf+"'";
        new UpdateValueInDataBase().execute(params);
    }

    //TODO

    public String getCf() {
        return cf;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
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

    public String getContratto() {
        return contratto;
    }

    public int getResponsabile() {
        return responsabile;
    }

    public int getEdificio() {
        return edificio;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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

    public void setContratto(String contratto) {
        this.contratto = contratto;
    }

    public void setResponsabile(int responsabile) {
        this.responsabile = responsabile;
    }

    public void setEdificio(int edificio) {
        this.edificio = edificio;
    }

}
