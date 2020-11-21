package com.mystic.todolistapp;
import androidx.lifecycle.MutableLiveData;

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
    private static TaskLab sTaskLab;
    private List<Task> mListTask;

    private TaskLab() {
        mListTask = new ArrayList<>();
    }

    public static synchronized TaskLab getInstance() {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab();
        }
        return sTaskLab;
    }


    public MutableLiveData<List<Task>> getTasksLive(){
        loadTasks();
        MutableLiveData<List<Task>> tasks = new MutableLiveData<>();
        tasks.setValue(mListTask);
        return tasks;
    }


    private void loadTasks(){
        fetchTasks().subscribeOn(Schedulers.io())
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


    public Single<List<Task>> fetchTasks(){
        return Single.fromCallable(new Callable<List<Task>>() {
            @Override
            public List<Task> call() throws Exception {
              List<Task> taskbucket = new ArrayList<>();
              return  taskbucket;
            }
        });

    }

    public void addTask(Task task){
        mListTask.add(task);
    }

    public void removeTask(int position){
        mListTask.remove(position);
    }

}

