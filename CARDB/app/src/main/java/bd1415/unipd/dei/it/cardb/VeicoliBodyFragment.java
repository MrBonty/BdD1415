package bd1415.unipd.dei.it.cardb;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Lavoro;
import bd1415.unipd.dei.it.cardb.databasetables.Modello;
import bd1415.unipd.dei.it.cardb.databasetables.Privato;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class VeicoliBodyFragment extends Fragment {

    private ViewHolder viewHolder = null;

    private int mPos = -1;
    private boolean mIsVis = false;
    private ImageView mImage;
    private LinearLayout mBody;
    private boolean isPriv;

    private Veicolo mCurrentV;

    private LavoriArrayAdapter lv;

    //onCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle args = this.getArguments();

        if (args != null) {
            mPos = args.getInt(VeicoliMenuFragment.POS);
            mIsVis = args.getBoolean(VeicoliMenuFragment.ISVIS);
        }

        final View view = inflater.inflate(R.layout.veicoli_body_fragment, container, false);

        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_veicoli);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_veicoli);
            mBody.setVisibility(View.VISIBLE);
        } else {
            mImage = (ImageView) view.findViewById(R.id.image_veicoli);
            mBody = (LinearLayout) view.findViewById(R.id.ll_veicoli);
            if (mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }
        viewHolder.targa = (TextView) view.findViewById(R.id.veicolo_targa_data);
        viewHolder.numero_telaio = (TextView) view.findViewById(R.id.veicolo_telaio_data);
        viewHolder.proprietario = (TextView) view.findViewById(R.id.veicolo_proprietario_data);
        viewHolder.marca = (TextView) view.findViewById(R.id.veicolo_modello_marca_data);
        viewHolder.modello = (TextView) view.findViewById(R.id.veicolo_modello_nome_data);
        viewHolder.anno_modello = (TextView) view.findViewById(R.id.veicolo_modello_anno_data);
        viewHolder.lavorazioni = (ListView) view.findViewById(android.R.id.list);
        viewHolder.addWork = (Button) view.findViewById(R.id.veicolo_lavoro_button);

        if (mIsVis) {
            final Veicolo veicolo = ApplicationData.veicoli.get(mPos);
            mCurrentV = veicolo;
            viewHolder.targa.setText(veicolo.getTarga());
            viewHolder.numero_telaio.setText(veicolo.getNumero_telaio());
            viewHolder.proprietario.setText(veicolo.getPrivato());

            isPriv = true;


            if (veicolo.getAzienda() != null && !veicolo.getAzienda().equals("")) {
                isPriv = false;
                for (int i = 0; i < ApplicationData.aziende.size(); i++) {
                    Azienda tmp = ApplicationData.aziende.get(i);
                    if (tmp.getPiva().equals(veicolo.getAzienda())) {
                        viewHolder.proprietario.setText(tmp.getNome());
                        break;
                    }
                }
            } else {
                isPriv = true;
                for (int i = 0; i < ApplicationData.privati.size(); i++) {
                    Privato tmp = ApplicationData.privati.get(i);
                    if (tmp.getCf().equals(veicolo.getPrivato())) {
                        viewHolder.proprietario.setText(tmp.getNome() + " " + tmp.getCognome());
                        break;
                    }
                }
            }

            Modello tmp = null;
            for (int i = 0; i < ApplicationData.modelli.size(); i++) {
                tmp = ApplicationData.modelli.get(i);
                if (tmp.getCodice_produzione().equals(veicolo.getModello_cod_prod()) &&
                        tmp.getMarca().equals(veicolo.getModello_marca())) {
                    break;
                }
            }
            viewHolder.marca.setText(tmp.getMarca());
            viewHolder.anno_modello.setText(tmp.getAnno());
            viewHolder.modello.setText(tmp.getNome());


            ArrayList<Lavoro> tmp1 = new ArrayList<>();
            for (int i = 0; i < ApplicationData.lavori.size(); i++) {
                Lavoro lv = ApplicationData.lavori.get(i);
                if (veicolo.getNumero_telaio().equals(lv.getVeicolo())) {
                    tmp1.add(lv);
                }
            }

            lv = new LavoriArrayAdapter(MainActivity.ctx, tmp1);
            viewHolder.lavorazioni.setAdapter(lv);

            viewHolder.addWork.setOnClickListener(openDialog());
            viewHolder.numero_telaio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.numero_telaio.getText().toString(),
                            false, MainActivity.ctx, viewHolder.numero_telaio).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        veicolo.setNumero_telaio(viewHolder.numero_telaio.getText().toString(), true);
                                                        VeicoliMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });

            viewHolder.targa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.targa.getText().toString(),
                            false, MainActivity.ctx, viewHolder.targa).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        veicolo.setTarga(viewHolder.targa.getText().toString(), true);
                                                        VeicoliMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });

            viewHolder.marca.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.marca.getText().toString(),
                            true, MainActivity.ctx, viewHolder.marca, new ModelloArrayAdapter(MainActivity.ctx, ApplicationData.modelli), true).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        try {
                                                            Modello az = ApplicationData.modelli.get(Integer.parseInt(viewHolder.marca.getText().toString()));
                                                            viewHolder.marca.setText(az.getMarca());
                                                            viewHolder.modello.setText(az.getNome());
                                                            viewHolder.anno_modello.setText(az.getAnno());
                                                            veicolo.setModello_cod_prod(az.getCodice_produzione(), true);
                                                            veicolo.setModello_marca(az.getMarca(), true);
                                                        } catch (java.lang.NumberFormatException ex) {

                                                        }
                                                    }
                                                }
                    );
                }
            });

            viewHolder.modello.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.modello.getText().toString(),
                            true, MainActivity.ctx, viewHolder.modello, new ModelloArrayAdapter(MainActivity.ctx, ApplicationData.modelli), true).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        try {
                                                            Modello az = ApplicationData.modelli.get(Integer.parseInt(viewHolder.modello.getText().toString()));
                                                            viewHolder.marca.setText(az.getMarca());
                                                            viewHolder.modello.setText(az.getNome());
                                                            viewHolder.anno_modello.setText(az.getAnno());
                                                            veicolo.setModello_cod_prod(az.getCodice_produzione(), true);
                                                            veicolo.setModello_marca(az.getMarca(), true);
                                                        } catch (java.lang.NumberFormatException ex) {

                                                        }
                                                    }
                                                }
                    );
                }
            });
            viewHolder.anno_modello.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.anno_modello.getText().toString(),
                            true, MainActivity.ctx, viewHolder.anno_modello, new ModelloArrayAdapter(MainActivity.ctx, ApplicationData.modelli), true).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        try {
                                                            Modello az = ApplicationData.modelli.get(Integer.parseInt(viewHolder.anno_modello.getText().toString()));
                                                            viewHolder.marca.setText(az.getMarca());
                                                            viewHolder.modello.setText(az.getNome());
                                                            viewHolder.anno_modello.setText(az.getAnno());
                                                            veicolo.setModello_cod_prod(az.getCodice_produzione(), true);
                                                            veicolo.setModello_marca(az.getMarca(), true);
                                                        } catch (java.lang.NumberFormatException ex) {

                                                        }
                                                    }
                                                }
                    );
                }
            });
            viewHolder.proprietario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPriv) {
                        SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.proprietario.getText().toString(),
                                false, MainActivity.ctx, viewHolder.proprietario, new PrivatiArrayAdapter(MainActivity.ctx, ApplicationData.privati), true).build();
                        dialog.show();
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                        @Override
                                                        public void onDismiss(DialogInterface arg0) {
                                                            try {
                                                                String c = viewHolder.proprietario.getText().toString();
                                                                if (!c.equals(null)) {
                                                                    Privato pr = ApplicationData.privati.get(Integer.parseInt(c));
                                                                    veicolo.setPrivato(pr.getCf(), true);
                                                                    viewHolder.proprietario.setText("CF: " + pr.getCf() + " -- Cognome: " + pr.getCognome() + " Nome: " + pr.getNome());
                                                                    PrivatiMenuFragment.list.notifyDataSetChanged();

                                                                }
                                                            } catch (java.lang.NumberFormatException ex) {

                                                            }
                                                        }
                                                    }
                        );
                    } else {
                        SpinnerDialog dialog = new SpinnerDialog.Builder(viewHolder.proprietario.getText().toString(),
                                false, MainActivity.ctx, viewHolder.proprietario, new AziendeArrayAdapter(MainActivity.ctx, ApplicationData.aziende), true).build();
                        dialog.show();
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                        @Override
                                                        public void onDismiss(DialogInterface arg0) {
                                                            try {
                                                                String c = viewHolder.proprietario.getText().toString();
                                                                if (!c.equals(null)) {
                                                                    Azienda az = ApplicationData.aziende.get(Integer.parseInt(c));
                                                                    veicolo.setAzienda(az.getPiva(), true);
                                                                    viewHolder.proprietario.setText("PIVA: " + az.getPiva() + "-- Nome: " + az.getNome());
                                                                    AziendeMenuFragment.list.notifyDataSetChanged();
                                                                }
                                                            } catch (java.lang.NumberFormatException ex) {

                                                            }
                                                        }
                                                    }
                        );
                    }
                }
            });
            //TODO relink a scheda proprietario
            /*viewHolder.proprietario.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MainActivity.container.removeAllViewsInLayout();
                    if (isPriv) {
                        MainActivity.container.addView(MainActivity.privatiLayout);
                    } else {
                        MainActivity.container.addView(MainActivity.aziendeLayout);
                    }
                    return false;
                }
            });*/

        }
        return view;
    }

    private View.OnClickListener openDialog() {
        return new View.OnClickListener() {

            private Dialog toShow;

            @Override
            public void onClick(View v) {
                Context mCtx = MainActivity.ctx;

                toShow = new Dialog(mCtx);
                toShow.requestWindowFeature(Window.FEATURE_NO_TITLE);
                toShow.setContentView(R.layout.picker_dialog);

                LinearLayout dialogLayout = (LinearLayout) toShow.findViewById(R.id.dialog);
                dialogLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                });

                Spinner spin = (Spinner) toShow.findViewById(R.id.spinner_fatture);
                spin.setVisibility(View.GONE);

                TextView title = (TextView) toShow.findViewById(R.id.title);
                title.setText("Vuoi aggiungere un nuovo lavoro?");

                Button cancel = (Button) toShow.findViewById(R.id.cancel);
                Button add = (Button) toShow.findViewById(R.id.add);


                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNewWork();
                        toShow.dismiss();
                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        toShow.dismiss();
                    }
                });
                toShow.show();
            }
        };
    }

    private void addNewWork() {
        String toShow = "Lavoro aggiunto con id: ";

        Lavoro tmp = new Lavoro(mCurrentV.getNumero_telaio(), true);
        String date = Util.getDate()[0];//TODO method for generate a date
        tmp.setData_inizio(date, true);
        date = Util.getDate()[1];
        tmp.setData_inizio(date, false);

        toShow += tmp.getId();


        ApplicationData.lavoriInCorso.add(tmp);
        LavorazioniMenuFragment.list.notifyDataSetChanged();
        lv.add(tmp);

        final Dialog show = new Dialog(MainActivity.ctx);

        show.requestWindowFeature(Window.FEATURE_NO_TITLE);
        show.setContentView(R.layout.picker_dialog);

        Spinner spin = (Spinner) show.findViewById(R.id.spinner_fatture);
        spin.setVisibility(View.GONE);

        Button cancel = (Button) show.findViewById(R.id.cancel);
        Button add = (Button) show.findViewById(R.id.add);
        View v2 = (View) show.findViewById(R.id.view2);

        TextView title = (TextView) show.findViewById(R.id.title);
        title.setText(toShow);

        cancel.setText("OK");

        add.setVisibility(View.GONE);
        v2.setVisibility(View.GONE);


        android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(0, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        cancel.setLayoutParams(params);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show.dismiss();
            }
        });
        show.show();
    }

    private class ViewHolder {
        public TextView targa;
        public TextView numero_telaio;
        public TextView proprietario;
        public TextView marca;
        public TextView modello;
        public TextView anno_modello;
        public ListView lavorazioni;
        public Button addWork;
    }

}
