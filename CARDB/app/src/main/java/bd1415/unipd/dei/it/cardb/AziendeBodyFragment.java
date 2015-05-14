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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bd1415.unipd.dei.it.cardb.databasetables.AddressType;
import bd1415.unipd.dei.it.cardb.databasetables.Azienda;
import bd1415.unipd.dei.it.cardb.databasetables.Modello;
import bd1415.unipd.dei.it.cardb.databasetables.Veicolo;

public class AziendeBodyFragment extends Fragment {

    private ViewHolder viewHolder = null;

    private int mPos = -1;
    private boolean mIsVis = false;
    private ImageView mImage;
    private LinearLayout mBody;

    private Azienda az;
    private ArrayList<Veicolo> veicoliArray;
    private VeicoliArrayAdapter veicoliArrayAdapter;

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
            mPos = args.getInt(AziendeMenuFragment.POS);
            mIsVis = args.getBoolean(AziendeMenuFragment.ISVIS);
        }

        final View view = inflater.inflate(R.layout.clienti_body_fragment, container, false);

        viewHolder = new ViewHolder();
        if (mIsVis) {
            mImage = (ImageView) view.findViewById(R.id.image_clienti);
            mImage.setVisibility(View.GONE);
            mBody = (LinearLayout) view.findViewById(R.id.ll_clienti);
            mBody.setVisibility(View.VISIBLE);
        } else {
            mImage = (ImageView) view.findViewById(R.id.image_clienti);
            mBody = (LinearLayout) view.findViewById(R.id.ll_clienti);
            if (mImage != null && mBody != null) {
                mImage.setVisibility(View.VISIBLE);
                mBody.setVisibility(View.GONE);
            }
        }
        viewHolder.nome = (TextView) view.findViewById(R.id.cliente_nome_data);
        viewHolder.cognomeLayout = (LinearLayout) view.findViewById(R.id.cliete_cognome_layout);
        viewHolder.cognome = (TextView) view.findViewById(R.id.cliente_cognome_data);
        viewHolder.pkTag = (TextView) view.findViewById(R.id.cliente_pk_tag);
        viewHolder.pk = (TextView) view.findViewById(R.id.cliente_pk_data);
        viewHolder.telefono = (TextView) view.findViewById(R.id.cliente_telefono_data);
        viewHolder.citta = (TextView) view.findViewById(R.id.cliente_citta_data);
        viewHolder.provincia = (TextView) view.findViewById(R.id.cliente_provincia_data);
        viewHolder.indirizzo = (TextView) view.findViewById(R.id.cliente_indirizzo_data);
        viewHolder.veicoli = (ListView) view.findViewById(android.R.id.list);
        viewHolder.numero_civico = (TextView) view.findViewById(R.id.cliente_civico_data);

        viewHolder.buttonll = (LinearLayout) view.findViewById(R.id.ll_button_clienti);
        viewHolder.addVeic = (Button) view.findViewById(R.id.add_veicolo_button);

        if (mIsVis) {
            az = ApplicationData.aziende.get(mPos);

            viewHolder.nome.setText(az.getNome());
            viewHolder.cognomeLayout.setVisibility(View.GONE);

            viewHolder.pkTag.setText(R.string.cliente_piva_tag);
            viewHolder.pk.setText(az.getPiva());

            viewHolder.telefono.setText(az.getTelefono());

            viewHolder.buttonll.setVisibility(View.VISIBLE);

            if (az.getIndirizzo() != null) {
                viewHolder.indirizzo.setText(az.getIndirizzo().indirizzo);
                viewHolder.numero_civico.setText(az.getIndirizzo().numero_civico + "");
                viewHolder.citta.setText(az.getIndirizzo().città);
                viewHolder.provincia.setText(az.getIndirizzo().provincia);
            }

            veicoliArray = new ArrayList<>();
            for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
                Veicolo vl = ApplicationData.veicoli.get(i);
                if (az.getPiva().equals(vl.getAzienda())) {
                    veicoliArray.add(vl);
                }
            }
            veicoliArrayAdapter = new VeicoliArrayAdapter(MainActivity.ctx, veicoliArray);
            viewHolder.veicoli.setAdapter(veicoliArrayAdapter);


            viewHolder.nome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.nome.getText().toString(),
                            false, MainActivity.ctx, viewHolder.nome).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        az.setNome(viewHolder.nome.getText().toString(), true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.pk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.pk.getText().toString(),
                            false, MainActivity.ctx, viewHolder.pk).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        String newpiva = viewHolder.pk.getText().toString();
                                                        for (int i = 0; i < ApplicationData.veicoli.size(); i++) {
                                                            Veicolo tmp = ApplicationData.veicoli.get(i);
                                                            if (az.getPiva().equals(tmp.getAzienda())) {
                                                                ApplicationData.veicoli.get(i).setAzienda(newpiva, false);
                                                            }
                                                        }

                                                        az.setPiva(newpiva, true);
                                                        VeicoliMenuFragment.list.notifyDataSetChanged();

                                                    }
                                                }
                    );
                }
            });
            viewHolder.telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.telefono.getText().toString(),
                            false, MainActivity.ctx, viewHolder.telefono).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        az.setTelefono(viewHolder.telefono.getText().toString(), true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.citta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.citta.getText().toString(),
                            false, MainActivity.ctx, viewHolder.citta).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.città = viewHolder.citta.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.indirizzo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.indirizzo.getText().toString(),
                            false, MainActivity.ctx, viewHolder.indirizzo).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.indirizzo = viewHolder.indirizzo.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.provincia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.provincia.getText().toString(),
                            false, MainActivity.ctx, viewHolder.provincia).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.provincia = viewHolder.provincia.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
            viewHolder.numero_civico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogEdit dialog = new DialogEdit.Builder(viewHolder.numero_civico.getText().toString(),
                            false, MainActivity.ctx, viewHolder.numero_civico).build();
                    dialog.show();
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        AddressType n = az.getIndirizzo();
                                                        if (n == null) {
                                                            n = new AddressType();
                                                        }
                                                        n.numero_civico = viewHolder.numero_civico.getText().toString();
                                                        az.setIndirizzo(n, true);
                                                        AziendeMenuFragment.list.notifyDataSetChanged();
                                                    }
                                                }
                    );
                }
            });
        }
        return view;
    }

    private View.OnClickListener addVeicolo() {
        return new View.OnClickListener() {
            protected Modello mod;

            private EditText targa;
            private EditText numero_telaio;

            protected TextView marca;
            protected TextView modello;
            protected TextView anno_modello;

            private Button addOldMod;
            private Button addNewMod;

            private Button cancel;
            private Button save;
            private Dialog dialogVe;

            @Override
            public void onClick(View v) {
                dialogVe = new Dialog(MainActivity.ctx);
                dialogVe.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogVe.setContentView(R.layout.insert_veicolo);

                LinearLayout dialogLayout = (LinearLayout) dialogVe.findViewById(R.id.ll_ins_veic);
                dialogLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                });

                dialogVe.setCanceledOnTouchOutside(false);

                targa = (EditText) dialogVe.findViewById(R.id.veicolo_targa_data);
                numero_telaio = (EditText) dialogVe.findViewById(R.id.veicolo_telaio_data);

                marca = (TextView) dialogVe.findViewById(R.id.veicolo_modello_marca_data);
                modello = (TextView) dialogVe.findViewById(R.id.veicolo_modello_nome_data);
                anno_modello = (TextView) dialogVe.findViewById(R.id.veicolo_modello_anno_data);

                addOldMod = (Button) dialogVe.findViewById(R.id.veicolo_mod_old_button);
                addNewMod = (Button) dialogVe.findViewById(R.id.veicolo_mod_new_button);

                cancel = (Button) dialogVe.findViewById(R.id.edit_cancel);
                save = (Button) dialogVe.findViewById(R.id.edit_ok);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogVe.dismiss();
                    }
                });
                addNewMod.setOnClickListener(addModello());

                addOldMod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SpinnerDialog dialog = new SpinnerDialog.Builder(marca.getText().toString(),
                                true, MainActivity.ctx, marca, new ModelloArrayAdapter(MainActivity.ctx, ApplicationData.modelli), true).build();
                        dialog.show();
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                        @Override
                                                        public void onDismiss(DialogInterface arg0) {
                                                            try {
                                                                mod = ApplicationData.modelli.get(Integer.parseInt(marca.getText().toString()));
                                                                marca.setText(mod.getMarca());
                                                                modello.setText(mod.getNome());
                                                                anno_modello.setText(mod.getAnno());

                                                            } catch (java.lang.NumberFormatException ex) {

                                                            }
                                                        }
                                                    }
                        );
                    }
                });

                save.setOnClickListener(saveVeic());
                dialogVe.show();
            }

            private View.OnClickListener saveVeic() {
                return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String targ = targa.getText().toString();
                        String num_telaio = numero_telaio.getText().toString();

                        if (mod == null) {
                            Toast.makeText(MainActivity.ctx, "Inserire modello", Toast.LENGTH_SHORT).show();
                        } else if (targ == null || targ.equals("")) {
                            Toast.makeText(MainActivity.ctx, "Targa non valida", Toast.LENGTH_SHORT).show();
                        } else if (num_telaio == null || num_telaio.equals("") || num_telaio.length() != 17) {
                            Toast.makeText(MainActivity.ctx, "numero telaio non valido", Toast.LENGTH_SHORT).show();
                        } else {
                            Veicolo ve = new Veicolo(num_telaio, mod.getCodice_produzione(), mod.getMarca(), true);
                            ve.setAzienda(az.getPiva(), true);
                            ve.setTarga(targ, true);

                            veicoliArray.add(ve);
                            veicoliArrayAdapter.notifyDataSetChanged();

                            ApplicationData.veicoli.add(ve);
                            VeicoliMenuFragment.list.notifyDataSetChanged();
                            dialogVe.dismiss();
                        }
                    }
                };
            }

            private View.OnClickListener addModello() {
                return new View.OnClickListener() {
                    private Dialog dialog;
                    private EditText marcaMod;
                    private EditText modelloMod;
                    private EditText anno_modelloMod;
                    private EditText codice_produzione;

                    private boolean toSetView;

                    @Override
                    public void onClick(View v) {
                        dialog = new Dialog(MainActivity.ctx);

                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.insert_modello);

                        LinearLayout dialogLayout = (LinearLayout) dialog.findViewById(R.id.ll_ins_mod);
                        dialogLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            }
                        });
                        dialog.setCanceledOnTouchOutside(false);


                        marcaMod = (EditText) dialog.findViewById(R.id.modello_marca_data);
                        modelloMod = (EditText) dialog.findViewById(R.id.modello_nome_data);
                        anno_modelloMod = (EditText) dialog.findViewById(R.id.modello_anno_data);
                        codice_produzione = (EditText) dialog.findViewById(R.id.modello_codice_data);

                        cancel = (Button) dialog.findViewById(R.id.edit_cancel);
                        save = (Button) dialog.findViewById(R.id.edit_ok);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        save.setOnClickListener(saveMod());
                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if (toSetView) {
                                    marca.setText(marcaMod.getText().toString());
                                    modello.setText(modelloMod.getText().toString());
                                    anno_modello.setText(anno_modelloMod.getText().toString());
                                }
                            }
                        });
                        dialog.show();
                    }

                    private View.OnClickListener saveMod() {
                        return new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String marca = marcaMod.getText().toString();
                                String codice = codice_produzione.getText().toString();
                                String modello = modelloMod.getText().toString();
                                String anno = anno_modelloMod.getText().toString();

                                if (modello == null) {
                                    modello = "";
                                }
                                if (anno == null) {
                                    anno = "";
                                }

                                if (marca == null || marca.equals("")) {
                                    Toast.makeText(MainActivity.ctx, "Inserire marca", Toast.LENGTH_SHORT).show();
                                } else if (codice == null || codice.equals("")) {
                                    Toast.makeText(MainActivity.ctx, "Inserire codice produzione", Toast.LENGTH_SHORT).show();
                                } else {
                                    mod = new Modello(codice, marca, true);
                                    mod.setAnno(anno, true);
                                    mod.setNome(modello, true);

                                    toSetView = true;

                                    ApplicationData.modelli.add(mod);
                                    dialog.dismiss();
                                }
                            }
                        };
                    }
                };
            }
        };
    }

    private class ViewHolder {
        public TextView nome;
        public LinearLayout cognomeLayout;
        public TextView cognome;
        public TextView pkTag;
        public TextView pk;
        public TextView telefono;
        public TextView citta;
        public TextView provincia;
        public TextView indirizzo;
        public ListView veicoli;
        public TextView numero_civico;
        public LinearLayout buttonll;
        public Button addVeic;
    }


}
