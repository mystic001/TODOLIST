package com.mystic.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    private TextView empty_TV ;
    private TextView textView;


    @Override
    protected void onResume() {
        super.onResume();
        setEmptyText();
        mAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        TaskViewModel model = new ViewModelProvider(this).get(TaskViewModel.class);
        model.getUsers().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                Toast.makeText(TaskListActivity.this, "onchanged",Toast.LENGTH_LONG).show();
                //Update recyclerView;
            }
        });
        defineViews();
        mListOfTasks = new ArrayList<>();
        mListOfTasks = TaskLab.get().getTasks();
        mAdapter = new TaskAdapter(mListOfTasks,this);

        mCrimeRecyclerView.setAdapter(mAdapter);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("TaskListActivity",""+mAdapter.getItemCount());
        btn_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnother();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnother();
            }
        });

        removalListener();
    }

    private void startAnother() {
        Intent intent = new Intent(TaskListActivity.this, CreateTaskActivity.class);
        startActivity(intent);
    }

    private void defineViews() {
        btn_float = findViewById(R.id.floatingActionButton);
        mCrimeRecyclerView = findViewById(R.id.cyclerview);
        empty_TV = findViewById(R.id.empty_TV);
        textView = findViewById(R.id.textView);
    }

    private void setEmptyText() {
        if(mAdapter.getItemCount() > 0){
            Log.d("TaskListActivity",""+mAdapter.getItemCount());
            empty_TV.setVisibility(View.GONE);
        }else{
            empty_TV.setVisibility(View.VISIBLE);
        }
    }


    //This deletes the item from d list of tasks
    public void removalListener(){
        mAdapter.setListenerForAdapter(new TaskAdapter.TaskAdapterListener() {
            @Override
            public void onClickdelete(int position) {
                TaskLab.get().removeTask(position);
                mAdapter.notifyItemRemoved(position);
                setEmptyText();
            }
        });

    }

}