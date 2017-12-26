package com.example.hp.beyfikar.dialogbox;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hp on 12/14/2017.
 */

public class MessageBox {

    private Context context;

    public MessageBox(Context context) {
        this.context = context;

    }
    public void showMessage(final String msg) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Alert Message")
                .setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //  Snackbar.make( constraintLayout, msg ,Snackbar.LENGTH_SHORT).show();
                      //  Log.d(TAG, "showMessageBox: " + msg);
                        Toast.makeText(context, "OKKKKKKKKKKKK", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
