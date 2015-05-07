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
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bd1415.unipd.dei.it.cardb.databasetables.Privato;

public class MainActivity extends ActionBarActivity {

    public static String[] params;
    public static boolean isLogged = false;
    public static boolean isWrong = false;
    public static boolean errorRetrievingData = false;
    public static Context ctx;
    public static Activity act;
    public static FrameLayout container;
    public static LinearLayout corrente;
    public static Intent intent;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ExpandableListView leftDrawerList;
    private ExpandableListAdapter exp;
    private String[] leftSliderData = {"Clienti", "Veicoli", "Lavorazioni", "Pagamenti", "Gestione"};
    private LinearLayout privatiLayout;
    private LinearLayout aziendeLayout;
    private LinearLayout veicoliLayout;
    public static LinearLayout lavorazioniLayout;
    private LinearLayout descrizioniLayout;
    private LinearLayout pagamentiLayout;
    private LinearLayout gestioneLayout;
    private FloatingActionButton fabButton = null;
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
        //savedInstanceState = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (toolbar != null) {
            toolbar.setTitle("Clienti");
            setSupportActionBar(toolbar);
        }
        initDrawer();
        container = (FrameLayout) findViewById(R.id.container);
        privatiLayout = (LinearLayout) findViewById(R.id.privati);
        aziendeLayout = (LinearLayout) findViewById(R.id.aziende);
        veicoliLayout = (LinearLayout) findViewById(R.id.veicoli);
        lavorazioniLayout = (LinearLayout) findViewById(R.id.lavorazioni);
        descrizioniLayout = (LinearLayout) findViewById(R.id.descrizioni);
        pagamentiLayout = (LinearLayout) findViewById(R.id.pagamenti);
        //gestioneLayout = (LinearLayout) findViewById(R.id.gestione);
        corrente = privatiLayout;
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

    private void initView() {
        prepareListData();
        leftDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
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
        lavorazioniSubItems.add("Descrizioni");
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
                if (position == 1) {
                    container.removeAllViewsInLayout();
                    container.addView(veicoliLayout);
                    corrente = veicoliLayout;
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                /*if (position == 0) {
                    container.removeAllViewsInLayout();
                    container.addView(privatiLayout);
                    corrente = privatiLayout;
                } else if (position == 1) {

                } else if (position == 2) {
                    container.removeAllViewsInLayout();
                    container.addView(lavorazioniLayout);
                } else if (position == 3) {
                    container.removeAllViewsInLayout();
                    container.addView(pagamentiLayout);
                    corrente = pagamentiLayout;
                } else if (position == 4) {
                    container.removeAllViewsInLayout();
                    container.addView(gestioneLayout);
                    corrente = gestioneLayout;
                }*/
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
                //Toast.makeText(getApplicationContext(),
                //        listDataHeader.get(groupPosition) + " Expanded",
                //        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        leftDrawerList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getApplicationContext(),
                //        listDataHeader.get(groupPosition) + " Collapsed",
                //        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        leftDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {
                    if (childPosition == 0) {
                        container.removeAllViewsInLayout();
                        container.addView(privatiLayout);
                        corrente = privatiLayout;
                        drawerLayout.closeDrawer(Gravity.LEFT);

                    } else {
                        container.removeAllViewsInLayout();
                        container.addView(aziendeLayout);
                        corrente = aziendeLayout;
                        drawerLayout.closeDrawer(Gravity.LEFT);

                    }
                } else if (groupPosition == 2) {

                    if (childPosition == 0) {
                        container.removeAllViewsInLayout();
                        container.addView(descrizioniLayout);
                        corrente = descrizioniLayout;
                        drawerLayout.closeDrawer(Gravity.LEFT);

                    } else if (childPosition == 1) {
                        ApplicationData.isFinished = false;
                        container.removeAllViewsInLayout();
                        container.addView(lavorazioniLayout);
                        corrente = lavorazioniLayout;
                        drawerLayout.closeDrawer(Gravity.LEFT);

                    } else if (childPosition == 2) {
                        ApplicationData.isFinished = true;
                        container.removeAllViewsInLayout();
                        container.addView(lavorazioniLayout);
                        corrente = lavorazioniLayout;
                        drawerLayout.closeDrawer(Gravity.LEFT);

                    }

                } else if (groupPosition == 3) {
                    if (childPosition == 0) {
                        ApplicationData.isPayed = true;
                        container.removeAllViewsInLayout();
                        container.addView(lavorazioniLayout);
                        corrente = lavorazioniLayout;
                        drawerLayout.closeDrawer(Gravity.LEFT);

                    } else if (childPosition == 1) {
                        ApplicationData.isPayed = false;
                        container.removeAllViewsInLayout();
                        container.addView(lavorazioniLayout);
                        corrente = lavorazioniLayout;
                        drawerLayout.closeDrawer(Gravity.LEFT);

                    }
                } else if (groupPosition == 4) {
                    if (childPosition == 0) {

                    } else if (childPosition == 1) {

                    } else if (childPosition == 2) {

                    } else if (childPosition == 3) {

                    }
                }
                leftDrawerList.setItemChecked(childPosition, true);
                toolbar.setTitle(listDataChild.get(leftSliderData[groupPosition]).get(childPosition));
                //Toast.makeText(getApplicationContext(),
                //        listDataHeader.get(groupPosition)
                //                + " : " + listDataChild.get(
                //                listDataHeader.get(groupPosition)).get(
                //                childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
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
                new LoginDialog(ctx);
            }
            return true;
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}