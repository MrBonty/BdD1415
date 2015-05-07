package bd1415.unipd.dei.it.cardb;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Avviso;
import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Compone;
import bd1415.unipd.dei.it.cardb.databasetables.Contiene;
import bd1415.unipd.dei.it.cardb.databasetables.Edificio;
import bd1415.unipd.dei.it.cardb.databasetables.Fattura;
import bd1415.unipd.dei.it.cardb.databasetables.Fornitore;
import bd1415.unipd.dei.it.cardb.databasetables.Guasto;
import bd1415.unipd.dei.it.cardb.databasetables.Lavora_a;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;
import bd1415.unipd.dei.it.cardb.databasetables.Modello;
import bd1415.unipd.dei.it.cardb.databasetables.Ordine;
import bd1415.unipd.dei.it.cardb.databasetables.Personale;
import bd1415.unipd.dei.it.cardb.databasetables.Pezzo;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.R7;
import bd1415.unipd.dei.it.cardb.databasetables.R8;
import bd1415.unipd.dei.it.cardb.databasetables.Usato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class ApplicationData {

    public static ArrayList<Veicolo> veicoli = new ArrayList<>();
    public static ArrayList<Azienda> aziende = new ArrayList<>();
    public static ArrayList<Avviso> avvisi = new ArrayList<>();
    public static ArrayList<Compone> compone = new ArrayList<>();
    public static ArrayList<Contiene> contiene = new ArrayList<>();
    public static ArrayList<Edificio> edifici = new ArrayList<>();
    public static ArrayList<Fornitore> fornitori = new ArrayList<>();
    public static ArrayList<Lavora_a> lavora_a = new ArrayList<>();
    public static ArrayList<Manutenzione> manutenzioni = new ArrayList<>();
    public static ArrayList<Guasto> guasti = new ArrayList<>();
    public static ArrayList<Lavoro> lavori = new ArrayList<>();
    public static ArrayList<Lavoro> lavoriFiniti = new ArrayList<>();
    public static ArrayList<Lavoro> lavoriInCorso = new ArrayList<>();
    public static ArrayList<Modello> modelli = new ArrayList<>();
    public static ArrayList<Ordine> ordini = new ArrayList<>();
    public static ArrayList<Personale> personale = new ArrayList<>();
    public static ArrayList<Pezzo> pezzi = new ArrayList<>();
    public static ArrayList<Privato> privati = new ArrayList<>();
    public static ArrayList<R7> r7 = new ArrayList<>();
    public static ArrayList<R8> r8 = new ArrayList<>();
    public static ArrayList<Usato> usato = new ArrayList<>();
    public static ArrayList<Fattura> fatture = new ArrayList<>();

    public static boolean isFinished = false;

    public static int posizioneCorrente;

    public static void splitWork() {
        for (int i = 0; i < lavori.size(); i++) {
            Lavoro tmp = lavori.get(i);
            String fine = tmp.getData_fine();
            if (fine != null | fine != "") {
                lavoriFiniti.add(tmp);
            } else {
                lavoriInCorso.add(tmp);
            }
        }
    }

}
