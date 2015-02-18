package com.example.masahironishiyama.asyncsample1;

/**
 * Created by MasahiroNishiyama on 2015/02/18.
 */
public class MyAsyncTask implements Runnable {

    private SetTextView activity;

    public MyAsyncTask(SetTextView activity) {
        this.activity = activity;
    }

    @Override
    public void run() {
        activity.setTextAsync("Now processing...");
        String result = doTask();
        activity.setTextAsync(result);
    }

    public String doTask() {
        String result = "Done...";

        try {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            result = "Interrupted...";
        }
        return result;
    }
}
