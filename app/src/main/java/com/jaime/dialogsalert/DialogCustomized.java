package com.jaime.dialogsalert;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jaime on 02/02/2017.
 */

public class DialogCustomized extends DialogFragment {
    private onDialogCustomizedListener listener;


    public interface onDialogCustomizedListener {
        void getMessage(String message);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (onDialogCustomizedListener) activity;
        } catch (ClassCastException e) {
            e.getMessage();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialogCustomized();
    }


    private Dialog createDialogCustomized() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        builder.setView(view);

        final EditText message = (EditText) view.findViewById(R.id.menssage);
        Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.getMessage(message.getText().toString());
                dismiss();
            }
        });

        return builder.create();
    }
}
