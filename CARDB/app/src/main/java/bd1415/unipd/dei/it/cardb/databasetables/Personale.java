package bd1415.unipd.dei.it.cardb.databasetables;

import bd1415.unipd.dei.it.cardb.InsertInDataBase;
import bd1415.unipd.dei.it.cardb.UpdateValueInDataBase;

public class Personale {

    public static final String TABLE_PERSONALE = "Personale";
    // Personale Columns
    public static final String PERSONALE_PK_CF = "cf";
    public static final String PERSONALE_NOME = "nome";
    public static final String PERSONALE_COGNOME = "cognome";
    public static final String PERSONALE_TELEFONO = "telefono";
    public static final String PERSONALE_INDIRIZZO = "indirizzo";
    public static final String PERSONALE_IBAN = "iban";
    public static final String PERSONALE_CONTRATTO = "contratto";
    public static final String PERSONALE_RESPONSABILE = "responsabile";
    public static final String PERSONALE_EDIFICIO = "edificio";

    public static final String PERSONALE_COLUMS = "(" + PERSONALE_PK_CF + ", "
            + PERSONALE_NOME + ", "
            + PERSONALE_COGNOME + ", "
            + PERSONALE_TELEFONO + ", "
            + PERSONALE_INDIRIZZO + ", "
            + PERSONALE_IBAN + ", "
            + PERSONALE_CONTRATTO + ", "
            + PERSONALE_RESPONSABILE + ", "
            + PERSONALE_EDIFICIO + ")";

    private String cf; //PRIMARY-KEY
    private String nome;
    private String cognome;
    private String telefono;
    private AddressType indirizzo;
    private String iban;
    private String contratto;
    private int responsabile = 0;
    private int edificio; //FOREING KEY

    //TODO -- CHECK IT
    public Personale(String cf, int edificio, boolean insert) {
        if (insert) {
            String[] params = new String[4];
            params[0] = TABLE_PERSONALE;
            params[1] = "(" + PERSONALE_PK_CF + PERSONALE_EDIFICIO + ")";
            params[2] = "('" + cf + "', " + edificio + ")";
            params[3] = ";";
            new InsertInDataBase().execute(params);
        }
        this.cf = cf;
        this.edificio = edificio;
    }

    public void updateValueInDatabase(String nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_PERSONALE;
        params[1] = nome_attributo;
        params[2] = "'" + nuovo_valore + "'";
        params[3] = PERSONALE_PK_CF;
        params[4] = "'" + this.cf + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(int nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_PERSONALE;
        params[1] = nome_attributo;
        params[2] = "" + nuovo_valore;
        params[3] = PERSONALE_PK_CF;
        params[4] = "'" + this.cf + "'";
        new UpdateValueInDataBase().execute(params);
    }

    public void updateValueInDatabase(AddressType nuovo_valore, String nome_attributo) {
        String[] params = new String[5];
        params[0] = TABLE_PERSONALE;
        params[1] = nome_attributo;
        params[2] = "ROW('" + nuovo_valore.indirizzo + "', '" + nuovo_valore.numero_civico
                + "', '" + nuovo_valore.città + "', '" + nuovo_valore.provincia + "')";
        params[3] = PERSONALE_PK_CF;
        params[4] = "'" + this.cf + "'";
        new UpdateValueInDataBase().execute(params);
    }

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

    public void setCf(String cf, boolean update) {
        this.cf = cf;
        if (update) {
            updateValueInDatabase(cf, PERSONALE_PK_CF);
        }
    }

    public void setNome(String nome, boolean update) {
        this.nome = nome;
        if (update) {
            updateValueInDatabase(nome, PERSONALE_NOME);
        }
    }

    public void setCognome(String cognome, boolean update) {
        this.cognome = cognome;
        if (update) {
            updateValueInDatabase(cognome, PERSONALE_COGNOME);
        }
    }

    public void setTelefono(String telefono, boolean update) {
        this.telefono = telefono;
        if (update) {
            updateValueInDatabase(telefono, PERSONALE_TELEFONO);
        }
    }

    public void setIndirizzo(AddressType indirizzo, boolean update) {
        this.indirizzo = indirizzo;
        if (update) {
            updateValueInDatabase(indirizzo, PERSONALE_INDIRIZZO);
        }
    }

    public void setIban(String iban, boolean update) {
        this.iban = iban;
        if (update) {
            updateValueInDatabase(iban, PERSONALE_IBAN);
        }
    }

    public void setContratto(String contratto, boolean update) {
        this.contratto = contratto;
        if (update) {
            updateValueInDatabase(contratto, PERSONALE_CONTRATTO);
        }
    }

    public void setResponsabile(int responsabile, boolean update) {
        this.responsabile = responsabile;
        if (update) {
            updateValueInDatabase(responsabile, PERSONALE_RESPONSABILE);
        }
    }

    public void setEdificio(int edificio, boolean update) {
        this.edificio = edificio;
        if (update) {
            updateValueInDatabase(edificio, PERSONALE_EDIFICIO);
        }
    }

}
