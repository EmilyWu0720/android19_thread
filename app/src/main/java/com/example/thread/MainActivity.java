package com.example.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static int GOTmessage = 0;
    private final static int GOTit = 1;

    TextView txtCount;
    MyHandler hd = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCount = findViewById(R.id.textview);
        Message msg = new Message();
        msg.what = GOTmessage;
        hd.sendMessage(msg);


        new Thread(new Runnable(){
            @Override
            public void run() {
                int count = 0;
                while (true){
                    if(count>=100){
                        Message msg = new Message();
                        msg.what = GOTit;
                        hd.sendMessage(msg);
                    }
                    count+=50;
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }


            }
        }).start();
    }






    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GOTmessage:
                    txtCount.setText("Got message");
                    break;
                case GOTit:
                    txtCount.setText("Got it");
                    break;
            }
        }
    }
}