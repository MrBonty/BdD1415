package bd1415.unipd.dei.it.cardb.databasetables;

public class Lavora_a {

    private String personale;
    private int lavoro;
    private int straordinari;
    private int ore_lavoro;

    //TODO -- mancano i metodi per l'update.

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

    public void setPersonale(String personale) {
        this.personale = personale;
    }

    public void setLavoro(int lavoro) {
        this.lavoro = lavoro;
    }

    public void setOre_lavoro(int ore_lavoro) {
        this.ore_lavoro = ore_lavoro;
    }

    public void setStraordinari(int straordinari) {
        this.straordinari = straordinari;
    }
}
