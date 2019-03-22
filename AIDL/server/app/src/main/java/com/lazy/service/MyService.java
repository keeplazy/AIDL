package com.lazy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    private ArrayList<Books> bookslist;
    @Override
    public void onCreate() {
        super.onCreate();
        bookslist = new ArrayList<>();
        bookslist.add(new Books("深入了解java"));
        bookslist.add(new Books("深入了解python"));
        bookslist.add(new Books("深入了解oc"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private final IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {

        @Override
        public List<Books> getBookList() throws RemoteException {
            return bookslist;
        }

        @Override
        public void addBookOut(Books book) throws RemoteException {
            if (null == book){
                Log.d("service","out is null");
            }else {
                Log.d("service",book.getName());
                bookslist.add(book);
            }
        }

        @Override
        public void addBookIn(Books book) throws RemoteException {
            Log.d("service",book.getName());
            bookslist.add(book);
        }

        @Override
        public void addBookInOut(Books book) throws RemoteException {
            Log.d("service",book.getName());
            bookslist.add(book);
        }
    };
}
