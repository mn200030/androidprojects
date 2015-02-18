package com.example.masahironishiyama.asyncsample1;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;


public class MainActivity extends ActionBarActivity
        implements View.OnClickListener, SetTextView {

    TextView resultTextView;
    Button testButton;

    Handler guiThreadHandler;
    ExecutorService asyncExecutorService;
    Runnable starterTask;
    Future taskPending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();

        guiThreadHandler = new Handler();
        asyncExecutorService = Executors.newSingleThreadExecutor();
        starterTask = new Runnable() {
            @Override
            public void run() {
                try {
                    MyAsyncTask asyncTask =
                            new MyAsyncTask(MainActivity.this);
                    asyncExecutorService.submit(asyncTask);
                } catch (RejectedExecutionException e) {
                    resultTextView.setText("Error");
                }
            }
        };
    }

    protected void findViews(){
        resultTextView = (TextView)findViewById(R.id.result);
        testButton = (Button)findViewById(R.id.test_button);
    }

    protected void setListeners(){
        testButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_button:
                startAsyncTask();
                break;
        }
    }

    protected void startAsyncTask() {
        guiThreadHandler.removeCallbacks(starterTask);
        guiThreadHandler.postDelayed(starterTask, 200);
    }

    @Override
    public void setTextAsync(final String text) {
        guiThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                resultTextView.setText(text);
            }
        });
    }
}
