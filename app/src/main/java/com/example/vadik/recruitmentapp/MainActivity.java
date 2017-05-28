package com.example.vadik.recruitmentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Main activity class
 */

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RecyclerItem> data;
    Button startButton;
    Button stopButton;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<>();
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        thread = new Thread(new MyRunnable());

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   if (thread.getState() == Thread.State.TERMINATED) {
                       thread = new Thread(new MyRunnable());
                       thread.start();
                   }
                   thread.start();
               } catch(IllegalThreadStateException e){
                    Log.e("OnClickStartButton" , e.getMessage());
               }
            }
        });

        stopButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new ListAdapter(data, getApplicationContext()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {

            while(!thread.isInterrupted()){
                try {
                    Thread.sleep(1000);
                    if(data.size()<5){
                        int random = (int) (Math.random() * 10);
                        Colour colour;
                        if (random > 5) {
                            colour = Colour.BLUE;
                        } else{
                            colour = Colour.RED;
                        }
                        data.add(0, new RecyclerItem(0, colour));
                    } else{
                        int randomElement = (int) (Math.random()*4);
                        int random = (int) (Math.random()*100);
                        if(random<5){
                            if(randomElement == 0){
                                RecyclerItem item = data.get(data.size()-1);
                                int counter = item.getCounter();
                                item.setCounter(++counter);
                                data.set(data.size()-1, item);
                            }else{
                                RecyclerItem item = data.get(randomElement-1);
                                int counter = item.getCounter();
                                item.setCounter(++counter);
                                data.set(randomElement-1, item);
                            }
                        } else if (random < 15) {
                            data.remove(randomElement);
                        } else if (random < 50){
                            RecyclerItem item = data.get(randomElement);
                            int counter = item.getCounter();
                            item.setCounter(0);
                            data.set(randomElement, item);
                        } else{
                            RecyclerItem item = data.get(randomElement);
                            int counter = item.getCounter();
                            item.setCounter(++counter);
                            data.set(randomElement, item);
                        }
                    }
                } catch (InterruptedException e) {
                    Log.e("OnClickStartButton", e.toString());
                    return;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(new ListAdapter(data, getApplicationContext()));
                    }
                });
            }
        }
    }
}