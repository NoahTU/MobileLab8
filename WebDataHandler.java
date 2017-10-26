package edu.temple.lab8;

import android.os.AsyncTask;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDataHandler extends AsyncTask<String, Void, String>{
    private WebView search;
    String input;

    public WebDataHandler(WebView browser){
        this.search = browser;
    }

    @Override
    protected String doInBackground(String... params){
        try {
            String input = params[0];
            input = cleanUrl(input);

            URL url = new URL(input);
            InputStreamReader isr = new InputStreamReader(url.openStream());

            BufferedReader reader = new BufferedReader(isr);

            StringBuilder response = new StringBuilder();

            while((this.input = reader.readLine()) != null){
                response.append(this.input);
            }

            reader.close();

            return response.toString();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result){
        if(this.search != null && result != null){
            this.search.loadData(result, "text/html", "UTF-8");
        }
    }

    public String cleanUrl(String url){
        url = url.toLowerCase();

        if(url.startsWith("http://") || url.startsWith("https://")){
            return url;
        }

        return "http://" + url;
    }
}