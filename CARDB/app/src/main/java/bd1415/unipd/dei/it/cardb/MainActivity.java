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
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Privato;

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
    private ExpandableListView leftDrawerList;
    private ArrayAdapter<String> navigationDrawerAdapter;
    private ExpandableListAdapter exp;
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
    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

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
        savedInstanceState = null;
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
        prv.setNome("7378", false);
        ApplicationData.privati.add(prv);
    }

    //mercoledì 6 ore 16:00 via san crispino 82 padova

    private void initView() {
        prepareListData();
        leftDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationDrawerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, leftSliderData);
        exp = new ExpandableListAdapter(MainActivity.this, listDataHeader, listDataChild);
        leftDrawerList.setAdapter(exp);
    }

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Clienti");
        listDataHeader.add("Veicoli");
        listDataHeader.add("Lavorazioni");
        listDataHeader.add("Pagamenti");
        listDataHeader.add("Gestione");

        List<String> clientiSubItems = new ArrayList<String>();
        clientiSubItems.add("Privato");
        clientiSubItems.add("Azienda");

        List<String> veicoliSubItems = new ArrayList<String>();

        List<String> lavorazioniSubItems = new ArrayList<String>();
        lavorazioniSubItems.add("Descirzioni");
        lavorazioniSubItems.add("In corso");
        lavorazioniSubItems.add("Finiti");

        List<String> pagamentiSubItems = new ArrayList<String>();
        pagamentiSubItems.add("Pagate");
        pagamentiSubItems.add("Non pagate");

        List<String> gestioneSubItems = new ArrayList<String>();
        gestioneSubItems.add("Magazzino");
        gestioneSubItems.add("Fornitori");
        gestioneSubItems.add("Ordini in corso");
        gestioneSubItems.add("Personale");

        listDataChild.put(listDataHeader.get(0), clientiSubItems);
        listDataChild.put(listDataHeader.get(1), veicoliSubItems);
        listDataChild.put(listDataHeader.get(2), lavorazioniSubItems);
        listDataChild.put(listDataHeader.get(3), pagamentiSubItems);
        listDataChild.put(listDataHeader.get(4), gestioneSubItems);
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
        // Listview Group click listener
        leftDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int position, long id) {
                if (position == 0) {
                    container.removeAllViewsInLayout();
                    container.addView(clienti);
                    corrente = clienti;
                } else if (position == 1) {
                    container.removeAllViewsInLayout();
                    container.addView(veicoliLayout);
                    corrente = veicoliLayout;
                    drawerLayout.closeDrawer(Gravity.LEFT);
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
                return false;
            }
        });

        // Listview Group expanded listener
        leftDrawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition == 1) {
                    leftDrawerList.collapseGroup(1);
                }
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        leftDrawerList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        leftDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    @Override
    public void onMenufrag(String s) {

    }

    /*private void selectItem(int position) {
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
    }*/

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

    /*private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }*/
}