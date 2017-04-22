package com.example.android.binge;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.id;

/**
 * Created by Mikaila on 4/15/2017.
 */

public class AniListAPI {
    public void getAccessToken(String url, String id, String secret) {
        new RenewAccessToken().execute(url, id, secret);

    }

    private class RenewAccessToken extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String line;
            String result = "";

            try {
                URL url = new URL(params[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", "dev_client");

                OutputStreamWriter rw = new OutputStreamWriter(conn.getOutputStream());
                rw.write("grant_type=client_credentials&client_id=" + params[1] + "&client_secret=" + params[2]);
                rw.flush();

                BufferedReader rr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = rr.readLine()) != null) {
                    result += line;
                }
                rr.close();
                rw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }
}

//        Thread thread = new Thread(new Runnable(){
//            @Override
//            public void run(){
//                //code to do the HTTP reuest
//                String API_URL = "https://anilist.co/api/auth/access_token";
//                String id = context.getResources().getString(R.string.client_id);
//                String secret = context.getResources().getString(R.string.client_secret);
//                URL url;
//                HttpURLConnection conn; // The actual connection to the web page
//                BufferedReader rr; // Used to read results from the web page
//                String line; // An individual line of the web page HTML
//                String result = ""; // A long string containing all the HTML
//
//                try {
//                    //url = new URL(API_URL + "auth/access_token/?grant_type=client_credentials&client_id=" + id + "&client_secret=" + secret);
//                    url = new URL(API_URL);
//                    conn = (HttpURLConnection) url.openConnection();
//                    conn.setDoOutput(true);
//                    conn.setRequestMethod("POST");
//                    conn.setRequestProperty("User-Agent", "dev_client");
//                    Log.w("this is connection ",""+conn);
//                    OutputStreamWriter rw = new OutputStreamWriter(conn.getOutputStream());
//                    rw.write("grant_type=client_credentials&client_id=" + id + "&client_secret=" + secret);
//                    rw.flush();
//                    rr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    while ((line = rr.readLine()) != null) {
//                        result += line;
//                    }
//                    rr.close();
//                    rw.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //return result;
//            }
//        });
//
//        thread.start();
