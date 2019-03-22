package com.lazy.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lazy.service.Books;
import com.lazy.service.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface iMyAidlInterface;
    private boolean connect;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            connect = true;
            Log.d("client","链接中");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connect = false;
            Log.d("client","断链");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    Books books = new Books("in");
//                    iMyAidlInterface.addBookIn(books);

                    Books books1 = new Books("out");
                    iMyAidlInterface.addBookOut(books1);
//
//                    Books books2 = new Books("inout");
//                    iMyAidlInterface.addBookInOut(books2);

                    log();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        Intent intent = new Intent();
        intent.setPackage("com.lazy.service");
        intent.setAction("com.lazy.service.action");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private void log(){
        try {
            for (Books books : iMyAidlInterface.getBookList()){
                Log.d("client",books.getName());
            }
        }catch (Exception e){

        }

    }


}
