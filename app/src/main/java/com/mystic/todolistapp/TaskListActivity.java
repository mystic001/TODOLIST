package com.mystic.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        mAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        btn_float = findViewById(R.id.floatingActionButton);
        mCrimeRecyclerView = findViewById(R.id.cyclerview);

        mListOfTasks = new ArrayList<>();
        mListOfTasks = TaskLab.get().getTasks();
        mAdapter = new TaskAdapter(mListOfTasks,this);

        mCrimeRecyclerView.setAdapter(mAdapter);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskListActivity.this, CreateTaskActivity.class);
                startActivity(intent);
            }
        });

        removalListener();
    }


    //This deletes the item from d list of tasks
    public void removalListener(){
        mAdapter.setListenerForAdapter(new TaskAdapter.TaskAdapterListener() {
            @Override
            public void onClickdelete(int position) {
                TaskLab.get().removeTask(position);
                mAdapter.notifyItemRemoved(position);
            }
        });

    }

}