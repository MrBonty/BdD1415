package bd1415.unipd.dei.it.cardb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LoginDialog {

    public LoginDialog(final Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.login_dialog);
        LinearLayout dialogLayout = (LinearLayout) dialog.findViewById(R.id.dialog);
        dialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        TextView dialogName = (TextView) dialog.findViewById(R.id.title);
        dialogName.setText(R.string.login);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button ok = (Button) dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) dialog.findViewById(R.id.username);
                EditText password = (EditText) dialog.findViewById(R.id.password);
                String s = username.getText().toString();
                String ss = password.getText().toString();
                if (!s.equals("") && !(s == null) && !ss.equals("") && !(ss == null)) {
                    MainActivity.params[0] = s;
                    MainActivity.params[1] = ss;
                    try {
                        Toast.makeText(context, "sono un pirla" + new ConnectionWithDataBase().execute(MainActivity.params).get(), Toast.LENGTH_LONG).show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                } else
                    Toast.makeText(context, "Inserire username e password", Toast.LENGTH_SHORT)
                            .show();
            }
        });
        dialog.show();
    }

}
