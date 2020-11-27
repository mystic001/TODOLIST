package com.mystic.todolistapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Task> mTask = new ArrayList<>() ;
    private TaskAdapterListener listener ;
    private Context context;


    //I created this constructor and passed context parameter to it so i could use the context in glide
    public TaskAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        if(mTask.get(position).getImage() == null){
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == 0){
            //Without image
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.withoutimage,parent,false);
            return new TaskHolderWithoutImage(view);
        }

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specificview, parent, false);
            return new TaskHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Task mBindTask = mTask.get(position);
        //Without image
        if(mBindTask.getImage() == null){

            TaskHolderWithoutImage taskHolder = (TaskHolderWithoutImage) holder;
            taskHolder.spectitle.setText(mBindTask.getTitle());
            if(mBindTask.getDate() != null){
                taskHolder.dateStr.setText(mBindTask.getDate());
            }

            taskHolder.box.setOnCheckedChangeListener((compoundButton, b) -> {
                if(mBindTask.getDone() == 0){
                    mBindTask.setDone(1);
                    taskHolder.box.setChecked(true);
                } else{
                    mBindTask.setDone(0);
                    taskHolder.box.setChecked(false);
                }
                taskHolder.box.isChecked();
                TaskLab.getsTaskLab(context).updateTask(mBindTask);
            });


        } else {
        //With image
            TaskHolder taskHolder = (TaskHolder) holder;
            taskHolder.spectitle.setText(mBindTask.getTitle());
            if(mBindTask.getDate() != null){
                taskHolder.dateStr.setText(mBindTask.getDate());
            }

            taskHolder.box.setOnCheckedChangeListener((compoundButton, b) -> {
                if(mBindTask.getDone() == 0){
                    mBindTask.setDone(1);
                    taskHolder.box.setChecked(true);
                } else{
                    mBindTask.setDone(0);
                    taskHolder.box.setChecked(false);
                }
                taskHolder.box.isChecked();
                TaskLab.getsTaskLab(context).updateTask(mBindTask);
            });


            Glide.with(context)
                    .asBitmap()
                    .circleCrop()
                    .load(Uri.parse(mBindTask.getImage()))
                    .into(taskHolder.imag);

        }

    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }
//Custom listener that makes the adapter listen to clicks
    public void setListenerForAdapter(TaskAdapterListener listener){
        this.listener = listener;
    }

    //The method below is what sets the List of tasks we are getting from the ViewModel to the list of tasks in this adapter
    public void setTask(List<Task> task){
        mTask = task;
        notifyDataSetChanged();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        CardView cardView ;
        ImageView imag;
        TextView spectitle ,dateStr;
        ImageView imageView;
        CheckBox box;
        public TaskHolder(View itemview) {
            super(itemview);
            cardView = itemview.findViewById(R.id.card);
            spectitle = itemview.findViewById(R.id.realtitle);
            dateStr = itemview.findViewById(R.id.date_str);
            box = itemview.findViewById(R.id.checkBox);
            imag = itemview.findViewById(R.id.taskimaage);
            imageView = itemview.findViewById(R.id.imageView);

            imageView.setOnClickListener(view -> {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onClickdelete(position);
                    }
                }
            });

        }


    }

    public class TaskHolderWithoutImage extends RecyclerView.ViewHolder{
        CardView cardView ;
        TextView spectitle ,dateStr;
        ImageView imageView;
        CheckBox box;
        public TaskHolderWithoutImage(@NonNull View itemview) {
            super(itemview);
            cardView = itemview.findViewById(R.id.cardw);
            spectitle = itemview.findViewById(R.id.realtitlew);
            dateStr = itemview.findViewById(R.id.date_strw);
            box = itemview.findViewById(R.id.checkBoxw);
            imageView = itemview.findViewById(R.id.imageVieww);

            imageView.setOnClickListener(view -> {
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onClickdelete(position);
                    }
                }
            });

        }
    }


    public interface  TaskAdapterListener{
        void onClickdelete(int position);
    }




}
