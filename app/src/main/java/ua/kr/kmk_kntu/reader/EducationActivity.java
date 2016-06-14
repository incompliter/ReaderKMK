package ua.kr.kmk_kntu.reader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by cooper on 07.06.16.
 */
public class EducationActivity extends AppCompatActivity {
    private ListView listView;
    private HashMap<String,String> map = new HashMap<>();
    private Elements elements;
    private ArrayList<String> data =new ArrayList<>();
    private ArrayList<String> author= new ArrayList<>();
    private ArrayList<String> links = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        listView = (ListView)findViewById(R.id.listView);
        ParseTitle parseTitle = new ParseTitle();
        parseTitle.execute();
        try {
            final HashMap<String,String> hashMap =parseTitle.get();
            final ArrayList<String> arrayList = new ArrayList<>();
            for (Map.Entry entry :hashMap.entrySet()){
                arrayList.add(entry.getKey().toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item,R.id.item,  arrayList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ParseText parseText = new ParseText();
                    parseText.execute(hashMap.get(arrayList.get(position)));

                    try{
                        Intent intent = new Intent(EducationActivity.this,InfoActivity.class);
                        intent.putExtra("info",parseText.get());
                        intent.putExtra("date",data);
                        intent.putExtra("details",links);
                        startActivity(intent);
                    }
                    catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    class ParseText extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(EducationActivity.this,"Завантажується інформація",Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... params) {
            String str= null;
            try {
                Document document = Jsoup.connect(params[0]).get();
                Element elem= document.select(".eMessage").first();
                Elements date = document.select(".dateBar");
                Elements details = document.select(".eDetails");
                Elements elm = details.select("img[src]");
                for(Element element:elm){
                    links.add(element.attr("src"));

                }
                str = elem.text();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            return str;
        }
    }

    class ParseTitle extends AsyncTask<Void,Void,HashMap<String,String>> {
        @Override
        protected HashMap<String, String> doInBackground(Void... params) {

            Document doc;
            try {
                doc= Jsoup.connect("http://news-kmk-kntu.at.ua/news/navchalna_robota/"+getIntent().getStringExtra("page")+"-0-5").get();
                elements = doc.select(".eTitle");
                for(Element element:elements){
                    Element link = element.select("a[href]").first();
                    map.put(element.text(),link.attr("abs:href"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }
    }
}
