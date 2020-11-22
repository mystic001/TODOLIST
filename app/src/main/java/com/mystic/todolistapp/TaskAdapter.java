package com.mystic.todolistapp;

import android.content.Context;
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

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder>  {
    private List<Task> mTask;
    private Context context ;
    private TaskAdapterListener listener ;

    public TaskAdapter(List<Task> mTask, Context context) {
        this.mTask = mTask;
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
       // holder.box.setChecked(mBindTask.getChecked());

        if (holder.box.isChecked()) {
            mBindTask.setChecked(true);
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


    public class TaskHolder extends RecyclerView.ViewHolder {
        CardView cardView ;
        TextView spectitle ,dateStr;
        ImageView imageView;
        CheckBox box;
        public TaskHolder(View itemview) {
            super(itemview);
            cardView = itemview.findViewById(R.id.card);
            spectitle = itemview.findViewById(R.id.realtitle);
            dateStr = itemview.findViewById(R.id.date_str);
            box = itemview.findViewById(R.id.checkBox);
            imageView = itemview.findViewById(R.id.imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onClickdelete(position);
                        }
                    }
                }
            });

        }


    }


    public interface  TaskAdapterListener{
        void onClickdelete(int position);
    }




}
