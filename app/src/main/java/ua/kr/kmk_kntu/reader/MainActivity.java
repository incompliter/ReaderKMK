package ua.kr.kmk_kntu.reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by cooper on 07.06.16.
 */
public class MainActivity extends AppCompatActivity {
    private EditText pageNumber;
    private Button setPage;
    String page;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        pageNumber =(EditText)findViewById(R.id.editText);
        setPage= (Button)findViewById(R.id.setpage);
        setPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page= (pageNumber.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    public void goToNews(MenuItem item){
        Intent intent = new Intent(this,NewsActivity.class);
        intent.putExtra("page",page);
        startActivity(intent);
    }
    public void goToEducation(MenuItem item){
        Intent intent = new Intent(this,EducationActivity.class);
        intent.putExtra("page",page);
        startActivity(intent);
    }
    public void goToStudying(MenuItem item){
        Intent intent = new Intent(this,StudyingActivity.class);
        intent.putExtra("page",page);
        startActivity(intent);
    }

    public void goToAbout(MenuItem item) {
        Intent intent = new Intent(this,AboutAppActivity.class);
        startActivity(intent);
    }
}

