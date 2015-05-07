package bd1415.unipd.dei.it.cardb;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

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

public class ConnectionWithDataBase extends AsyncTask<String, Void, String> {

    public static Session session;
    public static Connection con;
    private Toast toast;

    @Override
    protected void onPreExecute() {
        // here go all the graphics stuffs
        toast = Toast.makeText(MainActivity.ctx, "Connecting...", Toast.LENGTH_SHORT);
        toast.show();
    }

    protected String doInBackground(String... params) {
        String s = "";
        Statement st = null;
        try {
            // SSH loging username
            String strSshUser = params[0];
            // SSH login password
            String strSshPassword = params[1];
            // hostname or ip or SSH server
            String strSshHost = params[2];
            // hostname or ip of your database server
            String strRemoteHost = params[3];
            // local port number use to bind SSH tunnel
            int nLocalPort = Integer.parseInt(params[4]);
            // remote port number of your database
            int nRemotePort = Integer.parseInt(params[5]);
            // database loging username
            String strDbUser = strSshUser;
            // database login password
            String strDbPassword = strSshPassword;
            // create JSch object
            final JSch jsch = new JSch();
            // create a session for the ssh connection
            session = jsch.getSession(strSshUser, strSshHost, 22);
            // set session password
            session.setPassword(strSshPassword);
            // create valid properties configuration
            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            // set session configurations
            session.setConfig(config);
            // connecting...
            session.connect();
            // setting up ssh tunnel
            session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
            // calling postgresql JDBC drriver
            Class.forName("org.postgresql.Driver");
            // connect to DB through ssh tunnel
            con = DriverManager.getConnection("jdbc:postgresql://localhost:" + nLocalPort + "/"
                    + strSshUser, strDbUser, strDbPassword);
            // setting up test query
            st = con.createStatement();
            MainActivity.isWrong = false;
            MainActivity.isLogged = true;
        } catch (JSchException e) {
            MainActivity.isWrong = true;
            MainActivity.isLogged = false;
            s = "Errore di connessione, assicurarsi di avere accesso ad internet e reinserire le credenziali.";
            return s;
        } catch (Exception e) {
            s = "E' successo qualcosa di molto strano... chiedere assistenza agli sviluppatori!";
            return s;
        }
        try {
            if (st != null && con != null) {
                ResultSet rs = st.executeQuery("SELECT * FROM main.Veicolo;");
                while (rs.next()) {
                    s = s + rs.getString("numero_telaio");
                    s = s + rs.getString("targa");
                    s = s + rs.getString("azienda");
                    s = s + rs.getString("privato");
                    s = s + rs.getString("modello_cod_prod");
                    s = s + rs.getString("modello_marca");
                    s = s + "\n\n";
                    Veicolo veicolo = new Veicolo(rs.getString("numero_telaio"),
                            rs.getString("modello_cod_prod"), rs.getString("modello_marca"), false);
                    veicolo.setNumero_telaio(rs.getString("numero_telaio"), false);
                    veicolo.setTarga(rs.getString("targa"), false);
                    veicolo.setAzienda(rs.getString("azienda"), false);
                    veicolo.setPrivato(rs.getString("privato"), false);
                    veicolo.setModello_cod_prod(rs.getString("modello_cod_prod"), false);
                    veicolo.setModello_marca(rs.getString("modello_marca"), false);
                    ApplicationData.veicoli.add(veicolo);
                }/*

                rs = st.executeQuery("SELECT * FROM main.Azienda;");
                while (rs.next()) {
                    s = s + rs.getString("piva");
                    s = s + rs.getString("nome");
                    s = s + rs.getString("telefono");
                    s = s + rs.getString("indirizzo");
                    Azienda azienda = new Azienda(rs.getString("piva"), false);
                    //azienda.setIndirizzo(rs.getString("indirizzo"), false);
                    azienda.setTelefono(rs.getString("telefono"), false);
                    azienda.setNome(rs.getString("nome"), false);
                    ApplicationData.aziende.add(azienda);
                }

                rs = st.executeQuery("SELECT * FROM main.Avviso;");
                while (rs.next()) {
                    Avviso avviso = new Avviso(rs.getInt("manutenzione"), rs.getString("veicolo"),
                            false);
                    avviso.setData_prossima(rs.getString("data_prossima"), false);
                    avviso.setManutenzione(rs.getInt("manutezione"), false);
                    avviso.setVeicolo(rs.getString("veicolo"), false);
                    ApplicationData.avvisi.add(avviso);
                }

                rs = st.executeQuery("SELECT * FROM main.Compone;");
                while (rs.next()) {
                    Compone compone = new Compone(rs.getString("modello_cod_prod"),
                            rs.getString("modello_marca"), rs.getInt("pezzo"), false);
                    compone.setModello_marca(rs.getString("modello_marca"), false);
                    compone.setModello_cod_prod(rs.getString("modello_cod_prod"), false);
                    compone.setPezzo(rs.getInt("pezzo"), false);
                    ApplicationData.compone.add(compone);
                }

                rs = st.executeQuery("SELECT * FROM main.Contiene;");
                while (rs.next()) {
                    Contiene contiene = new Contiene(rs.getString("ordine_data"), rs.getString("ordine_fornitore"),
                            rs.getInt("pezzo"), false);
                    contiene.setPezzo(rs.getInt("pezzo"), false);
                    contiene.setNumero_pezzi(rs.getInt("numero_pezzi"), false);
                    contiene.setOrdine_data(rs.getString("ordine_data"), false);
                    contiene.setOrdine_fornitore(rs.getString("ordine_fornitore"), false);
                    contiene.setPrezzo_pezzo(rs.getFloat("prezzo_pezzo"), false);
                    ApplicationData.contiene.add(contiene);
                }

                rs = st.executeQuery("SELECT * FROM main.Edificio;");
                while (rs.next()) {
                    Edificio edificio = new Edificio(rs.getString("tipologia"), false);
                    edificio.setId(rs.getInt("id"), false);
                    edificio.setTipologia(rs.getString("tipologia"), false);
                    //edificio.setInditizzo(rs.getString("indirizzo"), false);
                    ApplicationData.edifici.add(edificio);
                }

                rs = st.executeQuery("SELECT * FROM main.Fattura;");
                while (rs.next()) {
                    Fattura fattura = new Fattura(false);
                    fattura.setAzienda(rs.getString("azienda"), false);
                    fattura.setPagato(rs.getInt("pagato"), false);
                    fattura.setPrivato(rs.getString("privato"), false);
                    fattura.setId(rs.getInt("id"), false);
                    ApplicationData.fatture.add(fattura);
                }
                rs = st.executeQuery("SELECT * FROM main.Fornitore;");
                while (rs.next()) {
                    Fornitore fornitore = new Fornitore(rs.getString("piva"), false);
                    fornitore.setPiva(rs.getString("piva"), false);
                    //fornitore.setIndirizzo(rs.getString("indirizzo"), false);
                    fornitore.setIban(rs.getString("iban"), false);
                    fornitore.setNome(rs.getString("nome"), false);
                    fornitore.setTelefono(rs.getString("telefono"), false);
                    ApplicationData.fornitori.add(fornitore);
                }

                rs = st.executeQuery("SELECT * FROM main.Guasto;");
                while (rs.next()) {
                    Guasto guasto = new Guasto(false);
                    guasto.setId(rs.getInt("id"), false);
                    guasto.setDescrizione(rs.getString("descrizione"), false);
                    ApplicationData.guasti.add(guasto);
                }

                rs = st.executeQuery("SELECT * FROM main.Lavora_a;");
                while (rs.next()) {
                    Lavora_a lavora_a = new Lavora_a(rs.getString("personale"), rs.getInt("id"), false);
                    lavora_a.setLavoro(rs.getInt("lavoro"), false);
                    lavora_a.setOre_lavoro(rs.getInt("ore_lavoro"), false);
                    lavora_a.setPersonale(rs.getString("personale"), false);
                    lavora_a.setStraordinari(rs.getInt("straordinari"), false);
                    ApplicationData.lavora_a.add(lavora_a);
                }

                rs = st.executeQuery("SELECT * FROM main.Lavoro;");
                while (rs.next()) {
                    Lavoro lavoro = new Lavoro(rs.getString("veicolo"), false);
                    lavoro.setId(rs.getInt("id"), false);
                    lavoro.setFattura(rs.getInt("fattura"), false);
                    lavoro.setData_fine(rs.getString("data_fine"), false);
                    lavoro.setData_inizio(rs.getString("data_inizio"), false);
                    lavoro.setVeicolo(rs.getString("veicolo"), false);
                    ApplicationData.lavori.add(lavoro);
                }
                ApplicationData.splitWork();

                rs = st.executeQuery("SELECT * FROM main.Manutenzione;");
                while (rs.next()) {
                    Manutenzione manutenzione = new Manutenzione(false);
                    manutenzione.setId(rs.getInt("id"), false);
                    manutenzione.setDescrizione(rs.getString("descrizione"), false);
                    ApplicationData.manutenzioni.add(manutenzione);
                }

                rs = st.executeQuery("SELECT * FROM main.Modello;");
                while (rs.next()) {
                    Modello modello = new Modello(rs.getString("codice_produzione"), rs.getString("marca"), false);
                    modello.setCodice_produzione(rs.getString("codice_produzione"), false);
                    modello.setMarca(rs.getString("marca"), false);
                    modello.setNome(rs.getString("nome"), false);
                    modello.setAnno(rs.getString("anno"), false);
                    ApplicationData.modelli.add(modello);
                }

                rs = st.executeQuery("SELECT * FROM main.Ordine;");
                while (rs.next()) {
                    Ordine ordine = new Ordine(rs.getString("data_or"), rs.getString("fornitore"), false);
                    ordine.setData_or(rs.getString("data_or"), false);
                    ordine.setFornitore(rs.getString("fornitore"), false);
                    ordine.setQuantita_fornita(rs.getInt("quantita_fornita"), false);
                    ApplicationData.ordini.add(ordine);
                }

                rs = st.executeQuery("SELECT * FROM main.Personale;");
                while (rs.next()) {
                    Personale personale = new Personale(rs.getString("cf"), rs.getInt("edificio"), false);
                    personale.setCf(rs.getString("cf"), false);
                    personale.setNome(rs.getString("nome"), false);
                    personale.setCognome(rs.getString("cognome"), false);
                    personale.setContratto(rs.getString("contratto"), false);
                    personale.setEdificio(rs.getInt("id"), false);
                    //personale.setIndirizzo(rs.getString("indirizzo"), false);
                    personale.setTelefono(rs.getString("telefono"), false);
                    personale.setIban(rs.getString("iban"), false);
                    personale.setResponsabile(rs.getInt("responsabile"), false);
                    ApplicationData.personale.add(personale);
                }

                rs = st.executeQuery("SELECT * FROM main.Pezzo;");
                while (rs.next()) {
                    Pezzo pezzo = new Pezzo(false);
                    pezzo.setId(rs.getInt("id"), false);
                    pezzo.setDescrizione(rs.getString("descrizione"), false);
                    pezzo.setNumero_totale_pezzi(rs.getInt("numero_totale_pezzi"), false);
                    pezzo.setPrezzo_vendita(rs.getFloat("prezzo_vendita"), false);
                    ApplicationData.pezzi.add(pezzo);
                }

                rs = st.executeQuery("SELECT * FROM main.Privato;");
                while (rs.next()) {
                    Privato privato = new Privato(rs.getString("cf"), false);
                    privato.setTelefono(rs.getString("telefono"), false);
                    //privato.setIndirizzo(rs.getString("indirizzo"), false);
                    privato.setCf(rs.getString("cf"), false);
                    privato.setCognome(rs.getString("cognome"), false);
                    privato.setNome(rs.getString("nome"), false);
                    ApplicationData.privati.add(privato);
                }

                rs = st.executeQuery("SELECT * FROM main.R7;");
                while (rs.next()) {
                    R7 r7 = new R7(rs.getInt("lavoro"), rs.getInt("guasto"), false);
                    r7.setGuasto(rs.getInt("guasto"), false);
                    r7.setLavoro(rs.getInt("lavoro"), false);
                    ApplicationData.r7.add(r7);
                }

                rs = st.executeQuery("SELECT * FROM main.R8;");
                while (rs.next()) {
                    R8 r8 = new R8(rs.getInt("lavoro"), rs.getInt("manutenzione"), false);
                    r8.setManutenzione(rs.getInt("manutenzione"), false);
                    r8.setLavoro(rs.getInt("lavoro"), false);
                    ApplicationData.r8.add(r8);
                }

                rs = st.executeQuery("SELECT * FROM main.Usato8;");
                while (rs.next()) {
                    Usato usato = new Usato(rs.getInt("lavoro"), rs.getInt("pezzo"), false);
                    usato.setPezzo(rs.getInt("pezzo"), false);
                    usato.setLavoro(rs.getInt("lavoro"), false);
                    usato.setNumero_pezzi(rs.getInt("numero_pezzi"), false);
                    ApplicationData.usato.add(usato);
                }*/

                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            }
            MainActivity.errorRetrievingData = false;
        } catch (Exception e) {
            MainActivity.errorRetrievingData = true;
            s = "Errore di reperimento dei dati, contattare l'amministratore del servizio.";
            return s;
        }
        return s;
    }

    @Override
    protected void onPostExecute(String result) {
        // here go all the graphics stuffs
        if (MainActivity.isWrong) {
            toast.cancel();
            Toast.makeText(
                    MainActivity.ctx, result, Toast.LENGTH_SHORT).show();
            new LoginDialog(MainActivity.ctx);
        } else if (MainActivity.errorRetrievingData) {
            toast.cancel();
            Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_SHORT).show();
        } else {
            toast.cancel();
            Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_LONG).show();
        }
        //ApplicationData.veicoli.get(1).updateValueInDataBase("jyfjrd", "targa");
        MainActivity.act.finish();
        MainActivity.act.startActivity(MainActivity.intent);

    }
}
