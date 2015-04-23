package bd1415.unipd.dei.it.cardb;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Guasto;
import bd1415.unipd.dei.it.cardb.databasetables.Manutenzione;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class ApplicationData {

    public static ArrayList<Veicolo> veicoli = new ArrayList<>();
    public static ArrayList<Privato> clienti = new ArrayList<>();
    public static ArrayList<Manutenzione> manutenzioni = new ArrayList<>();
    public static ArrayList<Guasto> guasti = new ArrayList<>();

    public static int posizioneCorrente;

}
