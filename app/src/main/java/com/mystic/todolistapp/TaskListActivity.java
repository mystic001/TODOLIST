package com.mystic.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {


    private RecyclerView mCrimeRecyclerView;
    private List<Task> mListOfTasks;
    private TaskAdapter mAdapter;
    private FloatingActionButton btn_float;


    @Override
    protected void onResume() {
        super.onResume();
        updateInterface();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        btn_float = findViewById(R.id.floatingActionButton);
        mListOfTasks = new ArrayList<>();

        mCrimeRecyclerView = findViewById(R.id.cyclerview);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        btn_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskListActivity.this, CreateTaskActivity.class);
                startActivity(intent);
            }
        });

    }

    private void updateInterface() {
        mListOfTasks = TaskLab.get().getTasks();
        if( mAdapter == null){
            mAdapter = new TaskAdapter(mListOfTasks,this);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }

    }
}