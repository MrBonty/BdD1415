package bd1415.unipd.dei.it.cardb;

public class Veicolo {

    private String numero_telaio; //PRIMARY-KEY
    private String targa;
    private String azienda;
    private String privato;
    private String modello_cod_prod;
    private String modello_marca;

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Veicolo";
        params[1] = nome_attributo;
        params[2] = "'"+nuovo_valore+"'";
        params[3] = "numero_telaio";
        params[4] = "'"+this.numero_telaio+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = "Veicolo";
        params[1] = nome_attributo;
        params[2] = ""+nuovo_valore;
        params[3] = "numero_telaio";
        params[4] = "'"+this.numero_telaio+"'";
        new UpdateValueInDataBase().execute(params);
    }

    public String getNumero_telaio() {
        return numero_telaio;
    }

    public void setNumero_telaio(String numero_telaio) {
        this.numero_telaio = numero_telaio;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public String getPrivato() {
        return privato;
    }

    public void setPrivato(String privato) {
        this.privato = privato;
    }

    public String getModello_cod_prod() {
        return modello_cod_prod;
    }

    public void setModello_cod_prod(String modello_cod_prod) {
        this.modello_cod_prod = modello_cod_prod;
    }

    public String getModello_marca() {
        return modello_marca;
    }

    public void setModello_marca(String modello_marca) {
        this.modello_marca = modello_marca;
    }

}
