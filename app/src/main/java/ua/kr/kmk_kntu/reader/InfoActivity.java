package ua.kr.kmk_kntu.reader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class InfoActivity extends AppCompatActivity {
    private TextView information;
    private TextView date;
    private TextView author;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        information = (TextView)findViewById(R.id.inform);
       /* date = (TextView)findViewById(R.id.textView);*/
        try{
        /*    date.setText(getIntent().getStringExtra("details"));*/
            information.setText(getIntent().getStringExtra("info"));
        }catch (NullPointerException n){

        }
    }
}
