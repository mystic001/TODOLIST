package com.mystic.todolistapp;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TaskLab {
    private List<Task> mListTask;
    private LiveData<List<Task>> tasks;
    private TaskDao taskDao;
    private static TaskLab sTaskLab;

    private TaskLab(Context context) {
        TaskDatabase database = TaskDatabase.getTaskDatabaseInstance(context.getApplicationContext());
        taskDao = database.taskDao();
        //Dont worry this operation to getAllTasks is automatically carried out in the database
        //Because the method needed to display all task uses @query which automatically executes in the background;
        //For every other operation you have to run them in the background yourself;
        tasks = taskDao.getAllTasks();
        mListTask = new ArrayList<>();
    }

    public static TaskLab getsTaskLab(Context context){
        if(sTaskLab == null){
            sTaskLab = new TaskLab(context);
        }
        return sTaskLab;
    }



    public LiveData<List<Task>> getAllTasks(){
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

   //The task we want to add is being added as a parameter to the Single addtask
    public Single<List<Task>> addTasks(final Task task){
        return Single.fromCallable(() -> {
            taskDao.insert(task);
            return mListTask;
        });

    }


    //the delete operation is carried out here;
    public void removeTask(Task task){
        deleteTask(task);
    }

    private void deleteTask(Task task) {
        deletTask(task).subscribeOn(Schedulers.io())
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

    private Single<List<Task>> deletTask(final Task task) {
        return Single.fromCallable(() -> {
            taskDao.delete(task);
            return mListTask;
        });

    }


    public void updatetask(Task task){
        uptaskInBack(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }



    private Single<Void> uptaskInBack(Task task){
        return Single.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                taskDao.update(task);
                Log.d("TaskLab","I updated succesfuly");
                return null;
            }
        });
    }




}

