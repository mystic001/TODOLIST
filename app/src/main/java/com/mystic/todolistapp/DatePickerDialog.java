package com.mystic.todolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerDialog extends DialogFragment {
    private View view;
    private DatePicker mDatePicker;
    private DateListesener listesener ;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listesener = (DateListesener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement Example dialog");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        mDatePicker = view.findViewById(R.id.date_picker);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        listesener.dateListener(year,month,day);
                        Toast.makeText(getActivity(),"didnt get it"+month,Toast.LENGTH_LONG).show();
                    }
                })
                .create();
    }

    public interface  DateListesener{
        void dateListener(int year,int month,int day);
    }

}