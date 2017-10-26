package edu.temple.lab8;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebFragment extends Fragment {
    private EditText urlInput;
    private Button execute;
    private WebView search;

    public static WebFragment newInstance() {
        WebFragment fragment = new WebFragment();
        return fragment;
    }

    public WebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web, container, false);

        urlInput = (EditText) view.findViewById(R.id.url);
        execute = (Button) view.findViewById(R.id.go);
        search = (WebView) view.findViewById(R.id.browser);

        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString();

                WebDataHandler urlData = new WebDataHandler(search);
                urlData.execute(url);
            }
        });

        return view;
    }
}