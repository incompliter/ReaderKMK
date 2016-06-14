package ua.kr.kmk_kntu.reader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoActivity extends AppCompatActivity {
    private TextView information;
    private TextView date;
    private TextView author;
    private ArrayList<String> images = new ArrayList<>() ;
    private ListView listView;
    String[] img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        information = (TextView)findViewById(R.id.inform);
        listView = (ListView)findViewById(R.id.listView2);
        try{
            information.setText(getIntent().getStringExtra("info"));

            images= getIntent().getStringArrayListExtra("images");
        }catch (NullPointerException n){}
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item,R.id.item,images);
      listView.setAdapter(adapter);
        Log.d("msg",images.toString());
    }
    public class Images extends AsyncTask<List<String>,Void,Void>{

        @Override
        protected Void doInBackground(List<String>... params) {
            return null;
        }
    }
}
