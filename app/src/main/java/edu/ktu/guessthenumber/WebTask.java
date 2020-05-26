package edu.ktu.guessthenumber;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/*public interface WebRequestFinished
(

        )
public class WebTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
            if (url.length > 0) {
                try {
                    URL urlObject = new URL(url[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader((new InputStreamReader(in)));
                    String line = null;
                    while ((line = reader.readLine() != null)) {
                        result += line;

                    }
                    httpURLConnection.disconnect();
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
            return  result;
    }

    @Override
    protected void onPostExecute(String s) {
        if()
    }


}*/
