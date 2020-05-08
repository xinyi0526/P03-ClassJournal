package com.myapplicationdev.android.p03_classjournal;

import androidx.annotation.Nullable;
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
    int requestCode1 =1;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<DailyCA> dailyCA;
    Button btnInfo,btnAdd,btnEmail;
    String moduleCode;
    String emailBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        lv = (ListView) findViewById(R.id.lvDailyGrades);
        dailyCA = new ArrayList<DailyCA>();
        dailyCA.add(new DailyCA("B",1));
        dailyCA.add(new DailyCA("C",2));
        dailyCA.add(new DailyCA("A",3));
        btnInfo = (Button) this.findViewById(R.id.buttonInfo);
        btnAdd = (Button) this.findViewById(R.id.buttonAdd);
        btnEmail = (Button) this.findViewById(R.id.buttonEmail);

        Intent i = getIntent();
        String faciEmail = i.getStringExtra("email");
        moduleCode = i.getStringExtra("moduleCode");
        setTitle("Info for " + moduleCode);

        final String one = faciEmail.toString();


        aa = new DailyCAAdapter(this,R.layout.row,dailyCA);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailsActivity.this, AddWeek.class);
                i.putExtra("week",dailyCA.size() +1);
                startActivityForResult(i,requestCode1);
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
                int count = dailyCA.size();
                for(int x = 0; x<count;x++){
                    DailyCA cd = dailyCA.get(x);
                    emailBody += "Week " + cd.getWeek() + " DG " + cd.getDgGrade() +"\n";
                }
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{one});
                email.putExtra(Intent.EXTRA_TEXT,"Hi faci, \nI am ...\n Please see my remarks so far, Thank you!\n"  + emailBody);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Choose an Email client:"));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == requestCode1){
                DailyCA newDG = (DailyCA) data.getSerializableExtra("newca");
                dailyCA.add(newDG);
                aa.notifyDataSetChanged();

            }
        }
    }
}
