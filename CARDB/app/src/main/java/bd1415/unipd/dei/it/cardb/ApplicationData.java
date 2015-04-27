package bd1415.unipd.dei.it.cardb;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Avviso;
import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Compone;
import bd1415.unipd.dei.it.cardb.databasetables.Contiene;
import bd1415.unipd.dei.it.cardb.databasetables.Edificio;
import bd1415.unipd.dei.it.cardb.databasetables.Fornitore;
import bd1415.unipd.dei.it.cardb.databasetables.Guasto;
import bd1415.unipd.dei.it.cardb.databasetables.Lavora_a;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class ApplicationData {

    public static ArrayList<Veicolo> veicoli = new ArrayList<>();
    public static ArrayList<Privato> clienti = new ArrayList<>();
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


    public static int posizioneCorrente;

}
