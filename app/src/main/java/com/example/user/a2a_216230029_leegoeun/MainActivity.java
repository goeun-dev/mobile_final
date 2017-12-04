package com.example.user.a2a_216230029_leegoeun;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    TextView text;
    MapView map;
    int mMainValue = 0;
    int mBackValue = 0;
    TextView mMainText, mBackText;
    TextView idView;
    EditText vocaBox;
    EditText meanBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("216230029 이고은");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

                tabHost = findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tab_thread = tabHost.newTabSpec("1").setContent(R.id.tab_thread).setIndicator("thread");
        TabHost.TabSpec tab_data = tabHost.newTabSpec("2").setContent(R.id.tab_data).setIndicator("database");
        TabHost.TabSpec tab_map = tabHost.newTabSpec("3").setContent(R.id.tab_map).setIndicator("google map");

        tabHost.addTab(tab_thread);
        tabHost.addTab(tab_data);
        tabHost.addTab(tab_map);

        text = findViewById(R.id.textView);

        map = findViewById(R.id.map);

        mMainText = findViewById(R.id.mainValue);
        mBackText = findViewById(R.id.backValue);



        idView = findViewById(R.id.vocaID);
        vocaBox = findViewById(R.id.voca);
        meanBox = findViewById(R.id.mean);

    }

    public void clearButtonClick(View view){
        vocaBox.setText("");
        meanBox.setText("");
    }

    public void newVoca (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        Voca voca =
                new Voca(vocaBox.getText().toString(), meanBox.getText().toString());
        dbHandler.addVoca(voca);
        vocaBox.setText("");
        meanBox.setText("");
    }

    public void lookupVoca (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Voca voca =
                dbHandler.findVoca(vocaBox.getText().toString());
        if (voca != null) {
            idView.setText(String.valueOf(voca.getID()));
            meanBox.setText(String.valueOf(voca.getMean()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeVoca (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null,
                null, 1);
        boolean result = dbHandler.deleteVoca(
               vocaBox.getText().toString());
        if (result)
        {
            idView.setText("Record Deleted");
            vocaBox.setText("");
            meanBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }

    public void mOnClick(View v){
        NumThread1 thread1 = new NumThread1();
        NumThread2 thread2 = new NumThread2();

        thread1.start();
        thread2.start();
    }

    Handler handler = new Handler(){
        int a=0;int b=0;int c=0;int d=0;int e=0;int f=0;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mMainText.setText(String.valueOf(mMainValue++));
                    a = (int) (Math.random() * 256);
                    b = (int) (Math.random() * 256);
                    c = (int) (Math.random() * 256);
                    mBackText.setTextColor(Color.rgb(a, b, c));
                    break;

                case 2:
                    mBackText.setText(String.valueOf(mBackValue++));
                    d = (int) (Math.random() * 256);
                    e = (int) (Math.random() * 256);
                    f = (int) (Math.random() * 256);
                    mMainText.setTextColor(Color.rgb(d, e, f));
                    break;
            }
        }
    };

    class NumThread1 extends Thread{
        public void run(){
            super.run();
            while(true){
                try {
                    Thread.sleep(1000);
                    handler.sendMessage(handler.obtainMessage(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class NumThread2 extends Thread{
        public void run(){
            super.run();
            while(true){
                try {
                    Thread.sleep(500);
                    handler.sendMessage(handler.obtainMessage(2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



