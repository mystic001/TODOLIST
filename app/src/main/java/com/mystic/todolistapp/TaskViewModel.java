package com.mystic.todolistapp;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class TaskViewModel extends AndroidViewModel {
    private MutableLiveData<List<Task>> tasks;
    public TaskViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Task>> getUsers() {
        if (tasks == null) {
            tasks = new MutableLiveData<>();
            loadTasks();
        }
        return tasks;
    }

    private void loadTasks() {
        fetchTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MutableLiveData<List<Task>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull MutableLiveData<List<Task>> taskfrombase) {
                        //They are not of the same data type
                        tasks = taskfrombase;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                    }
                });
    //

    }

 public Single<MutableLiveData<List<Task>>> fetchTasks(){
     return Single.fromCallable(new Callable<MutableLiveData<List<Task>>> () {
         @Override
         public MutableLiveData<List<Task>> call() throws Exception {
             MutableLiveData<List<Task>> taskContainer = new MutableLiveData<>();
             return taskContainer;
         }
     });
 }
}
