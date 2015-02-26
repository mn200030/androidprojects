package com.example.masahironishiyama.downloadimage;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpException;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.masahironishiyama.downloadimage.action.FOO";
    private static final String ACTION_BAZ = "com.example.masahironishiyama.downloadimage.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.masahironishiyama.downloadimage.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.masahironishiyama.downloadimage.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                Log.d("test", "bundle == null");
                return;
            }
            String urlString = bundle.getString("url");

            // HTTP Connection
            URL url = new URL(urlString);
            String fileName = getFilenameFromURL(url);
            URLConnection conn = url.openConnection();

            HttpURLConnection httpConn = (HttpURLConnection)conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            int response = httpConn.getResponseCode();

            // Check Response
            if (response != HttpURLConnection.HTTP_OK) {
                throw new HttpException();
            }
            int contentLength = httpConn.getContentLength();

            InputStream in = httpConn.getInputStream();
            FileOutputStream outStream = openFileOutput(fileName, MODE_PRIVATE);

            DataInputStream dataIn = new DataInputStream(in);
            DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(outStream));

            // Read Data
            byte[] b = new byte[4096];
            int readByte = 0, totalByte = 0;

            while ((readByte = dataIn.read(b)) != -1) {
                dataOut.write(b, 0, readByte);
                totalByte += readByte;
                sendProgressBroadcast(
                  contentLength,
                        totalByte,
                        fileName
                );
            }
            dataIn.close();
            dataOut.close();

            if (contentLength < 0) {
                sendProgressBroadcast(
                        totalByte,
                        totalByte,
                        fileName
                );
            }
        } catch (IOException e) {
            Log.d("test", "IOException");
        } catch (HttpException e) {
            Log.d("test", "HttpException");
        }
    }

    private void sendProgressBroadcast(
            int contentLength,
            int totalByte,
            String fileName
    ) {
        //BroadcastReceiverとの通信用Intent
        Intent broadcastIntent = new Intent();
        int completePercent = contentLength < 0 ?
                -1 : ((totalByte*1000)/(contentLength*10));

        broadcastIntent.putExtra("completePercent", completePercent);
        broadcastIntent.putExtra("totalByte", totalByte);
        broadcastIntent.putExtra("fileName", fileName);
        broadcastIntent.setAction("DOWNLOAD_PROGRESS_ACTION");
        getBaseContext().sendBroadcast(broadcastIntent);
    }

    private String getFilenameFromURL(URL url) {
        String[] p = url.getFile().split("/");
        String s = p[p.length-1];
        if (s.indexOf("?") > -1) {
            return s.substring(0, s.indexOf("?"));
        }
        return s;
    }

        /**
         * Handle action Foo in the provided background thread with the provided
         * parameters.
         */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
