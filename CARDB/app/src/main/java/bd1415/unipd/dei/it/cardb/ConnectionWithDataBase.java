package bd1415.unipd.dei.it.cardb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import android.os.AsyncTask;
import android.widget.Toast;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ConnectionWithDataBase extends AsyncTask<String, Void, String> {

	private Toast toast;

	@Override
	protected void onPreExecute() {
		// here go all the graphics things
		toast = Toast.makeText(MainActivity.ctx, "Connecting...", Toast.LENGTH_SHORT);
		toast.show();
	}

	protected String doInBackground(String... params) {
		String s = "";
		Statement st = null;
		Connection con = null;
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
			Session session = jsch.getSession(strSshUser, strSshHost, 22);
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
		}
        catch (Exception e) {
            s = "E' successo qualcosa di molto strano... chiedere assistenza agli sviluppatori!";
            return s;
        }
		try {
			if (st != null && con != null) {
				ResultSet rs = st.executeQuery("SELECT datname FROM pg_database\n" +
                        "WHERE datistemplate = false;");
				while (rs.next()) {
					s = s + rs.getString("datname");
				}
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
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
		// here go all the graphics things
		if (MainActivity.isWrong) {
			Toast.makeText(
					MainActivity.ctx, result, Toast.LENGTH_SHORT).show();
			new LoginDialog(MainActivity.ctx, MainActivity.act);
		} else if (MainActivity.errorRetrievingData) {
			Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_SHORT).show();
		} else {
			toast.cancel();
			Toast.makeText(MainActivity.ctx, result, Toast.LENGTH_LONG).show();
		}
	}
}
