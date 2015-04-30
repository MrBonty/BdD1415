package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;
import bd1415.unipd.dei.it.cardb.Util;

public class Veicolo {

    public static final String TABLE_VEICOLO = "Veicolo";
    // Veicolo Columns
    public static final String VEICOLO_PK_NUMERO_TELAIO = "numero_telaio";
    public static final String VEICOLO_TARGA = "targa";
    public static final String VEICOLO_AZIENDA = "azienda";
    public static final String VEICOLO_PRIVATO = "privato";
    public static final String VEICOLO_MODELLO_COD_PROD = "modello_cod_prod";
    public static final String VEICOLO_MODELLO_MARCA = "modello_marca";

    public static final String VEICOLO_COLUMS = "(" + VEICOLO_PK_NUMERO_TELAIO + ", "
            + VEICOLO_TARGA + ", "
            + VEICOLO_AZIENDA + ", "
            + VEICOLO_PRIVATO + ", "
            + VEICOLO_MODELLO_COD_PROD + ", "
            + VEICOLO_MODELLO_MARCA + ")";

    private String numero_telaio; //PRIMARY-KEY
    private String targa;
    private String azienda;//FOREING KEY but...
    private String privato;//FOREING KEY but...
    private String modello_cod_prod; //FOREING KEY
    private String modello_marca; //FOREING KEY

    //TODO -- CHECK IT
    public Veicolo(String numero_telaio, String modello_cod_prod, String modello_marca, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_VEICOLO;
            params[1] = "(" + VEICOLO_PK_NUMERO_TELAIO + ", " + VEICOLO_MODELLO_COD_PROD + ", " + VEICOLO_MODELLO_MARCA + ")";
            params[2] = "('" + numero_telaio + "', '" + modello_cod_prod + "', '" + modello_marca + "')";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }

        this.numero_telaio = numero_telaio;
        this.modello_cod_prod = modello_cod_prod;
        this.modello_marca = modello_marca;

        if (insert) {
            while (!Util.isSet()) ;
            Util.setToNull();
        }
    }

    public void updateValueInDataBase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_VEICOLO;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = VEICOLO_PK_NUMERO_TELAIO;
        params[4] = "'" + this.numero_telaio + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public String getNumero_telaio() {
        return numero_telaio;
    }

    public void setNumero_telaio(String numero_telaio, boolean update) {
        this.numero_telaio = numero_telaio;
        if (update) {
            updateValueInDataBase(numero_telaio, VEICOLO_PK_NUMERO_TELAIO);
        }
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa, boolean update) {
        this.targa = targa;
        if (update) {
            updateValueInDataBase(targa, VEICOLO_TARGA);
        }
    }

    public String getAzienda() {
        return azienda;
    }

    public void setAzienda(String azienda, boolean update) {
        this.azienda = azienda;
        if (update) {
            updateValueInDataBase(azienda, VEICOLO_AZIENDA);
        }
    }

    public String getPrivato() {
        return privato;
    }

    public void setPrivato(String privato, boolean update) {
        this.privato = privato;
        if (update) {
            updateValueInDataBase(privato, VEICOLO_PRIVATO);
        }
    }

    public String getModello_cod_prod() {
        return modello_cod_prod;
    }

    public void setModello_cod_prod(String modello_cod_prod, boolean update) {
        this.modello_cod_prod = modello_cod_prod;
        if (update) {
            updateValueInDataBase(modello_cod_prod, VEICOLO_MODELLO_COD_PROD);
        }
    }

    public String getModello_marca() {
        return modello_marca;
    }

    public void setModello_marca(String modello_marca, boolean update) {
        this.modello_marca = modello_marca;
        if (update) {
            updateValueInDataBase(modello_marca, VEICOLO_MODELLO_MARCA);
        }
    }

}
