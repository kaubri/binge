package com.example.android.binge;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable logo to show in action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Makes search option visible in action bar upon options menu creation
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem item = menu.findItem(R.id.search);

        // Implement listener for user's search
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    Toast.makeText(getApplicationContext(), "Searching for " + query + "...", Toast.LENGTH_LONG).show();
                    AniListAPI ani = new AniListAPI();
                    ani.getAccessToken(getResources().getString(R.string.api_url),
                            getResources().getString(R.string.client_id),
                            getResources().getString(R.string.client_secret));
                }
                //searchAnime(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    // searchAnime will likely be moved to class "SearchAnime"
    // api calls done in class "AniListAPI"
    private String searchAnime(String search_query) {
        // function POSTClientCredentials - returns access_token
        return "null";
    }

/*    public void get_access_token() throws IOException {
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                //code to do the HTTP reuest
                String API_URL = "https://anilist.co/api/auth/access_token";
                String id = getResources().getString(R.string.client_id);
                String secret = getResources().getString(R.string.client_secret);
                URL url;
                HttpURLConnection conn; // The actual connection to the web page
                BufferedReader rr; // Used to read results from the web page
                String line; // An individual line of the web page HTML
                String result = ""; // A long string containing all the HTML

                try {
                    //url = new URL(API_URL + "auth/access_token/?grant_type=client_credentials&client_id=" + id + "&client_secret=" + secret);
                    url = new URL(API_URL);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("User-Agent", "dev_client");
                    Log.w("this is connection ",""+conn);
                    OutputStreamWriter rw = new OutputStreamWriter(conn.getOutputStream());
                    rw.write("grant_type=client_credentials&client_id=" + id + "&client_secret=" + secret);
                    rw.flush();
                    rr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = rr.readLine()) != null) {
                        result += line;
                    }
                    rr.close();
                    rw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //return result;
            }
        });

        thread.start();
    }*/

}
