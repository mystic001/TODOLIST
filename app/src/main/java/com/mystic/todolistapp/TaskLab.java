package com.mystic.todolistapp;
import android.app.Application;

import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TaskLab {
    private List<Task> mListTask;
    private LiveData<List<Task>> tasks;

    public TaskLab(Application application) {
        TaskDatabase database = TaskDatabase.getTaskDatabaseInstance(application);
        TaskDao taskDao = database.taskDao();
        //Dont worry this operation to getAllTasks is automatically carried out in the database
        //Because the method needed to display all task uses @query which automatically executes in the background;
        //For every other operation you have to run them in the background yourself;
        tasks = taskDao.getAllTasks();
        mListTask = new ArrayList<>();
    }


    public LiveData<List<Task>> getTasksLive(){
        return tasks;
    }

    public void addTask(Task task){
        insertTasks(task);
    }

    private void insertTasks(Task task){
        addTasks(task).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Task>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull List<Task> tasks) {
                        mListTask = tasks;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

   //The task we want to add is being based as a parameter to the Single addtask
    public Single<List<Task>> addTasks(final Task task){
        return Single.fromCallable(new Callable<List<Task>>() {
            @Override
            public List<Task> call() {
                mListTask.add(task);
              return mListTask;
            }
        });

    }



    public void removeTask(int position){
        mListTask.remove(position);
    }

}

