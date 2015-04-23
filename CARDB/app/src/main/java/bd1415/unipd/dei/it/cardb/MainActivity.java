package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class MainActivity extends ActionBarActivity implements LavorazioniMenuFragment.OnMenufragListener,
        PagamentiMenuFragment.OnMenufragListener, GestioneMenuFragment.OnMenufragListener {

    public static String[] params;
    public static boolean isLogged = false;
    public static boolean isWrong = false;
    public static boolean errorRetrievingData = false;
    public static Context ctx;
    public static Activity act;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private String[] leftSliderData = {"Clienti", "Veicoli", "Lavorazioni", "Pagamenti", "Gestione"};
    public static FrameLayout container;
    private LinearLayout clienti;
    private LinearLayout veicoliLayout;
    private LinearLayout lavorazioni;
    private LinearLayout pagamenti;
    private LinearLayout gestione;
    private View current;
    private FloatingActionButton fabButton = null;
    public static LinearLayout corrente;
    public static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        params = new String[6];
        ctx = this;
        act = this;
        params[0] = "username";
        params[1] = "password";
        params[2] = "login.dei.unipd.it";
        params[3] = "dbstud2.dei.unipd.it";
        params[4] = "3366";
        params[5] = "5432";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (toolbar != null) {
            toolbar.setTitle("Clienti");
            setSupportActionBar(toolbar);
        }
        initDrawer();
        container = (FrameLayout) findViewById(R.id.container);
        clienti = (LinearLayout) findViewById(R.id.clienti);
        veicoliLayout = (LinearLayout) findViewById(R.id.veicoli);
        lavorazioni = (LinearLayout) findViewById(R.id.lavorazioni);
        pagamenti = (LinearLayout) findViewById(R.id.pagamenti);
        gestione = (LinearLayout) findViewById(R.id.gestione);
        corrente = clienti;
        ApplicationData.posizioneCorrente = 0;
        container.removeAllViewsInLayout();
        container.addView(corrente);
        fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(
                        getResources().getDrawable(
                                R.drawable.ic_add_black_24dp))
                .withButtonColor(getResources().getColor(R.color.primaryColor))
                .withGravity(Gravity.BOTTOM | Gravity.END)
                .withMargins(0, 0, 16, 16).create();
        intent = getIntent();
        Privato prv = new Privato();
        prv.setCf("7378", false);
        ApplicationData.clienti.add(prv);

    }

    private void initView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationDrawerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, leftSliderData);
        leftDrawerList.setAdapter(navigationDrawerAdapter);
    }

    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                fabButton.showFloatingActionButton();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                fabButton.hideFloatingActionButton();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        leftDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public void onMenufrag(String s) {

    }

    private void selectItem(int position) {
        if (position == 0) {
            container.removeAllViewsInLayout();
            container.addView(clienti);
            corrente = clienti;
        } else if (position == 1) {
            container.removeAllViewsInLayout();
            container.addView(veicoliLayout);
            corrente = veicoliLayout;
        } else if (position == 2) {
            container.removeAllViewsInLayout();
            container.addView(lavorazioni);
        } else if (position == 3) {
            container.removeAllViewsInLayout();
            container.addView(pagamenti);
            corrente = pagamenti;
        } else if (position == 4) {
            container.removeAllViewsInLayout();
            container.addView(gestione);
            corrente = gestione;
        }
        leftDrawerList.setItemChecked(position, true);
        toolbar.setTitle(leftSliderData[position]);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            if (isLogged) {
                Toast.makeText(MainActivity.ctx, "Già correttamente loggato!", Toast.LENGTH_LONG).show();
                return true;
            } else {
                new LoginDialog(ctx, act);
                //Veicolo vc = new Veicolo("pippo", "targa");
            }
            return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}