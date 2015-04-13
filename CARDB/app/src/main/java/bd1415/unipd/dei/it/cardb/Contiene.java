package bd1415.unipd.dei.it.cardb;

public class Contiene {

    private String ordine_data;
    private String ordine_fornitore;
    private int pezzo;
    private int numero_pezzi;
    private float prezzo_pezzo;

    public String getOrdine_data() {
        return ordine_data;
    }

    public String getOrdine_fornitore() {
        return ordine_fornitore;
    }

    public int getPezzo() {
        return pezzo;
    }

    public int getNumero_pezzi() {
        return numero_pezzi;
    }

    public float getPrezzo_pezzo() {
        return prezzo_pezzo;
    }

    public void setPrezzo_pezzo(float prezzo_pezzo) {
        this.prezzo_pezzo = prezzo_pezzo;
    }

    public void setOrdine_data(String ordine_data) {
        this.ordine_data = ordine_data;
    }

    public void setOrdine_fornitore(String ordine_fornitore) {
        this.ordine_fornitore = ordine_fornitore;
    }

    public void setPezzo(int pezzo) {
        this.pezzo = pezzo;

    }

    public void setNumero_pezzi(int numero_pezzi) {
        this.numero_pezzi = numero_pezzi;
    }

}
