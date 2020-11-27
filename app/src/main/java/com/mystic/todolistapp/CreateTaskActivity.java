package com.mystic.todolistapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTaskActivity extends AppCompatActivity implements DatePickerDialog.DateListesener, View.OnClickListener {
    private static final String DIALOG_DATE = "DialogDate";
    //private static final String DIALOG_TIME = "DialogTime" ;
    private TextInputEditText mTitleField;
    private Button setTime;
    private Button btnAdd ;
    private Button btn_setDate;
    private Task mtask = new Task();
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    private Uri photoURI;
    private static final String TAG = "CreateActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);


        mTitleField = findViewById(R.id.title);
        imageView = findViewById(R.id.imgView);
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
        if(photoURI != null){
            mtask.setImage(photoURI.toString());
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
                dispatchTakePictureIntent();
                //TimePickerDialog dialog = new TimePickerDialog();
                //showPopUp(dialog,DIALOG_TIME);
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d(TAG,"started 1");
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            Log.d(TAG,"started 2");
            try {
                photoFile = createImageFile();
                Log.d(TAG,"started 3");
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            // Continue only if the File was successfully created

            if(photoFile != null){
                photoURI = FileProvider.getUriForFile(this, "com.mystic.todolistapp.fileprovider", photoFile);
                Log.d(TAG,"started 4");
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    Log.d(TAG,"started 5");
                    Log.d("Create",""+photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    Log.d(TAG,"started 6");
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Glide.with(getApplicationContext()).load(photoURI).into(imageView);
        }
        /*Log.d(TAG,"started 7");
        Log.d("CreateActivity",""+data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if(data != null){
                Glide.with(getApplicationContext())
                        .load(photoURI)
                        .into(imageView);
                Log.d(TAG,"started 8");
            }
            Log.d(TAG,"started 9");
        }

        if(resultCode == RESULT_CANCELED && requestCode == REQUEST_IMAGE_CAPTURE){
            Toast.makeText(this,"Sorry action cancelled",Toast.LENGTH_LONG).show();
        }*/
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        String currentPhotoPath = image.getAbsolutePath();
        return image;
    }




}