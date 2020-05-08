package com.myapplicationdev.android.p03_classjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    int requestCode =1;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<DailyCA> dailyCA;
    Button btnInfo,btnAdd,btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        lv = (ListView) findViewById(R.id.lvModuleCode);
        dailyCA = new ArrayList<DailyCA>();
        dailyCA.add(new DailyCA("A",1));
        dailyCA.add(new DailyCA("B",2));
        dailyCA.add(new DailyCA("C",3));
        btnInfo = (Button) this.findViewById(R.id.buttonInfo);
        btnAdd = (Button) this.findViewById(R.id.buttonAdd);
        btnEmail = (Button) this.findViewById(R.id.buttonEmail);

        Intent i = getIntent();
        DailyCA module = (DailyCA) i.getSerializableExtra("classdetails");
        final String femail = i.getStringExtra("email");


        aa = new DailyCAAdapter(this,R.layout.row,dailyCA);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, AddWeek.class);
                i.putExtra("week",dailyCA.size() +1);
                startActivityForResult(i,requestCode);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.rp.edu.sg/soi/full-time-diplomas/details/diploma-in-digital-design-and-development"));
                startActivity(i);
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{femail});
                email.putExtra(Intent.EXTRA_TEXT,"Hi faci, I am ... Please see my remarks so far,thank you!");
                email.putExtra("dailyCA",DetailsActivity.this.getClass());
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Choose an Email client:"));
            }
        });


    }
}
