package com.mystic.todolistapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;


import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTaskActivity extends AppCompatActivity implements DatePickerDialog.DateListesener, View.OnClickListener {
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime" ;
    private TextInputEditText mTitleField;
    private Button setTime;
    private Button btnAdd ;
    private Button btn_setDate;
    private Task mtask = new Task();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);


        mTitleField = findViewById(R.id.title);
        setTime = findViewById(R.id.timeseter);
        btnAdd = findViewById(R.id.btn_add);
        btn_setDate = findViewById(R.id.btn_date);

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mtask.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        responder();

    }


    public void addToDo(){

        if( (mTitleField.getText().toString().equals(""))){
            return ;
        }
        mtask.setTitle(mTitleField.getText().toString());
        new TaskViewModel(getApplication()).addtask(mtask);
        finish();
    }

    public void responder(){
        btn_setDate.setOnClickListener(this);
        setTime.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void dateListener(int year, int month, int day) {

        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = new GregorianCalendar(year, month, day).getTime();
        String dateFormatted = formattedDate.format(date);
        mtask.setDate(dateFormatted);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.timeseter:
                TimePickerDialog dialog = new TimePickerDialog();
                showPopUp(dialog,DIALOG_TIME);
                break;
            case R.id.btn_date:
                DatePickerDialog dialog_date = new DatePickerDialog();
                showPopUp(dialog_date,DIALOG_DATE);
                break;
            case R.id.btn_add:
                addToDo();
                break;
            default:
                return ;
        }
    }
//This method triggers the dialog for both date and time;
    private void showPopUp(DialogFragment frag, String str) {
        frag.show(getSupportFragmentManager(),str);
    }

}