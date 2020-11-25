package com.mystic.todolistapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder>  {
    private List<Task> mTask = new ArrayList<>() ;
    private TaskAdapterListener listener ;
    private Context context;


    //I created this constructor and passed context parameter to it so i could use the context in glide
    public TaskAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specificview,parent,false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        final Task mBindTask = mTask.get(position);
        holder.spectitle.setText(mBindTask.getTitle());
        if(mBindTask.getDate() != null){
            holder.dateStr.setText(mBindTask.getDate());
        }


        holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(mBindTask.isDone()){
                    mBindTask.setDone(false);
                    TaskLab.getsTaskLab(context).updatetask(mBindTask);
                } else{
                    mBindTask.setDone(true);
                    TaskLab.getsTaskLab(context).updatetask(mBindTask);
                }
            }
        });

       /* if (holder.box.isChecked()) {
            mBindTask.setChecked(true);

        } else{
            mBindTask.setChecked(false);

        }
        TaskLab.getsTaskLab(context)
                .getTaskDao()
                .update(mBindTask);*/


        if(holder.imag != null){

            Glide.with(context)
                    .asBitmap()
                    .circleCrop()
                    .load(Uri.parse(mBindTask.getImage()))
                    .into(holder.imag);
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


    public interface  TaskAdapterListener{
        void onClickdelete(int position);
    }




}
