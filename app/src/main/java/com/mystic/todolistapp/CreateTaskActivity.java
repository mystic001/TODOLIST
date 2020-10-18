package com.mystic.todolistapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTaskActivity extends AppCompatActivity implements DatePickerDialog.DateListesener {
    private static final String DIALOG_DATE = "DialogDate";
    private TextInputEditText mTitleField;
    private Button setTime ;
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

        btn_setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DatePickerDialog dialog = new DatePickerDialog();
                dialog.show(manager,DIALOG_DATE);
            };
        });


        btn_setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DatePickerDialog dialog = new DatePickerDialog();
                dialog.show(manager,DIALOG_DATE);

            };
        });

    setTime.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        TimePickerDialog dialog = new TimePickerDialog();
        dialog.show(manager,DIALOG_DATE);
        }
    });


    btnAdd.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         addToDo();
     }

 });

    }


    public void addToDo(){

        if( (mTitleField.getText().toString().equals(""))){
            return ;
        }
        //Task task = new Task();
        //task.setTitle(mTitleField.getText().toString());
       // TaskLab.get().addTask(task);
        mtask.setTitle(mTitleField.getText().toString());
        TaskLab.get().addTask(mtask);
        finish();
    }

    @Override
    public void dateListener(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        mtask.setDate(date);
    }
}