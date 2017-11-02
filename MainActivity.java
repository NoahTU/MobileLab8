package edu.temple.lab8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FrameLayout page;
    private ArrayList<Fragment> savedURLS;
    private int count;
    Fragment tab = WebFragment.newInstance("");
    EditText urlInput;
    WebView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = (FrameLayout) findViewById(R.id.web);



        savedURLS = new ArrayList<>();
        count = 0;

        //Fragment tab = WebFragment.newInstance();

        String test=getIntent().getDataString();

        if (test !=null){
            System.out.println("Yo");
            System.out.println(test);
            tab = WebFragment.newInstance(test);
            savedURLS.add(tab);
            count++;
            loadTab(R.id.web, tab);

            //onOptionsItemSelected(R.id.newTab);


           search = (WebView) findViewById(R.id.browser);
           // urlInput = (EditText) findViewById(R.id.url);

           String url = test;
           // urlInput.setText(test);

            WebDataHandler urlData = new WebDataHandler(search);
            urlData.execute(url);

            //return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


       /*if (!getIntent().getData().toString().equals(null)){
            tab = WebFragment.newInstance();
            savedURLS.add(tab);
            count++;
            loadTab(R.id.web, tab);

            EditText urlInput = (EditText) findViewById(R.id.url);
            WebView search = (WebView) findViewById(R.id.browser);

            String url = getIntent().getData().toString();

            WebDataHandler urlData = new WebDataHandler(search);
            urlData.execute(url);
            return true;
        }*/


        switch(id){

            case (R.id.newTab): //new tab
                tab = WebFragment.newInstance("");
                savedURLS.add(tab);
                count++;
                loadTab(R.id.web, tab);
                return true;

            case (R.id.previous):// back
                if(count > 1){
                    count--;
                    loadTab(R.id.web, savedURLS.get(count-1));

                    urlInput = (EditText) findViewById(R.id.url);
                    search = (WebView) findViewById(R.id.browser);

                    String url = urlInput.getText().toString();

                    WebDataHandler urlData = new WebDataHandler(search);
                    urlData.execute(url);
                }
                return true;

            case (R.id.forward): //foward
                if(count < savedURLS.size()){
                    loadTab(R.id.web, savedURLS.get(count));


                    urlInput = (EditText) findViewById(R.id.url);
                    search = (WebView) findViewById(R.id.browser);

                    String url = urlInput.getText().toString();

                    WebDataHandler urlData = new WebDataHandler(search);
                    urlData.execute(url);


                    count++;
                }
                else{ //if no tab ahead, create new one
                    tab = WebFragment.newInstance("");
                    savedURLS.add(tab);
                    count++;
                    loadTab(R.id.web, tab);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadTab(int frame, Fragment frag){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(frame, frag);
        transaction.commit();
        manager.executePendingTransactions();
    }
}
