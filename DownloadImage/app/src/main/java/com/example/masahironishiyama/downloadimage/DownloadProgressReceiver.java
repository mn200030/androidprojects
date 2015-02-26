package com.example.masahironishiyama.downloadimage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DownloadProgressReceiver extends BroadcastReceiver {
    private TextView progressView;
    private ImageView imageView;

    public DownloadProgressReceiver(){}

    public DownloadProgressReceiver(TextView progressView, ImageView imageView) {
        this.progressView = progressView;
        this.imageView = imageView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        int completePercent = bundle.getInt("completePercent");
        int totalByte = bundle.getInt("totalByte");
        String progressString = totalByte + " byte read.";

        if (completePercent > 0) {
            progressString += "[" + completePercent + "%]";
        }
        progressView.setText(progressString);

        // If completed, show the picture
        if (completePercent == 100) {
            String fileName = bundle.getString("fileName");
            Bitmap bitmap = BitmapFactory.decodeFile("/data/data/com.example.masahironishiyama.downloadimage/files/" + fileName);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
