package com.mystic.todolistapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class TimePickerDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.timepicker, null);
        assert  getActivity() != null ;
        return new AlertDialog.Builder(getActivity())
                .setTitle("Pls set time")
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
